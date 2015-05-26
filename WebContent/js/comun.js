var id_intervalo="";
var text1 =""; 
var text2 ="";
var text3 ="";
var text4 ="";
var text5 ="";
var swShowVentana= false;

function ObjetoBusqueda (urlListaActua, funcionActua, indiceReq, parametros) {
    this.urlListaActua=urlListaActua;
	this.funcionActua=funcionActua;
	this.indiceReq= indiceReq;
	this.parametros=parametros;
};

$(document).ready(function(){
    $("#textoBuscado").keypress(function(evento){
    	if (evento.which == 0 || evento.which == 13 ){
    		return true;
    	}else{
    		if (validaTexto(evento, 'A_N', '-%')){
    			borrarFilas('tablaDetalle');

    			if (evento.which == 8){
    				textoBuscar  = textoBuscar.substr(0, textoBuscar.length-1);	
    			}else{
    				textoBuscar += String.fromCharCode(evento.which);
    			}		

    			if (textoBuscar!= ""){
    				//addNode('tablaDetalle', "Busqueda en proceso, favor esperar, gracias_%_"+100, "", "", "", "", "","");  
    				//invocaAjax(getContextPath() + objeBusqueda.urlListaActua, actualizaLista, objeBusqueda.parametros+textoBuscar);
    			}    			
    			return true;

    		}else{
    			alert("ERROR: No se acepta el caracter");
    			return false;
    		}
    	}    	
    });
    
    $("#btnBuscar").click(function(){
    	borrarFilas('tablaDetalle');
    	addNode('tablaDetalle', "Busqueda en proceso, favor esperar, gracias_%_"+100, "", "", "", "", "","");  
		invocaAjax(getContextPath() + objeBusqueda.urlListaActua, actualizaLista, objeBusqueda.parametros+textoBuscar);
    });
    
    
});

function actualizaLista(req){
	
	if (req.getElementsByTagName("error").length > 0){
		borrarFilas('tablaDetalle');
		addNode('tablaDetalle', req.getElementsByTagName("error")[0].childNodes[0].nodeValue+"_%_"+100, "", "", "", "", "");				
	}else{
		con = req.getElementsByTagName(objeBusqueda.indiceReq).length;

		borrarFilas('tablaDetalle');

		if (con > 0){					
			objeBusqueda.funcionActua(req);
		}
	}
}

function invocaAjax(url, funcion, parametros){
	
	$.ajax({
		  url: url,
		  type: 'POST',
		  async: true,
		  data: parametros,
		  dataType: "xml",
		  success: funcion
		});
}

function errorRequest(){
	alert("Hubo un Error en la Solicitud, favor revisar la data e intente nuevamente, gracias");
}

function validaTexto(eventoCampo, tipoValida, textoAdicional) {

	var ubicacion
	var enter = "\n"
	var campo;

	if (tipoValida == "N"){
		var caracteres = "1234567890.";
	}else if (tipoValida == "A_N") {
		var caracteres = "abcdefghijklmnopqrstuvwxyz�1234567890 ABCDEFGHIJKLMNOPQRSTUVWXYZ�����������";
	}else if (tipoValida == "A_N_ENTER") {
		var caracteres = "abcdefghijklmnopqrstuvwxyz�1234567890 ABCDEFGHIJKLMNOPQRSTUVWXYZ�����������" + String.fromCharCode(13);//Enter
	}
	
	if (textoAdicional != ""){
		caracteres += textoAdicional;
	}
	
	caracteres += String.fromCharCode(8) + String.fromCharCode(0);//Backspace y Flechas
	
	if (typeof eventoCampo.value != 'string'){
		campo = String.fromCharCode(eventoCampo.which);
	}else{
		campo = eventoCampo.value;
	}
	
	var contador = 0
	for (var i=0; i < campo.length; i++) {
		ubicacion = campo.substring(i, i + 1)
		if (caracteres.indexOf(ubicacion) != -1) {
			contador++
		}else{			
			if (typeof eventoCampo.value != 'string'){				
				return false;
			}else{
				eventoCampo.value = "";
				eventoCampo.focus();
				alert("ERROR: No se acepta el caracter");
				break;
			}
		}
	}
	return true;
}

