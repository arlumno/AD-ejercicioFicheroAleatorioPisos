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
public class Duplex extends Piso{
    private float cuotaExtra;
    public static char TIPO_PISO = 'D';

    public Duplex(String referencia, String nombrePropietario, float cuotaFija, float aguaCaliente, float cCalefaccion, float cuotaExtra) {
        super(referencia, TIPO_PISO, nombrePropietario, cuotaFija, aguaCaliente, cCalefaccion);
        this.cuotaExtra = cuotaExtra;
    }

    public float getCuotaExtra() {
        return cuotaExtra;
    }
        
    @Override
    public float totalRecibo() {
        setTotalRecibo(getCuotaFija() 
                + getAguaCaliente() * 0.4F 
                + getcCalefaccion() * 0.7F 
                + getCuotaExtra());
        return getTotalRecibo();
    }

    @Override
    public String toString() {
        return super.toString() + " | Cuota Extra: " + cuotaExtra;
    }
    
}
