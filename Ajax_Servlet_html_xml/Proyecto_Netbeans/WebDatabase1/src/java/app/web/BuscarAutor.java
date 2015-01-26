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

/** La clase BuscarAutor extiende de HttpServlet y su principal
 * función es  devolver una lista de libros que se cargarán posteriormente
 * en el archivo .jsp que ha realizaod la llamada al servlet.
 *
 * @author Amador Criado
 */
public class BuscarAutor extends HttpServlet {

    ItfzGestionLibreria dao = new GestionLibreria();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Definimos el tipo de respuesta, en este caso 'text/html'
        response.setContentType("text/html;charset=UTF-8");

        // Recibimos la respuesta y la cargamos en la variable out
        PrintWriter out = response.getWriter();

        // Recogemos el parámetro 'autor'
        String autor = request.getParameter("autor");

        // Realizamos una consulta a la capa app.negocio para obtener
        // la lista de libros segun el nombre pasado por parámetro
        List<String> librosAutor = new ArrayList<>();
        librosAutor = dao.consultarLibrosAutor(autor);
        String lista = "";
        
        // En el siguiente bucle for , vamos añadiendo a cada iteración
        // el carácter separador '-' que posteriormente será utilizado
        // por la función split() en el archivo .jsp que reciba la respuesta
        for (int i = 0; i < librosAutor.size(); i++) {
            lista = lista + librosAutor.get(i) + "-";
        }
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
