package sistemaAutogestion;

import dominio.Evento;
import dominio.Sala;
import dominio.Cliente;
import java.time.LocalDate;
import tads.ListaNodos;
import tads.Nodo;

public class Sistema implements IObligatorio {
    
    private ListaNodos<Evento> listaEventos;
    private ListaNodos<Cliente> listaClientes;
    private ListaNodos<Sala> listaSalas;
    
    // 1.1
    @Override
    public Retorno crearSistemaDeGestion() {
        this.listaEventos = new ListaNodos<Evento>();
        this.listaClientes = new ListaNodos<Cliente>();
        this.listaSalas = new ListaNodos<Sala>();
        return Retorno.ok("Si pudo inicializar el sistema correctamente.");
    }
    
    // 1.2
    @Override
    public Retorno registrarSala(String nombre, int capacidad) {
        Sala sAux = new Sala(nombre, capacidad);
        if (listaSalas.getCantElementos() == 0) {
            return Retorno.error2();
        } else {
            if (!listaSalas.existeElemento(sAux)) {
                listaSalas.agregarFinal(sAux);
                return Retorno.ok("Si pudo registrar la sala");
            } else {
                return Retorno.error1();
            }
        }
    }

    // 1.3
    @Override
    public Retorno eliminarSala(String nombre) {
        return Retorno.noImplementada();
    }

    // 1.4
    @Override
    public Retorno registrarEvento(String codigo, String descripcion, int aforoNecesario, LocalDate fecha) {
        
        /*if (listaEventos.existeElemento(auxEvento)) {
            return Retorno.error1();
        } else {
            if (aforoNecesario <= 0) {
                return Retorno.error2();
            } else {
            
                Sala auxSala = new Sala("Prueba", aforoNecesario);
            
                if (listaSalas.esVacia()) {
                    return Retorno.error3();
                } else {
                    boolean existe = false;
                    Nodo auxSala = listaSalas.getInicio();
                
                    while(auxSala != null && !existe) {
                        if (listaSalas.getInicio().getDato().getCapacidad() >= aforoNecesario) {

                        }
                    }
                }
                Evento auxEvento = new Evento(codigo, descripcion, auxSala);
                if (!listaEventos.existeElemento(auxEvento)) {
                    listaEventos.agregarFinal(auxEvento);
                    return Retorno.ok();
                } else {
                    return Retorno.error1();
                }
            }
        }*/
        
        Evento auxEvento = new Evento(codigo);
        if (!listaEventos.existeElemento(auxEvento)) {
            if (aforoNecesario != 0 && aforoNecesario > 0) {
                
                Nodo auxSala = listaSalas.getInicio();
                boolean salaDisponible = false;
                
                Sala auxSala2 = listaSalas.getInicio().getDato(); 
                while (auxSala != null && !salaDisponible) {
                    if (auxSala2.getCapacidad() >= aforoNecesario) {
                        
                    }
                }
            }
            return Retorno.error1();
        }
        return Retorno.error1();
    }

    @Override
    public Retorno registrarCliente(String cedula, String nombre) {
        /*Cliente cAux = new Cliente();
        cAux.setCedula(cedula);
        
        if (!listaClientes.existeElemento(cAux)) {
            Cliente c = new Cliente();
            listaClientes.agregarFinal(c);
        }*/
        return Retorno.noImplementada();
    }

    @Override
    public Retorno comprarEntrada(String cedula, String codigoEvento) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno eliminarEvento(String codigo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno devolverEntrada(String cedula, String codigoEvento) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno calificarEvento(String cedula, String codigoEvento, int puntaje, String comentario) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarSalas() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarEventos() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarClientes() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno esSalaOptima(String[][] vistaSala) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarClientesDeEvento(String código, int n) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarEsperaEvento() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno deshacerUtimasCompras(int n) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno eventoMejorPuntuado() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno comprasDeCliente(String cedula) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno comprasXDia(int mes) {
        return Retorno.noImplementada();
    }
}