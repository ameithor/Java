/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/** Filtro de tiempo de la aplicacion. mide en ms cada operacion que se realiza en ella
 *
 * @author amatore
 */
public class FiltroTiempo implements Filter{
      
    
    
    /**
     * Default constructor. 
     */
    public FiltroTiempo() {
        // TODO Auto-generated constructor stub
    }
    
/**
 * 
 * @param filterConfig
 * @throws ServletException 
 */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
      
    }

    /**
     * 
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException 
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      // Procesamos la peticion
		long tiempoInicio = System.currentTimeMillis();
		
		// paso de testigo
		chain.doFilter(request, response);
		
		// Procesamos la respuesta
		long tiempoFinal = System.currentTimeMillis();
		
		System.out.println("Ha tardado " + (tiempoFinal - tiempoInicio) + " mseg.");
    }

    /**
     * 
     */
    @Override
    public void destroy() {
   
    }
    
}
