// components/SortingVisualizer.js
import { useState } from 'react';
import Controls from './Controls';
import BarChart from './BarChart';
import { ApiService } from './ApiService.js';

const SortingVisualizer = () => {
  
  const [memoryAcesses, setMemoryAcesses] = useState(0);
  const [comparisons, setComparisons] = useState(0);
  const [array, setArray] = useState([]);
  const [highlighted1, setHighlighted1] = useState(-1);
  const [highlighted2, setHighlighted2] = useState(-1);
  const [isSorting, setIsSorting] = useState(false);

  const loadArray = async (arrayNumber, sorted=false) => {
    try {
      // api rodando 
      const newArray = await ((sorted) ? ApiService.getSortedArray(arrayNumber) : ApiService.getArray(arrayNumber));
      setArray(newArray);
    } catch (error) {
      console.error('Error fetching operations:', error);
    }
  }

  // chama api
  const handleSort = async (algorithm, arrayNumber) => {
    setIsSorting(true);
    try {
      // api rodando 
      const operations = await ApiService.sortAlgorithm(algorithm, arrayNumber);
      animateOperations(operations);
    } catch (error) {
      console.error('Error fetching operations:', error);
      setIsSorting(false);
    }
  };

  // chama api
  const handleSearch = async (arrayNumber, element) => {
    setIsSorting(true);
    try {
      // api rodando 
      const operations = await ApiService.searchAlgorithm(arrayNumber, element);
      animateOperations(operations, true);
    } catch (error) {
      console.error('Error fetching operations:', error);
      setIsSorting(false);
    }
  };

  // Animate the operations (swaps) returned from the backend
  const animateOperations = (operations, isSearch=false) => {
    
    const operationsInterval = (isSearch) ? 1000 : 100;

    operations.forEach((op, idx) => {
      setTimeout(() => {
        setHighlighted1(op.index_1);
        setHighlighted2(op.index_2);

        setMemoryAcesses((prev) => prev + op.memory_accesses);
        setComparisons(  (prev) => prev + op.did_compare);

        if (op.did_swap) {
          setArray((prevArray) => {
            const newArray = [...prevArray];
            // Swap elements at index_1 and index_2
            const temp = newArray[op.index_1];
            newArray[op.index_1] = newArray[op.index_2];
            newArray[op.index_2] = temp;
            return newArray;
          });
        }
      }, idx * operationsInterval);
    });
    setTimeout(() => {
      setIsSorting(false);
      if (!isSearch) {
        setHighlighted1(-1);
        setHighlighted2(-1);
      }
    }, operations.length * operationsInterval);
  };

  return (
    <div className="sorting-visualizer">
      <Controls loadArray={loadArray} onSort={handleSort} onSearch={handleSearch} isSorting={isSorting} />
      <BarChart array={array} highlighted1={highlighted1} highlighted2={highlighted2} />
    </div>
  );
};

export default SortingVisualizer;
