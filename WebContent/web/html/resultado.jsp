<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="sigefirrhh.login.LoginSession" %>

<% 

	String rutaTemp = null;

	if ((LoginSession)session.getAttribute("loginSession")!=null){

		if (((LoginSession)session.getAttribute("loginSession")).isValid()) {
					%>
					<jsp:include page="/inc/top.jsp" />	
	<%
			rutaTemp = "/" + request.getRequestURI().split("/")[1] ;
				
			
		}else{			
			response.sendRedirect("/sigefirrhh/error.html");
		}		
	}else{
		response.sendRedirect("/sigefirrhh/error.html");
	}
	
 	if (rutaTemp!=null){//Escribe el html solo si la sesion esta activa y se seteo el rutaTemp
	
%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Error de aplicaci&oacute;n</title>
		<script language="javascript" src="<%=rutaTemp %>/js/comun.js"></script>
	</head>
	
	<body class="app" oncontextmenu="return false;">

		<div align="center">
						
			</br>
	
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="840">
				<tbody>
										
				  	<tr>
						<td width="45" height="223" valign="top" background="../images/home_r3_c1.jpg"></td>
						<td width="750" valign="top" bgcolor="#ffffff">
							<table width="750" height="80" border="0" cellpadding="0" cellspacing="0">
							<tbody>
								<tr>
									<td width="750">
										<div class="events_text" align="center">
											<div align="center">
												<h2><bean:write name="mensaje"/> </h2>
											</div>
											<a href="/sigefirrhh">Ir al Sistema</a>
										</div>
									</td>
								</tr>
							</tbody>
						  	</table>    
						</td>
						<td width="45" height="223" background="../images/home_r3_c3.jpg"></td>
					</tr>
				  
				</tbody>
			</table>
		</div>
	</body>
</html>
<%
}
%>

