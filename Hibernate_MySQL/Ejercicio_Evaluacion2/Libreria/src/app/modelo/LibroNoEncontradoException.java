/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modelo;

/**
 * Excepción Personalizada que se va a utilizar cuando el usuario utilize el
 * campo de Búsqueda Habilitado en la parte superior dle programa.
 *
 * @author amatore
 */
public class LibroNoEncontradoException extends Exception {

    String message;

    public LibroNoEncontradoException(String message) {
        this.message = message;

    }

    public String message() {
        return message;
    }

}
