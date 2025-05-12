package sistemaAutogestion;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IObligatorioTest {

    private Sistema miSistema;

    public IObligatorioTest() {
        miSistema = new Sistema();
    }

    @Before
    public void setUp() {
        miSistema = new Sistema();
        miSistema.crearSistemaDeGestion();
    }

    @Test
    public void testCrearSistemaDeGestion() {
        Retorno ret = miSistema.crearSistemaDeGestion();
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }

    @Test
    public void testRegistrarSala() {
        Retorno ret = miSistema.registrarSala("Sala A", 50);
        assertEquals(Retorno.Resultado.OK, ret.resultado);

 	ret = miSistema.registrarSala("Sala B", 10);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }
    
    @Test // EQUALS SOBREESCRITO (DUDOSO)
    public void testRegistrarSala_ERROR1() {
        Retorno ret = miSistema.registrarSala("Sala A", 50);
        assertEquals(Retorno.Resultado.OK, ret.resultado);

 	ret = miSistema.registrarSala("Sala B", 10);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        
        ret = miSistema.registrarSala("Sala A", 10);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }
    
    @Test
    public void testRegistrarSala_ERROR2() {
        Retorno ret  = miSistema.registrarSala("Sala A", 50);
        assertEquals(Retorno.Resultado.OK, ret.resultado);

 	ret = miSistema.registrarSala("Sala B", 10);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        
        ret = miSistema.registrarSala("Sala B", -10);
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
    }
    
    @Test
    public void testEliminarSala() {
        Retorno ret  = miSistema.registrarSala("Sala A", 50);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        
        ret  = miSistema.eliminarSala("Sala A");
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }

    @Test
    public void testRegistrarEvento() {
        Retorno ret = miSistema.registrarSala("Sala A", 100);
	assertEquals(Retorno.Resultado.OK, ret.resultado);

        LocalDate fecha = LocalDate.of(2025, 5, 10);
        ret = miSistema.registrarEvento("EVT01", "Concierto", 80, fecha);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }
    
    @Test
    public void testRegistrarEvento_ERROR1() {
        Retorno ret = miSistema.registrarSala("Sala A", 100);
	assertEquals(Retorno.Resultado.OK, ret.resultado);

        LocalDate fecha = LocalDate.of(2025, 5, 10);
        ret = miSistema.registrarEvento("EVT01", "Concierto", 80, fecha);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        
        fecha = LocalDate.of(2025, 5, 10);
        ret = miSistema.registrarEvento("EVT01", "Concierto", 80, fecha);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }
    
    @Test
    public void testRegistrarEvento_ERROR2() {
        Retorno ret = miSistema.registrarSala("Sala A", 100);
	assertEquals(Retorno.Resultado.OK, ret.resultado);

        LocalDate fecha = LocalDate.of(2025, 5, 10);
        ret = miSistema.registrarEvento("EVT01", "Concierto", 0, fecha);
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
    }
    
    @Test
    public void testRegistrarEvento_ERROR3() {
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        Retorno ret = miSistema.registrarEvento("EVT01", "Concierto", 110, fecha);
        assertEquals(Retorno.Resultado.ERROR_3, ret.resultado);
    }
    
    @Test
    public void testRegistrarEvento_ERROR3_TIPO2() {
        Retorno sala = miSistema.registrarSala("Sala A", 100);
        
        LocalDate fecha = LocalDate.of(2025, 5, 10);
        Retorno ret = miSistema.registrarEvento("EVT01", "Concierto", 110, fecha);
        assertEquals(Retorno.Resultado.ERROR_3, ret.resultado);
    }
    
    @Test
    public void testRegistrarCliente() {
        Retorno ret = miSistema.registrarCliente("12345678", "Juan Pérez");
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }
    
    @Test
    public void testRegistrarCliente_ERROR1() {
        Retorno ret = miSistema.registrarCliente("1234", "Juan Pérez");
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }
    
    @Test
    public void testRegistrarCliente_ERROR2() {
        Retorno ret = miSistema.registrarCliente("12345678", "Juan Pérez");
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        
        ret = miSistema.registrarCliente("12345678", "Juan Pérez");
        assertEquals(Retorno.Resultado.ERROR_2, ret.resultado);
    }

    @Test
    public void testListarSalas() {
        miSistema.registrarSala("Sala A", 50);
        miSistema.registrarSala("Sala B", 70);
        miSistema.registrarSala("Sala C", 100);
        Retorno ret = miSistema.listarSalas();
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("Sala C-100#Sala B-70#Sala A-50", ret.valorString);
    }

    @Test // INC, NO
    public void testListarEventos() {
        
        Retorno ret = miSistema.registrarSala("Sala A", 100);
        ret = miSistema.registrarSala("Sala B", 50);

        LocalDate fecha = LocalDate.of(2025, 5, 10);
        ret = miSistema.registrarEvento("CUC22", "Tango", 100, fecha);
        ret = miSistema.registrarEvento("EVT01", "Concierto", 50, fecha);
        
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    	assertEquals("CUC22-Tango Rodríguez#35679992-Ramiro Perez#45678992-Micaela Ferrez", ret.valorString);
        
    }

    @Test // ERROR DE TIPO DE RETORNO: NO DEVUELVE POR ORDEN LAS CEDULAS (1, luego 2, etc.)
    public void testListarClientes() {
        miSistema.registrarCliente("45678992", "Micaela Ferrez");
    	miSistema.registrarCliente("23331111", "Martina Rodríguez");
    	miSistema.registrarCliente("35679992", "Ramiro Perez");

    	Retorno ret = miSistema.listarClientes();
    	assertEquals(Retorno.Resultado.OK, ret.resultado);
    	assertEquals("23331111-Martina Rodríguez#35679992-Ramiro Perez#45678992-Micaela Ferrez", ret.valorString);
    }

    @Test
    public void testEsSalaOptima() {
        //Completar para primera entrega
    }

}