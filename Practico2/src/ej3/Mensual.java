package ej3;

public class Mensual extends Funcionario {
    private int SueldoMensual;
    
    public Mensual (String nombre, String cedula, int SueldoMensual) {
        super (nombre, cedula);
        this.SueldoMensual =  SueldoMensual;
    }
    
    public void setSueldoMensual(int mSueldoMensual) {
        this.SueldoMensual = mSueldoMensual;
    }
     
    @Override
    public String toString() {
        return super.toString() + ", SueldoMensual: " + this.SueldoMensual;
    }
     
    @Override       
    public int calcularSueldo() {
        return 0;
    }
}