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
    private char tipoPiso;
    private String nombrePropietario;
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
    
    public void setTotalRecibo(float totalRecibo) {
        this.totalRecibo = totalRecibo;
    }
    
    public abstract float totalRecibo();
    
}
