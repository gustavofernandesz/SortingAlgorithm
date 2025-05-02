package com.AlgorithmVisualizer.Algorithms;

import com.AlgorithmVisualizer.DataStructures.PriorityQueue;
import com.AlgorithmVisualizer.Trace.*;

public class HeapSort {
    public static Trace sort(int[] vet) {
        Trace trace = new Trace();
        
        PriorityQueue max_heap = new PriorityQueue(vet, trace);

        // Adiciona os elementos da max heap em ordem para o vetor original
        for (int i = vet.length - 1; max_heap.hasNext(); i--) vet[i] = max_heap.pop();

        return trace;
    }
}
