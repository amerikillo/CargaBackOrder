<%-- 
    Document   : edita_clave_folio
    Created on : 30/09/2013, 10:13:55 AM
    Author     : CEDIS NAY 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="conn.ConectionDB" %>
<%
    ConectionDB obj = new ConectionDB();
    String registro = "";
    String folio ="";
    try {
        registro = "" + request.getParameter("registro");
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
        <h1>Edita Registro de Requerimiento</h1>
        <a href="editar_aba.jsp?folio=<%=folio%>">Regresar</a>
        <%
            ResultSet rset = null;
            obj.conectar();
            rset = obj.consulta("select cla_pro, can_sol from back_solicitado where id_bac_sol= '" + registro + "';");
            while (rset.next()) {
        %>
        <form name="form_actualiza_folio" action="../Principal" method="POST">
            
            <table class="table table-bordered table-striped table-hover">
              <tr>
                <th scope="col">Registro: </th>
                <th scope="col"><input type="text" name="registro" value="<%=registro%>" readonly /></th>
              </tr>
              <tr>
                <th scope="row">Folio: </th>
                <td><input type="text" name="folio" value="<%=folio%>" readonly /></td>
              </tr>
              <tr>
                <th scope="row">Tabla: </th>
                <td><input type="text" name="tabla" value="back_solicitado" readonly /></td>
              </tr>
              <tr>
                <th scope="row">Clave: </th>
                <td><input type="text" name="clave" value="<%=rset.getString(1)%>" readonly /></td>
              </tr>
              <tr>
                <th scope="row">Cantidad: </th>
                <td><input type="text" name="cantidad" value="<%=rset.getString(2)%>" /></td>
              </tr>
              <tr>
                <th scope="row">&nbsp;</th>
                <td><input class="btn btn-primary" type="submit" value="Actualizar Registro de Requerimiento" name="submit" /></td>
              </tr>
            </table>
            <p>&nbsp;</p>
        </form>
        <%            }
            obj.cierraConexion();
        %>
        <div class="navbar-fixed-bottom" style="text-align:center">
                <p>GNKL Derechos Reservados</p>
      </div>
    </div>
    </body>
</html>