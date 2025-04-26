package ej1;

public class Persona {

    private String nombre;
    private String apellido;
    private int edad;

    public Persona(String elNombre, String elApellido, int laEdad) {
        this.setNombre(elNombre);
        this.setApellido(elApellido);
        this.setEdad(laEdad);
    }

    // Getters & Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String elNombre) {
        nombre = elNombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String elApellido) {
        apellido = elApellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int laEdad) {
        edad = laEdad;
    }

    @Override
    public String toString() {
        return "persona{" + "nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + '}';
    }
}