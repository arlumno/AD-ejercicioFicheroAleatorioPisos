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
import javax.swing.JOptionPane;

/**
 *
 * @author a20armandocb
 */
public class GestorArchivosPisos {

    private static final String RUTA_FICHEROS = "src/main/java/com/mycompany/ejercicioficheroaleatoriopisos/ficheros/";
    private static final String NOMBRE_FICHERO = "save.dat";
    private static final String NOMBRE_FICHERO_TMP = "tmp_save.dat";
    private long maxSize;

    public GestorArchivosPisos(long maxSize) {
        this.maxSize = maxSize;
    }

    public void addPiso(Piso piso) {
        File fichero = new File(RUTA_FICHEROS + NOMBRE_FICHERO);
        RandomAccessFile raf = null;
        try {
            if (fichero.createNewFile()) {
                System.out.println("--- El fichero " + NOMBRE_FICHERO + " no existe. Se ha creado uno nuevo ---");
            }
            raf = new RandomAccessFile(fichero, "rw");

            int registros = (int) ceil((double) raf.length() / (double) maxSize);
            long indice = registros * maxSize;
            grabarPiso(raf, indice, piso);
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

    public void modPiso(Piso piso, long indice) {
        File fichero = new File(RUTA_FICHEROS + NOMBRE_FICHERO);
        RandomAccessFile raf = null;
        try {
            if (fichero.createNewFile()) {
                System.out.println("--- El fichero " + NOMBRE_FICHERO + " no existe. Se ha creado uno nuevo ---");
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
        modPiso(piso, (registro - 1) * maxSize);
    }

    public void delPiso(Piso piso, long indiceEliminar) {
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

    public void delPiso(Piso piso, int registro) {
        delPiso(piso, (registro - 1) * maxSize);
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
    public long grabarPiso(RandomAccessFile raf, long indice, Piso piso) throws Exception {
        raf.seek(indice);
        raf.writeChar(piso.getTipoPiso());
        raf.writeUTF(piso.getReferencia());
        raf.writeUTF(piso.getNombrePropietario());
        raf.writeFloat(piso.getCuotaFija());
        raf.writeFloat(piso.getAguaCaliente());
        raf.writeFloat(piso.getcCalefaccion());
        raf.writeFloat(piso.getTotalRecibo());
        if (piso instanceof Atico) {
            raf.writeFloat(((Atico) piso).getMetrosTerraza());
        } else if (piso instanceof Duplex) {
            raf.writeFloat(((Duplex) piso).getCuotaExtra());
        }
        return indice + maxSize;
    }

    public Piso recuperarPiso(RandomAccessFile raf, long indice) throws Exception {
        Piso piso = null;
        raf.seek(indice);
        char tipo = raf.readChar();
        if (tipo == 'A') {
            piso = new Atico(raf.readUTF(), raf.readUTF(), raf.readFloat(), raf.readFloat(), raf.readFloat(), raf.readFloat());
        } else if (tipo == 'D') {
            piso = new Duplex(raf.readUTF(), raf.readUTF(), raf.readFloat(), raf.readFloat(), raf.readFloat(), raf.readFloat());
        }
        return piso;
    }

    private Piso recuperarPiso(RandomAccessFile raf, int registro) throws Exception {
        return recuperarPiso(raf, ((registro - 1) * maxSize));
    }

    public Piso leerPiso(long indice) {
        File fichero = new File(RUTA_FICHEROS + NOMBRE_FICHERO);
        RandomAccessFile raf = null;
        Piso piso = null;
        try {
            raf = new RandomAccessFile(fichero, "r");
            System.out.println(indice);
            System.out.println(raf.length());
            if (indice < raf.length()) {
                piso = recuperarPiso(raf, indice);
            } else {
                JOptionPane.showMessageDialog(null, "Error, el registro no existe");
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el fichero\n" + e.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en leerPiso(): " + e.toString());
        } finally {
            try {
                raf.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar el flujo " + e.toString());
            }
        }
        return piso;
    }

    public Piso leerPiso(int registro) {
        return leerPiso(((registro - 1) * maxSize));
    }
}
