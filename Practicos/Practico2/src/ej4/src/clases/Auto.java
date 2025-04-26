package ej4.src.clases;

import ej4.src.clases.interfaces.IVehiculo;

public class Auto implements IVehiculo {

    private String marca;
    private String modelo;
    private String kilometraje;

    @Override
    public double calcularConsumo(double distancia, double litrosConsumidos) {
        return 0;
    }
    
}