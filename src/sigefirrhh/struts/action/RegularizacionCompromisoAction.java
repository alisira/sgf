package sigefirrhh.struts.action;

import org.apache.struts.action.*;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;
import org.postgresql.util.PSQLException;

/*
import sigecof.DisponibilidadPresupuestariaDTO;
import sigecof.clDisponibilidadPresupuestaria;
import sigecof.clRegistroCompromisoInicial;
import sigecof.ImputacionesCompromisoInicialDTO;
import sigecof.CompromisoInicialDTO;
*/






import sigefirrhh.persistencia.modelo.CompromisoInicial;
import sigefirrhh.persistencia.modelo.CompromisoInicialDetalle;
import sigefirrhh.persistencia.modelo.CriterioBusqueda;
import sigefirrhh.persistencia.modelo.Expediente;
import sigefirrhh.persistencia.modelo.RegularizacionCompromiso;
import sigefirrhh.persistencia.modelo.UnidadAdministradora;
import sigefirrhh.login.LoginSession;
import sigefirrhh.persistencia.modelo.TipoDocumento;
import sigefirrhh.persistencia.dao.CompromisoInicialDAO;
import sigefirrhh.persistencia.dao.CompromisoInicialDetalleDAO;
import sigefirrhh.persistencia.dao.ExpedienteDAO;
import sigefirrhh.persistencia.dao.GastoProyectadoDAO;
import sigefirrhh.persistencia.dao.RegularizacionCompromisoDAO;
import sigefirrhh.persistencia.dao.TipoDocumentoDAO;
import sigefirrhh.persistencia.dao.UnidadAdministradoraDAO;
import sigefirrhh.persistencia.dao.imple.CompromisoInicialDAOImple;
import sigefirrhh.persistencia.dao.imple.CompromisoInicialDetalleDAOImple;
import sigefirrhh.persistencia.dao.imple.ExpedienteDAOImple;
import sigefirrhh.persistencia.dao.imple.GastoProyectadoDAOImple;
import sigefirrhh.persistencia.dao.imple.RegularizacionCompromisoDAOImple;
import sigefirrhh.persistencia.dao.imple.TipoDocumentoDAOImple;
import sigefirrhh.persistencia.dao.imple.UnidadAdministradoraDAOImple;
import sigefirrhh.persistencia.modelo.GastoProyectado;
import sigefirrhh.sistema.ValidadorSesion;
import sigefirrhh.struts.actionForm.CompromisoInicialForm;
import sigefirrhh.struts.actionForm.RegularizacionCompromisoForm;
import sigefirrhh.struts.addons.Comun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibatis.common.jdbc.exception.NestedSQLException;

import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sigefirrhh.base.estructura.Organismo;

/** 
 *
 * @author Ali Sira
 */
public class RegularizacionCompromisoAction extends DispatchAction implements Serializable, Comun {

	private static final long serialVersionUID = 6333610918451615896L;	
	HttpSession session = null;
	String fwd = null;
	Object[] error= null;
	PrintWriter out = null;	
	MessageResources messageResources = null;
	
	
	public ActionForward nuevo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
						
		error= new Object[2];
		messageResources = getResources(request);		
    		
