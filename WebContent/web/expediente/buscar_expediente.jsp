<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %> 
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ page import="sigefirrhh.sistema.ValidadorSesion" %>
<%@ page import="sigefirrhh.login.LoginSession" %>

<% 

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
				<jsp:include page="/menu.jsp" />

<%
				rutaTemp = "/" + request.getRequestURI().split("/")[1] ;
				//System.out.println(request.getRequestURI());
			}
		}
		
	}else{
		response.sendRedirect("/sigefirrhh/error.html");
	}

%>

<html>

	<head>	
		<link rel="stylesheet" href="<%=rutaTemp %>/estilos/comun.css" type="text/css" media="screen"/>
		<link rel="stylesheet" href="<%=rutaTemp %>/estilos/theme.css" >
		<script language="javascript" src="<%=rutaTemp %>/js/jquery-2.1.1.min.js"></script>
		<script language="javascript" src="<%=rutaTemp %>/js/comun.js"></script>
		<script language="javascript" src="<%=rutaTemp %>/js/buscar_expediente.js"></script>
		
	</head>
	
	<body class="app" >
	
		<div id="pagina" align="center">
		
			<div id="estatus_expediente" style="text-align: center; background-color: #FFFFFF;opacity: 1.00; top: 200px; z-index: 9999; display: block; color: #666666; width:56%;" >
				<div class="close_div" onclick="mostrarVentana('fondo_opaco','estatus_expediente');borrarFilas('tablaDetalle')"></div>	

				<div id="conte_para" class="conte_para">

					<div id="titu_div" class="titu_div">
	                    <label>Estatus Expediente </label>
	                </div>

					<div class="conte_div_left">
	                    <label class="conte_label">Numero Expediente: <input name="textoBuscado" id="textoBuscado" size="25" class="inputtext" type="text"></label>	                     
	                </div>				

	                <div class="conte_div_right">
	                    <label class="conte_label">Tipo de Expediente: </label>
	                    <select id="tipo_expediente" name="tipo_expediente">
	                    	<option value=0>Resumen de Nómina Inicial</option>
	                    	<option value=1>Rendicion</option>
						</select>	                     
	                </div>

					<html:form action="/puntoDecision" method="post" styleId="ParametrosBusquedaForm" >
					
						<input type="hidden" id="expediente" name="expediente" value=""> </input>
						<input type="hidden" id="proceso" name="proceso" value=""> </input>
						<input type="hidden" id="accion" name="accion" value=""> </input>
		                <div class="conte_div_right" style="width: 98%;" >
		                    <input name="btnBuscar"id="btnBuscar" value="Buscar" onclick="" type="button">		                  
		                </div>	               
	                </html:form>

	           	</div>

	           	<div align="center">
			   
				   <table width="100%" id="encabezado" class="tablaEncabezado" border="1px" bordercolor="#c0c0c0" cellpadding="0px" cellspacing="0px" >
				  	   	<tbody>
				  	   		<tr>
						      	<td style="width :10%">
									Expediente
							  	</td>					  	
							  	<td style="width :10%">
									Fecha
							  	</td>
					   		  	<td style="width :20%">
									Proceso
							  	</td>
							  	<td style="width :57%">
									Observacion
							  	</td>
					   		</tr>
				    	</tbody>
				    </table>
				    
					<div id="divDetalle" style="width: 100%; height: 300px; overflow: auto;">
						<table id="tablaDetalle" style="width: 100%;" class="tablaDetalle" bordercolor="#c0c0c0" cellpadding="0px" cellspacing="0px" >
						
						</table>
					</div>
				</div>
				
				<br>			
				
			</div>
		</div>	
	</body>
</html>