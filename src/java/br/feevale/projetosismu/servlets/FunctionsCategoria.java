package br.feevale.projetosismu.servlets;

import br.feevale.projetosismu.dao.CategoriaDAO;
import br.feevale.projetosismu.entity.Categoria;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/FunctionsCategoria"})
public class FunctionsCategoria extends HttpServlet {
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // retorno out.println("xfdvgnhg");
            String funcao = request.getParameter("fun");
            String codigo;
            String descricao;
            
            switch(funcao) {
                case "salvarCategoria":
                    codigo = request.getParameter("codigo");
                    descricao = request.getParameter("descricao");                  
                    salvarCategoria(codigo, descricao);
                    break;
                case "excluirCategoria":
                    codigo = request.getParameter("codigo");
                    excluirCategoria(codigo);
                    break;
                case "lerCategoria":
                    codigo = request.getParameter("codigo");
                    lerCategoria(codigo, out);
                    break;
                case "listarCategorias":
                    listarCategoria(out);
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
    
    
    private void salvarCategoria(String codigo, String descricao){
        Categoria cat = new Categoria();
        CategoriaDAO catDAO = new CategoriaDAO();
        cat.setIdCategoria(Integer.parseInt(codigo));
        cat.setDescricao(descricao);
        
        if (catDAO.selectCategoria(Integer.parseInt(codigo)).isEmpty()){
            catDAO.insertCategoria(cat);
        } else{
            catDAO.updateCategoria(cat);
        }
    }
    
    private void excluirCategoria(String codigo) {
        CategoriaDAO catDAO = new CategoriaDAO();
        catDAO.deleteCategoria(Integer.parseInt(codigo));
    }

    private void listarCategoria(PrintWriter out) {
        String categorias;
        CategoriaDAO catDAO = new CategoriaDAO();
        categorias = catDAO.selectCategorias();
        out.print(categorias);
    }

    private void lerCategoria(String codigo, PrintWriter out) {
        String categoria;
        CategoriaDAO catDAO = new CategoriaDAO();
        categoria = catDAO.selectCategoria(Integer.parseInt(codigo));
        if (categoria.isEmpty()){
            out.print("|");
        }else{
            out.print(categoria);
        }
    }
}
