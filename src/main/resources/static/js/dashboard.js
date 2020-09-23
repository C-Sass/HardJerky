/* globals Chart:false, feather:false */

(function () {
  'use strict'

  feather.replace()

  // Graphs
  const ctx = document.getElementById('myChart');

  // eslint-disable-next-line no-unused-vars
  const myChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: [
        'Jan',
        'Feb',
        'Mär',
        'Apr',
        'Mai',
        'Jun',
        'Jul',
        'Aug',
        'Sep',
        'Okt',
        'Nov',
        'Dez'
      ],
      datasets: [{
        data: [
          70,
          20,
          0,
          30,
          23,
          18,
          50,
          33,
          65,
          18,
          32,
          57
        ],
        lineTension: 0,
        backgroundColor: 'transparent',
        borderColor: '#007bff',
        borderWidth: 4,
        pointBackgroundColor: '#007bff'
      }]
    },
    options: {
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: true
          }
        }]
      },
      legend: {
        display: false
      }
    }
  });

  const monat = document.getElementById('monat').valueOf();

  const getDaysInMonth = function (month, year) {
    return new Date(year, month, 0).getDate();
  };
  if (monat !== 13) {
    const monatTage = getDaysInMonth(monat, 2020)
  }


}())
