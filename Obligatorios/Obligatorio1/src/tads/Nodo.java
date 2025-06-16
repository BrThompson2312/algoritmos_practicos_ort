package tads;

public class Nodo<T extends Comparable> 
{
    private Nodo<T> siguiente;
    private T dato;

    public Nodo(T dato) {
        this.dato = dato;
        siguiente = null; 
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public T getDato() {
        return dato;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }
}
