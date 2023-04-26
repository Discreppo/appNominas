/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.sauces.appnominas;

import controlador.Controlador;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import modelo.SistemaNominas;
import vista.Ventana;

/**
 *
 * @author daw1
 */
public class AppNominasMVC {
/*
    private static final Logger LOG = Logger.getLogger(java.util.ResourceBundle.getBundle("mensajes").getString("ES.SAUCES.APPBANCO"));
*/
    public static void main(String[] args) throws IOException {
        SistemaNominas modelo = new SistemaNominas();
        Ventana vista = new Ventana();
        Controlador controlador = new Controlador(vista, modelo);
        vista.setControlador(controlador);
        controlador.iniciar();/*        
        SistemaNominas sistemaNominas;        

       
        Properties propiedades = new Properties();
        try ( BufferedReader fichero = Files.newBufferedReader(Paths.get("configuracion.properties"))) {
            propiedades.load(fichero);
            Locale.setDefault(new Locale(propiedades.getProperty("idioma"), propiedades.getProperty("pais")));

        } catch (IOException ioe) {
            System.out.println("Error de E/S");
        }

        java.util.ResourceBundle rb = ResourceBundle.getBundle("mensajes");

        LogManager.getLogManager().readConfiguration(ClassLoader.getSystemClassLoader().getResourceAsStream(java.util.ResourceBundle.getBundle("mensajes").getString("MYLOGGING.PROPERTIES")));

        sistemaNominas = new SistemaNominas();

        LOG.info(java.util.ResourceBundle.getBundle("mensajes").getString("INICIO DE SESION"));*/
    }
}