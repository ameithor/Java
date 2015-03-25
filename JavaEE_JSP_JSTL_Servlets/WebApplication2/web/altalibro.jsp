<%-- 
    Document   : altalibro
    Created on : 02-mar-2015, 17:30:41
    Author     : amatore
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="app.modelo.Libro"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
           <% String respuesta = (String) request.getAttribute("todos");
            String formOK = (String) request.getAttribute("formOK");
           %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dar de Alta Libro</title>
          <script type="text/javascript">

            function Redirect() {
            <% if (respuesta == null) { %>
                //var myWindow = window.open("controlador?opcion=1");
                this.document.location = "controlador?opcion=2";

            <%}%>
              <% if (formOK == "1") { %>
                   window.alert('Gracias. Libro dado de alta correctamente');

            <%}%> 
                              <% if (formOK == "0") { %>
                   window.alert('ATENCION: No dse ha dado de alta el libro');

            <%}%> 
            }
        </script>        
    </head>
    <body onload="Redirect()">
         <% ArrayList<String> lista = (ArrayList<String>) request.getAttribute("editoriales");%>
        <h1>Rellene el formulario para dar de alta el Libro que desee:</h1>
        <form action="controlador?opcion=3" method="POST">
        Titulo:<br>
        <input type="text" name="titulo" id="titulo" value="">
        <br>
        Autor:<br>
        <input type="text" name="autor" id="autor" value="">
        <br>
        Editorial:<br>
        <select id="editorial" name="editorial">
            <c:forEach items="${editoriales}" var="list" varStatus="myIndex">
                            <option value="${myIndex.index}">
                                ${myIndex.index}
                                ${list}
                            </option>
                        </c:forEach>
        </select><br>
        ISBN:<br>
        <input type="text" name="isbn" id="isbn" value=""> <br>
        Publicacion:<br>
        <input type="text" name="publicacion" id="publicacion" value=""> <br>
        Precio:<br>
        <input type="text" name="precio" id="precio" value=""> <br>
        Descripcion:<br>
        <textarea id="descripcion" name="descripcion" cols="40" rows="5"></textarea> <br>
        <br><br>
        <input type="submit" value="Aceptar">
        <input type="reset" value="Reset">
        </form> 
    </body>
</html>
