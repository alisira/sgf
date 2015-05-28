
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %> 
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ page import="sigefirrhh.login.LoginSession" %>
<%@ page import="sigefirrhh.sistema.ValidadorSesion" %>

<% 
{
	String rutaTemp = null;
	
	if ((LoginSession)session.getAttribute("loginSession")!=null){
	
		if ( !((LoginSession)session.getAttribute("loginSession")).isValid() ) {
			response.sendRedirect("/sigefirrhh/error.html");
		}else{
			ValidadorSesion vs = new ValidadorSesion();
			HttpServletRequest httpServletRequest = (HttpServletRequest)pageContext.getRequest();			
			boolean temp = vs.validarPermiso(httpServletRequest);			
			//System.out.println("Resultado:" + temp);
			
			if (!temp){
				
				response.sendRedirect("/sigefirrhh/sinpermiso.jsp");

			}else{
				%>
				<jsp:include page="/inc/top.jsp" />

<%
				rutaTemp = "/" + request.getRequestURI().split("/")[1] ;
				//System.out.println(request.getRequestURI());
			}
		}
		
	}else{
		response.sendRedirect("/sigefirrhh/error.html");
	}

%>

		<title> Registro de Compromiso Inicial</title>
		<script language="javascript" src="<%=rutaTemp %>/js/jquery.dataTables.min.js"></script>
		<script language="javascript" src="<%=rutaTemp %>/js/compromiso_inicial.js"></script>		

<% } %>	


		<div id="fondo_opaco" style="position: absolute; top: 0px; left: 0px; width: 903px; height: 553px; display: none; opacity: 0.70; z-index: 9; background-color: #111111;">
		</div>
	
		<%@include file="../unidad_administradora/buscar_unidad_administradora.jsp" %>
		
	
		<div id="pagina" align="center" >
		
			<div id="contenido"  style="width:960px">	
			
			<html:form action="/compromisoInicial" method="post"> <!-- <form id="resumenNominaInicial" name="resumenNominaInicial" action="" method="post"> -->
				<%@include file="origen_presupuestario.jsp" %>	
				<input name="idTipoFondo" id="idTipoFondo" value="1" type="hidden">
				<input name="ff" id="ff" value="1" type="hidden">				
				<input name="tablavacia" id="tablavacia" value="SI" type="hidden">
				<input name="accion" id="accion" value="guardar" type="hidden">
				<input name="codFrecuenPago" id="codFrecuenPago" value="1 2 3" type="hidden">
				
				<div id="conte_para" class="conte_para">					
					
					<div class="conte_div_left" style="width: 31%; height: 34px;" >
	                    <label class="conte_label">Fecha: <script>hoy();document.write(fechaHoy);</script> </label>	                     
	                </div>
	
					<div class="conte_div_left" style="width: 31%; height: 34px;" >
	                    <label class="conte_label">Ejercicio Presupuestario: <bean:write name="ano"/> </label>    
	                </div>
	                
	                <div class="conte_div_left" style="width: 31%; height: 34px;" >
	                    <label class="conte_label">Tipo de Fondo </label>
	                    <select id="tipoFondo" name="tipoFondo">
	                    	<option value=0>Personal Activo</option>
	                    	<option value=1>Jubilados y Pensionados</option>
						</select>	                     
	                </div>
	                
	                <div class="conte_div_left" style="width: 40%; height: 34px;">
	                    <label class="conte_label">Tipo de Documento </label>
						<html:select name="CompromisoInicialForm" property="idTipoDocumento" tabindex="0" style="width: 235px;" styleId="idTipoDocumento">
							<html:option value="">Seleccionar Tipo de Documento</html:option>
							<logic:iterate name="TipoDocumentos" id="td">
								<option	value=<bean:write name="td" property="codigo"/>>
									<bean:write name="td" property="denominacion" />
								</option>
							</logic:iterate>
						</html:select>

					</div>
	                
	                <div class="conte_div_left" style="width: 31%; height: 34px;">
	                    <label class="conte_label">Nro.Documento </label>
	                    <input type="text" name="documento" id="documento" size="15" maxlength="15">
	                </div>
	                
	                <div class="conte_div_left" style="width: 25%; height: 34px;" >
	                    <label class="conte_label">Origen Presupuestario: </label>
	                    <input style="width: 50px " value="..." tabindex="-1" name="listadoUnidadAdministradora" id="listadoUnidadAdministradora" onclick="abrirMB('origenPresupuestario', '', '', '')" type="button">								                     
	                </div>	                
	                
	                <div class="conte_div_left" style="width: 98%;">
	                    <label class="conte_label">Unidad Administradora: </label>
	                    <input type="text" maxlength="8" size="8" id="codUnidadAdministradora" name="codUnidadAdministradora" readonly="readonly" value = "">
	                    <input type="hidden" id="idUnidadAdministradora" name="idUnidadAdministradora" value = "">
						<input value="..." tabindex="-1" name="listadoUnidadAdministradora" id="listadoUnidadAdministradora" onclick="abrirMB('unidad_administradora', 'codUnidadAdministradora', 'denoUAD', 'idUnidadAdministradora')" type="button">
						<label class="conte_label">&nbsp;Denominacion:</label>
						<input type="text" readonly="readonly" name="denoUAD" id="denoUAD" size="49" maxlength="65">							                     
	                </div>
	                
	                <div class="conte_div_left" style="width: 98%;">
	                    <label class="conte_label">Observaci&oacute;n: </label>						
						<input type="text" name="observacion" id="observacion" size="87" maxlength="100">							                     
	                </div>
	                 
            	</div>
			  
				<div id="TituloyTabla" class="contenedorDetalle">	
			
		  			<div id="imputaciones" align="left">
						<table id="TablaConcepto" class="tablesorter tablesorter-blue" role="grid" >
			  				<thead>
								<tr id="encabezado" align="center">						    
								    <td class="celda_encabezado" width="10%">Cod.Cat.Pres</td>									    
									<td class="celda_encabezado" width="7%">UEL</td>
									<td class="celda_encabezado" width="22%">Deno UEL</td>
									<td class="celda_encabezado" width=5%">F.F</td>
									<td class="celda_encabezado" width="10%">Cod.Partida</td>
									<td class="celda_encabezado" width="25%">Deno Partida</td>
									<td class="celda_encabezado" width="10%">Disp.Presu</td>
							      	<td class="celda_encabezado" width="10%">Monto</td>						      	
				        		</tr>
			        		</thead>
			        		
			        		<tbody>
				        		<tr id="vacio_impu" class="title odd">
						 			<td colspan="8" >
						 				<div  style="text-align: center;" >
						 					Presione el bot&oacute;n calcular para obtener resumen de n&oacute;mina inicial
						 				</div>
						 			</td>
						 		</tr>
				   			</tbody>
				  			
						</table>			
				
						<table id="TablaTotal" width="100%" style="empty-cells: show;" border="0px" cellpadding="0px" cellspacing="0px">
						  <tbody>
							<tr style="background-color: rgb(255, 255, 255);">
							  <td width="83%" height="20px" class="celda_encabezado" style="text-align: right; font-weight: bold; background-color: rgb(204, 204, 204);">
							  	<b> Total: </b>
							  </td>
							  <td class="renglons" style="text-align: right; font-weight: bold;" width="17%">
							  	<b>
							  		<input name="montoTotal" id="montoTotal" value="0.00" size="15" type="text" align="right" class="textoTabla" readonly="readonly">
							  	</b>
							  </td>
							</tr>
					  		</tbody>
						</table>
				
		  			</div>					
					
			  	</div>
			  	
			  	<div id="botoneria" style="width:960px">				
					<input name="button" id="calcular" value="Calcular" style="width: 100px; height: 30px; cursor: pointer; background-repeat: no-repeat; background-position: 3px 1px; padding-bottom: 4px;" onclick="calcular_resumen_inicial();" type="button" >
					<input name="button" id="borrar" value="Borrar" style="width: 100px; height: 30px; cursor: pointer; background-repeat: no-repeat; background-position: 3px 1px; padding-bottom: 4px;" onclick="borrarTabla();borrarCampos();insertar_fila('');" type="button" disabled="disabled" >
					<input name="enviar" id="enviar" value="Enviar" style="width: 100px; height: 30px; cursor: pointer; background-position: 3px 1px; background-repeat: no-repeat; padding-bottom: 4px;" onclick="guardar();" type="button" >
				</div>
			  	
			</html:form>
			</div>
		</div>
	</body>
</html>

