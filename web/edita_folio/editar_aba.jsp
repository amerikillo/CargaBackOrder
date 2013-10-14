<%-- 
    Document   : editar_aba
    Created on : 28/09/2013, 12:41:11 PM
    Author     : CEDIS NAY 1
--%>

<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="conn.ConectionDB" %>
<%
    ConectionDB obj = new ConectionDB();
    String folio = "";
    try {
        folio = "" + request.getParameter("folio");
    } catch (Exception e) {
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../css/bootstrap.css" rel="stylesheet" type="text/css">
    </head>
    <body>
    <div class="container">
        <h1>Editar Abastos</h1>
        <a href="editar.jsp?folio=<%=folio%>">Regresar</a>
        <h3>Folio: <%=folio%></h3>
        <table class="table table-bordered table-striped table-hover">
            <tr>
                <td></td>
                <td>Clave</td>
                <td>Cantidad</td>
            </tr>
            <%
                ResultSet rset = null;
                obj.conectar();
                rset = obj.consulta("select * from back_surtido where fol_bac = '" + folio + "';");
                while (rset.next()) {
            %>
            <tr>
                <td><a href="edita_reg_aba.jsp?folio=<%=folio%>&registro=<%=rset.getString(1)%>"><%=rset.getString(1)%></a></td>
                <td><%=rset.getString(3)%></td>
                <td><%=rset.getString(4)%></td>
            </tr>
            <%
                }
                obj.cierraConexion();
            %>
        </table>
        <div class="navbar-fixed-bottom" style="text-align:center">
                <p>GNKL Derechos Reservados</p>
            </div>
        </div>
    </body>
</html>
