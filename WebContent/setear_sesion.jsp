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
<%@ page import="org.apache.jasper.runtime.JspRuntimeLibrary" %>

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
	a.setAdministrador("N");
	//System.out.println(listaUsuarioRol.size());	

	session.setAttribute("loginSession",a);	
	
	
	Iterator iterator = listaUsuarioRol.iterator();
	
	while (iterator.hasNext()){
		
		UsuarioRol ur = (UsuarioRol)iterator.next();	
		
		//System.out.println("El rol es: " + ur.getRol().getIdRol());
		//System.out.println("El rol es: " + ur.getIdUsuarioRol());
		//System.out.println(usuarioRol);
	}	
	
	//System.out.println(url);
	//response.sendRedirect("/sigefirrhh/index.jsp");

%>