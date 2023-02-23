
package es.sauces.appalquiler;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Oscar Pascual Ferrero
 */
public class AgenciaAlquiler{
    private List<Vehiculo> flota;
    private String nombre;

    public AgenciaAlquiler(){
        flota=new LinkedList<>();
    }
    
    public boolean incluirVehiculo(Vehiculo vehiculo){
        if(!flota.contains(vehiculo)){
            return flota.add(vehiculo);
        }
        else{
            return false;
        }
    }
    
    public Vehiculo consultarVehiculo(String matricula){
        for(Vehiculo v:flota){
            if(matricula.equals(v.getMatricula())){
                return v;
            }
        }
        return null;
    }
    
    public boolean eliminarVehiculo(Vehiculo vehiculo){
        return flota.remove(vehiculo);
    }
    
    public List<Vehiculo> listarVehiculosPorPrecio(){
        List<Vehiculo> listado=new ArrayList<>(flota);
        Collections.sort(listado,new ComparadorPrecio());
        return listado;
    }
    
    public List<Vehiculo> listarVehiculos(Grupo grupo){
        List<Vehiculo> listado=new ArrayList<>();
        
        for(Vehiculo v: flota){
            if(v.getGrupo()==grupo){
                listado.add(v);
            }
        }
        
        return listado;
    }
    
    public Vehiculo getVehiculoMasBarato(){
        if(flota.size()>0){
            return Collections.min(flota,new ComparadorPrecio());
        }
        return null;
    }
    
}
