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

    public float getMetrosTerraza() {
        return metrosTerraza;
    }

    public Atico(String referencia, String nombrePropietario, float cuotaFija, float aguaCaliente, float cCalefaccion, float metrosTerraza) {
        super(referencia, 'A', nombrePropietario, cuotaFija, aguaCaliente, cCalefaccion);
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
    
}
