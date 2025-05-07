package dominio;

import tads.ListaNodos;
import tads.Nodo;
import dominio.Entrada;
import java.time.LocalDate;

public class Evento implements Comparable<Evento> 
{
    private String codigo;
    private String descripcion;
    private int puntaje = 0;
    private Sala Sala;
    private int entradasDisponibles;
    private int entradasVendidas;
    private ListaNodos<Cliente> listadoEsperaClientes;
    private ListaNodos<Entrada> listadoEntradas;
    
    public Evento(String codigo, String descripcion, Sala sala) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.Sala = sala;
        
        this.entradasDisponibles = 0;
        this.entradasVendidas = 0;
        this.listadoEsperaClientes = new ListaNodos<Cliente>();
        this.listadoEntradas = new ListaNodos<Entrada>();
    }
    
    public Evento(String codigo) {
        this.codigo = codigo;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public Sala getSala() {
        return Sala;
    }

    public int getEntradasDisponibles() {
        return entradasDisponibles;
    }

    public int getEntradasVendidas() {
        return entradasVendidas;
    }

    public ListaNodos<Cliente> getListadoEsperaClientes() {
        return listadoEsperaClientes;
    }

    public ListaNodos<Entrada> getListadoEntradas() {
        return listadoEntradas;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public void setSala(Sala Sala) {
        this.Sala = Sala;
    }

    public void setEntradasDisponibles(int entradasDisponibles) {
        this.entradasDisponibles = entradasDisponibles;
    }

    public void setEntradasVendidas(int entradasVendidas) {
        this.entradasVendidas = entradasVendidas;
    }

    public void setListadoEsperaClientes(ListaNodos<Cliente> listadoEsperaClientes) {
        this.listadoEsperaClientes = listadoEsperaClientes;
    }

    public void setListadoEntradas(ListaNodos<Entrada> listadoEntradas) {
        this.listadoEntradas = listadoEntradas;
    }
    
    @Override
    public int compareTo(Evento o) {
        return this.getCodigo().compareTo(o.getCodigo());
    }
}