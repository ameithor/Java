/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversiones;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author amatore
 * 
 * 1 PES
 * 0,00711 DOL
 * 0,00601 EUR
 * 0,00469 LIB
 * 
 * 1 EURO
 * 1,18377 DOL
 * 0,78073 LIB
 * 166,386 PES
 * 
 * 1 DOL
 * 140,474 PES
 * 0,65940 LIB
 * 0,84427 EUR
 * 
 * 1 LIB
 * 1,27926 EUR
 * 1,51504 DOL
 * 212,824 PES
 */


public interface MetodosConversion extends Remote {
    

    public double PESaDOL(double importe) throws RemoteException;
    public double PESaEUR(double importe) throws RemoteException;
    public double PESaLIB(double importe) throws RemoteException;
  
    public double EURaDOL(double importe) throws RemoteException;
    public double EURaPES(double importe) throws RemoteException;
    public double EURaLIB(double importe) throws RemoteException;
    
    public double DOLaEUR(double importe) throws RemoteException;
    public double DOLaPES(double importe) throws RemoteException;
    public double DOLaLIB(double importe) throws RemoteException;
    
    public double LIBaEUR(double importe) throws RemoteException;
    public double LIBaPES(double importe) throws RemoteException;
    public double LIBaDOL(double importe) throws RemoteException;   
    
}
