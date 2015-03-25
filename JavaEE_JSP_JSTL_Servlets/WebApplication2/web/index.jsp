<%@page import="app.negocio.Carrito"%>
<%@page import="java.util.ArrayList"%>
<%@page import="app.modelo.Libro"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <% String respuesta = (String) request.getAttribute("todos");
            String logged = (String) request.getAttribute("logged");
            String user = (String) request.getAttribute("user");
            int i = 0;
            String login = "*vacio*";
            String nombreUser = "*vacio*";
        %>

        <% Libro libro = (Libro) request.getAttribute("libro"); %>
        <link rel="stylesheet" type="text/css" href="styles/miestilo.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina Principal</title>
    </head>
    <body >
        <div></div>
        <div id="header">
            <div id="header-inner">
                <div id="logo">
                    <a href="/" class>
                        <img src="http://image.casadellibro.com/t1/i/logo-img.png" id="logo-img"
                    </a>
                    <a href="/" class>
                        <img src="http://image.casadellibro.com/t1/i/logo-txt.png" id="logo-txt"
                    </a>
                </div>
                <br><br><br><br>
               <div id="nav-b-inner2">
                    <nav>
                        <ul> 
                            <li><a href="#">Libreria</a>
                                <ul>
                                    <li><a href="controlador?opcion=1">Ver Todos</a></li>
                                    <li><a href="altalibro.jsp">Dar de Alta</a></li>
                                    <li><a href="eliminarlibro.jsp">Eliminar Libro</a></li>
                                    <li>  <a href="mostrarcarrito.jsp"> Ver carrito  
                                            <img alt="" src="img/basket.png" width="30" height="30" align="middle">
                                        </a></li>
                                </ul>
                        </ul>
                    </nav> 
                </div>
                <br>
                 <div>
                        <div id="resp-login" align="center"> </div>
                        <form action="controlador?opcion=buscador" method="POST">
                            <input type="text" name="textoBuscador" id="textoBuscador" value="Buscar..."> 
                            <input type="submit" value="Buscar"> <br>
                            <input type="radio" name="tipoBuscador" value="isbn" checked>Por ISBN
                            <input type="radio" name="tipoBuscador" value="titulo">Por titulo
                        </form>
                    </div>  

            </div>
                            <div id="nav-a">
                    <div id="datos-registro">
                        <br>
                     
                   <div id="nav-b2">
                    <%
                        String userName = null;
                        Cookie[] cookies = request.getCookies();
                        if (cookies != null) {
                            for (Cookie cookie : cookies) {
                                if (cookie.getName().equals("MyUser")) {
                                    userName = cookie.getValue();
                                }
                            }
                        }
                    %>
                    <%if (userName != null) {%>
                    <h3>
                        Hi
                        <%=userName%>, Login successful.
                    </h3>
                    <form action="LogoutServlet" method="post">
                        <input type="submit" value="Logout">
                    </form>
                    <%} else { %>
                    <br>    
                    <div id="login-div">
                        <form action="LoginServlet" method="POST">
                            Usuario:  <input type="text" name="user"  />   (curso)<br>
                            Password: <input type="password" name="pw" />  (1234)<br>
                            <input type="submit" value="LOGIN" />
                        </form> 
                    </div>
                    <%}%>
                     
               </div>
                    </div>
                </div>

            <div id="body">
                <% ArrayList<Libro> lista = (ArrayList<Libro>) request.getAttribute("lista");%>
                <div id="div-table">
                    <table id="gridLibros-table">
                        <%!int i = 0;
                            int j = 0;
                        %>
                        <marquee> <a href="CarritoServlet?opcion=add&id=1" ><%= application.getAttribute("msg")%>  </a> </marquee>
                            <%i = 0;
                                j = i;%>
                            <c:forEach var="list0" varStatus="myIndex2" begin="0" end="${lista.size()}">

                            <tr>
                                <c:forEach items="${lista}" var="list" varStatus="myIndex" begin="<%=j%>" end="<%=j + 2%>">

                                    <td>
                                        </a>  
                                        <a href="controlador?opcion=2&id=">
                                            <img alt="" src="img/Book.png" width="100" height="100" align="middle">
                                        </a>  <br>

                                        ${list.getTitulo()}<br> 
                                        <a href="CarritoServlet?opcion=add&id=${list.getID()}">
                                            <img alt="" src="img/plus.png" width="30" height="30" align="middle">
                                        </a>
                                        <a href="controlador?opcion=2&id=">
                                            <img alt="" src="img/Cash.png" width="30" height="30" align="middle">
                                        </a>

                                    </td>

                                </c:forEach>

                            </tr>
                            <%j = j + 3;%>
                        </c:forEach>

                    </table>
   
                </div>
            </div>
    </body>
</html>