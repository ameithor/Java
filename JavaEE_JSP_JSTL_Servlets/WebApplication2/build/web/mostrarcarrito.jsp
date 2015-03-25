<%-- 
    Document   : mostrarcarrito
    Created on : 08-mar-2015, 12:33:07
    Author     : amatore
--%>

<%@page import="java.util.List"%>
<%@page import="app.modelo.Libro"%>
<%@page import="app.negocio.Carrito"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrito de la compra</title>
    </head>
    <body>
        <h1>bienvenido a tu carrito de la compra </h1>

        <% Carrito carro = (Carrito) session.getAttribute("miCarro");
            List<Libro> lista =null;
        if(carro!=null){
             lista= carro.getCarrito_cache();
        }
        %>

        <%
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ("MyUser".equals(c.getName())) {
                        out.print("Usuario : " + c.getValue());
                    }
                }
            }
        %>

        <%if(carro!=null){%>
        Importe: <%= carro.getImporte()%>
        <%}%>
        <table border="1" align="center">
            <tr>
                <th>ID</th>
                <th>DESCRIPCION</th>
                <th>PRECIO (€)</th>
                <th></th>
            </tr>

            <% if(lista!=null){
                int i=0;
                for (Libro p : lista) {%>
            <tr>
                <td> <%= i%> </td>
                <td> <%= p.getTitulo()%> </td>
                <td> <%= p.getPrecio()%> </td>
                <td><a href="CarritoServlet?opcion=delete&id=<%=i%>"> Eliminar</a> </td>
                
            </tr>
            <% i++;}}%>


        <a href="index.jsp">
        <input type="submit" value="Seguir Comprando">
        </a>

         <form action="LogoutServlet" method="post">
        <input type="submit" value="Finalizar Compra">
         </form>

            <br>
            <br>
    </body>
</html>
