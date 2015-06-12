package sigefirrhh.struts.action;

//librerias necesarias

import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;
import org.postgresql.util.PSQLException;

import sigefirrhh.base.estructura.Organismo;
import sigefirrhh.login.LoginSession;
import sigefirrhh.persistencia.dao.UnidadAdministradoraDAO;
import sigefirrhh.persistencia.dao.imple.UnidadAdministradoraDAOImple;
import sigefirrhh.persistencia.modelo.CriterioBusqueda;
import sigefirrhh.persistencia.modelo.UnidadAdministradora;
import sigefirrhh.sistema.ValidadorSesion;
import sigefirrhh.struts.actionForm.UnidadAdministradoraForm;
import sigefirrhh.struts.addons.Comun;

import com.ibatis.common.jdbc.exception.NestedSQLException;
import com.ibatis.sqlmap.client.SqlMapClient;


/**
 *
 * @author Ali Sira
 */
public class MantenimientoUnidadAdministradora extends DispatchAction  implements Serializable, Comun {

	private static final long serialVersionUID = -6448999377329252595L;
	HttpSession session = null;
	String fwd = null;	
	PrintWriter out = null;
	Object[] error= null;
	SqlMapClient SMC = null;
	MessageResources messageResources = null;
    
	public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception {
    	    	
    	HttpSession session = request.getSession();    	
    	UnidadAdministradoraForm forma = null;		
		error= new Object[2];	
		messageResources = getResources(request);		
		
		try {			
			
			session = request.getSession();
			out = response.getWriter();
	        response.setContentType("text/xml");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setStatus(HttpServletResponse.SC_OK);
	        out.write("<root>");	    
	        
	        CriterioBusqueda criterio = new CriterioBusqueda();
	        
	        String resp = validarAcceso(request, Thread.currentThread().getStackTrace()[1].getMethodName());
    		if (resp == "valido"){
			        
    	        forma = (UnidadAdministradoraForm) form;
    				    			
    			if (forma.getDenominacion() !=null && !forma.getDenominacion().equals("")) {	    				
    				
    				if (forma.getDenominacion().indexOf(' ') != -1){

    					for (int i = 0; i < forma.getDenominacion().trim().split("\\ ").length; i++) {
    						if (forma.getDenominacion().trim().split("\\ ")[i].trim().length() > 3){	    							
    							criterio.addDenominacion(forma.getDenominacion().trim().split("\\ ")[i].trim());
    						}
    					}

    				}else{
    					criterio.addDenominacion(forma.getDenominacion().trim());
    				}
    			}

    			criterio.addVigente("S");
    			
    			Organismo org = new Organismo();
				org = ((LoginSession) session.getAttribute("loginSession")).getOrganismo();					
				criterio.addIdOrganismo((int) org.getIdOrganismo());
				criterio.addAno(ano);
    			
    			UnidadAdministradoraDAO UnidadAdministradoraDAO = new UnidadAdministradoraDAOImple();
    			List<UnidadAdministradora> listadoUnidadAdministradora = (List<UnidadAdministradora>) UnidadAdministradoraDAO.buscar(criterio, "UnidadAdministradora");
    	       
    	        if (listadoUnidadAdministradora.size() > 0){	    	        	
    	        	for (int i = 0;i < listadoUnidadAdministradora.size(); i++){	    					
    					out.write("<idUnidadAdministradora>" + listadoUnidadAdministradora.get(i).getIdUnidadAdministradora() + "</idUnidadAdministradora>");
    					out.write("<idOrganismo>" + listadoUnidadAdministradora.get(i).getIdOrganismo() +  "</idOrganismo>");
    					out.write("<codUnidadAdministradora>" + listadoUnidadAdministradora.get(i).getCodUnidadAdministradora() +  "</codUnidadAdministradora>");							
    					out.write("<ano>" + listadoUnidadAdministradora.get(i).getAno() +  "</ano>");
    					out.write("<denominacion>" + listadoUnidadAdministradora.get(i).getDenominacion() +  "</denominacion>");
    					out.write("<codPagadora>" + listadoUnidadAdministradora.get(i).getCodPagadora() +  "</codPagadora>");
    					out.write("<vigente>" + listadoUnidadAdministradora.get(i).getVigente() +  "</vigente>");
    					//System.out.println(i.getCedula() + " " + i.getNombre() + " " + i.getApellido());
    	        	}
    	        }else{
    	        	error[0] = (String) "sinresultados";
    	        }
	        	
	        }else{
	        	error[0] = resp;
	        }
	        
		}catch (NullPointerException e){
			
			error[0] = (String) "Error de aplicacion, comuniquese con el administrador, disculpe las molestias t1";
			error[1]= e;
						
		} catch (NestedSQLException e) {
			error[0] = (String) "Error de aplicacion, comuniquese con el administrador, disculpe las molestias t2";
			error[1]= e;
						
		} catch (PSQLException e) {
			error[0] = (String) "Error de aplicacion, comuniquese con el administrador, disculpe las molestias t3";
			error[1]= e;
						
		} catch (SQLException e) {
			error[0] = (String) "Error de aplicacion, comuniquese con el administrador, disculpe las molestias t4";
			error[1]= e;
						
		} catch (Exception e) {
			error[0] = (String) "Error de aplicacion, comuniquese con el administrador, disculpe las molestias t5";
			error[1]= e;
				
		} finally{	
			
		
			if (((String) error[0]) != null){
				if (error[1] != null){
					System.out.println("Error Grave: " + this.getClass().getName() + " a las " + hora);
					((Throwable) error[1]).printStackTrace();
					out.write("<error>");
		        	out.write(error[0].toString());
					out.write("</error>");
				}else{
					System.out.println("Incidencia: " + this.getClass().getName() + " a las " + hora);
					System.out.println(error[0]);
				}

				out.write("<error>");
				
				if (error[0].equals("sesioncerrada")){
					out.write(messageResources.getMessage("errors.sesioncerrada"));
					fwd = "sesionCerrada";
		        }else if(error[0].equals("datosincompletos")){
		        	out.write(messageResources.getMessage("errors.datosincompletos"));
					fwd = "datosIncompletos";
		        }else if(error[0].equals("errorcomunicacion")){
		        	out.write(messageResources.getMessage("errors.comunicacion"));
					fwd = "error";
	        	}else if(error[0].equals("sinresultados")){
	        		out.write(messageResources.getMessage("errors.sinresultados"));
	        		fwd = "sinresultados";
	        	}else if(error[0].equals("erroraplicacion")){
	        		out.write(messageResources.getMessage("errors.aplicacion"));
	        		fwd = "error";
		        }
				
				out.write("</error>");
			}
			
			out.write("</root>");
			out.flush();
		}
		//return mapping.findForward(null);
		return null;		
		
    }	
	
	@Override
	public String validarAcceso(HttpServletRequest request, String funcion) {
		String resp= null;
		
		HttpSession session = request.getSession();
        if (session.getAttribute("loginSession") != null){
        	if (((LoginSession) session.getAttribute("loginSession")).isValid()){
        		
        		ValidadorSesion vs = new ValidadorSesion();
        		if (vs.validarPermiso(request)){
        			
        			if (funcion.equals("nuevo")){
            			request.getSession().setAttribute(this.getClass().getName() +"Bean", true);
            			resp ="valido";
        			}else{
    					if ((boolean) request.getSession().getAttribute(this.getClass().getName() +"Bean")){
    						resp ="valido";
            			}else{
            				resp ="sesionCerrada";
            			}
        			}	
            			
        		}else{
        			resp ="sinPermiso";
        		}
        		
        	}else{
        		resp ="sesionCerrada";
	        }	        	
        }else{
        	resp ="sesionCerrada";
        }  
		
		return resp;
	}
	
}