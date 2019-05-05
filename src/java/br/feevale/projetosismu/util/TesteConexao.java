
package br.feevale.projetosismu.util;

import br.feevale.projetosismu.dao.ExposicaoDAO;
import br.feevale.projetosismu.dao.ExpositorDAO;
import br.feevale.projetosismu.dao.UnidadeDAO;
import br.feevale.projetosismu.entity.Exposicao;
import br.feevale.projetosismu.entity.Expositor;
import br.feevale.projetosismu.entity.Unidade;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TesteConexao {

    public static void main(String[] args) {

        FabricaConexao c = new FabricaConexao();
        c.getConexao();
        
        Exposicao exp = new Exposicao();
        ExposicaoDAO expDAO = new ExposicaoDAO();
        
        exp.setIdExposicao(1);
        exp.setCodExpositor(1);
        exp.setCodUnidade(1);

        /*String dataInicio, dataFim;
        
        dataInicio = "2019-01-01";
        dataFim = "2019-02-01";
                
//      Tratamento de Data
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); // New Pattern
        java.util.Date date = null;
        try {
            date = sdf1.parse(dataInicio); // Returns a Date format object with the pattern
            java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
            exp.setDataInicio(sqlStartDate);
        } catch (ParseException ex){
            System.out.println("Erro ao converter data");
        }

//      Tratamento de Data
        SimpleDateFormat sdf12 = new SimpleDateFormat("yyyy-MM-dd"); // New Pattern
        java.util.Date date2 = null;
        try {
            date2 = sdf12.parse(dataFim); // Returns a Date format object with the pattern
            java.sql.Date sqlStartDate = new java.sql.Date(date2.getTime());
            exp.setDataFim(sqlStartDate);
        } catch (ParseException ex){
            System.out.println("Erro ao converter data");
        }

        
        expDAO.insertExposicao(exp);*/
        expDAO.deleteExposicao(1);
        //System.out.println(expDAO.selectExposicao(1));
        
        c.fecharConexao();
        
    }
}
