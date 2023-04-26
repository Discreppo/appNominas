package modelo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;
import com.thoughtworks.xstream.io.xml.DomDriver;
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
public class EmpleadoDaoXml implements EmpleadoDao {

    private Path path;

    public EmpleadoDaoXml(String path) {
        this.path = Paths.get(path);
    }

    @Override
    public List<Empleado> Listar() throws DaoException {
        XStream xstream = new XStream(new DomDriver());

        xstream.allowTypeHierarchy(EmpleadoFijo.class);
        xstream.allowTypeHierarchy(EmpleadoEventual.class);

        List<Empleado> listadoEmpleados=new ArrayList<>();
        try ( BufferedReader br = Files.newBufferedReader(path);) {
            listadoEmpleados = (List<Empleado>) xstream.fromXML(br);
        } catch (IOException|XStreamException ioe) {
            Logger.getLogger(EmpleadoDaoXml.class.getName()).log(Level.SEVERE, null, ioe);
        }
        return listadoEmpleados;
    }

    @Override
    public int insertar(List<Empleado> listadoEmpleados) throws DaoException {
        XStream xstream = new XStream(new DomDriver());

        xstream.allowTypeHierarchy(EmpleadoFijo.class);
        xstream.allowTypeHierarchy(EmpleadoEventual.class);

       
        try ( BufferedWriter bw = Files.newBufferedWriter(path)) {
            xstream.toXML(listadoEmpleados, bw);

        } catch (IOException ioe) {
            Logger.getLogger(EmpleadoDaoXml.class.getName()).log(Level.SEVERE, null, ioe);
        }
        return listadoEmpleados.size();
    }

}
