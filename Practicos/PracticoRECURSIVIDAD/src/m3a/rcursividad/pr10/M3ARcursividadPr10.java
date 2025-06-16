package m3a.rcursividad.pr10;

public class M3ARcursividadPr10 {

    public static void main(String[] args) {
        System.out.println("Potencia> " + pot(2,4));
        System.out.println("Fact 4>" + fact(4));
        
        int vector[] = {34,2,11,2};
        int suma = sumarVector(vector, 0);
    }

    public static String invertir(String pal) {

        if (pal.length() == 1) {
            return pal;
        } else {
            String recorte = pal.substring(0, pal.length() - 1);
            return pal.charAt(pal.length() - 1) + invertir(recorte);
        }

    }

    public static String invertirV2(String pal) {
        return invertirRec2(pal, pal.length() - 1);
    }

    public static String invertirRec2(String pal, int indice) {
       return "";
    }
    
    // pre1
    public static void mostrarAsc (int n){
        if (n ==1){
            System.out.println(n);
        }    
        else {
            mostrarAsc (n-1);
            System.out.println(n);
        }  
    }
    
    // pre2
    public static void mostrarDesc (int n){
        if (n ==1){
            System.out.println(n);
        }    
        else {
            System.out.println(n);
            mostrarDesc (n-1);
        }  
    }

    // 1
    public static int fact(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * fact(n - 1);
        }
    }

    // 2
    public static int pot(int n, int pot) {
        if (pot == 1) {
            return n;
        } else {
            return n * pot(n, pot - 1);
        }  
    }   
  
    // 4
    public static int sumar(int num)
    {
        if (num <= 9) {
            return num;
        } else {
            return (num % 10) + sumar(num/10);
        }
    }
    
    // 5
    public static int sumarVector(int vec[], int pos){
        if (pos == vec.length-1) {
            return vec[pos];
        }
        else {
            return vec[pos] + sumarVector(vec, pos+1);
        }
    }
    

    // 7
    public int max(int vec[]){
        return max(vec, 0);
    }
    
    public int max(int vec[], int pos){
        if (pos == vec.length-1) {
            return vec[pos];
        } else {
            int retmax = max(vec, pos+1);
            if (vec[pos] > retmax) {
                return vec[pos];
            } else {
                return retmax;
            }
        }
    }
    
    // 8
    public static boolean seEncuentraNumero_Desordenado(int vec[], int pos, int valor) {
        
        boolean encontrado = false;
        if (vec[vec.length-1] == valor) {
            encontrado = true;
        } else {
            return seEncuentraNumero_Desordenado(vec, pos++, vec[vec.length-1]-1);
        }
        
        return encontrado;
    }
    
    public static boolean seEncuentraNumero_Ordenado(int vec[], int pos, int valor) {
        int posIn =0;
        int posFin = vec.length -1;
        while (posIn <= posFin) {
            int medio = (posIn + posFin) /2;
            if (vec[medio] == valor){
                return true;
            } else {
                if (valor > vec[medio]) {
                    posIn = medio+1;
                } else {
                    posFin = medio-1;
                }
            }
        }    
        return false;
    }
    
    public static boolean existeElementoV2(int vec[], int numero, int ini, int fin) {
        if (ini > fin){
            return false;
        } else {
            int medio = (ini+fin)/2;
            
            if (vec[medio] == numero) {
                return true;
            } else {
                if (numero > vec[medio]) {
                    return existeElementoV2(vec, numero, medio+1, fin);
                } else {
                    return existeElementoV2(vec, numero, medio-1, fin);
                }
            }
        }
    }
    
}