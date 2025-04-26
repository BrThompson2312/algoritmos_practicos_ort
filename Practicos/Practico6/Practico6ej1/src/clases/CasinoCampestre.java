package src.clases;

public class CasinoCampestre {

    ListaSE<Animal> animales;

    public boolean EnPeligroExtincion(String especie) {
        int cantElementos = animales.cantidadElementos();
        int cantAnimales = 0;

        for (int i = 0; i < animales.cantidadElementos(); i++) {
            Animal a = animales.obtenerElemento(i);
            if (a.getEspecie().equalsIgnoreCase(especie) && a.getEdad() > 10) {
                cantAnimales++;
            }
        }
        return cantAnimales <= 3;
    }
}
