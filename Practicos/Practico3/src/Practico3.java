public class Practico3 {

    public static void main(String[] args) {

        int[] lista = {1, 2, 3, 4, 5, 6};
        int[] array = {1, 2, 3, 2, 1};
        int[] array2 = {1, 2, 3, 2, 1, 5};
        System.out.println("============== Ejercicio1 ========================");
        System.out.println(mostrarVector(lista));
        System.out.println("============== Ejercicio2 ========================");
        System.out.println(promedio(lista));
        System.out.println("============== Ejercicio3 ========================");
        System.out.println(MuestroValoresImpares(lista));
        System.out.println("============== Ejercicio5 ========================");
        System.out.println(maxVectorV1(lista));
        System.out.println("============== Ejercicio6 ========================");
        System.out.println(esSimetrico(array2));
        System.out.println("============== Ejercicio7 ========================");
        System.out.println(posMinVec(lista,2,5));
        System.out.println("============== Ejercicio8 ========================");
    }

    public static String mostrarVector(int[] v) {
        String retorno = "";
        for (int i = 0; i < v.length; i++) {
            if (i == v.length - 1) {
                retorno += v[i];

            } else {
                retorno += v[i] + "-";
            }

        }
        return retorno;
    }

    public static double promedio(int[] v) {
        double suma = 0;
        double promedio = 0;
        for (int i = 0; i < v.length; i++) {
            suma += v[i];
        }
        promedio = suma / v.length;
        return promedio;
    }

    public static String MuestroValoresImpares(int[] v) {
        String cadena = "";
        for (int i = 0; i < v.length; i++) {
            if (v[i] % 2 != 0) {
                cadena += v[i] + ",";
            }
        }
        return cadena;
    }

    public static int maxVectorV1(int[] v) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < v.length; i++) {
            if (v[i] > max) {
                max = v[i];
            }
        }
        return max;
    }

    public static int maxVectorV2(int[] v) {
        return v[v.length - 1];
    }

    public static boolean esSimetrico(int[] v) {
        boolean esSimetrico = true;
        for (int i = 0; i < v.length; i++) {
            if (v[i] == v[1] - 1) {
                esSimetrico = true;
            } else {
                esSimetrico = false;
            }
        }
        return esSimetrico;
    }
    
    public static int posMinVec (int []v, int desde, int hasta){
        int posMin = desde;
        for (int i = desde; i <= hasta; i++) {
            if (v[1] < v[posMin]) {
                posMin = i;
            }
        }
        return posMin;
    }
}