
package br.feevale.projetosismu.dao;

import br.feevale.projetosismu.entity.Unidade;
import br.feevale.projetosismu.util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UnidadeDAO {
    public void insertUnidade(Unidade unidade){
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareCall("INSERT INTO UNIDADE (IDUNIDADE, DESCRICAO, TAMANHO, HISTORIA, HISTORIADOADOR, "
                    + "FABRICANTE, ORIGEM, DATADOACAO, CODDOADOR, NPATRIMONIO, PACOTE, CODCATEGORIA, VALORNF, IDDOADOR, IDREPRESENTANTELEGAL) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, unidade.getIdUnidade());
            ps.setString(2, unidade.getDescricao());
            ps.setString(3, unidade.getTamanho());
            ps.setString(4, unidade.getHistoria());
            ps.setString(5, unidade.getHistoriaDoador());
            ps.setString(6, unidade.getFabricante());
            ps.setString(7, unidade.getOrigem());
            ps.setDate(8, unidade.getDataDoacao());
            ps.setInt(9, unidade.getCodDoador());
            ps.setInt(10, unidade.getnPatrimonio());
            ps.setString(11, unidade.getPacote());
            ps.setInt(12, unidade.getCodCategoria());
            ps.setInt(13, (int) unidade.getValorNf());
            ps.setInt(14, unidade.getIdDoador());
            ps.setInt(15, unidade.getIdRepresentanteLegal());  
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ExpositorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void updateUnidade(Unidade unidade){
    }
    
    public void deleteUnidade(Integer codigo){
    }
    
    public void selectUnidade(int codigo){
    }
    
    public void selectUnidade(String descricao){
    }
    
    public void selectUnidades(Unidade unidade){
    }
}
