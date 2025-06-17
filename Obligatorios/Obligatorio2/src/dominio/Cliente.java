package dominio;

import tads.ListaNodos;

public class Cliente implements Comparable<Cliente> 
{
    private String cedula;
    private String nombre;
    private ListaNodos<Evento> listadoEventosComprados = new ListaNodos<Evento>();
    
    public Cliente(String cedula, String nombre){
        this.cedula = cedula;
        this.nombre = nombre;
    }
    
    public Cliente(String cedula) {
        this.cedula = cedula;
    }
    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setListadoEventosComprados(ListaNodos<Evento> listadoEventosComprados) {
        this.listadoEventosComprados = listadoEventosComprados;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public ListaNodos<Evento> getListadoEventosComprados() {
        return listadoEventosComprados;
    }
    
    @Override
    public boolean equals(Object o) {
        Cliente auxCliente = (Cliente)o;
        return this.cedula == auxCliente.cedula;
    }

    @Override
    public int compareTo(Cliente o) {
        return this.cedula.compareTo(o.cedula);
    }
    
    @Override
    public String toString() {
        return this.cedula + "-" + this.nombre;
    }
    
    public static boolean cedulaValida(String cedula) {
        if (cedula.length() != 8) {
            return false;
        }
        return true;
    }
    
}