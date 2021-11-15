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
        System.out.println("--- inicio ---");
    }
    
    
}
