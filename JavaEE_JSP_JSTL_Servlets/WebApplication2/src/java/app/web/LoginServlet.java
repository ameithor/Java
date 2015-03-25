/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet encargado del Login de la Aplicacion Web correspondiente al ejercicio
 * de evaluación del módulo 5. Está definido en el archido web.xml como
 * '/LoginServlet'
 *
 * @author Amador Criado
 * @version 24-03-15
 */
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final String userID = "curso";
    private final String password = "1234";

    /**
     * La funcion procesar valida que el usuario sea 'curso' y el passwors '1234'
     * En caso afirmativo guarda la cookie de usuario y devuelve el control a /index.jsp
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void procesar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get request parameters for userID and password
        String MyUser = request.getParameter("user");
        String pwd = request.getParameter("pw");

        if (userID.equals(MyUser) && password.equals(pwd)) {
            Cookie MyUserCookie = new Cookie("MyUser", MyUser);
            // setting cookie to expiry in 60 mins
            MyUserCookie.setMaxAge(60 * 60);
            response.addCookie(MyUserCookie);
            response.sendRedirect("LoginSuccess.jsp");
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Please make sure you enter UserID/Pass as \"curso : curso\".</font>\n");
            rd.include(request, response);
        }

    }

    /**
     * @see LoginServlet#procesar(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)     
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        procesar(request, response);
    }

    /**
     * @see LoginServlet#procesar(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)     
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        procesar(request, response);
    }

}
