import java.util.Arrays;

public class Ordenacion2 {

    public static void main(String[] args) {
        int vec[] = {10, 2, 3, 12};
        bubbleSort(vec);       
        
        int vec2[] = {10, 2, 3, 12};
        bubbleSortMejorado(vec2);
        
        int vec3[] = {10, 2, 3, 12};
        selectionSort(vec2);
        System.out.println(Arrays.toString(vec3));
    }
    
    
    
    public static void bubbleSort(int vec[]) 
    {
        int pasadas = 0;
        int intercambios = 0;
        
        for (int i = 0; i < vec.length; i++)
        {
            pasadas++;
            
            for (int j = 0; j < vec.length-1-i; j++)
            {
                if (vec[j] > vec[j+1])
                {
                    intercambios++;
                    int aux = vec[j];
                    vec[j] = vec[j+1];
                    vec[j+1] = aux;
                }
            }
        }
        
        System.out.println(pasadas);
        System.out.println(intercambios);
    }
    
    public static void bubbleSortMejorado(int vec[]) 
    {
        
        int pasadas = 0;
        int intercambios = 0;
        
        boolean ordenado = false;
        
        for (int i = 0; i < vec.length && !ordenado; i++)
        {
            
            ordenado = true;
            pasadas++;
            
            for (int j = 0; j < vec.length-1-i; j++)
            {
                if (vec[j] > vec[j+1])
                {
                    intercambios++;
                    int aux = vec[j];
                    vec[j] = vec[j+1];
                    vec[j+1] = aux;
                    ordenado = false;
                }
            }
        }
        
        System.out.println(pasadas);
        System.out.println(intercambios);
    }
    
    public static void selectionSort(int vec[]) 
    {
        int pasadas = 0;
        int intercambios = 0;
        for (int i = 0; i < vec.length-1; i++)
        {
            int posMin = i;
            pasadas++;
            for (int j = i+1; j < vec.length; j++)
            {
                if (vec[j] < vec[posMin])
                {
                    posMin = j;
                }
            }
            intercambios++;
            int aux = vec[i];
            vec[i] = vec[posMin];
            vec[posMin] = aux;
        }
    }
    
    public static void selectionSortMejorado(int vec[]) 
    {
        int pasadas = 0;
        int intercambios = 0;
        boolean ordenado = false;
        
        for (int i = 0; i < vec.length-1 && !ordenado; i++)
        {
            int posMin = i;
            pasadas++;
            ordenado = true;
            
            for (int j = i+1; j < vec.length; j++)
            {
                if (vec[j] < vec[posMin])
                {
                    posMin = j;
                }
                if (vec[j] < vec[j-1])
                {
                    ordenado = false;
                }
            }
            intercambios++;
            int aux = vec[i];
            vec[i] = vec[posMin];
            vec[posMin] = aux;
        }
    }
}