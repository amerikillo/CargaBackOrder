<%-- 
    Document   : crear_folio
    Created on : 26/09/2013, 11:28:52 AM
    Author     : CEDIS NAY 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <h3>Creacion de Folios</h3>
        <a href="index.jsp">Regresar</a><br>
        
        <form action="Principal" method="POST">
            <p>Ingrese los datos del Formulario <br>
              <br>
            </p>
            <table class="table table-bordered table-striped table-hover">
              <tr>
                <th scope="col">Folio: </th>
                <th scope="col"><input type="text" name="Folio" value="" /></th>
              </tr>
              <tr>
                <th scope="row">Unidad: </th>
                <td><input type="text" name="Unidad" value="" /></td>
              </tr>
              <tr>
                <th scope="row"> Fecha de Abasto: </th>
                <td><input type="text" name="Fecha" value="" /></td>
              </tr>
              <tr>
                <th scope="row">&nbsp;</th>
                <td><input type="submit" name="submit" value="Crear Folio" class="btn btn-primary" /></td>
              </tr>
            </table>
            <p><br>
            </p>
        </form>
        <div class="navbar-fixed-bottom" style="text-align:center">
        <p>GNKL Derechos Reservados</p>
      </div>
        </div>
    </body>
</html>