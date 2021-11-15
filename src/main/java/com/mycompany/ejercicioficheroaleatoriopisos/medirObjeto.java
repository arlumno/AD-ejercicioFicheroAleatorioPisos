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
class medirObjeto {
    public float size = 0;

    public void addProperty(int property){ size += Integer.SIZE; }
    public void addProperty(float property){ size += Float.SIZE; }
    // public void addProperty(char property){ size += Char.SIZE; }
   // public void addProperty(String property){ size += property.length() * ; }
    
    public float getSize(){
            return size;
    }
    
}
