package tadlista.src;

public class ListaV1 implements IListaSimple {
    
    private int[] lista;
    private int cant = 0; 

    public ListaV1(int capMax) {
        this.lista = new int[capMax];
    }
    
    @Override
    public void agregarInicio(int x) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mostrar() {
        for(int i=0; i<cant;i++){
            System.out.print(" - " + lista[i]);
        }
    }

    @Override
    public int cantidadElementos() {
        return cant;
    }

    @Override
    public boolean esVacia() {
        return cant == 0;
    }

    @Override
    public void vaciar() {
        cant = 0;
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
       
        if(cant == lista.length){ //Ya esta llena
            System.out.println("Ya esta llena");
        }
        else{
            lista[cant] = x;
            cant++;
        }
        
    }

    @Override
    public void eliminarInicio() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarFinal() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
