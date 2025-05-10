package dominio;

import java.time.LocalDate;
import tads.ListaNodos;

public class Sala implements Comparable<Sala>
{
    private ListaNodos<LocalDate> fechaOcupada = new ListaNodos<LocalDate>();
    private String nombre;
    private int capacidad;
    
    public Sala(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
    }
    
    public void setFechaOcupada(LocalDate fechaOcupada) {
        this.fechaOcupada.agregarInicio(fechaOcupada);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public ListaNodos<LocalDate> getFechaOcupada() {
        return fechaOcupada;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    @Override
    public int compareTo(Sala o) {
        return this.getNombre().compareTo(o.getNombre());
    }
    
    @Override
    public String toString() {
        return this.nombre + "-" + this.capacidad;
    }
}