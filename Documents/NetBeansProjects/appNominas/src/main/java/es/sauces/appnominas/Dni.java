package es.sauces.appnominas;


import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Oscar Pascual Ferrero
 */
public class Dni implements comparable<Dni>{
    private String valor;

    private static final Logger LOG = Logger.getLogger(Dni.class.getName());
    
    public Dni(String valor) throws DniException {
        if(!esValido(valor)){
            LOG.log(Level.WARNING,"Dni incorrecto: (0)",valor);
            throw new DniException("Formato incorrecto");
        }
        this.valor = valor;
    }
    
    
    
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.valor);
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
        return Objects.equals(this.valor, other.valor);
    }
    
    public static boolean esValido(String dni){        
        Pattern p=Pattern.compile("([0-9]{8})([A-Z])");
        Matcher m=p.matcher(dni);    
        return m.matches();
    }
    
    public static Dni valueOf(String dni) throws DniException{
        return new Dni(dni);
    }
}
