
package es.sauces.appalquiler;

import java.util.Comparator;

/**
 *
 * @author Oscar Pascual Ferrero
 */
public class ComparadorPrecio implements Comparator<Vehiculo> {

    @Override
    public int compare(Vehiculo o1, Vehiculo o2) {
        float precio1=o1.getPrecioAlquiler();
        float precio2=o2.getPrecioAlquiler();
        int salida=0;
        if(precio1<precio2){
            salida=-1;
        }
        else{
            if(precio1>precio2){
                salida=1;
            }
        }
        return salida;
    }
    
}