		try {
			
			HttpSession session = request.getSession();
	        if ( session.getAttribute("loginSession") != null){
	        	if (((LoginSession) session.getAttribute("loginSession")).isValid()){
	        		
	        		TipoDocumentoDAOImple tipoDocumentoDAO = new TipoDocumentoDAOImple();					
					List<TipoDocumento> listaTipoDocu= (List<TipoDocumento>) tipoDocumentoDAO.buscarTipoDocuTempo();					
					
					//Recupera la data del maestro compromiso
					CriterioBusqueda criterioBusqueda = new CriterioBusqueda();
					criterioBusqueda.addExpediente(51);					
					CompromisoInicialDAO compromisoInicialDAO = new CompromisoInicialDAOImple();
					List<CompromisoInicial> compromisoInicial= (List<CompromisoInicial>) compromisoInicialDAO.buscar(criterioBusqueda, "CompromisoInicial");					
					
					//Recupera la data de la unidad administradora
					criterioBusqueda =null;
					criterioBusqueda = new CriterioBusqueda();
					criterioBusqueda.addIdUnidadAdministradora(compromisoInicial.get(0).getIdUnidadAdministradora());					
					UnidadAdministradoraDAO unidadAdministradoraDAO = new UnidadAdministradoraDAOImple();
					List<UnidadAdministradora> unidadAdministradora= (List<UnidadAdministradora>) unidadAdministradoraDAO.buscar(criterioBusqueda, "UnidadAdministradora");		

					//Recupera la data del detalle del compromiso
					criterioBusqueda =null;
					criterioBusqueda = new CriterioBusqueda();
					criterioBusqueda.addIdCompromisoInicial((compromisoInicial.get(0).getIdCompromisoInicial()));
					CompromisoInicialDetalleDAO compromisoInicialDetalleDAO = new CompromisoInicialDetalleDAOImple();
					List<CompromisoInicialDetalle> compromisoInicialDetalle= (List<CompromisoInicialDetalle>) compromisoInicialDetalleDAO.buscarExt(criterioBusqueda);					

					//System.out.println(compromisoInicialDetalle.size());

					CompromisoInicialForm comIni = new CompromisoInicialForm(compromisoInicialDetalle.size());
					comIni.setAno(ano);
					comIni.setDenoTipoFondo("Xfondo");
					
					//System.out.println(compromisoInicial.get(0).getDocumento());
					
					comIni.setDocumento(compromisoInicial.get(0).getDocumento());
					comIni.setCodUnidadAdministradora((unidadAdministradora.get(0).getCodUnidadAdministradora()));
					comIni.setDenoUniAdmi(unidadAdministradora.get(0).getDenominacion());
					comIni.setObservacion(compromisoInicial.get(0).getObservacion());
					comIni.setIdCompromisoInicial(compromisoInicial.get(0).getIdCompromisoInicial());
					
					
					 
					
					
					for (int c=0;c<compromisoInicialDetalle.size();c++){
						comIni.setFf(compromisoInicialDetalle.get(0).getFf(),c);
						comIni.setCodCatePresu(compromisoInicialDetalle.get(0).getCodCatePresu(),c);
						comIni.setCodUel(compromisoInicialDetalle.get(0).getCodUnidadEjecutora() ,c);
					    comIni.setDenoUel(compromisoInicialDetalle.get(0).getDenoUnidadEjecutora(),c);
					    comIni.setPartida(compromisoInicialDetalle.get(0).getCodPartida(),c);
					    comIni.setDenoPartida(compromisoInicialDetalle.get(0).getDenoPartida(),c);
					    comIni.setDispo(500.0,c);
					    comIni.setMonto(compromisoInicialDetalle.get(0).getMonto(),c);
					}
					
					request.setAttribute("Compromiso", comIni);
					request.setAttribute("TipoDocumento", listaTipoDocu);
					request.setAttribute("ano", ano);
					request.setAttribute("titulo", "Regularizacion de Compromiso");
					request.getSession().setAttribute("ReguComproBean", null);
					
					fwd ="apruebaNuevo";
		        	
	        	}else{
	        		fwd ="sesionCerrada";
		        }
	        	
	        }else{
	        	fwd ="sesionCerrada";
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
		}
		return mapping.findForward(fwd);	
		//fwd = new ActionForward("AprobarReg", "/imprimirResumenInicial.do?expediente=" + expeResul +"&ano="+ano, verdadero);		
		
	}
	
	public ActionForward guardar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
		
		error= new Object[2];
		messageResources = getResources(request);		
		
