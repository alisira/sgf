package sigefirrhh.struts.action;

import org.apache.struts.action.*;
import org.apache.struts.util.MessageResources;
import org.postgresql.util.PSQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibatis.common.jdbc.exception.NestedSQLException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import sigefirrhh.base.estructura.Organismo;
import sigefirrhh.persistencia.modelo.CompromisoInicial;
import sigefirrhh.login.LoginSession;
import sigefirrhh.persistencia.dao.CompromisoInicialDAO;
import sigefirrhh.persistencia.dao.imple.CompromisoInicialDAOImple;
import sigefirrhh.persistencia.modelo.CriterioBusqueda;
import sigefirrhh.struts.actionForm.ParametrosBusquedaForm;
import sigefirrhh.struts.addons.Comun;

public class BuscarExpedienteAction extends Action implements Serializable, Comun {
   
	private static final long serialVersionUID = -4298746168227316826L;
	HttpSession session = null;
	String fwd = null;		
	Object[] error= null;	
	PrintWriter out = null;	
	MessageResources messageResources = null;

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

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
	        
	        if ( session.getAttribute("loginSession") != null){
	        	if (((LoginSession) session.getAttribute("loginSession")).isValid()){
	        		
					String tempExpe = "";
					if (String.valueOf(forma.getExpediente()) !=null && !String.valueOf(forma.getExpediente()).equals("")) {
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
					
					Organismo org = new Organismo();
					org = ((LoginSession) session.getAttribute("loginSession")).getOrganismo();
					criterio.addIdOrganismo((int) org.getIdOrganismo());
					criterio.addAno(ano);
					criterio.addEstatus(forma.getEstatus());					
					
					CompromisoInicialDAO resumenNominaDAO = new CompromisoInicialDAOImple();
					List<CompromisoInicial> listadoResumenNomina = (List<CompromisoInicial>) resumenNominaDAO.buscar(criterio, "CompromisoInicial" );				
					
			        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			        
			        if (listadoResumenNomina.size() > 0){
			        	for (int i = 0;i < listadoResumenNomina.size(); i++){
							out.write("<expediente>" + listadoResumenNomina.get(i).getExpediente() + "</expediente>");
							out.write("<fecha_reg>" + formatter.format(listadoResumenNomina.get(i).getFechaRegistro()) + "</fecha_reg>");
							out.write("<cod_proceso>" + "pddCompromiso" + "</cod_proceso>");
							out.write("<deno_proceso>" + "Compromiso Inicial" + "</deno_proceso>");
							out.write("<observacion>" + listadoResumenNomina.get(i).getObservacion() + "</observacion>");
							//System.out.println(i.getAno() + " " + i.getMonto()  + " " + i.getCodUnidadEjecutora());
						}
			       }else{
			    	   error[0] = (String) "sinresultados";
			       }
	        	
	        	}else{
	        		error[0] = (String) "sesioncerrada";
		        }
	        	
	        }else{
	        	error[0] = (String) "sesioncerrada";
	        } 	        
	      
 		
		} catch (NestedSQLException e) {
			error[0] = (String) "errorcomunicacion";
			error[1]= e;
						
		} catch (PSQLException e) {
			error[0] = (String) "errorcomunicacion";
			error[1]= e;
						
		} catch (SQLException e) {
			error[0] = (String) "errorcomunicacion";
			error[1]= e;
						
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
		}

		//if (request.getHeader("content-type") == null){//Si el request es de un ajax entonces es null
			out.write("</root>");
			out.flush();
			return null;
		//}else{
		//	return mapping.findForward(fwd);
		//}
	}
}

