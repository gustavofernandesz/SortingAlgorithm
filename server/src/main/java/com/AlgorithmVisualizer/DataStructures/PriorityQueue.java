package com.AlgorithmVisualizer.DataStructures;

import com.AlgorithmVisualizer.Trace.Trace;

/*
 * Implementação de uma priority queue usando max heap
 */
public class PriorityQueue {
    private int[] queue;
    private int ptr_last_elem = -1;
    private Trace trace;

    // Cria nova array auxiliar para a max heap
    // O(1) tempo
    // O(n) espaço auxiliar
    public PriorityQueue(int size, Trace trace) {
        queue = new int[size];
        this.trace = trace;
    }

    // Modifica a array passada como argumento para a estrutura do max heap
    // O(n log n) tempo
    // O(1) espaço auxiliar
    public PriorityQueue(int[] array, Trace trace) {
        this.queue = array;
        this.trace = trace;

        // Organizar todos os elementos da array no formato do max heap
        for (int node = 0; node < array.length; node++) maxHeapifyBottom(node);
        ptr_last_elem = array.length - 1;
    }

    // Adiciona um elemento para a heap
    // O(log n) tempo
    public void append(int key) {
        ptr_last_elem++;
        queue[ptr_last_elem] = key;
        maxHeapifyBottom(ptr_last_elem);
    }

    // Retorna o maior elemento da heap
    // O(1) tempo
    public int max() {
        return queue[0];
    }

    // Remove e retorna o maior elemento da heap
    // O(log n) tempo
    public int pop() {
        int popped_key = queue[0];
        queue[0] = queue[ptr_last_elem];
        
        trace.addSwap(0, ptr_last_elem, 3);
        ptr_last_elem--;

        maxHeapify(0);

        return popped_key;
    }

    // Usado quando toda a array está no formato correto, menos o ultimo elemento
    // O(log n) tempo
    private void maxHeapifyBottom(int node) {
        if (node == 0) return;
    
        int parent = (node - 1) / 2;

        trace.addComparison(node, parent, 2);

        if (queue[node] > queue[parent]) {
            trace.addSwap(node, parent, 2);

            int tmp = queue[node];
            queue[node] = queue[parent];
            queue[parent] = tmp;

            maxHeapifyBottom(parent);
            return;
        }
    }

    // Usado quando os 2 filhos de `node` são max heaps, mas
    // `node` não está na posição correta
    // O(log n) tempo
    private void maxHeapify(int node) {
        int n = ptr_last_elem + 1;
        if (n == 0 || node + 1 > n/2) return;

        int index_child1 = node*2 + 1;
        int index_child2 = node*2 + 2;
        if (index_child2 >= n) index_child2 = index_child1;

        trace.addComparison(index_child1, index_child2, 2);

        int biggest_children_index = (queue[index_child1] > queue[index_child2]) ? index_child1 : index_child2;
        int biggest_children = queue[biggest_children_index];

        if (biggest_children < queue[node]) {
            trace.addComparison(biggest_children_index, node, 1);
            return;
        }

        queue[biggest_children_index] = queue[node];
        queue[node] = biggest_children;

        trace.addSwap(biggest_children_index, node, 2);

        maxHeapify(biggest_children_index);
    }

    public boolean hasNext() {
        return ptr_last_elem != -1;
    }
}
