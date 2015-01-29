/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversiones;

import java.rmi.Naming;
import java.util.Scanner;

/**
 * Esta es la clase cliente desde la cual nos conectaremos al Servidor
 * y realizaremos al conversion de moneda a partir de seleccionar la 
 * divisa origen y destino
 *
 * @author amatore
 */
public class ConversionCliente {

    /**Parámetros de Conexión**/
    static final String host="127.0.0.1";
    static final String port="1099";
    static final String name="MiConversor";


    public static void main(String[] args) {

        /**Variables**/
        Scanner sc = new Scanner(System.in);
        double d;
        int origen, destino = 0;

        try {
            MetodosConversion m = (MetodosConversion) Naming.lookup("rmi://"+host+":"+port+"/"+name);

            System.out.println("Introduzca Importe a convertir:\n");
            d = sc.nextDouble();
            System.out.println("Seleccione Divisa origen:\n");
            System.out.println("(1-Peseta, 2-EURO, 3-Dolar, 4-Libra)\n");
            origen = sc.nextInt();

            System.out.println("Seleccione Divisa destino:\n");

            switch (origen) {
                case 1:
                    /**
                     * PESETA*
                     */
                    System.out.println("(2-EURO, 3-Dolar, 4-Libra)\n");
                    destino = sc.nextInt();

                    if (destino == 2) {
                        d = m.PESaEUR(d);
                    } else if (destino == 3) {
                        d = m.PESaDOL(d);
                    } else if (destino == 4) {
                        d = m.PESaLIB(d);
                    }
                    break;

                case 2:
                    /**
                     * EURO*
                     */
                    System.out.println("(1-Peseta, 3-Dolar, 4-Libra)\n");
                    destino = sc.nextInt();

                    if (destino == 1) {
                        d = m.EURaPES(d);
                    } else if (destino == 3) {
                        d = m.EURaDOL(d);
                    } else if (destino == 4) {
                        d = m.EURaLIB(d);
                    }

                    break;

                case 3:
                    /**
                     * DOLAR*
                     */
                    System.out.println("(1-Peseta, 2-EURO, 4-Libra)\n");
                    destino = sc.nextInt();

                    if (destino == 1) {
                        d = m.DOLaPES(d);
                    } else if (destino == 2) {
                        d = m.DOLaEUR(d);
                    } else if (destino == 4) {
                        d = m.DOLaLIB(d);
                    }
                    break;

                case 4:
                    /**
                     * LIBRA*
                     */
                    System.out.println("(1-Peseta, 2-EURO, 3-Dolar)\n");
                    destino = sc.nextInt();

                    if (destino == 1) {
                        d = m.LIBaPES(d);
                    } else if (destino == 2) {
                        d = m.LIBaEUR(d);
                    } else if (destino == 3) {
                        d = m.LIBaDOL(d);
                    }
                    break;
            };

            System.out.println("\nResultado: " + d);

        } catch (Exception ex) {
            System.out.println("Error en el cliente");
            ex.printStackTrace();
        }
    }

}
