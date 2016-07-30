
ranks = {
     data : [
          { "key" : '501' ,  "value" : "  Sydney" },
          { "key" : '502' ,  "value" : "  Perth" },
          { "key" : '503' ,  "value" : "  Albany" },
          { "key" : '504' ,  "value" : "  Collie" },
          { "key" : '506' ,  "value" : "  Brisbane" },
         
     ]
}




$( document ).ready(function() {
    var listitems = "<ol>";
   
    $.each(ranks.data, function(key, value){
        //listitems = listitems + "<option value='" + value.key + "'>" + value.value + "</option>";
        listitems = listitems + "<li><a href='javascript:showResult(\"" + value.key + "\");' >" + value.value + "</a></li>";
    });  
    listitems = listitems + "</ol>";
    $('#rankSelect').append(listitems);
});

   