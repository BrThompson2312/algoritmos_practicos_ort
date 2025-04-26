package ej2;

public class Socio {
    
    private String nombre;
    private int numero;
    private static int ProximoNumero = 1;
     
    public Socio(String elNombre) {
        this.setNombre(elNombre);
        numero = ProximoNumero++;
    }
    
    // Getters & Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String elNombre) {
        nombre = elNombre;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public int getProximoNumero() {
        return ProximoNumero;
    }
    
   @Override
   public String toString() {
       return "nombre: " + nombre + ", numero: " +numero;
   }
}
