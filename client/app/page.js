"use client";

// pages/index.js
import './styles/global.css';
import SortingVisualizer from './components/SortingVisualizer';

export default function Home() {
  return (
    <div className="container">
      <h1 className="title">Algorithm Visualizer</h1>
      <SortingVisualizer />
    </div>
  );
}
