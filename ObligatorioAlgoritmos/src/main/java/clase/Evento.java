package clase;

import tadlista.ListaNodos;
import tadlista.Nodo;

import java.time.LocalDate;

public class Evento 
{
    public String codigo;
    public String descripcion;
    public int puntaje = 0;
    public Sala Sala;
    public int entradasDisponibles;
    public int entradasVendidas;

    public ListaNodos<Cliente> listadoEsperaClientes;
    public ListaNodos<Entrada> listadoEntradas;
}