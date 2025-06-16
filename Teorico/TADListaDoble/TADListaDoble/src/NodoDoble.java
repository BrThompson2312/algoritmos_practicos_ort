
public class NodoDoble<T extends Comparable> {

    private NodoDoble<T> siguiente;
    private NodoDoble<T> anterior;
    private T dato;

    public NodoDoble(T dato) {
        this.dato = dato;
        siguiente = null;
        anterior = null;
    }

    public NodoDoble getSiguiente() {
        return siguiente;
    }

    public T getDato() {
        return dato;
    }

    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }

   public NodoDoble getAnterior() {
        return anterior;
    }

}
