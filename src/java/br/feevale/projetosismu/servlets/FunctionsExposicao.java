
package br.feevale.projetosismu.servlets;

import br.feevale.projetosismu.dao.ExposicaoDAO;
import br.feevale.projetosismu.dao.ExpositorDAO;
import br.feevale.projetosismu.dao.UnidadeDAO;
import br.feevale.projetosismu.entity.Exposicao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/FunctionsExposicao"})
public class FunctionsExposicao extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String funcao = request.getParameter("fun");
            String codigo;
            String dataInicio;
            String dataFim;
            String codExpositor;
            String codUnidade;
            
            switch(funcao) {
                case "salvarExposicao":
                    codigo = request.getParameter("codigo");                
                    codExpositor = request.getParameter("expositor");                  
                    codUnidade = request.getParameter("unidades");
                    dataInicio = request.getParameter("datIni");                  
                    dataFim = request.getParameter("datFim");
                    salvarExposicao(codigo, dataInicio, dataFim, codExpositor, codUnidade);
                    break;
                case "excluirExposicao":
                    codigo = request.getParameter("codigo");
                    excluirExposicao(codigo);
                    break;
                case "lerExposicao":
                    codigo = request.getParameter("codigo");
                    lerExposicao(codigo, out);
                    break;
                case "listarExposicoes":
                    listarExposicoes(out);
                    break;
                case "listarExpositores":
                    listarExpositores(out);
                    break;
                case "listarUnidades":
                    listarUnidades(out);
                    break;
                case "listarUnidadesLivres":
                    dataInicio = request.getParameter("datIni");                  
                    dataFim = request.getParameter("datFim");
                    listarUnidadesLivres(dataInicio, dataFim, out);
                    break;
            }            
        }
    }
    
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void salvarExposicao(String codigo, String dataInicio, String dataFim, String codExpositor, String codUnidade) {
        Exposicao exp = new Exposicao();
        ExposicaoDAO expDAO = new ExposicaoDAO();
        exp.setIdExposicao(Integer.parseInt(codigo));
        exp.setCodExpositor(Integer.parseInt(codExpositor));
        exp.setCodUnidade(Integer.parseInt(codUnidade));

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

        if (expDAO.selectExposicao(Integer.parseInt(codigo)).isEmpty()){
            expDAO.insertExposicao(exp);
        } else{
            expDAO.updateExposicao(exp);
        }
    }
    
    private void excluirExposicao(String codigo) {
        ExposicaoDAO expDAO = new ExposicaoDAO();
        expDAO.deleteExposicao(Integer.parseInt(codigo));
    }

    private void listarExposicoes(PrintWriter out) {
        String exposicoes;
        ExposicaoDAO expDAO = new ExposicaoDAO();
        exposicoes = expDAO.selectExposicoes();
        out.print(exposicoes);
    }

    private void listarExpositores(PrintWriter out) {
        String expositores;
        ExpositorDAO expDAO = new ExpositorDAO();
        expositores = expDAO.selectExpositores();
        out.print(expositores);
    }

    private void listarUnidadesLivres(String dataInicio, String dataFim, PrintWriter out) {
        Date inicio = null, fim = null;

//      Tratamento de Data Inicio
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); // New Pattern
        java.util.Date date = null;
        try {
            date = sdf1.parse(dataInicio); // Returns a Date format object with the pattern
            java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
            inicio = sqlStartDate;
        } catch (ParseException ex){
            System.out.println("Erro ao converter data");
        }

//      Tratamento de Data Fim
        SimpleDateFormat sdf12 = new SimpleDateFormat("yyyy-MM-dd"); // New Pattern
        java.util.Date date2 = null;
        try {
            date2 = sdf12.parse(dataFim); // Returns a Date format object with the pattern
            java.sql.Date sqlStartDate = new java.sql.Date(date2.getTime());
            fim = sqlStartDate;
        } catch (ParseException ex){
            System.out.println("Erro ao converter data");
        }
        
        String unidades;
        UnidadeDAO uniDAO = new UnidadeDAO();
        unidades = uniDAO.selectUnidadesLivres(inicio, fim);
        out.print(unidades);
    }
    
    public void listarUnidades(PrintWriter out){
        String unidades;
        UnidadeDAO uniDAO = new UnidadeDAO();
        unidades = uniDAO.selectUnidades();
        out.print(unidades);
    }

    private void lerExposicao(String codigo, PrintWriter out) {
        String exposicao;
        ExposicaoDAO expDAO = new ExposicaoDAO();
        exposicao = expDAO.selectExposicao(Integer.parseInt(codigo));
        if (exposicao.isEmpty()){
            out.print("|");
        }else{
            out.print(exposicao);
        }
    }
}
