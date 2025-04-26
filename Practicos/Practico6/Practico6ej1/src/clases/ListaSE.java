package src.clases;

public class ListaSE<T> implements IListaSE<T> {
    
    public Animal<T> animal;
    public int cantidad;

    public ListaSE() {
        this.animal = null;
    }

    @Override
    public void agregarInicio(T x) {

        Animal<T> nuevo = new Animal("Perro");
        nuevo.setSiguiente(animal);
        cantidad++;
    }

    @Override
    public void mostrar() {
        Animal<T> aux = animal;
        while(aux != null){
            System.out.print(aux.getEspecie() + " - ");
            aux = aux.getSiguiente();
        }
    }

    public int cantidadElementos() {
        // int cant = 0;
        // Animal<T> aux = animal;
        return cantidad;
    }

    @Override
    public boolean esVacia() {
        return animal == null;
    }

    @Override
    public void vaciar() {
        animal = null;
    }

    @Override
    public boolean existeElemento(T x) {
        return true;
    }

    @Override
    public T obtenerElemento(int indice) {
        return null;
    }

    @Override
    public void agregarFinal(T x) {
        if(esVacia()){
            agregarInicio(x);
        }
        else{
            Nodo<T> nuevo = new Nodo(x);
            Nodo<T> aux = inicio;
            while(aux.getSiguiente()!=null){
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
        }
    }

    @Override
    public void eliminarInicio() {
        if (!esVacia()) {
            Animal<T> aBorrar = animal;
            animal = animal.getSiguiente();
            aBorrar.setSiguiente(null);
        }
    }

    @Override
    public void eliminarFinal() {
        if(!esVacia()){
            if(animal.getSiguiente() == null){ //Tengo solo 1 elemento
                vaciar();
            }
            else{ //Tengo mas de 1 elemento
                Animal<T> aux = animal;
                while(aux.getSiguiente().getSiguiente() != null){
                    aux = aux.getSiguiente();
                }
                aux.setSiguiente(null);
            }
        }
    }
    
}