package src.clases;

import src.tadlista.ListaNodos;
import src.tadlista.Nodo;
import src.clases.Evento;
import src.clases.Sala;
import src.clases.Cliente;

import java.time.LocalDate;

public class Sistema {

    public ListadoNodos<Evento> listadoEventos;
    public Listadonodos<Sala> listadoSalas;
    public ListadoNodos<Cliente> listadoClientes;
    public String[] reportes;

    public Sistema() {
        this.listadoEventos = new ListaNodos();
        this.listadoSalas = new ListaNodos();
        this.listadoClientes = new ListadoNodos();
    }

    public Retorno crearSistemaDeGestion() {
        Retorno retorno = new Retorno();
        return null;

        try {

        } catch (exception e) {
            
        }
    }

    public Retorno registrarSala(String nombre, int capacidad) {
        return null;
    }

    public Retorno eliminarSala(String nombre) {
        return null;
    }

    public Retorno registrarEvento(String codigo, String descripcion, int aforoNecesario, LocalDate fecha) {
        return null;
    }

    public Retorno registrarCliente(String cedula, String nombre) {
        return null;
    }

    // public Retorno comprarEntrada(String cedula, String codigoEvento) {
    //     return null;
    // }

    // public Retorno eliminarEvento(String codigo) {
    //     return null;
    // }

    // public Retorno devolverEntrada(String cedula, String codigoEvento) {
    //     return null;
    // }

    // public Retorno calificarEvento(String cedula, String codigoEvento, int puntaje, String
    // comentario) {
    //     return null;
    // }

    public Retorno listarSalas() {
        return null;
    }

    public Retorno listarEventos() {
        return null;
    }

    public Retorno listarClientes() {
        return null;
    }

    public Retorno esSalaOptima(String vistaSala[][]) {
        return null;
    }

    // public Retorno listarClientesDeEvento(String código, int n) {
    //     return null;
    // }

    // public Retorno listarEsperaEvento() {
    //     return null;
    // }

    // public Retorno deshacerUtimasCompras(int n) {
    //     return null;
    // }

    // public Retorno eventoMejorPuntuado() {
    //     return null;
    // }

    // public Retorno comprasDeCliente(String cedula) {
    //     return null;
    // }

    // public Retorno comprasXDia(int mes) {
    //     return null;
    // }
    
}