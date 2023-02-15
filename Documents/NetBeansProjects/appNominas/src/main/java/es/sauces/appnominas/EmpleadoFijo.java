/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.sauces.appnominas;

/**
 *
 * @author daw1
 */
public class EmpleadoFijo extends Empleado {
    private float salario;

    public EmpleadoFijo(float salario, String dni, String nombre) {
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
        return super.toString()+", "+salario;
    }
    
     
    @Override
    public float ingresos() {
        return salario;
    }
    
}
