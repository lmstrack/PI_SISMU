package br.feevale.projetosismu.servlets;

import br.feevale.projetosismu.dao.ExpositorDAO;
import br.feevale.projetosismu.entity.Expositor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/FunctionsExpositor"})
public class FunctionsExpositor extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String funcao = request.getParameter("fun");
            String codigo;
            String descricao;
            
            switch(funcao) {
                case "salvarExpositor":
                    codigo = request.getParameter("codigo");
                    descricao = request.getParameter("descricao");                  
                    salvarExpositor(codigo, descricao);
                    break;
                case "excluirExpositor":
                    codigo = request.getParameter("codigo");
                    excluirExpositor(codigo);
                    break;
                case "lerExpositor":
                    codigo = request.getParameter("codigo");
                    lerExpositor(codigo, out);
                    break;
                case "listarExpositores":
                    listarExpositores(out);
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
    }
    
    private void salvarExpositor(String codigo, String descricao){
        Expositor cat = new Expositor();
        ExpositorDAO catDAO = new ExpositorDAO();
        
        cat.setIdExpositor(Integer.parseInt(codigo));
        cat.setDescricao(descricao);
        
        if (catDAO.selectExpositor(Integer.parseInt(codigo)).isEmpty()){
            catDAO.insertExpositor(cat);
        } else{
            catDAO.updateExpositor(cat);
        }
    }
    
    private void excluirExpositor(String codigo) {
        ExpositorDAO catDAO = new ExpositorDAO();
        catDAO.deleteExpositor(Integer.parseInt(codigo));
    }

    private void listarExpositores(PrintWriter out) {
        String expositores;
        ExpositorDAO catDAO = new ExpositorDAO();
        expositores = catDAO.selectExpositores();
        out.print(expositores);
    }

    private void lerExpositor(String codigo, PrintWriter out) {
        String expositor;
        ExpositorDAO catDAO = new ExpositorDAO();
        expositor = catDAO.selectExpositor(Integer.parseInt(codigo));
        if (expositor.isEmpty()){
            out.print("|");
        }else{
            out.print(expositor);
        }
    }
}
