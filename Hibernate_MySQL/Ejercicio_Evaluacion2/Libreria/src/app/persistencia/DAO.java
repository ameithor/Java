/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.persistencia;

import app.modelo.Autor;
import app.modelo.Direccion;
import app.modelo.Editorial;
import app.modelo.Libro;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * La clase DAO implementa la interfaz 'ItfzDao.java' y corresponde a la clase
 * 'Data Access Object' de nuestro programa. forma parte de la capa de
 * persistencia y se encarga principalmente de la gestion de conexión con la
 * base de datos mediante Hibernate.
 *
 * @author amatore
 */
public class DAO implements ItfzDAO {

    private Connection conexion;
    private boolean bd_connect = false;
    private String rutaBD;
    private String user;
    private String pass;
    private boolean isbn_search;
    private boolean encontrado;
    private boolean isbn_duplicado;

    public SessionFactory sf;
    public Session session;
    public Transaction tx;

    /**
     * Este metódo tiene como función principal conectarse a la base de datos
     * mediante Hibernate. El archivo de configuración de encuentra en la raiz
     * del proyecto: "hibernate.cfg.xml"
     *
     */
    public void abrirConexion() {

        try {
            sf = new Configuration().configure().buildSessionFactory();
            session = sf.openSession();
            tx = session.getTransaction();
            tx.begin();
        } catch (HibernateException e) {
            System.out.println("Error al abrir sesion. Mas detalles -->" + e.getMessage());

        }

    }

    /**
     * Sirve para cerrar la conexión a la base de datos y realizar el commit()
     * Que va apersistir los datos modificados en esta transaction.
     */
    public void cerrarConexion() {

        try {
            tx.commit();
            session.close();
            sf.close();
        } catch (HibernateException e) {
            System.out.println("Error al cerrar sesion. Mas detalles -->" + e.getMessage());

        }

    }

    /**
     * Esta funcion nos devuelve el valor máximo del campo ID de la base de
     * datos
     *
     * @param entidad String que se refiere al nombre de la clase de la que
     * queremos obtener el ID. No confundir con el nombre de la tabla de la base
     * de datos a la que persistimos.
     *
     * @return ID - integer
     */
    public int getID(String entidad) {
        cerrarConexion();
        abrirConexion();
        int max = -1;
        System.out.println("MAX: " + max);
        try {

            Query q1 = session.createQuery("select max(id) from " + entidad);
            q1.setMaxResults(1);
            max = (int) q1.uniqueResult();
            max = max + 1;
            System.out.println("MAX: " + max);

        } catch (HibernateException e) {
            System.out.println("Error al consultar la ID de la entidad " + entidad + " . Mas detalles -->" + e.getMessage());
        }

        return max;

    }

    /**
     * Da de alta un objeto de tipo Libro en la Base de Datos
     *
     * @param libro Libro por parámetro que va persistirse en la base de datos
     * mediante la función: session.save(libro);
     * @return true - Si se ha dado de alta el libro correctamente
     */
    public boolean altaLibro(Libro libro) {
        try {

            //Debe crear un nuevo registro en la tabla con los datos del libro recibido como argumento
            int maxID = getID("Libro");
            libro.setID(maxID);
            session.save(libro);
            libro.mostrar();

        } catch (HibernateException ex) {

            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("\n**********Error HQL en altaLibro()********** Mas detalles -->" + ex.getMessage());
            setIsbn_duplicado(true);

        }

        return true;
    }

    /**
     * Da de alta un objeto de tipo Editorial en la Base de Datos
     *
     * @param editorial Editorial por parámetro que va persistirse en la base de
     * datos mediante la función: session.save(editorial);
     * @return true - Si se ha dado de alta la Editorial correctamente
     */
    public boolean altaEditorial(Editorial editorial) {
        try {

            //Debe crear un nuevo registro en la tabla con los datos de la editorial recibido como argumento
            int maxID = getID("Editorial");
            editorial.setID(maxID);
            session.save(editorial);

        } catch (HibernateException ex) {

            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("\n**********Error HQL en altaEditorial()********** Mas detalles -->" + ex.getMessage());
            setIsbn_duplicado(true);

        }

        return true;
    }

