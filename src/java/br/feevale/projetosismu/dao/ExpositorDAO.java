package br.feevale.projetosismu.dao;

import br.feevale.projetosismu.entity.Expositor;
import br.feevale.projetosismu.util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ExpositorDAO {
    public void insertExpositor(Expositor expositor){
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareCall("INSERT INTO EXPOSITOR (idexpositor,descricao) VALUES (?,?)");
            ps.setInt(1, expositor.getIdExpositor());
            ps.setString(2, expositor.getDescricao());
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ExpositorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void updateExpositor(Expositor expositor){
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("UPDATE EXPOSITOR SET DESCRICAO = ? WHERE IDEXPOSITOR = ?");
            ps.setString(1, expositor.getDescricao());
            ps.setInt(2, expositor.getIdExpositor());
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteExpositor(int codigo){
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM EXPOSITOR WHERE IDEXPOSITOR = ?");
            ps.setInt(1, codigo);
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String selectExpositor(int codigo){
        String categoria = "";
        try {
            Connection conexao = FabricaConexao.getConexao();
            String consulta = "SELECT IDEXPOSITOR, DESCRICAO FROM EXPOSITOR WHERE IDEXPOSITOR = ?";
            PreparedStatement ps = conexao.prepareStatement(consulta);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                categoria += rs.getInt("IDEXPOSITOR")+"|";
                categoria += rs.getString("DESCRICAO");
            }
            FabricaConexao.fecharConexao();
            return categoria;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoria;
    }
    
    public String selectExpositores(){
        String categorias = "";
        try {
            Connection conexao = FabricaConexao.getConexao();
            String consulta = "SELECT IDEXPOSITOR, DESCRICAO FROM EXPOSITOR";
            PreparedStatement ps = conexao.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                categorias += rs.getInt("IDEXPOSITOR")+"|";
                categorias += rs.getString("DESCRICAO")+"\n";
            }
            FabricaConexao.fecharConexao();
            return categorias;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorias;
    }
}
