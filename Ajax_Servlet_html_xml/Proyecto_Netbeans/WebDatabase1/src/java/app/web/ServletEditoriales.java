/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.web;

import app.negocio.GestionLibreria;
import app.negocio.ItfzGestionLibreria;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**  La clase ServletEditoriales extiende de HttpServlet y su principal
 * función es  devolver una lista de editoriales que se cargarán posteriormente
 * en el archivo .jsp que ha realizaod la llamada al servlet.
 *
 * @author Amador Criado
 */
public class ServletEditoriales extends HttpServlet {

    ItfzGestionLibreria dao = new GestionLibreria();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Definimos el tipo de respuesta, en este caso 'text/xml'
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();

       // Realizamos una consulta a la capa app.negocio para obtener
       // la lista de editoriales 
        List<String> editoriales = new ArrayList<>();
        editoriales = dao.consultarEditoriales();

        // Preparamos la cabecera del fichero xml
        // Es importante que este contenga un nivel raiz
        // en nuestor caso lo hemos llamado  <root>
        String lista = "<?xml version='1.0'?>";
        lista += "<root>";
        for (int i = 0; i < editoriales.size(); i++) {
            lista += "<editorial>";
            lista += "<nombre>" + editoriales.get(i) + "</nombre>";
            lista += "</editorial>";
        }
        lista += "</root>";
        try {

            out.println(lista);

        } finally {
            out.close();
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

}
