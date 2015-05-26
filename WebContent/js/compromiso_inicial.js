function cargarTabla(tabla){
		a= $('#TablaConcepto').DataTable( {
		data: tabla,
	    destroy: true,
	    paging: false,
        searching: false,
        paging: false,
		lengthChange: true,
		info: false,
		
        "columns": [
				    {data: 'cod_cate_presu', className: "detalle_center" },				    
				    {data: 'cod_unidad_ejecutora',  className: "detalle_center" },
				    {data: 'deno_unidad_ejecutora',  className: "detalle_center" },
				    {data: 'ff',  className: "detalle_center" },
				    {data: 'codPartida',  className: "detalle_center" },
				    {data: 'denoPartida',  className: "detalle_center" },
				    {data: 'dispoPresu',  className: "detalle_right" },
				    {data: 'monto',  className: "detalle_right" }
				  ]
	} );actualizaLista
}

function ObjetoGasto (cod_cate_presu, cod_unidad_ejecutora, deno_unidad_ejecutora, ff, codPartida,denoPartida, dispoPresu, monto) {
    this.cod_cate_presu=cod_cate_presu;    
    this.cod_unidad_ejecutora=cod_unidad_ejecutora;
    this.deno_unidad_ejecutora=deno_unidad_ejecutora;
    this.ff=ff
    this.codPartida=codPartida;
    this.denoPartida = denoPartida;
    this.dispoPresu = dispoPresu;
    this.monto=monto;
   
};

function borrarTabla(){
	document.getElementById('calcular').disabled=false;
	document.getElementById('borrar').disabled=true;
	
	var elim_vacio=document.getElementById("vacio_impu");
	if (elim_vacio!=null){
		document.getElementById("TablaConcepto").tBodies[0].deleteRow(elim_vacio);		
	}
	
	var tabla = document.getElementById("TablaConcepto")
	var numFilasCuerpo = tabla.tBodies[0].rows.length;
	
	for (i=0;i < numFilasCuerpo;i++){
		tabla.tBodies[0].deleteRow(0);	
	}
}

function borrarCampos(){	
	/*
	document.getElementById("montoTotal").value = formato_numerico(0);
	document.getElementById('codUnidadAdministradora').value='';
	document.getElementById('denoUAD').value='';
	document.getElementById('observacion').value='';
	*/
}

function insertar_fila(mensaje){
	
	if (mensaje == ""){//Si esta vacio asigna el mensaje por defecto
		mensaje = "<div style=\"text-align: center;\">Presione el bot&oacute;n calcular para obtener resumen de n&oacute;mina inicial</div>";
	}else{
		mensaje = "<div style=\"text-align: center;\">"+mensaje+"</div>";
	}
	
	tbody = document.getElementById("TablaConcepto").tBodies[0];
	var ultimaFila = tbody.rows.length;
	var pos = tbody.insertRow(ultimaFila);
	pos.id = "vacio_impu";
	pos.className="title odd";

	linea=pos.insertCell();	
	linea.colSpan=8;
	//htmlText="<div style=\"text-align: center;\">Presione el bot&oacute;n calcular para obtener resumen de n&oacute;mina inicial</div>";
	linea.innerHTML=mensaje;
	
	//inspector(linea);//Sirve para ver todas las propiedades del objeto
	
}

function guardar(){	
	var formu = document.forms['CompromisoInicialForm'];
	
	var continuar=validarFormulario();
	if (continuar){
		formu.submit();
	}
}

function calcular_resumen_inicial(){
	
	if (document.getElementById('codUnidadAdministradora').value==''){
		alert('Debe Seleccionar la Unidad Administradora');
		document.getElementById('codUnidadAdministradora').focus();
		return;
	}
	
	if (document.getElementById('observacion').value==''){
		alert('Debe Ingresar Una Observaci\u00f3n');
		document.getElementById('observacion').focus();
		return;
	}
	
	document.getElementById('calcular').disabled=true;
	document.getElementById('calcular').value='Calculando';
	document.getElementById('borrar').disabled=true;	
	
	borrarTabla();
	insertar_fila("Busqueda en proceso, favor esperar, gracias")
	
	setTimeout(function(){		
		invocaAjax(getContextPath() + '/gastoProyectado.do', cargarListaOG, 'codFrecuenciaPago=1 2 3');
		}, 50);
	
}

function cargarListaOG(req){
			
	if (req.getElementsByTagName("error").length > 0){
		borrarTabla();
		insertar_fila(req.getElementsByTagName("error")[0].childNodes[0].nodeValue);
	}else{
	
		con = req.getElementsByTagName("cod_cate_presu").length;
		
		if (con > 0){
			
			borrarTabla();
			
			var tabla = new Array(); 
			for (x=0; x < req.getElementsByTagName("cod_partida").length; x++){						
				codCatePresu = req.getElementsByTagName("cod_cate_presu")[x].childNodes[0].nodeValue;	
				cod_unidad_ejecutora = req.getElementsByTagName("cod_unidad_ejecutora")[x].childNodes[0].nodeValue;
				deno_unidad_ejecutora = req.getElementsByTagName("deno_unidad_ejecutora")[x].childNodes[0].nodeValue;
				codPartida = req.getElementsByTagName("cod_partida")[x].childNodes[0].nodeValue;
				deno_partida = req.getElementsByTagName("deno_partida")[x].childNodes[0].nodeValue;
				id_organismo = req.getElementsByTagName("id_organismo")[x].childNodes[0].nodeValue;
				ff = req.getElementsByTagName("ff")[x].childNodes[0].nodeValue;
				dispoPresu = formato_numerico(req.getElementsByTagName("dispo_presu")[x].childNodes[0].nodeValue);
				monto = formato_numerico_db(req.getElementsByTagName("monto")[x].childNodes[0].nodeValue);
				monto = formato_numerico(monto) +"<input name='monto' value=" + formato_numerico_db(monto) + " type='hidden'>";
				
				obje_gasto = new ObjetoGasto(codCatePresu, cod_unidad_ejecutora, deno_unidad_ejecutora, ff, codPartida, deno_partida, dispoPresu, monto );
				tabla[x] = obje_gasto;
			}

			cargarTabla(tabla);
			f_suma_total();	
		}
	}
	
	document.getElementById('calcular').disabled=true;
	document.getElementById('calcular').value='Calcular';
	document.getElementById('borrar').disabled=false;		
	
}

function f_suma_total()
{	// 
	/* Suma los montos de las imputaciones */
	var arreg_montos = document.getElementsByName("monto");
	var tabla = document.getElementById("TablaConcepto");
	var montoTotal = document.getElementById("montoTotal");
	var nfilas = arreg_montos.length;
 	var sum_monto=0;
	 
 	for (var i=0;i<nfilas;i++){ 	
		sum_monto += parseFloat(arreg_montos[i].value);
	}
 	
 	montoTotal.value = formato_numerico(sum_monto);
 	
}


function validarFormulario() {
	
	if (document.getElementById('codUnidadAdministradora').value==''){
		alert('Debe Seleccionar la Unidad Administradora');
		document.getElementById('codUnidadAdministradora').focus();
		return false;
	}
	
	if (document.getElementById('observacion').value==''){
		alert('Debe Ingresar Una Observaci\u00f3n');
		document.getElementById('observacion').focus();
		return false;
	}
	
	var tabla = document.getElementById("TablaConcepto")
	var numFilasCuerpo = tabla.tBodies[0].rows.length;
	//alert(numFilasCuerpo);
	
	if (numFilasCuerpo < 1){
		alert('Favor Calcular N\u00f3mina Inicial');
		return false;	
	}
	
	return true;
	
}