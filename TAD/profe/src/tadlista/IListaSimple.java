
package tadlista;


public interface IListaSimple {
    
    public void agregarInicio(int x);
    
    public void mostrar();
    
    public int cantidadElementos ();
    
    public boolean esVacia();
    
    public void vaciar();
    
    public boolean existeElemento (int x);
    
    public int obtenerElemento(int indice);
    
    public void agregarFinal (int x);
    
    public void eliminarInicio();
    
    public void eliminarFinal();


    
}
