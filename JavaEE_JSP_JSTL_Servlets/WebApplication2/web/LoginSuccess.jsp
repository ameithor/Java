<%-- 
    Document   : LoginSuccess
    Created on : 14-mar-2015, 10:00:37
    Author     : amatore
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <div align="center">
        <br> <br>
 
        <%
            String userName = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("MyUser"))
                        userName = cookie.getValue();
                }
            }
            if (userName == null)
                response.sendRedirect("index.jsp");
        %>
        <h3>
            Hi
            <%=userName%>, Login successful.
        </h3>
        <br>
 
        <form action="LogoutServlet" method="post">
            <input type="submit" value="Logout">
        </form>
        <br>
        <a href="index.jsp">
        <input type="submit" value="volver">
        </a>
    </div>
    </body>
</html>
