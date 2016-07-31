
occupations = {
     "industry" : [
        { "key" : '' ,  "value" : " select industry" }, 
        { "key" : 'H' , "value" : "Accommodation and food services " },
        { "key" : 'N' , "value" : "Administrative and support services " },
        { "key" : 'A' , "value" : "Agriculture" },
        { "key" : 'R' , "value" : "Arts and recreation services " },
        { "key" : 'E' , "value" : "Construction " },
        { "key" : 'P' , "value" : "Education and training " },
        { "key" : 'D' , "value" : "Electricity" },
        { "key" : 'K' , "value" : "Financial and insurance services " },
        { "key" : 'Q' , "value" : "Health care and social assistance " },
        //{ "key" : 'Z' , "value" : "Inadequately described/Not stated " },
        { "key" : 'J' , "value" : "Information media and telecommunications" },
        { "key" : 'C' , "value" : "Manufacturing " },
        { "key" : 'B' , "value" : "Mining " },
        { "key" : 'S' , "value" : "Other services " },
        { "key" : 'M' , "value" : "Professional" },
        { "key" : 'O' , "value" : "Public administration and safety " },
        { "key" : 'L' , "value" : "Rental" },
        { "key" : 'G' , "value" : "Retail trade " },
        { "key" : 'I' , "value" : "Transport" },
        { "key" : 'F' , "value" : "Wholesale trade " },


        
        
       /* 
        { "key" : '' ,  "value" : " select industry" }, 
        { "key" : '4' ,  "value" : "Community and Personal Service Workers" },
        { "key" : '5' ,  "value" : "Clerical and Administrative Workers" },     
        { "key" : '8' ,  "value" : "Labourers" },
        { "key" : '7' ,  "value" : "Machinery Operators and Drivers" },
        { "key" : '1' ,  "value" : "Managers" },
        { "key" : '2' ,  "value" : "Professionals" },
        { "key" : '6' ,  "value" : "Sales Workers" },    
        { "key" : '3' ,  "value" : "Technicians and Trades Workers" },
         */      
     ],
     //oocupdefen 2 digit
    "occupation" : [
            { "key" : '' ,  "value" : " select occupation" },
           { "key" : '21  ' , "value" : "Arts and Media Professionals" },
            { "key" : '32  ' , "value" : "Automotive and Engineering Trades Workers" },
            { "key" : '22  ' , "value" : "Business, Human Resource and Marketing Professionals" },
            { "key" : '42  ' , "value" : "Carers and Aides" },
            { "key" : '11  ' , "value" : "Chief Executives, General Managers and Legislators" },
            { "key" : '81  ' , "value" : "Cleaners and Laundry Workers" },
            { "key" : '56  ' , "value" : "Clerical and Office Support Workers" },
            { "key" : '82  ' , "value" : "Construction and Mining Labourers" },
            { "key" : '33  ' , "value" : "Construction Trades Workers" },
            { "key" : '23  ' , "value" : "Design, Engineering, Science and Transport Professionals" },
            { "key" : '24  ' , "value" : "Education Professionals" },
            { "key" : '34  ' , "value" : "Electrotechnology and Telecommunications Trades Workers" },
            { "key" : '31  ' , "value" : "Engineering, ICT and Science Technicians" },
            { "key" : '83  ' , "value" : "Factory Process Workers" },
            { "key" : '84  ' , "value" : "Farm, Forestry and Garden Workers" },
            { "key" : '12  ' , "value" : "Farmers and Farm Managers" },
            { "key" : '85  ' , "value" : "Food Preparation Assistants" },
            { "key" : '35  ' , "value" : "Food Trades Workers" },
            { "key" : '53  ' , "value" : "General Clerical Workers" },
            { "key" : '41  ' , "value" : "Health and Welfare Support Workers" },
            { "key" : '25  ' , "value" : "Health Professionals" },
            { "key" : '43  ' , "value" : "Hospitality Workers" },
            { "key" : '14  ' , "value" : "Hospitality, Retail and Service Managers" },
            { "key" : '26  ' , "value" : "ICT Professionals" },
            { "key" : '54  ' , "value" : "Inquiry Clerks and Receptionists" },
            { "key" : '27  ' , "value" : "Legal, Social and Welfare Professionals" },
            { "key" : '71  ' , "value" : "Machine and Stationary Plant Operators" },
            { "key" : '72  ' , "value" : "Mobile Plant Operators" },
            { "key" : '55  ' , "value" : "Numerical Clerks" },
            { "key" : '51  ' , "value" : "Office Managers and Program Administrators" },
            { "key" : '59  ' , "value" : "Other Clerical and Administrative Workers" },
            { "key" : '89  ' , "value" : "Other Labourers" },
            { "key" : '39  ' , "value" : "Other Technicians and Trades Workers" },
            { "key" : '52  ' , "value" : "Personal Assistants and Secretaries" },
            { "key" : '44  ' , "value" : "Protective Service Workers" },
            { "key" : '73  ' , "value" : "Road and Rail Drivers" },
            { "key" : '62  ' , "value" : "Sales Assistants and Salespersons" },
            { "key" : '61  ' , "value" : "Sales Representatives and Agents" },
            { "key" : '63  ' , "value" : "Sales Support Workers" },
            { "key" : '36  ' , "value" : "Skilled Animal and Horticultural Workers" },
            { "key" : '13  ' , "value" : "Specialist Managers" },
            { "key" : '45  ' , "value" : "Sports and Personal Service Workers" },
            { "key" : '74  ' , "value" : "Storepersons" }
        ]         
    }


$( document ).ready(function() {
    var listitems = "";
    var first=true;
    $.each(occupations.industry, function(key, value){
        listitems = listitems + "<option " + (first ? "disabled selected" : "") + " value='" + value.key + "'>" + value.value + "</option>";     
        first = false;       
    });   
    $('#industrySelect').append(listitems);
    listitems = "";
     first= true;
     $.each(occupations.occupation, function(key, value){
        listitems = listitems + "<option " + (first ? "disabled selected" : "") + " value='" + value.key + "'>" + value.value + "</option>";        
        first = false;    
    });   
    $('#occupationSelect').append(listitems);
});

   