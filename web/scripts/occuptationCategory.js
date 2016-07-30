
occupations = {
     "industry" : [
        { "key" : '' ,  "value" : " select industry" }, 
        { "key" : '1' ,  "value" : "Managers" },
        { "key" : '2' ,  "value" : "Professionals" },
        { "key" : '3' ,  "value" : "Technicians and Trades Workers" },
        { "key" : '4' ,  "value" : "Community and Personal Service Workers" },
        { "key" : '5' ,  "value" : "Clerical and Administrative Workers" },
        { "key" : '6' ,  "value" : "Sales Workers" },
        { "key" : '7' ,  "value" : "Machinery Operators and Drivers" },
        { "key" : '8' ,  "value" : "Labourers" },
               
     ],
     //oocupdefen 2 digit
    "occupation" : [
            { "key" : '' ,  "value" : " select occupation" },
            { "key" : '11  ' , "value" : "Chief Executives, General Managers and Legislators" },
            { "key" : '12  ' , "value" : "Farmers and Farm Managers" },
            { "key" : '13  ' , "value" : "Specialist Managers" },
            { "key" : '14  ' , "value" : "Hospitality, Retail and Service Managers" },
            { "key" : '21  ' , "value" : "Arts and Media Professionals" },
            { "key" : '22  ' , "value" : "Business, Human Resource and Marketing Professionals" },
            { "key" : '23  ' , "value" : "Design, Engineering, Science and Transport Professionals" },
            { "key" : '24  ' , "value" : "Education Professionals" },
            { "key" : '25  ' , "value" : "Health Professionals" },
            { "key" : '26  ' , "value" : "ICT Professionals" },
            { "key" : '27  ' , "value" : "Legal, Social and Welfare Professionals" },
            { "key" : '31  ' , "value" : "Engineering, ICT and Science Technicians" },
            { "key" : '32  ' , "value" : "Automotive and Engineering Trades Workers" },
            { "key" : '33  ' , "value" : "Construction Trades Workers" },
            { "key" : '34  ' , "value" : "Electrotechnology and Telecommunications Trades Workers" },
            { "key" : '35  ' , "value" : "Food Trades Workers" },
            { "key" : '36  ' , "value" : "Skilled Animal and Horticultural Workers" },
            { "key" : '39  ' , "value" : "Other Technicians and Trades Workers" },
            { "key" : '41  ' , "value" : "Health and Welfare Support Workers" },
            { "key" : '42  ' , "value" : "Carers and Aides" },
            { "key" : '43  ' , "value" : "Hospitality Workers" },
            { "key" : '44  ' , "value" : "Protective Service Workers" },
            { "key" : '45  ' , "value" : "Sports and Personal Service Workers" },
            { "key" : '51  ' , "value" : "Office Managers and Program Administrators" },
            { "key" : '52  ' , "value" : "Personal Assistants and Secretaries" },
            { "key" : '53  ' , "value" : "General Clerical Workers" },
            { "key" : '54  ' , "value" : "Inquiry Clerks and Receptionists" },
            { "key" : '55  ' , "value" : "Numerical Clerks" },
            { "key" : '56  ' , "value" : "Clerical and Office Support Workers" },
            { "key" : '59  ' , "value" : "Other Clerical and Administrative Workers" },
            { "key" : '61  ' , "value" : "Sales Representatives and Agents" },
            { "key" : '62  ' , "value" : "Sales Assistants and Salespersons" },
            { "key" : '63  ' , "value" : "Sales Support Workers" },
            { "key" : '71  ' , "value" : "Machine and Stationary Plant Operators" },
            { "key" : '72  ' , "value" : "Mobile Plant Operators" },
            { "key" : '73  ' , "value" : "Road and Rail Drivers" },
            { "key" : '74  ' , "value" : "Storepersons" },
            { "key" : '81  ' , "value" : "Cleaners and Laundry Workers" },
            { "key" : '82  ' , "value" : "Construction and Mining Labourers" },
            { "key" : '83  ' , "value" : "Factory Process Workers" },
            { "key" : '84  ' , "value" : "Farm, Forestry and Garden Workers" },
            { "key" : '85  ' , "value" : "Food Preparation Assistants" },
            { "key" : '89  ' , "value" : "Other Labourers" },
        ]         
    }


$( document ).ready(function() {
    var listitems = "";
    
    $.each(occupations.industry, function(key, value){
        listitems = listitems + "<option value='" + value.key + "'>" + value.value + "</option>";            
    });   
    $('#industrySelect').append(listitems);
    listitems = "";
     $.each(occupations.occupation, function(key, value){
        listitems = listitems + "<option value='" + value.key + "'>" + value.value + "</option>";            
    });   
    $('#occupationSelect').append(listitems);
});

   