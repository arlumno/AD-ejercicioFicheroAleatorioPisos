/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicioficheroaleatoriopisos;

import java.util.ArrayList;

/**
 *
 * @author Arsito
 */
public class GestorPisos {
    private ArrayList<Piso> pisos = new ArrayList<Piso>();

    public GestorPisos() {
        Atico pisoEjemplo01 = new Atico("ejemplo01", "Ana", 10, 20, 30, 40);
        Duplex pisoEjemplo02 = new Duplex("ejemplo02", "Maria", 10, 20, 30, 40);
        
        MedirObjeto medirObjeto = new MedirObjeto();
        
        /**
         *  private String referencia;
            private char tipoPiso;
            private String nombrePropietario;
            private float cuotaFija;
            private float aguaCaliente;
            private float cCalefaccion;
            private float totalRecibo;

         */
        
        medirObjeto.addProperty(pisoEjemplo01.getTipoPiso());
        medirObjeto.addProperty(pisoEjemplo01.getReferencia(),Piso.MAX_SIZE_REFERENCIA);
        medirObjeto.addProperty(pisoEjemplo01.getNombrePropietario(),Piso.MAX_SIZE_PROPIETARIO);
        medirObjeto.addProperty(pisoEjemplo01.getCuotaFija());
        medirObjeto.addProperty(pisoEjemplo01.getAguaCaliente());
        medirObjeto.addProperty(pisoEjemplo01.getcCalefaccion());
        medirObjeto.addProperty(pisoEjemplo01.getTotalRecibo());  
               
        System.out.println("--- inicio ---");
        System.out.println("--- size ---");
        System.out.println(medirObjeto.getSize());
        System.out.println("--- maxSize ---");
        System.out.println(medirObjeto.getMaxSize());
        System.out.println("--- guardar.. ---");
        
        GestorArchivosPisos gestorArchivos = new GestorArchivosPisos(medirObjeto.getMaxSize());
        gestorArchivos.addPiso(pisoEjemplo01);
        int numero = 1; 
        Piso p = gestorArchivos.leerPiso(5);
        System.out.println(p.toString());
    }
    
    
}
