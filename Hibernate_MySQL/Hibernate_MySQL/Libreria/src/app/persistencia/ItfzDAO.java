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
import java.util.List;

/**
 *
 * @author amatore
 */
public interface ItfzDAO {

    public boolean altaLibro(Libro libro);
    
    public boolean altaEditorial(Editorial editorial);
    
    public boolean altaAutor(Autor autor);
    
    public boolean altaDireccion(Direccion direccion);

    public boolean eliminarEntidad(int id, String entidad);
    
    public boolean eliminarTodos(String entidad);

    public List<Libro> consultarLibros();
    
    public List<Autor> consultarAutores();
    
    public List<Editorial> consultarEditoriales();

    public Libro consultarISBN(String isbn);

    public List<Libro> consultarTituloLibro(String titulo);
    
    public List<Editorial> consultarNombreEditorial(String nombre);
    
    public List<Autor> consultarNombreAutor(String nombre);
    
    public List<Autor> consultarNacionalidadAutor(String nacionalidad); 

    public boolean modificarPrecioLibro(String isbn, double precio); 

}
