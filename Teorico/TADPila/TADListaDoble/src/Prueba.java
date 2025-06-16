public class Prueba {
    public static void main(String [] args){
        Pila<Integer> miPila = new Pila();
        miPila.push(100);
        miPila.push(50);
        miPila.push(70);
        miPila.push(10);
        miPila.mostrar();
    }
    
    public static Pila copiarPila(Pila p) {
        Pila<Integer> miPila = new Pila();
        Pila otra = new Pila ();
        while (!p.esVacia()) { // Paso a pila auxiliar
            otra.push(p.top());
            p.pop();
        }
        while (!otra.esVacia()) { // Armo nueva pila y reconstruyo original
            otra.push(p.top());
            p.push(otra.top());
            p.pop();
        }
        return miPila;
    }
    
    public static void concatenarPila(Pila p1, Pila p2) {
    }
    
    public static void intercambiarV1(Pila p) {
        Integer numPrim = (Integer)p.top();
        p.pop();
        Integer numSeg = (Integer)p.top();
        p.pop();
        p.push(numPrim);
        p.push(numSeg);
    }
    
    public static void intercambiarV2(Pila p) {
        Pila p1 = new Pila();
        Pila p2 = new Pila();

        p1.push(p.top());
        p.pop();
        p2.push(p.top());
        p.pop();
        p.push(p1.top());
        p.push(p2.top());
    }
    
}