package sistemaAutogestion;

import dominio.Sala;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IObligatorioTest {

    private Sistema miSistema;

    //---------------------------- Obligatorio 1 ----------------------------

    public IObligatorioTest() {
        miSistema = new Sistema();
    }

    @Before
    public void setUp() {
        miSistema = new Sistema();
        miSistema.crearSistemaDeGestion();
    }

    //------- 1.1 -------
    @Test
    public void testCrearSistemaDeGestion() {
        Retorno ret = miSistema.crearSistemaDeGestion();
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }

    //------- 1.2 -------
    @Test
    public void testRegistrarSala() {
        Retorno ret;
        
        ret = miSistema.registrarSala("Sala A", 50);
        assertEquals(Retorno.Resultado.OK, ret.resultado);

 	ret = miSistema.registrarSala("Sala B", 10);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }
    
    @Test
    public void testRegistrarSala_ERROR1() {
        Retorno ret;
        
        ret = miSistema.registrarSala("Sala A", 50);
        assertEquals(Retorno.Resultado.OK, ret.resultado);

        ret = miSistema.registrarSala("Sala A", 10);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }
    
    @Test
    public void testRegistrarSala_ERROR2() {
        Retorno ret;
        
        ret = miSistema.registrarSala("Sala A", 0);
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
    }
    
    //------- 1.3 -------
    @Test
    public void testEliminarSala() {
        Retorno ret;
        
        ret  = miSistema.registrarSala("Sala A", 120);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        
        ret  = miSistema.registrarSala("Sala B", 60);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        
        ret  = miSistema.registrarSala("Sala C", 30);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        
        ret  = miSistema.eliminarSala("Sala A");
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }
    
    @Test
    public void testEliminarSala_ERROR1() {
        Retorno ret;
        
        ret  = miSistema.registrarSala("Sala A", 120);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        
        ret  = miSistema.eliminarSala("Sala D");
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }
    
    //------- 1.4 -------
    @Test
    public void testRegistrarEvento() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        miSistema.registrarSala("Sala A", 120);
        miSistema.registrarSala("Sala B", 120);

        ret = miSistema.registrarEvento("EVT01", "Concierto", 80, fecha);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        
        ret = miSistema.registrarEvento("EVT02", "Concierto2", 120, fecha);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }
    
    @Test
    public void testRegistrarEvento_ERROR1() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        miSistema.registrarSala("Sala A", 120);
        miSistema.registrarSala("Sala B", 120);
        
        ret = miSistema.registrarEvento("EVT01", "Concierto", 80, fecha);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        
        ret = miSistema.registrarEvento("EVT01", "Concierto2", 120, fecha);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }
    
    @Test
    public void testRegistrarEvento_ERROR2() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        miSistema.registrarSala("Sala A", 120);

        ret = miSistema.registrarEvento("EVT01", "Concierto", 0, fecha);
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
    }
    
    @Test
    public void testRegistrarEvento_ERROR3() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        miSistema.registrarSala("Sala A", 80);
        miSistema.registrarSala("Sala B", 120);

        ret = miSistema.registrarEvento("EVT01", "Concierto", 80, fecha);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        
        ret = miSistema.registrarEvento("EVT02", "Concierto2", 100, fecha);
        assertEquals(Retorno.Resultado.ERROR_3, ret.resultado);
    }
    
    //------- 1.5 -------
    @Test
    public void testRegistrarCliente() {
        Retorno ret = miSistema.registrarCliente("12345678", "Juan Pérez");
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }
    
    @Test
    public void testRegistrarCliente_ERROR1() {
        Retorno ret = miSistema.registrarCliente("1234", "No funciona");
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }
    
    @Test
    public void testRegistrarCliente_ERROR2() {
        Retorno ret;
        
        ret = miSistema.registrarCliente("12345678", "Juan Pérez");
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        
        ret = miSistema.registrarCliente("12345678", "No funciona");
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
    }

    //------- 2.1 -------
    @Test
    public void testListarSalas() {
        miSistema.registrarSala("Sala A", 50);
        miSistema.registrarSala("Sala B", 70);
        miSistema.registrarSala("Sala C", 100);
        Retorno ret = miSistema.listarSalas();
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("Sala A-50#Sala B-70#Sala C-100", ret.valorString);
    }

    //------- 2.2 -------
    @Test
    public void testListarEventos() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);

        miSistema.registrarSala("Sala A", 100);
        miSistema.registrarSala("Sala B", 70);
        
        miSistema.registrarEvento("CUC22", "Tango", 90, fecha);
        miSistema.registrarEvento("EVT01", "Concierto", 70, fecha);
        
        ret = miSistema.listarEventos();
    	assertEquals("CUC22-Tango-Sala A-100-90-0#EVT01-Concierto-Sala B-70-70-0", ret.valorString);
    }

    //------- 2.3 -------
    @Test
    public void testListarClientes() {
        miSistema.registrarCliente("45678992", "Micaela Ferrez");
    	miSistema.registrarCliente("23331111", "Martina Rodríguez");
    	miSistema.registrarCliente("35679992", "Ramiro Perez");

    	Retorno ret = miSistema.listarClientes();
    	assertEquals("23331111-Martina Rodríguez#35679992-Ramiro Perez#45678992-Micaela Ferrez", ret.valorString);
    }

    //------- 2.4 -------
    @Test
    public void testSalaOptima_EsOptima() {
        String[][] vistaSala = {
            {"#", "#", "#", "#", "#", "#", "#"},
            {"#", "#", "O", "X", "X", "X", "#"},
            {"#", "O", "O", "X", "X", "X", "#"},
            {"#", "O", "O", "O", "O", "X", "#"},
            {"#", "O", "O", "X", "O", "O", "#"},
            {"#", "O", "O", "O", "O", "O", "#"},
            {"#", "X", "X", "O", "X", "O", "O"},
            {"#", "X", "X", "O", "X", "O", "X"},
            {"#", "X", "X", "O", "X", "X", "#"},
            {"#", "X", "X", "X", "X", "X", "#"},
            {"#", "#", "#", "X", "#", "#", "#"},
            {"#", "#", "#", "X", "#", "#", "#"}
        };

	Retorno ret = miSistema.esSalaOptima(vistaSala);
    	assertEquals(Retorno.Resultado.OK, ret.resultado);
    	assertEquals("Es óptimo", ret.valorString);
    }
    
    @Test
    public void testSalaOptima_NoEsOptima() {
        String[][] vistaSala = {
            {"#", "#", "#", "#"},
            {"#", "O", "O", "#"},
            {"#", "X", "O", "#"},
            {"#", "O", "X", "#"}
        };

	Retorno ret = miSistema.esSalaOptima(vistaSala);
    	assertEquals(Retorno.Resultado.OK, ret.resultado);
    	assertEquals("No es óptimo", ret.valorString);
    }
    
    //---------------------------- Obligatorio 2 ----------------------------
    
    //------- 1.6 -------
    @Test
    public void testComprarEntrada() {
        // Valores Default
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);       

        /* Creamos sala, cliente y evento para la compra de la entrada */
        miSistema.registrarSala("Sala A", 1);

        miSistema.registrarCliente("11112222", "Bruno");

        miSistema.registrarEvento("4289", "Tango", 1, fecha);

        /* EN CASO de que hayan entradas disponibles */
        ret = miSistema.comprarEntrada("11112222", "4289");
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }
    
    @Test
    public void testComprarEntrada_ERROR1() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);       

        miSistema.registrarSala("Sala A", 1);

        miSistema.registrarCliente("11112222", "Bruno");

        miSistema.registrarEvento("4289", "Tango", 1, fecha);

        /* No existe cliente */
        ret = miSistema.comprarEntrada("22224444", "4289");
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }
    
    @Test
    public void testComprarEntrada_ERROR2() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        miSistema.registrarSala("Sala A", 1);    
        
        miSistema.registrarCliente("33332222", "Juan");
        
        miSistema.registrarEvento("4290", "Tango", 1, fecha);

        /* ERROR 2 : Evento no existe */
        ret = miSistema.comprarEntrada("33332222", "1111");
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
    }
    
    //------- 1.7 -------
    @Test
    public void testEliminarEvento() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        miSistema.registrarSala("Sala A", 1);   

        miSistema.registrarCliente("54936798", "Bruno");

        miSistema.registrarEvento("4289", "Tango", 1, fecha);
        
        /* Eliminamos evento SI NO HAY entradas vendidas */
        ret = miSistema.eliminarEvento("4289");
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }
    
    @Test
    public void testEliminarEvento_ERROR1() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        miSistema.registrarSala("Sala A", 1);

        miSistema.registrarCliente("22223333", "Bruno");
        
        miSistema.registrarEvento("4290", "Tango", 1, fecha);
        
        miSistema.comprarEntrada("22223333", "4290");

        /* ERROR 1 : No existe el evento */
        ret = miSistema.eliminarEvento("1111");
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }
    
    @Test
    public void testEliminarEvento_ERROR2() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        miSistema.registrarSala("Sala A", 1);

        miSistema.registrarCliente("11112222", "Bruno");
        
        miSistema.registrarEvento("1234", "Bienvenidos", 1, fecha);
        
        miSistema.comprarEntrada("11112222", "1234");

        /* ERROR 2 : El evento tiene entradas vendidas */
        ret = miSistema.eliminarEvento("1234");
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
    }
    
    //------- 1.8 -------
    @Test
    public void testDevolverEntrada() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        miSistema.registrarSala("Sala A", 1);

        miSistema.registrarCliente("11112222", "Bruno");

        miSistema.registrarEvento("4289", "Tango", 1, fecha);
        
        miSistema.comprarEntrada("11112222", "4289");
        
        /* Devolver entrada */
        ret = miSistema.devolverEntrada("11112222", "4289");
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        
        /* 
            Nota: Esto internamente debería devolver la entrada Y asignarla automaticamente a otro cliente 
        */
    }
    
    @Test
    public void testDevolverEntrada_ERROR1() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        miSistema.registrarSala("Sala A", 1);
        
        miSistema.registrarCliente("11112222", "Bruno");

        miSistema.registrarEvento("4289", "Tango", 1, fecha);
        
        miSistema.comprarEntrada("11112222", "4289");

        /* ERROR 1 : Cliente no existe */
        ret = miSistema.devolverEntrada("22224444", "4289");
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }
    
    @Test
    public void testDevolverEntrada_ERROR2() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        miSistema.registrarSala("Sala A", 1);

        miSistema.registrarCliente("11112222", "Bruno");
        
        miSistema.registrarEvento("4290", "Tango", 1, fecha);
        
        miSistema.comprarEntrada("11112222", "4289");

        /* ERROR 2 : Evento no existe */
        ret = miSistema.devolverEntrada("11112222", "2222");
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
    }
    
    //------- 1.9 -------
    @Test
    public void testCalificarEvento() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        miSistema.registrarSala("Sala A", 1);

        miSistema.registrarCliente("11112222", "Bruno");

        miSistema.registrarEvento("4289", "Tango", 1, fecha);
        
        miSistema.comprarEntrada("11112222", "4289");

        /* Calificar evento */
        ret = miSistema.calificarEvento("11112222", "4289", 5, "El peor evento que vivi en mi vida");
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }
    
    @Test
    public void testCalificarEvento_ERROR1() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        miSistema.registrarSala("Sala A", 1);
        
        miSistema.registrarCliente("11112222", "Bruno");

        miSistema.registrarEvento("4289", "Tango", 1, fecha);
        
        miSistema.comprarEntrada("11112222", "4289");
        
        // ERROR 1 : Cliente no existe
        ret = miSistema.calificarEvento("22224444", "4289", 5, "El peor evento que vivi en mi vida");
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }
    
    @Test
    public void testCalificarEvento_ERROR2() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        miSistema.registrarSala("Sala A", 1);

        miSistema.registrarCliente("11112222", "Bruno");
        
        miSistema.registrarEvento("4290", "Tango", 1, fecha);
        
        miSistema.comprarEntrada("11112222", "4290");

        /* ERROR 2 : Evento no existe */
        ret = miSistema.calificarEvento("11112222", "1111", 5, "No deberia de suceder");
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
    }
    
    @Test
    public void testCalificarEvento_ERROR3() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        miSistema.registrarSala("Sala A", 1);

        miSistema.registrarCliente("11112222", "Bruno");

        miSistema.registrarEvento("4289", "Tango", 1, fecha);
        
        miSistema.comprarEntrada("11112222", "4289");
        
        /* ERROR 3 : Puntate < 1 o puntaje > 10 */
        ret = miSistema.calificarEvento("11112222", "4289", 0, "No deberia de suceder");
        assertEquals(Retorno.Resultado.ERROR_3, ret.resultado);
    }
    
    @Test
    public void testCalificarEvento_ERROR4() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        miSistema.registrarSala("Sala A", 1);

        miSistema.registrarCliente("11112222", "Bruno");

        miSistema.registrarEvento("4289", "Tango", 1, fecha);
        
        miSistema.comprarEntrada("11112222", "4289");
        
        miSistema.calificarEvento("11112222", "4289", 2, "El peor evento que vivi en mi vida");
        
        /* ERROR 4 : El evento ya fue calificado por el cliente */
        ret = miSistema.calificarEvento("11112222", "4289", 3, "No deberia de suceder");
        assertEquals(Retorno.Resultado.ERROR_4, ret.resultado);
    }
    
    //------- 2.5 -------
    @Test
    public void testListarClientesDeEvento() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        miSistema.registrarSala("Sala A", 3);

        // Clientes de prueba
        miSistema.registrarCliente("12341234", "c1");
        miSistema.registrarCliente("12341235", "c2");
        miSistema.registrarCliente("12341236", "c3");

        miSistema.registrarEvento("4289", "Tango", 3, fecha);
        
        miSistema.comprarEntrada("12341234", "4289");
        miSistema.comprarEntrada("12341235", "4289");
        miSistema.comprarEntrada("12341236", "4289");
        
        /* Deberia de listar los hasta N ultimos clientes que compraron entradas para el evento */
        /* Si hay menos de N clientes, entonces se listan todos */
        ret = miSistema.listarClientesDeEvento("4289", 2);
        assertEquals("12341236-c3#12341235-c2", ret.valorString);
    }
    
    @Test
    public void testListarClientesDeEvento_ERROR1() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        miSistema.registrarSala("Sala A", 3);

        // Clientes de prueba
        miSistema.registrarCliente("12341234", "c1");
        miSistema.registrarCliente("12341235", "c2");
        miSistema.registrarCliente("12341236", "c3");

        miSistema.registrarEvento("4289", "Tango", 3, fecha);
        
        miSistema.comprarEntrada("12341234", "4289");
        miSistema.comprarEntrada("12341235", "4289");
        miSistema.comprarEntrada("12341236", "4289");
        
        /* ERROR 1 : No existe evento */
        ret = miSistema.listarClientesDeEvento("1111", 2);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }
    
    @Test
    public void testListarClientesDeEvento_ERROR2() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        miSistema.registrarSala("Sala A", 3);

        // Clientes de prueba
        miSistema.registrarCliente("12341234", "c1");
        miSistema.registrarCliente("12341235", "c2");
        miSistema.registrarCliente("12341236", "c3");

        miSistema.registrarEvento("4289", "Tango", 3, fecha);
        
        miSistema.comprarEntrada("12341234", "4289");
        miSistema.comprarEntrada("12341235", "4289");
        miSistema.comprarEntrada("12341236", "4289");
        
        /* ERROR 1 : No existe evento */
        ret = miSistema.listarClientesDeEvento("4289", 0);
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
    }
    
    //------- 2.6 -------
    @Test
    public void testListaDeEsperaPorEvento() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        /* Creamos sala y cliente para el registro de un evento */
        miSistema.registrarSala("Sala A", 2);        
        miSistema.registrarSala("Sala B", 2);

        // Clientes de prueba
        miSistema.registrarCliente("12341234", "c1");
        miSistema.registrarCliente("12341235", "c2");
        miSistema.registrarCliente("12341236", "c3");
        miSistema.registrarCliente("12341237", "c4");
        miSistema.registrarCliente("12341238", "c5");
        miSistema.registrarCliente("12341239", "c6");

        miSistema.registrarEvento("1234", "Bienvenidos", 2, fecha);
        miSistema.registrarEvento("1235", "Bienvenidos", 2, fecha);
        
        // Evento 1
        miSistema.comprarEntrada("12341234", "1234");
        miSistema.comprarEntrada("12341235", "1234");
        
        // Evento 2
        miSistema.comprarEntrada("12341236", "1235");
        miSistema.comprarEntrada("12341237", "1235");
        
        // Clientes en espera
        miSistema.comprarEntrada("12341238", "1234");
        miSistema.comprarEntrada("12341239", "1235");

        /* Eventos deberian estar ordenados en forma alfabética */        
        /* Clientes ordenados por cedula dentro del mismo evento */         
        /* Deberian de listarse los eventos SOLAMENTE con clientes EN ESPERA */ 
        ret = miSistema.listarEsperaEvento();
        assertEquals("1234-12341238#1235-12341239", ret.valorString);
        
        /* 
            Formato:
            KAK34-2333111#KAK34-45678992
        */
    }
    
    //------- 2.7 -------
    @Test
    public void testDeshacerComprasDeEntradas() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);        

        miSistema.registrarSala("Sala A", 3);        
        miSistema.registrarSala("Sala B", 3);

        miSistema.registrarCliente("12341234", "c1");
        miSistema.registrarCliente("52323214", "c2");
        miSistema.registrarCliente("12341235", "c3");
        miSistema.registrarCliente("12341365", "c4");
        miSistema.registrarCliente("12341231", "c5");
        miSistema.registrarCliente("76532456", "c6");

        miSistema.registrarEvento("1234", "Bienvenidos", 3, fecha);
        miSistema.registrarEvento("12345", "Bienvenidos", 3, fecha);

        // No
        miSistema.comprarEntrada("12341234", "1234");   
        
        // Entrada que deberia deshacerse
        miSistema.comprarEntrada("12341235", "1234");
        
        // Entrada que deberia deshacerse
        miSistema.comprarEntrada("12341365", "1234");   
        
        // No
        miSistema.comprarEntrada("12341231", "12345");
        
        // Entrada que deberia deshacerse
        miSistema.comprarEntrada("52323214", "12345");
        
        // Entrada que deberia deshacerse
        miSistema.comprarEntrada("76532456", "12345");
        
        /* Nota: debería de deshacer las ultimas compras de TODOS los eventos */
        ret = miSistema.deshacerUltimasCompras2(2);
        assertEquals("1234-12341235#1234-12341365#12345-52323214#12345-76532456", ret.valorString);
        
        /* 
            Nota:
            1234-12341235#1234-12341365#12345-52323214#12345-76532456
        */
    }
    
    //------- 2.8 -------
    @Test
    public void testEventoMejorPuntuado() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        miSistema.registrarSala("Sala A", 3);        
        miSistema.registrarSala("Sala B", 3);
        
        // Clientes de prueba
        miSistema.registrarCliente("11112222", "c2");
        miSistema.registrarCliente("33334444", "c3");

        miSistema.registrarEvento("1111", "Bienvenidos", 3, fecha);
        miSistema.registrarEvento("2222", "Bienvenidos", 3, fecha);
        
        miSistema.comprarEntrada("11112222", "1111");
        miSistema.comprarEntrada("33334444", "2222");
        
        miSistema.calificarEvento("11112222", "1111", 8, "Buen evento");
        miSistema.calificarEvento("33334444", "2222", 9, "El mejor evento que vivi en mi vida");

        ret = miSistema.eventoMejorPuntuado();
        assertEquals("2222-9.0", ret.valorString);
        
        /* 
            Nota: Debería de mostrar al evento mejor puntuado (SI hay uno solo).
            Si hay mas, entonces debe mostrar todos ordenados por codigo de evento indicando el puntaje
            
            Ejemplo para puntaje promedio 9:
                KAK34-9#TEC49-9
        */
    }
    
    //------- 2.9 -------
    @Test
    public void testComprasDeCliente() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        miSistema.registrarSala("Sala A", 30);        

        miSistema.registrarCliente("11112222", "Bruno");
        miSistema.registrarCliente("11114444", "Hernan");

        miSistema.registrarEvento("4289", "Tango", 30, fecha);
        
        miSistema.comprarEntrada("11112222", "4289");
        miSistema.comprarEntrada("11114444", "4289");
        
        // Devolver entrada
        ret = miSistema.devolverEntrada("11114444", "4289");
        assertEquals(Retorno.Resultado.OK, ret.resultado);

        ret = miSistema.comprasDeCliente("11112222");
        assertEquals("4289-N", ret.valorString);
        
        ret = miSistema.comprasDeCliente("11114444");
        assertEquals("4289-D", ret.valorString);
        
        /* 
            Nota: Debería de mostrar las compras realizadas por el cliente
            (D) si fue devuelta. (N) si NO fue devuelta.
        
            Formato:
            TEC43-N#CUC11-N#COP10-D
        */
    }
    
    @Test
    public void testComprasDeCliente_ERROR1() {
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        miSistema.registrarSala("Sala A", 30);        

        miSistema.registrarCliente("11112222", "Bruno");
        miSistema.registrarEvento("4289", "Tango", 30, fecha);
        
        miSistema.comprarEntrada("11112222", "4289");
        
        ret = miSistema.comprasDeCliente("11114444");
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }
    
    //------- 2.10 -------
    @Test
    public void testCantidadDeComprasPorDia() {
        // Valores Default
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        /* Creamos sala y cliente para el registro de un evento */
        miSistema.registrarSala("Sala A", 30);        

        miSistema.registrarCliente("54936798", "Bruno");
        miSistema.registrarEvento("4289", "Bienvenidos", 30, fecha);
        
        ret = miSistema.comprasXDia(2 /*Mes*/);
        assertEquals(Retorno.Resultado.OK, ret.resultado);

        /* 
            Nota: Debería de mostrar la cantidad de compras que se realizo en el mes mencionado
            
            Formato: 
            1-10#2-6#4-67#7-5#28-6
        */
    }
    
    @Test
    public void testCantidadDeComprasPorDia_ERROR1() {
        // Valores Default
        Retorno ret;
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        
        /* Creamos sala y cliente para el registro de un evento */
        ret = miSistema.registrarSala("Tango", 30);
        assertEquals(Retorno.Resultado.OK, ret.resultado);

        ret = miSistema.registrarCliente("54936798", "Bruno");
        assertEquals(Retorno.Resultado.OK, ret.resultado);

        ret = miSistema.registrarEvento("4289", "Tango", 30, fecha);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        
        /* ERROR 1 : Mes < 1 o Mes > 12 */
        ret = miSistema.comprasXDia(0 /*Mes*/);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }
    
}