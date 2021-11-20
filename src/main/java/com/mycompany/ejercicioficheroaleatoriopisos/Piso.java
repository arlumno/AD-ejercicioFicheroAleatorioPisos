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
public abstract class Piso {
    private String referencia;
    public static int MAX_SIZE_REFERENCIA = 10;
    private char tipoPiso;
    private String nombrePropietario;
    public static int MAX_SIZE_PROPIETARIO = 10;
    private float cuotaFija;
    private float aguaCaliente;
    private float cCalefaccion;
    private float totalRecibo;


    public Piso(String referencia, char tipoPiso, String nombrePropietario, float cuotaFija, float aguaCaliente, float cCalefaccion) {
        this.referencia = referencia;
        this.tipoPiso = tipoPiso;
        this.nombrePropietario = nombrePropietario;
        this.cuotaFija = cuotaFija;
        this.aguaCaliente = aguaCaliente;
        this.cCalefaccion = cCalefaccion;
    }
    
   
    
    public String getReferencia() {
        return referencia;
    }

    public char getTipoPiso() {
        return tipoPiso;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public float getCuotaFija() {
        return cuotaFija;
    }

    public float getAguaCaliente() {
        return aguaCaliente;
    }

    public float getcCalefaccion() {
        return cCalefaccion;
    }

    public float getTotalRecibo() {
        return totalRecibo;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    public void setCuotaFija(float cuotaFija) {
        this.cuotaFija = cuotaFija;
    }

    public void setAguaCaliente(float aguaCaliente) {
        this.aguaCaliente = aguaCaliente;
    }

    public void setcCalefaccion(float cCalefaccion) {
        this.cCalefaccion = cCalefaccion;
    }
    
    public void setTotalRecibo(float totalRecibo) {
        this.totalRecibo = totalRecibo;
    }

    @Override
    public String toString() {
        String resultado = "-> Ref: " +  referencia
                         +" | Tipo: " +  tipoPiso
                         +" | Propietario: " +  nombrePropietario
                         +" | Cuota Fija: " +  cuotaFija
                         +" | Agua: " +  aguaCaliente
                         +" | Calefacción: " +  cCalefaccion;
      
        return resultado;
    }
    
    
    public abstract float totalRecibo();
    
}
