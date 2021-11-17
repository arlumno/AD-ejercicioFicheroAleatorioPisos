/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicioficheroaleatoriopisos;

/**
 *
 * @author Arsito
 */
public class Atico extends Piso{
    private float metrosTerraza;
    public static char TIPO_PISO = 'A';

    public float getMetrosTerraza() {
        return metrosTerraza;
    }

    public Atico(String referencia, String nombrePropietario, float cuotaFija, float aguaCaliente, float cCalefaccion, float metrosTerraza) {
        super(referencia, TIPO_PISO, nombrePropietario, cuotaFija, aguaCaliente, cCalefaccion);
        this.metrosTerraza = metrosTerraza;
    }
    
    @Override
    public float totalRecibo() {
        setTotalRecibo(getCuotaFija() 
                + getAguaCaliente() * 0.4F 
                + getcCalefaccion() * 0.7F 
                + getMetrosTerraza() * 0.45F);
        return getTotalRecibo();
    }

    @Override
    public String toString() {        
        return super.toString() + " | Terraza: " + metrosTerraza;
    }
    
}
