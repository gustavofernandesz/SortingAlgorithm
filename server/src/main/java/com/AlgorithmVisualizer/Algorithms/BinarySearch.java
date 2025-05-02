package com.AlgorithmVisualizer.Algorithms;

import com.AlgorithmVisualizer.Trace.Trace;

public class BinarySearch {
    // Retorna o indice do elemento se ele estiver no vetor ordenado, -1 caso contrário
    public static Trace search(int[] vet, int elem) {
        Trace trace = new Trace();
        trace.addComparison(-1, -1);
        search(vet, elem, trace);
        return trace;
    }
    
    private static int search(int[] vet, int elem, Trace trace) {
        int n = vet.length;
        int ptr_first_elem = 0, ptr_middle_elem = n / 2, ptr_last_elem = n - 1;

        while (true) {
            trace.addComparison(ptr_middle_elem, -1, 1); 
            if (elem == vet[ptr_middle_elem]) return ptr_middle_elem;

            if (ptr_last_elem - ptr_first_elem <= 1) {
                trace.addComparison(ptr_last_elem, -1, 1);
                if (elem == vet[ptr_last_elem]) return ptr_last_elem;

                trace.addComparison(-1, -1);
                return -1;
            }
            
            if (elem < vet[ptr_middle_elem]) ptr_last_elem = ptr_middle_elem;
            else ptr_first_elem = ptr_middle_elem;

            ptr_middle_elem = ptr_first_elem + (ptr_last_elem - ptr_first_elem) / 2;
        }
    }
}
