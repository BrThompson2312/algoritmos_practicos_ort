package sistemaAutogestion;

import dominio.Evento;
import dominio.Sala;
import dominio.Cliente;
import dominio.Entrada;
import dominio.Calificacion;
import java.time.LocalDate;
import tads.ListaNodos;

public class Sistema implements IObligatorio {
    
    public ListaNodos<Evento> listaEventos;
    public ListaNodos<Cliente> listaClientes;
    public ListaNodos<Sala> listaSalas;
   
    // 1.1
    @Override
    public Retorno crearSistemaDeGestion() {
        this.listaEventos = new ListaNodos<>();
        this.listaClientes = new ListaNodos<>();
        this.listaSalas = new ListaNodos<>();
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
        int indice = 0;
        Sala auxSala = listaSalas.obtenerElemento(indice);
        boolean encontrado = false;
        
        while (auxSala != null && !encontrado) {
            if (auxSala.getNombre() == nombre) {
                listaSalas.eliminarElemento(auxSala);
                encontrado = true;
            } else {
                indice++;
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
            
        if (listaSalas.esVacia()) {
            return Retorno.error3();
        }
        
        int indiceActual = 0;
        Sala auxSala = listaSalas.obtenerElemento(indiceActual);
        boolean encontrado = false;

        while (auxSala != null && !encontrado) {
            
            if (auxSala.getCapacidad() >= aforoNecesario) {
                
                if (auxSala.getFechaOcupada().esVacia()) {
                    
                    auxSala.setFechaOcupada(fecha);
                    encontrado = true;
                    
                } else {
                    
                    int indiceFecha = 0;
                    LocalDate auxFechaOcupada = auxSala.getFechaOcupada().obtenerElemento(indiceFecha);
                    boolean finalizado = false;
                    
                    while (auxFechaOcupada != null && !finalizado) {
                        if (auxFechaOcupada.equals(fecha)) {
                            finalizado = true;
                        } else {
                            auxFechaOcupada = auxSala.getFechaOcupada().obtenerElemento(indiceFecha++);
                        }
                    }
                    
                    if (!finalizado) {
                        auxSala.setFechaOcupada(fecha);
                        encontrado = true;
                    } else {
                        indiceActual++;
                        auxSala = listaSalas.obtenerElemento(indiceActual);
                    }
                    
                }
                
            } else {
                indiceActual++;
                auxSala = listaSalas.obtenerElemento(indiceActual);
            }
            
        }

        if (encontrado) {
            Evento evento = new Evento(codigo, descripcion, auxSala, aforoNecesario);
            listaEventos.agregarInicio(evento);
            return Retorno.ok("Si pudo registrar el evento");
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

    // 1.6
    @Override
    public Retorno comprarEntrada(String cedula, String codigoEvento) {
        
        int indice = 0;
        Cliente cliente = listaClientes.obtenerElemento(indice);
        boolean encontrado = false;
        
        // Buscamos cliente
        while (cliente != null && !encontrado) {
            if (cliente.getCedula().equals(cedula)) {
                encontrado = true;
            } else {
                cliente = listaClientes.obtenerElemento(indice++);
            }
        }
        
        if (!encontrado) {
            return Retorno.error1();
        }
        
        indice = 0;
        Evento evento = listaEventos.obtenerElemento(indice);
        encontrado = false;
        
        // Buscamos evento
        while (evento != null && !encontrado) {
            if (evento.getCodigo().equals(codigoEvento)) {
                encontrado = true;
            } else {
                evento = listaEventos.obtenerElemento(indice++);
            }
        }
        
        if (!encontrado) {
            return Retorno.error2();
        }
        
        if (evento.getEntradasDisponibles() >= 1) {
            Entrada entrada = new Entrada(evento.getCodigo(), evento, cliente);
            evento.setListadoEntradas(entrada);
            evento.setEntradasDisponibles(evento.getEntradasDisponibles()-1);
        } else {
            evento.setListadoEsperaClientes(cliente);
        }
        
        return Retorno.ok();
    }
    
    // 1.7
    @Override
    public Retorno eliminarEvento(String codigo) {
        Evento evento = null;
        boolean terminado = false;
        for (int i = 0; i <= listaEventos.cantidadElementos() && !terminado; i++) {
            Evento e = listaEventos.obtenerElemento(i);
            if (e != null) {
                if (e.getCodigo().equals(codigo)) {
                    evento = e;
                }
            } else {
                terminado = true;
            }
        } 
        if (evento == null) {
            return Retorno.error1();
        }
        
        boolean tieneEspera = !evento.getListadoEsperaClientes().esVacia();
        boolean tieneEntradas = !evento.getListadoEntradas().esVacia();
        
        if (tieneEspera || tieneEntradas) {
            return Retorno.error2();
        }
        
        listaEventos.eliminarElemento(evento);
        return Retorno.ok("Se elimino el evento");
    }

    // 1.8
    @Override
    public Retorno devolverEntrada(String cedula, String codigoEvento) {
        Cliente cliente = null;
        boolean terminado = false;
        for (int i = 0; i <= listaClientes.cantidadElementos() && !terminado; i++) {
            Cliente c = listaClientes.obtenerElemento(i);
            if (c != null) {
                if (c.getCedula().equals(cedula)) {
                    cliente = c;
                }
            } else {
                terminado = true;
            }
        }
        if (cliente == null) {
            return Retorno.error1();
        }

        Evento evento = null;
        terminado = false;
        for (int i = 0; i <= listaEventos.cantidadElementos() && !terminado; i++) {
            Evento e = listaEventos.obtenerElemento(i);
            if (e != null) {
                if (e.getCodigo().equals(codigoEvento)) {
                    evento = e;
                } 
            } else {
                terminado = true;
            }
        }
        if (evento == null) {
            return Retorno.error2();
        }
        
        int indice = 0;
        Entrada entrada = evento.getListadoEntradas().obtenerElemento(indice);
        boolean encontrado = false;
        
        while (entrada != null && !encontrado) {
            if (entrada.getEntradaCliente().getCedula().equals(cedula)) {
                encontrado = true;

                // Siempre y cuando la lista de espera no este vacia
                if (evento.getListadoEsperaClientes().cantidadElementos() >= 1) {
                    // Asignamos entrada al nuevo cliente
                    entrada.setEntradaCliente(evento.getListadoEsperaClientes().getFin().getDato());

                    // Eliminamos cliente de la lista de espera
                    evento.getListadoEsperaClientes().eliminarElemento(cliente);
                }
                
            } else {
                indice++;
            }
        }
        
        return Retorno.ok();
    }

    // 1.9
    @Override
    public Retorno calificarEvento(String cedula, String codigoEvento, int puntaje, String comentario) {
        
        if (listaEventos.esVacia()) {
            return Retorno.error2();
        }
        
        // Validamos puntaje
        if (puntaje < 1 || puntaje > 10) {
            return Retorno.error3();
        }
        
        int indice = 0;
        Evento evento = listaEventos.obtenerElemento(indice);
        boolean encontrado = false;
        while (evento != null && !encontrado) {
            if (evento.getCodigo().equals(codigoEvento)) {
                encontrado = true;
            } else {
                evento = listaEventos.obtenerElemento(indice++);
            }
        }
        
        // Evento no existe
        if (!encontrado) {
            return Retorno.error2();
        }
        
        indice = 0;
        Entrada entrada = evento.getListadoEntradas().obtenerElemento(indice);
        encontrado = false;
        Cliente cliente = null;
        while (entrada != null && !encontrado) {
            if (entrada.getEntradaCliente().getCedula().equals(cedula)) {
                cliente = entrada.getEntradaCliente();
                encontrado = true;
            } else {
                entrada = evento.getListadoEntradas().obtenerElemento(indice++);
            }
        }
        
        // No encontro al cliente
        if (!encontrado) {
            return Retorno.error1();
        } else {
            indice = 0;
            Calificacion auxCalificacion = evento.getCalificaciones().obtenerElemento(indice);
            encontrado = false;

            while (auxCalificacion != null && !encontrado) {
                if (auxCalificacion.getCliente().getCedula().equals(cedula)) {
                    encontrado = true;
                } else {
                    auxCalificacion = evento.getCalificaciones().obtenerElemento(indice++);
                }
            }

            if (!encontrado) {
                Calificacion calificacion = new Calificacion(cliente, puntaje, comentario);
                evento.setCalificaciones(calificacion);
                return Retorno.ok();
            } else {
                return Retorno.error4();
            }
        }
        
    }
     
    // 2.1
    @Override
    public Retorno listarSalas() {
        String texto = "";

        int indice = listaSalas.cantidadElementos()-1;
        Sala sala = listaSalas.obtenerElemento(indice);
        while (sala != null) {
            texto += sala.toString() + "#";
            indice--;
            sala = listaSalas.obtenerElemento(indice);
        }
        
        texto = texto.substring(0, texto.length() - 1);
        
        Retorno r = new Retorno(Retorno.Resultado.OK);
        r.valorString = texto;
        return r;
    }

    // 2.2
    @Override
    public Retorno listarEventos() {
        
        String texto = "";
        ListaNodos<Evento> auxListaEventos = new ListaNodos<Evento>();

        int indice = 0;
        while (listaEventos.obtenerElemento(indice) != null) {
            auxListaEventos.agregarOrdenado(listaEventos.obtenerElemento(indice));
            indice++;
        }
        
        indice = 0;
        while (auxListaEventos.obtenerElemento(indice) != null) {
            texto += auxListaEventos.obtenerElemento(indice).toString() + "#";
            indice++;
        }
        
        Retorno r = new Retorno(Retorno.Resultado.OK);
        r.valorString = texto;
        return r;
    }

    // 2.3
    @Override
    public Retorno listarClientes() {
        
        String texto = "";
        ListaNodos<Cliente> auxListaClientes = new ListaNodos<Cliente>();
        
        int indice = 0;
        while (listaClientes.obtenerElemento(indice) != null) {
            auxListaClientes.agregarOrdenado(listaClientes.obtenerElemento(indice));
            indice++;
        }
        
        indice = 0;
        while (auxListaClientes.obtenerElemento(indice) != null) {
            texto += auxListaClientes.obtenerElemento(indice).toString() + "#";
            indice++;
        }
        
        texto = texto.substring(0, texto.length() - 1);
        Retorno r = new Retorno(Retorno.Resultado.OK);
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

    // 2.5
    @Override
    public Retorno listarClientesDeEvento(String codigo, int n) {
        if (n < 1) {
            return Retorno.error2();
        }

        if (listaEventos.esVacia()) {
            return Retorno.error1();
        }
        Evento evento = null;
        boolean terminado = false;
        for (int i = 0; i <= listaEventos.cantidadElementos() && !terminado; i++) {
            Evento e = listaEventos.obtenerElemento(i);
            if (e != null) {
                if (e.getCodigo().equals(codigo)) {
                    evento = e;
                }
            } else {
                terminado = true;
            }
        }
        if (evento == null) {
            return Retorno.error1();
        }

        ListaNodos<Entrada> entradas = evento.getListadoEntradas();
        
        String resultado = "";
        terminado = false;
        for (int i = 0; i < n && !terminado; i++) {
            Entrada entrada = entradas.obtenerElemento(i);
            if (entrada != null) {
                Cliente cliente = entrada.getEntradaCliente();
                String clienteStr = cliente.getCedula() + "-" + cliente.getNombre();

                if (resultado.equals("")) {
                    resultado = clienteStr;
                } else {
                    resultado = resultado + "#" + clienteStr;
                }
            } else {
                terminado = true;
            }
        }
        
        Retorno r = new Retorno(Retorno.Resultado.OK);
        r.valorString = resultado;
        return r;
    }

    // 2.6
    @Override
    public Retorno listarEsperaEvento() {
        ListaNodos<Evento> eventosConEspera = new ListaNodos<>();

        boolean terminado = false;
        for (int i = 0; i < listaEventos.cantidadElementos() && !terminado; i++) {
            Evento evento = listaEventos.obtenerElemento(i);
            if (evento != null) {
                if (!evento.getListadoEsperaClientes().esVacia()) {
                    eventosConEspera.agregarOrdenado(evento);
                }
            } else {
                terminado = true;
            }
        }
        
        String resultado = "";
        for (int i = 0; i < eventosConEspera.cantidadElementos(); i++) {
            Evento evento = eventosConEspera.obtenerElemento(i);
            resultado += evento.getCodigo() + "-";

            ListaNodos<Cliente> clientesOrdenados = new ListaNodos<>();

            ListaNodos<Cliente> espera = evento.getListadoEsperaClientes();
            for (int k = 0; k < espera.cantidadElementos(); k++) {
                clientesOrdenados.agregarOrdenado(espera.obtenerElemento(k));
            }
            for (int k = 0; k < clientesOrdenados.cantidadElementos(); k++) {
                resultado += clientesOrdenados.obtenerElemento(k).getCedula();
                if (k < clientesOrdenados.cantidadElementos() - 1) {
                    resultado += "-";
                }
            }
            resultado += "#";
        }
        resultado = resultado.substring(0, resultado.length() - 1);
        Retorno r = new Retorno(Retorno.Resultado.OK);
        r.valorString = resultado;
        return r;
    }
 
    // 2.7
    @Override
    public Retorno deshacerUltimasCompras(int n) {
        
        ListaNodos<Entrada> entradasADeshacer = new ListaNodos<>();
        
        //busco entradas activas
        for (int i = listaEventos.cantidadElementos()-1; i >= 0 && entradasADeshacer.cantidadElementos() < n; i++){
            Evento evento = listaEventos.obtenerElemento(i);
            if (evento != null) {
                ListaNodos<Entrada> entradas = evento.getListadoEntradas();
        
                for (int j = entradas.cantidadElementos() -1; j >= 0 && entradasADeshacer.cantidadElementos() < n; j++){
                    Entrada entrada = entradas.obtenerElemento(j);
                    if (entrada != null) {
                        if (entrada.estaActiva()) {
                            entradasADeshacer.agregarFinal(entrada);
                        } else {
                            break;
                        }
                    }
                }
            } else {
                break;
            }
        }
        
        // desactiva y muestra
        ListaNodos <String> descripciones = new ListaNodos<>();
        
        for (int i = 0; i < entradasADeshacer.cantidadElementos(); i++){
            Entrada entrada = entradasADeshacer.obtenerElemento(i);
            entrada.setActiva(false);

            String descripcion = entrada.getEntradaEvento().getCodigo() + "-" + entrada.getEntradaCliente().getCedula();
            descripciones.agregarOrdenado(descripcion);
        }
        String resultado ="";
        for (int i = 0; i < descripciones.cantidadElementos(); i++){
            resultado += descripciones.obtenerElemento(i) + "#";
        }
        
        resultado = resultado.substring(0, resultado.length() - 1);
        Retorno r = new Retorno(Retorno.Resultado.OK);
        r.valorString = resultado;
        return r;
    }

    public Retorno deshacerUltimasCompras2(int n) {
        
        if (listaEventos.esVacia() || n < 0) {
            return Retorno.ok();
        }
        
        int indice = 0;
        Evento evento = listaEventos.obtenerElemento(indice);
        
        String resultado = "";
        
        ListaNodos<Entrada> listadoEntradas = new ListaNodos<>();
        
        int cantVueltas = 0;
        while (evento != null) {
            
            // Verificamos si hay clientes en lista de espera. Si lo hay, deshacemos las compras de ESA lista
            if (!evento.getListadoEsperaClientes().esVacia()) {
                
                evento.getListadoEsperaClientes().eliminarInicio();
                cantVueltas++;
                
            } else { // Si no, entonces deshacemos desde la lista original
                
                evento.setEntradasDisponibles(evento.getEntradasDisponibles()+1);
                listadoEntradas.agregarInicio(evento.getListadoEntradas().getInicio().getDato());
                evento.getListadoEntradas().eliminarInicio();
                cantVueltas++;
                
            }
            
            if (cantVueltas == n) {
                cantVueltas = 0;
                indice++;
                evento = listaEventos.obtenerElemento(indice);
            }
        }
        
        ListaNodos<Entrada> listadoEntradasOrdenadas = new ListaNodos<>();
        
        int indice2 = 0;
        Entrada auxEntrada = listadoEntradas.obtenerElemento(indice2);
        while (auxEntrada != null) {
            listadoEntradasOrdenadas.agregarOrdenado(auxEntrada);
            indice2++;
            auxEntrada = listadoEntradas.obtenerElemento(indice2);
        }
        
        indice2 = 0;
        auxEntrada = listadoEntradasOrdenadas.obtenerElemento(indice2);
        while (auxEntrada != null) {
            resultado += auxEntrada + "#";
            indice2++;
            auxEntrada = listadoEntradas.obtenerElemento(indice2);
        }
        
        resultado = resultado.substring(0, resultado.length() - 1);
        Retorno r = new Retorno(Retorno.Resultado.OK);
        r.valorString = resultado;
        return r;
    }
    
    // 2.8
    @Override
    public Retorno eventoMejorPuntuado() {

        ListaNodos<Evento> eventosCalificados = new ListaNodos<>();
        double mejorPromedio = 0;

        for (int i = 0; i < listaEventos.cantidadElementos(); i++) {
            Evento e = listaEventos.obtenerElemento(i);
            ListaNodos<Calificacion> calificaciones = e.getCalificaciones();

            if (!calificaciones.esVacia()) {
                int totalPuntaje = 0;
                int cantidad = 0;

                for (int h = 0; h < calificaciones.cantidadElementos(); h++) {
                    Calificacion c = calificaciones.obtenerElemento(h);
                    totalPuntaje += c.getPuntaje();
                    cantidad++;
                }
                double promedio = (double) totalPuntaje / cantidad;
                e.setPromedioPuntaje(promedio);
                if (promedio > mejorPromedio) {
                    mejorPromedio = promedio;
                    eventosCalificados = new ListaNodos<>();
                    eventosCalificados.agregarInicio(e);
                } else if (promedio == mejorPromedio) {
                    eventosCalificados.agregarInicio(e);
                }
            }
        }

        ListaNodos <Evento> ordenados = new ListaNodos<>();
        for (int i = 0; i < eventosCalificados.cantidadElementos(); i++){
            ordenados.agregarOrdenado(eventosCalificados.obtenerElemento(i));
        }
        
        String resultado = "";
        for (int i = 0; i < ordenados.cantidadElementos(); i++) {
            Evento e = ordenados.obtenerElemento(i);
            resultado += e.getCodigo() + "-" + (e.getPromedioPuntaje());
        }
        
        Retorno r = new Retorno(Retorno.Resultado.OK);
        r.valorString = resultado;
        return r;
    }

    // 2.9
    @Override
    public Retorno comprasDeCliente(String cedula) {
        
        if (listaClientes.esVacia()) {
            return Retorno.error1();
        }
        
        Cliente cliente = new Cliente(cedula);
        if (!listaClientes.existeElemento(cliente)) {
            return Retorno.error1();
        }
        
        for (int i = 0; i <= listaClientes.cantidadElementos(); i++) {
            Cliente c = listaClientes.obtenerElemento(i);
            if (c.getCedula().equals(cedula)) {
                cliente = c;
            }
        }

        String resultado = "";

        for (int i = 0; i <= listaEventos.cantidadElementos(); i++) {
            Evento evento = listaEventos.obtenerElemento(i);
            ListaNodos<Entrada> entradas = evento.getListadoEntradas();

            if (!entradas.esVacia()) {
                for (int j = 0; j <= entradas.cantidadElementos(); j++) {
                    Entrada entrada = entradas.obtenerElemento(j);

                    if (entrada.getEntradaCliente().getCedula().equals(cedula)) {
                        String estado = "";
                        if (entrada.estaActiva()) {
                            estado = "N";
                        } else {
                            estado = "D";
                        }
                        resultado += evento.getCodigo() + "-" + estado + "#";
                    }
                }
            }
            
        }
        
        resultado = resultado.substring(0, resultado.length() - 1);
        Retorno r = new Retorno(Retorno.Resultado.OK);
        r.valorString = resultado;
        return r;
    }

    // 2.10
    @Override
    public Retorno comprasXDia(int mes) {
        return Retorno.noImplementada();
    }
    
}