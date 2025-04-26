package src.clases;

public class Animal<T> {

    public Animal<T> siguiente;
    public String especie;
    public int edad;
    
    public Animal(String especie){
        this.especie = especie;
    }

    public String getEspecie() {
        return especie;
    }

    public Animal<T> getSiguiente() {
        return siguiente;
    }

    public int getEdad() {
        return edad;
    }
    
    public void setSiguiente(Animal siguiente) {
        this.siguiente = siguiente;
    }
}