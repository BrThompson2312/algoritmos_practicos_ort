
public class NodoSimple<T extends Comparable> {

    private NodoSimple<T> siguiente;
    private T dato;

    public NodoSimple(T dato) {
        this.dato = dato;
        siguiente = null;
    }

    public NodoSimple getSiguiente() {
        return siguiente;
    }

    public T getDato() {
        return dato;
    }

    public void setSiguiente(NodoSimple siguiente) {
        this.siguiente = siguiente;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }
}
