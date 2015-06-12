package sigefirrhh.struts.action;

import org.apache.struts.action.*;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

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
import sigefirrhh.login.LoginSession;
import sigefirrhh.persistencia.modelo.TipoDocumento;
import sigefirrhh.persistencia.dao.CompromisoInicialDAO;
import sigefirrhh.persistencia.dao.CompromisoInicialDetalleDAO;
import sigefirrhh.persistencia.dao.ExpedienteDAO;
import sigefirrhh.persistencia.dao.GastoProyectadoDAO;
import sigefirrhh.persistencia.dao.imple.CompromisoInicialDAOImple;
import sigefirrhh.persistencia.dao.imple.CompromisoInicialDetalleDAOImple;
import sigefirrhh.persistencia.dao.imple.ExpedienteDAOImple;
import sigefirrhh.persistencia.dao.imple.GastoProyectadoDAOImple;
import sigefirrhh.persistencia.modelo.GastoProyectado;
import sigefirrhh.sistema.ValidadorSesion;
import sigefirrhh.struts.actionForm.CompromisoInicialForm;
import sigefirrhh.struts.addons.Comun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sigefirrhh.base.estructura.Organismo;

/** 
 *
 * @author Ali Sira
 */
public class CompromisoInicialAction extends DispatchAction implements Serializable, Comun {

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
			String resp = validarAcceso(request, Thread.currentThread().getStackTrace()[1].getMethodName());
    		if (resp == "valido"){
    			List<TipoDocumento> tipoDocumentos = new ArrayList<TipoDocumento>();
				tipoDocumentos.add(new TipoDocumento(0, "Resumen de Nomina"));
				tipoDocumentos.add(new TipoDocumento(1, "Memorandum"));
				tipoDocumentos.add(new TipoDocumento(2, "Oficio"));
				request.setAttribute("TipoDocumentos", tipoDocumentos);
				request.setAttribute("ano", ano);				
				fwd ="apruebaNuevo";

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
	
	public ActionForward guardar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
		
		error= new Object[2];
		messageResources = getResources(request);
		
		try {

			session = request.getSession();			
	        Organismo org = new Organismo();

			CriterioBusqueda criterio = new CriterioBusqueda();

			String resp = validarAcceso(request, Thread.currentThread().getStackTrace()[1].getMethodName());
    		if (resp == "valido"){
					
				org = ((LoginSession) session.getAttribute("loginSession")).getOrganismo();
				CompromisoInicialForm formaPeti = (CompromisoInicialForm ) form;

				Integer expeResul = 0;
				Date fecha = new Date();

				int idUsuario = 1;
				int idUnidadAdministadora = formaPeti.getIdUnidadAdministradora();

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
				List<GastoProyectado> listaGastoProyec = (List<GastoProyectado>) gastoProyectadoDAO.proyectarGasto(criterio);
				
 		        if (listaGastoProyec.size() > 0){
 		        	
 		        	Expediente expediente = new Expediente();
 		        	expediente.setExpediente(0);
					expediente.setFechaReg(fecha);
					expediente.setAno(ano);
					expediente.setEstatus(1);
					expediente.setIdUsuario(idUsuario);
					expediente.setObservacion(formaPeti.getObservacion());
					expediente.setIdOrganismo((int) org.getIdOrganismo());
					ValidadorSesion vs = new ValidadorSesion();
					Integer idOpcion = vs.getIdOpcion(request);
					expediente.setIdOpcion(idOpcion);//Buscar el id del proceso actual en la base de datos
					ExpedienteDAO expedienteDAO = new ExpedienteDAOImple();							
					expeResul = (Integer) expedienteDAO.guardar(expediente);
 		        	
 		        	Double montoTotal = 0.0;
					
					CompromisoInicialDAO compromisoInicialDAO = new CompromisoInicialDAOImple();
					CompromisoInicial compromisoInicial = new CompromisoInicial();
					compromisoInicial = (CompromisoInicial ) compromisoInicial.llenarBean(compromisoInicial,formaPeti);						
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
					
					CompromisoInicialDetalleDAO compromisoInicialDetalleDAO = new CompromisoInicialDetalleDAOImple();
					CompromisoInicialDetalle compromisoInicialDetalle= new CompromisoInicialDetalle();
					
					//Arreglos temporales que seran metidos en el form temporales 
					Integer[] ff = new Integer [listaGastoProyec.size()];
					Integer[] codCatePresu = new Integer [listaGastoProyec.size()];
					Integer[] codUel = new Integer [listaGastoProyec.size()];
					String[] denoUel = new String [listaGastoProyec.size()];
					String[] partida = new String [listaGastoProyec.size()];
					String[] denoPartida = new String [listaGastoProyec.size()];
					String[] mensajeSigecof = new String [listaGastoProyec.size()];
					Double[] dispo = new Double [listaGastoProyec.size()];
					Double[] monto = new Double [listaGastoProyec.size()];
					
					for (int i = 0;i < listaGastoProyec.size(); i++){
						
						//aqui agrego las clases del webservice y hago la llamada a la disponibilidad presupuestaria
						double dispoPresu = 3000000.0;//
 		        		
						if (dispoPresu > listaGastoProyec.get(i).getMonto()){
							compromisoInicialDetalle.setIdCompromisoInicial(idCompromisoInicial);
	 		        		compromisoInicialDetalle.setIdPartidaUelEspecifica(listaGastoProyec.get(i).getIdPartidaUelEspecifica());
	 		        		compromisoInicialDetalle.setFf(1);
	 		        		compromisoInicialDetalle.setMonto(listaGastoProyec.get(i).getMonto());
	 		        		
	 		        		expeResul = (Integer) compromisoInicialDetalleDAO.guardar(compromisoInicialDetalle);
	 		        		if (expeResul.equals(Integer.valueOf(0)) || expeResul == null){
	 							throw new Exception("Error 1 Desconocido en ResumenNominaInicialAction");
	 						}
	 		        		mensajeSigecof[i]="Imputacion guardada exitosamente";
						}else{
							mensajeSigecof[i]="Imputacion no posee imputacion presupuestaria";
						}
						
						ff[i]= listaGastoProyec.get(i).getFf();
						codCatePresu[i]= (listaGastoProyec.get(i).getCodCatePresu());
						codUel[i]= (listaGastoProyec.get(i).getCodUnidadEjecutora() );
					    denoUel[i]= (listaGastoProyec.get(i).getDenoUnidadEjecutora());
					    partida[i]= (listaGastoProyec.get(i).getCodPartida());
					    denoPartida[i]= (listaGastoProyec.get(i).getDenoPartida());
					    dispo[i]= (dispoPresu);
					    monto[i]= (listaGastoProyec.get(i).getMonto());
					    montoTotal += listaGastoProyec.get(i).getMonto();
 		        	}
					
					formaPeti.setFf(ff);
				    formaPeti.setCodCatePresu(codCatePresu);
				    formaPeti.setCodUel(codUel);
				    formaPeti.setDenoUel(denoUel);
				    formaPeti.setPartida(partida);
				    formaPeti.setDenoPartida(denoPartida);
				    formaPeti.setDispo(dispo);
				    formaPeti.setMonto(monto);							
					formaPeti.setExpediente(expeResul);
					formaPeti.setFechaRegistro(fecha);
					formaPeti.setMensajeSigecof(mensajeSigecof);
 		        	
 		        	formaPeti.setTotalResumen(montoTotal);
 		        	request.getSession().setAttribute(this.getClass().getName() +"Bean", false);
 		        	fwd = "apruebaGuardar";
 		        	
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