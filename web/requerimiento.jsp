<%-- 
    Document   : requerimiento
    Created on : 26/09/2013, 11:29:53 AM
    Author     : CEDIS NAY 1
--%>

<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="conn.ConectionDB" %>
<%
    ConectionDB obj = new ConectionDB();
    HttpSession sesion = request.getSession();
    String info = null;
    info = (String) session.getAttribute("info");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="container">
            <h1>Cargar Requerimientos</h1>
            <a href="index.jsp">Regresar</a><br>
            <div class="row">
                <div class="col-sm-4">

                    Seleccione el Folio:<br>
                    <table class="table table-bordered table-striped table-hover">
                        <%
                            ResultSet rset = null;
                            obj.conectar();
                            rset = obj.consulta("select fol_bac from folio_back where not exists (select fol_bac from back_solicitado  where folio_back.fol_bac=back_solicitado.fol_bac)  group by fol_bac;");
                            while (rset.next()) {
                        %>
                        <tr>
                            <td><a href="requerimiento.jsp?req=<%=rset.getString(1)%>"><%=rset.getString(1)%></a></td>
                        </tr>
                        <%
                            }
                            obj.cierraConexion();
                        %>
                    </table>
                </div>
                <div class="col-sm-8">
                
                  <%
                        obj.conectar();
                        String folio = "";
                        try {
                            folio = "" + request.getParameter("req");
                        } catch (Exception e) {
                        }

                        rset = obj.consulta("select cla_uni, fol_bac, fec_sur from folio_back where fol_bac = '" + folio + "'");
                        while (rset.next()) {
                    %>
                    <FORM ACTION="Principal" METHOD="POST">
                        
                        <table class="table table-bordered table-striped table-hover">
                          <tr>
                            <th scope="col">Unidad: </th>
                            <th scope="col"><input type="text" name="uni" readonly value="<%=rset.getString(1)%>" /></th>
                          </tr>
                          <tr>
                            <th scope="row">Folio: </th>
                            <td><input type="text" name="folio"  readonly="Readonly"value="<%=rset.getString(2)%>" /></td>
                          </tr>
                          <tr>
                            <th scope="row">Fecha: </th>
                            <td><input type="text" name="fec"  readonly="Readonly"value="<%=rset.getString(3)%>" /></td>
                          </tr>
                          <tr>
                            <th scope="row">Seleccione el abasto a cargar:</th>
                            <td><input name="file" type="file" class="text-success"></td>
                          </tr>
                          <tr>
                            <th scope="row">&nbsp;</th>
                            <td><input type="submit" name="submit" value="Cargar Requerimiento" class="btn btn-primary" /></td>
                          </tr>
                        </table>
                        <p>&nbsp;</p>
                    </FORM>
                    <%            }
                        obj.cierraConexion();
                    %>
                </div>
            </div>
            <div class="navbar-fixed-bottom" style="text-align:center">
                <p>GNKL Derechos Reservados</p>
            </div>
        </div>
    </body>
</html>
