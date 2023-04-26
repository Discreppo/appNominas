package modelo;

/**
 *
 * @author Oscar Pascual Ferrero
 */
public class EmpleadoFijo extends Empleado {
    private float salario;

    public EmpleadoFijo(String dni, String nombre,float salario) throws DniException {
        super(dni, nombre);
        this.salario = salario;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return super.toString()+","+salario;
    }
    
     
    @Override
    public float ingresos() {
        return salario;
    }
    
}
