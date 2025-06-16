public class Pila<T extends Comparable> implements IPila {

    private NodoSimple<T> inicio;
    private int cantElementos;

    public Pila() {
        this.inicio = null;
        cantElementos = 0;
    }
    
    public NodoSimple<T> getInicio() {
        return inicio;
    }

    public int getCantElementos() {
        return cantElementos;
    }

    public void setInicio(NodoSimple<T> inicio) {
        this.inicio = inicio;
    }

    public void setCantElementos(int cantElementos) {
        this.cantElementos = cantElementos;
    }

    public void push(T x) {
        NodoSimple<T> nuevo = new NodoSimple(x);
        if (esVacia()) {
            inicio = nuevo;
        } else {
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
        }

        cantElementos++;
    }

    public void mostrar() {
        String texto = "";
        NodoSimple<T> aux = inicio;
        while (aux != null) {
            texto += aux.getDato() + "\n";
            aux = aux.getSiguiente();
        }
        System.out.println(texto);
    }

    public int cantidadElementos() {
        return cantElementos;
    }

    public boolean esVacia() {
        return inicio == null;
    }

    public void vaciar() {
        inicio = null;
        cantElementos = 0;
    }

    public void pop() {
        if (!esVacia()) {
            if (cantElementos == 1) {
                vaciar();
            } else {
                NodoSimple aBorrar = inicio;
                inicio = inicio.getSiguiente();
                aBorrar.setSiguiente(null);
                cantElementos--;
            }
        }
    }

    public T top() {
        return inicio.getDato();
    }

    @Override
    public void push(Object x) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
