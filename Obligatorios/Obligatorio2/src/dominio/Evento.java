package dominio;

import tads.ListaNodos;
import dominio.Entrada;
import dominio.Evento;

public class Evento implements Comparable<Evento> 
{
    private String codigo;
    private String descripcion;
    private int puntaje = 0;
    private double PromedioPuntaje = 0;
    private Sala sala;
    private int entradasDisponibles;
    private int entradasVendidas = 0;
    private ListaNodos<Cliente> listadoEsperaClientes = new ListaNodos<Cliente>();
    private ListaNodos<Entrada> listadoEntradas = new ListaNodos<Entrada>();
    private ListaNodos<Calificacion> calificaciones = new ListaNodos<>();
     
    public Evento(String codigo, String descripcion, Sala sala, int entradasDisponibles) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.sala = sala;
        this.entradasDisponibles = entradasDisponibles;
    }
    
    public Evento(String codigo) {
        this.codigo = codigo;
    }
    
    public String getCodigo() {
        return this.codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPuntaje() {
        return puntaje;
    }
    
    public double getPromedioPuntaje(){
    return PromedioPuntaje;
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
    
   public ListaNodos<Calificacion> getCalificaciones(){
        return calificaciones;
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
    
    public void setPromedioPuntaje(double promedio){
    this.PromedioPuntaje = promedio;
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

    public void setListadoEsperaClientes(Cliente cliente) {
        this.listadoEsperaClientes.agregarInicio(cliente);
    }

    public void setListadoEntradas(Entrada entrada) {
        this.listadoEntradas.agregarInicio(entrada);
    }
    
    public void setCalificaciones(Calificacion calificacion) {
        this.calificaciones.agregarInicio(calificacion);
    }
    
    @Override
    public boolean equals(Object o) {
        Evento auxEvento = (Evento)o;
        return this.codigo.equals(auxEvento.codigo);
    }
    
    @Override
    public int compareTo(Evento o) {
        return this.codigo.compareTo(o.codigo);
    }
    
    @Override
    public String toString() {
        return this.codigo + "-" + this.descripcion + "-" + this.sala + "-" + this.entradasDisponibles + "-" + this.entradasVendidas;
    }

}