		try {		
			
			session = request.getSession();
			out = response.getWriter();
	        response.setContentType("text/xml");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setStatus(HttpServletResponse.SC_OK);
	        out.write("<root>");
	        Organismo org = new Organismo();
			org = ((LoginSession) session.getAttribute("loginSession")).getOrganismo();
			RegularizacionCompromisoForm formaPeti = (RegularizacionCompromisoForm ) form;			
			CriterioBusqueda criterio = new CriterioBusqueda();

	        if ( session.getAttribute("loginSession") != null){
	        	if (((LoginSession) session.getAttribute("loginSession")).isValid()){			

					if (request.getSession().getAttribute("ReguComproBean") != null){
						System.out.println("Posiblemente le dio atras, debe iniciar proceso desde el menu inicial");
						fwd = "sesionCerrada";						

					}else{							

						Integer expeResul = 0;
						Date fecha = new Date();
						int idUsuario = 1;
	 		        	
						Expediente expediente = new Expediente();
						expediente.setFechaReg(fecha);
						expediente.setAno(ano);				
						expediente.setEstatus(1);
						expediente.setIdUsuario(idUsuario);
						expediente.setObservacion(formaPeti.getObservacion());
						expediente.setIdOrganismo((int) org.getIdOrganismo());
						expediente.setIdProceso(55);
						ExpedienteDAO expedienteDAO = new ExpedienteDAOImple();							
						expeResul = (Integer) expedienteDAO.guardar(expediente);
						
	 		        	RegularizacionCompromisoDAO regularizacionCompromisoDAO = new RegularizacionCompromisoDAOImple();
						
	 		        	RegularizacionCompromiso regularizacionCompromiso = new RegularizacionCompromiso();
	 		        	regularizacionCompromiso = (RegularizacionCompromiso) regularizacionCompromiso.llenarBean(regularizacionCompromiso,formaPeti);
						
	 		        	regularizacionCompromiso.setAno(ano);
	 		        	regularizacionCompromiso.setExpediente(expeResul);
	 		        	regularizacionCompromiso.setEstatus(0);
	 		        	regularizacionCompromiso.setFechaRegistro(fecha);
	 		        	regularizacionCompromiso.setTarea(1);
	 		        	
	 		        	regularizacionCompromiso.setIdCompromisoInicial(formaPeti.getIdCompromisoInicial());
	 		        	regularizacionCompromiso.setIdOrganismo((int) org.getIdOrganismo());
							
						Integer idReguCompro;
						idReguCompro = (Integer) regularizacionCompromisoDAO.guardar(regularizacionCompromiso);
						
						
						
		 		       		
					}						
					
			
	        	}else{
	        		error[0] = (String) "sesioncerrada";
		        }
	        	
	        }else{
        		error[0] = (String) "sesioncerrada";
	        }

		} catch (NestedSQLException e) {
			error[0] = (String) "errorData";
			error[1]= e;
						
		} catch (PSQLException e) {
			error[0] = (String) "errorData";
			error[1]= e;
						
		} catch (SQLException e) {
			error[0] = (String) "errorcomunicacion";
			error[1]= e;
						
		} catch (Exception e) {
			error[0] = (String) "erroraplicacion";
			error[1]= e;

		} finally{
			
			if (((String) error[0]) != null){
				/*try {					
					//if (SMC != null){
					//	SMC.endTransaction();
					//	fwd = "error";
					//}				
				} catch (SQLException e) {
					e.printStackTrace();
				}*/
				
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
		        	request.setAttribute("mensaje_error", messageResources.getMessage("errors.datosincompletos"));
					fwd = "datosIncompletos";
		        }else if(error[0].equals("errorcomunicacion")){
		        	out.write(messageResources.getMessage("errors.comunicacion"));
		        	request.setAttribute("mensaje_error", messageResources.getMessage("errors.comunicacion"));
					fwd = "error";
		        }else if(error[0].equals("errorData")){
		        	out.write(messageResources.getMessage("errors.data"));
		        	request.setAttribute("mensaje_error", messageResources.getMessage("errors.data"));
					fwd = "error";
	        	}else if(error[0].equals("sinresultados")){
	        		out.write(messageResources.getMessage("errors.sinresultados"));
	        		fwd = "sinresultados";
	        	}else if(error[0].equals("erroraplicacion")){
	        		request.setAttribute("mensaje_error", messageResources.getMessage("errors.aplicacion"));
	        		out.write(messageResources.getMessage("errors.aplicacion"));
	        		fwd = "error";
		        }
				out.write("</error>");
			}
		}

		//System.out.println(request.getHeader("content-type"));
		
		if (request.getHeader("content-type") == null){//Si el request es de un ajax el valor sera nulo
			out.write("</root>");
			out.flush();
			return null;
		}else{
			return mapping.findForward(fwd);
		}
	}
}