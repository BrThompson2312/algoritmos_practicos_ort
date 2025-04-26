import java.util.Arrays;

public class Practico4 {
    public static void main(String args[]) {
        int [][] matriz = {{1, 2, 3}, {4, 5, 6}};
        int [][] matriz2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        mostrarSumaCol(matriz2);
    }

// Ejercicio1
    public static void mostrarMatriz(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
    }

// Ejercicio2
    /* 
        Pre: Debe ser una matriz cuadrada
        Post: Debera imprimir en pantalla la diagonal de la matriz
    */
    public static void mostrardiagonal(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            System.out.println(mat[i][i]);
        }
    }

// Ejercicio3
    public static int maximoMatriz(int[][] mat) {
        int mayor = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int c = 0; c < mat[i].length; c++) {
                if (mat[i][c] >= mayor) {
                    mayor = mat[i][c];
                }
            }
            
        }
        return mayor;
    }

// Ejercicio4
    /* 
        Pre: La columna implementada por el usuario debe estar contenida
        por los limites de la matriz
        Post: Se imprime en pantalla la columna deseada de la matriz
    */
    public static void mostrarColumna(int[][] mat, int columna) {
        for (int i = 0; i < mat.length; i++) {
            System.out.println(mat[i][columna]);
        }
    }

// Ejercicio4
    /* 
        Pre: La fila implementada por el usuario debe estar contenida
        por los limites de la matriz
        Post: Se imprime en pantalla la fila deseada de la matriz
    */
    public static void mostrarFila(int[][] mat, int fila) {
        for (int i = 0; i < mat[fila].length; i++) {
            System.out.println(mat[fila][i]);
        }
    }

// Ejercicio 6
    /* 
        Pre: Se debera pasar por parametro una matriz, de preferencia con 
            una cantidad de filas impares
        Post: Se mostrara en pantalla los vectores de la matriz impares
    */
    public static void mostrarFilasImpares(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            if (i % 2 != 0) {
                System.out.println(Arrays.toString(mat[i]));
            } 
        }
    }

// Ejercicio 7
    /* 
        Pre: 
        Post: Se devolvera un booleano, dependiendo de si el elemento se encontro
    */
    public static boolean buscarElementoEnMatriz(int[][] mat, int elemento) {
        boolean encontrado = false;
        for (int i = 0; i < mat.length; i++) {
            for (int c = 0; c < mat[i].length; c++) {
                if (mat[i][c] == elemento) encontrado = true;
            }
        }
        return encontrado;
    }

// Ejercicio 8
    /* 
        Pre: Que la columna exista
        Post: Se retornara un booleano
    */
    public static boolean buscarEnColumna(int[][] mat, int columna, int elemento) {
        boolean encontrado = false;
        for (int i = 0; i < mat.length; i++) {
            if (mat[i][columna] == elemento) encontrado = true;
        }
        return encontrado;
    }

// Ejercicio 9
    /* 
        Pre:  
        Post: Se devolvera el resultado de la suma de las columnas de la matriz
    */
    public static void mostrarSumaCol(int[][] mat) {
        String total = "";
        int sumaTemp = 0;
        int incrementoTemp = 0;
        boolean terminado = false;

        // Solucion 1
        while (!terminado){
            for (int i = 0; i < mat.length; i++) {
                sumaTemp += mat[i][incrementoTemp];
            }
            total += sumaTemp + " - ";
            sumaTemp = 0;
            if (incrementoTemp >= mat[0].length - 1) {
                terminado=true;
            }
            incrementoTemp++;
        }

        // Solucion 2 - Profe
        /*
            for (int i = 0; i < j < mat[0].length; j++) {
                int suma = 0;
                for (int j = 0; j < mat.length; j++) {
                    suma += mat[i][j]
                }
                sout('col' + j + 'es' + suma)
            }
        */

        System.out.println(total);
    }

// Ejercicio 12 - Profe
    // public static int existeColumnaAscendente (int[][] mat) {
    //     int col = -1;
    //     boolean encontre = false;
    //     for (int j = 0; j < mat[0].length; j++) {
    //         int ant = mat[0][j];
    //         int cant = 1;
    //         for (int a = 1; a < mat.length; a++) {
    //             if (mat[j][a].length > ant) {
    //                 cant++;
    //                 if (cant == 3) {
    //                     col = j;
    //                     encontre = true;
    //                 }
    //             } else {
    //                 cant = 1;
    //                 ant = [j][a];
    //             }
    //         }
    //     }
    //     return encontre;
    // }

    public static boolean esSimetricaHorizontal (int[][] mat) {
        return true;
    }

}