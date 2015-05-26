<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %> 
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page language="java" %>

<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ page import="sigefirrhh.login.LoginSession" %>

<% 
{
	String rutaTemp = null;

	if (!((LoginSession)session.getAttribute("loginSession")).isValid()) {
		response.sendRedirect("/sigefirrhh/error.html");
	}else{
		
		rutaTemp = "/" + request.getRequestURI().split("/")[1];
		//System.out.println(request.getRequestURI());
	}

%>

<html>

	<head>	
		<script language="javascript" src="<%=rutaTemp %>/js/unidad_administradora.js"></script>		
	</head>
<% 
	}

%>	
	<body>
		
		<div id="unidad_administradora" style="background-color: #FFFFFF;position: absolute; opacity: 1.00; top: 200px; z-index: 9999; display: none; color: #666666;border: 2px solid; -moz-border-radius: 8px 8px 8px 8px; width:750px;" >
			<div class="close_div" onclick="mostrarVentana('fondo_opaco','unidad_administradora');borrarFilas('tablaDetalle')"></div>		
	
			<div align="center">
				<h2 style="width: 362px;">Lista de Unidades Administradoras</h2>
			</div>
			
			<div align="center">
			    <table class="inputtdtext" border="0px" bordercolor="#8080c0" width="370px">
					<tr>
						<td bordercolor="#FFFFFF" width="70px">
							<input name="textoBuscado" id="textoBuscado" size="25" class="inputtext" type="text">
							</td>
						<td bordercolor="#FFFFFF" align="right">
							<input name="btnBuscar" id="btnBuscar" value="Buscar" type="button" >
						</td>
						<td bordercolor="#FFFFFF" nowrap="true" align="right">
							<input name="" value="Cerrar" onclick="self.close()" type="button" disabled="disabled">
						</td>
					</tr>
			  	</table>
			</div>
			
			<br>
			
			<div align="center">
			   
			   <table width="70%" id="encabezado" class="tablaEncabezado" border="1px" bordercolor="#c0c0c0" cellpadding="0px" cellspacing="0px" >
			  	   	<tbody>
			  	   		<tr>
					      	<td width="21%" >
								Cod.Uni.Admin
						  	</td>					  	
				   		  	<td width="79%">
								Denominacion
						  	</td>
				   		</tr>
			    	</tbody>
			    </table>
			    
				<div style="width: 70%; height: 300px; overflow: auto;">
					<table id="tablaDetalle" style="width: 100%;" class="tablaDetalle" bordercolor="#c0c0c0" cellpadding="0px" cellspacing="0px" >
					
					</table>
				</div>
			</div>
		</div>	
	</body>
</html>
