/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package es.sauces.appnominas;

import java.util.Scanner;

/**
 *
 * @author daw1
 */
public class AppNominas {

    public static void main(String[] args) {
        Scanner teclado=new Scanner(System.in);
        SistemaNominas sn=null;
        sn=new SistemaNominas();
        int opcion, operacion, horas;
        String dni, nombre;
        float salario, salarioHoras;
        Empleado e=null;
        
        
        do{
            opcion = getOpcion();
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
                                salario=teclado.nextFloat();
                                teclado.nextLine();
                                e=new EmpleadoFijo(salario,dni,nombre);
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
                                salarioHoras=teclado.nextFloat();
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
    
}
