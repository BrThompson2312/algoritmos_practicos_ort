
package ej3;

public abstract class Funcionario {
    private String nombre;
    private String cedula;
    
    public Funcionario (String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String pNombre) {
        nombre = pNombre;
    }
    
    public String getCedula() {
        return cedula;
    }
    
    public void setCedula(String pCedula) {
        cedula = pCedula;
    }
    
    @Override
    public String toString() {
        return "nombre: " + nombre + ", cedula: " +cedula;
    }
   
    public abstract int calcularSueldo();
   
    @Override
    public boolean equals (Object o) {
        Funcionario f = (Funcionario)o;
        return this.getCedula().equals(f.getCedula());
    }
}