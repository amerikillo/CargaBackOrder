/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import conn.ConectionDB;

/**
 *
 * @author CEDIS NAY 1
 */
public class EdicionFolios {

    ConectionDB conexion = new ConectionDB();

    public void EdicionFolios(int caso, String folio) {
        switch (caso) {
            case 1://Edicion de Requrimiento de backorder
                break;
            case 2://Eliminacion de Requrimiento de backorder
                eliminaRequerimiento(folio);
                break;
            case 3://Edicion de Abasto de backorder
                break;
            case 4://Eliminacion de Abasto de backorder
                eliminaAbasto(folio);
                break;
        }
    }

    //Metodo para eliminar requerimiento
    private void eliminaRequerimiento(String folio) {
        String borra = "delete from back_solicitado where fol_bac = '" + folio + "'";
        try {
            conexion.borrar2(borra);
        } catch (Exception e) {
            System.out.println("Se produjo un error de eliminacion" + e.getMessage());
        }
    }
    //Metodo para eliminar abasto

    private void eliminaAbasto(String folio) {
        String borra = "delete from back_surtido where fol_bac = '" + folio + "'";
        try {
            conexion.borrar2(borra);
        } catch (Exception e) {
            System.out.println("Se produjo un error de eliminacion" + e.getMessage());
        }
    }

    public void actualiza_registro(String registro, String tabla, String clave, String cantidad) {
        String actualiza = "";
        if (tabla.equals("back_solicitado")) {
            actualiza = "update " + tabla + " set can_sol = '" + cantidad + "' where id_bac_sol = '" + registro + "' ";
        }
        if (tabla.equals("back_surtido")) {
            actualiza = "update " + tabla + " set can_sur = '" + cantidad + "' where id_bac_sur = '" + registro + "' ";
        }
        try {
            conexion.actualizar(actualiza);
        } catch (Exception e) {
            System.out.println("Se produjo un error de actualizacion" + e.getMessage());
        }

    }
}
