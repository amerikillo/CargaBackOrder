<%-- 
    Document   : index
    Created on : 06-sep-2013, 17:33:12
    Author     : wence
--%>
<%
    HttpSession sesion = request.getSession();
    String info = null;
%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="container">
            <div class="header"><h1>Sistema de Carga de Back-Orders</h1></div>

            <a class="btn btn-lg btn-success" href="crear_folio.jsp">Crear Folio</a> <br>
            <a class="btn btn-lg btn-success" href="requerimiento.jsp">Subir Requerimiento</a> <br>
            <a class="btn btn-lg btn-success" href="abasto.jsp">Subir Abasto</a> <br>
            <a class="btn btn-lg btn-success" href="edita_folio/editar.jsp">Editar Folios</a>
            <div class="navbar-fixed-bottom" style="text-align:center">
                <p>GNKL Derechos Reservados</p>
            </div>
        </div>
    </body>
</html>
