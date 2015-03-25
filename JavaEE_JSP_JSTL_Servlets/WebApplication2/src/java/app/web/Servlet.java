/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.web;

import app.modelo.Libro;
import app.negocio.GestionLibreria;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet principal de la Aplicacion Web correspondiente al ejercicio de
 * evaluación del módulo 5. Está definido en el archido web.xml como
 * '/controlador'
 *
 * @author Amador Criado
 * @version 24-03-15
 */
public class Servlet extends HttpServlet {

    GestionLibreria libreria = new GestionLibreria();
    /*
     @param libreria Es inmportante porque se trata d euna instancia a la capa de negocio mediante la clase GestionLibreria
    
     */
    HttpSession sesion;
    boolean logado = false;
    Cookie loggedCookie;
    Cookie miCookie;
    String Logged = "0";

    public Servlet() {
        super();
    }

    /**
     * La función Init sirve para inicializar la aplicacion y recoger el
     * parámetro inicial "oferta".
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        // super.init(config);
        String msg = config.getInitParameter("oferta");

        // Obtenemos el contexto de la aplicacion
        ServletContext ctxApp = config.getServletContext();

        // guardamos el mensaje de la oferta en el ambito de la aplicacion
        ctxApp.setAttribute("msg", msg);

    }

    /**
     * En la función destroy dejamos la sesion invalida de manera que se pierden
     * los datos de sesion
     */
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        super.destroy();
        System.out.println(" --- El servlet se va a destruir ---");
        sesion.setMaxInactiveInterval(10);
        sesion.invalidate();

    }

    /**
     *
     * Es la funcion principal del Servlet, en ella se recibe el parámetor
     * 'opcion' y en función de su valor se realizan las siguientes funciones:
     *
     * '1'- Ver todos los Libros '2'- Dar de alta un libro '4'- Eliminar un
     * Libro por ID 'buscador' - Segun el parámetro 'tipobuscador' realiza una
     * busqueda por ibsn o por titulo en la base de datos y devuelve una lista
     * de libros con los resultados
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void procesar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Saber que ha seleccioado el usuario
        String op = request.getParameter("opcion");

        //Parametros de Formulario Alta Libro
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String editorial = request.getParameter("editorial");
        String isbn = request.getParameter("isbn");
        String publicacion = request.getParameter("publicacion");
        String precio = request.getParameter("precio");
        String descripcion = request.getParameter("descripcion");

        //Parametros de Formulario Eliminar Libro
        String idlibro = request.getParameter("idlibro");

        if ("1".equals(op)) {
            op = op + " respuesta";

            List<Libro> lista = libreria.consultarLibros();

            request.setAttribute("todos", op);
            request.setAttribute("lista", lista);
            RequestDispatcher rd = request
                    .getRequestDispatcher("/index.jsp");
            request.setAttribute("logged", Logged);
            // Redirigimos hacia esa vista
            rd.forward(request, response);
        } else if ("2".equals(op)) {
            op = op + " respuesta";
            request.setAttribute("todos", op);
            List<String> editoriales = libreria.consultarEditoriales();
            request.setAttribute("editoriales", editoriales);
            RequestDispatcher rd = request
                    .getRequestDispatcher("/altalibro.jsp");

            // Redirigimos hacia esa vista
            rd.forward(request, response);
        } else if ("3".equals(op)) {

            String formresponse = "";
            String formOK = "";
            Libro l = new Libro();
            l.setAutor(autor);
            l.setTitulo(titulo);
            l.setEditorial(editorial);
            l.setIsbn(isbn);
            l.setPublicacion(Integer.parseInt(publicacion));
            l.setPrecio(Integer.parseInt(precio));
            l.setDescripcion(descripcion);

            RequestDispatcher rd = request
                    .getRequestDispatcher("/altalibro.jsp");
            if (libreria.altaLibro(l)) {

                formOK = "1";

            } else {

                formOK = "0";
            }

            request.setAttribute("formOK", formOK);

            // Redirigimos hacia esa vista
            rd.forward(request, response);

        } else if ("4".equals(op)) {
            RequestDispatcher rd = request
                    .getRequestDispatcher("/eliminarlibro.jsp");
            String libroEliminadoOK = "";

            if (libreria.eliminarLibro(Integer.parseInt(idlibro))) {
                libroEliminadoOK = "1";
            } else {
                libroEliminadoOK = "0";
            }

            request.setAttribute("libroeliminado", libroEliminadoOK);

            // Redirigimos hacia esa vista
            rd.forward(request, response);
        } else if ("buscador".equals(op)) {

            //Parametros del Buscador de index.jsp
            String textoBuscador = request.getParameter("textoBuscador");
            String tipoBuscador = request.getParameter("tipoBuscador");

            List<Libro> lista = new ArrayList<>();;
            Libro l = new Libro();

            if ("isbn".equals(tipoBuscador)) {

                l = libreria.consultarISBN(textoBuscador);
                lista.add(l);
            } else if ("titulo".equals(tipoBuscador)) {
                lista = libreria.consultarTitulo(textoBuscador);

            }
            request.setAttribute("lista", lista);
            request.setAttribute("logged", Logged);
            RequestDispatcher rd = request
                    .getRequestDispatcher("/index.jsp");

            // Redirigimos hacia esa vista
            rd.forward(request, response);
        }

    }

    /**
     * @see Servlet#procesar(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse) 
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        procesar(request, response);
    }

    /**
     * @see Servlet#procesar(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse) 
     * 
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        procesar(request, response);
    }

}
