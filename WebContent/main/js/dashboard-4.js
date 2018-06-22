
$(function () {
    "use strict";


    var ctx = document.getElementById("chart-0");
    ctx.height = 100;
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"],
            datasets: [{
                label: 'BTC',
                data: [5, 6, 4.5, 5.5, 3, 6, 4.5, 6, 8, 3, 5.5, 4, 6, 9, 12, 4, 3, 6, 4.5, 6, 8, 4.5, 5, 6, 4.5, 5.5,],
                datalabels: {
                    align: 'end',
                    anchor: 'start'
                },
                backgroundColor: '#f7931a',
            },
            {
                label: 'NEO',
                data: [5, 6, 4.5, 5.5, 3, 6, 4.5, 6, 8, 3, 5.5, 4, 6, 9, 12, 4, 3, 6, 4.5, 6, 8, 4.5, 5, 6, 4.5, 5.5,],
                datalabels: {
                    align: 'center',
                    anchor: 'center'
                },
                backgroundColor: '#58bf00',
            },

            {
                label: 'XRP',
                data: [5, 6, 4.5, 5.5, 3, 6, 4.5, 6, 8, 3, 5.5, 4, 6, 9, 12, 4, 3, 6, 4.5, 6, 8, 4.5, 5, 6, 4.5, 5.5,],
                datalabels: {
                    align: 'center',
                    anchor: 'center'
                },
                backgroundColor: '#346aa9',
            }]
        },
        options: {
            responsive: true,
            plugins: {
                datalabels: {
                    color: '#fff',
                }
            },
            legend: {
                display: true
            },
            scales: {
                xAxes: [{
                    stacked: true
                }],
                yAxes: [{
                    stacked: true
                }]
            },
            tooltips: {
                enabled: false
            }
        }
    });

});
