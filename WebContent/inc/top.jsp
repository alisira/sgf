<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page language="java" %>
<%@ page import="sigefirrhh.login.LoginSession " %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="sigefirrhh.sistema.ValidadorSesion" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.jsp.tagext.TagSupport" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.TimeZone" %>	

	

<% 

	String rutaTemp = null;
	String c="";
	ValidadorSesion vs = new ValidadorSesion();
	String fecha=null;
	Locale l = new Locale("es","VE");
	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Caracas"),l);
	String titulo = "Sistema de Gestión Financiera de los Recursos Humanos";

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

			String mes = "00".substring(String.valueOf((cal.get(Calendar.MONTH)+1)).length())+ String.valueOf((cal.get(Calendar.MONTH)+1)); 
			String dia = "00".substring(String.valueOf((cal.get(Calendar.DATE)+1)).length())+ String.valueOf((cal.get(Calendar.DATE)+1));		
			fecha =   dia + "/" +  mes  + "/" + String.valueOf(cal.get(Calendar.YEAR));			
			
			if (session.getAttribute("titulo")!=null) titulo = (String) session.getAttribute("titulo");
			
		}
	
	}else{
		response.sendRedirect("/sigefirrhh/error.html");
	}
	
	
%>

<html>
	<head>
		<title> <%=titulo %></title>
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
		</table>
		
		
		<div class="contenedor_div">
			
			<div class="contenedor_div_left" style="width: 23%;background: #dddddd none repeat scroll 0 0; border-bottom-left-radius: 10px;border-right: 2px solid #fff;border-top-left-radius: 10px; margin-left: 4px;color: #777777 !important;font-size: 12px;padding: 0px;">
				<img style="vertical-align:middle;" width="30" height="30" src="<%=rutaTemp %>/images/user.png">
				Usuario: <%=((LoginSession)session.getAttribute("loginSession")).getUsuarioObject().getNombres() + " " + ((LoginSession)session.getAttribute("loginSession")).getUsuarioObject().getApellidos() %>
			</div>

			<div class="contenedor_div_left" style="width: 6%;background: #eeeeee none repeat scroll 0 0; color: #777777 !important;font-size: 14px; padding: 7px; border-right: 2px solid #fff;">
				<%=fecha%>
			</div>

			<div class="contenedor_div_left" style="width:58%;text-align: center; background: #eeeeee none repeat scroll 0 0; font-size: 20px; padding: 4px; border-right: 2px solid #fff;">
				Sistema de Gesti&oacute;n Financiera de los Recursos Humanos
			</div>
			
			<div class="contenedor_div_left" style="width:9%;text-align: center;background: #dddddd none repeat scroll 0 0;border-bottom-right-radius: 10px;border-top-right-radius: 10px; color: #777777 !important; font-size: 14px; padding: 7px;">
				<jsp:include page="/version.inc" />
			</div>
			
		</div>
		
		

<% 

%>
		<div id="contenedor" class="contenedor">		
				<script type="text/javascript">
					c= '<%=c%>';
					document.write(c);
				</script>		
		</div>	