package com.AlgorithmVisualizer.Algorithms;

import com.AlgorithmVisualizer.Trace.*;

public class QuickSort {
    public static Trace sort(int[] vet) {
        Trace trace = new Trace();

        sort_recursive(vet, 0, vet.length - 1, trace);
        
        return trace;
    }

    private static void sort_recursive(int[] vet, int start_index, int end_index, Trace trace) {
        if (end_index <= start_index) return;

        int index_pivot = getIndexPivot(vet, start_index, end_index);

        // O(n)
        int new_index_pivot = partitionArray(vet, index_pivot, start_index, end_index, trace);

        sort_recursive(vet, start_index, new_index_pivot - 1, trace);
        sort_recursive(vet, new_index_pivot + 1, end_index, trace);
    }

    private static int getIndexPivot(int[] vet, int start_index, int end_index) {
        return start_index + (end_index - start_index) / 2;
    }

    private static int partitionArray(int[] vet, int index_pivot, int start_index, int end_index, Trace trace) {
        int i = start_index, j = end_index;
        int pivot = vet[index_pivot];

        trace.addMemoryAcess(index_pivot);

        while (i < j) {
            // Encontra um elemento maior ou igual ao pivot pela esquerda
            while (vet[i] < pivot && i < index_pivot) {
                trace.addComparison(i, index_pivot, 1);
                i++;
            }

            // Encontra um elemento menor ou igual ao pivot pela direita
            while (vet[j] > pivot && j > index_pivot) {
                trace.addComparison(index_pivot, j, 1);
                j--;
            }

            // Tratar elementos duplicados
            if (vet[i] == vet[j] && vet[i] == pivot) {
                trace.addComparison(i, j, 2);
                
                if (j == index_pivot) i++;
                else j--;

                continue;
            }

            // Swap se eles ainda não se cruzaram
            if (i < j) {
                trace.addSwap(i, j, 4);

                int tmp = vet[i];
                vet[i] = vet[j];
                vet[j] = tmp;

                // Atualizar index do pivot se ele for um dos elementos do swap
                if (index_pivot == i) index_pivot = j;
                else if (index_pivot == j) index_pivot = i;
            }
        }

        return index_pivot;
    }
}
