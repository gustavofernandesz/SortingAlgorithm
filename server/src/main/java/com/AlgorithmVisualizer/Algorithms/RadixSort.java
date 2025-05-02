package com.AlgorithmVisualizer.Algorithms;

import com.AlgorithmVisualizer.Trace.*;

public class RadixSort {
    public static Trace sort(int[] arr) {
        Trace trace = new Trace();
        
        int max = getMax(arr, trace);

        int num_bits = 32 - Integer.numberOfLeadingZeros(max);

        for (int i = 0; i < num_bits; i++)
            countingSort(arr, i, trace);

        return trace;
    }

    private static int getMax(int[] arr, Trace trace) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            trace.addMemoryAcess(i);
            if (arr[i] > max) max = arr[i];
        }
        
        return max;
    }

    private static void countingSort(int[] arr, int index_bit, Trace trace) {
        int[] count_arr = new int[2];

        for (int i = 0; i < arr.length; i++) {
            int bit = (arr[i] >> index_bit) & 1;

            trace.addMemoryAcess(i);

            count_arr[bit]++;
        }

        count_arr[1] = arr.length;
        
        int[] output_arr = new int[arr.length];
        int[] output_indices = new int[arr.length];

        for (int i = arr.length - 1; i >= 0; i--) {
            int bit = (arr[i] >> index_bit) & 1;

            trace.addMemoryAcess(i);

            int index_out = count_arr[bit] - 1;
            output_arr[index_out] = arr[i];

            output_indices[i] = index_out;

            count_arr[bit]--;
        }

        // Adiciona swaps para trace
        int index_in = 0, index_out = 0;
        while (index_in != arr.length && index_out != arr.length) {
            index_out = output_indices[index_in];

            if (index_in == index_out) {
                index_in++;
                continue;
            }

            trace.addSwap(index_in, index_out);
            
            int tmp = output_indices[index_in];
            output_indices[index_in]  = output_indices[index_out];
            output_indices[index_out] = tmp;
        }

        for (int i = 0; i < arr.length; i++) arr[i] = output_arr[i];
    }
}
