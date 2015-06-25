<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %> 
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ page import="sigefirrhh.login.LoginSession" %>
<%@ page import="sigefirrhh.sistema.ValidadorSesion" %>

<% 
	String rutaTemp = null;

	if ((LoginSession)session.getAttribute("loginSession")!=null){

		if (((LoginSession)session.getAttribute("loginSession")).isValid()) {

			if (session.getAttribute("CompromisoInicialForm")!=null){
				
				rutaTemp = "/" + request.getRequestURI().split("/")[1] ;
				
				%>
				<jsp:include page="/inc/top.jsp" />	
<%
				
				
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

		<title> Resumen de <bean:write name="CompromisoInicialForm" property="tituloApli"/></title>

		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">		
		<meta http-equiv="Content-Style-Type" content="text/css">
		
		<link rel="STYLESHEET" href="<%=rutaTemp %>/estilos/estilo-impresion.css" media="print" type="text/css">
		<link rel="STYLESHEET" href="<%=rutaTemp %>/estilos/comun.css" media="screen" type="text/css">

		<script language=JavaScript1.1>
			
			//Función para Inhabilitar el boton derecho
			function non(clic) {
				var msg="Botón Derecho Inhabilitado";
				if (navigator.appName == 'Netscape' && clic.which==3) {
					alert(msg);
					return false;
				}else if (navigator.appName == 'Microsoft Internet Explorer' && event.button==2) {
					alert(msg);
					return false;
				}
				return true;
			}
			document.onmousedown = non;
			
			
			// slight update to account for browsers not supporting e.which
			function disableF5(e) { if ((e.which || e.keyCode) == 116) e.preventDefault(); };
			// To disable f5
			    /* jQuery < 1.7 */
			$(document).bind("keydown", disableF5);
			/* OR jQuery >= 1.7 */
			$(document).on("keydown", disableF5);
			
		</script>
	
	</head>
	 
	<body class="app" oncontextmenu="return false;">		

		<div id ="contenidoImpresion" align="center" >
			<img src="<%=rutaTemp %>/images/cintillo2.jpg" width="725" height="96">
			<a href="javascript:imprime();" id="imprimir" class="noImprimir" style="FONT-FAMILY: Tahoma,Verdana,Arial,san-serif;">
				<img src="<%=rutaTemp %>/images/imprimir.jpg" alt="Imprimir" width="28px" align="right" border="0px" height="28px">		
			</a>	
		
			<br>	
			 
			<table class="tablaCintillo" style="text-align: center;" width="800">
				<tr>
					<td valign="bottom" width="800" >				
						REPÚBLICA BOLIVARIANA DE VENEZUELA				
					</td>
				</tr>
				
				<tr>
					<td valign="bottom" width="800" nowrap="nowrap">				
						NOMBRE DEL ORGANISMO
					</td>
				</tr>
				
				<tr>
					<td valign="bottom" width="800">
						Resumen de <bean:write name="CompromisoInicialForm" property="tituloApli"/>
					</td>
				</tr>
			
			</table>
			
		    <br>
			<br>
	
			<table class="tablaParametro" width="900px" border="0px" cellpadding="1px" cellspacing="5px">
				<tbody>
					<tr>
						<td id="titulo" height="20px">
							<div style="width: 50px;">
								Expediente:
							</div>
						</td>
	
						<td id="contenido" style="text-align: left;" height="20px">
							<div style="width: 50px;">
								<bean:write name="CompromisoInicialForm" property="expediente" />
							</div>
						</td>
	
						<td id="titulo" height="20px">Fecha:</td>
	
						<td id="contenido" style="text-align: left;" width="103" height="20px">
							<div style="width: 150px;">
								<bean:write	name="CompromisoInicialForm" property="fechaRegistro" />
							</div>							
						</td>
	
						<td id="titulo" height="20px">
							<div style="width: 90px;">
								Tipo de Nomina:
							</div>
						</td>
	
						<td id="contenido" style="text-align: left;" height="20px">
							<div style="width: 50px;">
								<bean:write name="CompromisoInicialForm" property="idTipoNomina" />							
							</div>							
						</td>
	
						<td id="titulo" height="20px">
							<div style="width: 150px;">
								Ejercicio Presupuestario:				
							</div>							
						</td>
	
						<td id="contenido" style="text-align: left;" height="20px">
							<div style="width: 50px;">
								<bean:write name="CompromisoInicialForm" property="ano" />		
							</div>
						</td>	
	
					</tr>
	
					<tr>
						<td id="titulo" height="20px">Observacion:</td>
						<td id="contenido" style="text-align: left;">
							<bean:write	name="CompromisoInicialForm" property="idTipoNomina" />
						</td>
					</tr>
	
				</tbody>
			</table>
	
			<br>

	<table id="tablaDetalle" class="tablaDetalle" bordercolor="#c0c0c0" cellpadding="0px" cellspacing="0px" width="900px">
	
		<thead>
			<tr id="detalle_encabezado" align="center">	     									    
			    <td class="detalle_encabezado" width="9%">Cod.Cat.Pres</td>
				<td class="detalle_encabezado" width="5%">UEL</td>
				<td class="detalle_encabezado" width="20%">Deno UEL</td>
				<td class="detalle_encabezado" width="2%">F.F</td>
				<td class="detalle_encabezado" width="10%">Partida</td>
				<td class="detalle_encabezado" width="25%">Denominacion</td>
		      	<td class="detalle_encabezado" width="14%">Monto</td>	
		      	<td class="detalle_encabezado" width="16%">Mensaje</td>							      	
			</tr>
   		</thead>
	
		<tbody>
			
			<logic:iterate name="CompromisoInicialForm" property="codCatePresu" id="ict" indexId="indice">
						
				<tr>
	 		  
					<td id="detalle_estatico" style="text-align: center; padding: 0px 5px;" width="34">
						<bean:write name="CompromisoInicialForm" property='<%="codCatePresu[" + indice +"]"%>'/>
					</td>
					<td id="detalle_estatico" style="text-align: center; padding: 0px 5px;" width="34">
						<bean:write name="CompromisoInicialForm" property='<%="codUel[" + indice +"]"%>'/>
					</td>					
					<td id="detalle_estatico" style="text-align: center; padding: 0px 5px;" width="34">
						<bean:write name="CompromisoInicialForm" property='<%="denoUel[" + indice +"]"%>'/>
					</td>
					
					<td id="detalle_estatico" style="text-align: center; padding: 0px 5px;" width="34">
						<bean:write name="CompromisoInicialForm" property='<%="ff[" + indice +"]"%>'/>
					</td>
					
					<td id="detalle_estatico" style="text-align: center; padding: 0px 5px;" width="34">
						<bean:write name="CompromisoInicialForm" property='<%="partida[" + indice +"]"%>'/>
					</td>
					
					<td id="detalle_estatico" style="text-align: center; padding: 0px 5px;" width="34">
						<bean:write name="CompromisoInicialForm" property='<%="denoPartida[" + indice +"]"%>'/>
					</td>
					
					<td id="detalle_estatico" style="text-align: right; padding: 0px 5px;" width="34">
						<script type="text/javascript">
							document.write(formato_numerico('<bean:write name="CompromisoInicialForm" property='<%="monto[" + indice +"]"%>'/>')); 
						</script>						                       
					</td>
					
					<td id="detalle_estatico" style="text-align: center; padding: 0px 5px;" width="34">
						<bean:write name="CompromisoInicialForm" property='<%="mensajeSigecof[" + indice +"]"%>'/>
					</td>
					
					
											  
				</tr>
			  
			</logic:iterate>
				 	   
		 	<tr>
		  		<td colspan =7  id="detalle_estatico" style="text-align: right; padding: 0px 5px;" >
					TOTAL COMPROMISO INICIAL
				</td>
				<td id="detalle_estatico" style="text-align: right; padding: 0px 5px;" >					
					<script type="text/javascript">
						document.write(formato_numerico('<bean:write name="CompromisoInicialForm" property='totalResumen' />')); 
					</script>
				</td>
			</tr>
			 
		</tbody>
	</table>
	
	<br>
	<br>
	<br>	
	
	<table class="tablaCintillo" style="font-size: 14px;" width="834" border="0" cellpadding="1px" cellspacing="1px" >
		<tr>
			<td width="33%">&nbsp;</td>
		    <td width="33%">&nbsp;</td>
		    <td width="33%">&nbsp;</td>
		</tr>
		<tr>
		    <td height="44px">&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		</tr>		 
		<tr>
			<td>DIRECTOR DE ADMINISTRACION DE RECURSOS HUMANOS</td>
			<td>DIRECTORA GENERAL DE LA OFICINA DE RRHH</td>
			<td>DIRECTOR GENERAL DE LA OFICINA DE GESTION ADMINISTRATIVA</td>
		</tr>
	
	</table>
			
				<br>
				<br>
				<br>
				<div > 
					<p>Elaborado Por: <bean:write name="CompromisoInicialForm" property="codCatePresu"/> <bean:write name="CompromisoInicialForm" property="codCatePresu"/>	 Analista de Personal I </p>			  
				</div> 

			</div>

		<script>
			alert("Recuerde Imprimir su Formato antes de salir, gracias");

			/*var c, tmp;
			 
		    c = document.getElementById("contenidoImpresion");
		        
		    tmp = window.open(" ","Impresión.");
		    
		    tmp.document.open();
		    tmp.document.write('<head><link href="/css/print.css" type="text/css" rel="stylesheet"/></head>'); //Esto es omitible
		    tmp.document.write(c.innerHTML);
		    tmp.document.close();
		    tmp.print();
		    tmp.close();*/

			
		</script>

	</body>
</html>

<% 
	}

%>	
