
package br.feevale.projetosismu.util;

import br.feevale.projetosismu.dao.CategoriaDAO;
import br.feevale.projetosismu.entity.Categoria;

public class TesteConexao {

    public static void main(String[] args) {

        FabricaConexao c = new FabricaConexao();
        c.getConexao();
        
        Categoria cat = new Categoria();
        CategoriaDAO dao = new CategoriaDAO();
        
        cat.setIdCategoria(10);
        cat.setDescricao("Novo valor");
        
        dao.insertCategoria(cat);
        
        String listaCategorias = dao.selectCategorias();
        
        System.out.println(listaCategorias);
           
    }

    
}
