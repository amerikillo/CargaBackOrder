/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CEDIS NAY 1
 */
@WebServlet(name = "Principal", urlPatterns = {"/Principal"})
public class Principal extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    CreaFolio crea_folio = new CreaFolio();
    CargaCsv cargacsv = new CargaCsv();
    EdicionFolios edita_folio = new EdicionFolios();
    String mensaje;
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet creafolio</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet creafolio at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String folio = request.getParameter("folio");
        int accion = Integer.parseInt(request.getParameter("accion"));
        switch (accion) {
            case 1://Edicion de Requrimiento de backorder
                response.sendRedirect(response.encodeRedirectURL("edita_folio/editar_req.jsp?folio=" + folio + ""));
                break;
            case 2://Eliminacion de Requrimiento de backorder
                edita_folio.EdicionFolios(accion, folio);
                response.sendRedirect(response.encodeRedirectURL("edita_folio/editar.jsp"));
                break;
            case 3://Edicion de Abasto de backorder
                response.sendRedirect(response.encodeRedirectURL("edita_folio/editar_aba.jsp?folio=" + folio + ""));
                break;
            case 4://Eliminacion de Abasto de backorder
                edita_folio.EdicionFolios(accion, folio);
                response.sendRedirect(response.encodeRedirectURL("edita_folio/editar.jsp"));
                break;
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String submit = request.getParameter("submit");

        if (submit != null) {
            /**
             * **************METODO PARA LA INSERCION DE FOLIOS*************
             */
            if (submit.equals("Crear Folio")) {
                crea_folio.validar(request.getParameter("Unidad"), request.getParameter("Folio"), request.getParameter("Fecha"));
                response.sendRedirect(response.encodeRedirectURL("crear_folio.jsp"));
            } /**
             * *************************************************************
             */
            /**
             * **************METODO PARA LA INSERCION DE
             * Requerimientos*************
             */
            else if (submit.equals("Cargar Requerimiento")) {
                
                try {
                    HttpSession sesion = request.getSession(true);
                    mensaje = cargacsv.carga(request.getParameter("file"), request.getParameter("folio"), "requerimiento");
                    sesion.setAttribute("mensaje", mensaje);
                } catch (SQLException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                response.sendRedirect(response.encodeRedirectURL("requerimiento.jsp"));
            } /**
             * *************************************************************
             */
            /**
             * **************METODO PARA LA INSERCION DE Abastos*************
             */
            else if (submit.equals("Cargar Abasto")) {
                String folio;
                try {
                    HttpSession sesion = request.getSession(true);
                    mensaje = cargacsv.carga(request.getParameter("file"), request.getParameter("folio"), "abasto");
                    sesion.setAttribute("mensaje", mensaje);
                } catch (SQLException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                response.sendRedirect(response.encodeRedirectURL("abasto.jsp"));
            } /**
             * *************************************************************
             */
            /**
             * **************METODO PARA LA ACTUALIZACION REGISTRO
             * ABASTO*************
             */
            else if (submit.equals("Actualizar Registro de Abasto")) {
                //crea_folio.validar(request.getParameter("file"));
                edita_folio.actualiza_registro(request.getParameter("registro"), request.getParameter("tabla"), request.getParameter("clave"), request.getParameter("cantidad"));
                response.sendRedirect(response.encodeRedirectURL("edita_folio/editar_aba.jsp?folio=" + request.getParameter("folio") + ""));
            } /**
             * *************************************************************
             */
            /**
             * **************METODO PARA LA ACTUALIZACION REGISTRO
             * Requerimiento*************
             */
            else if (submit.equals("Actualizar Registro de Requerimiento")) {
                //crea_folio.validar(request.getParameter("file"));
                edita_folio.actualiza_registro(request.getParameter("registro"), request.getParameter("tabla"), request.getParameter("clave"), request.getParameter("cantidad"));
                response.sendRedirect(response.encodeRedirectURL("edita_folio/editar_req.jsp?folio=" + request.getParameter("folio") + ""));
            }
            /**
             * *************************************************************
             */
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
