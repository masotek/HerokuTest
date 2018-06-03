<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://bootswatch.com/4/sandstone/bootstrap.min.css" crossorigin="anonymous">
    <link rel="stylesheet" href="site.css" crossorigin="anonymous">
    <title>Challenge</title>
    <script>
        window.onload = function () {

            var chart = new CanvasJS.Chart("chartContainer", {
                animationEnabled: true,
                title:{
                    text: "CoinTrends Challenge 2018"
                },
                axisX: {
                    valueFormatString: "DD MMM,YY"
                },
                axisY: {
                    title: "Value (in USD)",
                    logarithmic: true,
                    includeZero: false,
                },
                legend:{
                    cursor: "pointer",
                    fontSize: 16,
                    itemclick: toggleDataSeries
                },
                toolTip:{
                    shared: true
                },
                data: [{
                    name: "Bitcoin",
                    type: "spline",
                    showInLegend: true,
                    dataPoints: [
                        <#list listOfBTC>
                            <#items as point>
                             { x: new Date(${point.time}), y: ${point.average} },
                            </#items>
                        </#list>
                    ]
                },
                    {
                        name: "Ethereum",
                        type: "spline",
                        showInLegend: true,
                        dataPoints: [
                        <#list listOfETH>
                            <#items as point>
                             { x: new Date(${point.time}), y: ${point.average} },
                            </#items>
                        </#list>
                        ]
                    },
                    {
                        name: "Litcoin",
                        type: "spline",
                        showInLegend: true,
                        dataPoints: [
                        <#list listOfLTC>
                            <#items as point>
                             { x: new Date(${point.time}), y: ${point.average} },
                            </#items>
                        </#list>
                        ]
                    }]
            });
            chart.render();

            function toggleDataSeries(e){
                if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
                    e.dataSeries.visible = false;
                }
                else{
                    e.dataSeries.visible = true;
                }
                chart.render();
            }

        }
    </script>

</head>
<body style="padding-top: 70px;">
<div id="chartContainer" style="height: 300px; width: 100%;"></div>
  <#list listOfGrowth>
      <#items as string>
<div class="container body-content">
    <div class="card text-white bg-success mb-3" style="max-width: 20rem;">
        <h5 class="card-header">${string}</h5>
    </div>
</div>
      </#items>
  </#list>
  <#list listOfDrop>
      <#items as string>
<div class="container body-content">
    <div class="card text-white bg-danger mb-3" style="max-width: 20rem;">
        <h5 class="card-header">${string}</h5>
    </div>
</div>
      </#items>
  </#list>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>