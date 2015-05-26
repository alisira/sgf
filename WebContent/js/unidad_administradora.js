var textoBuscar='';

objeBusqueda = new ObjetoBusqueda('/mantenimientoUnidadAdministradora.do', actualizaListaAdministradoras, "codUnidadAdministradora", 'accion=buscar&denominacion=') 

function actualizaListaAdministradoras(req){
	
	for (x=0; x < req.getElementsByTagName("idUnidadAdministradora").length; x++){
		codUnidadAdministradora = req.getElementsByTagName("codUnidadAdministradora")[x].childNodes[0].nodeValue;
		denominacion = req.getElementsByTagName("denominacion")[x].childNodes[0].nodeValue;
		idUnidadAdministradora = req.getElementsByTagName("idUnidadAdministradora")[0].childNodes[0].nodeValue;
		addNode('tablaDetalle', codUnidadAdministradora+"_%_"+20, denominacion+"_%_"+80,null,null, idUnidadAdministradora);
		//function addNode(tabla, col1, col2, col3, col4, valorOculto ){
	}
	
	$("#tablaDetalle tr").click(function(){
		document.getElementById("codUnidadAdministradora").value = this.cells[0].textContent;//Primera columna de la tabla
		document.getElementById("denoUAD").value = this.cells[1].textContent;//Segunda columna de la tabla
		document.getElementById("idUnidadAdministradora").value = this.cells[2].textContent;//Columna oculta de la tabla
			
		mostrarVentana('fondo_opaco','unidad_administradora');					
		borrarFilas('tablaDetalle');
    	
    })
	
}

