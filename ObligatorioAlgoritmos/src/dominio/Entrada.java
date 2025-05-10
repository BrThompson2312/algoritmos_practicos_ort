package dominio;

import dominio.Evento;

public class Entrada implements Comparable<Entrada> 
{
    private String codigo;
    private Evento entradaEvento;
    private Cliente entradaCliente;

    public Entrada(String codigo, Evento entradaEvento, Cliente entradaCliente) {
        this.codigo = codigo;
        this.entradaEvento = entradaEvento;
        this.entradaCliente = entradaCliente;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setEntradaEvento(Evento entradaEvento) {
        this.entradaEvento = entradaEvento;
    }

    public void setEntradaCliente(Cliente entradaCliente) {
        this.entradaCliente = entradaCliente;
    }

    public String getCodigo() {
        return codigo;
    }

    public Evento getEntradaEvento() {
        return entradaEvento;
    }

    public Cliente getEntradaCliente() {
        return entradaCliente;
    }

    @Override
    public int compareTo(Entrada o) {
        return this.getCodigo().compareTo(o.getCodigo());
    }
}