package modelo;

import java.util.List;

/**
 *
 * @author Oscar Pascual Ferrero
 */
public interface EmpleadoDao {
    List<Empleado> Listar() throws DaoException;
        int insertar(List<Empleado> listado) throws DaoException;
    
}
