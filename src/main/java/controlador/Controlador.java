/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.logging.Logger;
import modelo.DniException;
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
                vista.mostrarTipo((e instanceof EmpleadoFijo)?"FIJO":"EVENTUAL");
                vista.mostrarSalario((e instanceof EmpleadoFijo)?((EmpleadoFijo)e).getSalario():((EmpleadoEventual)e).getSalarioHoras());
                    if(e instanceof EmpleadoEventual empleadoEventual){
                        vista.mostrarHoras(empleadoEventual.getHoras());
                    }
                vista.mostrarIngresos(e.ingresos());
            }
        }catch(IllegalArgumentException iae){
        }
    }
    
    public void eliminarEmpleado(){
     Empleado e;
     if(vista.solicitarConfirmacion()){
         
     }
    }
    
    public void modificarEmpleado(){
        String dni=vista.getDni();
        Empleado e=modelo.getEmpleado(dni);
        if(e!=null){
            e=modelo.getEmpleado(vista.getDni());
            if(modelo.eliminarEmpleado(e)){
                vista.mostrarMensaje("Empleado eliminado");
            }
        }
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
