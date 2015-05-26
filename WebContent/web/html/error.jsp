<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
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
		<title>Error de aplicaci&oacute;n</title>
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
				<td valign="bottom" width="800">REPÚBLICA BOLIVARIANA DE
					VENEZUELA</td>
			</tr>

			<tr>
				<td valign="bottom" width="800" nowrap="nowrap">NOMBRE DEL ORGANISMO</td>
			</tr>

			<tr>
				<td valign="bottom" width="800">ERROR DE SISTEMA</td>
			</tr>

		</table>

		</br>

		<table align="center" border="0" cellpadding="0" cellspacing="0" width="840">
			<tbody>
				<tr>
					<td colspan="3" >
						<div id="container"></div>
						<div id="container"></div>
					</td>
			  	</tr>
				
			  	<tr>
					<td width="45" height="223" valign="top" background="../images/home_r3_c1.jpg"></td>
					<td width="750" valign="top" bgcolor="#ffffff">
						<table width="750" height="80" border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td width="750">
									<div class="events_text" align="center">
										<div align="center">
											<h2><bean:write name="mensaje_error"/> </h2>
										</div>
										<a href="javascript:window.close();">Ir al Sistema</a>
									</div>
								</td>
							</tr>
						</tbody>
					  	</table>    
					</td>
					<td width="45" height="223"  background="../images/home_r3_c3.jpg"></td>
				</tr>
			  
			  <tr>
			    <td colspan="3" >
					<div id="container"></div>
					<div id="container"></div>
				</td>
			  </tr>
			  
			</tbody>
		</table>

	</div>
</body>

</html>