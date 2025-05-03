package clase;

import tadlista.ListaNodos;
import tadlista.Nodo;
import clase.Entrada;

public class Cliente 
{
    public String cedula;
    public String nombre;
    public Nodo clienteSiguiente;

    public Cliente(String cedula) {
        this.cedula = cedula;
        validar();
    }

    private void validar() {
        if (cedula.length() != 8) {
            throw new Error("Error, no cumple con la cantidad deseada de digitos");
        }
    }
    
}