package com.AlgorithmVisualizer.Algorithms;

import com.AlgorithmVisualizer.Trace.*;

public class BubbleSort {
    public static Trace sort(int[] vet) {
	    int temp;
	    int size = vet.length;
        Trace trace = new Trace();
	    
	    for(int i = 0; i < size - 1; i++) {	  
            
	        for(int j = 0; j < size - i - 1; j++) {  
                trace.addComparison(j, j + 1, 2);

                // Swap se fora de ordem
	            if( vet[j] > vet[j + 1] ) {
                    trace.addSwap(j, j + 1, 2);

	                temp = vet[j];
	                vet[j] = vet[j + 1];
	                vet[j + 1] = temp;
	            }
	        }
	    }

        return trace;
	}
}