    /**
     * Da de alta un objeto de tipo Autor en la Base de Datos
     *
     * @param autor Autor por parámetro que va persistirse en la base de datos
     * mediante la función: session.save(autor);
     * @return true - Si se ha dado de alta el Autor correctamente
     */
    public boolean altaAutor(Autor autor) {
        try {

            //Debe crear un nuevo registro en la tabla con los datos del autor recibido como argumento
            int maxID = getID("Autor");
            autor.setID(maxID);
            session.save(autor);

        } catch (HibernateException ex) {

            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("\n**********Error HQL en altaAutor()********** Mas detalles -->" + ex.getMessage());
            setIsbn_duplicado(true);

        }

        return true;
    }

    /**
     * Da de alta un objeto de tipo Direccion en la Base de Datos
     *
     * @param direccion Direccion por parámetro que va persistirse en la base de
     * datos mediante la función: session.save(direccion);
     * @return true - Si se ha dado de alta el Direccion correctamente
     */
    public boolean altaDireccion(Direccion direccion) {
        try {

            //Debe crear un nuevo registro en la tabla con los datos de la direccion recibido como argumento
            int maxID = getID("Direccion");
            direccion.setID(maxID);
            session.save(direccion);

        } catch (HibernateException ex) {

            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("\n**********Error HQL en altaDireccion()********** Mas detalles -->" + ex.getMessage());
            setIsbn_duplicado(true);

        }

        return true;
    }

    /**
     * Elimina un libro cuyo ID se pas apor parámetro
     *
     * @param id param entidad Sirve para generar la query mediante la funcion
     * Query query = session.createQuery("DELETE FROM " + entidad + " WHERE
     * ID=:id");
     * @return true - Si ha sido posible eliminar el objeto Libro
     * satisfactoriamente
     */
    public boolean eliminarEntidad(int id, String entidad) {

        int result = -1;

        try {

            Query query = session.createQuery("DELETE FROM " + entidad + " WHERE ID=:id");
            query.setParameter("id", id);
            result = query.executeUpdate();

        } catch (HibernateException e) {

            System.out.println("\n**********Error HQL en eliminarEntidad()********** Mas detalles -->" + e.getMessage());

        } finally {
            if (result > 0) {

                System.out.println("\n**********Se ha eliminado el id: " + id + " de la entidad: " + entidad + " -- DELETE Ok**********");
                return true;

            } else {

                System.out.println("\n**********NO! Se ha eliminado el id: " + id + " de la entidad: " + entidad + " -- DELETE Ok**********");
                return false;

            }
        }

    }

    /**
     * Funcion auxiliar añadida para Eliminar todos los Registros de la Base de
     * Datos. Se ha utilizado par aoperaciones de testeo del programa.
     *
     * @param entidad Sirve para generar la query mediante la funcion Query
     * query = session.createQuery("DELETE FROM " + entidad );
     * @return true - Si ha sido posible eliminar todos los objetos de la Base
     * de datos de forma satisfactoria.
     */
    public boolean eliminarTodos(String entidad) {

        int maxentidad = getID(entidad);
        int result = -1;

        try {

            Query query = session.createQuery("DELETE FROM " + entidad);
            result = query.executeUpdate();

        } catch (HibernateException e) {

            System.out.println("\n**********Error HQL en eliminarEntidad()********** Mas detalles -->" + e.getMessage());

        } finally {
            if (result == maxentidad - 1) {
                System.out.println("MAXEntidaD:" + maxentidad + " Results:" + result);
                System.out.println("\n**********Se ha eliminado por completo la entidad: " + entidad + " -- DELETE Ok**********\n" + result + " objeto(s) eliminado(s)");
                return true;

            } else {
                System.out.println("MAXEntidaD:" + maxentidad + " Results:" + result);
                System.out.println("\n**********NO! Se ha eliminado  la entidad: " + entidad + " -- DELETE CANCELADO**********");
                return false;

            }
        }

    }

