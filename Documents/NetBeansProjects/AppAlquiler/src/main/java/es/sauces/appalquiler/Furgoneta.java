
package es.sauces.appalquiler;

/**
 *
 * @author Oscar Pascual Ferrero
 */
public class Furgoneta extends Vehiculo {
    private float capacidad;

    public Furgoneta(String matricula, Grupo grupo,float capacidad) {
        super(matricula, grupo);
        this.capacidad=capacidad;
    }

    public float getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return super.toString()+", " + capacidad ;
    }

    @Override
    public float getPrecioAlquiler(){
    return this.getGrupo().getPrecioBase()+getGrupo().getVariableFurgoneta()*capacidad;
    }
    
    
    
}
