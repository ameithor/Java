/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Clase principal de la capa app-modelo. Es el objeto básico a partir del cual
 * vamos a implementar nuestor programa de Libreria. Se trata de una Clase que
 * tiene las propiedades necesarias para almacenar el libro en la base de datos.
 *
 * @author amatore
 */
public class Libro implements Serializable {

    private int ID;
    private String titulo;
    private String isbn;
    private int publicacion;
    private double precio;
    private String descripcion;

    private Set<Autor> autores = new HashSet<Autor>();
    private Editorial editorial;

    /**
     * Constructor Vacío de la clase
     */
    public Libro() {

    }

    public void mostrarAutores() {
        int i = 0;
        Iterator it = autores.iterator();

        while (it.hasNext()) {

            System.out.println(it.next());
        }

    }

    /**
     * Sobrecarga del constructor
     *
     * @param titulo
     * @param autores
     * @param editorial
     * @param isbn
     * @param publicacion
     * @param precio
     * @param descripcion
     */
    public Libro(String titulo, Set<Autor> autores, Editorial editorial, String isbn, int publicacion, double precio, String descripcion) {

        this.titulo = titulo;
        this.autores = autores;
        this.editorial = editorial;
        this.isbn = isbn;
        this.publicacion = publicacion;
        this.precio = precio;
        this.descripcion = descripcion;

    }

    /**
     * Sobrecarga del constructor
     *
     * @param ID
     * @param titulo
     * @param autores
     * @param editorial
     * @param isbn
     * @param publicacion
     * @param precio
     * @param descripcion
     */
    public Libro(int ID, String titulo, Set<Autor> autores, Editorial editorial, String isbn, int publicacion, double precio, String descripcion) {

        this.ID = ID;
        this.titulo = titulo;
        this.autores = autores;
        this.editorial = editorial;
        this.isbn = isbn;
        this.publicacion = publicacion;
        this.precio = precio;
        this.descripcion = descripcion;

    }

    /**
     * Metodo auxiliar para Mostrar un libro por consola. Se ha utilizado para
     * realizar LOG y testeo de la aplicación.
     */
    public void mostrar() {

        System.out.print("ID: " + getID() + "\nTitulo: " + titulo + "\nAutor: " + this.getStringAutores() + "\nEditorial: " + editorial.getNombre() + "\nISBN: " + isbn + "\nPublicacion: " + publicacion + "\nPrecio: " + precio + "\nDescripcion: " + descripcion);
    }

    public void addAutor(Autor a) {
        this.autores.add(a);
        a.getLibros().add(this);

    }

    /**
     * @return the ID
     */
    public int getID() {
        return this.ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the editorial
     */
    public Editorial getEditorial() {
        return editorial;
    }

    /**
     * @param editorial the editorial to set
     */
    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the publicacion
     */
    public int getPublicacion() {
        return publicacion;
    }

    /**
     * @param publicacion the publicacion to set
     */
    public void setPublicacion(int publicacion) {
        this.publicacion = publicacion;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the autores
     */
    public Set<Autor> getAutores() {

        return autores;
    }

    /**
     *
     * @return
     */
    public String getStringAutores() {
        String TAutores = "";
        Iterator iter = autores.iterator();
        Autor a = new Autor();

        while (iter.hasNext()) {
            a = (Autor) iter.next();
            TAutores = TAutores + a.getNombre() + ",";

        }
        return TAutores;
    }

    /**
     * @param autores the autores to set
     */
    public void setAutores(Set<Autor> autores) {
        this.autores = autores;
    }

    /**
     *
     * @param a
     */
}
