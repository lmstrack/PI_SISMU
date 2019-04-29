
package br.feevale.projetosismu.util;

import br.feevale.projetosismu.dao.ExpositorDAO;
import br.feevale.projetosismu.dao.UnidadeDAO;
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
        
        Unidade uni = new Unidade();
        UnidadeDAO uniD = new UnidadeDAO();
        
        uni.setIdUnidade(1);
        uni.setDescricao("Unidade 1");
        uni.setTamanho("Grande");
        uni.setHistoria("Lorem Ipsum");
        uni.setHistoriaDoador("Lorem Ipsum");
        uni.setFabricante("DELL");
        uni.setOrigem("Doação");
        
        String startDate="15/04/2019"; // Input String
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy"); // New Pattern
        java.util.Date date = null;
        try {
            date = sdf1.parse(startDate); // Returns a Date format object with the pattern
        } catch (ParseException ex) {
            Logger.getLogger(TesteConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
        
        uni.setDataDoacao(sqlStartDate);
        
        uni.setCodDoador(2);
        uni.setnPatrimonio(10);
        uni.setPacote("Teste");
        uni.setCodCategoria(2);
        uni.setValorNf(0);
        uni.setCodRepLegal(1);
        
        uniD.insertUnidade(uni);     
        
        c.fecharConexao();
        
    }
}
