package TAD.src.clases;

public class NodoG<T> {
    
    private T dato;
    private Nodo siguiente;

    public NodoG(T dato, Nodo siguiente) {
        this.dato = dato;
        this.siguiente = siguiente;
    }

}