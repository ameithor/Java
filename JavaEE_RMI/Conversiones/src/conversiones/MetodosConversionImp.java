/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversiones;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Esta clase es la Implementacion de la interfaz MEtodosConversion. Es
 * importante porque aqui vamos a definir las constantes de conversion de cada
 * divisa y todos los metodos abstractos de la interfaz.
 *
 * @author amatore
 */
public class MetodosConversionImp extends UnicastRemoteObject implements conversiones.MetodosConversion {

    /**
     * Constantes de cada divisa
     * 
     */
//PESETAS a...
    final static double PESaDOL = 0.00711;
    final static double PESaEUR = 0.00601;
    final static double PESaLIB = 0.00469;
//EUROS a...
    final static double EURaDOL = 0.18377;
    final static double EURaLIB = 0.78073;
    final static double EURaPES = 166.386;
//DOLARES a...
    final static double DOLaPES = 140.474;
    final static double DOLaLIB = 0.65940;
    final static double DOLaEUR = 0.84427;
//LIBRAS a...
    final static double LIBaDOL = 1.27926;
    final static double LIBaEUR = 1.51504;
    final static double LIBaPES = 212.824;

    public MetodosConversionImp() throws RemoteException {

    }

    @Override
    public double PESaDOL(double importe) throws RemoteException {

        return importe * PESaDOL;
    }

    @Override
    public double PESaEUR(double importe) throws RemoteException {

        return importe * PESaEUR;
    }

    @Override
    public double PESaLIB(double importe) throws RemoteException {

        return importe * PESaLIB;
    }

    @Override
    public double EURaDOL(double importe) throws RemoteException {

        return importe * EURaDOL;

    }

    @Override
    public double EURaPES(double importe) throws RemoteException {
        return importe * EURaPES;

    }

    @Override
    public double EURaLIB(double importe) throws RemoteException {

        return importe * EURaLIB;
    }

    @Override
    public double DOLaEUR(double importe) throws RemoteException {

        return importe * DOLaEUR;

    }

    @Override
    public double DOLaPES(double importe) throws RemoteException {

        return importe * DOLaPES;
    }

    @Override
    public double DOLaLIB(double importe) throws RemoteException {

        return importe * DOLaLIB;
    }

    @Override
    public double LIBaEUR(double importe) throws RemoteException {

        return importe * LIBaEUR;
    }

    @Override
    public double LIBaPES(double importe) throws RemoteException {
        return importe * LIBaPES;
    }

    @Override
    public double LIBaDOL(double importe) throws RemoteException {

        return importe * LIBaDOL;
    }

}
