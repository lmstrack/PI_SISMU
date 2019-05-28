
package br.feevale.projetosismu.dao;

import br.feevale.projetosismu.entity.Exposicao;
import br.feevale.projetosismu.util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExposicaoDAO {
    
    public void insertExposicao(Exposicao exposicao){
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareCall("INSERT INTO EXPOSICAO (IDEXPOSICAO, DATAINICIO, DATAFIM, CODEXPOSITOR, CODUNIDADE) "
                    + "VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, exposicao.getIdExposicao());
            ps.setDate(2, exposicao.getDataInicio());
            ps.setDate(3, exposicao.getDataFim());
            ps.setInt(4, exposicao.getCodExpositor());
            ps.setInt(5, exposicao.getCodUnidade());
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ExpositorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void updateExposicao(Exposicao exposicao) {
        Connection conexao = FabricaConexao.getConexao();
        try {
            PreparedStatement ps = conexao.prepareStatement("UPDATE EXPOSICAO SET DATAINICIO=?,"
                    + " DATAFIM=?, CODEXPOSITOR=?, CODUNIDADE=? WHERE IDEXPOSICAO =?");
            ps.setDate(1, exposicao.getDataInicio());
            ps.setDate(2, exposicao.getDataFim());
            ps.setInt(3, exposicao.getCodExpositor());
            ps.setInt(4, exposicao.getCodUnidade());
            ps.setInt(5, exposicao.getIdExposicao());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ExposicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        FabricaConexao.fecharConexao();
    }
    
    public void deleteExposicao(Integer codigo){
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            ps = conexao.prepareStatement("DELETE FROM EXPOSICAO WHERE IDEXPOSICAO = ?");
            ps.setInt(1, codigo);
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ExposicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String selectExposicao(int codigo){
        String exposicao = "";
        try {
            Connection conexao = FabricaConexao.getConexao();
            String consulta = "SELECT * FROM EXPOSICAO WHERE IDEXPOSICAO = ?";
            PreparedStatement ps = conexao.prepareStatement(consulta);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                exposicao += rs.getInt("IDEXPOSICAO")+"|";
                exposicao += rs.getString("DATAINICIO")+"|";
                exposicao += rs.getString("DATAFIM")+"|";
                exposicao += rs.getInt("CODEXPOSITOR")+"|";
                exposicao += rs.getInt("CODUNIDADE"); 
            }
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ExposicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exposicao;
    }
    
    public String selectExposicoes(){
        String exposicoes = "";
        String data;
        String dataFormatada;
        try {
            Connection conexao = FabricaConexao.getConexao();
                String consulta = "SELECT IDEXPOSICAO, DATAINICIO, DATAFIM, CODEXPOSITOR, EXPOSITOR.DESCRICAO DESCEXPOSITOR, CODUNIDADE, UNIDADE.DESCRICAO DESCUNIDADE FROM EXPOSICAO INNER JOIN EXPOSITOR ON EXPOSITOR.IDEXPOSITOR = EXPOSICAO.CODEXPOSITOR INNER JOIN UNIDADE ON UNIDADE.IDUNIDADE = EXPOSICAO.CODUNIDADE ORDER BY EXPOSICAO.CODEXPOSITOR, EXPOSICAO.DATAINICIO ASC";
            PreparedStatement ps = conexao.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                exposicoes += rs.getString("IDEXPOSICAO")+"|";
                exposicoes += rs.getString("CODEXPOSITOR")+"-"+rs.getString("DESCEXPOSITOR")+"|";
                exposicoes += rs.getString("CODUNIDADE")+"-"+rs.getString("DESCUNIDADE")+"|";
                data = rs.getString("DATAINICIO");
                dataFormatada = data.substring(8, 10) + "/" + data.substring(5, 7) + "/" + data.substring(0, 4);
                exposicoes += dataFormatada+"|";
                data = rs.getString("DATAFIM");
                dataFormatada = data.substring(8, 10) + "/" + data.substring(5, 7) + "/" + data.substring(0, 4);
                exposicoes += dataFormatada+"\n";
            }
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ExposicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exposicoes;
    }
}
