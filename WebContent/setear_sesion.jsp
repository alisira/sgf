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

<%@ page import="java.net.InetSocketAddress" %>
<%@ page import=" java.net.Proxy" %>
<%@ page import="java.net.SocketAddress" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.URLConnection" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.FileOutputStream" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="java.io.PrintWriter" %>


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
	
	//System.out.println(url);
	//response.sendRedirect("/sigefirrhh/index.jsp");
	
/*

SocketAddress addr = new InetSocketAddress("proxyr.mf.gob.ve", 3128);
Proxy proxy = new Proxy(Proxy.Type.SOCKS, addr);

//System.setProperty("http.proxyHost", "proxyr.mf.gob.ve");
//System.setProperty("http.proxyPort", "3128");
URL url = new URL("http://sonnyt.com/uglyemail/");
//URL url = new URL("http://10.79.6.231/InterfazNegociadoraWEB/inicio.jsp");
//

//URL url = new URL("http://java.example.org/");
//URLConnection conn = url.openConnection(proxy);
InputStream in = url.openStream();
File destino = new File("destino.txt");

try {
 
  OutputStream out2 = new FileOutputStream(destino);
				
  byte[] buf = new byte[1024];
  int len;

  while ((len = in.read(buf)) > 0) {
    out2.write(buf, 0, len);
  }
		
  in.close();
  out2.close();
} catch (IOException ioe){
  ioe.printStackTrace();
}
*/

//URL url = new URL("http://java.example.org/");
//URLConnection conn = url.openConnection(proxy);
//conn.connect();

//System.out.println("prueba: " + conn.getContentLength());
	
	

%>