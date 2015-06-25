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
import sigefirrhh.persistencia.modelo.CriterioBusqueda;
import sigefirrhh.persistencia.modelo.Opcion;
import sigefirrhh.login.LoginSession;
import sigefirrhh.persistencia.dao.ExpedienteDAO;
import sigefirrhh.persistencia.dao.OpcionDAO;
import sigefirrhh.persistencia.dao.imple.ExpedienteDAOImple;
import sigefirrhh.persistencia.dao.imple.OpcionDAOImple;
import sigefirrhh.sistema.ExcepcionSigefirrhh;
import sigefirrhh.sistema.ValidadorSesion;
import sigefirrhh.struts.actionForm.ParametrosBusquedaForm;
import sigefirrhh.struts.addons.Comun;
import sigefirrhh.struts.pdd.ContenedorPDD;
import sigefirrhh.struts.pdd.Pdd;

public class PuntoDecisionAction extends DispatchAction implements Serializable, Comun {
	
	private static final long serialVersionUID = 2849809543351858920L;
	HttpSession session = null;
	String fwd = null;	
	PrintWriter out = null;	
	MessageResources messageResources = null;
	
	public ActionForward nuevo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
				
		messageResources = getResources(request);
    		
		try {			
			ParametrosBusquedaForm forma = (ParametrosBusquedaForm) form;			
			
    		if (validarAcceso(request, Thread.currentThread().getStackTrace()[1].getMethodName())){
    			OpcionDAO opcionDAO = new OpcionDAOImple();
				CriterioBusqueda criterio =new CriterioBusqueda();
				criterio.addAno(ano);
				criterio.addEstatusStr("A");
				criterio.setGrupo("descripcion");
				List<Opcion> listadoOpcion = (List<Opcion>) opcionDAO.buscarOpcionUsadoenExpediente(criterio);
				request.setAttribute("Opcion", listadoOpcion);
				request.setAttribute("ano", ano);
				forma.setTituloApli("Buscar Expediente Punto de Decision ");
				fwd = "apruebaNuevo";    		
    		}    			
	
		} catch (ExcepcionSigefirrhh e) {
			
			if (e.toString().equals("sesionCerrada")){				
				fwd = e.toString();	
	        }else if(e.toString().equals("datosIncompletos")){	        	
				fwd = "datosIncompletos";
	        }else if(e.toString().equals("sinResultados")){
				fwd = "sinResultados";
	        }else if (e.toString().equals("sinPermiso")){					
	        	fwd = e.toString();	
	        }			
			
		} catch (Exception e) {			
			System.out.println("Error Grave: " + this.getClass().getName() + " a las " + hora);
			e.printStackTrace();
			request.setAttribute("mensaje", messageResources.getMessage("errors.aplicacion"));
    		fwd = "respuestaProceso";				
		
		}
		return mapping.findForward(fwd);	
		//fwd = new ActionForward("AprobarReg", "/imprimirResumenInicial.do?expediente=" + expeResul +"&ano="+ano, verdadero);		
	}
	
	
	public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		messageResources = getResources(request);	
		String errorTem = null;//Variable que guarda temporalmente el mensaje de excepcion en el caso
		
		try{			
			ParametrosBusquedaForm forma = (ParametrosBusquedaForm) form;
			session = request.getSession();
			out = response.getWriter();
	        response.setContentType("text/xml");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setStatus(HttpServletResponse.SC_OK);
	        out.write("<root>");
	        
	        CriterioBusqueda criterio = new CriterioBusqueda();
	       
    		if (validarAcceso(request, Thread.currentThread().getStackTrace()[1].getMethodName())){
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
				criterio.addIdOpcion(forma.getIdOpcion());
				
				ExpedienteDAO expedienteDAO = new ExpedienteDAOImple();
				List<Opcion> listadoExpediente = (List<Opcion>) expedienteDAO.buscarExpedienteOpcion(criterio);
				
		        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		        
		        if (listadoExpediente.size() > 0){
		        	for (int i = 0;i < listadoExpediente.size(); i++){
						out.write("<expediente>" + listadoExpediente.get(i).getExpediente() + "</expediente>");
						out.write("<fecha_reg>" + formatter.format(listadoExpediente.get(i).getFechaRegistro()) + "</fecha_reg>");						
						out.write("<cod_proceso>" + listadoExpediente.get(i).getRuta().replace(".do", "_do") + "</cod_proceso>");
						out.write("<deno_proceso>" + listadoExpediente.get(i).getDescripcion() + "</deno_proceso>");
						out.write("<observacion>" + listadoExpediente.get(i).getObservacion() + "</observacion>");
						//System.out.println(i.getAno() + " " + i.getMonto()  + " " + i.getCodUnidadEjecutora());
					}
		       }else{
		    	   throw new ExcepcionSigefirrhh("sinResultados");
		       }    		
    		}
						
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
		
		return null;
		
	}
	
	public ActionForward imprimir(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		messageResources = getResources(request);		
		try {			
			
			session = request.getSession();
			
			if (validarAcceso(request, Thread.currentThread().getStackTrace()[1].getMethodName())){
					
				ParametrosBusquedaForm forma = (ParametrosBusquedaForm) form;
				
				Pdd pdd = (Pdd) ContenedorPDD.getComponents(forma.getProceso());
				
				if (pdd!= null){
					Organismo org = new Organismo();
					org = ((LoginSession) session.getAttribute("loginSession")).getOrganismo();
					
					String urlReporte =  pdd.urlReporte(Integer.parseInt(forma.getExpediente()), ano, (int) org.getIdOrganismo());
					
					Map<Integer, String> opciones = pdd.opciones();
					
					request.setAttribute("Opciones", opciones);				
					request.setAttribute("urlReporte", urlReporte);
					request.setAttribute("expediente", forma.getExpediente());
					request.setAttribute("proceso", forma.getProceso());
					request.setAttribute("ano", ano);
		        	fwd = "aprobarImprimir";
				}else{
					throw new RuntimeException("pddNoFound");
				}	
    		}    
			
		} catch (ExcepcionSigefirrhh e) {
			
			if (e.toString().equals("sesionCerrada")){				
				fwd = e.toString();	
	        }else if(e.toString().equals("datosIncompletos")){
				fwd = "datosIncompletos";
	        }else if(e.toString().equals("sinResultados")){
				fwd = "sinResultados";
	        }else if (e.toString().equals("sinPermiso")){					
	        	fwd = e.toString();	
	        }			
			
		} catch (Exception e) {			
			System.out.println("Error Grave: " + this.getClass().getName() + " a las " + hora);
			e.printStackTrace();
			request.setAttribute("mensaje", messageResources.getMessage("errors.aplicacion"));
    		fwd = "respuestaProceso";		
		}
		return mapping.findForward(fwd);		
	}
	
	public ActionForward actualizar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		messageResources = getResources(request);		
		try {
			session = request.getSession();			
			ParametrosBusquedaForm forma = (ParametrosBusquedaForm) form;

			if (validarAcceso(request, Thread.currentThread().getStackTrace()[1].getMethodName())){
        		if (forma.getExpediente() != null ){		        		
	        		if (forma.getExpediente() != null ){
	        			if (forma.getProceso()!= null ){
	        				
	        				Pdd pdd = (Pdd) ContenedorPDD.getComponents(forma.getProceso());
	        				
	        				Organismo org = new Organismo();
	        				org = ((LoginSession) session.getAttribute("loginSession")).getOrganismo();

	        				int resultado = pdd.actualizaEstatus(Integer.parseInt(forma.getExpediente()), ano, (int) org.getIdOrganismo(), forma.getDecision());

    						//System.out.println("resultado: " + resultado);
    						request.getSession().setAttribute(this.getClass().getName() +"Bean", false);
    						request.setAttribute("mensaje", messageResources.getMessage("mensaje.exito"));
    						fwd = "respuestaProceso";

	        				
	        			}else{
	        				throw new RuntimeException("datosIncompletos");
		        		}
	        		}else{
	        			throw new RuntimeException("datosIncompletos");
	        		}
        		}else{
        			throw new RuntimeException("datosIncompletos");
        		}        	
	        }	       
			
		} catch (ExcepcionSigefirrhh e) {
			
			if (e.toString().equals("sesionCerrada")){				
				fwd = e.toString();	
	        }else if(e.toString().equals("datosIncompletos")){
				fwd = "datosIncompletos";
	        }else if(e.toString().equals("sinResultados")){
				fwd = "sinResultados";
	        }else if (e.toString().equals("sinPermiso")){					
	        	fwd = e.toString();	
	        }			
			
		} catch (Exception e) {			
			System.out.println("Error Grave: " + this.getClass().getName() + " a las " + hora);
			e.printStackTrace();
			request.setAttribute("mensaje", messageResources.getMessage("errors.aplicacion"));
    		fwd = "respuestaProceso";		
		}

		//if (request.getHeader("content-type") == null){//Si el request es de un ajax
		//	out.write("</root>");
		//	out.flush();
		//	return null;
		//}else{
		return mapping.findForward(fwd);
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