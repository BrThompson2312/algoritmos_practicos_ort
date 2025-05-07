package dominio;

import tads.Nodo;
import java.time.LocalDate;
import tads.ListaNodos;

public class Sala implements Comparable<Evento> 
{
    private ListaNodos<LocalDate> fechaOcupada;
    private String nombre;
    private int capacidad;
    
    public Sala(String nombre, int capacidad) {
        this.fechaOcupada = new ListaNodos<LocalDate>(); 
        this.nombre = nombre;
        this.capacidad = capacidad;
    }
    
    public void setFechaOcupada(ListaNodos<LocalDate> fechaOcupada) {
        this.fechaOcupada = fechaOcupada;
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
    public int compareTo(Evento o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}