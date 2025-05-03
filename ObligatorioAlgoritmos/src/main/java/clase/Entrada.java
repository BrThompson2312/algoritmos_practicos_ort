package clase;

import tadlista.Nodo;
import clase.Evento;

public class Entrada
{
    public String codigo;
    public Evento entradaEvento;
    public Cliente entradaCliente;
    public Nodo entradaSiguiente;

    public Entrada(String codigo, Evento entradaEvento, Cliente entradaCliente, Nodo entradaSiguiente) 
    {
        this.codigo = codigo;
        this.entradaEvento = entradaEvento;
        this.entradaCliente = entradaCliente;
        this.entradaSiguiente = entradaSiguiente;
        validar();
    }

    private void validar()
    {
        if (codigo.length() != 3)
        {
            throw new Error("Codigo de Entrada debe ser de 3 digitos");
        }
    }
}