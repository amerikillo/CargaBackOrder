/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import conn.ConectionDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CEDIS NAY 1
 */
@WebServlet(name = "CreaFolio", urlPatterns = {"/CreaFolio"})
public class CreaFolio extends HttpServlet {

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
    ConectionDB obj = new ConectionDB();
    private String unidad, folio, fec_sur, EX = ""; //En pass se guarda la que ingreso el usuario
    private boolean exito;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreaFolio</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreaFolio at " + request.getContextPath() + "</h1>");
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
    public void validar(String uni, String fol, String fec) {

        Boolean estado = false;
        String falta = null;

        if (uni.isEmpty() || fol.isEmpty() || fec.isEmpty()) {
            falta = "Datos Incompletos";
            estado = false;
        } else {
            /**
             * *Datos Correctos**
             */
            this.unidad = uni;
            this.folio = fol;
            this.fec_sur = fec;
            falta = "Datos Correctos";
            estado = true;
        }
        this.alta_fol(estado, falta);
    }

    private void alta_fol(Boolean estado, String aviso) {
        if (estado) {
            String qry_folio = "INSERT INTO FOLIO_BACK VALUES ('0', '" + this.unidad + "', '" + this.folio + "', CURDATE(), '" + this.fec_sur + "');";
            System.out.println(qry_folio);
            try {
                obj.insertar(qry_folio);
            } catch (Exception e) {
                System.out.println("Se produjo un error de carga" + e.getMessage());
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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
