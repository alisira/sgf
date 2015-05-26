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
		<link rel="stylesheet" type="text/css" href="<%=rutaTemp %>/estilos/bootstrap.css" >
		<link rel="stylesheet" type="text/css" href="<%=rutaTemp %>/estilos/bootstrap-datetimepicker.css">	    
	    	<script type="text/javascript" src="<%=rutaTemp %>/js/bootstrap-datetimepicker.js"></script> 
		<script type="text/javascript" src="<%=rutaTemp %>/js/bootstrap-datetimepicker_002.js"></script> 
		<script type="text/javascript" src="<%=rutaTemp %>/js/origen_presupuestario.js"></script>
	</head>
<% 
	}

%>	
	<body>
		<div id="origenPresupuestario"
			style="background-color: #FFFFFF; position: absolute; opacity: 1.00; top: 200px; z-index: 9; display: none; color: #666666; border: 2px solid; -moz-border-radius: 8px 8px 8px 8px; width: 587px; height: 290px;">
			<div class="close_div" onclick="mostrarVentana('fondo_opaco','origenPresupuestario');borrarFilas('tablaDetalle')"></div>
	
			<div style="position: relative; width: 750px; padding-top: 8px;">
				<div class="conte_div_left" style="width: 100%">
					<label class="conte_label">Origen Presupuestario: </label> 
					<select id="oriPresu" name="oriPresu">
						<option value=1>Presupuesto Ley</option>
						<option value=2>Credito Adicional</option>
						<option value=3>Rectificacion</option>
					</select>
				</div>
			</div>
	
			<fieldset>
				<legend>Credito Adicional</legend>			
	
				<div class="conte_div_left">
					<label class="conte_label">Nro Gaceta </label> 
					<input type="text" name="gaceCrediAdi" id="gaceCrediAdi" size="15" maxlength="15">
				</div>
				
				<div class="conte_div_left">
					<label class="conte_label">Nro.Decreto </label> 
					<input type="text" name="decreCrediAdi" id="decreCrediAdi" size="15" maxlength="15">
				</div>
	
				<div class="conte_div_left">
					<label class="conte_label">Fecha Gaceta </label>
					<div class="input-append date" id="fechaGaceCredi">
						<input id="fechaGaceCredi" name="fechaGaceCredi" value="" data-format="dd/MM/yyyy" type="text" size="11">
						<span class="add-on"> 
							<i class="icon-calendar" data-date-icon="icon-calendar" data-time-icon="icon-time"></i>
						</span>
					</div>
				</div>
	
			</fieldset>
	
			<fieldset>
				<legend>Rectificacion</legend>
				
				<div class="conte_div_left">
					<label class="conte_label">Nro Gaceta </label> 
					<input type="text" name="gaceRecti" id="gaceRecti" size="15" maxlength="15">
				</div>
				
				<div class="conte_div_left">
					<label class="conte_label">Nro.Decreto </label> 
					<input type="text" name="decreRecti" id="decreRecti" size="15" maxlength="15">
				</div>
					
				<div class="conte_div_left">
					<label class="conte_label">Fecha Gaceta </label>
					<div class="input-append date" id="fechaGaceRecti">
						<input id="fechaGaceRecti" name="fechaGaceRecti" value="" data-format="dd/MM/yyyy" type="text" size="11">
						<span class="add-on"> 
							<i class="icon-calendar" data-date-icon="icon-calendar" data-time-icon="icon-time"></i>
						</span>
					</div>
				</div>	
			</fieldset>	
		</div>
	</body>
</html>
