/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.negocio;

import app.modelo.Libro;
import app.modelo.LibroNoEncontradoException;
import app.persistencia.Dao;
import java.util.ArrayList;
import java.util.List;

/**
 * Es la clase principal de la capa de negocio Implementa la interface
 * 'ItfzGestionLibreria.java' y accede a todas las funciones de la capa de
 * persistencia.
 *
 * @author amatore
 *
 */
public class GestionLibreria implements ItfzGestionLibreria {

    public List<Libro> libreria_cache;
    List<String> keywords;
    private Dao dao;
    private String rutaBD;
    private String user;
    private String pass;
    public boolean isbn_search;
    public boolean encontrado;
    public boolean isbn_duplicado;

    public GestionLibreria() {

        libreria_cache = new ArrayList<>();
        keywords = new ArrayList<>();
        dao = new Dao();

    }
    //IMPORTANTE: Todos los metodos deben acceder a la capa de persistencia a trave de la interface ItfzLibros DAO

    /**
     * @see en la capa app.persistencia
     *
     */
    @Override
    public boolean altaLibro(Libro libro) {

        isbn_duplicado = dao.isIsbn_duplicado();
        return dao.altaLibro(libro);
    }

    @Override
    public boolean eliminarLibro(int id) {

        return dao.eliminarLibro(id);

    }

    /**
     *
     * @return
     */
    @Override
    public List<String> consultarTodos() {

        return dao.consultarTodos();

    }

    @Override
    public Libro consultarISBN(String isbn) {

        Libro l = dao.consultarISBN(isbn);
        isbn_search = dao.isIsbn_search();
        return l;

    }

    public List<Libro> consultarLibros(){
        
         return this.dao.consultarLibros();
    }
    
    @Override
    public boolean Login(String usuario, String password) {
        
        return this.dao.Login(usuario, password);
        
    }
    
    
    @Override
    public List<Libro> consultarTitulo(String titulo) {
        //Asignando a la List que tenemos como Atributo en esta clase
        //dejamos el Reusltado de la última búsqueda en caché guardado.
        try {
            this.libreria_cache = dao.consultarTitulo(titulo);
            keywords.add(titulo);
            //Asignando a la List que tenemos como Atributo en esta clase
            //dejamos el Reusltado de la última búsqueda en caché guardado.
            //throw new LibroNoEncontradoException("Error");
            encontrado = dao.isEncontrado();
            if (!encontrado) {
                throw new LibroNoEncontradoException("Libro no Encontrado");
            }

        } catch (LibroNoEncontradoException e) {
            System.out.println("Libro no Encontrado" + e.message());
        }

        return this.libreria_cache;

    }

    @Override
    public List<String> consultarLibrosAutor(String autor) {

        return this.dao.consultarLibrosAutor(autor);
    }

    @Override
    public List<String> consultarAutores() {

        return this.dao.consultarAutores();
    }

    @Override
    public List<String> consultarEditoriales() {

        return this.dao.consultarEditoriales();

    }

    @Override
    public List<String> consultarLibrosEditorial(String editorial) {

        return this.dao.consultarLibrosEditorial(editorial);
    }

    @Override
    public boolean modificarPrecio(String isbn, double precio) {

        return dao.modificarPrecio(isbn, precio);

    }

    public boolean limpiar_cache() {

        libreria_cache.clear();
        return true;
    }

    public boolean borrar_historial() {

        keywords.clear();
        return true;
    }

    public boolean conectar() {

        dao.abrirConexion();
        return dao.isBd_connect();

    }

    public boolean cerrarConexion() {

        dao.cerrarConexion();
        return dao.isBd_connect();

    }

    /**
     * @return the rutaBD
     */
    public String getRutaBD() {
        return dao.getRutaBD();
    }

    /**
     * @param rutaBD the rutaBD to set
     */
    public void setRutaBD(String rutaBD) {
        this.dao.setRutaBD(rutaBD);
    }

    /**
     * @return the user
     */
    public String getUser() {
        return dao.getUser();
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.dao.setUser(user);
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return dao.getPass();
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.dao.setPass(pass);
    }

}
