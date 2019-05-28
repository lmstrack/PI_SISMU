/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.feevale.projetosismu.servlets;

import br.feevale.projetosismu.dao.CategoriaDAO;
import br.feevale.projetosismu.dao.DoadorDAO;
import br.feevale.projetosismu.dao.UnidadeDAO;
import br.feevale.projetosismu.entity.Doador;
import br.feevale.projetosismu.entity.Unidade;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LUCASSTRACK
 */
@WebServlet(name = "FunctionsUnidade", urlPatterns = {"/FunctionsUnidade"})
public class FunctionsUnidade extends HttpServlet {
    
    UnidadeDAO uniDAO = new UnidadeDAO();
    CategoriaDAO catDAO = new CategoriaDAO();
    Unidade uni = new Unidade();
    DoadorDAO doaDAO = new DoadorDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String funcao = request.getParameter("fun");
            String idUnidade;
            String descricao;
            String tamanho;
            String historia;
            String historiaDoador;
            String fabricante;
            String origem;
            String dataDoacao;
            String codDoador;
            String nPatrimonio;
            String pacote;
            String codCategoria;
            String valorNf;
            String codRepLegal;
            
            switch(funcao) {
                case "salvarUnidade":
                    idUnidade = request.getParameter("codigo");
                    descricao = request.getParameter("descricao");
                    tamanho = request.getParameter("tamanho");
                    historia = request.getParameter("historia");
                    historiaDoador = request.getParameter("historiaD");
                    fabricante = request.getParameter("fabricante");
                    origem = request.getParameter("origem");
                    dataDoacao = request.getParameter("data");
                    codDoador = request.getParameter("doador");
                    nPatrimonio = request.getParameter("patrimonio");
                    pacote = request.getParameter("pacote");
                    codCategoria = request.getParameter("categoria");
                    valorNf = request.getParameter("valor");
                    codRepLegal = request.getParameter("repLegal");
                    inserirUnidade(idUnidade, descricao, tamanho, historia, historiaDoador,
                            fabricante, origem, dataDoacao, codDoador, nPatrimonio, pacote, codCategoria,
                            valorNf, codRepLegal);
                    break;
                
                case "excluirUnidade":
                    idUnidade = request.getParameter("codigo");
                    excluirUnidade(idUnidade);
                    break;
                   
                case "lerUnidade":
                    idUnidade = request.getParameter("codigo");
                    lerUnidade(idUnidade, out);
                    break;
                
                case "listarUnidades":
                    listarUnidades(out);
                    break;
                    
                case "listarDoadores":
                    listarDoadores(out);
                    break;
                    
                case "listarCategorias":
                    listarCategorias(out);
                    break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

    private void excluirUnidade(String codigo) {
        uniDAO.deleteUnidade(Integer.parseInt(codigo));
    }

    private void inserirUnidade(String idUnidade, String descricao, String tamanho, String historia, 
            String historiaDoador, String fabricante, String origem, String dataDoacao, String codDoador, 
            String nPatrimonio, String pacote, String codCategoria, String valorNf, String codRepLegal) {
        
        uni.setIdUnidade(Integer.parseInt(idUnidade));
        uni.setDescricao(descricao);
        uni.setTamanho(tamanho);
        uni.setHistoria(historia);
        uni.setHistoriaDoador(historiaDoador);
        uni.setFabricante(fabricante);
        uni.setOrigem(origem);
        
//      Tratamento de Data
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); // New Pattern
        java.util.Date date = null;
        try {
            date = sdf1.parse(dataDoacao); // Returns a Date format object with the pattern
            java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
            uni.setDataDoacao(sqlStartDate);
        } catch (ParseException ex){
            System.out.println("Erro ao converter data");
        }
        
        if (!codDoador.isEmpty()) {
            uni.setCodDoador(Integer.parseInt(codDoador));
        } else {
            uni.setCodDoador(null);
        }
        uni.setnPatrimonio(Integer.parseInt(nPatrimonio));
        uni.setPacote(pacote);
        uni.setCodCategoria(Integer.parseInt(codCategoria));
        uni.setValorNf(Float.parseFloat(valorNf));
        uni.setCodRepLegal(codRepLegal);
        
//      Verifica se unidade já existe, se sim, atualiza unidade existente
        if (uniDAO.selectCodUnidade(Integer.parseInt(idUnidade)).isEmpty()){
            uniDAO.insertUnidade(uni);
        } else{
            uniDAO.updateUnidade(uni);
        }
    }
       
    private void listarCategorias(PrintWriter out) {
        String categorias;
        categorias = catDAO.selectCategorias();
        out.print(categorias);
    }

    private void lerUnidade(String codigo, PrintWriter out) {
        String unidade;
        unidade = uniDAO.selectCodUnidade(Integer.parseInt(codigo));
        if (unidade.isEmpty()){
            out.print("|||||||||||||");
        }else{
            out.print(unidade);
        }
    }

    private void listarUnidades(PrintWriter out) {
        String unidades;
        unidades = uniDAO.selectUnidades();
        out.print(unidades);
    }

    private void listarDoadores(PrintWriter out) {
        String doadores;
        doadores = doaDAO.selectDoadores();
        out.print(doadores);
    }

}
