
package es.sauces.appalquiler;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Oscar Pascual Ferrero
 */
public class AppAlquiler {

    public static void main(String[] args) {
       Scanner teclado=new Scanner(System.in);
        AgenciaAlquiler aa=null;
        aa=new AgenciaAlquiler();
        int opcion, operacion, plazas;        
        String matricula, grupo;
        Vehiculo t=null, f=null;
        float capacidad;
        
        do{
            opcion = getOpcion();
                switch(opcion){ 
                    case 1 -> {
                        operacion=getCrear();
                        switch(operacion){
                            case 1-> {
                                System.out.println("Introduce matricula: ");
                                matricula=teclado.nextLine();
                                System.out.println("Introduce grupo: "+Arrays.toString(Grupo.values()));
                                grupo=teclado.nextLine();
                                System.out.println("Introduce plazas: ");
                                plazas=teclado.nextInt();
                                teclado.nextLine();
                                t=new Turismo(matricula,Grupo.valueOf(grupo),plazas);
                                if(aa.incluirVehiculo(t)){
                                    System.out.println("Turismo creado con exito.");
                                }
                                else{
                                    System.out.println("No creado.");
                                }   
                            }
                            case 2-> {                                
                                System.out.println("Introduce matricula: ");
                                matricula=teclado.nextLine();
                                System.out.println("Introduce grupo: "+Arrays.toString(Grupo.values()));
                                grupo=teclado.nextLine();
                                System.out.println("Introduce capacidad: ");
                                capacidad=teclado.nextFloat();
                                teclado.nextLine();
                                f=new Furgoneta(matricula,Grupo.valueOf(grupo),capacidad);
                                if(aa.incluirVehiculo(f)){
                                    System.out.println("Furgoneta creado con exito.");
                                }
                                else{
                                    System.out.println("No creado.");
                                } 
                            }
                        }
                    }
                    case 2 -> {
                        System.out.println("Daime una matricula");
                        matricula=teclado.nextLine();
                        if(matricula!=null){
                        System.out.println(aa.consultarVehiculo(matricula));
                        }
                    }
                    case 3 -> {
                        System.out.println("Daime una matricula");
                        matricula=teclado.nextLine();                        
                        if(matricula!=null){
                            Vehiculo v=aa.consultarVehiculo(matricula);
                            aa.eliminarVehiculo(v);
                            System.out.println("Listo.");
                        }
                        
                    }
                    case 4 -> {
                        operacion=getListar();
                        switch(operacion){
                            case 1-> {
                                for(Vehiculo v: aa.listarVehiculosPorPrecio()){
                                System.out.println(v); 
                                }
                            }
                            case 2-> {                                
                               for(Vehiculo v: aa.listarVehiculosPorPrecio()){
                                   if(v.getClass()==Turismo.class)
                                       System.out.println(v);
                               }
                            }
                            case 3-> {                                
                               for(Vehiculo v: aa.listarVehiculosPorPrecio()){
                                   if(v.getClass()==Furgoneta.class)
                                       System.out.println(v);
                               }
                            }
                            case 4-> {                                
                                System.out.println("Daime un grupo: ");
                                grupo=teclado.nextLine();                                
                                for(Vehiculo v: aa.listarVehiculos(Grupo.valueOf(grupo))){
                                    System.out.println(v);
                               }
                            }
                        }
                    }
                    case 5 -> {                         
                        System.out.println(aa.getVehiculoMasBarato()); 
                    }                                      
                }        
        }while(opcion!=0);
    }
        
    private static int getOpcion(){
        int opcion;
        Scanner teclado=new Scanner(System.in);
            System.out.println("1. Crear vehiculo.");
            System.out.println("2. Consultar vehiculo.");
            System.out.println("3. Eliminar vehiculo.");
            System.out.println("4. Listar vehiculos.");
            System.out.println("5. Consultar alquiler mas barato.");
            System.out.println("0. Salir.");
            System.out.print("Introduzca opcion: ");
            opcion=leerEntero();            
            return opcion;
    }
    
    private static int getCrear(){
        int opcion;
        Scanner teclado=new Scanner(System.in);
            System.out.println("Introduzca opcion de creacion: ");
            System.out.println("1.1 Crear turismo.");
            System.out.println("1.2 Crear furgoneta.");            
            opcion=leerEntero();
            return opcion;
    }
    
    private static int getListar(){
        int opcion;
        Scanner teclado=new Scanner(System.in);
            System.out.println("Introduzca opcion de listado: ");
            System.out.println("1.1 Listar por precio.");
            System.out.println("1.2 Listar turismos.");   
            System.out.println("1.3 Listar furgonetas."); 
            System.out.println("1.4 Listar por grupo."); 
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
    
    
}
