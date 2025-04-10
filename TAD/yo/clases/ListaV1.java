package TAD.clases;

import TAD.interfaces.IListaSimple;

public class ListaV1 implements IListaSimple {

    private int[] lista;
    private int cant = 0;

    public ListaV1 (int capacidadMaxima) {
        this.lista = new int[capacidadMaxima];
    }

    @Override
    public void agregarInicio(int x) {
        // Método sin implementación
    }

    @Override
    public void mostrar() {
        for (int i = 0; i < cant; i++) {
            System.out.println(" - " + lista[i]);
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
        if (cant == lista.length) {
            System.out.println("Ya esta llena");
        } else {
            lista[cant] = x;
            cant++;
        }
    }

    @Override
    public void eliminarInicio() {
        // Método sin implementación
    }

    @Override
    public void eliminarFinal() {
        // Método sin implementación
    }
}