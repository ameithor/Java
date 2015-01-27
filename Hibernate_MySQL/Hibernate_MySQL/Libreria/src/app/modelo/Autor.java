/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/** Se refiere al Autor que tienen los libros almacenados en la libreria
 *  La relacion con Libro es de N a M. Un autor puede tener varios Libros 
 *  y por contra, un Libro puede tener varios autores.
 *
 * @author amatore
 */
public class Autor implements Serializable {
    
    private int ID;
    private String nombre;
    private String nacionalidad;
    private String comentarios;
    private Set<Libro> libros = new HashSet<Libro>();
    
    
    public Autor(){
        
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the nacionalidad
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * @param nacionalidad the nacionalidad to set
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * @return the comentarios
     */
    public String getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios the comentarios to set
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * @return the libros
     */
    public Set<Libro> getLibros() {
        return libros;
    }

    /**
     * @param libros the libros to set
     */
    public void setLibros(Set<Libro> libros) {
        this.libros = libros;
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
 
    
    public void addLibro(Libro l){
        this.libros.add(l);
        l.getAutores().add(this);
    
    }
    
    
    
}
