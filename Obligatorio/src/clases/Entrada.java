package src.clases;

public class Entrada
{
    public String codigo;
    public Nodo entradaSiguiente;

    public Evento entradaEvento
    public boolean entradaComprada;

    public Entrada(String codigo) 
    {
        this.codigo = codigo;
        validar();
    }

    private void validar()
    {
        if (codigo.length != 3)
        {
            throw new Exception("Codigo de Entrada debe ser de 3 digitos");
        }
    }
}