

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %> 
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ page import="sigefirrhh.login.LoginSession" %>
<%@ page import="sigefirrhh.sistema.ValidadorSesion" %>

<% 
//System.out.println(session.getAttribute("titulo"));
	String rutaTemp = null;

	if ((LoginSession)session.getAttribute("loginSession")!=null){

		if (((LoginSession)session.getAttribute("loginSession")).isValid()) {

			if (session.getAttribute("ReguComproBean")==null){
				ValidadorSesion vs = new ValidadorSesion();
				HttpServletRequest httpServletRequest = (HttpServletRequest)pageContext.getRequest();			
				boolean temp = vs.validarPermiso(httpServletRequest);

				if (temp){
					%>
					<jsp:include page="/inc/top.jsp" />	
	<%
					rutaTemp = "/" + request.getRequestURI().split("/")[1] ;
				}else{
					response.sendRedirect("/sigefirrhh/sinpermiso.jsp");					
				}				
			}else{
				response.sendRedirect("/sigefirrhh/error.html");
			}
		}else{			
			response.sendRedirect("/sigefirrhh/error.html");
		}		
	}else{
		response.sendRedirect("/sigefirrhh/error.html");
	}
	
 	if (rutaTemp!=null){//Escribe el html solo si la sesion esta activa y se seteo el rutaTemp
	
%>


		<script type="text/javascript" language="JavaScript">		
			    document.title = "<bean:write name="titulo"/>" ;		
		</script>

		<script language="javascript" src="<%=rutaTemp %>/js/jquery.dataTables.min.js"></script>
		<script language="javascript" src="<%=rutaTemp %>/js/regularizacion_compromiso.js"></script>

		<div id="fondo_opaco" style="position: absolute; top: 0px; left: 0px; width: 903px; height: 553px; display: none; opacity: 0.70; z-index: 9; background-color: #111111;">
		</div>	
	
		<div id="pagina" align="center" >
		
			<div id="contenido">	
			
			<html:form action="/regularizacionCompromiso" method="post"> <!-- <form id="resumenNominaInicial" name="resumenNominaInicial" action="" method="post"> -->
				<jsp:include page="../compromiso_inicial/origen_presupuestario.jsp" />
								
				<input name="tablavacia" id="tablavacia" value="SI" type="hidden">
				<input name="accion" id="accion" value="guardar" type="hidden">				
				<html:hidden name="Compromiso" property="idCompromisoInicial" />
				
				<div id="conte_para" class="conte_para">
					
					<div class="conte_div_left" style="width: 31%; height: 34px;" >
	                    <label class="conte_label">Fecha: <script>hoy();document.write(fechaHoy);</script> </label>
	                </div>
	
					<div class="conte_div_left" style="width: 34%; height: 34px;" >
	                    <label class="conte_label">Ejercicio Presupuestario: <bean:write name="ano"/> </label>    
	                </div>
	                
	                <div class="conte_div_left" style="width: 31%; height: 34px;" >
	                    <label class="conte_label">Tipo de Fondo </label>
	                    <label class="conte_label">&nbsp;<bean:write name="Compromiso" property="denoTipoFondo"/></label> 
	                </div>
	                
	                <div class="conte_div_left" style="width: 40%; height: 34px;">
	                    <label class="conte_label">Tipo de Documento </label>
						<html:select name="Compromiso" property="idTipoDocumento" tabindex="0" style="width: 240px;" styleId="idTipoDocumento">
							<html:options collection="TipoDocumento" property="codigo" labelProperty="denominacion"/>
						</html:select>
					</div>
	                
	                <div class="conte_div_left" style="width: 32%; height: 34px;">
	                    <label class="conte_label">Nro.Documento </label>
	                    <html:text name="Compromiso" property="documento" styleId="documento" size="15" maxlength="15" />
	                </div>
	                
	                <div class="conte_div_left" style="width: 25%; height: 34px;" >
	                    <label class="conte_label">Origen Presupuestario: </label>
	                    <input style="width: 50px " value="..." tabindex="-1" name="listadoUnidadAdministradora" id="listadoUnidadAdministradora" onclick="abrirMB('origenPresupuestario', '', '', '')" type="button">								                     
	                </div>	                
	                
	                <div class="conte_div_left" style="width: 25%;">
	                    <label class="conte_label">Unidad Administradora:</label>
	                    <label class="conte_label">&nbsp;<bean:write name="Compromiso" property="codUnidadAdministradora"/></label>
	                </div>
	                
	                <div class="conte_div_left" style="width: 70%;">
						<label class="conte_label">&nbsp;Denominacion:</label>
						<label class="conte_label"><bean:write name="Compromiso" property="denoUniAdmi"/> </label>							                     
	                </div>
	                
	                <div class="conte_div_left" style="width: 98%;">
	                    <label class="conte_label">Observaci&oacute;n: </label>
						<html:text name="Compromiso" property="observacion" styleId="observacion" size="87" maxlength="100" />
	                </div>
	                 
            	</div>
			  
				<div id="TituloyTabla" class="contenedorDetalle">	
			
		  			<div id="imputaciones" align="left">
						<table id="TablaConcepto" class="tablesorter tablesorter-blue" role="grid" >
			  				<thead>
								<tr id="encabezado" align="center">						    
								    <td class="celda_encabezado" width="8%">Cod.Cat.Pres</td>									    
									<td class="celda_encabezado" width="4%">UEL</td>
									<td class="celda_encabezado" width="16%">Deno UEL</td>
									<td class="celda_encabezado" width=3%">F.F</td>
									<td class="celda_encabezado" width="10%">Cod.Partida</td>
									<td class="celda_encabezado" width="23%">Deno Partida</td>
									<td class="celda_encabezado" width="12%">Disp.Presu</td>
							      	<td class="celda_encabezado" width="12%">Monto-Compro</td>						      	
							      	<td class="celda_encabezado" width="12%">+/-</td>
				        		</tr>
			        		</thead>
			        		
			        		<tbody>
						 		
						 		<logic:iterate name="Compromiso" property="codCatePresu" id="ict" indexId="indice">
						
									<tr>
						 		  
										<td class="detalle_center">										
											<bean:write name="Compromiso" property='<%="codCatePresu[" + indice +"]"%>'/>
											<input name="codCatePresu" value=<bean:write name="Compromiso" property='<%="codCatePresu[" + indice +"]"%>'/> type="hidden">											
										</td>
										
										<td class="detalle_center">
											<bean:write name="Compromiso" property='<%="codUel[" + indice +"]"%>'/>
										</td>
															
										<td class="detalle_left">
											<bean:write name="Compromiso" property='<%="denoUel[" + indice +"]"%>'/>
										</td>
										
										<td class="detalle_center">
											<bean:write name="Compromiso" property='<%="ff[" + indice +"]"%>'/>
										</td>
										
										<td class="detalle_center">
											<bean:write name="Compromiso" property='<%="partida[" + indice +"]"%>'/>
										</td>
										
										<td class="detalle_left">
											<bean:write name="Compromiso" property='<%="denoPartida[" + indice +"]"%>'/>
										</td>
										
										<td class="detalle_right">
											
											<script type="text/javascript">
												document.write(formato_numerico('<bean:write name="Compromiso" property='<%="dispo[" + indice +"]"%>'/>')); 
											</script>
										</td>
										
										<td class="detalle_right">
											<script type="text/javascript">
												document.write(formato_numerico('<bean:write name="Compromiso" property='<%="monto[" + indice +"]"%>'/>')); 
											</script>						                       
										</td>
										
										<td class="detalle_right">
											<script type="text/javascript">
												document.write(formato_numerico(0.00)); 
											</script>						                       
										</td>
										
																  
									</tr>
								  
								</logic:iterate>
						 		
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
			  	
			  	<div id="botoneria" style="width:1000px">
					<input name="button" id="borrar" value="Borrar" style="width: 100px; height: 30px; cursor: pointer; background-repeat: no-repeat; background-position: 3px 1px; padding-bottom: 4px;" onclick="borrarTabla();borrarCampos();insertar_fila('');" type="button" disabled="disabled" >
					<input name="enviar" id="enviar" value="Enviar" style="width: 100px; height: 30px; cursor: pointer; background-position: 3px 1px; background-repeat: no-repeat; padding-bottom: 4px;" onclick="guardar();" type="button" >
				</div>
			  	
			</html:form>
			</div>
		</div>
	</body>
</html>
<%
}
%>

