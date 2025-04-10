package TAD.interfaces;

public interface IListaSimple {
    void agregarInicio(int x);

    void mostrar();

    int cantidadElementos ();
    
    boolean esVacia();
    
    void vaciar();

    boolean existeElemento (int x);
    
    int obtenerElemento(int indice);
    
    void agregarFinal (int x);
    
    void eliminarInicio();

    void eliminarFinal();
}