<%-- 
    Document   : eliminarlibro
    Created on : 05-mar-2015, 19:54:10
    Author     : amatore
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <% String eliminado = (String) request.getAttribute("libroeliminado");%>
         
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
          <script type="text/javascript">

            function Redirect() {
           
              <% if (eliminado == "1") { %>
                   window.alert('Gracias. Libro eliminado correctamente');

            <%}%> 
                              <% if (eliminado == "0") { %>
                   window.alert('ATENCION: No se ha eliminado el libro');

            <%}%> 
            }
        </script> 
    </head>
    <body onload="Redirect()">
        <h1>Introduzca el ID dle Libro a eliminar</h1>
         <form action="controlador?opcion=4" method="POST">
        ID:<br>
        <input type="text" name="idlibro" id="titulo" value="">
        <br>
       
        <input type="submit" value="Aceptar">
        <input type="reset" value="Reset">
        </form> 
        
    </body>
</html>
