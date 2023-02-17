/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.sauces.appnominas;

import java.util.ArrayList;
import java.util.Collections;
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
        if(!empleados.contains(empleado)){
            return empleados.add(empleado);
        }
        else{
            return false;
        }        
    }
    
    public Empleado getEmpleado(String dni){        
        for(Empleado e:empleados){
            if(dni.equals(e.getDni())){
                return e;
            }
        }
        return null;
    }
    
    public boolean eliminarEmpleado(Empleado empleado){
        return empleados.remove(empleado);        
    }
    
    public List<Empleado> listarEmpleados(){
        List<Empleado> listado=new ArrayList<>(empleados);        
        return listado;
    }
    
    public List<Empleado> listarEmpleadosPorSueldo(){
        List<Empleado> listado=new ArrayList<>(empleados);
        Collections.sort(listado,new ComparadorSueldo());
        return listado;
    }
    
    public float getTotalSalarios(){
        float totalSalario=0;
        for(Empleado e:empleados){
            totalSalario+=e.ingresos();
        }
        return totalSalario;
    }
    
}
