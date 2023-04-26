package modelo;


import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Oscar Pascual Ferrero
 */
public class Dni implements Serializable, comparable<Dni>{
    private String dni;

    private static final Logger LOG = Logger.getLogger(Dni.class.getName());
    
    public Dni(String dni) throws DniException {
        if(!esValido(dni)){
            LOG.log(Level.WARNING,"Dni incorrecto: (0)",dni);
            throw new DniException("Formato incorrecto");
        }
        this.dni = dni;
    }
    
    
    
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.dni);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dni other = (Dni) obj;
        return Objects.equals(this.dni, other.dni);
    }
    
    public int compareTo(Dni o){
        return dni.compareTo(o.dni);
    }
    
    public static boolean esValido(String dni){        
        Pattern p=Pattern.compile("([0-9]{8})([A-Z])");
        Matcher m=p.matcher(dni);    
        return m.matches();
    }
    
    public static Dni valueOf(String dni) throws DniException{
        return new Dni(dni);
    }

    @Override
    public String toString() {
        return  dni ;
    }
    
    
}
