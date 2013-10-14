/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import conn.ConectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author CEDIS NAY 1
 */
@WebServlet(name = "CargaCsv", urlPatterns = {"/CargaCsv"})
public class CargaCsv extends HttpServlet {

    private String qry_sol;
    ConectionDB obj = new ConectionDB();

    public String carga(String csvfile, String folio, String tipo) throws SQLException {
        boolean ban = checaCarga(csvfile);
        String mensaje;
        if (ban) {
            mensaje = "Archivo ya cargado";
        } else {
            if (tipo.equals("abasto")) {
                abasto(csvfile, folio);
            } else if (tipo.equals("requerimiento")) {
                requerimiento(csvfile, folio);
            }
            mensaje = "Archivo cargado correctamente";
        }
        System.out.println(mensaje);
        return mensaje;
    }

    private boolean checaCarga(String csvfile) throws SQLException {
        ResultSet rset;
        boolean ban = false;
        obj.conectar();
        String qry_revisaarchivo = "select tipo from registro_backs where archivo = '" + csvfile + "';";
        rset = obj.consulta(qry_revisaarchivo);
        {
            ban = true;
        }
        obj.cierraConexion();
        return ban;
    }

    private void requerimiento(String csvfile, String folio) {
        //String csvfile = "F://data.csv";
        BufferedReader reader = null;
        String line;

        try {

            reader = new BufferedReader(new FileReader(csvfile));
            while ((line = reader.readLine()) != null) {
                String[] linearray = line.split(",");
                String qry_archivo = "insert into registro_backs values ('0', '" + csvfile + "', 'R')";
                this.qry_sol = "insert into back_solicitado values ('0','" + folio + "','" + linearray[0] + "','" + linearray[1] + "')";
                System.out.println(qry_sol);
                try {
                    obj.insertar(qry_sol);
                    obj.insertar(qry_archivo);
                } catch (Exception e) {
                    System.out.println("Se produjo un error de carga" + e.getMessage());
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void abasto(String csvfile, String folio) {
        //String csvfile = "F://data.csv";
        BufferedReader reader = null;
        String line;

        try {

            reader = new BufferedReader(new FileReader(csvfile));
            while ((line = reader.readLine()) != null) {
                String[] linearray = line.split(",");
                String qry_archivo = "insert into registro_backs values ('0', '" + csvfile + "', 'A')";
                this.qry_sol = "insert into back_surtido values ('0','" + folio + "','" + linearray[0] + "','" + linearray[1] + "','0')";
                System.out.println(qry_sol);
                try {
                    obj.insertar(qry_sol);
                    obj.insertar(qry_archivo);
                } catch (Exception e) {
                    System.out.println("Se produjo un error de carga" + e.getMessage());
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
