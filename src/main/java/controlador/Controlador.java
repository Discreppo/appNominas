package controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.DaoException;
import modelo.DniException;
import modelo.Empleado;
import modelo.EmpleadoDao;
import modelo.EmpleadoDaoCsv;
import modelo.EmpleadoDaoJson;
import modelo.EmpleadoDaoObj;
import modelo.EmpleadoDaoXml;
import modelo.EmpleadoEventual;
import modelo.EmpleadoFijo;
import modelo.SistemaNominas;
import org.apache.commons.codec.digest.DigestUtils;
import vista.Ventana;

/**
 *
 * @author daw1
 */
public class Controlador {

    private Ventana vista;
    private SistemaNominas modelo;
    private EmpleadoDao empleadoDao;

    public Controlador(Ventana vista, SistemaNominas modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    public void crearEmpleado() throws DniException {
        String dni = vista.getDni();
        String nombre = vista.getNombre();
        String tipo = vista.getTipo();
        float salario = vista.getSalario();
        if (tipo.equals("FIJO")) {
            Empleado e = new EmpleadoFijo(dni, nombre, salario);
            if (modelo.incluirEmpleado(e)) {
                vista.mostrarMensaje("Empleado creado con exito");
                vista.mostrarIngresos(salario);
            } else {
                vista.mostrarMensaje("No creado");
            }
        } else {
            int horas = vista.getHoras();
            Empleado e = new EmpleadoEventual(dni, nombre, salario, horas);
            if (modelo.incluirEmpleado(e)) {
                vista.mostrarMensaje("Empleado creado con exito");
                vista.mostrarIngresos(salario * horas);
            } else {
                vista.mostrarMensaje("No creado");
            }
        }
    }

    public void buscarEmpleado() {
        String dni = vista.getDni();
        try {
            Empleado e = modelo.getEmpleado(dni);
            if (e != null) {
                vista.mostrarNombre(e.getNombre());
                vista.mostrarTipo((e instanceof EmpleadoFijo) ? "FIJO" : "EVENTUAL");
                vista.mostrarSalario((e instanceof EmpleadoFijo) ? ((EmpleadoFijo) e).getSalario() : ((EmpleadoEventual) e).getSalarioHoras());
                if (e instanceof EmpleadoEventual empleadoEventual) {
                    vista.mostrarHoras(empleadoEventual.getHoras());
                }
                vista.mostrarIngresos(e.ingresos());
            }
        } catch (IllegalArgumentException iae) {
        }
    }

    public void eliminarEmpleado() {
        Empleado e;
        if (vista.solicitarConfirmacion()) {
            e = modelo.getEmpleado(vista.getDni());
            if (modelo.eliminarEmpleado(e)) {
                vista.mostrarMensaje("Eliminado");
                vista.listarEmpleados(modelo.listarEmpleados());
            } else {
                vista.mostrarMensaje("Por lo que sea, no se ha borrado");
            }

        }
    }

    public void modificarEmpleado() {
        String dni = vista.getDni();
        Empleado e = modelo.getEmpleado(dni);
        if (e != null) {
            e = modelo.getEmpleado(vista.getDni());
            e.setNombre(vista.getNombre()); 
            if (e instanceof EmpleadoFijo) {
                ((EmpleadoFijo) e).setSalario(vista.getSalario());                               
            }else{
                ((EmpleadoEventual) e).setSalarioHoras(vista.getSalario());
                ((EmpleadoEventual) e).setHoras((int)vista.getHoras());
            }
        }
    }

    public void listarEmpleados() {

        List<Empleado> listado;

        switch (vista.getOrden()) {
            case "dni" ->
                listado = modelo.listarEmpleados();
            case "ingresos" ->
                listado = modelo.listarEmpleadosPorSueldo();
            case "nombre" -> {
                listado = modelo.listarEmpleados();
                Collections.sort(listado, new Comparator<Empleado>() {
                    @Override
                    public int compare(Empleado o1, Empleado o2) {
                        return o1.getNombre().compareTo(o2.getNombre());
                    }
                });
            }
            default ->
                listado = null;            
        }
        if(listado!=null){
            vista.listarEmpleados(listado);
        }
    }

    public void guardarEmpleados() {
        String archivo = vista.getArchivo();
        EmpleadoDao dao;
        if (archivo != null) {
            dao = getEmpleadoDao(archivo);
            if (dao != null) {
                modelo.setEmpleadoDao(dao);
            }
        }

        try {
            vista.mostrarMensaje(String.format("SE HAN GUARDADO %d CUENTAS", modelo.guardarEmpleados()));
        } catch (DaoException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            vista.mostrarMensaje(ex.getMessage());
        }
    }

    public void cargarEmpleados() {
        String archivo=vista.getArchivo();
        modelo.setDao(new EmpleadoDaoCsv(archivo));
        try{
            vista.mostrarMensaje(String.format("SE HAN CARGADO %d CUENTAS", modelo.cargarEmpleados()));            
        }catch(DaoException de){
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, de);
            vista.mostrarMensaje(de.getMessage()); 
        }
    }
    
