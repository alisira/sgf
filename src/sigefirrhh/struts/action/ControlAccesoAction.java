package sigefirrhh.struts.action;

import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
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
import sigefirrhh.sistema.EscribirArchivo;
import sigefirrhh.sistema.ExcepcionSigefirrhh;
import sigefirrhh.sistema.ValidadorSesion;
import sigefirrhh.struts.actionForm.ParametrosBusquedaForm;
import sigefirrhh.struts.addons.Comun;
import sigefirrhh.struts.pdd.ContenedorPDD;
import sigefirrhh.struts.pdd.Pdd;

public class ControlAccesoAction extends DispatchAction implements Serializable, Comun {
	
	private static final long serialVersionUID = 2849809543351858920L;
	HttpSession session = null;
	String fwd = null;	
	PrintWriter out = null;	
	MessageResources messageResources = null;
	
	
	public ActionForward guardar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
				
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
	        
	        Locale l = new Locale("es","VE");
	    	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Caracas"),l);
	    	String archivo =  cal.get(Calendar.DATE) + "-" +  (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.YEAR)+"_video";
	    	
	        String tiempo = cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
	        String foto =   forma.getFoto();
	        EscribirArchivo.escribir(archivo, tiempo + '|' );
	       // EscribirArchivo.escribir(archivo, tiempo + '|' + foto);
	        
	        
	        

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

	public ActionForward leer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		
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
	        
	        Locale l = new Locale("es","VE");
	    	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Caracas"),l);
	    	String archivo =  cal.get(Calendar.DATE) + "-" +  (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.YEAR)+"_video";
	    	
	        String tiempo = cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
	        String foto =   forma.getFoto();
	        String horaIni = forma.getHoraIni();
	        EscribirArchivo.leer(archivo, tiempo + '|' );
	       // EscribirArchivo.escribir(archivo, tiempo + '|' + foto);
	        
	        
	        

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