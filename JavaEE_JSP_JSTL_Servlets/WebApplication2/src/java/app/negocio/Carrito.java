/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.negocio;

import app.modelo.Libro;
import app.persistencia.Dao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase del modelo de negocio que sirve como punto de conexión entre la capa de
 * persistencia y la capa cliente
 *
 * @author Amador Criado
 */
public class Carrito {

    private List<Libro> carrito_cache;
    public List<Integer> carrito_id;
    private Dao dao;
    int pos;
    private double importe;

    /**
     * Constructor de la clase Carrito
     *
     */
    public Carrito() {

        dao = new Dao();
        carrito_id = new ArrayList<Integer>();
        carrito_cache = new ArrayList<>();
        importe = 0.0d;
        pos = 0;

    }

    /**
     * Agega un libro en la base de datos cuyo isbn coincida por el pasado por
     * parámetro
     *
     * @param isbn
     */
    public void AgregarLibro(int isbn) {

        getCarrito_cache().add(dao.consultarISBN(String.valueOf(isbn)));
        importe = importe + getCarrito_cache().get(pos).getPrecio();
        pos++;
    }

    /**
     *
     * @return La suma del precio de todos los libros que hay en la memoria
     * cache del carrito
     */
    double sumarImporte() {

        double suma = 0.0d;
        Libro l = new Libro();

        for (int i = 0; i < getCarrito_cache().size(); i++) {

            l = getCarrito_cache().get(i);
            suma = suma + l.getPrecio();

        }
        return suma;
    }

    /**
     *
     * @return el importe total del carrito
     */
    public double verImporte() {
        return importe;
    }

    /**
     * Elimina un libro del carrito cuyo id coincida con el pasado por parámetro
     *
     * @param id
     */
    public void eliminarLibro(String id) {

        //ese id hace referencia a la posicion de la Lista carrito_cache
        Libro l = getCarrito_cache().get(Integer.parseInt(id));

        Iterator<Libro> it = getCarrito_cache().iterator();
        while (it.hasNext()) {
            Libro aux = it.next();

            if (aux.getID() == l.getID()) {
                it.remove(); 
                importe = importe - aux.getPrecio();
                pos--;
                System.out.println("remove it");
            }
        }

    }

    /**
     * @return the importe
     */
    public double getImporte() {
        return importe;
    }

    /**
     * @param importe the importe to set
     */
    public void setImporte(double importe) {
        this.importe = importe;
    }

    /**
     * @return the carrito_cache
     */
    public List<Libro> getCarrito_cache() {
        return carrito_cache;
    }

    /**
     * @param carrito_cache the carrito_cache to set
     */
    public void setCarrito_cache(List<Libro> carrito_cache) {
        this.carrito_cache = carrito_cache;
    }

}
