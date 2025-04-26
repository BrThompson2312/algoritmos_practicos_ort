package TAD.src;

import TAD.src.clases.ListaV1;

public class App {
    public static void main(String[] args) {
        ListaV1 miLista = new ListaV1(5);
        miLista.agregarFinal(10);
        miLista.agregarFinal(10);
        miLista.agregarFinal(10);
        miLista.agregarFinal(10);
        // No deberia
        miLista.agregarFinal(10);
        miLista.mostrar();
    }
}