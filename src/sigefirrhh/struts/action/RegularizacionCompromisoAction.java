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
import sigefirrhh.persistencia.modelo.UnidadAdministradora;
import sigefirrhh.login.LoginSession;
import sigefirrhh.persistencia.modelo.TipoDocumento;
import sigefirrhh.persistencia.dao.CompromisoInicialDAO;
import sigefirrhh.persistencia.dao.CompromisoInicialDetalleDAO;
import sigefirrhh.persistencia.dao.ExpedienteDAO;
import sigefirrhh.persistencia.dao.GastoProyectadoDAO;
import sigefirrhh.persistencia.dao.TipoDocumentoDAO;
import sigefirrhh.persistencia.dao.UnidadAdministradoraDAO;
import sigefirrhh.persistencia.dao.imple.CompromisoInicialDAOImple;
import sigefirrhh.persistencia.dao.imple.CompromisoInicialDetalleDAOImple;
import sigefirrhh.persistencia.dao.imple.ExpedienteDAOImple;
import sigefirrhh.persistencia.dao.imple.GastoProyectadoDAOImple;
import sigefirrhh.persistencia.dao.imple.TipoDocumentoDAOImple;
import sigefirrhh.persistencia.dao.imple.UnidadAdministradoraDAOImple;
import sigefirrhh.persistencia.modelo.GastoProyectado;
import sigefirrhh.sistema.ValidadorSesion;
import sigefirrhh.struts.actionForm.CompromisoInicialForm;
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
					
					System.out.println(compromisoInicialDetalle.size());
					
					CompromisoInicialForm comIni = new CompromisoInicialForm();
					comIni.setAno(ano);
					comIni.setIdUnidadAdministradora(compromisoInicial.get(0).getIdUnidadAdministradora());					
					
					for (int c=0;c<compromisoInicialDetalle.size();c++){
						comIni.setFf(compromisoInicialDetalle.get(0).getFf(),c);						
					}
					
					request.setAttribute("comIni", comIni);
					request.setAttribute("CompromisoInicial", compromisoInicial.get(0));
					request.setAttribute("CompromisoInicialDetalle", compromisoInicialDetalle.get(0));
					request.setAttribute("TipoDocumento", listaTipoDocu);
					request.setAttribute("UnidadAdministradora", unidadAdministradora.get(0));
					request.setAttribute("ano", ano);
					request.setAttribute("titulo", "Regularizacion de Compromiso");
					request.getSession().setAttribute("ReguComproBean", null);
					
					fwd ="apruebaNuevo";					
					
		        	//response.
		        	//request.setAttribute("ResumenNominaInicialAction", this.MD5("1"));
		        	//System.out.println(request.getAttribute("ResumenNominaInicialAction"));
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
			CompromisoInicialForm formaPeti = (CompromisoInicialForm ) form;
			CompromisoInicialForm formaResp = new CompromisoInicialForm();
			CriterioBusqueda criterio = new CriterioBusqueda();
	        
	        if ( session.getAttribute("loginSession") != null){
	        	if (((LoginSession) session.getAttribute("loginSession")).isValid()){			
			
					if (request.getSession().getAttribute("ReguComproBean") != null){
						System.out.println("Posiblemente le dio atras, debe iniciar proceso desde el menu inicial");
						fwd = "sesionCerrada";
						//System.out.println(request.getSession().getAttribute("ResumenNominaBean"));
					
					}else{
														
						
						Integer expeResul = 0;
						Date fecha = new Date();
									
						int idUsuario = 1;
						int idUnidadAdministadora = formaPeti.getIdUnidadAdministradora();	
						
						
						ValidadorSesion vs = new ValidadorSesion();
						
						boolean temp = vs.validarPermiso(request);
						
						
						System.out.println("A ver el resultado es " +temp);
						
						
						
								
						//Integer tipoNomina = formaPeti.getTipoNomina();
						//String observacion = 
						
						/*clRegistroCompromisoInicial clcompIni = new clRegistroCompromisoInicial();
			 		       
						clcompIni.setAnho_fiscal(ano);
						clcompIni.setCod_Sigecof("60");
						clcompIni.setCod_Sigecof(String.valueOf(org));
						clcompIni.setCod_unidad_administ("60008");
						clcompIni.setPersid_cuentadante(393821);
						clcompIni.setTipo_pago(3);
						clcompIni.setTipo_fondo(3);
						clcompIni.setTipo_documento(46);
						clcompIni.setNumero_documento("777");
						clcompIni.setObservacion(formaPeti.getObservacion());
						clcompIni.setOrigen_presupuestario("1");
						
						CompromisoInicialDTO compIniDTO = new CompromisoInicialDTO();
						 * 
						 */
					
						
						if (formaPeti.getCodFrecuenPago() !=null && !formaPeti.getCodFrecuenPago().equals("")) {
							if (formaPeti.getCodFrecuenPago().indexOf(' ') != -1){							
								for (int i = 0; i < formaPeti.getCodFrecuenPago().trim().split("\\ ").length; i++) {
									if (formaPeti.getCodFrecuenPago().trim().split("\\ ")[i].trim().length() > 0){									
										criterio.addCodFrecuenPago(Integer.valueOf(formaPeti.getCodFrecuenPago().trim().split("\\ ")[i].trim()));
									}
								}						
							}else{							
								criterio.addCodFrecuenPago(Integer.valueOf(formaPeti.getCodFrecuenPago().trim()));
							}												
						}
												
						criterio.addIdOrganismo((int) org.getIdOrganismo());
						criterio.addAno(ano);
						
						criterio.addMesesCalcu(12 -mes);
						criterio.addQuinceCalcu((12 -mes) * 2);
						criterio.addSemaCalcu(52 - semana);	
						
						GastoProyectadoDAO gastoProyectadoDAO = new GastoProyectadoDAOImple();					
						List<GastoProyectado> listadoGasto = (List<GastoProyectado>) gastoProyectadoDAO.proyectarGasto(criterio);
						
		 		        if (listadoGasto.size() > 0){	 		        	
		 		        	
		 		        	Double montoTotal = 0.0;
		 		        	
		 		        	//ArrayList<ImputacionesCompromisoInicialDTO> impComDTO = new ArrayList<ImputacionesCompromisoInicialDTO>();
		 		        	//ImputacionesCompromisoInicialDTO impDTOTemp = null;
		 		        	
		 		        	for (int i = 0;i < listadoGasto.size(); i++){				
		 		        		/*impDTOTemp = new ImputacionesCompromisoInicialDTO();
		 		        		impDTOTemp.setFuenteFinanciamiento("1");
		 		        		impDTOTemp.setCategoriaPresupuestaria(String.valueOf(i.getCodCatePresu()));
		 		        		impDTOTemp.setCodUnidadEjecutora(String.valueOf(i.getCodUnidadEjecutora()));
		 		        		impDTOTemp.setMonto(BigDecimal.valueOf(i.getMonto()));
		 		        		impDTOTemp.setObjetoGasto(i.getCodPartida().replace(".", ""));
		 	 					
			 					impComDTO.add(impDTOTemp);*/
		 		        	}
		 		        	
		 		        	//clcompIni.setDetalles_compromiso(impComDTO);
		 		        	//CompromisoInicialDTO ciDTO = clcompIni.EjecuteConsulta();
		 		        	//System.out.println(ciDTO.getNumeroExpediente() + " " + ciDTO.getNumeroCompromiso() + " " +ciDTO.getDetallesCompromiso().size());
							
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
							
							//System.out.println("Triunfo seguro " +expeResul); 
							
		 		        	
		 		        	
		 		        	formaResp.setTotalResumen(montoTotal);
		 		        	//request.setAttribute("ResumenNominaBean", formaResp); //Para poner un bean en memoria y devolverlo
		 		        	request.getSession().setAttribute("ResumenNominaBean", formaResp); //Para poner un bean en memoria y devolverlo
		 		        	
							
		 		        	//request.setAttribute("ResumenNominaInicialAction", this.MD5("1"));
		 		        	//System.out.println(request.getAttribute("ResumenNominaInicialAction"));
		 		        	

		 		        	
		 		        	fwd = "apruebaGuardar";			 		        	
		 		        	
		 		        }else{
		 		        	error[0] = (String) "sinresultados";
		 		       	}
						
						CompromisoInicialDAO compromisoInicialDAO = new CompromisoInicialDAOImple();
						
						CompromisoInicial compromisoInicial = new CompromisoInicial();
						compromisoInicial = (CompromisoInicial ) compromisoInicial.llenarBean(compromisoInicial,formaPeti);
						
						//System.out.println(formaPeti.getOriPresu() +  compromisoInicial.getOrigenPresupuestario())
						
						
						compromisoInicial.setAno(ano);
						compromisoInicial.setExpediente(expeResul);
						compromisoInicial.setEstatus(0);
						compromisoInicial.setFechaRegistro(fecha);
						compromisoInicial.setTarea(1);
						compromisoInicial.setIdCuentadante(1);
						compromisoInicial.setIdOrganismo((int) org.getIdOrganismo());						
						compromisoInicial.setCompromiso(9999);
						compromisoInicial.setIdTipoPago(1);
						compromisoInicial.setIdUnidadAdministradora(idUnidadAdministadora);
							
						Integer idCompromisoInicial;
						idCompromisoInicial = (Integer) compromisoInicialDAO.guardar(compromisoInicial);
										
						
						
		 		       /* if (arraySIGECOF.size() > 0){		 		        	
		 		        	
		 		        	Expediente expediente = new Expediente();
							expediente.setFechaReg(fecha);
							expediente.setAno(ano);				
							expediente.setEstatus(1);
							expediente.setIdUsuario(idUsuario);
							ExpedienteDAOImpl expedienteDAO = new ExpedienteDAOImpl ();
							expedienteDAO.setSqlMapClient(SMC);		
							expeResul = expedienteDAO.insertarExpediente(expediente);//Nuevo Expediente			 		        	
		 		        	
		 		        	
		 		        	CompromisoInicialDetalleDAOImpl compromisoInicialDetalleDAO = new CompromisoInicialDetalleDAOImpl();
		 		        	compromisoInicialDetalleDAO.setSqlMapClient(SMC);
		 					CompromisoInicialDetalle compromisoInicialDetalle = new CompromisoInicialDetalle();
		 					
		 					//Arrays temporales contendran los valores pertenecientes al detalle del resumen de nomina
		 					Integer[] tCodCatePresu = new Integer[listadoGasto.size()];			 					
		 					Integer[] tCodUel = new Integer[listadoGasto.size()];
		 					Integer[] tFF = new Integer[listadoGasto.size()];
		 					String[] tDenoUel = new String[listadoGasto.size()];
		 					String[] tPartida = new String[listadoGasto.size()];
		 					String[] tDenoPartida = new String[listadoGasto.size()];
		 					Double[] tMonto = new Double[listadoGasto.size()];

		 		        	Iterator it = listadoGasto.iterator();
		 		        	int con=0;
		 		        	Double montoTotal = 0.0;
		 		        	while(it.hasNext()){
		 		        		GastoProyectado i = (GastoProyectado) it.next();
		 		        		 
		 		        		compromisoInicialDetalle.setIdCompromisoInicial(idCompromisoInicial);
		 		        		compromisoInicialDetalle.setIdPartidaUelEspecifica(i.getIdPartidaUelEspecifica());
		 		        		compromisoInicialDetalle.setMonto(i.getMonto());
		 		        		
		 		        		expeResul = compromisoInicialDetalleDAO.insertarCompromisoInicialDetalle(compromisoInicialDetalle);
		 		        		if (expeResul.equals(Integer.valueOf(0)) || expeResul == null){
		 							throw new Exception("Error 1 Desconocido en ResumenNominaInicialAction");
		 						}    		        		
								//System.out.println(i.getAno() + " " + i.getMonto()  + " " + i.getCodUnidadEjecutora() );
		 		        		
		 		        		tCodCatePresu[con] = i.getCodCatePresu();
			 					tCodUel[con] = i.getCodUnidadEjecutora();
			 					tDenoUel[con] = i.getDenoUnidadEjecutora();
			 					tPartida[con] = i.getCodPartida();
			 					tDenoPartida[con] = i.getDenoPartida();
			 					tFF[con] = i.getFf();
			 					//DecimalFormat formatoNum = new DecimalFormat("###,###,###,###.00");
			 					//String tt = formatoNum.format(i.getMonto());				 					
			 					tMonto[con] = i.getMonto();
			 					montoTotal +=  i.getMonto();
		 		        		con++;
								
		 		        	}
		 		        	
		 		        	
		 		        	
		 		        	
		 		        	formaResp.setCodCatePresu(tCodCatePresu);
		 		        	formaResp.setCodUel(tCodUel);
		 		        	formaResp.setDenoUel(tDenoUel);
		 		        	formaResp.setPartida(tPartida);
		 		        	formaResp.setDenoPartida(tDenoPartida);
		 		        	formaResp.setFF(tFF);

		 		        	formaResp.setMonto(tMonto);
		 		        	formaResp.setTotalResumen(montoTotal);
		 		        	//request.setAttribute("ResumenNominaBean", formaResp); //Para poner un bean en memoria y devolverlo
		 		        	request.getSession().setAttribute("ResumenNominaBean", formaResp); //Para poner un bean en memoria y devolverlo
		 		        	
							
		 		        	//request.setAttribute("ResumenNominaInicialAction", this.MD5("1"));
		 		        	//System.out.println(request.getAttribute("ResumenNominaInicialAction"));
		 		        	

		 		        	SMC.commitTransaction();
		 		        	fwd = "apruebaGuardar";			 		        	
		 		        	
		 		        }else{
		 		        	error[0] = (String) "sinresultados";
		 		       	}*/			
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