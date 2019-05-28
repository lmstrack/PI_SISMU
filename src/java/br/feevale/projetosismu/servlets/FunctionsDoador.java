package br.feevale.projetosismu.servlets;

import br.feevale.projetosismu.dao.DoadorDAO;
import br.feevale.projetosismu.entity.Doador;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/FunctionsDoador"})
public class FunctionsDoador extends HttpServlet {
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // retorno out.println("xfdvgnhg");
            String funcao = request.getParameter("fun");
            String codigo;
            String nome;
            String cidade;
            String cpf_cnpj;
            String endereco;
            String identidade;
            
            switch(funcao) {
                case "salvarDoador":
                    codigo = request.getParameter("codigo");
                    nome = request.getParameter("nome");                  
                    cidade = request.getParameter("cidade");                  
                    cpf_cnpj = request.getParameter("cpf_cnpj");                  
                    endereco = request.getParameter("endereco");                  
                    identidade = request.getParameter("identidade");                  
                    salvarDoador(codigo, nome, cidade, cpf_cnpj, endereco, identidade);
                    break;
                case "excluirDoador":
                    codigo = request.getParameter("codigo");
                    excluirDoador(codigo);
                    break;
                case "lerDoador":
                    codigo = request.getParameter("codigo");
                    lerDoador(codigo, out);
                    break;
                case "listarDoadores":
                    listarDoadores(out);
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
    
    
    private void salvarDoador(String codigo, String nome, String cidade, String cpf_cnpj, String endereco, String identidade){
        Doador doador = new Doador();
        DoadorDAO doaDAO = new DoadorDAO();
        doador.setIdDoador(Integer.parseInt(codigo));
        doador.setNome(nome);
        doador.setCidade(cidade);
        doador.setCpf_cnpj(cpf_cnpj);
        doador.setEndereco(endereco);
        doador.setIdentidade(identidade);
        
        if (doaDAO.selectDoador(Integer.parseInt(codigo)).isEmpty()){
            doaDAO.insertDoador(doador);
        } else{
            doaDAO.updateDoador(doador);
        }
    }
    
    private void excluirDoador(String codigo) {
        DoadorDAO doaDAO = new DoadorDAO();
        doaDAO.deleteDoador(Integer.parseInt(codigo));
    }

    private void listarDoadores(PrintWriter out) {
        String doadores;
        DoadorDAO doaDAO = new DoadorDAO();
        doadores = doaDAO.selectDoadores();
        out.print(doadores);
    }

    private void lerDoador(String codigo, PrintWriter out) {
        String doador;
        DoadorDAO doaDAO = new DoadorDAO();
        doador = doaDAO.selectDoador(Integer.parseInt(codigo));
        if (doador.isEmpty()){
            out.print("|||||");
        }else{
            out.print(doador);
        }
    }
}
