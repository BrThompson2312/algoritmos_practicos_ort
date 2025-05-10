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
        
        if (capacidad <= 0) {
            return Retorno.error2();
        }
        
        Sala sAux = new Sala(nombre, capacidad);
        
        if (!listaSalas.existeElemento(sAux)) {
            listaSalas.agregarInicio(sAux);
            return Retorno.ok("Si pudo registrar la sala");
        }
        
        return Retorno.error1();
    }

    // 1.3
    @Override
    public Retorno eliminarSala(String nombre) {
        
        Nodo auxNodo = listaSalas.getInicio();
        Sala auxSala = (Sala)auxNodo.getDato();
        boolean encontrado = false;
        
        while (auxSala != null && !encontrado) {
            if (auxSala.getNombre() == nombre) {
                listaSalas.eliminarElemento(auxSala);
                encontrado = true;
            } else {
                auxNodo = auxNodo.getSiguiente();
            }
        }
        
        if (encontrado) {
            return Retorno.ok("Si pudo eliminar la sala");
        }
        return Retorno.error1();
    }

    // 1.4
    @Override
    public Retorno registrarEvento(String codigo, String descripcion, int aforoNecesario, LocalDate fecha) {
        
        if (aforoNecesario <= 0) {
            return Retorno.error2();
        }
        
        Evento auxEvento = new Evento(codigo);
        
        if (listaEventos.existeElemento(auxEvento)) {
            return Retorno.error1();
        }
            
        if (!listaSalas.esVacia()) {
            
            Nodo auxNodoSala = listaSalas.getInicio();
            Sala auxSala = (Sala)auxNodoSala.getDato();
            boolean encontrado = false;

            while (auxSala != null && !encontrado) {
                if (auxSala.getCapacidad() >= aforoNecesario) {

                    if (auxSala.getFechaOcupada().esVacia()) {
                        
                        LocalDate nuevaFecha = LocalDate.now();
                        auxSala.setFechaOcupada(nuevaFecha);
                        
                    } else {
                        
                        Nodo auxNodoFecha = auxSala.getFechaOcupada().getInicio();
                        LocalDate auxFecha = (LocalDate)auxNodoFecha.getDato();
                        
                        while (auxFecha != null && !encontrado) {
                            if (
                                auxFecha.getDayOfMonth() >= 1 && auxFecha.getDayOfMonth() <= 30
                                && auxFecha.getMonthValue() >= 1 && auxFecha.getMonthValue() <= 12
                            ){
                                encontrado = true;
                            } else {
                                auxNodoFecha = auxNodoFecha.getSiguiente();
                            }
                        }
                    }
                    
                } else {
                    auxNodoSala = auxNodoSala.getSiguiente();
                }
            }

            if (encontrado) {
                auxEvento.setDescripcion(descripcion);
                auxEvento.setSala(auxSala);
                listaEventos.agregarInicio(auxEvento);
                return Retorno.ok("Si pudo registrar el evento");
            } else {
                return Retorno.error3();
            }
            
        } else {
            return Retorno.error3();
        }
    }

    // 1.5
    @Override
    public Retorno registrarCliente(String cedula, String nombre) {
        
        if (!Cliente.cedulaValida(cedula)) {
            return Retorno.error1();
        }
        
        Cliente auxCliente = new Cliente(cedula, nombre);
        
        if (!listaClientes.existeElemento(auxCliente)) {
            listaClientes.agregarInicio(auxCliente);
            return Retorno.ok("Si pudo registrar el cliente");
        }
        
        return Retorno.error2();
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

    // 2.1
    @Override
    public Retorno listarSalas() {
        listaSalas.mostrarInverso();
        return Retorno.ok();
    }

    // 2.2
    @Override
    public Retorno listarEventos() {
        ListaNodos<Cliente> listaEventosOrdenados = new ListaNodos<Cliente>();
        
        Nodo auxNodo = listaClientes.getInicio();
        Cliente auxCliente = (Cliente)auxNodo.getDato();
        
        while (auxCliente != null) {
            listaEventosOrdenados.agregarOrdenado(auxCliente);
            auxNodo = auxNodo.getSiguiente();
        }
        
        listaEventosOrdenados.mostrar();
        
        return Retorno.ok();
    }

    // 2.3
    @Override
    public Retorno listarClientes() {
        listaClientes.mostrar();
        return Retorno.ok();
    }

    // 2.4
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