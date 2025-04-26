package src.clases;

import src.tadlista.ListaNodos;
import src.tadlista.Nodo;

public class Evento 
{
    public String codigo;
    public String descripcion;
    public int puntaje = 0;
    public DateTime fecha;
    public Sala Sala;
    public int entradasDisponibles;
    public int entradasVendidas;

    public ListadoNodos<Cliente> listadoClientes;
    public ListadoNodos<Cliente> listadoEsperaClientes;
    public ListadoNodos<Entrada> listadoEntradas;
}