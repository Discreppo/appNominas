/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.sauces.appalquiler;

import java.util.Objects;

/**
 *
 * @author Oscar Pascual Ferrero
 */
public abstract class Vehiculo implements Comparable<Vehiculo> {
    private String matricula;
    private Grupo grupo;

    public Vehiculo(String matricula, Grupo grupo) {
        this.matricula = matricula;
        this.grupo = grupo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    
    @Override
    public int hashCode() {
        int hash = 5;
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
        final Vehiculo other = (Vehiculo) obj;
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        return this.grupo == other.grupo;
    }

    @Override
    public String toString() {
        return  matricula + ", " + grupo;
    }
    
    
    
    public float getPrecioAlquiler(int dias){
        return dias*getPrecioAlquiler();
    }
    
    public abstract float getPrecioAlquiler();

    @Override
    public int compareTo(Vehiculo o) {
        return this.matricula.compareTo(o.matricula);
    }
}
