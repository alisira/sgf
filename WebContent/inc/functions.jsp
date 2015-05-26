<script language=javascript> 

		function saveScrollCoordinates() {
        
    	}
		function scrollToCoordinates() {
        
    	}

		function check_date(field){
		var checkstr = "0123456789";
		var DateField = field;
		var Datevalue = "";
		var DateTemp = "";
		var seperator = "-";
		var day;
		var month;
		var year;
		var leap = 0;
		var err = 0;
		var i;
		   err = 0;
		   DateValue = DateField.value;
		   /* Delete all chars except 0..9 */
		   for (i = 0; i < DateValue.length; i++) {
			  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
			     DateTemp = DateTemp + DateValue.substr(i,1);
			  }
		   }
		   DateValue = DateTemp;
		   /* Always change date to 8 digits - string*/
		   /* if year is entered as 2-digit / always assume 20xx */
		   if (DateValue.length == 6) {
		      DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }
		   if (DateValue.length != 8) {
		      err = 19;}
		   /* year is wrong if year = 0000 */
		   year = DateValue.substr(4,4);
		   if (year == 0) {
		      err = 20;
		   }
		   /* Validation of month*/
		   month = DateValue.substr(2,2);
		   if ((month < 1) || (month > 12)) {
		      err = 21;
		   }
		   /* Validation of day*/
		   day = DateValue.substr(0,2);
		   if (day < 1) {
		     err = 22;
		   }
		   /* Validation leap-year / february / day */
		   if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
		      leap = 1;
		   }
		   if ((month == 2) && (leap == 1) && (day > 29)) {
		      err = 23;
		   }
		   if ((month == 2) && (leap != 1) && (day > 28)) {
		      err = 24;
		   }
		   /* Validation of other months */
		   if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
		      err = 25;
		   }
		   if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
		      err = 26;
		   }
		   /* if 00 ist entered, no error, deleting the entry */
		   if ((day == 0) && (month == 0) && (year == 00)) {
		      err = 0; day = ""; month = ""; year = ""; seperator = "";
		   }
		   /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
		   if (err == 0) {
		      DateField.value = day + seperator + month + seperator + year;
		   }
		   /* Error-message if err != 0 */
		   else {
		      alert("Fecha Invalida!");
		      DateField.select();
			  DateField.focus();
		   }
		}

		function replaceDotByComma(str) {	
			// var xstr = "";	
			// var xfield = str; 				
			// xstr = str.value;
			// xstr = xstr.replace(".", ",");
			// xfield.value = xstr;
			
		}
		
		function keyFloatCheck(eventObj, obj)
		{
			var keyCode		//  End -->
			var field = obj
			
			var xstr = "";	
			var xfield = obj; 				
			xstr = obj.value;
			if (xstr.indexOf(",")>0 && xstr.indexOf(".")>0){
				xstr = xstr.replace(".", "");
			}
			xstr = xstr.replace(".", ",");
			xfield.value = xstr;
			
			// Check For Browser Type
			if (document.all){ 
				keyCode=eventObj.keyCode
			}
			else{
				keyCode=eventObj.which
			}
		
			var str=obj.value
			if(keyCode==13){ 
				return false
			}
			
			if(keyCode==44 || keyCode==46){ 
				if ((str.indexOf(",")+str.indexOf("."))>0){
					return false
				}
			}
		
			if((keyCode<48 || keyCode >58)   &&   (keyCode != 44) && (keyCode != 8) && (keyCode!=46) && (keyCode!=0)){ 
				return false
			}
			
			return true
		}
				
		function keyIntegerCheck(eventObj, obj)
		{
			var keyCode
		
			if (document.all){ 
				keyCode=eventObj.keyCode
			}
			else{
				keyCode=eventObj.which
			}
			
			if(keyCode==13){ 
				return false
			}
			
			if((keyCode<48 || keyCode >58) && (keyCode != 8 ) && (keyCode!=0)){
				return false
			}
		
			return true
		}
		
		function keyEnterCheck(eventObj, obj)
		{
			var keyCode
					
			if (document.all){ 
				keyCode=eventObj.keyCode
			}
			else{
				keyCode=eventObj.which
			}		
		
			if(keyCode==13){ 
				return false
			}		
			return true
		}
		
		var double_delay = null;
		var delay_value = 1000; // 1 second.
		var click_count=0;
		
		function clickMade() {
		
		if (click_count>0) {
		    // it is a double click
			clearTimeout(double_delay);
			double_delay=null;
			return false
		  } else {
		    // it is a single click
			click_count++;
			double_delay = setTimeout("click_count=0",delay_value);
			return true

		  }

		}
		
		function firstFocus(){
			var paso = "false";			
			for (i = 0; i < document.forms.length; i++){
				for (j = 0; j < document.forms[i].elements.length; j++){					
					if (document.forms[i].elements[j].type =="text" || document.forms[i].elements[j].type =="select-one"){											
						document.forms[i].elements[j].focus();					
						break;
					}
				}				
				if (paso =="true"){
					break;
				}
			}
			
		}
		
		function fieldEmpty(field){
			var varField = field;
			var varValue = "";
			varValue = varField.value;
			if (varValue.length == 0) {	
				varField.value = 0;
			}else{					
				//var newStr = "";				
				//newStr = varValue.replace(".", ",");							
				//varField.value = newStr;
			}
			
			
		}
		
		function deleteZero(eventObj, obj)
		{
		
			var keyCode
		
			// Check For Browser Type
			if (document.all){ 
				keyCode=eventObj.keyCode
			}
			else{
				keyCode=eventObj.which
			}					
		
			var varField = obj;
			var varValue = "";
			varValue = varField.value;
			if (varValue.length == 1 && keyCode == 0) {	
				varField.value = "";
			}
			
			return true
		}
		
		
function rellenaCero(n){ //this function to fill in zeros so there will always be 2 digits for time display
		n=n+'';
		while(n.length<4)n="0"+n;
		return n;
}

//WANG-RADARTECH

function dF(ds){
    if (ds!=''){
	var d=document.getElementById(ds);
	var im=document.getElementById('viewDataPersonal:personalForm:imagenTrabajador');
	var ih=document.getElementById('viewDataPersonal:personalForm:fotoTrabajador');
	var eF=document.getElementById('viewDataPersonal:personalForm:eliminarFoto');
	ih.value="";
    var dad=im.parentNode;
    dad.removeChild(im);
    dad.removeChild(eF);
    d.innerHTML='<iframe id="iFoto" src="./FormularioFoto.jsf" frameborder="no" scrolling="no" width="100%" height="28px" class="archivo"> </iframe>';
    }


}	

</script>		