function borrarFilas(tabla){
	var ufila= parseInt(document.getElementById(tabla).rows.length);
	for(z=0; z < ufila ; z++){
		document.getElementById(tabla).deleteRow(0);
	}
}


var textoBuscar;
function abrirMB(div, t1, t2, t3, t4, t5){
	
	//Todas los posibles objetos donde meter la informacion cuando se devuelve
	text1 = document.getElementById(t1);
	text2 = document.getElementById(t2);
	text3 = document.getElementById(t3);
	text4 = document.getElementById(t4);
	text5 = document.getElementById(t5);
	
	textoBuscar = "";
	mostrarVentana('fondo_opaco',div);
	document.getElementById("textoBuscado").value = "";
	document.getElementById("textoBuscado").focus();

}

function hoy(){
	var fechaActual = new Date();
      
    dia = fechaActual.getDate();
    mes = fechaActual.getMonth() + 1 ;
    anno = fechaActual.getFullYear();
   
    if (dia <10) dia = "0" + dia;
    if (mes <10) mes = "0" + mes;   
   
    fechaHoy = dia + "/" + mes + "/" + anno;
      
    return fechaHoy;
}

var id_intervalo="";
var opacidad=0;

function mostrarVentana(fondo_opaco,mensaje_emergente){
	//alert(mensaje_emergente);	
	
	var div_fondo_opaco =  document.getElementById(fondo_opaco);	
	var div_mensaje_emergente =  document.getElementById(mensaje_emergente);
	
	//var width = document.body.clientWidth;//Propiedad de los objetos
	var width = $(window).width()
	var height= document.body.clientHeight;
	tempWidth = parseInt(div_mensaje_emergente.style.width);
	
	var left= ((width-tempWidth)/ 2) +"px";
	
	if (window.innerWidth) //si el browser supports window.innerWidth		
		var top= ((window.innerHeight-411-50)/2)+"px";
	else if (document.all) //sino si browser supports document.all (IE 4+)
		var top= ((document.body.clientHeight-411)/2)+"px";
			
	div_mensaje_emergente.style.top=top;
	div_mensaje_emergente.style.left=left;
	div_fondo_opaco.style.height=height;
	div_fondo_opaco.style.width=width;	
	
	swShowVentana = !swShowVentana;
	
	if (swShowVentana) {
		$('#'+mensaje_emergente).fadeIn(350);
		$('#'+fondo_opaco).fadeIn(200);
	}else{
		$('#'+mensaje_emergente).fadeOut(350);
		$('#'+fondo_opaco).fadeOut(200);
	}
	
}

function addNode(tabla, col1, col2, col3, col4, valorOculto ){

	var tb =  document.getElementById(tabla);
	
	var col1 = col1.split("_%_");
	var col2 = col2.split("_%_");

	var cTR=document.createElement("tr");
	cTR.setAttribute('id', 'detalle_dinamico');
	
	var cTD1=document.createElement("td");
	cTD1.setAttribute('width', col1[1]+"%");
	cTD1.setAttribute('style', "text-align: center;");	
	
	var cTD2=document.createElement("td");
	cTD2.setAttribute('width', col2[1]+"%");
	cTD2.setAttribute('style', "text-align: center;");	
	
	var txtNode1=document.createTextNode(col1[0]);
	var txtNode2=document.createTextNode(col2[0]);
	
	cTD1.appendChild(txtNode1);
	cTD2.appendChild(txtNode2);
	
	cTR.appendChild(cTD1);
	cTR.appendChild(cTD2);
	
	if (col3!=null ){
		var col3 = col3.split("_%_");		
		var cTD3=document.createElement("td");
		cTD3.setAttribute('width', col3[1]+"%");
		cTD3.setAttribute('style', "text-align: left;");		
		var txtNode3=document.createTextNode(col3[0]);
		cTD3.appendChild(txtNode3);
		cTR.appendChild(cTD3);
		
	}
	
	if (col4 != null ){
		var col4 = col4.split("_%_");
		var cTD4=document.createElement("td");
		cTD4.setAttribute('width', col4[1]+"%");
		cTD4.setAttribute('style', "text-align: left;");		
		var txtNode4=document.createTextNode(col4[0]);
		cTD4.appendChild(txtNode4);
		cTR.appendChild(cTD4);
	}
	
	if (valorOculto != null ){		
		var cTD5=document.createElement("td");		
		//cTD5.setAttribute('display', 'none');
		cTD5.setAttribute('style', "display: none;");	
		var txtNode5=document.createTextNode(valorOculto);
		cTD5.appendChild(txtNode5);
		cTR.appendChild(cTD5);
	}
	
	tb.appendChild(cTR);
	
}

