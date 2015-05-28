<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %> 
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<html>
	<head>

		<title> Regularizacion de Compromiso</title>
	
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="Content-Style-Type" content="text/css">
	
		<link rel="STYLESHEET" href="estilos/transaccion.css" media="screen" type="text/css">
		<link rel="STYLESHEET" href="estilos/estilo-sisrepor.css" media="screen" type="text/css">
		<link rel="stylesheet" href="carpeta_temporal/commonuieditor.css" type="text/css" media="screen"/>
		<link rel="stylesheet" href="carpeta_temporal/temp.css" type="text/css" media="screen"/>
		<link rel="stylesheet" href="estilos/menu.css" type="text/css" media="screen"/>
		
		<script language="javascript" src="js/consultaImputaciones.js"></script>
		<script language="javascript" src="js/funciones.js"></script>
		<script language="javascript" src="js/registroPagoDirecto.js"></script>
	
	</head>

	<body class="app">
	
		<div id="fondo_opaco" style="position: absolute; top: 0px; left: 0px; width: 903px; height: 553px; display: none; opacity: 0.0; z-index: 9998; background-color: #111111;"></div>
		<div id="mensaje_emergente" style="background-color: #FFFFFF;position: absolute; opacity: 0.24; top: 200px; z-index: 9999; display: none; color: #666666;border: 10px solid; -moz-border-radius: 8px 8px 8px 8px; width:750px;" >
			<div class="close" onclick="mostrarVentana('cerrar','fondo_opaco','mensaje_emergente');borrarFilas('tablaDetalle')"></div>
		
	
			<div align="center">
				<h2 style="width: 362px;">Lista de Beneficiarios</h2>
			</div>
			
			<div align="center">
			    <table class="inputtdtext" border="0px" bordercolor="#8080c0" width="370px">
					<tr>
						<td bordercolor="#FFFFFF" width="70px"><input name="textoBuscado" id="textoBuscado" size="25" class="inputtext" type="text" onkeypress="return llenarListaBeneficiarios(event);"></td>
						<td bordercolor="#FFFFFF" align="right"><input name="" value="Buscar" onclick="f_buscar(this)" type="button" disabled="disabled"></td>
						<td bordercolor="#FFFFFF" nowrap="true" align="right"><input name="" value="Cerrar" onclick="self.close()" type="button" disabled="disabled"></td>
					</tr>
			  	</table>
			</div>
			
			<br>
			
			<div align="center">
			   
			   <table width="445px" id="encabezado" class="tablaEncabezado" border="1px" bordercolor="#c0c0c0" cellpadding="0px" cellspacing="0px" >
			  	   	<tbody>
			  	   		<tr>
					      	<td width="60px" >
								RIF
						  	</td>					  	
				   		  	<td width="241px">
								Razón Social
						  	</td>
				   		</tr>
			    	</tbody>
			    </table>
			    
				<div style="width: 670px; height: 300px; overflow: auto;">
					<table id="tablaDetalle"  class="tablaDetalle" bordercolor="#c0c0c0" cellpadding="0px" cellspacing="0px" >
					<logic:present name="listaBeneficiario" >
							<logic:iterate name="listaBeneficiario" id="temp">
								<tr id="detalle_dinamico" onclick="invocarURL('mantenimientoBeneficiario.do?accion=buscar&amp;cedula=<bean:write name="temp"  property="cedula"/>', actualizaFormulario);">
									<td width="59px" style="text-align: center;">
										<bean:write name="temp"  property="cedula"/>
									</td>
									<td width="250px" style="text-align: left;">
										<bean:write name="temp"  property="nombre"/>  
									</td>
									
								</tr>
							</logic:iterate>
						</logic:present>
					</table>
				</div>
			</div>
		</div>
		
		<div id="pagina" align="center">			
			
			<table width="850px" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td width="600"><h2 align="left">Registro de Gasto Orden de Pago Directa</h2></td>
					<td width="140"><A href="" onclick="cerrarse();">Ir a Menu Principal</A></td>
				</tr>
	  	  	</table>
			
			<html:form action="/registrarOrdenPagoDirecta" method="post">
			
			<input name="tipoNomina" id="tipoNomina" value="7" type="hidden">
			<input name="ff" id="ff" value="" type="hidden">
			<input name="caller" id="caller" value="registro" type="hidden">
			  
			<table id="expediente" class="tablaParametro" width="850px" border="0px" bordercolor="black" cellpadding="5px" cellspacing="0px">
				
				<tr>
					<td id="titulo" height="15px" width="145px" >Ejercicio Presupuestario:</td>
					
					<td id="contenido" width="397px">2011</td>	
								
					<td id="titulo" width="63px">Fecha:</td>
					
					<td id="contenido" align="left" width="235px">
						<script>hoy();document.write(fechaHoy);</script>
					</td>
					
				  </tr>
	
			  	<tr>
				  	<td id="titulo">
						<label class="question top_question">R.I.F</label>
					</td>
				  	<td>
						<input type="text" maxlength="15" size="15" id="cedula" name="cedula" readonly="readonly">
						<input value="..." tabindex="-1" name="listadoBeneficiario" id="listadoBeneficiario"  style="padding-left: 0px; padding-right: 0px; text-align: center;" onclick="bBuscar()" type="button">
					</td>
				</tr>
				
				<tr>
				
					<td id="titulo">
						<label class="question top_question">Razón Social</label>
					</td>			  				
			  		
			  		<td colspan="3">					
						<input type="text" maxlength="55"  size="55" id="nombre" name="nombre"  readonly="readonly">
					</td>
							
				
				</tr>
			  
				
			  
				<tr>
				
				<td id="titulo">
					<label class="question top_question">Banco</label>
				</td>
			  						
				<td id="titulo"> 
					<input type="text" maxlength="48"  size="55" id="denoBanco" name="denoBanco"  readonly="readonly" >
				</td>
				
				<td id="titulo">
					<label class="question top_question">Número de Cuenta</label>
				</td>			  
			  	<td>					
					<input type="text" maxlength="30"  size="35" id="cuenta" name="cuenta"  readonly="readonly" >
				</td>
				
				</tr>
			 
			  <tr>			  
				<td id="titulo" height="15px" width="103">Unidad Funcional:</td>
				<td id="contenido"> 
					<html:select name="portadaForm" property="idUnidadFuncional" tabindex="0" style="width: 250px;" styleId="idUnidadFuncional" >
						<html:option value="">Seleccionar Unidad Funcional</html:option>
						<logic:iterate name="UnidadFuncional" id="uf">
							<option value=<bean:write name="uf" property="idUnidadFuncional"/>><bean:write name="uf" property="descripcion"/></option>
						</logic:iterate>
					</html:select>
				</td>				
			  </tr>
			  
			  <tr>
				<td id="titulo" width="103" height="10px">Concepto de Pago:</td>
				<td class="contenido" style="text-align: left;" colspan="5">
					<span class="contenido" style="text-align: left;">
						<input name="conceptoPago" size="120" maxlength="500" class="caja_texto" id="conceptoPago" type="text">
					</span>          
				</td>
			  </tr>
				  
			  </table>
			  
			  <input name="tablavacia" id="tablavacia" value="SI" type="hidden">		  
			  
			  <div id="TituloyTabla" class="contenedorDetalle">
				
				<table cellspacing="0px" cellpadding="0px" border="1px" style="empty-cells: show; width: 712px;">
				
				  <tbody>
			        <tr align="center">
			          <td class="celda_encabezado" width="29">
					  	<input name="chk" value="checkbox" onclick="ChequearTodos(this)" id="chk" type="checkbox">
			          </td>
			          <td class="celda_encabezado" width="63">Código Concepto</td>
					  <td class="celda_encabezado" width="25">F.F</td>
			          <td class="celda_encabezado" width="308">Descripción del Concepto de Pago</td>
			          <td class="celda_encabezado" width="95">Asignaciones</td>
					  <td class="celda_encabezado" width="109">Deducciones</td>
			        </tr>
	
			      </tbody>
				</table>
				
				<div id="scrolltable" style=" background-color: rgb(204, 204, 204); visibility: inherit; border: medium hidden; height: 200px; width: 710px; overflow-y: auto; overflow-x: hidden;">
			
				  <div id="imputaciones" align="left">
					<table id="TablaConcepto" style="empty-cells: show;" border="0px" cellpadding="0px" cellspacing="0px" width="695px">
					  <tbody>
						 
						 <tr class="title" id="vacio_impu">
						 	<td colspan="7" class="sinDetalle" height="20" width="664">
						 		No Tiene Conceptos Ingresados Actualmente
						 	</td>
						 </tr>
					   
					  </tbody>
					</table>
					
					<table id="TablaSubTotal" style="empty-cells: show;" border="0px" cellpadding="0px" cellspacing="0px" width="695">
					  <tbody>
						<tr style="background-color: rgb(255, 255, 255);">
						  <td class="celda_encabezado" style="text-align: right; background-color: rgb(204, 204, 204);" height="20px" width="445"> 
						  		<b>Total Gasto Imputado:</b>
						  </td>
						  
						  <td class="detalle" height="20px" width="98">
						  	<b>
							  	<input name="mta" id="mta" value="0.00" size="15" type="text" align="right" class="textoTabla" readonly="readonly">
						  	</b>						  						  
						  </td>
						  
						  <td class="detalle"  width="99">
						  	<b>
						  		<input name="mtd" id="mtd" value="0.00" size="15" type="text" align="right" class="textoTabla" readonly="readonly">
						  	</b>
						  </td>
						  
						</tr>
					  </tbody>
					</table>
					
					<table id="TablaTotal" style="empty-cells: show;" border="0px" cellpadding="0px" cellspacing="0px" width="695">
					  <tbody>
						<tr style="background-color: rgb(255, 255, 255);">
						  <td width="610" height="20px" class="celda_encabezado" style="text-align: right; font-weight: bold; background-color: rgb(204, 204, 204);">
						  	<b> Pago Neto: </b>
						  </td>
						  <td class="renglons" style="text-align: right; font-weight: bold;" width="108">
						  	<b>
						  		<input name="montoNeto" id="montoNeto" value="0.00" size="15" type="text" align="right" class="textoTabla" readonly="readonly">
						  	</b>
						  </td>
						</tr>
					  </tbody>
					</table>
					
				  </div>
				</div>
				<div >
				  <table style="top: 0px;" id="aceptar" align="right" border="0px" cellpadding="0px" cellspacing="0px" height="30px" width="700px">
					<tbody>
					  <tr>
					  
						<td width="101" height="25px" id="agregar" style="text-align: left; padding-right: 1px;">
							<input name="button" value="Agregar" style="width: 100px; height: 30px; cursor: pointer; background-repeat: no-repeat; background-position: 3px 1px; padding-bottom: 4px;" onclick="f_agregar_concepto();" type="button">
						</td>
						
						<td width="100" height="25px" id="eliminar" style="text-align: left; display: table-cell;">
							<input name="button" value="Eliminar" style="width: 100px; height: 30px; cursor: pointer; background-position: 3px 1px; background-repeat: no-repeat; padding-bottom: 4px;" onclick=" f_delete_impu();" type="button">
						</td>
						
						<td width="499" height="65px" id="guardar" style="text-align: right; padding-right: 6px;">
							<input name="bGuardar" id="bGuardar" value="Guardar" style="width: 100px; height: 30px; cursor: pointer; background-position: 3px 1px; background-repeat: no-repeat; padding-bottom: 4px;" onclick="guardar();" type="button">
						</td>
						
					  </tr>
					</tbody>
				  </table>
				  
				</div>
				<table id="TablaEliminar" style="display: none;">
				</table>
			  </div>
			</html:form>
		</div>
	</body>
</html>