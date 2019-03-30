import br.feevale.projetosismu.dao.CategoriaDAO;
import br.feevale.projetosismu.entity.Categoria;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/functions"})
public class functions extends HttpServlet {
  
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
                    lerCategoria(codigo);
                    break;
                case "listarCategorias":
                    listarCategoria();
                    break;
            }            
        }
    }
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    
    private void salvarCategoria(String codigo, String descricao){
        Categoria cat = new Categoria();
        CategoriaDAO catDAO = new CategoriaDAO();
        
        cat.setIdCategoria(Integer.parseInt(codigo));
        cat.setDescricao(descricao);
        
        catDAO.insertCategoria(cat);
    }
    
    private void excluirCategoria(String codigo) {
        CategoriaDAO catDAO = new CategoriaDAO();
        catDAO.deleteCategoria(Integer.parseInt(codigo));
    }

    private String listarCategoria() {
        String categorias;
        CategoriaDAO catDAO = new CategoriaDAO();
        categorias = catDAO.selectCategorias();
        return categorias;
    }

    private String lerCategoria(String codigo) {
        String categoria;
        CategoriaDAO catDAO = new CategoriaDAO();
        categoria = catDAO.selectCategoria(Integer.parseInt(codigo));
        return categoria;
    }
}
