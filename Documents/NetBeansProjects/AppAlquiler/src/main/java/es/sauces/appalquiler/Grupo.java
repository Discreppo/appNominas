package es.sauces.appalquiler;

/**
 *
 * @author Oscar Pascual Ferrero
 */
public enum Grupo {
    A (50.0f,1.5f,5.0f),B (55.0f,2.0f,10.0f),C (60.0f,2.5f,15.0f);
    private final float precioBase;
    private final float variableTurismo;
    private final float variableFurgoneta;
    
    

    private Grupo(float precioBase,float variableTurismo,float variableFurgoneta) {
        this.precioBase = precioBase;
        this.variableTurismo = variableTurismo;
        this.variableFurgoneta = variableFurgoneta;
    }

    public float getPrecioBase() {
        return precioBase;
    }
    
    public float getVariableTurismo() {
        return variableTurismo;
    }
    
    public float getVariableFurgoneta() {
        return variableFurgoneta;
    }
    
    
}
