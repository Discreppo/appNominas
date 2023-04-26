package modelo;

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
public class EmpleadoDaoCsv implements EmpleadoDao {
    private Path path;

    public EmpleadoDaoCsv(String path) {
        this.path = Paths.get(path);
    }
    
    @Override
    public List<Empleado> Listar() throws DaoException {
        List<Empleado> listado = new ArrayList<>();
        String linea, nombre;
        Dni dni;
        String[] tokens;
        float salario;
        int horas;
        Empleado e=null;
        try(BufferedReader br=Files.newBufferedReader(path)){
            
                linea=br.readLine();
           
            while(linea!=null){
                tokens=linea.split(",");
                dni=new Dni(tokens[1]);
                nombre=tokens[2];
                salario=Float.parseFloat(tokens[3]);
                switch(tokens[0]){
                    case "EmpleadoFijo" -> {
                        e=new EmpleadoFijo(dni.toString(), nombre, salario);
                    }
                    case "EmpleadoEventual" -> {
                        horas=Integer.parseInt(tokens[4]);
                        e=new EmpleadoEventual(dni.toString(), nombre, salario, horas);
                    }
                    default -> throw new DaoException("Formato de archivo incorrecto");
                }
                listado.add(e);
                linea=br.readLine();
            }
            
        } catch (IOException|DniException ex) {            
            Logger.getLogger(EmpleadoDaoCsv.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex.getMessage());
        }
        return listado;
    }

    @Override
    public int insertar(List<Empleado> listado) throws DaoException {
        try(BufferedWriter bw=Files.newBufferedWriter(path)){
            for(Empleado e:listado){
                bw.write(e.getClass().getSimpleName());

                if(e.getClass().getSimpleName().equals("EmpleadoFijo")){
                    EmpleadoFijo ef=(EmpleadoFijo) e;
                    bw.write(","+ef.toString());
                }else{
                    EmpleadoEventual emF=(EmpleadoEventual) e;
                    bw.write(","+emF.toString());
                }
                bw.newLine();
            }
        }catch(IOException ioe){
            Logger.getLogger(EmpleadoDaoCsv.class.getName()).log(Level.SEVERE, null, ioe);
            throw new DaoException(ioe.getMessage());
        }
        return listado.size();
    }
    
}
