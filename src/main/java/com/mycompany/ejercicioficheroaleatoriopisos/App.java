/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicioficheroaleatoriopisos;

import ar.csdam.pr.libreriaar.Menu;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Arsito
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean continuar = true;
        Scanner lector = new Scanner(System.in);
        GestorPisos gestor = new GestorPisos();
        Menu menu = construirMenuPrincipal(lector);
        do {
            try {
                continuar = menuAcciones(menu, gestor);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en en main(): " + e.getStackTrace().toString());
            }
        } while (continuar);

        lector.close();
    }

    private static Menu construirMenuPrincipal(Scanner lector) {
        Menu menu = new Menu(lector);

        menu.setTituloMenu("Menú para el Gestor de Pisos");
        
        menu.addOpcion("Alta Piso");//1
        menu.addOpcion("Baja Piso");//2
        menu.addOpcion("Modificar Piso");//3
        menu.addOpcion("Listar Pisos");//4
        menu.addOpcion("Listar Recibos");//5
        menu.addOpcion("Listar Pisos de Propietario");//6        
        
        return menu;
    }

    private static boolean menuAcciones(Menu menu, GestorPisos gestor) throws Exception {
        boolean continuar = true;
        menu.mostrarGUI();       
        switch (menu.getSeleccion()) {
            case 0:
                //salir
                continuar = false;
                System.out.println("Bye Bye!");
                break;
            case 1:
                gestor.altaPiso();
                break;
            case 2:                
                gestor.bajaPiso();
                break;
            case 3:                
                gestor.modPiso();
                break;
           
            case 4:                
                gestor.listarPisos();
                break;
           
            case 5:
                gestor.listarRecibos();
                break;
            case 6:
                gestor.listarPisosPropietario();
                break;
       
            default:
                System.out.println("**Opcion incorrecta**");
                break;
        }
        return continuar;
    }
}
