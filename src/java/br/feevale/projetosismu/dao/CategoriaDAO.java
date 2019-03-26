
package br.feevale.projetosismu.dao;

import br.feevale.projetosismu.entity.Categoria;
import br.feevale.projetosismu.util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriaDAO {

    public void insertCategoria(Categoria categoria){
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareCall("INSERT INTO CATEGORIA (idcategoria,descricao) VALUES (?,?)");
            ps.setInt(1, categoria.getIdCategoria());
            ps.setString(2, categoria.getDescricao());
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void updateCategoria(Categoria categoria){
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("UPDATE CATEGORIA SET DESCRICAO = ? WHERE IDCATEGORIA = ?");
            ps.setString(1, categoria.getDescricao());
            ps.setInt(2, categoria.getIdCategoria());
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteCategoria(int codigo){
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM CATEGORIA WHERE IDCATEGORIA = ?");
            ps.setInt(1, codigo);
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String selectCategoria(int codigo){
        String categoria = "";
        try {
            Connection conexao = FabricaConexao.getConexao();
            String consulta = "SELECT IDCATEGORIA, DESCRICAO FROM CATEGORIA WHERE IDCATEGORIA = ?";
            PreparedStatement ps = conexao.prepareStatement(consulta);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                categoria += rs.getInt("IDCATEGORIA")+"|";
                categoria += rs.getString("DESCRICAO");
            }
            FabricaConexao.fecharConexao();
            return categoria;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoria;
    }
    
    public String selectCategorias(){
        String categorias = "";
        try {
            Connection conexao = FabricaConexao.getConexao();
            String consulta = "SELECT IDCATEGORIA, DESCRICAO FROM CATEGORIA";
            PreparedStatement ps = conexao.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                categorias += rs.getInt("IDCATEGORIA")+"|";
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
