package es.sauces.appnominas;

import controlador.Controlador;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import modelo.SistemaNominas;
import vista.Ventana;

/**
 *
 * @author Oscar Pascual Ferrero
 */
public class AppNominasMVC {


    private static final Logger LOG = Logger.getLogger("ES.SAUCES.APPNOMINAS");

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

        java.util.ResourceBundle rb = ResourceBundle.getBundle("mensajes");*/

        LogManager.getLogManager().readConfiguration(ClassLoader.getSystemClassLoader().getResourceAsStream("mylogging.properties"));

        /*sistemaNominas = new SistemaNominas();*/

        LOG.info("INICIO DE SESION");
    }
}