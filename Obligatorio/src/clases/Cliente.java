package src.clases;

import src.tadlista.ListaNodos;
import src.tadlista.Nodo;
import src.clases.Entrada;

public class Cliente 
{
    public String cedula;
    public String nombre;
    public ListadoNodos<Entrada> eventosComprados;
    public Nodo clienteSiguiente;

    public Cliente(String cedula) {
        this.cedula = cedula;
        validar();
    }

    private void validar() {
        if (cedula.length != 8) {
            throw new Exception("Error, no cumple con la cantidad deseada de digitos");
        }
    }
    
}