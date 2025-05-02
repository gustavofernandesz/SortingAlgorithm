// components/BarChart.js
const BarChart = ({ array, highlighted1, highlighted2 }) => {
  return (
    <div className="bar-chart">
      {array.map((value, index) => (
        <div
          key={index}
          className={(index == highlighted1 || index == highlighted2) ? "highlighted-bar" : "bar"}
          style={{ height: `${value * 3}px` }}
        ></div>
      ))}
    </div>
  );
};

export default BarChart;
