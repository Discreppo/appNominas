package modelo;

import java.util.Comparator;

/**
 *
 * @author Oscar Pascual Ferrero
 */
public class ComparadorSueldo implements Comparator<Empleado>{

    @Override
    public int compare(Empleado o1, Empleado o2) {
        float ingresos1=o1.ingresos(), ingresos2=o2.ingresos();
        if(ingresos1<ingresos2){
            return -1;
        }
        else{
            if(ingresos1>ingresos2){
                return 1;
            }                       
        }
        return 0;
    // comparar los ingresos de ambos, devuelve num positivo si el primero es mayor, negativo si es el segundo y 0
    }
    
}
