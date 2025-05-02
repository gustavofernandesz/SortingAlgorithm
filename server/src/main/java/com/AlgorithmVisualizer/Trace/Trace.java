package com.AlgorithmVisualizer.Trace;

import com.AlgorithmVisualizer.DataStructures.LinkedList;
import java.util.Iterator;
import java.util.List;

public class Trace implements Iterable<Operation>{
    private List<Operation> trace;
    private int num_comparisons = 0;
    private int num_memory_accesses = 0;

    public Trace() {
        trace = new LinkedList<Operation>();
    }

    public void addComparison(int index_1, int index_2) {
        addComparison(index_1, index_2, 0);
    }

    public void addComparison(int index_1, int index_2, int memory_accesses) {
        num_comparisons++;
        num_memory_accesses += memory_accesses;
        trace.add(new Operation(index_1, index_2, memory_accesses, false, true));
    }

    public void addMemoryAcess(int index) {
        num_memory_accesses++;
        trace.add(new Operation(index, -1, 1, false, false));
    }

    public void addSwap(int index_1, int index_2) {
        trace.add(new Operation(index_1, index_2, 0, true, false));
    }

    public void addSwap(int index_1, int index_2, int num_memory_accesses) {
        trace.add(new Operation(index_1, index_2, num_memory_accesses, true, false));
    }

    public int numOperations() {
        return trace.size();
    }

    public int numComparisons() {
        return num_comparisons;
    }

    public int numMemoryAccesses() {
        return num_memory_accesses;
    }

    @Override
    public Iterator<Operation> iterator() {
        return trace.iterator();
    }
}
