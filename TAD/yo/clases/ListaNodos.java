package TAD.clases;

import TAD.interfaces.IListaSimple;

public class ListaNodos implements IListaSimple {
    
    private Nodo inicio;

    public ListaNodos(Nodo inicio) {
        this.inicio = null;
    }

    @Override
    public void agregarInicio(int x) {
        Nodo nuevo = new Nodo(x);
        nuevo.setSiguiente(inicio);
        inicio = nuevo;
    }

    @Override
    public void mostrar() {
        Nodo aux = inicio;
        while (aux != null) {
            System.out.println(aux.getDato() + " - ");
        }
    }

    @Override
    public int cantidadElementos() {
        return 0;
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
    public boolean existeElemento(int x) {
        // Método sin implementación
        return false;
    }

    @Override
    public int obtenerElemento(int indice) {
        // Método sin implementación
        return 0;
    }

    @Override
    public void agregarFinal(int x) {
        if (esVacia()) {
            agregarInicio(x);
        } else {
            Nodo nuevo = new Nodo(x);
            Nodo aux = inicio;
            while(aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
        }
    }

    @Override
    public void eliminarInicio() {
        // if (!esVacia()) {
        //     Nodo aBorrar = inicio;
        //     inicio = inicio.getSiguiente();
        //     aAborrar.setSiguiente(null);
        // }
    }

    @Override
    public void eliminarFinal() {
        if (!esVacia()) {
            if (inicio.getSiguiente() == null) { //Tengo solo 1 elemento
                vaciar();
            } else { // Tiene mas de 1 elemento
                Nodo aux = inicio;
                while (aux.getSiguiente().getSiguiente() != null) {
                    aux = aux.getSiguiente();
                }
                aux.setSiguiente(null);
            }
        }
    }
}