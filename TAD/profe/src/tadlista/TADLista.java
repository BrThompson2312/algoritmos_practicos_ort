package tadlista;

public class TADLista {

    public static void main(String[] args) {
        /*
        ListaV1 miLista = new ListaV1(5);
        miLista.agregarFinal(10);
        miLista.agregarFinal(3);      
        miLista.agregarFinal(34);
        miLista.mostrar();
         */

        ListaNodos lista = new ListaNodos();
        lista.agregarInicio(10);
        lista.agregarInicio(20);
        lista.agregarInicio(40);
        lista.agregarInicio(40);
        lista.agregarInicio(40);
        lista.agregarInicio(40);
        lista.agregarInicio(40);

        lista.mostrar();
        // System.out.println("");
        // lista.eliminarInicio();
        // lista.eliminarInicio();
        // lista.mostrar();
        

    }

}
