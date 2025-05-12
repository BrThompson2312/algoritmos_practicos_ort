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
    
    @Test
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
        ret = miSistema.registrarEvento("EVT01", "Conierto2", 120, fecha);
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

    @Test
    public void testListarEventos() {
        
        Retorno sala1 = miSistema.registrarSala("Sala A", 90);
        Retorno sala2 = miSistema.registrarSala("Sala B", 70);
        Retorno sala3 = miSistema.registrarSala("Sala C", 40);
        assertEquals(Retorno.Resultado.OK, sala3.resultado);

        LocalDate fecha = LocalDate.of(2025, 5, 10);
        Retorno ret = miSistema.registrarEvento("CUC22", "Tango", 90, fecha);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        
        ret = miSistema.registrarEvento("EVT01", "Concierto", 70, fecha);
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        
        ret = miSistema.registrarEvento("DTRE2", "Jazz", 40, fecha);
        assertEquals(Retorno.Resultado.OK, ret.resultado);

        ret = miSistema.listarEventos();

        assertEquals(Retorno.Resultado.OK, ret.resultado);
    	assertEquals("CUC22-Tango-Sala A-90-0-0#DTRE2-Jazz-Sala C-40-0-0#EVT01-Concierto-Sala C-70-0-0", ret.valorString);
    }

    @Test
    public void testListarClientes() {
        miSistema.registrarCliente("45678992", "Micaela Ferrez");
    	miSistema.registrarCliente("23331111", "Martina Rodríguez");
    	miSistema.registrarCliente("35679992", "Ramiro Perez");

    	Retorno ret = miSistema.listarClientes();
    	assertEquals(Retorno.Resultado.OK, ret.resultado);
    	assertEquals("23331111-Martina Rodríguez#35679992-Ramiro Perez#45678992-Micaela Ferrez", ret.valorString);
    }

    @Test
    public void testSalaOptima_EsOptima() {
        String[][] vistaSala = {
        {"#", "#", "#", "#", "#", "#", "#"},
        {"#", "#", "X", "X", "X", "X", "#"},
        {"#", "O", "O", "X", "X", "X", "#"},
        {"#", "O", "O", "O", "O", "X", "#"},
        {"#", "O", "O", "X", "O", "O", "#"},
        {"#", "O", "O", "O", "O", "O", "#"},
        {"#", "X", "X", "O", "O", "O", "O"},
        {"#", "X", "X", "O", "O", "O", "X"},
        {"#", "X", "X", "O", "X", "X", "#"},
        {"#", "X", "X", "O", "X", "X", "#"},
        {"#", "#", "#", "O", "#", "#", "#"},
        {"#", "#", "#", "O", "#", "#", "#"}
    	};

	Retorno ret = miSistema.esSalaOptima(vistaSala);
    	assertEquals(Retorno.Resultado.OK, ret.resultado);
    	assertEquals("Es óptimo", ret.valorString);
    }
    
    @Test
    public void testSalaOptima_NoEsOptima() {
        String[][] vistaSala = {
        {"#", "#", "#", "#", "#", "#", "#"},
        {"#", "#", "X", "X", "X", "X", "#"},
        {"#", "X", "O", "O", "O", "X", "#"},
        {"#", "X", "X", "X", "X", "X", "#"},
        {"#", "X", "X", "X", "X", "X", "#"},
        {"#", "X", "X", "X", "X", "X", "#"},
        {"#", "X", "X", "X", "X", "X", "X"},
        {"#", "X", "X", "X", "X", "X", "X"},
        {"#", "X", "X", "X", "X", "X", "#"},
        {"#", "X", "X", "X", "X", "X", "#"},
        {"#", "#", "#", "X", "#", "#", "#"},
        {"#", "#", "#", "X", "#", "#", "#"}
    	};

	Retorno ret = miSistema.esSalaOptima(vistaSala);
    	assertEquals(Retorno.Resultado.OK, ret.resultado);
    	assertEquals("No es óptimo", ret.valorString);
    }

}