
package br.feevale.projetosismu.util;

import br.feevale.projetosismu.dao.ExpositorDAO;
import br.feevale.projetosismu.entity.Expositor;

public class TesteConexao {

    public static void main(String[] args) {

        FabricaConexao c = new FabricaConexao();
        c.getConexao();

        Expositor expo = new Expositor();
        ExpositorDAO expDAO = new ExpositorDAO();
        
        expo.setIdExpositor(1);
        expo.setDescricao("Teste de insert");
        
        expDAO.insertExpositor(expo);
        
                
        c.fecharConexao();
    }

    
}
