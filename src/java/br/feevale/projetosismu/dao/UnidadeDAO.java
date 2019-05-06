
package br.feevale.projetosismu.dao;

import br.feevale.projetosismu.entity.Unidade;
import br.feevale.projetosismu.util.FabricaConexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UnidadeDAO {
    public void insertUnidade(Unidade unidade){
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareCall("INSERT INTO UNIDADE (IDUNIDADE, DESCRICAO, TAMANHO, HISTORIA, HISTORIADOADOR, "
                    + "FABRICANTE, ORIGEM, DATADOACAO, CODDOADOR, NPATRIMONIO, PACOTE, CODCATEGORIA, VALORNF, CODREPLEGAL) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, unidade.getIdUnidade());
            ps.setString(2, unidade.getDescricao());
            ps.setString(3, unidade.getTamanho());
            ps.setString(4, unidade.getHistoria());
            ps.setString(5, unidade.getHistoriaDoador());
            ps.setString(6, unidade.getFabricante());
            ps.setString(7, unidade.getOrigem());
            ps.setDate(8, unidade.getDataDoacao());
            if (unidade.getCodDoador() == null) {
                ps.setNull(9, Types.INTEGER);
            } else {
                ps.setInt(9, unidade.getCodDoador());
            }
            ps.setInt(10, unidade.getnPatrimonio());
            ps.setString(11, unidade.getPacote());
            ps.setInt(12, unidade.getCodCategoria());
            ps.setFloat(13, unidade.getValorNf());
            ps.setInt(14, unidade.getCodRepLegal());  
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ExpositorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void updateUnidade(Unidade unidade){
        Connection conexao = FabricaConexao.getConexao();
        try {
            PreparedStatement ps = conexao.prepareStatement("UPDATE UNIDADE SET DESCRICAO=?, TAMANHO=?, HISTORIA=?,"
                    + " HISTORIADOADOR=?, FABRICANTE=?, ORIGEM=?, DATADOACAO=?, CODDOADOR=?, NPATRIMONIO=?, PACOTE=?,"
                    + " CODCATEGORIA=?, VALORNF=?, CODREPLEGAL=? WHERE IDUNIDADE =?");
            ps.setString(1, unidade.getDescricao());
            ps.setString(2, unidade.getTamanho());
            ps.setString(3, unidade.getHistoria());
            ps.setString(4, unidade.getHistoriaDoador());
            ps.setString(5, unidade.getFabricante());
            ps.setString(6, unidade.getOrigem());
            ps.setDate(7, unidade.getDataDoacao());
            ps.setInt(8, unidade.getCodDoador());
            ps.setInt(9, unidade.getnPatrimonio());
            ps.setString(10, unidade.getPacote());
            ps.setInt(11, unidade.getCodCategoria());
            ps.setFloat(12, unidade.getValorNf());
            ps.setInt(13, unidade.getCodRepLegal());  
            ps.setInt(14, unidade.getIdUnidade());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        FabricaConexao.fecharConexao();
    }
    
    public void deleteUnidade(Integer codigo){
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            ps = conexao.prepareStatement("DELETE FROM UNIDADE WHERE IDUNIDADE = ?");
            ps.setInt(1, codigo);
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String selectCodUnidade(int codigo){
        String unidade = "";
        try {
            Connection conexao = FabricaConexao.getConexao();
            String consulta = "SELECT * FROM UNIDADE WHERE IDUNIDADE = ?";
            PreparedStatement ps = conexao.prepareStatement(consulta);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                unidade += rs.getInt("IDUNIDADE")+"|";
                unidade += rs.getString("DESCRICAO")+"|";
                unidade += rs.getString("TAMANHO")+"|";
                unidade += rs.getString("HISTORIA")+"|";
                unidade += rs.getString("HISTORIADOADOR")+"|";
                unidade += rs.getString("FABRICANTE")+"|";
                unidade += rs.getString("ORIGEM")+"|";
                unidade += rs.getString("DATADOACAO")+"|";
                unidade += rs.getInt("CODDOADOR")+"|";
                unidade += rs.getInt("NPATRIMONIO")+"|";
                unidade += rs.getString("PACOTE")+"|";
                unidade += rs.getInt("CODCATEGORIA")+"|";
                unidade += rs.getFloat("VALORNF")+"|";
                unidade += rs.getInt("CODREPLEGAL"); 
            }
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return unidade;
    }
    
    public String selectDescUnidade(String descricao){
        String unidade = "";
        try {
            Connection conexao = FabricaConexao.getConexao();
            String consulta = "SELECT * FROM UNIDADE WHERE DESCRICAO LIKE %?%";
            PreparedStatement ps = conexao.prepareStatement(consulta);
            ps.setString(1, descricao);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                unidade += rs.getInt("IDUNIDADE")+"|";
                unidade += rs.getString("DESCRICAO")+"|";
                unidade += rs.getString("TAMANHO")+"|";
                unidade += rs.getString("HISTORIA")+"|";
                unidade += rs.getString("HISTORIADOADOR")+"|";
                unidade += rs.getString("FABRICANTE")+"|";
                unidade += rs.getString("ORIGEM")+"|";
                unidade += rs.getDate("DATADOACAO")+"|";
                unidade += rs.getInt("CODDOADOR")+"|";
                unidade += rs.getInt("NPATRIMONIO")+"|";
                unidade += rs.getString("PACOTE")+"|";
                unidade += rs.getInt("CODCATEGORIA")+"|";
                unidade += rs.getFloat("VALORNF")+"|";
                unidade += rs.getInt("CODREPLEGAL")+"\n"; 
            }
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return unidade;
    }
    
    public String selectUnidades(){
        String unidade = "";
        try {
            Connection conexao = FabricaConexao.getConexao();
            String consulta = "SELECT IDUNIDADE, DESCRICAO FROM UNIDADE";
            PreparedStatement ps = conexao.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                unidade += rs.getInt("IDUNIDADE")+"|";
                unidade += rs.getString("DESCRICAO")+"\n";
            }
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return unidade;
    }
    
    public String selectUnidadesLivres(Date inicio, Date fim){
        String unidade = "";
        try {
            Connection conexao = FabricaConexao.getConexao();
            String consulta = "SELECT UN.IDUNIDADE, UN.DESCRICAO FROM UNIDADE UN WHERE IDUNIDADE NOT IN (SELECT EX.CODUNIDADE FROM EXPOSICAO EX WHERE EX.DATAINICIO BETWEEN ? AND ? OR EX.DATAFIM BETWEEN ? AND ? AND EX.CODUNIDADE = UN.IDUNIDADE)";
            PreparedStatement ps = conexao.prepareStatement(consulta);
            ps.setDate(1, inicio);
            ps.setDate(2, fim);
            ps.setDate(3, inicio);
            ps.setDate(4, fim);
            System.out.println(ps.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                unidade += rs.getInt("IDUNIDADE")+"|";
                unidade += rs.getString("DESCRICAO")+"\n";
            }
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return unidade;
    }
}