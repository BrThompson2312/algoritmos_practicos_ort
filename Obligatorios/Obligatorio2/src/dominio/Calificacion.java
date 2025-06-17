package dominio;
import tads.ListaNodos;
        
public class Calificacion implements Comparable <Calificacion> {
    private Cliente cliente;
    private int puntaje;
    private String comentario;
    
    public Calificacion (Cliente cliente, int puntaje, String comentario) {
        this.cliente = cliente;
        this.puntaje = puntaje;
        this.comentario = comentario;
    }
    
    public Cliente getCliente(){
        return cliente;
    }
    
    public int getPuntaje(){
        return puntaje;
    }
    
    public String getComentario(){
        return comentario;
    }
    
    public void setPuntaje(int puntaje){
        this.puntaje = puntaje;
    }
    
    public void setComentario (String comentario) {
        this.comentario = comentario;
    }
    
    @Override
    public boolean equals (Object o) {
        Calificacion otra = (Calificacion) o;
        return this.cliente.getCedula().equals(otra.cliente.getCedula());
    }
    
    @Override
    public int compareTo (Calificacion o) {
        return this.cliente.getCedula().compareTo(o.cliente.getCedula());
    }
    
    @Override
    public String toString() {
        return this.cliente.getCedula() + " - " + this.puntaje + " - " + this.comentario;
    }
    
}

