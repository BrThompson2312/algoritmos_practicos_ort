package ej4.src;

import ej4.src.clases.*;

public class Main {
    public static void main(String[] args) {
        Auto auto1 = new Auto();

        Moto moto1 = new Moto();

        auto1.calcularConsumo(100, 2);
        moto1.calcularConsumo(100, 2);
    }
}