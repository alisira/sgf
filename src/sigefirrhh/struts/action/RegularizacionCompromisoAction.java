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
import sigefirrhh.persistencia.modelo.GastoProyectado;
import sigefirrhh.persistencia.modelo.Opcion;
import sigefirrhh.persistencia.modelo.UnidadAdministradora;
import sigefirrhh.login.LoginSession;
import sigefirrhh.persistencia.modelo.TipoDocumento;
import sigefirrhh.persistencia.dao.CompromisoInicialDAO;
import sigefirrhh.persistencia.dao.CompromisoInicialDetalleDAO;
import sigefirrhh.persistencia.dao.ExpedienteDAO;
import sigefirrhh.persistencia.dao.GastoProyectadoDAO;
import sigefirrhh.persistencia.dao.OpcionDAO;
import sigefirrhh.persistencia.dao.UnidadAdministradoraDAO;
import sigefirrhh.persistencia.dao.imple.CompromisoInicialDAOImple;
import sigefirrhh.persistencia.dao.imple.CompromisoInicialDetalleDAOImple;
import sigefirrhh.persistencia.dao.imple.ExpedienteDAOImple;
import sigefirrhh.persistencia.dao.imple.GastoProyectadoDAOImple;
import sigefirrhh.persistencia.dao.imple.OpcionDAOImple;
import sigefirrhh.persistencia.dao.imple.TipoDocumentoDAOImple;
import sigefirrhh.persistencia.dao.imple.UnidadAdministradoraDAOImple;
import sigefirrhh.sistema.ExcepcionSigefirrhh;
import sigefirrhh.sistema.ValidadorSesion;
import sigefirrhh.struts.actionForm.CompromisoInicialForm;
import sigefirrhh.struts.actionForm.ParametrosBusquedaForm;
import sigefirrhh.struts.actionForm.RegularizacionCompromisoForm;
import sigefirrhh.struts.addons.Comun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
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
	PrintWriter out = null;
	MessageResources messageResources = null;
	
	public ActionForward nuevo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
		
		messageResources = getResources(request);
    		
		try {
			if (validarAcceso(request, Thread.currentThread().getStackTrace()[1].getMethodName())){
    			RegularizacionCompromisoForm forma = (RegularizacionCompromisoForm) form;	
    			OpcionDAO opcionDAO = new OpcionDAOImple();
				CriterioBusqueda criterio =new CriterioBusqueda();
				criterio.addAno(ano);
				criterio.addDescripcion("compromisoInicial.do");//Hay que buscar modelar la base de datos para que este id sea buscado de acuerdo al proceso actual
				List<Opcion> listadoOpcion = (List<Opcion>) opcionDAO.buscarOpcionUsadoenExpediente(criterio);
				
				ParametrosBusquedaForm parametrosBusquedaForm= new ParametrosBusquedaForm();
				forma.setTituloApli("Buscar Expediente de Compromiso");				
				request.setAttribute("Opcion", listadoOpcion);
				request.setAttribute("ano", ano);
				request.setAttribute("ParametrosBusquedaForm", parametrosBusquedaForm);
				
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
		//System.out.println("Se Valido al usuario: en " + this.getClass().getName() +  " a las " + hora);		
		messageResources = getResources(request);
		String errorTem = null;//Variable que guarda temporalmente el mensaje de excepcion en el caso
		
		try{			
			session = request.getSession();
			out = response.getWriter();
	        response.setContentType("text/xml");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setStatus(HttpServletResponse.SC_OK);
	        out.write("<root>");
	        RegularizacionCompromisoForm forma = (RegularizacionCompromisoForm) form;
	        
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
				criterio.addEstatus(1);//Estatus Aprobado
				criterio.addRuta("compromisoInicial.do");
				
				ExpedienteDAO expedienteDAO = new ExpedienteDAOImple();
				List<Opcion> listadoExpediente = (List<Opcion>) expedienteDAO.buscarExpedienteOpcion(criterio);
				
		        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		        
		        if (listadoExpediente.size() > 0){
		        	for (int i = 0;i < listadoExpediente.size(); i++){
						out.write("<expediente>" + listadoExpediente.get(i).getExpediente() + "</expediente>");
						out.write("<fecha_reg>" + formatter.format(listadoExpediente.get(i).getFechaRegistro()) + "</fecha_reg>");						
						out.write("<cod_proceso>" + listadoExpediente.get(i).getCodigoOpcion() + "</cod_proceso>");
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
	
	public ActionForward cargar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		messageResources = getResources(request);		
    		
		try {
			
			if (validarAcceso(request, Thread.currentThread().getStackTrace()[1].getMethodName())){
	        	
    			RegularizacionCompromisoForm forma = (RegularizacionCompromisoForm) form;
    			
        		TipoDocumentoDAOImple tipoDocumentoDAO = new TipoDocumentoDAOImple();					
				List<TipoDocumento> listaTipoDocu= (List<TipoDocumento>) tipoDocumentoDAO.buscarTipoDocuTempo();
				
				Organismo org = new Organismo();
				org = ((LoginSession) session.getAttribute("loginSession")).getOrganismo();
				
				//Recupera la data del maestro compromiso
				CriterioBusqueda criterioBusqueda = new CriterioBusqueda();
				criterioBusqueda.addExpediente(forma.getExpediente());
				criterioBusqueda.addEstatus(1);
				criterioBusqueda.addIdOrganismo((int) org.getIdOrganismo());
				criterioBusqueda.addAno(ano);
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

				//Crea un form y carga los datos del maestro
				CompromisoInicialForm comIni = new CompromisoInicialForm(compromisoInicialDetalle.size());
				comIni.setAno(ano);
				comIni.setDenoTipoFondo("Xfondo");
				comIni.setDocumento(compromisoInicial.get(0).getDocumento());
				comIni.setCodUnidadAdministradora((unidadAdministradora.get(0).getCodUnidadAdministradora()));
				comIni.setDenoUniAdmi(unidadAdministradora.get(0).getDenominacion());
				comIni.setObservacion(compromisoInicial.get(0).getObservacion());
				comIni.setIdCompromisoInicial(compromisoInicial.get(0).getIdCompromisoInicial());
				forma.setIdCompromisoInicial(compromisoInicial.get(0).getIdCompromisoInicial());
				
				
				//Carga los datos del detalle
				for (int c=0;c<compromisoInicialDetalle.size();c++){
					comIni.setFf(compromisoInicialDetalle.get(c).getFf(),c);
					comIni.setCodCatePresu(compromisoInicialDetalle.get(c).getCodCatePresu(),c);
					comIni.setCodUel(compromisoInicialDetalle.get(c).getCodUnidadEjecutora() ,c);
				    comIni.setDenoUel(compromisoInicialDetalle.get(c).getDenoUnidadEjecutora(),c);
				    comIni.setPartida(compromisoInicialDetalle.get(c).getCodPartida(),c);
				    comIni.setDenoPartida(compromisoInicialDetalle.get(c).getDenoPartida(),c);
				    comIni.setDispo(500.0,c);
				    comIni.setMonto(compromisoInicialDetalle.get(c).getMonto(),c);
				}
				
				request.setAttribute("Compromiso", comIni);
				request.setAttribute("TipoDocumento", listaTipoDocu);
				request.setAttribute("ano", ano);				
				forma.setTituloApli("Regularizacion de Compromiso");	
				
				fwd ="apruebaCarga";	        	
        	
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
	
public ActionForward guardar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
		
		messageResources = getResources(request);
		
		try {

			session = request.getSession();			
	        Organismo org = new Organismo();

			CriterioBusqueda criterio = new CriterioBusqueda();
			
    		if (validarAcceso(request, Thread.currentThread().getStackTrace()[1].getMethodName())){
					
				org = ((LoginSession) session.getAttribute("loginSession")).getOrganismo();
				RegularizacionCompromisoForm forma = (RegularizacionCompromisoForm ) form;

				Integer expeResul = 0;
				Date fecha = new Date();

				int idUsuario = 1;

				criterio.addIdOrganismo((int) org.getIdOrganismo());
				criterio.addAno(ano);
 		        	
 		        	Expediente expediente = new Expediente();
 		        	expediente.setExpediente(0);
					expediente.setFechaRegistro(fecha);
					expediente.setAno(ano);
					expediente.setEstatus(0);
					expediente.setIdUsuario(idUsuario);
					expediente.setObservacion(forma.getObservacion());
					expediente.setIdOrganismo((int) org.getIdOrganismo());
					
					ValidadorSesion vs = new ValidadorSesion();
					Integer idOpcion = vs.getIdOpcion(request);
					expediente.setIdOpcion(idOpcion);//Buscar el id del proceso actual en la base de datos
					ExpedienteDAO expedienteDAO = new ExpedienteDAOImple();
					expeResul = (Integer) expedienteDAO.guardar(expediente);					
 		        	
 		        	Double montoTotal = 0.0;
					
					CompromisoInicialDAO compromisoInicialDAO = new CompromisoInicialDAOImple();
					CompromisoInicial compromisoInicial = new CompromisoInicial();
					compromisoInicial = (CompromisoInicial ) compromisoInicial.llenarBean(compromisoInicial,forma);						
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
	 							throw new RuntimeException("Error Desconocido en ResumenNominaInicialAction");
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
 		        	
 		       
	        }		
    		
		} catch (ExcepcionSigefirrhh e) {
			
			if (e.toString().equals("sesionCerrada")){				
				fwd = e.toString();	
	        }else if(e.toString().equals("datosIncompletos")){	        	
				fwd = "datosIncompletos";
	        }else if(e.toString().equals("sinResultados")){
				fwd = "sinResultados";
	        }else if (e.toString().equals("sinPermiso")){					
				fwd = "sinPermiso";
	        }			
			
		} catch (Exception e) {			
			System.out.println("Error Grave: " + this.getClass().getName() + " a las " + hora);
			e.printStackTrace();
			request.setAttribute("mensaje", messageResources.getMessage("errors.aplicacion"));
    		fwd = "respuestaProceso";				
		
		}
		
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