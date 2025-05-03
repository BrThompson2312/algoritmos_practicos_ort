package tadlista;

public class ListaNodos<T> implements IListaSimple<T> {

    private Nodo<T> inicio;
    private int cantElementos;

    public ListaNodos() {
        this.inicio = null;
        cantElementos = 0;
    }

    @Override
    public void agregarInicio(T x) {

        Nodo<T> nuevo = new Nodo(x);
        nuevo.setSiguiente(inicio);
        inicio = nuevo;
        cantElementos++;
    }

    @Override
    public void mostrar() {
        Nodo<T> aux = inicio;
        while (aux != null) {
            System.out.print(aux.getDato() + " - ");
            aux = aux.getSiguiente();
        }
    }

    @Override
    public int cantidadElementos() {
        /*
        int cant = 0;
        Nodo aux = inicio;

        while (aux != null) {
            cant++;
            aux = aux.getSiguiente();
        }
        return cant;*/
        return cantElementos;
    }

    @Override
    public boolean esVacia() {
        return inicio == null;
    }

    @Override
    public void vaciar() {
        inicio = null;
    }

    @Override
    public boolean existeElemento(T x) {
        boolean existe = false;
        Nodo<T> aux = inicio;

        while (aux != null && !existe) {
            if (aux.getDato().equals(x)) {
                existe = true;
            }
            aux = aux.getSiguiente();
        }
        return existe;
    }

    @Override
    public T obtenerElemento(int indice) {
        int indiceActual = 0;
        T ret = null;
        Nodo<T> aux = inicio;

        while (aux != null && ret == null) {
            if (indiceActual == indice) {
                ret = aux.getDato();
            }
            aux = aux.getSiguiente();
            indiceActual++;
        }
        return ret;
    }

    @Override
    public void agregarFinal(T x) {
        if (esVacia()) {
            agregarInicio(x);
        } else {
            Nodo<T> nuevo = new Nodo(x);
            Nodo<T> aux = inicio;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
            cantElementos++;
        }
    }

    @Override
    public void eliminarInicio() {
        if (!esVacia()) {
            Nodo aBorrar = inicio;
            inicio = inicio.getSiguiente();
            aBorrar.setSiguiente(null);
            cantElementos--;
        }
    }

    @Override
    public void eliminarFinal() {
        if (!esVacia()) {
            if (inicio.getSiguiente() == null) { //Tengo solo 1 elemento
                vaciar();
            } else { //Tengo mas de 1 elemento
                Nodo<T> aux = inicio;
                while (aux.getSiguiente().getSiguiente() != null) {
                    aux = aux.getSiguiente();
                }
                aux.setSiguiente(null);
                cantElementos--;
            }
        }
    }
    
    public Nodo<T> getInicio() {
        return inicio;
    }

    public void setInicio(Nodo<T> inicio) {
        this.inicio = inicio;
    }

    public int getCantElementos() {
        return cantElementos;
    }

    public void setCantElementos(int cantElementos) {
        this.cantElementos = cantElementos;
    }

}
