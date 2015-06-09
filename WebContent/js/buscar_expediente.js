var textoBuscar='';

$(document).ready(function(){	
	objeBusqueda = new ObjetoBusqueda('/puntoDecision.do', actualizaListaExpedientes, "expediente", 'accion=buscar&estatus=0&expediente=');
	
	function actualizaListaExpedientes(req){
		
		for (x=0; x < req.getElementsByTagName("expediente").length; x++){
			expediente = req.getElementsByTagName("expediente")[x].childNodes[0].nodeValue;
			fecha_reg = req.getElementsByTagName("fecha_reg")[x].childNodes[0].nodeValue;
			cod_proceso = req.getElementsByTagName("cod_proceso")[x].childNodes[0].nodeValue;
			deno_proceso = req.getElementsByTagName("deno_proceso")[x].childNodes[0].nodeValue;
			observacion = req.getElementsByTagName("observacion")[x].childNodes[0].nodeValue;
			addNode('tablaDetalle', expediente+"_%_"+10, fecha_reg+"_%_"+10, deno_proceso+"_%_"+20, observacion+"_%_"+60, cod_proceso);
		}
		
		$("#tablaDetalle tr").click(function(){
			var form = document.forms['ParametrosBusquedaForm'];
	        document.getElementById("expediente").value = this.cells[0].textContent;//Primera columna de la tabla
	    	document.getElementById("proceso").value = this.cells[4].textContent;//Columna oculta de la tabla
	    	document.getElementById("accion").value = "imprimir";
	    	form.submit();
		})
	}
		
});