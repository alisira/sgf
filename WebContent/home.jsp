<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/extensions" prefix="x" %>
<%@ page import="sigefirrhh.login.LoginSession" %>
<%@ page import="sigefirrhh.sistema.ValidadorSesion" %>

<% 

	String rutaTemp = null;
	
	if ((LoginSession)session.getAttribute("loginSession")!=null){
	
		if (!((LoginSession)session.getAttribute("loginSession")).isValid()) {
			//System.out.println("sesion cerrada1");
			response.sendRedirect("/sigefirrhh/error.html");
		}else{
			ValidadorSesion vs = new ValidadorSesion();
			HttpServletRequest httpServletRequest = (HttpServletRequest)pageContext.getRequest();			
			boolean temp = vs.validarPermiso(httpServletRequest);
			
			if (!temp){
				
				response.sendRedirect("/sigefirrhh/sinpermiso.jsp");

			}else{
				%>
				<jsp:include page="/inc/top.jsp"  />
<%
				rutaTemp = "/" + request.getRequestURI().split("/")[1] + "/" + request.getRequestURI().split("/")[2];
				//System.out.println(request.getRequestURI());
			}
		}
		
	}else{
		//System.out.println("sesion cerrada2");
		response.sendRedirect("/sigefirrhh/error.html");
	}

 	if (rutaTemp!=null){//Escribe el html solo si la sesion esta activa y se seteo el rutaTemp
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
 
<html>

<head>
	<title>Sigefirrhh</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="/sigefirrhh/style/webstyle.css" 
		rel="stylesheet" type="text/css">
		
	<script language=javascript> 
		
		var double_delay = null;
		var delay_value = 1000; // 1 second.
		var click_count=0;
		
		function clickMade() {
		
		if (click_count>0) {
		    // it is a double click
			clearTimeout(double_delay);
			double_delay=null;
			
			return true

		  } else {
		    // it is a single click
			click_count++;
			double_delay = setTimeout("click_count=0",delay_value);
			
			return false

		  }

		}
		
		
		
    </script>	
</head>

	<body>
		<f:view>	
			<table width="800"  border="0" cellspacing="0" cellpadding="5" align="left">
				<tr>
		    		<!-- Menú Izquierdo -->
		    		<td width="200" valign="top">
		    			<div align="left">
		                     
		    			</div>
		    		</td>
					<td width="570" valign="top">
						<h:form>
							<table width="100%" border="0" cellspacing="0" cellpadding="0" valign="top">
								<tr>	
									<td valign="top">
									</td>
								</tr>
							</table>
						</h:form>
					</td>
				</tr>
			</table>
		</f:view>
	</body>
</html>

<%
}
%>
