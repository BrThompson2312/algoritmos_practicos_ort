package sistemaAutogestion;

import java.time.LocalDate;

public interface IObligatorio {

    /*
    **************** REGISTROS y ELIMINACIÓN **************************************
     */
    
    public Retorno crearSistemaDeGestion();

    public Retorno registrarSala(String nombre, int capacidad);

    public Retorno eliminarSala(String nombre);

    public Retorno registrarEvento(String codigo, String descripcion, int aforoNecesario,
            LocalDate fecha);

    public Retorno registrarCliente(String cedula, String nombre);

    public Retorno comprarEntrada(String cedula, String codigoEvento);

    public Retorno eliminarEvento(String codigo);

    public Retorno devolverEntrada(String cedula, String codigoEvento);

    public Retorno calificarEvento(String cedula, String codigoEvento, int puntaje, String comentario);

   
    /*
    **************** REPORTES Y CONSULTAS **************************************
     */
    
    public Retorno listarSalas();
    
   public Retorno listarEventos();

   public Retorno listarClientes();
   
   public Retorno esSalaOptima(String vistaSala[][]);
   
   public Retorno listarClientesDeEvento(String código, int n);
   
   public Retorno listarEsperaEvento();
   
   public Retorno deshacerUtimasCompras(int n);

   public Retorno eventoMejorPuntuado();
 
   public Retorno comprasDeCliente(String cedula);
   
   public Retorno comprasXDia(int mes);
}