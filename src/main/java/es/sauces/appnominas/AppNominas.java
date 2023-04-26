package es.sauces.appnominas;

import modelo.EmpleadoDaoCsv;
import modelo.EmpleadoDao;
import modelo.SistemaNominas;
import modelo.DaoException;
import modelo.EmpleadoFijo;
import modelo.EmpleadoDaoJson;
import modelo.EmpleadoDaoObj;
import modelo.DniException;
import modelo.EmpleadoEventual;
import modelo.EmpleadoDaoXml;
import modelo.Empleado;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 *
 * @author Oscar Pascual Ferrero
 */
public class AppNominas {

    private static final Logger LOG = Logger.getLogger("es.sauces.appnominas");
    
    
    public static void main(String[] args) throws IOException, DniException {
        Scanner teclado=new Scanner(System.in);
        SistemaNominas sn=null;
        int opcion, operacion, horas;
        String dni, nombre, nombreArchivo;
        float salario, salarioHoras;
        Empleado e=null;
        EmpleadoDao dao;
        
        
        /*LogManager.getLogManager().reset();*/
        LogManager.getLogManager().readConfiguration(ClassLoader.getSystemClassLoader().getResourceAsStream("mylogging.properties"));        
        /*Handler controlador=new FileHandler("./registro.log",true);
        controlador.setFormatter(new SimpleFormatter());
        LOG.setLevel(Level.WARNING);
        controlador.setEncoding(StandardCharsets.UTF_8.name());
        LOG.addHandler(controlador);*/
        
        LOG.info("Inicio de aplicacion");
        sn=new SistemaNominas();
        
        do{
            opcion = getOpcion();
            LOG.log(Level.INFO,String.format("opcion seleccionada: %d", opcion));
                switch(opcion){ 
                    case 1 -> {
                        operacion=getOperacion();
                        switch(operacion){
                            case 1-> {
                                System.out.println("Introduce DNI: ");
                                dni=teclado.nextLine();                               
                                System.out.println("Introduce nombre: ");
                                nombre=teclado.nextLine();
                                System.out.println("Introduce salario: ");
                                salario=leerFloat();
                                e=new EmpleadoFijo(dni,nombre,salario);
                                if(sn.incluirEmpleado(e)){
                                    System.out.println("Empleado creado con exito.");
                                }
                                else{
                                        System.out.println("No creado.");
                                }       
                            }
                            case 2-> {                                
                                System.out.println("Introduce DNI: ");
                                dni=teclado.nextLine();                                
                                System.out.println("Introduce nombre: ");
                                nombre=teclado.nextLine();
                                System.out.println("Introduce salario por horas: ");
                                salarioHoras=leerFloat();
                                System.out.println("Introduce horas trabajadas: ");
                                horas=teclado.nextInt();
                                teclado.nextLine();
                                e=new EmpleadoEventual(dni,nombre,salarioHoras,horas);
                                if(sn.incluirEmpleado(e)){
                                    System.out.println("Empleado creado con exito.");
                                }
                                else{
                                        System.out.println("No creado.");
                                } 
                            }
                        }
                    }
                    case 2 -> {
                        System.out.println("Introduzca DNI a buscar: ");
                        dni=teclado.nextLine();
                        System.out.println(sn.getEmpleado(dni));
                    }
                    case 3 -> {
                        System.out.println("Introduzca DNI a buscar: ");
                        dni=teclado.nextLine();
                        e=sn.getEmpleado(dni);
                        if(sn.eliminarEmpleado(e)){
                            System.out.println("Eliminado");
                        }
                        else{
                            System.out.println("Por lo que sea, no se ha borrado.");
                        }
                    }
                    case 4 -> {
                        for(Empleado em: sn.listarEmpleados()){
                            System.out.println(em);
                        }
                    }
                    case 5 -> {                        
                        for(Empleado em: sn.listarEmpleadosPorSueldo()){
                            System.out.println(em);
                        }
                    }
                    case 6 -> {                        
                        System.out.println(sn.getTotalSalarios());
                    }
                    case 7 -> {                        
                        System.out.println("Introduzca nombre del archivo: ");
                        nombreArchivo=teclado.nextLine();
                        dao = getEmpleadoDao(nombreArchivo);
                        if(dao!=null){
                            sn.setEmpleadoDao(dao);
                            try {
                                sn.guardarEmpleados();
                            } catch (DaoException de) {
                                System.out.println(de.getMessage());
                            }
                        }
                    }  
                    case 8 -> {                        
                        System.out.println("Introduzca nombre del archivo: ");
                        nombreArchivo=teclado.nextLine();
                        dao = getEmpleadoDao(nombreArchivo);
                        if(dao!=null){
                            sn.setEmpleadoDao(dao);
                            try {
                                sn.cargarEmpleados();
                            } catch (DaoException de) {                            
                                System.out.println(de.getMessage());
                            }
                        }
                    }  
                }        
        }while(opcion!=0);
        
    }
        
    private static int getOpcion(){
        int opcion;
        Scanner teclado=new Scanner(System.in);
            System.out.println("1. Crear empleado.");
            System.out.println("2. Consultar empleado.");
            System.out.println("3. Eliminar empleado.");
            System.out.println("4. Listar empleados.");
            System.out.println("5. Listar empleados por sueldo.");
            System.out.println("6. Consultar total salarios.");
            System.out.println("7.- Guardar empleados. ");
            System.out.println("8.- Cargar empleados. ");
            System.out.println("0. Salir.");
            System.out.print("Introduzca opcion: ");
            opcion=leerEntero();  
                     
            return opcion;
            
    }
    
    private static int getOperacion(){
        int opcion;
        Scanner teclado=new Scanner(System.in);
            System.out.println("Introduzca opcion de creacion: ");
            System.out.println("1.1 Crear empleado fijo.");
            System.out.println("1.2 Crear empleado eventual.");            
            opcion=leerEntero();
            
            return opcion;
    }
    
    private static int leerEntero() {
        Scanner teclado = new Scanner(System.in);
        int n;
        while (!teclado.hasNextInt()) {
            teclado.nextLine();
            System.out.println("Introduce un numero entero por favor");
        }
        n = teclado.nextInt();
        
        return n;
    }
    
    private static float leerFloat() {
        Scanner teclado = new Scanner(System.in);
        float n;
        while (!teclado.hasNextFloat()) {
            teclado.nextLine();
            System.out.println("Introduce un numero float por favor");
        }
        n = teclado.nextInt();
        
        return n;
    }
    private static EmpleadoDao getEmpleadoDao(String nombreArchivo){
        String ext = nombreArchivo.substring(nombreArchivo.lastIndexOf("."));
        EmpleadoDao dao=null;
        switch(ext){
            case ".csv" -> dao = new EmpleadoDaoCsv(nombreArchivo);
            case ".obj" -> dao = new EmpleadoDaoObj(nombreArchivo);
            case ".xml" -> dao = new EmpleadoDaoXml(nombreArchivo);
            case ".json" -> dao = new EmpleadoDaoJson(nombreArchivo);
        }
        return dao;
    }
}
