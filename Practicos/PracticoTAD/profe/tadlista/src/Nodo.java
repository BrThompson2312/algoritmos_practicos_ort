package tadlista.src;

public class Nodo {
    
    private Nodo siguiente;
    private int dato;

    public Nodo(int dato) {
        this.dato = dato;
        siguiente = null; 
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public int getDato() {
        return dato;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }
     
}
