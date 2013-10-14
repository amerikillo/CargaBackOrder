<%-- 
    Document   : editar
    Created on : 26/09/2013, 02:51:41 PM
    Author     : CEDIS NAY 1
--%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="conn.ConectionDB" %>
<%
    ConectionDB obj = new ConectionDB();
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
        <h1>Editar Folios</h1>
        <a href="../index.jsp">Regresar</a>
        <div class="row">
        <div class="col-sm-4">
        Seleccione el Folio:<br>
        <table class="table table-bordered table-striped table-hover">
            <%
                ResultSet rset = null;
                obj.conectar();
                rset = obj.consulta("select fol_bac from folio_back;");
                while (rset.next()) {
            %>
            <tr>
                <td><a href="editar.jsp?folio=<%=rset.getString(1)%>"><%=rset.getString(1)%></a></td>
            </tr>
            <%
                }
                obj.cierraConexion();
            %>
        </table>
</div>
<div class="col-sm-8">
        <table>
            <tr>
                <td>
                    <table class="table table-bordered table-striped table-hover">
                        <%
                            String folio = "";
                            try {
                                folio = "" + request.getParameter("folio");
                            } catch (Exception e) {
                            }
                        %>
                        <tr><td colspan="5">Requerimento Folio: <%=folio%> <a href="../Principal?accion=1&folio=<%=folio%>">editar</a> <a href="../Principal?accion=2&folio=<%=folio%>">eliminar</a></td></tr>
                        <tr>
                            <td>Unidad</td>
                            <td>Fecha</td>
                            <td>Clave</td>
                            <td>Cantidad</td>
                        </tr>
                        <%
                            rset = null;
                            obj.conectar();
                            rset = obj.consulta("select fb.fol_bac, fb.cla_uni,fb.fec_sol, bs.cla_pro, bs.can_sol from folio_back fb, back_solicitado bs where fb.fol_bac = bs.fol_bac AND fb.fol_bac='" + folio + "' ;");
                            while (rset.next()) {
                        %>
                        <tr>
                            <td><%=rset.getString(2)%></td>
                            <td><%=rset.getString(3)%></td>
                            <td><%=rset.getString(4)%></td>
                            <td><%=rset.getString(5)%></td>
                        </tr>
                        <%
                            }
                            obj.cierraConexion();
                        %>
                    </table>
                </td>
                <td>
                    <table class="table table-bordered table-striped table-hover">
                        <%
                            folio = "";
                            try {
                                folio = "" + request.getParameter("folio");
                            } catch (Exception e) {
                            }
                        %>
                        <tr><td colspan="5">Abasto Folio: <%=folio%> <a href="../Principal?accion=3&folio=<%=folio%>">editar</a> <a href="../Principal?accion=4&folio=<%=folio%>">eliminar</a></td></tr>
                        <tr>
                            <td>Unidad</td>
                            <td>Fecha</td>
                            <td>Clave</td>
                            <td>Cantidad</td>
                        </tr>
                        <%
                            rset = null;
                            obj.conectar();
                            rset = obj.consulta("select fb.fol_bac, fb.cla_uni,fb.fec_sur, bs.cla_pro, bs.can_sur from folio_back fb, back_surtido bs where fb.fol_bac = bs.fol_bac AND fb.fol_bac='" + folio + "' ;");
                            while (rset.next()) {
                        %>
                        <tr>
                            <td><%=rset.getString(2)%></td>
                            <td><%=rset.getString(3)%></td>
                            <td><%=rset.getString(4)%></td>
                            <td><%=rset.getString(5)%></td>
                        </tr>
                        <%
                            }
                            obj.cierraConexion();
                        %>
                    </table>
                </td>
            </tr>
        </table>
        </div>
        <div class="navbar-fixed-bottom" style="text-align:center">
                <p>GNKL Derechos Reservados</p>
            </div>
        </div>
    </body>
</html>
