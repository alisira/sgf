<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page language="java" %>
<%@ page import="sigefirrhh.login.LoginSession " %>
<%@ page import="sigefirrhh.base.estructura.Organismo" %>
<%@ page import="sigefirrhh.sistema.UsuarioRol" %>
<%@ page import="sigefirrhh.sistema.Usuario" %>
<%@ page import="sigefirrhh.sistema.Rol" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="sigefirrhh.sistema.ValidadorSesion" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.jsp.tagext.TagSupport" %>

<%
	Organismo org = new Organismo();
	org.setIdOrganismo(11);
	
	Usuario user = new Usuario();
	user.setNombres("Ali");
	user.setApellidos("Sira");
		

	LoginSession a= new LoginSession();
	
	a.setUsuarioObject(user);	
	a.setUsuario("asira");
	a.setOrganismo(org);
	
	List listaUsuarioRol = new ArrayList();	
	
	UsuarioRol usuarioRol = new UsuarioRol();
	
	Rol rol = new Rol();
	rol.setCodigoRol("3");
	rol.setIdRol(41);
	rol.setNombre("ANALISTA COMPROMISO");

	usuarioRol.setRol(rol);
	usuarioRol.setIdUsuarioRol(31);
	
	//usuarioRol.setUsuario(usuario)
	listaUsuarioRol.add(usuarioRol);

	
	a.setColUsuarioRol(listaUsuarioRol);
	a.setAdministrador("S");
	//System.out.println(listaUsuarioRol.size());	

	session.setAttribute("loginSession",a);	
	
	
	Iterator iterator = listaUsuarioRol.iterator();
	
	while (iterator.hasNext()){
		
		UsuarioRol ur = (UsuarioRol)iterator.next();	
		
		//System.out.println("El rol es: " + ur.getRol().getIdRol());
		//System.out.println("El rol es: " + ur.getIdUsuarioRol());
		//System.out.println(usuarioRol);
	}
	
	
	
	
/*	
	SELECT 
	  public.rolopcion.* , opcion.*
	FROM 
	  public.usuario, 
	  public.usuariorol, 
	  public.rol, 
	  public.opcion, 
	  public.rolopcion
	WHERE 
	  usuario.id_usuario = usuariorol.id_usuario AND
	  rol.id_rol = usuariorol.id_rol AND
	  opcion.id_opcion = rolopcion.id_opcion AND
	  rolopcion.id_rol = rol.id_rol
	  and usuario.id_usuario = 43
	  and rol.id_rol in (41)
	  and ruta in ('sigefirrhh/struts/action')




	SELECT this.id_usuario_rol, this.id_rol,this.id_usuario 
	FROM usuariorol this INNER JOIN usuario this_usuario ON this.id_usuario = this_usuario.id_usuario WHERE this_usuario.id_usuario = 43

	SELECT usuariorol.id_rol,usuariorol.id_usuario FROM usuariorol WHERE (usuariorol.id_usuario_rol = 31)
	


	
*/
	
	//session.setAttribute("loginSession",null);
%>

<% 

HttpServletRequest httpServletRequest = (HttpServletRequest)pageContext.getRequest();

ValidadorSesion b = new ValidadorSesion();
String c = b.generarMenu(httpServletRequest);


c= c.replace("<ul id=\"xx\" class=\"xx\">", "<ul id=\"idmenu\" class=\"mnmenu\">");
c= c.replace("<li>", "<li class=\"level-0 middle last\">");
c= c.replace("<ul>", "<ul class=\"level-1\" >");

%>

<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	
	<head>
		<title>Menu de Opciones</title>
		<script type="text/javascript" src="/sigefirrhh/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="/sigefirrhh/js/jquery.js"></script>
		
		<script type="text/javascript" src="/sigefirrhh/js/menu_view.js"></script>
		<link type="text/css" rel="stylesheet" href="/sigefirrhh/estilos/styleIE.css">
	
	</head>

	<body>

		<div id="contenedor" class="contenedor">

			<script type="text/javascript">
				c= '<%=c%>';
				document.write(c);
			</script>

		</div>


	</body>
</html>