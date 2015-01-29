/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversiones;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author amatore
 */
public class ConversionServidor {

    /**
     * @param args the command line arguments
     */
    public static final int numPuerto= 1099;
    public static void main(String[] args) {
        // TODO code application logic here
        
        try{
            		LocateRegistry.createRegistry(numPuerto);
			
			MetodosConversionImp conv = new MetodosConversionImp();
			
			Naming.rebind("MiConversor", conv);
                        
        }catch(Exception e){
            
            	System.out.println("Error en el servidor");
		
                e.printStackTrace();
                
        }
    }
    
}