    /**
     * Devuelve un objeto tipo List<Libro> con todos los Libros existentes en la
     * Base de datos. En el programa principal 'MainFrame.java' se ha utilizado
     * para rellenar la Tabla principal.
     *
     * @return List<Libro> - Lista de objetos Libro.
     */
    public List<Libro> consultarLibros() {

        List<Libro> libreria = new ArrayList<>();

        try {
            Query q1 = session.createQuery("from Libro");
            libreria = q1.list();

        } catch (HibernateException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return libreria;
    }

    /**
     * Devuelve un objeto tipo List<Autor> con todos los Autor existentes en la
     * Base de datos. En el programa principal 'MainFrame.java' se ha utilizado
     * para rellenar la Tabla principal.
     *
     * @return List<Autor> - Lista de objetos Autor.
     */
    public List<Autor> consultarAutores() {

        List<Autor> autores = new ArrayList<>();

        try {
            Query q1 = session.createQuery("from Autor");
            autores = q1.list();

        } catch (HibernateException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return autores;
    }

    /**
     * Devuelve un objeto tipo List<Editorial> con todos los Editorial
     * existentes en la Base de datos. En el programa principal 'MainFrame.java'
     * se ha utilizado para rellenar la Tabla principal.
     *
     * @return List<Editorial> - Lista de objetos Editorial.
     */
    public List<Editorial> consultarEditoriales() {

        List<Editorial> editoriales = new ArrayList<>();

        try {
            Query q1 = session.createQuery("from Editorial");
            editoriales = q1.list();

        } catch (HibernateException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return editoriales;
    }

    /**
     * Devuelve un objeto Libro cuyo ISBN coincida con el pasado por parámetro.
     * En el programa principal 'MainFrame.java' se ha utilizado para dar
     * funcionalidad al botón de búsqueda.
     *
     * @param isbn - String
     * @return Libro - clase principal del paquete 'app.modelo'
     */
    public Libro consultarISBN(String isbn) {

        List<Libro> libreria = new ArrayList<>();
        Libro libro = null;

        try {
            Query q1 = session.createQuery(" from Libro " + " where isbn LIKE :isbn");
            q1.setParameter("isbn", isbn);
            libreria = q1.list();

            if (libreria.size() > 0) {
                isbn_search = true;
                libro = (Libro) libreria.get(0);
            }

        } catch (HibernateException e) {
            System.out.println("Error al consultar el ISBN: " + isbn + " . Mas detalles -->" + e.getMessage());
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
    public List<Libro> consultarTituloLibro(String titulo) {

        //El argumento titulo recibido en el metodo puede que no sea el nombre completo
        //Se trata de devolver una lista con todos los libros que ocntienen ese dato
        //como parte de su titulo. Utilizar el operador 'LIKE'
        List<Libro> libreria = new ArrayList<>();

        try {

            Query q1 = session.createQuery("from Libro l where l.titulo LIKE :titulo ");
            /*Dara que el LIKE funcione correctamente debe añadirse % en la consulta como comodin
             Ejemplo:
             List<Libro> libreria = new ArrayList<>();
             libreria=dao.consultarTitulo("%br%"); --> esto devuelve una lista con todos los libro
             cuyo titulo contiene la cadena 'br'
             */
            q1.setParameter("titulo", titulo);
            libreria = q1.list();

            if (libreria.size() > 0) {
                encontrado = true;

            }

        } catch (HibernateException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return libreria;

    }

    /**
     * Devuelve un objeto Editorial cuyo Titulo coincida tottal o parcialmente
     * con el pasado por parámetro. En el programa principal 'MainFrame.java' se
     * ha utilizado para dar funcionalidad al botón de búsqueda.
     *
     * @param nombre - String
     * @return List<Editorial> - Lista de objetos de tipo Editorial
     */
    public List<Editorial> consultarNombreEditorial(String nombre) {

        //El argumento nombre recibido en el metodo puede que no sea el nombre completo
        //Se trata de devolver una lista con todos los editoriales que ocntienen ese dato
        //como parte de su nombre. Utilizar el operador 'LIKE'
        List<Editorial> editoriales = new ArrayList<>();

        try {

            Query q1 = session.createQuery("from Editorial e where e.nombre LIKE :nombre ");
            /*Dara que el LIKE funcione correctamente debe añadirse % en la consulta como comodin
             Ejemplo:
             List<Editorial> libreria = new ArrayList<>();
             libreria=dao.consultarTitulo("%br%"); --> esto devuelve una lista con todos los Editorial
             cuyo titulo contiene la cadena 'br'
             */
            q1.setParameter("nombre", nombre);
            editoriales = q1.list();

        } catch (HibernateException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return editoriales;

    }

    /**
     * Devuelve un objeto Autor cuyo Titulo coincida tottal o parcialmente con
     * el pasado por parámetro. En el programa principal 'MainFrame.java' se ha
     * utilizado para dar funcionalidad al botón de búsqueda.
     *
     * @param nombre - String
     * @return List<Autor> - Lista de objetos de tipo Autor
     */
    public List<Autor> consultarNombreAutor(String nombre) {

        //El argumento titulo recibido en el metodo puede que no sea el nombre completo
        //Se trata de devolver una lista con todos los libros que ocntienen ese dato
        //como parte de su titulo. Utilizar el operador 'LIKE'
        List<Autor> autores = new ArrayList<>();

        try {
            // abrirConexion();

            Query q1 = session.createQuery("from Autor e where e.nombre LIKE :nombre ");
            /*Dara que el LIKE funcione correctamente debe añadirse % en la consulta como comodin
             Ejemplo:
             List<Libro> libreria = new ArrayList<>();
             libreria=dao.consultarTitulo("%br%"); --> esto devuelve una lista con todos los libro
             cuyo titulo contiene la cadena 'br'
             */
            q1.setParameter("nombre", nombre);
            autores = q1.list();

        } catch (HibernateException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return autores;

    }

    /**
     * Devuelve un objeto Autor cuyo Titulo coincida tottal o parcialmente con
     * el pasado por parámetro. En el programa principal 'MainFrame.java' se ha
     * utilizado para dar funcionalidad al botón de búsqueda.
     *
     * @param nacionalidad - String
     * @return List<Autor> - Lista de objetos de tipo Autor
     */
    public List<Autor> consultarNacionalidadAutor(String nacionalidad) {

        //El argumento titulo recibido en el metodo puede que no sea el nombre completo
        //Se trata de devolver una lista con todos los libros que ocntienen ese dato
        //como parte de su titulo. Utilizar el operador 'LIKE'
        List<Autor> autores = new ArrayList<>();

        try {
            // abrirConexion();
                        /*Dara que el LIKE funcione correctamente debe añadirse % en la consulta como comodin
             Ejemplo:
             List<Libro> libreria = new ArrayList<>();
             libreria=dao.consultarTitulo("%br%"); --> esto devuelve una lista con todos los libro
             cuyo titulo contiene la cadena 'br'
             */
            Query q1 = session.createQuery("from Autor e where e.nacionalidad=:nacionalidad ");
            q1.setParameter("nacionalidad", nacionalidad);
            autores = q1.list();

        } catch (HibernateException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autores;
    }

    /**
     * El metodo recibe el isbn dle libro a actualizar, asi como el nuevo
     * precio.
     *
     * @param isbn - String
     * @param precio - double
     * @return true - Si se ha podido cambiar el precio satisfactoriamente
     */
    public boolean modificarPrecioLibro(String isbn, double precio) {
        try {

            //El metodo recibe el isbn del libro a actualizar, asi como el nuevo precio
            Query q1 = session.createQuery("UPDATE Libro l  set l.precio = :precio"
                    + " WHERE isbn = :isbn");
            q1.setParameter("precio", precio);
            q1.setParameter("isbn", isbn);
            int result = q1.executeUpdate();

            if (result > 0) {

                System.out.println("\n**********Precio del libro con isbn " + isbn + " cambiado Ok**********\nNuevo precio: " + precio);

            }

        } catch (HibernateException e) {

            System.out.println("Error al realizar cambio de precio. Mas detalles -->" + e.getMessage());

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
