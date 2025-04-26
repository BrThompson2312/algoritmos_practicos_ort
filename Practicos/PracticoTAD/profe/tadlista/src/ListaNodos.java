package tadlista.src;

public class ListaNodos implements IListaSimple {

    private Nodo inicio;

    public ListaNodos() {
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
        while(aux != null){
            System.out.print(aux.getDato() + " - ");
            aux = aux.getSiguiente();
        }
    }

    @Override
    public int cantidadElementos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int obtenerElemento(int indice) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void agregarFinal(int x) {
        if(esVacia()){
            agregarInicio(x);
        }
        else{
            Nodo nuevo = new Nodo(x);
            Nodo aux = inicio;
            while(aux.getSiguiente()!=null){
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
        }
    }

    @Override
    public void eliminarInicio() {
        if (!esVacia()) {
            Nodo aBorrar = inicio;
            inicio = inicio.getSiguiente();
            aBorrar.setSiguiente(null);

        }
    }

    @Override
    public void eliminarFinal() {
        if(!esVacia()){
            if(inicio.getSiguiente() == null){ //Tengo solo 1 elemento
                vaciar();
            }
            else{ //Tengo mas de 1 elemento
                Nodo aux = inicio;
                while(aux.getSiguiente().getSiguiente() != null){
                    aux = aux.getSiguiente();
                }
                aux.setSiguiente(null);
            }
        }
    }

}
