package sistemaAutogestion;

import dominio.Evento;
import dominio.Sala;
import dominio.Cliente;
import dominio.Entrada;
import dominio.Calificacion;
import java.time.LocalDate;
import tads.ListaNodos;
import tads.Nodo;

public class Sistema implements IObligatorio {
    
    public ListaNodos<Evento> listaEventos;
    public ListaNodos<Cliente> listaClientes;
    public ListaNodos<Sala> listaSalas;
   
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
                auxEvento.setEntradasDisponibles(aforoNecesario);
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

    // 1.45
    public Retorno registrarEvento2(String codigo, String descripcion, int aforoNecesario, LocalDate fecha) {
        
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
                if (auxSala.getCapacidad() >= aforoNecesario && !auxSala.getFechaOcupada().esVacia()) {
                    int indiceFecha = 0;
                    while (auxSala.getFechaOcupada().obtenerElemento(indiceFecha) != null && !encontrado) {
                        if (auxSala.getFechaOcupada().obtenerElemento(indiceFecha).equals(fecha)) {
                            encontrado = true;
                        } else {
                            indiceFecha++;
                        }
                    }
                    if (!encontrado) {
                        auxSala = listaSalas.obtenerElemento(indiceActual++);
                    }
                } else {
                    auxSala = listaSalas.obtenerElemento(indiceActual++);
                }
            }

