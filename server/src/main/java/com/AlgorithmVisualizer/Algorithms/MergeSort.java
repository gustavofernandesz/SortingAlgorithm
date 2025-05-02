package com.AlgorithmVisualizer.Algorithms;

import com.AlgorithmVisualizer.Trace.*;
import java.lang.Math;

public class MergeSort {
    public static Trace sort(int[] vet) {
        Trace trace = new Trace();

        sort_recursive(vet, trace, 0);

        return trace;
    }

    private static void sort_recursive(int[] vet, Trace trace, int offset_original_array) {
        if (vet.length == 1) return;

        int[] vet_1 = new int[(int) Math.floor(vet.length / 2.0)];
        int[] vet_2 = new int[(int) Math.ceil( vet.length / 2.0)];

        // Copiar elementos da array original para as novas arrays
        for (int i = 0; i < vet_1.length; i++) vet_1[i] = vet[i];
        for (int i = 0; i < vet_2.length; i++) vet_2[i] = vet[i + vet_1.length];

        // Adicionar acessos de memoria da copia para o trace
        for (int i = 0; i < vet.length; i++) {
            trace.addMemoryAcess(offset_original_array + i);
        }

        sort_recursive(vet_1, trace, offset_original_array);
        sort_recursive(vet_2, trace, offset_original_array + vet_1.length);
        merge(vet, vet_1, vet_2, trace, offset_original_array);
    }

    private static void merge(int[] dst_array, int[] arr_1, int[] arr_2, Trace trace, int offset_original_array) {
        int index_arr_1 = 0;
        int index_arr_2 = 0;
        int element_arr_1 = arr_1[0], element_arr_2 = arr_2[0];

        int[] output_indices = new int[dst_array.length];
        
        for (int i = 0; i < dst_array.length; i++) {
            int element;

            if (element_arr_1 != Integer.MAX_VALUE && element_arr_2 != Integer.MAX_VALUE) 
                trace.addComparison(
                    offset_original_array + index_arr_1, 
                    offset_original_array + arr_1.length + index_arr_2, 
                    2
                );

            if (element_arr_1 > element_arr_2) {
                element = element_arr_2;
                output_indices[index_arr_2] = i;
                
                index_arr_2++;

                if (index_arr_2 < arr_2.length) element_arr_2 = arr_2[index_arr_2];
                else element_arr_2 = Integer.MAX_VALUE;
            }
            else {
                element = element_arr_1;
                output_indices[arr_1.length + index_arr_1] = i;

                index_arr_1++;

                if (index_arr_1 < arr_1.length) element_arr_1 = arr_1[index_arr_1];
                else element_arr_1 = Integer.MAX_VALUE;
            }

            dst_array[i] = element;
        }

        // Adiciona swaps para trace
        int index_in = 0, index_out = 0;
        while (index_in != dst_array.length && index_out != dst_array.length) {
            index_out = output_indices[index_in];

            if (index_in == index_out) {
                index_in++;
                continue;
            }

            trace.addSwap(offset_original_array + index_in, offset_original_array + index_out);
            
            int tmp = output_indices[index_in];
            output_indices[index_in]  = output_indices[index_out];
            output_indices[index_out] = tmp;
        }
    }
}
