/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.web;


import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La clase Libros DAO implementa la interfaz 'ItfzLibrosDao.java' y corresponde
 * a la clase 'Data Access Object' de nuestro programa.
 *
 * @author amatore
 */
public class Dao {

    private Connection conexion;
    private boolean bd_connect = false;
    private String rutaBD;
    private String user;
    private String pass;
    private boolean isbn_search;
    private boolean encontrado;
    private boolean isbn_duplicado;

    /**
     * Este metódo tiene como función principal conectarse a la base de datos
     * mediante jdbc (com.mysql.jdbc.Driver)
     *
     * @param method String de uso interno para registrar el LOG de acceso a
     * base de datos, nos permite identificar el método que accede a la funcion
     * abrirConexion()
     *
     */
    public void abrirConexion() {
        try {
            try {
                //setBd_connect(true);
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
            }
            setRutaBD("jdbc:mysql://127.0.0.1:3306/libreria4");
            setUser("curso");
            setPass("curso");
            conexion = DriverManager.getConnection(getRutaBD(), getUser(), getPass());

        } catch (SQLException ex) {
            System.out.println("Error al abrir la conexión");
            //setBd_connect(false);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
            //setBd_connect(false);
        } //finally {
          //  if (isBd_connect()) {
            //    System.out.println("LOG, conexión BD OK");
            //}

        }

    /**
     * @return the rutaBD
     */
    public String getRutaBD() {
        return rutaBD;
    }

    /**
     * @param rutaBD the rutaBD to set
     */
    public void setRutaBD(String rutaBD) {
        this.rutaBD = rutaBD;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }
    public void cerrarConexion() {
        try {
            // cerrar la conexion
           // setBd_connect(false);
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexion");
        }
    }
    
 public List<String> consultarTodos() {

        List<String> usuarios = new ArrayList<>();

        try {

            //abrirConexion();
            System.out.println("sessio oberta");
            //Elimina el libro de la tabla que coincida con el identificador como argumento
            String query = "select name from usuarios";
            PreparedStatement stm = conexion.prepareStatement(query);

            ResultSet resultados = stm.executeQuery();

            while (resultados.next()) {

                usuarios.add(resultados.getString("name"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } //finally {
           // cerrarConexion();
        //}

        return usuarios;
    }    
 
 public boolean Login(String usuario, String password) {
     boolean result=false;
     
        try {

            //abrirConexion();
            System.out.println("sessio oberta");
            //Elimina el libro de la tabla que coincida con el identificador como argumento
            String query = "select * from usuarios where name=? and password=?";
            PreparedStatement stm = conexion.prepareStatement(query);
            stm.setString(1, usuario);
            stm.setString(2, password);

            ResultSet resultados = stm.executeQuery();

            while (resultados.next()) {

               result=true;

            }

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } //finally {
           // cerrarConexion();
        //}
        return result;
       
    }    
    


}


