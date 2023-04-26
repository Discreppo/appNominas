package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Oscar Pascual Ferrero
 */
public class SistemaNominas {
    private Set<Empleado> empleados;
    private EmpleadoDao empleadoDao;

    public SistemaNominas() {
        empleados=new TreeSet<>();
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
            if(dni.equals(e.getDni().toString())){
                return e;
            }
        }
        return null;
    }
    
    public EmpleadoDao getEmpleadoDao() {
        return empleadoDao;
    }

    public void setEmpleadoDao(EmpleadoDao empleadoDao) {
        this.empleadoDao = empleadoDao;
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
    
    public int guardarEmpleados() throws DaoException{
        return empleadoDao.insertar(new ArrayList<>(listarEmpleados()));
    }
    
    public int cargarEmpleados() throws DaoException{
        int contador=0;
        for(Empleado e:empleadoDao.Listar()){
            if(incluirEmpleado(e)){            
                contador++;
            }
        }
        return contador;
    }
}
