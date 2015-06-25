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
import sigefirrhh.sistema.ExcepcionSigefirrhh;
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
	SqlMapClient SMC = null;
	MessageResources messageResources = null;
    
	public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception {
    	    	
    	HttpSession session = request.getSession();    	
    	UnidadAdministradoraForm forma = null;
		messageResources = getResources(request);
		String errorTem = null;//Variable que guarda temporalmente el mensaje de excepcion en el caso
		
		try {			
			
			session = request.getSession();
			out = response.getWriter();
	        response.setContentType("text/xml");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setStatus(HttpServletResponse.SC_OK);
	        out.write("<root>");	    
	        
	        CriterioBusqueda criterio = new CriterioBusqueda();	        
	      
    		if (validarAcceso(request, "nuevo")){
			        
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
    	        	throw new ExcepcionSigefirrhh("sinResultados");
    	        }	      
	        }
	        
		}catch (NullPointerException e){
			
			System.out.println("Error Grave: " + this.getClass().getName() + " a las " + hora);
			e.printStackTrace();
			errorTem = messageResources.getMessage("errors.aplicacion");    		
						
		} catch (NestedSQLException e) {
			System.out.println("Error Grave: " + this.getClass().getName() + " a las " + hora);
			e.printStackTrace();
			errorTem = messageResources.getMessage("errors.comunicacion");
			
		} catch (PSQLException e) {				
			System.out.println("Error Grave: " + this.getClass().getName() + " a las " + hora);
			e.printStackTrace();			
			errorTem = messageResources.getMessage("errors.aplicacion");
			
		} catch (SQLException e) {
			System.out.println("Error Grave: " + this.getClass().getName() + " a las " + hora);
			e.printStackTrace();			
			errorTem = messageResources.getMessage("errors.aplicacion");
			
		} catch (ExcepcionSigefirrhh e) {			
			
			if (e.toString().equals("sesionCerrada")){
				errorTem = messageResources.getMessage("errors.sesionCerrada");
	        }else if(e.toString().equals("datosIncompletos")){	        	
	        	errorTem = messageResources.getMessage("errors.datosIncompletos");				
	        }else if(e.toString().equals("sinResultados")){
	        	errorTem = messageResources.getMessage("errors.sinResultados");				
	        }			
			
		} catch (Exception e) {			
			System.out.println("Error Grave: " + this.getClass().getName() + " a las " + hora);
			e.printStackTrace();			
			errorTem = messageResources.getMessage("errors.aplicacion");

		} finally{

			if (errorTem != null){
				out.write("<error>");        	
				out.write(errorTem);
				out.write("</error>");		
			}

			out.write("</root>");
			out.flush();
		}
		//return mapping.findForward(null);
		return null;		
		
    }	
	
	@Override
	public boolean validarAcceso(HttpServletRequest request, String funcion) throws ExcepcionSigefirrhh {		

		HttpSession session = request.getSession();
        if (session.getAttribute("loginSession") != null){
        	if (((LoginSession) session.getAttribute("loginSession")).isValid()){

        		ValidadorSesion vs = new ValidadorSesion();
        		if (vs.validarPermiso(request)){

        			if (funcion.equals("nuevo")){
            			request.getSession().setAttribute(this.getClass().getName() +"Bean", true);
        			}else{
    					if (!(boolean) request.getSession().getAttribute(this.getClass().getName() +"Bean")){    					
            				throw new ExcepcionSigefirrhh("sesionCerrada");
            			}
        			}

        		}else{
        			throw new ExcepcionSigefirrhh("sinPermiso");
        		}

        	}else{
        		throw new ExcepcionSigefirrhh("sesionCerrada");
	        }	        	
        }else{
        	throw new ExcepcionSigefirrhh("sesionCerrada");
        }  

		return true;
	}
	
}