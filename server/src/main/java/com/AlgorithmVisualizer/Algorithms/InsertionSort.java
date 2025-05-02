package com.AlgorithmVisualizer.Algorithms;

import com.AlgorithmVisualizer.Trace.*;

public class InsertionSort {
    public static Trace sort(int[] vet) {
        Trace trace = new Trace();

        for (int i = 1; i < vet.length; i++) {
            int element = vet[i];
            trace.addMemoryAcess(i);
            
            int j = i - 1;
            while (j >= 0 && vet[j] > element) {
                trace.addComparison(i, j, 1);

                vet[j + 1] = vet[j];
                trace.addSwap(j, j+1, 1);

                j--;
            }
            if (j >= 0) trace.addComparison(i, j, 1);

            vet[j + 1] = element;
        }

        return trace;
    }
}
