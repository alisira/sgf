<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page language="java" %>
<%@ page import="sigefirrhh.login.LoginSession " %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="sigefirrhh.sistema.ValidadorSesion" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.jsp.tagext.TagSupport" %>

<% 

	String rutaTemp = null;
	String c="";
	ValidadorSesion vs = new ValidadorSesion();

	if ((LoginSession)session.getAttribute("loginSession")!=null){
		HttpServletRequest httpServletRequest = (HttpServletRequest)pageContext.getRequest();
		if ( !((LoginSession)session.getAttribute("loginSession")).isValid() ) {
			response.sendRedirect("/sigefirrhh/error.html");
		}else{		
			
			rutaTemp = "/" + request.getRequestURI().split("/")[1];			
			//System.out.println("URI del menu: " +request.getRequestURI());

			ValidadorSesion b = new ValidadorSesion();
			c = b.generarMenu(httpServletRequest);

			c= c.replace("<ul id=\"xx\" class=\"xx\">", "<ul id=\"idmenu\" class=\"mnmenu\">");
			c= c.replace("<li>", "<li class=\"level-0 middle last\">");
			c= c.replace("<ul>", "<ul class=\"level-1\">");
			
		}
	
	}else{
		response.sendRedirect("/sigefirrhh/error.html");
	}
	
	
%>

<html>
	<head>
		
		<script type="text/javascript" src="<%=rutaTemp %>/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="<%=rutaTemp %>/js/jquery.js"></script>
		<script type="text/javascript" src="<%=rutaTemp %>/js/menu_view.js"></script>
		<script language="javascript" src="<%=rutaTemp %>/js/comun.js"></script>
	
		<link type="text/css" rel="stylesheet" href="<%=rutaTemp %>/estilos/styleIE.css">
		<link rel="stylesheet" href="<%=rutaTemp %>/estilos/comun.css" type="text/css" media="screen"/>
		<link rel="stylesheet" href="<%=rutaTemp %>/estilos/theme.css" >		

	</head>

	<body>

		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	    		<tr>
			    <td align="left" width="100%">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="22%" align="left">
								<img src="<%=rutaTemp %>/images/logo-sigefirrhh-web.jpg" />
							</td>
							<td width="64%" align="center">
								<img src="<%=((LoginSession)session.getAttribute("loginSession")).getURLNombre() %>" />
							</td>
							<td width="78%" align="right">						
								<img src="<%=((LoginSession)session.getAttribute("loginSession")).getURLLogo() %>" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
		    		<td class="menutitle">
			    		<table width="100%" height="22" border="0" align="right" cellpadding="0" cellspacing="0">
			    			<tr>
			    				<td width="25%" align="center">			    					
			    					Usuario: <%=((LoginSession)session.getAttribute("loginSession")).getUsuarioObject().getNombres() + " " + ((LoginSession)session.getAttribute("loginSession")).getUsuarioObject().getApellidos() %>			    		
			    				</td>
			    				
			    				<td width="50%" align="center">
			    					Sistema de Gesti&oacute;n Financiera de los Recursos Humanos
			    				</td>
								
			    				<td width="25%" align="center">
				    				<jsp:include page="/version.inc" />
			    				</td>
			    				
			    			</tr>
			    		</table>		  
		    		</td>
 			</tr>
		</table>

<% 

%>
		<div id="contenedor" class="contenedor">		
				<script type="text/javascript">
					c= '<%=c%>';
					document.write(c);
				</script>		
		</div>	
