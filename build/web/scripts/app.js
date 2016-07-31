
$( document ).ready(function() {
     $('#go').click(function(){
         
         CalculateRanks();
         //$("#rankSelect").show();
         $("#part2").css("position","relative");
         $("#part2").css("top","0px");
         $("#part2").css("left","0px");
         $("#part2").fadeIn(2000);
        
         
         
         //window.location.href = "#part2";
          var aTag = $("a[name='part2']");
         $('html,body').animate({scrollTop: aTag.offset().top},'slow');
         
         
         

     })
     $(postcodeSelect).change(function(){
         $(industrySelect).focus();  
         enableGo();
     })
     $(industrySelect).change(function(){
         $(occupationSelect).focus();
         enableGo();
     })
     $(occupationSelect).change(function(){
          $("#go").focus();
         enableGo();
     })
     

});

 
function enableGo()
{
        var p1 = $(postcodeSelect).val();
        var p2 = $(industrySelect).val();
        var p3 = $(occupationSelect).val();
        if (p1 && p2 && p3)
        {
             $('#go').removeAttr("disabled");
        }
        else
        {
            $('#go').attr('disabled','disabled');           
        }
        
}  
    
 
    
function showResult(rankKey){
         
          var localLink = $(".local");
          $.each(localLink, function(key, value)
          {
             
         });
         
         $("#part3").fadeIn();
         
         
        
          $.each(localLink, function(key, value){
              
              $(value).attr("src", "content/gears.gif");
              var url = $(value).attr( "data-imagecode" ) + "?region=" + rankKey 
                +"&left=" + $(postcodeSelect).val()
                +"&right=" + rankKey 
                +"&industry=" + $(industrySelect).val()
                +"&occupation=" + $(occupationSelect).val();
              
              $(value).unbind();
              var _value =  $(value); 
              var _url = url;
              setTimeout(function(){                   
                   _value.click(function(){
                       //alert("clicked");
                        $('#chartDialog').modal('show');
                        $('#chartDialogImg').attr("src", url) = _value.attr("src", _url);  
                   });
                   _value.attr("src", _url);
              },100);              
                
              
                
            });  
         
    
         
         
         //window.location.href = "#part3";
         
           var aTag = $("a[name='part3']");
         $('html,body').animate({scrollTop: aTag.offset().top},'slow');
         
         
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




  
