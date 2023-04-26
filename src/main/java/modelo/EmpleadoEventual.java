package modelo;

/**
 *
 * @author Oscar Pascual Ferrero
 */
public class EmpleadoEventual extends Empleado {
    private float salarioHoras;
    private int horas;

    public EmpleadoEventual(String dni,String nombre,float salarioHoras, int horas ) throws DniException {
        super(dni, nombre);
        this.salarioHoras = salarioHoras;
        this.horas = horas;
    }

    public float getSalarioHoras() {
        return salarioHoras;
    }

    public void setSalarioHoras(float salarioHoras) {
        this.salarioHoras = salarioHoras;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }    
    
    @Override
    public String toString() {
        return super.toString()+","+salarioHoras+","+horas;
    }
        
    
    @Override
    public float ingresos() {
        return salarioHoras*horas;
    }
    
}
