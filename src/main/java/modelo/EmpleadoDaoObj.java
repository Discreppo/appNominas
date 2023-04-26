package modelo;

import java.io.EOFException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oscar Pascual Ferrero
 */
public class EmpleadoDaoObj implements EmpleadoDao{
    private Path path;
    private static final Logger LOG = Logger.getLogger(EmpleadoDaoObj.class.getName());

    public EmpleadoDaoObj(String path) {
        this.path = Paths.get(path);
    }

    @Override
    public List<Empleado> Listar() throws DaoException {
        List<Empleado> listado = new ArrayList<>();
        Empleado empleado;
        
        try(InputStream is= Files.newInputStream(path);
            ObjectInputStream entrada = new ObjectInputStream(is)) {
             while (is.available()>0) {
                 empleado = (Empleado)entrada.readObject();
                  listado.add(empleado);
                }
                } catch (EOFException eofe) {
                    LOG.log(Level.WARNING, "ERROR: {0}", eofe.getMessage());
                    throw new DaoException("Error al leer los datos de los empleados.");
                } catch (IOException | ClassNotFoundException eofe) {
                    LOG.log(Level.WARNING, "ERROR: {0}", eofe.getMessage());
                    throw new DaoException("Error al leer los datos de los empleados.");
                }
                 return listado;
                     

    }

    @Override
    public int insertar(List<Empleado> listado) throws DaoException {
       try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            for (Empleado e: listado) {
                oos.writeObject(e);
            }
        } catch (IOException ex) {
            LOG.log(Level.WARNING, "ERROR: {0}", ex.getMessage());
            throw new DaoException("Error de entrada/salida.");
        }
        return 0;
    }
    
    
}
