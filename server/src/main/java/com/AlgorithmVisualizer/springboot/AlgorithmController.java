package com.AlgorithmVisualizer.springboot;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AlgorithmVisualizer.LoadFromFile;
import com.AlgorithmVisualizer.Algorithms.BinarySearch;
import com.AlgorithmVisualizer.Algorithms.BubbleSort;
import com.AlgorithmVisualizer.Algorithms.HeapSort;
import com.AlgorithmVisualizer.Algorithms.InsertionSort;
import com.AlgorithmVisualizer.Algorithms.QuickSort;
import com.AlgorithmVisualizer.Algorithms.RadixSort;
import com.AlgorithmVisualizer.Trace.Trace;

@RestController
@RequestMapping("/api")
public class AlgorithmController {

	@CrossOrigin
	@GetMapping("/sort")
	public Trace sort(@RequestParam String algorithm, @RequestParam int arrayNumber) {
		int[] array = LoadFromFile.loadArray(arrayNumber);
		if (array == null) return null;

		switch (algorithm) {
			case "bubbleSort":
				return BubbleSort.sort(array);

			case "quickSort":
				return QuickSort.sort(array);
				
			case "insertionSort":
				return InsertionSort.sort(array);
				
			case "heapSort":
				return HeapSort.sort(array);
				
			case "radixSort":
				return RadixSort.sort(array);
				
			default:
				return null;
		}
	}

	@CrossOrigin
	@GetMapping("/search")
	public Trace seach(@RequestParam int arrayNumber, @RequestParam int element) {
		int[] array = LoadFromFile.loadArray(arrayNumber);
		if (array == null) return null;

		QuickSort.sort(array);

		return BinarySearch.search(array, element);
	}

	@CrossOrigin
	@GetMapping("/array/sorted/{arrayNumber}")
	public int[] getSortedArray(@PathVariable int arrayNumber) {
		int[] array = LoadFromFile.loadArray(arrayNumber);
		QuickSort.sort(array);
		return array;
	}

	@CrossOrigin
	@GetMapping("/array/{arrayNumber}")
	public int[] getArray(@PathVariable int arrayNumber) {
		return LoadFromFile.loadArray(arrayNumber);
	}
}
