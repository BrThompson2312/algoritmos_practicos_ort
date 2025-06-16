public class ListaNodos<T extends Comparable> implements IListaDoble<T> {

    private NodoDoble<T> inicio;
    private NodoDoble<T> fin;
    private int cantElementos;

    public ListaNodos() {
        this.inicio = null;
        this.fin = null;
        cantElementos = 0;
    }

    @Override
    public void agregarInicio(T x) {

        NodoDoble<T> nuevo = new NodoDoble(x);

        if (esVacia()) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            inicio.setAnterior(nuevo);
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
        }

        cantElementos++;
    }

    @Override
    public void mostrar() {
        NodoDoble<T> aux = inicio;
        while (aux != null) {
            System.out.print(aux.getDato() + " - ");
            aux = aux.getSiguiente();
        }
    }

    @Override
    public int cantidadElementos() {
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
        NodoDoble<T> aux = inicio;

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
        NodoDoble<T> aux = inicio;

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
            NodoDoble<T> nuevo = new NodoDoble(x);
            nuevo.setAnterior(fin);
            fin.setSiguiente(nuevo);
            fin = nuevo;
            cantElementos++;
        }
    }

    @Override
    public void eliminarInicio() {
        if (!esVacia()) {
            if (cantElementos == 1) {
                vaciar();
            } else {
                NodoDoble aBorrar = inicio;
                inicio = inicio.getSiguiente();
                inicio.setAnterior(null);
                aBorrar.setSiguiente(null);
                cantElementos--;
            }

        }
    }

    @Override
    public void eliminarFinal() {
        if (!esVacia()) {
            if (inicio.getSiguiente() == null) { //Tengo solo 1 elemento
                vaciar();
            } else { //Tengo mas de 1 elemento
                NodoDoble<T> aBorrar = fin;
                fin = fin.getAnterior();
                fin.setSiguiente(null);
                aBorrar.setAnterior(null);
                cantElementos--;
            }
        }
    }

    public NodoDoble<T> getInicio() {
        return inicio;
    }

    public void setInicio(NodoDoble<T> inicio) {
        this.inicio = inicio;
    }

    public int getCantElementos() {
        return cantElementos;
    }

    public void setCantElementos(int cantElementos) {
        this.cantElementos = cantElementos;
    }

    @Override
    public boolean eliminarElemento(T x) {

        boolean elimine = false;

        if (!esVacia()) {
            if (inicio.getDato().equals(x)) {
                eliminarInicio();
                elimine = true;
            } else {

                NodoDoble<T> aux = inicio;

                while (aux != null && !aux.getDato().equals(x)) {
                    aux = aux.getSiguiente();
                }
                
                if (aux != null) { // Quiere decir que encontre el elemento
                    NodoDoble aEliminar = aux;
                    aEliminar.getAnterior().setSiguiente(aux.getSiguiente());
                    aEliminar.getSiguiente().setAnterior(aux.getAnterior());
                    aEliminar.setAnterior(null);
                    aEliminar.setAnterior(null);
                    cantElementos--;
                    elimine = true;

                }
            }
        }
        return elimine;
    }


}
