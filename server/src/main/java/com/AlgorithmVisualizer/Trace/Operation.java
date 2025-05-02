package com.AlgorithmVisualizer.Trace;

public class Operation {
    public int index_1, index_2, memory_accesses;
    public boolean did_swap, did_compare;

    public Operation(int index_1, int index_2, int memory_accesses, boolean did_swap, boolean did_compare) {
        this.index_1 = index_1;
        this.index_2 = index_2;
        this.memory_accesses = memory_accesses;
        this.did_swap = did_swap;
        this.did_compare = did_compare;
    }    
}
