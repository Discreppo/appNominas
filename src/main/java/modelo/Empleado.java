package modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Oscar Pascual Ferrero
 */
public abstract class Empleado implements Serializable,Comparable<Empleado>{
    private Dni dni;
    private String nombre;
    

    public Empleado(String dni, String nombre) throws DniException {
        this.dni = new Dni(dni);
        this.nombre = nombre;
    }

    public Dni getDni() {
        return dni;
    }

    public void setDni(Dni dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.dni);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass().getGenericSuperclass() != obj.getClass().getGenericSuperclass()) {
            return false;
        }
        final Empleado other = (Empleado) obj;
        return Objects.equals(this.dni, other.dni);
    }

    @Override
    public String toString() {
        return  dni + "," + nombre;
    }
    
    @Override
    public int compareTo(Empleado o){
        
       this.dni.equals(o.dni);
        return this.dni.compareTo(o.dni);
    }
    
    public abstract float ingresos();
}
