
package br.feevale.projetosismu.dao;

import br.feevale.projetosismu.entity.Doador;
import br.feevale.projetosismu.util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoadorDAO {

    public void insertDoador(Doador doador){
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareCall("INSERT INTO DOADOR (iddoador, nome, cidade, cpf_cnpj, endereco, identidade) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, doador.getIdDoador());
            ps.setString(2, doador.getNome());
            ps.setString(3, doador.getCidade());
            ps.setString(4, doador.getCpf_cnpj());
            ps.setString(5, doador.getEndereco());
            ps.setString(6, doador.getIdentidade());
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(DoadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void updateDoador(Doador doador){
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("UPDATE DOADOR SET NOME = ?, CIDADE = ?, CPF_CNPJ = ?, ENDERECO = ?, IDENTIDADE = ? WHERE IDDOADOR = ?");
            ps.setString(1, doador.getNome());
            ps.setString(2, doador.getCidade());
            ps.setString(3, doador.getCpf_cnpj());
            ps.setString(4, doador.getEndereco());
            ps.setString(5, doador.getIdentidade());
            ps.setInt(6, doador.getIdDoador());
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(DoadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteDoador(int codigo){
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("DELETE FROM DOADOR WHERE IDDOADOR = ?");
            ps.setInt(1, codigo);
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(DoadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String selectDoador(int codigo){
        String doador = "";
        try {
            Connection conexao = FabricaConexao.getConexao();
            String consulta = "SELECT IDDOADOR, NOME, CIDADE, CPF_CNPJ, ENDERECO, IDENTIDADE FROM DOADOR WHERE IDDOADOR = ?";
            PreparedStatement ps = conexao.prepareStatement(consulta);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                doador += rs.getInt("IDDOADOR")+"|";
                doador += rs.getString("NOME")+"|";
                doador += rs.getString("CIDADE")+"|";
                doador += rs.getString("CPF_CNPJ")+"|";
                doador += rs.getString("ENDERECO")+"|";
                doador += rs.getString("IDENTIDADE");
            }
            FabricaConexao.fecharConexao();
            return doador;
        } catch (SQLException ex) {
            Logger.getLogger(DoadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doador;
    }
    
    public String selectDoadores(){
        String doadores = "";
        try {
            Connection conexao = FabricaConexao.getConexao();
            String consulta = "SELECT IDDOADOR, NOME FROM DOADOR";
            PreparedStatement ps = conexao.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                doadores += rs.getInt("IDDOADOR")+"|";
                doadores += rs.getString("NOME")+"\n";
            }
            FabricaConexao.fecharConexao();
            return doadores;
        } catch (SQLException ex) {
            Logger.getLogger(DoadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doadores;
    }
    
}
