// components/Controls.js
import { useEffect, useState } from 'react';

const Controls = ({ loadArray, onSort, onSearch, isSorting }) => {
  const [algorithm, setAlgorithm] = useState('bubbleSort');
  const [arrayNumber, setArrayNumber] = useState('1');
  const [target, setTarget] = useState(''); // State for binary search target

  useEffect(() => {
    loadArray(arrayNumber, (algorithm == 'binarySearch'));
  }, [algorithm, arrayNumber]);

  const handleSortClick = () => {
    if (algorithm === 'binarySearch') {
      onSearch(arrayNumber, target); // Pass target for binary search
    } else {
      loadArray(arrayNumber);
      onSort(algorithm, arrayNumber);
    }
  };

  return (
    <div className="controls">
      <select
        value={arrayNumber}
        onChange={(e) => setArrayNumber(e.target.value)}
        disabled={isSorting}
      >
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        <option value="6">6</option>
        <option value="7">7</option>
        <option value="8">8</option>
        <option value="9">9</option>
        <option value="10">10</option>
      </select>
      <select
        value={algorithm}
        onChange={(e) => setAlgorithm(e.target.value)}
        disabled={isSorting}
      >
        <option value="bubbleSort">Bubble Sort</option>
        <option value="quickSort">Quick Sort</option>
        <option value="insertionSort">Insertion Sort</option>
        <option value="heapSort">Heap Sort</option>
        <option value="radixSort">Radix Sort</option>
        <option value="binarySearch">Binary Search</option>
      </select>
      {algorithm === 'binarySearch' && (
        <div className="binary-search-input">
          <input
            type="number"
            value={target}
            onChange={(e) => setTarget(e.target.value)}
            placeholder="Enter target value"
            disabled={isSorting}
          />
        </div>
      )}

      <button onClick={handleSortClick} disabled={isSorting}>
        {algorithm === 'binarySearch' ? 'Search' : 'Sort'}
      </button>
    </div>
  );
};

export default Controls;
