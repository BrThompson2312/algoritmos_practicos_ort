package tads;

public class ListaNodos<T extends Comparable> implements IListaSimple<T> {

    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int cantElementos;
    
    public ListaNodos() {
        this.inicio = null;
        this.fin = null;
        cantElementos = 0;
    }

    @Override
    public void agregarInicio(T x) {
        Nodo<T> nuevo = new Nodo(x);
        
        if (esVacia()){
            inicio = nuevo;
            fin = nuevo;
        } else {
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
            cantElementos++;
        }
        
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
        fin = null;
        cantElementos = 0;
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
            fin.setSiguiente(nuevo);
            fin = nuevo;
            /*Nodo<T> aux = inicio;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);*/
            cantElementos++;
        }
    }

    @Override
    public void eliminarInicio() {
        if (!esVacia()) {
            if (cantElementos == 1) {
                vaciar();
            } else {
                Nodo aBorrar = inicio;
                inicio = inicio.getSiguiente();
                aBorrar.setSiguiente(null);
                cantElementos--; 
            }
        }
    }

    @Override
    public void eliminarFinal() {
        if (!esVacia()) {
            if (cantElementos == 1) {
                vaciar();
            } else {
                Nodo<T> aux = inicio;
                while (aux.getSiguiente().getSiguiente() != null) {
                    aux = aux.getSiguiente();
                }
                aux.setSiguiente(null);
                fin = aux;
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
    
    public Nodo<T> getFin() {
        return fin;
    }
    
    @Override
    public void insertarEnPos(int pos, T dato) {
        if (!esVacia()) {
            if (pos == 0) {
                agregarInicio(dato);
            } else {
                if (pos == cantElementos) {
                    agregarFinal(dato);
                } else {
                    int indiceActual = 1;
                    Nodo ant = inicio;
                    Nodo actual = inicio.getSiguiente();
                    while(pos != indiceActual) {
                        ant = actual;
                        actual = actual.getSiguiente();
                        indiceActual++;
                    }
                    Nodo nuevo = new Nodo(dato);
                    nuevo.setSiguiente(actual);
                }
            }
        }
    }
    
    @Override
    public void eliminarEnPos(int pos) {
        
    }
    
    public void agregarOrdenado(T x) {
        if (esVacia() || x.compareTo(inicio.getDato()) < 0) {
            agregarFinal(x);
        } else {
            Nodo<T> act = inicio;
            while (act.getSiguiente() != null && act.getSiguiente().getDato().compareTo(x) <= 0) {
                act = act.getSiguiente();
            }
            if (act.getSiguiente() == null) {
                agregarFinal(x);
            } else {
                Nodo<T> nuevo = new Nodo(x);
                nuevo.setSiguiente(act.getSiguiente());
                act.getSiguiente();
            }
        }
    }
}