package modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oscar Pascual Ferrero
 */
public class EmpleadoDaoJson implements EmpleadoDao {

    private Path path;

    public EmpleadoDaoJson(String path) {
        this.path = Paths.get(path);
    }


    @Override
    public List<Empleado> Listar() throws DaoException {

        java.lang.reflect.Type tipo = new com.google.gson.reflect.TypeToken<List<Empleado>>() {
        }.getType();
    
        RuntimeTypeAdapterFactory<Empleado> empleadoAdapter = RuntimeTypeAdapterFactory.of(Empleado.class, "type");

        empleadoAdapter.registerSubtype(EmpleadoFijo.class, "Fijo");
        empleadoAdapter.registerSubtype(EmpleadoEventual.class, "Eventual");

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting ();
        builder.registerTypeAdapterFactory (empleadoAdapter);

        Gson gson = builder.create();


        List<Empleado> listadoEmpleados=new ArrayList<>();
        Paths.get("empleados.json");

        try ( BufferedReader br = Files.newBufferedReader(path)) {
            listadoEmpleados = gson.fromJson(br, tipo);
        } catch (IOException ioe) {
            Logger.getLogger(EmpleadoDaoJson.class.getName()).log(Level.SEVERE, null, ioe);
        }

        return listadoEmpleados;
    }

    @Override
    public int insertar(List<Empleado> listado) throws DaoException {

        java.lang.reflect.Type tipo = new com.google.gson.reflect.TypeToken<List<Empleado>>() {
        }.getType();
    
        RuntimeTypeAdapterFactory<Empleado> empleadoAdapter = RuntimeTypeAdapterFactory.of(Empleado.class, "type");

        empleadoAdapter.registerSubtype(EmpleadoFijo.class, "Fijo");
        empleadoAdapter.registerSubtype(EmpleadoEventual.class, "Eventual");
        
        
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting ();
        builder.registerTypeAdapterFactory (empleadoAdapter);

        Gson gson = builder.create();
      
        Paths.get("empleados.json");

        try ( BufferedWriter bw = Files.newBufferedWriter(path)) {
            gson.toJson(listado, tipo, bw);
        } catch (IOException ioe) {
            Logger.getLogger(EmpleadoDaoJson.class.getName()).log(Level.SEVERE, null, ioe);
        }
        return listado.size();
    }

}
