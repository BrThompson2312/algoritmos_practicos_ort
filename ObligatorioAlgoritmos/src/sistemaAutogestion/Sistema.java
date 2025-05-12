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
            
            int indiceActual = 0;
            Sala auxSala = listaSalas.obtenerElemento(indiceActual);
            boolean encontrado = false;

            while (auxSala != null && !encontrado) {
                if (auxSala.getCapacidad() >= aforoNecesario) {

                    if (auxSala.getFechaOcupada().esVacia()) {
                        
                        auxSala.setFechaOcupada(fecha);
                        encontrado = true;
                        
                    } else {
                        
                        Nodo auxNodoFecha = auxSala.getFechaOcupada().getInicio();
                        LocalDate auxFecha = (LocalDate)auxNodoFecha.getDato();
                        
                        while (auxNodoFecha != null && !encontrado) {
                            if (
                                auxFecha == fecha
                                && auxFecha.getDayOfMonth() >= 1 && auxFecha.getDayOfMonth() <= 30
                                && auxFecha.getMonthValue() >= 1 && auxFecha.getMonthValue() <= 12
                            ){
                                auxSala.setFechaOcupada(fecha);
                                encontrado = true;
                            } else {
                                auxNodoFecha = auxNodoFecha.getSiguiente();
                            }
                        }
                        
                        if (!encontrado) {
                            auxSala = listaSalas.obtenerElemento(++indiceActual);
                        }
                    }
                    
                } else {
                    auxSala = listaSalas.obtenerElemento(++indiceActual);
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
        Retorno r = new Retorno(Retorno.Resultado.OK);
        
        String texto = "";
        for (int i = 0; i <= listaSalas.cantidadElementos(); i++) {
            Sala s = listaSalas.obtenerElemento(i);
            texto += s.toString() + "#";
        }
        
        // Elimino ultimo "#"
        texto = texto.substring(0, texto.length() - 1);
        
        r.valorString = texto;
        
        return r;
    }

    // 2.2
    @Override
    public Retorno listarEventos() {
        Retorno r = new Retorno(Retorno.Resultado.OK);
        
        ListaNodos<Evento> auxListaEventos = new ListaNodos<Evento>();

        for (int i = 0; i <= listaEventos.cantidadElementos(); i++) {
            Evento auxEvento = listaEventos.obtenerElemento(i);
            auxListaEventos.agregarOrdenado(auxEvento);
        }
        
        String texto = "";
        for (int i = 0; i <= auxListaEventos.cantidadElementos(); i++) {
            Evento auxEvento = auxListaEventos.obtenerElemento(i);
            texto += auxEvento.toString() + "#";
        }
        
        // Elimino ultimo "#"
        texto = texto.substring(0, texto.length() - 1);
        r.valorString = texto;
        
        return r;
    }

    // 2.3
    @Override
    public Retorno listarClientes() {
        Retorno r = new Retorno(Retorno.Resultado.OK);
        
        ListaNodos<Cliente> auxListaClientes = new ListaNodos<Cliente>();
        
        for (int i = 0; i <= listaClientes.cantidadElementos(); i++) {
            Cliente auxCliente = listaClientes.obtenerElemento(i);
            auxListaClientes.agregarOrdenado(auxCliente);
        }
        
        String texto = "";
        for (int i = 0; i <= auxListaClientes.cantidadElementos(); i++) {
            Cliente auxCliente = auxListaClientes.obtenerElemento(i);
            texto += auxCliente.toString() + "#";
        }
        
        // Elimino ultimo "#"
        texto = texto.substring(0, texto.length() - 1);
        
        r.valorString = texto;
        
        return r;
    }

    // 2.4
    @Override
    public Retorno esSalaOptima(String[][] vistaSala) {
        
        Retorno r = new Retorno(Retorno.Resultado.OK);
        
        int columnas = 0;
        
        for (int f = 0; f < vistaSala[0].length; f++) {
            
            int asientosLibres_X = 0;
            int asientosOcupados_O = 0;
            int consecutivos = 0;
            int maxConsecutivos = 0;
                
            for (int c = 0; c < vistaSala.length; c++) {
                
                if (vistaSala[c][f].equals("O")) {
                    asientosOcupados_O++;
                    consecutivos++;
                    
                    if (consecutivos > maxConsecutivos) {
                        maxConsecutivos = consecutivos;
                    }
                    
                } else if (vistaSala[c][f].equals("X")) {
                    asientosLibres_X++;
                    consecutivos = 0;
                }
                
            }
            
            if (maxConsecutivos >= asientosLibres_X) {
                columnas++;
            }
        }
        
        if (columnas >= 2) {
            r.valorString = "Es óptimo";
        } else {
            r.valorString = "No es óptimo";
        }

        return r;
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