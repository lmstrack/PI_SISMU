
package br.feevale.projetosismu.util;

import br.feevale.projetosismu.dao.ExpositorDAO;
import br.feevale.projetosismu.entity.Expositor;

public class TesteConexao {

    public static void main(String[] args) {

        FabricaConexao c = new FabricaConexao();
        c.getConexao();
        
                
        c.fecharConexao();
    }

    
}
