package ej3;

public class Jornalero extends Funcionario {
    private int horasTrabajadas;
    private int valorHora;
    
    public Jornalero (String nombre, String cedula, int horasTrabajadas, int valorHora) {
        super(nombre, cedula);
        this.horasTrabajadas =  horasTrabajadas;
        this.valorHora = valorHora;
    }
    
    public int getHorasTrabajadas() {
        return this.horasTrabajadas;
    }
    
    public void setValorHora(int jValorhora) {
        this.valorHora = jValorhora;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Horas trabajadas: " + this.horasTrabajadas + " Valor hora:" + this.valorHora;
    }
     
    @Override       
    public int calcularSueldo() {
        return 0;
    }
}