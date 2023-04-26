/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Empleado;
import modelo.EmpleadoEventual;
import modelo.EmpleadoFijo;
import modelo.SistemaNominas;
import vista.Ventana;

/**
 *
 * @author daw1
 */
public class Controlador {
    private Ventana vista;
    private SistemaNominas modelo;

    public Controlador(Ventana vista, SistemaNominas modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
    
    public void crearEmpleado(){
    
    }
    
    public void buscarEmpleado(){
        String dni=vista.getDni();
        try{
            Empleado e=modelo.getEmpleado(dni);
            if(e!=null){
                vista.mostrarNombre(e.getNombre());
                vista.mostrarTipo((e instanceof EmpleadoFijo)?(EmpleadoFijo));
                    if(e instanceof EmpleadoEventual empleadoEventual){
                        vista.mostrarHoras(empleadoEventual)
                    }
                vista.mostrarIngresos(e.ingresos);
            }
        }catch(IllegalArgumentException iae){
        }
    }
    
    public void eliminarEmpleado(){
    
    }
    
    public void modificarEmpleado(){
    
    }
    
    public void listarEmpleados(){
    
    }
    
    public void guardarEmpleados(){
    
    }
    
    public void cargarEmpleados(){
    
    }
    
    public void iniciar(){
        vista.mostrar();
    }
    
    
}
