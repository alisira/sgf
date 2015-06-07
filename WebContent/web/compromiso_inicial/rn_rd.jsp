<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import="sigefirrhh.login.LoginSession" %>

<% 
HttpServletRequest httpServletRequest = (HttpServletRequest)pageContext.getRequest();			

if ((LoginSession)session.getAttribute("loginSession")!=null){

	if ( !((LoginSession)session.getAttribute("loginSession")).isValid() ) {
		response.sendRedirect("/sigefirrhh/error.html");
	}else{

		response.sendRedirect("/sigefirrhh/compromisoInicial.do?accion=nuevo");

	}
}else{
	response.sendRedirect("/sigefirrhh/error.html");
}
%>

