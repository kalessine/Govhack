
$( document ).ready(function() {
     $('#go').click(function(){
         //$("#rankSelect").show();
         $("#part2").show();
         window.location.href = "#part2";
     })
     
     $('#info').click(function(){
         //$("#rankSelect").show();
         $("#part2").show();
         window.location.href = "#part2";
     })
     
     
});

    
    
function showResult(rankKey){
         $("#part3").show();
         
         
         var localLink = $(".local");
         
          $.each(localLink, function(key, value){
               var url = $(value).attr( "data-imagecode" ) + "?region=" + rankKey 
                +"&left=" + $(postcodeSelect).val()
                +"&right=" + rankKey 
                +"&industry=" + $(industrySelect).val()
                +"&occupation=" + $(occupationSelect).val();
                
               $(value).attr("src", url);
                
            });  
         
    
        //  google.charts.load('current', {'packages': ['corechart','geochart']});
        //  google.charts.setOnLoadCallback(drawCharts);
         
         google.charts.load("current", {packages:['corechart']});
         google.charts.setOnLoadCallback( function drawCharts() {
          
                console.log("chart start")
                var data = google.visualization.arrayToDataTable([
                    ['City',   'Rank' ],
                    ['Sydney',     1 ],
                    ['Perth',     2],
                    ['Hobart',    3],
                    ['Darwin',    4]  
                ]);

                var options = {
                    region: 'AU',
                    displayMode: 'markers',
                    sizeAxis: { minValue: 100, maxValue: 100 }
                    
                };
                var chart = new google.visualization.GeoChart(document.getElementById('chart_div'));
                chart.draw(data, options);
                
                data = google.visualization.arrayToDataTable([
                    ["Element", "Density", { role: "style" } ],
                    ["Iga", 8.94, "#b87333"],
                    ["Woolworths", 10.49, "silver"],
                    ["Coles", 19.30, "gold"],
                    ["Farmer Jacks", 21.45, "color: #e5e4e2"]
                ]);

                    var view = new google.visualization.DataView(data);
                    view.setColumns([0, 1,
                                    { calc: "stringify",
                                        sourceColumn: 1,
                                        type: "string",
                                        role: "annotation" },
                                    2]);

                    var options = {
                        title: "Supermarket preference breakdown",
                        width: 600,
                        height: 400,
                        bar: {groupWidth: "95%"},
                        legend: { position: "none" },
                    };
                    var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
                    chart.draw(view, options);
               console.log("chart end")     
                    
            });

         
      
       
      
         
         
         window.location.href = "#part3";
         
         
         
         
};

$('#tabSet a').click(function (e) {
    alert("Click")
    
    
        $(this).tab('show');
    
});

function tab(tab){
    $('#TabA').hide();
    $('#TabB').hide();
    $('#TabC').hide();
    $('#TabD').hide();
    $('#TabE').hide();
    
    var x = $('#Tab' + tab );
    x.show()
    
}




  
