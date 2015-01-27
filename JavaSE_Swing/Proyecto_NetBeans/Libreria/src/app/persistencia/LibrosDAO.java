/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.persistencia;

import app.modelo.Libro;
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
public class LibrosDAO implements ItfzLibrosDao {

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
    public void abrirConexion(String method) {
        try {
            setBd_connect(true);
            Class.forName("com.mysql.jdbc.Driver");
            setRutaBD("jdbc:mysql://127.0.0.1:3306/libreria");
            setUser("curso");
            setPass("curso");
            conexion = DriverManager.getConnection(rutaBD, user, pass);

        } catch (SQLException ex) {
            System.out.println("Error al abrir la conexión");
            setBd_connect(false);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LibrosDAO.class.getName()).log(Level.SEVERE, null, ex);
            setBd_connect(false);
        } finally {
            if (isBd_connect()) {
                System.out.println("LOG, conexión BD OK");
            }

        }

    }

    /**
     * Sirve para cerrar la conexión a la base de datos mediante jdbc funcion
     * close()
     */
    public void cerrarConexion() {
        try {
            // cerrar la conexion
            setBd_connect(false);
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexion");
        }
    }

    /**
     * Esta funcion nos devuelve el valor máximo del campo ID de la base de
     * datos
     *
     * @return ID - integer
     */
    public int getID() {
        int id = 0;
        try {
            abrirConexion("getID");
            String query = "select max(ID) from libros ";
            PreparedStatement stm = conexion.prepareStatement(query);
            ResultSet resultados = stm.executeQuery();

            while (resultados.next()) {

                id = resultados.getInt(1);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LibrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }

        id++;
        return id;
    }

    /**
     * Da de alta un objeto de tipo Libro en la Base de Datos
     *
     * @param libro
     * @return true - Si se ha dado de alta el libro correctamente
     */
    @Override
    public boolean altaLibro(Libro libro) {
        try {

            //Debe crear un nuevo registro en la tabla con los datos del libro recibido como argumento
            int maxID = getID();
            abrirConexion("altaLibro");
            String query = "insert into libros values (?,?,?,?,?,?,?,?) ";
            PreparedStatement stm = conexion.prepareStatement(query);
            setIsbn_duplicado(false);

            stm.setInt(1, maxID);
            stm.setString(2, libro.getTitulo());
            stm.setString(3, libro.getAutor());
            stm.setString(4, libro.getEditorial());
            stm.setString(5, libro.getIsbn());
            stm.setInt(6, libro.getPublicacion());
            stm.setDouble(7, libro.getPrecio());
            stm.setString(8, libro.getDescripcion());

            // 3.- Ejecutar la query
            int resultados = stm.executeUpdate();

            if (resultados == 1) {
                libro.mostrar();
                System.out.println("\n**********Alta Ok**********");

            }

        } catch (SQLException ex) {

            Logger.getLogger(LibrosDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("\n**********Error SQL en altaLibro()**********");
            setIsbn_duplicado(true);

        } finally {
            cerrarConexion();
        }

        return true;
    }

    /**
     * Elimina un libro cuyo ID se pas apor parámetro
     *
     * @param id
     * @return true - Si ha sido posible eliminar el objeto Libro
     * satisfactoriamente
     */
    @Override
    public boolean eliminarLibro(int id) {
        try {
            abrirConexion("eliminarLibro");
            //Elimina el libro de la tabla que coincida con el identificador como argumento
            String query = "delete from libros where id=? ";
            PreparedStatement stm = conexion.prepareStatement(query);

            stm.setInt(1, id);

            int resultados = stm.executeUpdate();

            if (resultados == 1) {
                System.out.println("\n**********libro con ID: " + id + " Eliminado Ok**********\n");

            }

        } catch (SQLException ex) {
            Logger.getLogger(LibrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }

        return true;
    }

    /**
     * Funcion auxiliar añadida para Eliminar todos los Registros de la Base de
     * Datos. Se ha utilizado par aoperaciones de testeo del programa.
     *
     * @return true - Si ha sido posible eliminar todos los objetos Libro de la
     * Base de datos de forma satisfactoria.
     */
    public boolean eliminarTodos() {
        String mensaje = "No se pudieron eliminar todos los registros";
        try {
            // 1.- Abrir la conexion
            abrirConexion("eliminarTodos");

            // 2.- Preparar la query
            Statement stm = conexion.createStatement();

            // 3.- Ejecutar la query
            int resultados = stm.executeUpdate("delete from Libros");

            // 4.- Recorrer la coleccion de registros
            if (resultados > 0) {
                System.out.println("\n**********Tabla de Libros Eliminado Ok**********\n");
            }

        } catch (SQLException ex) {
            System.out.println("Error al consultar todos los productos");
            ex.printStackTrace();
        } finally {
            // Cerrar la conexion
            cerrarConexion();
        }
        return true;
    }

    /**
     * Devuelve un objeto tipo List<Libro> con todos los Libros existentes en la
     * Base de datos. En el programa principal 'MainFrame.java' se ha utilizado
     * para rellenar la Tabla principal.
     *
     * @return List<Libro> - Lista de objetos Libro.
     */
    @Override
    public List<Libro> consultarTodos() {

        List<Libro> libreria = new ArrayList<>();

        try {

            abrirConexion("consultarTodos");
            //Elimina el libro de la tabla que coincida con el identificador como argumento
            String query = "select * from libros";
            PreparedStatement stm = conexion.prepareStatement(query);

            ResultSet resultados = stm.executeQuery();

            while (resultados.next()) {

                libreria.add(new Libro(resultados.getInt("ID"), resultados.getString("titulo"), resultados.getString("autor"), resultados.getString("editorial"), resultados.getString("isbn"), resultados.getInt("publicacion"), resultados.getDouble("precio"), resultados.getString("descripcion")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(LibrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }

        return libreria;
    }

    /**
     * Devuelve un objeto Libro cuyo ISBN coincida con el pasado por parámetro.
     * En el programa principal 'MainFrame.java' se ha utilizado para dar
     * funcionalidad al botón de búsqueda.
     *
     * @param isbn - String
     * @return Libro - clase principal del paquete 'app.modelo'
     */
    @Override
    public Libro consultarISBN(String isbn) {

        Libro libro = new Libro();

        try {
            abrirConexion("consultarISBN");
            //Elimina el libro de la tabla que coincida con el identificador como argumento
            String query = "select * from libros where isbn=? ";
            PreparedStatement stm = conexion.prepareStatement(query);

            stm.setString(1, isbn);

            ResultSet resultados = stm.executeQuery();

            if (resultados.next()) {
                setIsbn_search(true);

                libro.setID(resultados.getInt("id"));
                libro.setTitulo(resultados.getString("titulo"));
                libro.setAutor(resultados.getString("autor"));
                libro.setEditorial(resultados.getString("editorial"));
                libro.setIsbn(isbn);
                libro.setPublicacion(resultados.getInt("publicacion"));
                libro.setPrecio(resultados.getDouble("precio"));
                libro.setDescripcion(resultados.getString("descripcion"));

                System.out.println("\n**********libro con Isbn: " + isbn + " encontrado*********\n");

            } else {
                setIsbn_search(false);
                System.err.println("\n**********libro con Isbn: " + isbn + " NO encontrado*********\n");
            }

        } catch (SQLException ex) {

            Logger.getLogger(LibrosDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            cerrarConexion();
        }

        return libro;
    }

    /**
     * Devuelve un objeto Libro cuyo Titulo coincida tottal o parcialmente con
     * el pasado por parámetro. En el programa principal 'MainFrame.java' se ha
     * utilizado para dar funcionalidad al botón de búsqueda.
     *
     * @param titulo - String
     * @return List<Libro> - Lista de objetos de tipo Libro
     */
    public List<Libro> consultarTitulo(String titulo) {

        //El argumento titulo recibido en el metodo puede que no sea el nombre completo
        //Se trata de devolver una lista con todos los libros que ocntienen ese dato
        //como parte de su titulo. Utilizar el operador 'LIKE'
        List<Libro> libreria = new ArrayList<>();

        try {

            abrirConexion("consultarTitulo");
            //Elimina el libro de la tabla que coincida con el identificador como argumento
            String query = "SELECT * FROM libros WHERE titulo LIKE ?";
            PreparedStatement stm = conexion.prepareStatement(query);

            stm.setString(1, "%" + titulo + "%");

            ResultSet resultados = stm.executeQuery();

            encontrado = false;
            while (resultados.next()) {
                encontrado = true;
                libreria.add(new Libro(resultados.getInt("ID"), resultados.getString("titulo"), resultados.getString("autor"), resultados.getString("editorial"), resultados.getString("isbn"), resultados.getInt("publicacion"), resultados.getDouble("precio"), resultados.getString("descripcion")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(LibrosDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            cerrarConexion();
        }

        return libreria;

    }

    /**
     * El metodo recibe el isbn dle libro a actualizar, asi como el nuevo
     * precio.
     *
     * @param isbn - String
     * @param precio - double
     * @return true - Si se ha podido cambiar el precio satisfactoriamente
     */
    @Override
    public boolean modificarPrecio(String isbn, double precio) {
        try {
            //El metodo recibe el isbn del libro a actualizar, asi como el nuevo precio

            abrirConexion("eliminarLibro");
            //Elimina el libro de la tabla que coincida con el identificador como argumento
            String query = "UPDATE libros SET precio=? WHERE isbn=? ";
            PreparedStatement stm = conexion.prepareStatement(query);

            stm.setDouble(1, precio);
            stm.setString(2, isbn);

            int resultados = stm.executeUpdate();

            if (resultados > 0) {

                System.out.println("\n**********Precio del libro con isbn " + isbn + " cambiado Ok**********\nNuevo precio: " + precio);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LibrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
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

    /**
     * @return the isbn_search
     */
    public boolean isIsbn_search() {
        return isbn_search;
    }

    /**
     * @param isbn_search the isbn_search to set
     */
    public void setIsbn_search(boolean isbn_search) {
        this.isbn_search = isbn_search;
    }

    /**
     * @return the encontrado
     */
    public boolean isEncontrado() {
        return encontrado;
    }

    /**
     * @param encontrado the encontrado to set
     */
    public void setEncontrado(boolean encontrado) {
        this.encontrado = encontrado;
    }

    /**
     * @return the bd_connect
     */
    public boolean isBd_connect() {
        return bd_connect;
    }

    /**
     * @param bd_connect the bd_connect to set
     */
    public void setBd_connect(boolean bd_connect) {
        this.bd_connect = bd_connect;
    }

    /**
     * @return the isbn_duplicado
     */
    public boolean isIsbn_duplicado() {
        return isbn_duplicado;
    }

    /**
     * @param isbn_duplicado the isbn_duplicado to set
     */
    public void setIsbn_duplicado(boolean isbn_duplicado) {
        this.isbn_duplicado = isbn_duplicado;
    }

}
