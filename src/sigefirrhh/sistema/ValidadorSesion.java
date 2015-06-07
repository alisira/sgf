package sigefirrhh.sistema;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import sigefirrhh.login.LoginSession;
import sigefirrhh.persistencia.dao.OpcionDAO;
import sigefirrhh.persistencia.dao.RolOpcionDAO;
import sigefirrhh.persistencia.dao.imple.OpcionDAOImple;
import sigefirrhh.persistencia.dao.imple.RolOpcionDAOImple;
import sigefirrhh.persistencia.modelo.CriterioBusqueda;
import sigefirrhh.persistencia.modelo.Opcion;
import sigefirrhh.persistencia.modelo.RolOpcion;

public class ValidadorSesion {
	
	public Integer getIdOpcion(HttpServletRequest request) {
		
		OpcionDAO OpcionDAO = new OpcionDAOImple();					
		List<Opcion> listadoOpcion = null;
		
		try {
			String uri = null;
			CriterioBusqueda criterio = new CriterioBusqueda();
			
			if (request.getRequestURI().indexOf(".do") != -1){					
				uri = request.getServletPath().replace("/", "");
				criterio.addRuta(uri);					
			}else{
				uri = request.getRequestURI().substring(12);
				uri = uri.substring(0,uri.length()-4);
				criterio.addUri(uri);
			}
		
			listadoOpcion = (List<Opcion>) OpcionDAO.buscar(criterio, "Opcion");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listadoOpcion.get(0).getIdOpcion();
		
	}
	public boolean validarPermiso(HttpServletRequest request) {

		HttpSession session = request.getSession();
		LoginSession loginSession = (LoginSession)session.getAttribute("loginSession");
		boolean resp = false;
		
		
		try{
			loginSession.setAutenticado(false);
			loginSession.setConsultar(false);
			loginSession.setAgregar(false);
			loginSession.setModificar(false);
			loginSession.setEliminar(false);
			loginSession.setEjecutar(false);
				
			if (loginSession.getAdministrador().equals("S") || loginSession.isServicioPersonal() == true) {	
				loginSession.setAutenticado(false);
				loginSession.setConsultar(true);
				loginSession.setAgregar(true);
				loginSession.setModificar(true);
				loginSession.setEliminar(true);
				loginSession.setEjecutar(true);	
				resp= true;
			}else{
					
				
				//System.out.println("URL completo " + request.getRequestURI());
				String uri = null;
				CriterioBusqueda criterio = new CriterioBusqueda();
				
				//Evalua desde donde viene el request (un jsp que searia un jsf o desde un action de struts)				
				if (request.getRequestURI().indexOf(".do") != -1){					
					uri = request.getServletPath().replace("/", "");
					criterio.addRuta(uri);					
				}else{
					uri = request.getRequestURI().substring(12);
					uri = uri.substring(0,uri.length()-4);
					criterio.addUri(uri);
				}
						
				//System.out.println("URL a evaluar " + uri);					
				Collection colUsuarioRol = new ArrayList();
				colUsuarioRol = (ArrayList)loginSession.getColUsuarioRol();
				
				Iterator iterator = colUsuarioRol.iterator();
				
				while (iterator.hasNext()){
					
					UsuarioRol usuarioRol = (UsuarioRol)iterator.next();
					criterio.addIdRol(usuarioRol.getRol().getIdRol());						
				}
				
				
				RolOpcionDAO colRolOpcionDAO = new RolOpcionDAOImple();					
				List<RolOpcion> listadoRolOpcion = (List<RolOpcion>) colRolOpcionDAO.buscar(criterio, "RolOpcion");
											
				if (listadoRolOpcion .iterator().hasNext()){							
					RolOpcion rolOpcion = new RolOpcion();
					rolOpcion = (RolOpcion)listadoRolOpcion.iterator().next();
					loginSession.setAutenticado(true);
					loginSession.setConsultar(rolOpcion.getConsultar().equals("S"));
					loginSession.setAgregar(rolOpcion.getAgregar().equals("S"));
					loginSession.setModificar(rolOpcion.getModificar().equals("S"));
					loginSession.setEliminar(rolOpcion.getEliminar().equals("S"));
					loginSession.setEjecutar(rolOpcion.getEjecutar().equals("S"));
					resp= true;						
				}else{
					resp= false;
				}
				
				if (!loginSession.isAutenticado()){
					resp= false;
				}
			}
		
		}catch (Exception e){
			//log.debug("Error en el TLD de Seguridad",e);
			e.printStackTrace();
		}

		return resp;
		
	}
	
	public String generarMenu(HttpServletRequest request) {
		HttpSession session = request.getSession();
		LoginSession loginSession = (LoginSession)session.getAttribute("loginSession");
		String menu = null;
		CriterioBusqueda criterio = null;
		OpcionDAO OpcionDAO = new OpcionDAOImple();	
		List<Opcion> listadoOpcion = null;
		
		try {
			
			if (loginSession.getAdministrador().equals("S") != true) {
			
				Collection colUsuarioRol = new ArrayList();
				colUsuarioRol = (ArrayList)loginSession.getColUsuarioRol();	
				
				criterio = new CriterioBusqueda();
				Iterator iterator = colUsuarioRol.iterator();
				
				while (iterator.hasNext()){
					
					UsuarioRol usuarioRol = (UsuarioRol)iterator.next();
					criterio.addIdRol(usuarioRol.getRol().getIdRol());						
				}
				listadoOpcion = (List<Opcion>) OpcionDAO.buscar(criterio, "OpcionxRol");	
			}else{
				listadoOpcion = (List<Opcion>) OpcionDAO.buscar(null, "Opcion");	
			}			
			
			String vtOpciones[][] = new String[listadoOpcion.size()][3]; 
			for (int i=0; i < listadoOpcion.size();i++){
				vtOpciones[i][0] = listadoOpcion.get(i).getJerarquia().toString();
				vtOpciones[i][1] = listadoOpcion.get(i).getDescripcion().toString();
				vtOpciones[i][2] = listadoOpcion.get(i).getRuta();
			}
			
			
			//System.out.println(vtOpciones[1][0] + " " + vtOpciones[1][1]);
			
			//Ponerle el bloque que esta en la oficina 
			
			
			menu = "<ul id=\"xx\" class=\"xx\">";
			
			for (int i=0; i<vtOpciones.length; i++) {
				if (tieneHijos(i, vtOpciones)) {
					//System.out.println("indice:" + i + " valor:" + vtOpciones[i][0] + " SI");				
					menu += "<li>" + vtOpciones[i][1] +  "<ul>";
				} else {
					
					String rutaTemp = null;
					rutaTemp = "/" + request.getRequestURI().split("/")[1] ;					
					
					//System.out.println("Si consigue el compromiso: " + vtOpciones[i][2].indexOf(".do"));
					
					if (vtOpciones[i][2].indexOf(".do") != -1){
						menu += "<li> <a href=\"" + rutaTemp+ "/" + vtOpciones[i][2] + "?accion=nuevo\" class=\"level-1\">";	
					}else{
						if (!vtOpciones[i][2].equals("")){
							menu += "<li> <a href=\"" + rutaTemp+ "/" + vtOpciones[i][2] + ".jsf\" class=\"level-1\">";	
						}else{
							menu += "<li> <a href=\"" + rutaTemp+ "/" + "home.jsf\" class=\"level-1\">";
						}
						
						
					}
					
					
					menu += vtOpciones[i][1] + "</a></li>";
					//System.out.println("indice:" + i + " valor:" + vtOpciones[i][0] + " NO");
				}
				//System.out.println(TieneHijos2(i, vtOpciones));
				
				if (i+1 < vtOpciones.length){
			    	 if (vtOpciones[i+1][0].length() < vtOpciones[i][0].length()){ 
			    		 
			    		 int cont = vtOpciones[i][0].split("\\.").length - vtOpciones[i+1][0].split("\\.").length ;
			    		 for (int y=0; y<cont; y++) {
			    			 menu +="</ul></li>";	 
			    		 }
			    	 }
				}else if (i+1 == vtOpciones.length){
			    	 if (vtOpciones[i][0].split("\\.").length > 1){
	 		    		 
			    		 int cont = vtOpciones[i][0].split("\\.").length-1 ;
			    		 for (int y=0; y<cont; y++) {
			    			 menu +="</ul></li>";	 
			    		 }
			    	 }
			    	
			    	 
			    	 
			     }
		    	// System.out.println(m.toString()+ " " + resp);			
				
	        }
			
			//sirve para incluir el cambio de contrasena por defecto sin embargo ya este esta incluida como una opcion a ser asociado a los roles 
			/*menu += "<li>"; 
	    	menu += "Personal";
	    	menu += "<ul>";
	    	menu += "<li>"; 
	    	menu += "<a class=\"level-1 level-3\" href=\"/sigefirrhh/login/ChangePassword.jsf\">&nbsp;&nbsp;Cambio Contrasena</a>";
	    	menu += "</li>";
	    	menu += "</ul>";	    	
			menu += "</li>";
			*/
			
			menu += "<li>"; 
	    	menu += "<a class=\"level-1 level-3\" href=\"/sigefirrhh/logout.jsf\">&nbsp;&nbsp;Salir</a>";
			menu += "</li>";
			menu += "</ul>";
			
			//System.out.println(menu);     
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return menu;
		
	}
	
	private boolean tieneHijos(int indiceActual, String vtOpc[][]) {
		
		Pattern p = Pattern.compile("^" + vtOpc[indiceActual][0]+ ".*"); 
		boolean resp = false;
	    
		if (indiceActual+1 < vtOpc.length){
			Matcher m = p.matcher(vtOpc[indiceActual+1][0]); // get a matcher object
			if (m.find()) {
				resp = true;
			} else {
				resp = false;
			}
		}

		return resp;
	     
	}
}
