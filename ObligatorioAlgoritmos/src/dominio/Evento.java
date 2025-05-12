package dominio;

import tads.ListaNodos;
import dominio.Entrada;

public class Evento implements Comparable<Evento> 
{
    private String codigo;
    private String descripcion;
    private int puntaje = 0;
    private Sala sala;
    private int entradasDisponibles = 0;
    private int entradasVendidas = 0;
    private ListaNodos<Cliente> listadoEsperaClientes = new ListaNodos<Cliente>();
    private ListaNodos<Entrada> listadoEntradas = new ListaNodos<Entrada>();;
    
    public Evento(String codigo, String descripcion, Sala sala) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.sala = sala;
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
        return sala;
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
        this.sala = Sala;
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
    public boolean equals(Object o) {
        Evento auxEvento = (Evento)o;
        return this.codigo == auxEvento.codigo;
    }
    
    @Override
    public int compareTo(Evento o) {
        return this.getCodigo().compareTo(o.getCodigo());
    }
    
    @Override
    public String toString() {
        return this.codigo + "-" 
            + this.descripcion + "-" 
            + this.sala + "-" 
            + this.entradasDisponibles + "-" 
            + this.entradasVendidas;
    }
}