/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.web;

import app.negocio.Carrito;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet encargado del Logout de la Aplicacion Web correspondiente al
 * ejercicio de evaluación del módulo 5. Está definido en el archido web.xml
 * como '/LogoutServlet'
 *
 * @author Amador Criado
 * @version 24-03-15
 */
public class LogoutServlet extends HttpServlet {

    HttpSession sesion;

    /**
     * 
     * @param request
     * @param response
     * @throws IOException 
     */
    private void procesar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        Cookie loginCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("MyUser")) {
                    loginCookie = cookie;
                    break;
                }

            }
        }
        if (loginCookie != null) {
            loginCookie.setMaxAge(0);
            sesion = request.getSession();
            Carrito carrito = (Carrito) sesion.getAttribute("miCarro");
            response.addCookie(loginCookie);

            carrito = null;
            sesion.setAttribute("miCarro", carrito);

        }
        response.sendRedirect("index.jsp");
    }

    /**
     * @see LogoutServlet#procesar(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse) 
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        procesar(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @see LogoutServlet#procesar(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)     
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        procesar(req, resp);
    }
}
