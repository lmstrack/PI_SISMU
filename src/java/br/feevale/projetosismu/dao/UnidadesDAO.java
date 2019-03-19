package br.feevale.projetosismu.dao;

import br.feevale.projetosismu.util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class UnidadesDAO {
    
    public void salvar( Unidades unidade ){
        Connection conexao = FabricaConexao.getConexao();
        /*PreparedStatement ps = conexao.prepareCall(null);
        ps.setString(1, null);*/
       
    }
    
}
