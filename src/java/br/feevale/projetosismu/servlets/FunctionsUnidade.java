/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.feevale.projetosismu.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
            String idDoador;
            String idRepresentanteLegal;
            
            switch(funcao) {
                case "salvarUnidade":
                    idUnidade = request.getParameter("idunidade");
                    descricao = request.getParameter("descricao");
                    tamanho = request.getParameter("tamanho");
                    historia = request.getParameter("historia");
                    historiaDoador = request.getParameter("historiaDoador");
                    fabricante = request.getParameter("fabricante");
                    origem = request.getParameter("origem");
                    dataDoacao = request.getParameter("dataDoacao");
                    codDoador = request.getParameter("codDoador");
                    nPatrimonio = request.getParameter("nPatrimonio");
                    pacote = request.getParameter("pacote");
                    codCategoria = request.getParameter("codCategoria");
                    valorNf = request.getParameter("valorNf");
                    idDoador = request.getParameter("idDoador");
                    idRepresentanteLegal = request.getParameter("idRepresentanteLegal");
                    inserirUnidade(idUnidade, descricao, tamanho, historia, historiaDoador,
                            fabricante, origem, dataDoacao, codDoador, nPatrimonio, pacote, codCategoria,
                            valorNf, idDoador, idRepresentanteLegal);
                    break;
                
                case "excluirUnidade":
                    idUnidade = request.getParameter("idunidade");
                    excluirUnidade(idUnidade);
                    break;
                   
                case "lerUnidade":
                    
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

    private void excluirUnidade(String idUnidade) {
        
    }

    private void inserirUnidade(String idUnidade, String descricao, String tamanho, String historia, 
            String historiaDoador, String fabricante, String origem, String dataDoacao, String codDoador, 
            String nPatrimonio, String pacote, String codCategoria, String valorNf, String idDoador,
            String idRepresentanteLegal) {
        
    }

}