    public void iniciar() {
        vista.mostrar();
    }

    private static EmpleadoDao getEmpleadoDao(String archivo) {
        String ext = archivo.substring(archivo.lastIndexOf("."));
        EmpleadoDao dao = null;
        switch (ext) {
            case ".csv" ->
                dao = new EmpleadoDaoCsv(archivo);
            case ".obj" ->
                dao = new EmpleadoDaoObj(archivo);
            case ".xml" ->
                dao = new EmpleadoDaoXml(archivo);
            case ".json" ->
                dao = new EmpleadoDaoJson(archivo);
        }
        return dao;
    }

    public void setEmpleadoDao(EmpleadoDao empleadoDao) {
        this.empleadoDao = empleadoDao;
    }
    
    public void hacerLogin(){
        String usuario=vista.getUsuario();
        String password=String.valueOf(vista.getPassword());
        if(esUsuarioValido(usuario,password)){
            vista.cambiarUsuario("registrado");
        }
        else{
            vista.mostrarMensaje("Usuario o contraseña incorrecta");
        
        }
        
    }
    
    public void hacerLogout(){
        vista.cambiarUsuario("noRegistrado");
    }
    
    private boolean esUsuarioValido(String user,String pass){
        boolean esValido=false;
        String usuario, password;
        Properties propiedades=new Properties();
        try(BufferedReader fichero=Files.newBufferedReader(Paths.get("configuracion.properties"))){
            propiedades.load(fichero);
            
            usuario=propiedades.getProperty("usuario");
            password=propiedades.getProperty("password");
        
       
        esValido=user.equals(usuario) && DigestUtils.sha256Hex(pass).equals(password);
            
        
        
        }catch(IOException ioe) {
        
        }
        return esValido;
    }
    /*
    public void iniciar(){
        String usuario = "admin", password = "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944";
        Properties propiedades=new Properties();
        try(BufferedReader fichero=Files.newBufferedReader(Paths.get("configuracion.properties"))){
            propiedades.load(fichero);
            
            String usuariob=propiedades.getProperty("usuario");
            String passworde=propiedades.getProperty("password");
        
        vista.pedirCredenciales();
        if(vista.getUsuario().equals(usuario) && DigestUtils.sha256Hex(String.valueOf(vista.getPassword())).equals(password)){
            vista.mostrar();
            vista.mostrarMensaje("Bien");
            vista.mostrarMensaje(passworde);
            vista.mostrarMensaje(usuariob);
        }
        else{
            vista.mostrarMensaje("Mal");
            vista.mostrarMensaje(passworde);
            vista.mostrarMensaje(usuariob);
        }
        }catch(IOException ioe) {
        
        }
    }*/
    
}
