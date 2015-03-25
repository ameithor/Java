/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.web;

import app.negocio.Carrito;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet encargado de gestionar las peticiones de gestion del Carrito. Esta spueden provenir
 * tanto desde la página principal index.jsp como de la página mostrarcarrito.jsp con consultas
 * como eliminar un libro del carrito.
 * @author Amador Criado
 */
public class CarritoServlet extends HttpServlet {

    HttpSession sesion;
    Carrito carrito;

/** Tanto para peticiones POST como GET esta funcion comprueba si existe la cookie 'MyUser'
 * y en caso de no existir crea el carrito y asigna la cookie. Tambien evalua el parámetro
 * 'opcion' con los siguientes valores:
 * 
 * 'add' --> Añadir libro al Carrito
 * 'delete' --> Eliminar libro del carrito
 * 
 * @param request
 * @param response
 * @throws IOException
 * @throws ServletException 
 */    
    private void procesar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String op = request.getParameter("opcion");
        String id = request.getParameter("id");

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
            // Obtengo el carrito de la sesion del usuario
            sesion = request.getSession();
            carrito = (Carrito) sesion.getAttribute("miCarro");

            // Comprobar si el carrito existe o no
            if (carrito == null) {
                carrito = new Carrito();
                sesion.setAttribute("miCarro", carrito);
            }

            if ("add".equals(op)) {
                // Agregamos el producto al carrito
                Integer codigo = Integer.parseInt(request.getParameter("id"));
                carrito.AgregarLibro(codigo);
            }
            if ("delete".equals(op)) {

                carrito.eliminarLibro(id);

                RequestDispatcher rd = request.getRequestDispatcher("/mostrarcarrito.jsp");
                rd.forward(request, response);
            }

            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);

        }

    }
    
    /**
     * @see CarritoServlet#procesar(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)  
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        procesar(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    
    /**
     * @see CarritoServlet#procesar(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)  
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        procesar(req, resp); //To change body of generated methods, choose Tools | Templates.
    }


}
