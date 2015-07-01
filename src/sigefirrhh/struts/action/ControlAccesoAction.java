package sigefirrhh.struts.action;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

import sigefirrhh.login.LoginSession;
import sigefirrhh.persistencia.modelo.TipoDocumento;
import sigefirrhh.sistema.EscribirArchivo;
import sigefirrhh.sistema.ExcepcionSigefirrhh;
import sigefirrhh.sistema.ValidadorSesion;
import sigefirrhh.struts.actionForm.ParametrosBusquedaForm;
import sigefirrhh.struts.addons.Comun;

public class ControlAccesoAction extends DispatchAction implements Serializable, Comun {
	
	private static final long serialVersionUID = 2849809543351858920L;
	HttpSession session = null;
	String fwd = null;	
	PrintWriter out = null;	
	MessageResources messageResources = null;
	
public ActionForward nuevo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		
		messageResources = getResources(request);	
		String errorTem = null;//Variable que guarda temporalmente el mensaje de excepcion en el caso
		
		try{			
			//if (validarAcceso(request, Thread.currentThread().getStackTrace()[1].getMethodName())){
    			
				request.setAttribute("dia", cal.get(Calendar.DATE));
				request.setAttribute("ano", ano);				
				fwd ="apruebaNuevo";
    		//} 	
	        		

		} catch (Exception e) {			
			System.out.println("Error Grave: " + this.getClass().getName() + " a las " + hora);
			e.printStackTrace();			
			errorTem = messageResources.getMessage("errors.aplicacion");

		} finally{

			
			
		}
		
		fwd = "apruebaNuevo";

		return mapping.findForward(fwd);
	}

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
	    	String archivo =  "video_" + StringUtils.leftPad(String.valueOf(cal.get(Calendar.DATE)),2, "0") + "-" +  StringUtils.leftPad(String.valueOf((cal.get(Calendar.MONTH)+1)),2, "0")  + "-" + cal.get(Calendar.YEAR);
	    	
	        String tiempo = StringUtils.leftPad(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)),2, "0") + ":" +  StringUtils.leftPad(String.valueOf( cal.get(Calendar.MINUTE)),2, "0") + ":" + StringUtils.leftPad(String.valueOf(cal.get(Calendar.SECOND)),2, "0") ;
	        String foto =   forma.getFoto();
	        //EscribirArchivo.escribir(archivo, tiempo + '|' );
	        EscribirArchivo.escribir(archivo, tiempo + '|' + foto);
	        
	        
	        

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
	        response.setHeader("Cache-Control", "n	o-cache");
	        response.setStatus(HttpServletResponse.SC_OK);
	        out.write("<root>");

	    	String archivo =  "video_" + forma.getFechaDesde();
	        FileReader lector=new FileReader(archivo + ".log");
	        BufferedReader contenido=new BufferedReader(lector);
    		Integer max= 100;
    		String horaIni= forma.getHoraIni();
    		String horaFin= forma.getHoraFin();
	        
	        String[] registro=null;
	        String regTemp=null;
	        
	        Integer con = 0;
	        //Con el siguiente ciclo extraemos todo el contenido del objeto "contenido" y lo mostramos
	        while((regTemp=contenido.readLine())!=null){
	        	
	        	registro = regTemp.split("\\|");
		        String hora = registro[0];		        
		        
		        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		        
		        if ( sdf.parse(hora).getTime() >= sdf.parse(horaIni).getTime() && sdf.parse(hora).getTime()<= sdf.parse(horaFin).getTime()  ){
		        	con++;

		        	//System.out.println(con + " " + sdf.parse(hora) + " HoraIni: " + sdf.parse(horaIni)+ " HoraFin: " + sdf.parse(horaFin));
		        	
		        	out.write("<foto>");        	
					out.write(registro[1]);
					out.write("</foto>");
		        	
		            if (max == con) {		                
		                break;
		            }
		            
		        }else if(sdf.parse(hora).getTime() > sdf.parse(horaFin).getTime()) {
		        	break;
		        }
	        }
		    
	        contenido.close();
	        lector.close();
	        		

		} catch (ParseException e) {
            e.printStackTrace();

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