function formato_numerico(valor){
    
	var num = valor ; 
	var nuevo_numero = "";
	var parte_entera = "";
	
	if (num.toString().indexOf('E')!= -1 ){
		temp = num.split("E");
		multi=1;
		for (c=0;c< parseInt(temp[1]);c++){
			multi += "0"; 
		}
		num = parseFloat(temp[0]) * parseFloat(multi);
	}
	
	if (num.toString().indexOf(',')!=-1 ){
		num = num.replace(/\./g, "");
		num = num.replace(/\,/g, ".");		
	}
	
    if(!isNaN(num)){
		
		if (num.toString().indexOf('.')!=-1 ){			
			num_dividido = num.toString().split('.');	
			entera_par =  num_dividido[0].substring(0,num_dividido[0].length);	
			conTemp = 1;
			for(i= num_dividido[0].length-1; i >= 0; i=i-1){

				if (conTemp==3){
					if (Trim(num_dividido[0].substring(i,i+1) ) == "-"){
						parte_entera = Trim(num_dividido[0].substring(i,i+1) ) + parte_entera ;	
					}else{						
						if (Trim(num_dividido[0].substring(i-1,i))  == "" || Trim(num_dividido[0].substring(i-1,i))  == "-"   ){
							parte_entera = Trim(num_dividido[0].substring(i,i+1) ) + parte_entera ;
						}else{						
							parte_entera = "." + Trim(num_dividido[0].substring(i,i+1) ) + parte_entera ;
						}
						conTemp	=1;
					}
				}else{
					parte_entera = Trim(num_dividido[0].substring(i,i+1) ) + parte_entera ;
					conTemp++;
				}
			}	
			
			if (num_dividido[1].length >= 2 ){
				dec_par =  num_dividido[1].substring(0,2);
			} else if (num_dividido[1].length = 1 ){
				dec_par =  num_dividido[1].substring(0,1) + "0";
			}			
			nuevo_numero = parte_entera + "," + dec_par;
		}else{			
			conTemp = 1;
			for(i= num.toString().length-1; i >= 0; i=i-1){
				if (conTemp==3){					
					if (Trim(num.toString().substring(i,i+1) ) == "-"){
						parte_entera = Trim(num.toString().substring(i,i+1) ) + parte_entera ;	
					}else {				
						
						if (Trim(num.toString().substring(i-1,i))  == "" || Trim(num.toString().substring(i-1,i))  == "-" ){
							parte_entera = Trim(num.toString().substring(i,i+1) ) + parte_entera ;							
						}else{						
							parte_entera = "." + Trim(num.toString().substring(i,i+1) ) + parte_entera ;
						}
						conTemp	=1;
					}
				}else{
					parte_entera = Trim(num.toString().substring(i,i+1) ) + parte_entera ;
					conTemp++;
				}
			}			
			nuevo_numero = parte_entera + "," + "00";			
		}
		
		return nuevo_numero;
    }else{
		alert('Solo se permiten numeros');
		return null;
    }
}


function formato_numerico_db(valor){
	nuevo_numero = valor;
	//var nuevo_numero = valor.replace(/\./g, "");
	//nuevo_numero = nuevo_numero.replace(/\,/g, ".");	
	return nuevo_numero;
}

function Trim(valor){
    while ((valor.substring(0,1)==" " || valor.substring(0,1)=="\r\n")&&(valor.length>0))
	 {valor=valor.substring(1,valor.length);}
    while ((valor.substring(valor.length-1,valor.length)==" ")&&(valor.length>0))
  	 {valor=valor.substring(0,valor.length-1);}
	 return valor;
  }

function getContextPath() {
   return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}
	

function imprime(){	
    window.print();    
}

function cerrarse(){ 
	window.close(); 
} 

