/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.sauces.appnominas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author daw1
 */
public class SistemaNominas {
    private List<Empleado> empleados;

    public SistemaNominas() {
        empleados=new LinkedList<>();
    }
    
    public boolean incluirEmpleado(Empleado empleado){
        return true;
    }
    
    public Empleado getEmpleado(String dni){
        return null;
    }
    
    public boolean eliminarEmpleado(Empleado empleado){
        return true;
    }
    
    public List<Empleado> listarEmpleados(){
        List<Empleado> listado=new ArrayList<>();
        return listado;
    }
    
    public List<Empleado> listarEmpleadosPorSueldo(){
        List<Empleado> listado=new ArrayList<>();
        return listado;
    }
    
    public float getTotalSalarios(){
        return 0;
    }
    
}
