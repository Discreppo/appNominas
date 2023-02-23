
package es.sauces.appalquiler;

/**
 *
 * @author Oscar Pascual Ferrero
 */
public class Turismo extends Vehiculo{
    private int plazas;

    public Turismo(String matricula, Grupo grupo,int plazas) {
        super(matricula, grupo);
        this.plazas=plazas;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    @Override
    public String toString() {
        return super.toString()+", " + plazas;
    }
    

    @Override
    public float getPrecioAlquiler() {
        return this.getGrupo().getPrecioBase()+getGrupo().getVariableTurismo()*plazas;
    }
    
}