            if (encontrado) {
                auxEvento.setEntradasDisponibles(aforoNecesario);
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

    // 1.6
    @Override
    public Retorno comprarEntrada(String cedula, String codigoEvento) {
        
        Cliente cliente = null;
        
        for (int i = 0; i <= listaClientes.cantidadElementos(); i++) {
            Cliente c = listaClientes.obtenerElemento(i);
            if (c.getCedula().equals(cedula)) {
                cliente = c;
            }
        }
        if (cliente == null) {
            return Retorno.error1();
        }

        Evento evento = null;
        for (int i = 0; i <= listaEventos.cantidadElementos(); i++) {
            Evento e = listaEventos.obtenerElemento(i);
            if (e.getCodigo().equals(codigoEvento)) {
                evento = e;
            }
        }
        if (evento == null) {
            return Retorno.error2();
        }

        //verifico si ya tiene entrada
        ListaNodos<Entrada> entradas = evento.getListadoEntradas();
        for (int i = 0; i <= entradas.cantidadElementos(); i++) {
            Entrada en = entradas.obtenerElemento(i);
            if (en.getEntradaCliente().getCedula().equals(cedula)) {
                return Retorno.error3();
            }
        }

        // hay lugar?
        if (evento.getEntradasVendidas() < evento.getSala().getCapacidad()) {
            String codigoEntrada = "E" + (evento.getEntradasVendidas() + 1);
            Entrada nuevaEntrada = new Entrada(codigoEntrada, evento, cliente);
            evento.getListadoEntradas().agregarInicio(nuevaEntrada);
            evento.setEntradasVendidas(evento.getEntradasVendidas() + 1);
            evento.setEntradasDisponibles(evento.getSala().getCapacidad() - evento.getEntradasVendidas());
            return Retorno.ok("Entrada comprada con exito");
        } else {
            ListaNodos<Cliente> espera = evento.getListadoEsperaClientes();
            for (int i = 0; i <= espera.cantidadElementos(); i++) {
                Cliente c = espera.obtenerElemento(i);
                if (c.getCedula().equals(cedula)) {
                    return Retorno.error4();
                }
            }
            espera.agregarInicio(cliente);
            return Retorno.ok("Agregado a lista de espera");
        }
    }
    
    public Retorno comprarEntrada2(String cedula, String codigoEvento) {
        
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
        for (int i = 0; i <= listaEventos.cantidadElementos(); i++) {
            Evento e = listaEventos.obtenerElemento(i);
            if (e.getCodigo().equals(codigo)) {
                evento = e;
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
        for (int i = 0; i <= listaClientes.cantidadElementos(); i++) {
            Cliente c = listaClientes.obtenerElemento(i);
            if (c.getCedula().equals(cedula)) {
                cliente = c;
            }
        }
        if (cliente == null) {
            return Retorno.error1();
        }

        Evento evento = null;
        for (int i = 0; i <= listaEventos.cantidadElementos(); i++) {
            Evento e = listaEventos.obtenerElemento(i);
            if (e.getCodigo().equals(codigoEvento)) {
                evento = e;
            }
        }
        if (evento == null) {
            return Retorno.error2();
        }

        //El cliente tiene entrada?
        ListaNodos<Entrada> entradas = evento.getListadoEntradas();
        Entrada entradaADevolver = null;

        for (int i = 0; i <= entradas.cantidadElementos(); i++) {
            Entrada entrada = entradas.obtenerElemento(i);
            if (entrada.getEntradaCliente().getCedula().equals(cedula)&& entrada.estaActiva()) {
                entradaADevolver = entrada;
                
            }
        }

        if (entradaADevolver == null) {
            return Retorno.error3();
        }

     
       entradaADevolver.setActiva(false);
       
       

        evento.setEntradasVendidas(evento.getEntradasVendidas() - 1);
        evento.setEntradasDisponibles(evento.getEntradasDisponibles() + 1);

        // ahora voy a revisar si hay cliente en lista de espera, si hay le asigno la entrada que se devolvio
        ListaNodos<Cliente> espera = evento.getListadoEsperaClientes();
        if (!espera.esVacia()) {
            Cliente clienteEnEspera = espera.obtenerElemento(0);
            espera.eliminarElemento(clienteEnEspera);

            String nuevoCodigoEntrada = "E" + (evento.getEntradasVendidas() + 1);
            Entrada nuevaEntrada = new Entrada(nuevoCodigoEntrada, evento, clienteEnEspera);
            evento.getListadoEntradas().agregarInicio(nuevaEntrada);
            nuevaEntrada.setActiva(true);
            entradas.agregarFinal(nuevaEntrada);
            
            evento.setEntradasVendidas(evento.getEntradasVendidas() + 1);
            evento.setEntradasDisponibles(evento.getEntradasDisponibles() - 1);
        }
        return Retorno.ok("Entrada devuelta correctamente");
    }

    public Retorno devolverEntrada2(String cedula, String codigoEvento) {
        Cliente cliente = null;
        for (int i = 0; i <= listaClientes.cantidadElementos(); i++) {
            Cliente c = listaClientes.obtenerElemento(i);
            if (c.getCedula().equals(cedula)) {
                cliente = c;
            }
        }
        if (cliente == null) {
            return Retorno.error1();
        }

        Evento evento = null;
        for (int i = 0; i <= listaEventos.cantidadElementos(); i++) {
            Evento e = listaEventos.obtenerElemento(i);
            if (e.getCodigo().equals(codigoEvento)) {
                evento = e;
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
        
        if (puntaje < 1 || puntaje > 10) {
            return Retorno.error3();
        }
        
        Cliente cliente = null;
        for (int i = 0; i <= listaClientes.cantidadElementos(); i++) {
            Cliente c = listaClientes.obtenerElemento(i);
            if (c.getCedula().equals(cedula)) {
                cliente = c;
            }
        }
        if (cliente == null) {
            return Retorno.error1();
        }
        
        Evento evento = null;
        for (int i = 0; i <= listaEventos.cantidadElementos(); i++) {
            Evento e = listaEventos.obtenerElemento(i);
            if (e.getCodigo().equals(codigoEvento)) {
                evento = e;
            }
        } 
        if (evento == null) {
            return Retorno.error1();
        }
        
        // tengo que ver si el cliente ya califico el evento
        
        for (int i =0; i <= evento.getCalificaciones().cantidadElementos(); i++) {
            Calificacion cal = evento.getCalificaciones().obtenerElemento(i);
            if (cal.getCliente().getCedula().equals(cedula)) {
                return Retorno.error4();
            }
        }
        
        Calificacion nueva = new Calificacion(cliente, puntaje, comentario);
        evento.getCalificaciones().agregarInicio(nueva);
        return Retorno.ok("Se califico el evento");
    }
    
    public Retorno calificarEvento2(String cedula, String codigoEvento, int puntaje, String comentario) {
        
        // Validamos puntaje
        if (puntaje < 1 || puntaje > 10) {
            return Retorno.error3();
        }
        
        int indice = 0;
        Evento evento = listaEventos.obtenerElemento(indice);
        boolean encontrado = false;
        if (evento != null && !encontrado) {
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
        Retorno r = new Retorno(Retorno.Resultado.OK);
        
        String texto = "";
        for (int i = listaSalas.cantidadElementos(); i >= 0; i--) {
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
        
        String texto = "";
        ListaNodos<Evento> auxListaEventos = new ListaNodos<Evento>();

        for (int i = 0; i <= listaEventos.cantidadElementos(); i++) {
            Evento auxEvento = listaEventos.obtenerElemento(i);
            auxListaEventos.agregarOrdenado(auxEvento);
        }
        
        for (int i = 0; i <= auxListaEventos.cantidadElementos(); i++) {
            Evento auxEvento = auxListaEventos.obtenerElemento(i);
            texto += auxEvento.toString() + "#";
        }
        
//        int indice = 0;
//        while (listaEventos.obtenerElemento(indice) != null) {
//            auxListaEventos.agregarOrdenado(listaEventos.obtenerElemento(indice));
//            indice++;
//        }
//        
//        indice = 0;
//        while (auxListaEventos.obtenerElemento(indice) != null) {
//            texto += auxListaEventos.obtenerElemento(indice).toString() + "#";
//            indice++;
//        }
        
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

    // 2.5
    @Override
    public Retorno listarClientesDeEvento(String codigo, int n) {
        if (n < 1) {
            return Retorno.error2();
        }

        Evento evento = null;
        for (int i = 0; i <= listaEventos.cantidadElementos(); i++) {
            Evento e = listaEventos.obtenerElemento(i);
            if (e.getCodigo().equals(codigo)) {
                evento = e;
            }
        }
        if (evento == null) {
            return Retorno.error1();
        }

        ListaNodos<Entrada> entradas = evento.getListadoEntradas();
        int cantidadEntradas = entradas.cantidadElementos();

        if (cantidadEntradas == 0) {
            return Retorno.ok("");
        }

        int inicio = cantidadEntradas - n;
        if (inicio < 0) {
            inicio = 0;
        }

        String resultado = "";
        for (int i = inicio; i < cantidadEntradas; i++) {
            Entrada entrada = entradas.obtenerElemento(i);
            Cliente cliente = entrada.getEntradaCliente();
            String clienteStr = cliente.getCedula() + "-" + cliente.getNombre();

            if (resultado.equals("")) {
                resultado = clienteStr;
            } else {
                resultado = resultado + "#" + clienteStr;
            }

        }
        return Retorno.ok(resultado);
    }

    // 2.6
    @Override
    public Retorno listarEsperaEvento() {
        ListaNodos<Evento> eventosConEspera = new ListaNodos<>();

        for (int i = 0; i < listaEventos.cantidadElementos(); i++) {
            Evento evento = listaEventos.obtenerElemento(i);
            if (!evento.getListadoEsperaClientes().esVacia()) {
                eventosConEspera.agregarOrdenado(evento);
            }
        }
        if (eventosConEspera.esVacia()) {
            return Retorno.error1();
        }
        String resultado = "";
        for (int i = 0; i < eventosConEspera.cantidadElementos(); i++) {
            Evento evento = eventosConEspera.obtenerElemento(i);
            resultado += evento.getCodigo() + "#";

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
        }
            Retorno r = new Retorno(Retorno.Resultado.OK);
            r.valorString = resultado;
            return r;
        }

 
    // 2.7
    @Override
    public Retorno deshacerUtimasCompras(int n) {
        if (n <=0) return Retorno.error1();
        
        ListaNodos<Entrada> entradasADeshacer = new ListaNodos<>();
        
        //busco entradas activas
        for (int i = listaEventos.cantidadElementos()-1; i>= 0 && entradasADeshacer.cantidadElementos() < n; i++){
            Evento evento = listaEventos.obtenerElemento(i);
            ListaNodos<Entrada> entradas = evento.getListadoEntradas();
        
            for (int j = entradas.cantidadElementos() -1; j >= 0 &&entradasADeshacer.cantidadElementos() < n; j++){
                Entrada entrada = entradas.obtenerElemento(j);
                if (entrada.estaActiva()) {
                    entradasADeshacer.agregarFinal(entrada);
                }
            }
        }
        
        if (entradasADeshacer.esVacia()){
            return Retorno.error2();
        }
        
        // desactiva y muestra
        ListaNodos <String> descripciones = new ListaNodos<>();
        
        for (int i =0; i < entradasADeshacer.cantidadElementos(); i++){
            Entrada entrada = entradasADeshacer.obtenerElemento(i);
            entrada.setActiva(false);

            String descripcion = entrada.getEntradaEvento().getCodigo() + "-" + entrada.getEntradaCliente().getCedula();
            descripciones.agregarOrdenado(descripcion);
        }
        String resultado ="";
        for (int i =0; i < descripciones.cantidadElementos(); i++){
            resultado += descripciones.obtenerElemento(i) + "#";
        }
        return Retorno.ok(resultado);
    }

    // 2.8
    @Override
    public Retorno eventoMejorPuntuado() {
        if (listaEventos.esVacia()) {
            return Retorno.error1();
        }

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
        if (eventosCalificados.esVacia()) {
            return Retorno.error1();
        }
        
        ListaNodos <Evento> ordenados = new ListaNodos<>();
        for (int i =0; i < eventosCalificados.cantidadElementos(); i++){
            ordenados.agregarOrdenado(eventosCalificados.obtenerElemento(i));
        }
        
        String resultado ="";
        for (int i =0; i < ordenados.cantidadElementos(); i++) {
            Evento e = ordenados.obtenerElemento(i);
            resultado += e.getCodigo() + "(" + (e.getPromedioPuntaje()) + ")";
        }
        Retorno r = new Retorno(Retorno.Resultado.OK);
        r.valorString = resultado;
        return r;
    }

    // 2.9
    @Override
    public Retorno comprasDeCliente(String cedula) {
        Cliente cliente = null;
        for (int i = 0; i <= listaClientes.cantidadElementos(); i++) {
            Cliente c = listaClientes.obtenerElemento(i);
            if (c.getCedula().equals(cedula)) {
                cliente = c;
            }
        }
        if (cliente == null) {
            return Retorno.error1();
        }

        String resultado = "";

        for (int i = 0; i < listaEventos.cantidadElementos(); i++) {
            Evento evento = listaEventos.obtenerElemento(i);
            ListaNodos<Entrada> entradas = evento.getListadoEntradas();

            for (int j = 0; j < entradas.cantidadElementos(); j++) {
                Entrada entrada = entradas.obtenerElemento(j);

                if (entrada.getEntradaCliente().getCedula().equals(cedula)) {
                    String estado = "";
                    if (entrada.estaActiva()) {
                        estado = "No devuelta";
                    } else {
                        estado = "Devuelta";
                    }
                    resultado += evento.getCodigo() + "-" + estado + "#";
                }
            }
        }
        return Retorno.ok(resultado);
    }

    // 2.10
    @Override
    public Retorno comprasXDia(int mes) {
        return Retorno.noImplementada();
    }
    
}