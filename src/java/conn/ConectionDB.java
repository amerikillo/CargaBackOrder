/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author wence
 */
public class ConectionDB {

//variables miembro
    private String usuario;
    private String clave;
    private String url;
    private String driverClassName;
    private Connection conn = null;
    private Statement estancia;
    public Object objeto;
    private ResultSet rset;
    public String id_medico;

//CONSTRUCTORES
    //Constructor que toma los datos de conexion por medio de parametros
    public ConectionDB(String usuario, String clave, String url, String driverClassName) {
        this.usuario = usuario;
        this.clave = clave;
        this.url = url;
        this.driverClassName = driverClassName;
    }

    //Constructor que crea la conexion sin parametros con unos definidos en la clase
    //(meter los datos correspondientes)
    public ConectionDB() {
        //poner los datos apropiados
        this.usuario = "nayarit_rp";
        this.clave = "Y58Go0mX35";
        this.url = "jdbc:mysql://166.78.128.202:3306/receta_nay";
        this.driverClassName = "org.gjt.mm.mysql.Driver";
    }

    //metodos para recuperar los datos de conexion
    public String getClave() {
        return clave;
    }

    public String getUrl() {
        return url;
    }

    public String getUsuario() {
        return usuario;
    }

    public Connection getConn() {
        return conn;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    //metodos para establecer los valores de conexion
    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsuario(String usuario) throws SQLException {
        this.usuario = usuario;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

//la conexion propiamente dicha
    public void conectar() throws SQLException {
        try {
            Class.forName(this.driverClassName).newInstance();
            this.conn = DriverManager.getConnection(this.url, this.usuario, this.clave);
//            System.out.println("Se conectó de FORMA EXITOSA ");

        } catch (Exception err) {
            System.out.println("Error " + err.getMessage());
        }
    }
    //Cerrar la conexion

    public void cierraConexion() throws SQLException {
        this.conn.close();
    }

//METODOS PARA TRABAJAR CON LA BASE DE DATOS
    public ResultSet consulta(String consulta) throws SQLException {
        this.estancia = (Statement) conn.createStatement();

        return this.estancia.executeQuery(consulta);
    }

    // -------------------
    public void actualizar(String actualiza) throws SQLException {
        conectar();
        this.estancia = (Statement) conn.createStatement();
        estancia.executeUpdate(actualiza);
        cierraConexion();
        //this.conn.commit();
    }

    public void borrar(String borra) throws SQLException {
        conectar();
        Statement st = (Statement) this.conn.createStatement();
        System.out.println(borra);
        st.executeQuery(borra);
        cierraConexion();
    }

    public void borrar2(String borra) throws SQLException {
        conectar();
        this.estancia = (Statement) conn.createStatement();
        estancia.executeUpdate(borra);
        cierraConexion();
    }

    public void insertar(String inserta) throws SQLException {
        conectar();
        Statement st = (Statement) this.conn.createStatement();
        System.out.println(inserta);
        st.executeUpdate(inserta);
        cierraConexion();
    }
}
/**/