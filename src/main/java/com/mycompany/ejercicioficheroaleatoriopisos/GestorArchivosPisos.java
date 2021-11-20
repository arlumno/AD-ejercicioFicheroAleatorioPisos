/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicioficheroaleatoriopisos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import static java.lang.Math.ceil;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author a20armandocb
 */
public class GestorArchivosPisos {

    private final String RUTA_FICHEROS = "src/main/java/com/mycompany/ejercicioficheroaleatoriopisos/ficheros/";
    private final String NOMBRE_FICHERO = "save.dat";
    private final String NOMBRE_FICHERO_TMP = "tmp_save.dat";
    private final String DIA_RECIBOS = "20";
    private long maxSize;

    public GestorArchivosPisos(long maxSize) {
        this.maxSize = maxSize;
    }

    public void addPiso(Piso piso) {
        File fichero = new File(RUTA_FICHEROS + NOMBRE_FICHERO);
        RandomAccessFile raf = null;
        try {
            comprobarFichero(fichero);            
            raf = new RandomAccessFile(fichero, "rw");
            
            int totalRegistros = 0;            
            if(raf.length() != 0){
                totalRegistros = calcularRegistro(raf.length());                 
            }
            
            long indiceNuevoRegistro = calcularIndice(totalRegistros + 1);
            grabarPiso(raf, indiceNuevoRegistro, piso);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el fichero\n" + e.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en addPiso(): " + e.toString());
        } finally {
            try {
                raf.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar el flujo " + e.toString());
            }
        }
    }
    private void comprobarFichero(File fichero) throws Exception{
        if (fichero.createNewFile()) {
                JOptionPane.showMessageDialog(null, "--- El fichero " + NOMBRE_FICHERO + " no existe. Se ha creado uno nuevo ---");
            }        
    }
    public void modPiso(Piso piso, long indice) {
        File fichero = new File(RUTA_FICHEROS + NOMBRE_FICHERO);
        RandomAccessFile raf = null;
        try {
            comprobarFichero(fichero);
            if (fichero.createNewFile()) {
                JOptionPane.showMessageDialog(null, "--- El fichero " + NOMBRE_FICHERO + " no existe. Se ha creado uno nuevo ---");
            }
            raf = new RandomAccessFile(fichero, "rw");
            grabarPiso(raf, indice, piso);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el fichero\n" + e.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en modPiso(): " + e.toString());
        } finally {
            try {
                raf.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar el flujo " + e.toString());
            }
        }
    }

    public void modPiso(Piso piso, int registro) {
        modPiso(piso, calcularIndice(registro));
    }

    public void delPiso(long indiceEliminar) {
        File ficheroLectura = new File(RUTA_FICHEROS + NOMBRE_FICHERO);
        File ficheroEscritura = new File(RUTA_FICHEROS + NOMBRE_FICHERO_TMP);
        RandomAccessFile rafR = null;
        RandomAccessFile rafW = null;
        int indiceW = 0;
        if (ficheroLectura.exists()) {
            try {
                rafR = new RandomAccessFile(ficheroLectura, "r");
                long indiceFinal = rafR.length();
                if (indiceEliminar < indiceFinal) {
                    rafW = new RandomAccessFile(ficheroEscritura, "rw");
                    for (long i = 0; i < indiceFinal; i += maxSize) {
                        if (indiceEliminar != i) {
                            grabarPiso(rafW, indiceW, recuperarPiso(rafR, i));
                            indiceW += maxSize;
                        }
                    }
                    rafR.close();
                    rafW.close();
                    ficheroLectura.delete();
                    ficheroEscritura.renameTo(ficheroLectura);
                } else {
                    JOptionPane.showMessageDialog(null, "Error el registro a eliminar no existe");
                }

            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Error al leer el fichero\n" + e.toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en modPiso(): " + e.toString());
            } finally {
                try {
                    rafR.close();
                    rafW.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar el flujo " + e.toString());
                }
            }
        }
    }

    public void delPiso(int registro) {
        delPiso(calcularIndice(registro));
    }

    /**
     * Graba los elementos a partir del indice dado
     *
     * @param raf
     * @param indice
     * @param piso
     * @return devuelve el indice del siguiente objeto en función del valor
     * máximo
     * @throws Exception
     */
    public void grabarPiso(RandomAccessFile raf, long indice, Piso piso) throws Exception {
        raf.seek(indice);
        raf.writeChar(piso.getTipoPiso());
        raf.writeUTF(piso.getReferencia());
        raf.writeUTF(piso.getNombrePropietario());
        raf.writeFloat(piso.getCuotaFija());
        raf.writeFloat(piso.getAguaCaliente());
        raf.writeFloat(piso.getcCalefaccion());
        if (piso instanceof Atico) {
            raf.writeFloat(((Atico) piso).getMetrosTerraza());
        } else if (piso instanceof Duplex) {
            raf.writeFloat(((Duplex) piso).getCuotaExtra());
        }
    }

    public Piso recuperarPiso(RandomAccessFile raf, long indice) throws Exception {
        Piso piso = null;
        raf.seek(indice);
        char tipo = raf.readChar();
        if (tipo == Atico.TIPO_PISO) {
            piso = new Atico(raf.readUTF(), raf.readUTF(), raf.readFloat(), raf.readFloat(), raf.readFloat(), raf.readFloat());
        } else if (tipo == Duplex.TIPO_PISO) {
            piso = new Duplex(raf.readUTF(), raf.readUTF(), raf.readFloat(), raf.readFloat(), raf.readFloat(), raf.readFloat());
        }
        return piso;
    }

    private Piso recuperarPiso(RandomAccessFile raf, int registro) throws Exception {
        return recuperarPiso(raf, calcularIndice(registro));
    }

    public Piso getPiso(long indice) {
        File fichero = new File(RUTA_FICHEROS + NOMBRE_FICHERO);
        RandomAccessFile raf = null;
        Piso piso = null;
        try {
            comprobarFichero(fichero);
            raf = new RandomAccessFile(fichero, "r");
            if (indice < raf.length()) {
                piso = recuperarPiso(raf, indice);
            } else {                
                JOptionPane.showMessageDialog(null, "Error, el registro no existe");
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el fichero\n" + e.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en getPiso(): " + e.toString());
        } finally {
            try {
                raf.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar el flujo " + e.toString());
            }
        }
        return piso;
    }

    public Piso getPiso(int registro) {
        return getPiso(calcularIndice(registro));
    }

    private String listarPisos(boolean recibo, String filtroPropietario) {
        StringBuilder listado = new StringBuilder();
        File fichero = new File(RUTA_FICHEROS + NOMBRE_FICHERO);
        RandomAccessFile raf = null;
        Piso piso = null;
        try {
            comprobarFichero(fichero);
            raf = new RandomAccessFile(fichero, "r");
            if (raf.length() != 0) {
                for (long i = 0 ;i < raf.length(); i += maxSize) {
                    piso = recuperarPiso(raf, i);
                    if (filtroPropietario == "" || filtroPropietario.toUpperCase().equals(piso.getNombrePropietario().toUpperCase())) {
                        listado.append("[" + calcularRegistro(i) + "] ");
                        if (piso.getTipoPiso() == Atico.TIPO_PISO) {
                            listado.append(((Atico) piso).toString() + "\n");
                        } else if (piso.getTipoPiso() == Duplex.TIPO_PISO) {

                            listado.append(((Duplex) piso).toString() + "\n");
                        }
                        if (recibo) {
                            listado.append("--- Recibo del Mes :" + piso.totalRecibo() + "€\n");
                        }
                    }
                }
            } else {                
                listado.append("No hay registros");
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el fichero\n" + e.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en listarPisos(): " + e.toString());
        } finally {
            try {
                raf.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar el flujo " + e.toString());
            }
        }
        return listado.toString();
    }

    public String listarPisos() {
        return listarPisos(false, "");
    }

    public String listarRecibos() {
        String resultado = "Error fecha. Los recibos estarán disponibles el día " + DIA_RECIBOS;
        String diaActual = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
        if (diaActual.equals(DIA_RECIBOS)) {
            resultado = listarPisos(true, "");
        }
        return resultado;
    }

    public int calcularRegistro(Long indice) {
        return ((int) ceil(((double) indice + 1) / (double) maxSize));
    }
    public long calcularIndice(int registro) {
        return ((long) registro - 1) * ((long) maxSize);
    }

    public String buscarPisos(String propietario) {
        return listarPisos(false, propietario);
    }
}
