package sigefirrhh.struts.action;

import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

import sigefirrhh.base.estructura.Organismo;
import sigefirrhh.persistencia.modelo.CompromisoInicial;
import sigefirrhh.login.LoginSession;
import sigefirrhh.persistencia.dao.CompromisoInicialDAO;
import sigefirrhh.persistencia.dao.imple.CompromisoInicialDAOImple;
import sigefirrhh.struts.actionForm.ParametrosBusquedaForm;
import sigefirrhh.struts.addons.Comun;
import sigefirrhh.struts.pdd.ContenedorPDD;
import sigefirrhh.struts.pdd.Pdd;

public class PuntoDecisionAction extends DispatchAction implements Serializable, Comun {
	
	private static final long serialVersionUID = 2849809543351858920L;
	HttpSession session = null;
	String fwd = null;
	Object[] error= null;
	PrintWriter out = null;	
	MessageResources messageResources = null;
	
	public ActionForward imprimir(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		error= new Object[2];
		messageResources = getResources(request);		
		try {			
			
			session = request.getSession();
			out = response.getWriter();
	        response.setContentType("text/xml");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setStatus(HttpServletResponse.SC_OK);
	        out.write("<root>");			
			
			/*if (request.getHeader("content-type") == null){//Si el request es de un ajax
				System.out.println("Ajax");
			}else{
				System.out.println("No Ajax");
			}*/
			//System.out.println("Entro al pdde");
			
	        if ( session.getAttribute("loginSession") != null){
	        	if (((LoginSession) session.getAttribute("loginSession")).isValid()){
					
					ParametrosBusquedaForm forma = (ParametrosBusquedaForm) form;
					int idUsuario = 1;
					
					Pdd pdd = (Pdd) ContenedorPDD.getComponents(forma.getProceso());
					
					Organismo org = new Organismo();
					org = ((LoginSession) session.getAttribute("loginSession")).getOrganismo();		
					
					//System.out.println("Expediente: " + forma.getExpediente() + " " + "Proceso: " + forma.getProceso());
					
					String urlReporte =  pdd.urlReporte(Integer.parseInt(forma.getExpediente()), ano, (int) org.getIdOrganismo()); 		        	
 		       
 		        	//request.setAttribute("ResumenNominaBean", formaResp); //Para poner un bean en memoria y devolverlo
 		        	//request.getSession().setAttribute("ResumenNominaBean", formaResp); //Para poner un bean en memoria y devolverlo
										
					request.setAttribute("urlReporte", urlReporte);
					request.setAttribute("expediente", forma.getExpediente());
					request.setAttribute("proceso", forma.getProceso());
					request.setAttribute("ano", ano);
					
					request.getSession().setAttribute("validaInicio", true);//sirve para impedir que le de atras al navegador e intente actualizar nuevamente
 		        	
 		        	fwd = "aprobarImprimir";
			
	        	}else{
	        		error[0] = (String) "sesioncerrada";
		        }
	        	
	        }else{
	        	error[0] = (String) "sesioncerrada";				
	        }		
			
		} catch (Exception e) {
			error[0] = (String) "erroraplicacion";
			error[1]= e;

		} finally{			

			if (((String) error[0]) != null){
				if (error[1] != null){
					System.out.println("Error Grave: " + this.getClass().getName() + " a las " + hora);
					((Throwable) error[1]).printStackTrace();					
				}else{
					//System.out.println("Incidencia: " + this.getClass().getName() + " a las " + hora);
					//System.out.println(error[0]);
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
			
			//out.write("</root>");
			//out.flush();
		}
		return mapping.findForward(fwd);		
	}
	
	public ActionForward actualizar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		error= new Object[2];
		messageResources = getResources(request);		
		try {
			session = request.getSession();
			out = response.getWriter();
	        response.setContentType("text/xml");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setStatus(HttpServletResponse.SC_OK);
	        out.write("<root>");
			session = request.getSession();			
			
			ParametrosBusquedaForm forma = (ParametrosBusquedaForm) form;
			
			/*if (request.getHeader("content-type") == null){//Si el request es de un ajax
				System.out.println("Ajax");
			}else{
				System.out.println("No Ajax");
			}*/			
						
			//validaInicio = (boolean) request.getSession().getAttribute("validaInicio");
			
	        if (session.getAttribute("loginSession") != null){
	        	if (((LoginSession) session.getAttribute("loginSession")).isValid()){
	        		if ( (boolean) request.getSession().getAttribute("validaInicio")){	        	
		        		if (forma.getExpediente() != null ){		        		
			        		if (forma.getExpediente() != null ){
			        			if (forma.getProceso()!= null ){
			        				if (forma.getProceso().equals("pddCompromiso") ){
		
			        					Organismo org = new Organismo();
			        					org = ((LoginSession) session.getAttribute("loginSession")).getOrganismo();	
			        					
			        					CompromisoInicial compromisoInicial = new CompromisoInicial();
			        					compromisoInicial.setExpediente(Integer.parseInt(forma.getExpediente()));
			        					compromisoInicial.setAno(ano);
			        					compromisoInicial.setIdOrganismo((int) (long)org.getIdOrganismo());
			        					compromisoInicial.setEstatus(forma.getDecision());
		
			        					int resultado;
			    						CompromisoInicialDAO compromisoInicialDAO = new CompromisoInicialDAOImple();
			    						resultado = compromisoInicialDAO.actualizarCompromisoInicial(compromisoInicial);
		
			    						//System.out.println("resultado: " + resultado);
			    						request.getSession().setAttribute("validaInicio", false);
			    						fwd = "exito";
		
			        				}else{
			        					error[0] = (String) "datosincompletos";
					        		}
			        			}else{
			        				error[0] = (String) "datosincompletos";
				        		}
			        		}else{
			        			error[0] = (String) "datosincompletos";
			        		}
		        		}else{
		        			error[0] = (String) "datosincompletos";
		        		}
	        		}else{
	        			error[0] = (String) "sesioncerrada";
	        		}
	        	}else{
	        		error[0] = (String) "sesioncerrada";
		        }        	
	        }else{
	        	error[0] = (String) "sesioncerrada";	        	
	        }	        
			
		} catch (Exception e) {
			error[0] = (String) "erroraplicacion";
			error[1]= e;
		
		} finally{			

			if (((String) error[0]) != null){
				if (error[1] != null){
					System.out.println("Error Grave: " + this.getClass().getName() + " a las " + hora);
					((Throwable) error[1]).printStackTrace();					
				}else{
					//System.out.println("Incidencia: " + this.getClass().getName() + " a las " + hora);
					//System.out.println(error[0]);
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
			
			//out.write("</root>");
			//out.flush();
		}

		//if (request.getHeader("content-type") == null){//Si el request es de un ajax
		//	out.write("</root>");
		//	out.flush();
		//	return null;
		//}else{
		return mapping.findForward(fwd);
	}
	
}
