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
class MedirObjeto {
    private long maxSize = 0;
    private long size = 0;
    
    public void addProperty(byte property){ 
        maxSize += 1;        
        size += 1;
    }
    
    public void addProperty(short property){ 
        maxSize += 2;
        size += 2;
    }
    
    public void addProperty(int property){ 
        maxSize += 4;
        size += 4;
    }
    public void addProperty(long property){ 
        maxSize += 8;
        size += 8;
    }
    public void addProperty(float property){         
        maxSize += 4; 
        size += 4;
    }
    
    public void addProperty(double property){ 
        maxSize += 8;
        size += 8;
    }
    
    public void addProperty(boolean property){ 
        maxSize += 1;
        size += 1;
    }
     public void addProperty(char property){ 
         maxSize += 2; 
         size += 2;         
     }
     
    public void addProperty(String property, int numeroMaximoLetras){ 
        maxSize += numeroMaximoLetras * 2; 
        size += property.length() *  2; 
    }
    
    public long getSize(){
            return size;
    }
    public long getMaxSize(){
            return maxSize;
    }
    
}
