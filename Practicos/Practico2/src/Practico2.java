import ej1.Persona;
import ej2.Socio;
import ej3.*;

public class Practico2 {

    public static void main(String[] args) {
        Persona p1 = new Persona("Hernan", "Hernandez", 19);
        
        System.out.println("Datos");
        System.out.println(p1);
        System.out.println("===================================");
        
        System.out.println("Listado de Socios");
        Socio s1 = new Socio("Hernan");
        Socio s2 = new Socio("Bruno");
        
        System.out.println(s1);
        System.out.println(s2);
        System.out.println("===================================");
        
        System.out.println("Listado de Funcionarios"); 
        Funcionario f1 = new Mensual("Hernan", "76545", 678);
        Funcionario f2 = new Jornalero("Hernan", "76545", 678, 12);
        
        System.out.println(f1);
        System.out.println(f2);
    }
}