/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modelo;

import java.io.Serializable;

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
    private String autor;
    private String editorial;
    private String isbn;
    private int publicacion;
    private double precio;
    private String descripcion;

    /**
     * Constructor Vacío de la clase
     */
    public Libro() {

    }

    /**
     * Sobrecarga del constructor
     *
     * @param titulo
     * @param autor
     * @param editorial
     * @param isbn
     * @param publicacion
     * @param precio
     * @param descripcion
     */
    public Libro(String titulo, String autor, String editorial, String isbn, int publicacion, double precio, String descripcion) {

        this.titulo = titulo;
        this.autor = autor;
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
     * @param autor
     * @param editorial
     * @param isbn
     * @param publicacion
     * @param precio
     * @param descripcion
     */
    public Libro(int ID, String titulo, String autor, String editorial, String isbn, int publicacion, double precio, String descripcion) {

        this.ID = ID;
        this.titulo = titulo;
        this.autor = autor;
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

        System.out.print("ID: " + ID + "\nTitulo: " + titulo + "\nAutor: " + autor + "\nEditorial: " + editorial + "\nISBN: " + isbn + "\nPublicacion: " + publicacion + "\nPrecio: " + precio + "\nDescripcion: " + descripcion);
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
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
     * @return the autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * @return the editorial
     */
    public String getEditorial() {
        return editorial;
    }

    /**
     * @param editorial the editorial to set
     */
    public void setEditorial(String editorial) {
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

}
