package ej4.src.clases;

import ej4.src.clases.interfaces.IVehiculo;

public class Moto implements IVehiculo {

    private String marca;
    private String cilindrada;
    private String kilometraje;

    @Override
    public double calcularConsumo(double distancia, double litrosConsumidos) {
        return 0;
    }
    
}