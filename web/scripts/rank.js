
ranks = {
     data : [
          { "key" : '501' ,  "value" : "  Sydney Ranked 1 " },
          { "key" : '502' ,  "value" : "  Perth Ranked 2 " },
          { "key" : '503' ,  "value" : "  Albany Ranked 3 " },
          { "key" : '504' ,  "value" : "  Collie Ranked 4 " },
          { "key" : '506' ,  "value" : "  Brisbabe Ranked 5 " },
         
     ]
}




$( document ).ready(function() {
    var listitems = "";
   
    $.each(ranks.data, function(key, value){
        //listitems = listitems + "<option value='" + value.key + "'>" + value.value + "</option>";
        listitems = listitems + "<a href='javascript:showResult(\"" + value.key + "\");' >" + value.value + "</a><br>";
    });  
    $('#rankSelect').append(listitems);
});

   