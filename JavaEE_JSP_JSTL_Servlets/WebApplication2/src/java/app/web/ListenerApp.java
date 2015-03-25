/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/** Listaner de la Aplicacion Web
 *
 * @author amatore
 */
public class ListenerApp implements ServletContextListener {
    
     /**
     * Default constructor. 
     */
    public ListenerApp() {
        // TODO Auto-generated constructor stub
    }

/** 
 * 
 * @param sce 
 */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
         ServletContext ctxApp = sce.getServletContext();
         String prom = ctxApp.getInitParameter("oferta");
         ctxApp.setAttribute("oferta", prom);
         System.out.println(" -- Aplicacion arrancada ----");
    }

    /**
     * 
     * @param sce 
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println(" -- Aplicacion parada ----");
    }
    
}
