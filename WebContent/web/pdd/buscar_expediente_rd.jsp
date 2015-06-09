<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/tags.tld" prefix="tags" %>
<%@ page import="sigefirrhh.login.LoginSession" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/extensions" prefix="x" %>

<html>
	<head>

<% if ( !((LoginSession)session.getAttribute("loginSession")).isValid() ) {
		response.sendRedirect("/sigefirrhh/error.html");
	}else{%>

		<script>
			window.open('/sigefirrhh/web/expediente/buscar_expediente.jsp', 'Buscar Expediente','width='+screen.width+ ',height=768,toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,copyhistory=no,resizable=yes,left=100,top=50,screenX=100,screenY=50');
		</script>

<%		
	}
%>

	<title>Sigefirrhh</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="/sigefirrhh/style/webstyle.css" rel="stylesheet" type="text/css">
		
	<jsp:include page="/inc/functions.jsp" />
	
</head>

	<body onload="">
		<f:view>
			<x:saveState value="#{cargoForm}" />
	    	<f:loadBundle basename="sigefirrhh.custMessages" var="custMessages"/>
			<jsp:include page="/inc/top.jsp" />
	
			<table width="770"  border="0" cellspacing="0" cellpadding="5" align="left">
		
			</table>

		</f:view>
	</body>
</html>