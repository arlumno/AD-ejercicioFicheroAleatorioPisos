/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicioficheroaleatoriopisos;

import ar.csdam.pr.libreriaar.Entradas;
import ar.csdam.pr.libreriaar.EntradasGui;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author Arsito
 */
public class GestorPisos {

    private ArrayList<Piso> pisos = new ArrayList<Piso>();
    private GestorArchivosPisos gestorArchivos;

    public GestorPisos() {
        MedirObjeto medirObjeto = new MedirObjeto();

        medirObjeto.addChar();
        medirObjeto.addString(Piso.MAX_SIZE_REFERENCIA);
        medirObjeto.addString(Piso.MAX_SIZE_PROPIETARIO);
        medirObjeto.addFloat();
        medirObjeto.addFloat();
        medirObjeto.addFloat();
        medirObjeto.addFloat();

        gestorArchivos = new GestorArchivosPisos(medirObjeto.getMaxSize());
    }

    public void altaPiso() {
        String referencia = EntradasGui.pedirString("Indica la referencia (max " + Piso.MAX_SIZE_REFERENCIA + " letras)", Piso.MAX_SIZE_REFERENCIA);
        String opcionPiso = EntradasGui.pedirOpcion("Indica el tipo de piso", new ArrayList<>(Arrays.asList("Atico", "Duplex")));
        String nombrePropietario = EntradasGui.pedirString("Nombre del propietario: ", Piso.MAX_SIZE_PROPIETARIO);
        float cuotaFija = EntradasGui.pedirFloat("Cuota Fija:");
        float aguaCaliente = EntradasGui.pedirFloat("Agua Caliente:");
        float cCalefaccion = EntradasGui.pedirFloat("Calefacción :");
        Piso piso = null;
        switch (opcionPiso) {
            case "Atico":
                float metrosTerraza = EntradasGui.pedirFloat("Metros Terraza: ");
                piso = new Atico(referencia, nombrePropietario, cuotaFija, aguaCaliente, cCalefaccion, metrosTerraza);
                break;
            case "Duplex":
                float cuotaExtra = EntradasGui.pedirFloat("Cuota Extra: ");
                piso = new Duplex(referencia, nombrePropietario, cuotaFija, aguaCaliente, cCalefaccion, cuotaExtra);
                break;
        }
        if (piso != null) {
            gestorArchivos.addPiso(piso);
            JOptionPane.showMessageDialog(null, "Piso añadido con éxito");
        } else {
            JOptionPane.showMessageDialog(null, "¡¡Error al añadir el piso!!");
        }
    }

    public void bajaPiso() {
        int registro = EntradasGui.pedirInt("Indica el numero de registro. Se pedirá confirmación",1);
        Piso piso = gestorArchivos.getPiso(registro);
        if (piso != null && EntradasGui.pedirBoolean("Seguro que quieres eliminar el registro " + registro + "\n" + piso.toString())) {
            gestorArchivos.delPiso(registro);
            JOptionPane.showMessageDialog(null," --- Piso eliminado con éxito, se han actualizado los registros ---");
        }
    }

    public void modPiso() {
        int registro = EntradasGui.pedirInt("Indica el numero de registro a modificar",1);
        Piso piso = gestorArchivos.getPiso(registro);
        boolean modificado = false;
        if (piso != null && EntradasGui.pedirBoolean("Seguro que quieres modificar el registro " + registro + "\n" + piso.toString())) {            
            if(EntradasGui.pedirBoolean("¿Modificar la referencia?")){
                piso.setReferencia(EntradasGui.pedirString("Indica la referencia (max " + Piso.MAX_SIZE_REFERENCIA + " letras)", Piso.MAX_SIZE_REFERENCIA));        
                modificado = true;
            }            
            if(EntradasGui.pedirBoolean("¿Modificar el nombre del propietario?")){
                piso.setNombrePropietario(EntradasGui.pedirString("Nombre del propietario: ", Piso.MAX_SIZE_PROPIETARIO));                        
                modificado = true;
            }
            
            if(EntradasGui.pedirBoolean("¿Modificar el Agua caliente?")){
                piso.setAguaCaliente(EntradasGui.pedirFloat("Agua caliente: "));                        
                modificado = true;
            }

            if(EntradasGui.pedirBoolean("¿Modificar cuota fija?")){
                piso.setCuotaFija(EntradasGui.pedirFloat("Cuota fija:"));                        
                modificado = true;
            }            
            
            if(EntradasGui.pedirBoolean("¿Modificar calefacción?")){
                piso.setcCalefaccion(EntradasGui.pedirFloat("Calefacción:"));                        
                modificado = true;
            }            
            
            if(modificado){
                JOptionPane.showMessageDialog(null," --- Piso modificado con éxito ---");                
            }else{
                JOptionPane.showMessageDialog(null," --- No se han realizado modificaciones ---");                
            }
        }
    }

    public void listarPisos() {
        JOptionPane.showMessageDialog(null, gestorArchivos.listarPisos());
    }

    public void listarRecibos() {
        JOptionPane.showMessageDialog(null, gestorArchivos.listarRecibos());
    }

    public void listarPisosPropietario() {
        String propietario = EntradasGui.pedirString("Nombre de propietario a buscar:");
        JOptionPane.showMessageDialog(null, "Pisos de  " + propietario + ":\n" + gestorArchivos.buscarPisos(propietario));
    }

}
