/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.negocio;

import app.modelo.Autor;
import app.modelo.Editorial;
import app.modelo.Libro;
import app.modelo.LibroNoEncontradoException;
import app.persistencia.DAO;
import java.util.ArrayList;
import java.util.List;

/**
 * Es la clase principal de la capa de negocio Implementa la interface
 * 'ItfzGestionLibreria.java' y accede a todas las funciones de la capa de
 * persistencia mediante el atributo privado -dao-
 * 
 *IMPORTANTE: Todos los metodos deben acceder a la capa de persistencia a trave de la interface ItfzLibros DAO
 * 
 * @author amatore
 *
 */
public class GestionLibreria implements ItfzGestionLibreria {

    public List<Libro> libreria_cache;
    public List<Editorial> editoriales_cache;
    public List<Autor> autores_cache;
    List<String> keywords;
    private DAO dao;
    private String rutaBD;
    private String user;
    private String pass;
    public boolean isbn_search;
    public boolean encontrado;
    public boolean isbn_duplicado;

    public GestionLibreria() {

        libreria_cache = new ArrayList<>();
        keywords = new ArrayList<>();
        dao = new DAO();

    }
    //IMPORTANTE: Todos los metodos deben acceder a la capa de persistencia a trave de la interface ItfzLibros DAO

    /**
     * @see en la capa app.persistencia
     *
     */
    public boolean altaLibro(Libro libro) {

        isbn_duplicado = getDao().isIsbn_duplicado();
        return getDao().altaLibro(libro);
    }

    public boolean altaAutor(Autor autor) {

        return getDao().altaAutor(autor);
    }

    public boolean altaEditorial(Editorial editorial) {

        return getDao().altaEditorial(editorial);
    }

    @Override
    public boolean eliminarLibro(int id) {

        return getDao().eliminarEntidad(id, "Libro");

    }

    public boolean eliminarAutor(int id) {

        return getDao().eliminarEntidad(id, "Autor");

    }

    public boolean eliminarEditorial(int id) {

        System.out.println("Editorial borrada");
        return getDao().eliminarEntidad(id, "Editorial");

    }

    @Override
    public List<Libro> consultarTodos() {

        libreria_cache = getDao().consultarLibros();

        return libreria_cache;

    }

    @Override
    public Libro consultarISBN(String isbn) {

        Libro l = getDao().consultarISBN(isbn);
        isbn_search = getDao().isIsbn_search();
        return l;

    }

    public List<Editorial> consultarEditoriales() {

        editoriales_cache = getDao().consultarEditoriales();
        return editoriales_cache;

    }

    public List<Autor> consultarAutores() {

        autores_cache = getDao().consultarAutores();
        return autores_cache;

    }

    public List<Libro> consultarTitulo(String titulo) {
        //Asignando a la List que tenemos como Atributo en esta clase
        //dejamos el Reusltado de la última búsqueda en caché guardado.
        try {
            this.libreria_cache = getDao().consultarTituloLibro(titulo);
            keywords.add(titulo);
            //Asignando a la List que tenemos como Atributo en esta clase
            //dejamos el Reusltado de la última búsqueda en caché guardado.
            //throw new LibroNoEncontradoException("Error");
            encontrado = getDao().isEncontrado();
            if (!encontrado) {
                throw new LibroNoEncontradoException("Libro no Encontrado");
            }

        } catch (LibroNoEncontradoException e) {
            System.out.println("Libro no Encontrado" + e.message());
        } finally {
            return this.libreria_cache;
        }

    }

    public boolean modificarPrecio(String isbn, double precio) {

        return getDao().modificarPrecioLibro(isbn, precio);

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

        getDao().abrirConexion();
        return getDao().isBd_connect();

    }

    public boolean cerrarConexion() {

        getDao().cerrarConexion();
        return getDao().isBd_connect();

    }

    /**
     * @return the rutaBD
     */
    public String getRutaBD() {
        return getDao().getRutaBD();
    }

    /**
     * @param rutaBD the rutaBD to set
     */
    public void setRutaBD(String rutaBD) {
        this.getDao().setRutaBD(rutaBD);
    }

    /**
     * @return the user
     */
    public String getUser() {
        return getDao().getUser();
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.getDao().setUser(user);
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return getDao().getPass();
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.getDao().setPass(pass);
    }

    /**
     * @return the dao
     */
    public DAO getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(DAO dao) {
        this.dao = dao;
    }

}
