<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %> 
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="sigefirrhh.login.LoginSession" %>
<%@ page import="sigefirrhh.sistema.ValidadorSesion" %>

<% 

	String rutaTemp = null;

	if ((LoginSession)session.getAttribute("loginSession")!=null){

		if (((LoginSession)session.getAttribute("loginSession")).isValid()) {

			if (session.getAttribute("ComproIniBean")==null){
				ValidadorSesion vs = new ValidadorSesion();
				HttpServletRequest httpServletRequest = (HttpServletRequest)pageContext.getRequest();			
				boolean temp = vs.validarPermiso(httpServletRequest);

				if (temp){
					%>
					<jsp:include page="/inc/top.jsp" />	
	<%
					rutaTemp = "/" + request.getRequestURI().split("/")[1] ;
				}else{
					response.sendRedirect("/sigefirrhh/sinpermiso.jsp");					
				}				
			}else{
				response.sendRedirect("/sigefirrhh/error.html");
			}
		}else{			
			response.sendRedirect("/sigefirrhh/error.html");
		}		
	}else{
		response.sendRedirect("/sigefirrhh/error.html");
	}
	
 	if (rutaTemp!=null){//Escribe el html solo si la sesion esta activa y se seteo el rutaTemp
	
%>

<html>
	<head>
		<title>Punto de Decision</title>
		<script language="javascript" src="<%=rutaTemp %>/js/jquery-2.1.1.min.js"></script>
		<script language="javascript" src="<%=rutaTemp %>/js/comun.js"></script>
	</head>
	
	<body class="app" oncontextmenu="return false;">

	<div style="text-align: center;" >
		 
		
		
		<div>REPÚBLICA BOLIVARIANA DE VENEZUELA</div>
		<div>NOMBRE DEL ORGANISMO</div>
		<div>PDD RESUMEN DE NOMINA INICIAL</div>		

		</br>

		<div id="conte_para" class="conte_para">

			<div>			
				<html:form action="/puntoDecision" method="post">
					<input type="hidden" name="expediente" value="<bean:write name="expediente"/>"> </input>
					<input type="hidden" name="proceso" value="<bean:write name="proceso"/>"> </input>
					<input type="hidden" name="accion" value="actualizar"> </input>
					<select name="decision" style="width: 100px; height: 31px; cursor: pointer; background-position: 3px 1px; background-repeat: no-repeat; padding: 2px;">						
						<logic:iterate name="Opciones" id="td">
							<option	value=<bean:write name="td" property="key"/>>
								<bean:write name="td" property="value" />
							</option>
						</logic:iterate>
					</select>
					<input name="enviar" id="enviar" value="Enviar"	style="width: 100px; height: 31px; cursor: pointer; background-position: 3px 1px; background-repeat: no-repeat; padding: 2px;"	type="submit" >
				</input>
				</html:form>
			</div>
		</div>
		
		<div>
			<embed src="<bean:write name="urlReporte"/>" width="800" height="600" ></embed>
		</div>
	</div>
	
</body>

</html>

<%
}
%>
