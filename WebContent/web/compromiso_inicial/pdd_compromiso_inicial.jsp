<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="sigefirrhh.login.LoginSession" %>

<% 

	String rutaTemp = null;

	if ( !((LoginSession)session.getAttribute("loginSession")).isValid() ) {
		response.sendRedirect("/sigefirrhh/error.html");
	}else{
		
		rutaTemp = "/" + request.getRequestURI().split("/")[1] + "/" + request.getRequestURI().split("/")[2];
		//System.out.println(request.getRequestURI());
	}

%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Registro de Resumen de N&oacute;mina Inicial</title>
		<script language="javascript" src="<%=rutaTemp %>/js/comun.js"></script>
	</head>
	
	<body class="app" oncontextmenu="return false;">

	<div align="center">
		<img src="<%=rutaTemp %>/images/cintillo2.jpg" width="725" height="96"></img> 
		<a href="javascript:imprime();" id="imprimir" class="noImprimir" style="FONT-FAMILY: Tahoma, Verdana, Arial, san-serif;"> 
			<img src="<%=rutaTemp %>/images/imprimir.jpg" alt="Imprimir" width="28px" align="right" border="0px" height="28px">
			</img>
		</a>

		<table class="tablaCintillo" style="text-align: center;" width="800">
			<tr>
				<td valign="bottom" width="800">REPÚBLICA BOLIVARIANA DE VENEZUELA</td>
			</tr>

			<tr>
				<td valign="bottom" width="800" nowrap="nowrap">NOMBRE DEL ORGANISMO</td>
			</tr>

			<tr>
				<td valign="bottom" width="800">PDD RESUMEN DE NOMINA INICIAL</td>
			</tr>

		</table>

		</br>

		<div id="conte_para" class="conte_para">

			<div class="conte_div_left">
				<select
					style="width: 100px; height: 31px; cursor: pointer; background-position: 3px 1px; background-repeat: no-repeat; padding: 2px;">
					<option>Aprobar</option>
					<option>Anular</option>
				</select> 
				<input name="enviar" id="enviar" value="Enviar"
					style="width: 100px; height: 31px; cursor: pointer; background-position: 3px 1px; background-repeat: no-repeat; padding: 2px;"
					onclick="enviarPDD();" type="button" />
			</div>

			<div class="conte_div_left">
				<embed src="reference.pdf" width="800" height="600"></embed>
			</div>
		</div>

	</div>
</body>

</html>