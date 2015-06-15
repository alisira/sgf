package sigefirrhh.struts.action;

import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

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
import sigefirrhh.persistencia.modelo.CriterioBusqueda;
import sigefirrhh.persistencia.modelo.Expediente;
import sigefirrhh.persistencia.modelo.Opcion;
import sigefirrhh.login.LoginSession;
import sigefirrhh.persistencia.dao.CompromisoInicialDAO;
import sigefirrhh.persistencia.dao.ExpedienteDAO;
import sigefirrhh.persistencia.dao.OpcionDAO;
import sigefirrhh.persistencia.dao.imple.CompromisoInicialDAOImple;
import sigefirrhh.persistencia.dao.imple.ExpedienteDAOImple;
import sigefirrhh.persistencia.dao.imple.OpcionDAOImple;
import sigefirrhh.sistema.ValidadorSesion;
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
	
	public ActionForward nuevo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		error= new Object[2];
		messageResources = getResources(request);
    		
		try {			
			ParametrosBusquedaForm forma = (ParametrosBusquedaForm) form;			
			String resp = validarAcceso(request, Thread.currentThread().getStackTrace()[1].getMethodName());
    		if (resp == "valido"){
    			OpcionDAO opcionDAO = new OpcionDAOImple();
				CriterioBusqueda criterio =new CriterioBusqueda();
				criterio.addAno(ano);
				List<Opcion> listadoOpcion = (List<Opcion>) opcionDAO.buscarOpcionExpediente(criterio);
				request.setAttribute("Opcion", listadoOpcion);
				request.setAttribute("ano", ano);
				forma.setTituloApli("Buscar Expediente Punto de Decision ");
				fwd = "apruebaNuevo";
    		}else{
    			error[0] = resp;
    		}    			
	
		} catch (Exception e) {
			error[0] = (String) "errorAplicacion";
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
				
				if (error[0].equals("sesionCerrada")){					
					fwd = "sesionCerrada";
				}else if (error[0].equals("sinPermiso")){					
					fwd = "sinPermiso";
	        	}else if(error[0].equals("errorAplicacion")){
	        		request.setAttribute("mensaje_error", messageResources.getMessage("errors.aplicacion"));
	        		fwd = "error";
		        }

			}
		}
		return mapping.findForward(fwd);	
		//fwd = new ActionForward("AprobarReg", "/imprimirResumenInicial.do?expediente=" + expeResul +"&ano="+ano, verdadero);		
	}
	
	
	public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//System.out.println("Se Valido al usuario: en " + this.getClass().getName() +  " a las " + hora);
		error= new Object[2];
		messageResources = getResources(request);					
		try{			
			ParametrosBusquedaForm forma = (ParametrosBusquedaForm) form;
			session = request.getSession();
			out = response.getWriter();
	        response.setContentType("text/xml");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setStatus(HttpServletResponse.SC_OK);
	        out.write("<root>");
	        
	        CriterioBusqueda criterio = new CriterioBusqueda();
	        String resp = validarAcceso(request, Thread.currentThread().getStackTrace()[1].getMethodName());
    		if (resp == "valido"){
				if (forma.getExpediente() !=null){
					String tempExpe = "";
					if (!forma.getExpediente().equals("")){						
						tempExpe = String.valueOf(forma.getExpediente());
						if (tempExpe.indexOf(' ') != -1){
							for (int i = 0; i < tempExpe.trim().split("\\ ").length; i++) {
								if (tempExpe.trim().split("\\ ")[i].trim().length() > 0){									
									criterio.addExpediente(Integer.parseInt(tempExpe.trim().split("\\ ")[i].trim()));
								}
							}							
						}else{
							criterio.addExpediente(Integer.parseInt(tempExpe.trim()));		
						}
					}									
				}
				
				Organismo org = new Organismo();
				org = ((LoginSession) session.getAttribute("loginSession")).getOrganismo();
				criterio.addIdOrganismo((int) org.getIdOrganismo());
				criterio.addAno(ano);
				criterio.addEstatus(forma.getEstatus());					
				
				ExpedienteDAO expedienteDAO = new ExpedienteDAOImple();
				List<Opcion> listadoExpediente = (List<Opcion>) expedienteDAO.buscarExpedienteOpcion(criterio);
				
		        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		        
		        if (listadoExpediente.size() > 0){
		        	for (int i = 0;i < listadoExpediente.size(); i++){
						out.write("<expediente>" + listadoExpediente.get(i).getExpediente() + "</expediente>");
						out.write("<fecha_reg>" + formatter.format(listadoExpediente.get(i).getFechaRegistro()) + "</fecha_reg>");
						out.write("<cod_proceso>" + "pddCompromiso" + "</cod_proceso>");
						out.write("<deno_proceso>" + "Compromiso Inicial" + "</deno_proceso>");
						out.write("<observacion>" + listadoExpediente.get(i).getObservacion() + "</observacion>");
						//System.out.println(i.getAno() + " " + i.getMonto()  + " " + i.getCodUnidadEjecutora());
					}
		       }else{
		    	   error[0] = (String) "sinResultados";
		       }
    		}else{
    			error[0] = resp;
    		}
						
		} catch (Exception e) {
			error[0] = (String) "errorAplicacion";
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
				
				if (error[0].equals("sesionCerrada")){
					out.write(messageResources.getMessage("errors.sesionCerrada"));
					fwd = "sesionCerrada";
	        	}else if(error[0].equals("errorAplicacion")){
	        		out.write(messageResources.getMessage("errors.aplicacion"));
	        		fwd = "error";
		        }else if (error[0].equals("sinPermiso")){
					out.write(messageResources.getMessage("errors.sinPermiso"));
					fwd = "sinPermiso";
		        }else if(error[0].equals("sinResultados")){
	        		out.write(messageResources.getMessage("errors.sinResultados"));
	        		fwd = "sinResultados";
		        }

				out.write("</error>");

			}
		}

		//if (request.getHeader("content-type") == null){//Si el request es de un ajax entonces es null
			out.write("</root>");
			out.flush();
			return null;
		//}else{
		//	return mapping.findForward(fwd);
		//}
	}
	
	public ActionForward imprimir(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		error= new Object[2];
		messageResources = getResources(request);		
		try {			
			
			session = request.getSession();
			
	        String resp = validarAcceso(request, Thread.currentThread().getStackTrace()[1].getMethodName());
    		if (resp == "valido"){
					
				ParametrosBusquedaForm forma = (ParametrosBusquedaForm) form;
				
				Pdd pddCompromiso = (Pdd) ContenedorPDD.getComponents(forma.getProceso());
				
				Organismo org = new Organismo();
				org = ((LoginSession) session.getAttribute("loginSession")).getOrganismo();
				
				String urlReporte =  pddCompromiso.urlReporte(Integer.parseInt(forma.getExpediente()), ano, (int) org.getIdOrganismo());
				
				Map<Integer, String> opciones = pddCompromiso.opciones();
				
				request.setAttribute("Opciones", opciones);				
				request.setAttribute("urlReporte", urlReporte);
				request.setAttribute("expediente", forma.getExpediente());
				request.setAttribute("proceso", forma.getProceso());
				request.setAttribute("ano", ano);
	        	fwd = "aprobarImprimir";
		
        	}else{
    			error[0] = resp;
    		}    
			
		} catch (Exception e) {
			error[0] = (String) "errorAplicacion";
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
				
				if (error[0].equals("sesionCerrada")){
					fwd = "sesionCerrada";
	        	}else if(error[0].equals("errorAplicacion")){
	        		request.setAttribute("mensaje_error", messageResources.getMessage("errors.aplicacion"));
	        		fwd = "error";
		        }else if (error[0].equals("sinPermiso")){					
					fwd = "sinPermiso";
		        }else if(error[0].equals("sinResultados")){	        		
	        		fwd = "sinResultados";
		        }
				
			}
			
		}
		return mapping.findForward(fwd);		
	}
	
	public ActionForward actualizar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		error= new Object[2];
		messageResources = getResources(request);		
		try {
			session = request.getSession();			
			ParametrosBusquedaForm forma = (ParametrosBusquedaForm) form;

			String resp = validarAcceso(request, Thread.currentThread().getStackTrace()[1].getMethodName());
    		if (resp == "valido"){
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
	    						request.getSession().setAttribute(this.getClass().getName() +"Bean", false);
	    						request.setAttribute("mensaje", messageResources.getMessage("mensaje.exito"));
	    						fwd = "exito";

	        				}else{
	        					error[0] = (String) "datosIncompletos";
			        		}
	        			}else{
	        				error[0] = (String) "datosIncompletos";
		        		}
	        		}else{
	        			error[0] = (String) "datosIncompletos";
	        		}
        		}else{
        			error[0] = (String) "datosIncompletos";
        		}        		
        	}else{
        		error[0] = resp;
	        }
	       
			
		} catch (Exception e) {
			error[0] = (String) "errorAplicacion";
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

				if (error[0].equals("sesionCerrada")){					
					fwd = "sesionCerrada";
				}else if (error[0].equals("sinPermiso")){					
					fwd = "sinPermiso";
	        	}else if(error[0].equals("errorAplicacion")){
	        		request.setAttribute("mensaje_error", messageResources.getMessage("errors.aplicacion"));
	        		fwd = "error";
	        	}else if(error[0].equals("datosIncompletos")){
					fwd = "datosIncompletos";		        
		        }				
			}
		}

		//if (request.getHeader("content-type") == null){//Si el request es de un ajax
		//	out.write("</root>");
		//	out.flush();
		//	return null;
		//}else{
		return mapping.findForward(fwd);
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
