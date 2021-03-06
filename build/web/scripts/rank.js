
// ranks = {
//      data : [
//           { "key" : '501' ,  "value" : "  Sydney" },
//           { "key" : '502' ,  "value" : "  Perth" },
//           { "key" : '503' ,  "value" : "  Albany" },
//           { "key" : '504' ,  "value" : "  Collie" },
//           { "key" : '506' ,  "value" : "  Brisbane" },
         
//      ]
// }

function CalculateRanks(){
    $(".rankDataArea").show();
            
    var url = "rest/top5.json?industry=" + $(industrySelect).val() +"&occupation=" + $(occupationSelect).val();
    $.ajax({
    url: url,
    type: 'GET',
    success: function(data){ 
             var listitems = "<ol>";
               $.each(data, function(key, value){
                      listitems = listitems + "<li><a href='javascript:showResult(\"" + value.regionDataPK.sa4code + "\");' >" + value.sa4name + "</a></li>";
                });
                
                listitems = listitems + "</ol>";
                $('#rankSelect').html(listitems);
            },
            
            error: function(data) {
                
                $(".rankDataArea").hide();
                $('#rankSelect').html("");
                
                // if (window.location.protocol = "file:" )
                // {
                //    data = [{"averageAnnualMovement":-26.4206746,"averageAnnualMovementScore":0,"housePrice":500.0,"housePriceScore":1,"increaseBusScore":2,"increaseBusinesses":1.448551449,"jobVacancyMvmt":37.44520977,"jobVacancyScore":0,"medianIncomeScore":2,"medianIncomel":66000.0,"newBusinessIncrease":13.14328027,"newBusinessScore":0,"occUnemp1315":-10.15384615,"occUnemp1315Score":0,"patentIncrease":-35.67839196,"patentIncreaseScore":0,"regionDataPK":{"anzsco":"22","industry":"A","sa4code":"111"},"rentPrice":400.0,"rentPriceScore":1,"sa4name":"Newcastle and Lake Macquarie","totalBusinesses13":22022.0,"totalBusinesses14":22341.0,"totalScore":8,"tradeMarksIncrease":-1.814516129,"tradeMarksScore":2,"unempRateMvmt":5.247947194,"unempRateScore":0},{"averageAnnualMovement":-20.55010995,"averageAnnualMovementScore":1,"housePrice":507.0,"housePriceScore":1,"increaseBusScore":1,"increaseBusinesses":0.93232897,"jobVacancyMvmt":8.990228013,"jobVacancyScore":1,"medianIncomeScore":1,"medianIncomel":58900.0,"newBusinessIncrease":15.58915537,"newBusinessScore":1,"occUnemp1315":-17.57575758,"occUnemp1315Score":0,"patentIncrease":-25.33333333,"patentIncreaseScore":1,"rank":1,"regionDataPK":{"anzsco":"22","industry":"A","sa4code":"404"},"rentPrice":385.0,"rentPriceScore":1,"sa4name":"Adelaide - West","totalBusinesses13":16196.0,"totalBusinesses14":16347.0,"totalScore":8,"tradeMarksIncrease":-14.10788382,"tradeMarksScore":0,"unempRateMvmt":5.39644883,"unempRateScore":0},{"averageAnnualMovement":0.0,"averageAnnualMovementScore":0,"housePrice":1677.5,"housePriceScore":0,"increaseBusScore":1,"increaseBusinesses":1.363636364,"jobVacancyMvmt":36.5630713,"jobVacancyScore":2,"medianIncomeScore":3,"medianIncomel":97300.0,"newBusinessIncrease":20.32667877,"newBusinessScore":2,"occUnemp1315":-20.36199095,"occUnemp1315Score":0,"patentIncrease":-38.29787234,"patentIncreaseScore":0,"rank":2,"regionDataPK":{"anzsco":"22","industry":"A","sa4code":"128"},"rentPrice":855.0,"rentPriceScore":0,"sa4name":"Sydney - Sutherland","totalBusinesses13":19140.0,"totalBusinesses14":19401.0,"totalScore":10,"tradeMarksIncrease":-9.014675052,"tradeMarksScore":1,"unempRateMvmt":3.385547429,"unempRateScore":1},{"averageAnnualMovement":-30.58053134,"averageAnnualMovementScore":0,"housePrice":420.0,"housePriceScore":2,"increaseBusScore":0,"increaseBusinesses":0.093950624,"jobVacancyMvmt":0.0,"jobVacancyScore":0,"medianIncomeScore":2,"medianIncomel":72500.0,"newBusinessIncrease":23.96598376,"newBusinessScore":3,"occUnemp1315":-5.445544554,"occUnemp1315Score":1,"patentIncrease":-37.14285714,"patentIncreaseScore":0,"rank":3,"regionDataPK":{"anzsco":"22","industry":"A","sa4code":"311"},"rentPrice":395.0,"rentPriceScore":1,"sa4name":"Logan - Beaudesert","totalBusinesses13":19159.0,"totalBusinesses14":19177.0,"totalScore":10,"tradeMarksIncrease":-18.14671815,"tradeMarksScore":0,"unempRateMvmt":3.736951404,"unempRateScore":1},{"averageAnnualMovement":0.0,"averageAnnualMovementScore":0,"housePrice":1000.0,"housePriceScore":0,"increaseBusScore":1,"increaseBusinesses":1.130726868,"jobVacancyMvmt":36.5630713,"jobVacancyScore":2,"medianIncomeScore":3,"medianIncomel":122900.0,"newBusinessIncrease":16.6443178,"newBusinessScore":1,"occUnemp1315":-14.76510067,"occUnemp1315Score":0,"patentIncrease":-17.99065421,"patentIncreaseScore":1,"rank":4,"regionDataPK":{"anzsco":"22","industry":"A","sa4code":"121"},"rentPrice":600.0,"rentPriceScore":0,"sa4name":"Sydney - North Sydney and Hornsby","totalBusinesses13":51206.0,"totalBusinesses14":51785.0,"totalScore":11,"tradeMarksIncrease":-4.304347826,"tradeMarksScore":1,"unempRateMvmt":2.886875352,"unempRateScore":2}];
                    
                //     var listitems = "<ol>";
                //     $.each(data, function(key, value){
                //         listitems = listitems + "<li><a href='javascript:showResult(\"" + value.regionDataPK.sa4code + "\");' >" + value.sa4name + "</a></li>";
                //     });
                    
                //     listitems = listitems + "</ol>";
                //     $('#rankSelect').html(listitems);
                // }             
                
            }
    });
    
    
}

$( document ).ready(function() {1
    $('#rankSelect').html("<img src='content/gears.gif' style='width:100px;height:100px; /'><br>Processing");   
   
});

   