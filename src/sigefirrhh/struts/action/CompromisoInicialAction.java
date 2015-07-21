package sigefirrhh.struts.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.*;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

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
import sigefirrhh.persistencia.dao.imple.GenericDAOImplHibernate;
import sigefirrhh.persistencia.modelo.GastoProyectado;
import sigefirrhh.sistema.ExcepcionSigefirrhh;
import sigefirrhh.sistema.ValidadorSesion;
import sigefirrhh.struts.actionForm.CompromisoInicialForm;
import sigefirrhh.struts.addons.Comun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sigefirrhh.base.estructura.Organismo;





import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import java.math.BigDecimal;

import sigecof.clRegularizacionCompromisoInicial;
import sigecof.CompromisoInicialDTO;
import sigecof.ImputacionesCompromisoInicialDTO;

import java.util.ArrayList;

import sigecof.ExpedienteTO;
import sigecof.SesionTO;
import sigecof.UsuarioWFTO;
import sigecof.WorkItemTO;

import java.util.ArrayList;
import java.math.BigDecimal;

import sigecof.clResumenNomina;
import sigecof.ResumenNominaDTO;
import sigecof.ImputacionesResNominaDTO;
import sigecof.EjercicioPresupuestarioTO;
import sigecof.Uadto;
import sigecof.OrganismoTO;
import sigecof.TipoFondoTO;
import sigecof.TipoNominaTO;
import sigecof.TipoDocumentoTO;
import sigecof.FuenteFinanciamientoTO;
import sigecof.Uejto;
import sigecof.Uejlto;
import sigecof.CategoriaPresupuestariaTO;
import sigecof.ObjetoGastoTO;
import sigecof.ControlDivisasTO;
import sigecof.ExpedienteTO;
import sigecof.SesionTO;
import sigecof.UsuarioWFTO;
import sigecof.WorkItemTO;

import java.math.BigDecimal;
import java.util.ArrayList;





import sigecof.DisponibilidadPresupuestariaDTO;
import sigecof.clDisponibilidadPresupuestaria;
import sigecof.clRegistroCompromisoInicial;
import sigecof.clResumenNominaRendicion;
import sigecof.ResumenRendicionNominaDTO;
import sigecof.RetencionesRendNominaDTO;
import sigecof.ImputacionesRendNominaDTO;
import sigecof.EjercicioPresupuestarioTO;
import sigecof.OrganismoTO;
import sigecof.CompromisoTO;
import sigecof.CausadoTO;
import sigecof.Uadto;
import sigecof.Uejto;
import sigecof.Uejlto;
import sigecof.PersonaTO;
import sigecof.TipoPagoTO;
import sigecof.TipoFondoTO;
import sigecof.CategoriaPresupuestariaTO;
import sigecof.ObjetoGastoTO;
import sigecof.FuenteFinanciamientoTO;
import sigecof.RetencionTO;
import sigecof.TipoRetencionTO;
import sigecof.HistoricoCausadoTO;
import sigecof.SesionTO;
import sigecof.TipoDocumentoTO;
import sigecof.ExpedienteTO;
import sigecof.UsuarioWFTO;
import sigecof.WorkItemTO;
import sigecof.ControlDivisasTO;
import sigecof.TipoMonedaTO;
import sigecof.ConceptoPagoTO;
import sigecof.CuentaTO;
import sigecof.TipoPasivoTO;
import sigecof.EnteReceptorTO;
import sigecof.ReglaRetencionTO;




/** 
 *
 * @author Ali Sira
 */
public class CompromisoInicialAction extends DispatchAction implements Serializable, Comun {

	private static final long serialVersionUID = 6333610918451615896L;	
	protected final Log logger = LogFactory.getLog(getClass());
	HttpSession session = null;
	String fwd = null;	
	PrintWriter out = null;	
	MessageResources messageResources = null;
	
	public ActionForward nuevo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
					
		
		messageResources = getResources(request);		
    		
		try {
			
			if (validarAcceso(request, Thread.currentThread().getStackTrace()[1].getMethodName())){
    			List<TipoDocumento> tipoDocumentos = new ArrayList<TipoDocumento>();
				tipoDocumentos.add(new TipoDocumento(0, "Resumen de Nomina"));
				tipoDocumentos.add(new TipoDocumento(1, "Memorandum"));
				tipoDocumentos.add(new TipoDocumento(2, "Oficio"));
				request.setAttribute("TipoDocumentos", tipoDocumentos);
				request.setAttribute("ano", ano);				
				fwd ="apruebaNuevo";
    		}			
			
			//URL url3 = new URL("http://www.lapatilla.com/site/");			
			
			/*URL url;
			URLConnection uc;
			String urlString="http://10.79.6.232/documentation.html";
			//String urlString="http://www.lapatilla.com/site/";
			System.out.println("Getting content for URl : " + urlString);
			url = new URL(urlString);
			uc = url.openConnection();
			uc.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
			uc.connect();
			File destino = new File("destino.txt");
			InputStream in = uc.getInputStream();
					  
			try {			 
				OutputStream out = new FileOutputStream(destino);								
			
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
			 		out.write(buf, 0, len);
				}
								
				in.close();
				out.close();
			} catch (IOException ioe){
				ioe.printStackTrace();
			}
			*/
			
			
			
	        DisponibilidadPresupuestariaDTO disponibilidad = new DisponibilidadPresupuestariaDTO();
	        clDisponibilidadPresupuestaria consulta = new clDisponibilidadPresupuestaria();
	              
	        consulta.setId_organismo("37");
	        consulta.setCod_unidad_administ("03036");
	        consulta.setCod_Unidad_Ejecutora("03036");
	        consulta.setAnho_fiscal(2014);
	        consulta.setFuente_Financiamiento("1");
	        consulta.setCategoria_Presupuestaria("370050001");
	        consulta.setObjeto_Gasto("401010100");
	        
	        //consulta.setObjeto_Gasto("401.01.01.00");
	        //consulta.setDisponibilidad_Imputacion(BigDecimal.ZERO);
	        //consulta.setp_Disponibilidad_Partida(BigDecimal.ZERO);
	        
	        //String url = consulta.getUrl();
	        disponibilidad = consulta.EjecuteConsulta();
	        
	        //System.out.println("Url = " + url);
	        System.out.println("Disponibilidad Imputacion = " + disponibilidad.getDisponibilidadImputacion());
	        System.out.println("Disponibilidad Partida    = " + disponibilidad.getDisponibilidadPartida());
	        System.out.println("Estatus de la operacón = " + disponibilidad.getEstatus());
	        System.out.println("Fin de la aplicacion");
	        
	        
	        
	        clRegistroCompromisoInicial CrearExpediente = new clRegistroCompromisoInicial();
	        ExpedienteTO expedienteTO = new ExpedienteTO();
	        UsuarioWFTO usuarioTO = new UsuarioWFTO();
	        SesionTO sesionTO = new SesionTO();
	        WorkItemTO workitemTO = new WorkItemTO();
	        
	        expedienteTO.setAnho(2014);
	        expedienteTO.setIdOrganismo("37");
	        expedienteTO.setIdProceso("P_RBT_REG_COMP");
	        expedienteTO.setDenominacionProceso("Registro del Compromiso");
	        expedienteTO.setIdUsuario("MPPDP_SARAHI");
	        expedienteTO.setNombreUsuario("SARAHI");
	        expedienteTO.setDescripcion("Registro del Compromiso");
	        expedienteTO.setObservacion("Registro de Compromiso por Webservice");
	        expedienteTO.setNombre("Registro de Compromiso por Webservice");
	        expedienteTO.setInstancia("0");
	        usuarioTO.setIdUsuario("MPPDP_SARAHI");
	        usuarioTO.setIpUsuario("10.90.24.79");
	        usuarioTO.setRol("R_ANA_ADMI_II");
	        
	        sesionTO.setIdUsuario("MPPDP_SARAHI");
	        sesionTO.setIpMaquina("10.90.24.79");
	        sesionTO.setMaquina("DESARROLLO WEBSERVICE DIGEMAFE");
	        sesionTO.setPrograma("Registro del Compromiso");
	        sesionTO.setFechaInicio(null);
	        sesionTO.setFechaFinalizacion(null);
	        sesionTO.setFechaMovimiento(null);
	        //sesionTO.setIdSesion(null);

	        CrearExpediente.setExpedienteTO(expedienteTO);
	        CrearExpediente.setUsuarioTO(usuarioTO);
	        CrearExpediente.setSesionTO(sesionTO);
	        CrearExpediente.setIP("10.90.24.73");
	        CrearExpediente.setPuerto("8080");
	        System.out.println("Se va a llamar a la clase cliente de CrearExpediente ");
	        workitemTO = CrearExpediente.CrearExpediente();
	        
	        System.out.println("Numero Expediente: " + workitemTO.getExpediente().getIdExpediente());
	        System.out.println("Numero WorkItem: " + workitemTO.getIdWorkItem());
	        System.out.println("Descripción    : " + workitemTO.getDescripcion());
	        System.out.println("-------------------------------");
			
	        
	        
	        clRegistroCompromisoInicial Ci = new clRegistroCompromisoInicial();
	        CompromisoInicialDTO CiDTO = new CompromisoInicialDTO();
	        ArrayList<ImputacionesCompromisoInicialDTO> ImpCiDTO = new ArrayList<ImputacionesCompromisoInicialDTO>();
	        ImputacionesCompromisoInicialDTO registro = new ImputacionesCompromisoInicialDTO();
	        Ci.setAnho_fiscal(2014);
	        Ci.setCod_Sigecof("37");
	        Ci.setCod_unidad_administ("03036");
	        Ci.setPersid_cuentadante(13863);
	        Ci.setTipo_pago(3);
	        Ci.setTipo_fondo(3);
	        Ci.setTipo_documento(47);
	        Ci.setNumero_documento("1006");
	        Ci.setOrigen_presupuestario("S");
	        Ci.setObservacion("Registro de compromiso realizado por el Webservice");
	        Ci.setGaceta_credito_adicional(null);
	        Ci.setDecreto_credito_adicional(null);
	        Ci.setFecha_credito_adicional(null);
	        Ci.setGaceta_rectificacion(null);
	        Ci.setDecreto_rectificacion(null);
	        Ci.setFecha_rectificacion(null);
	        Ci.setNumero_expediente(workitemTO.getExpediente().getIdExpediente());
	        Ci.setIP("10.90.24.73");
	        Ci.setPuerto("8080");
	        //Ci.setNumero_compromiso(null);
	        
	         //No tiene dispobibilidad  
	        /*
	        ImpCiDTO = new ArrayList<ImputacionesCompromisoInicialDTO>();
	        registro.setFuenteFinanciamiento("1");
	        registro.setCodUnidadEjecutora("00024");
	        registro.setCategoriaPresupuestaria("370001001");
	        registro.setObjetoGasto("401011000");
	        registro.setMonto(BigDecimal.valueOf(3000));
	        ImpCiDTO.add(registro);
	        */
	        
	         
	        //Prueba con disponibilidad menor	        
	       /* registro = new ImputacionesCompromisoInicialDTO();
	        registro.setFuenteFinanciamiento("1");
	        registro.setCodUnidadEjecutora("00024");
	        registro.setCategoriaPresupuestaria("370001001");
	        registro.setObjetoGasto("401011800");
	        registro.setMonto(BigDecimal.valueOf(3000));
	        ImpCiDTO.add(registro);
	       */
	        
	        //tienen disponibilidad
	        registro = new ImputacionesCompromisoInicialDTO();
	        registro.setFuenteFinanciamiento("1");
	        registro.setCodUnidadEjecutora("03036");
	        registro.setCategoriaPresupuestaria("370050001");
	        registro.setObjetoGasto("401010100");
	        registro.setMonto(BigDecimal.valueOf(3000));
	        ImpCiDTO.add(registro);
	        
	        registro = new ImputacionesCompromisoInicialDTO();
	        registro.setFuenteFinanciamiento("1");
	        registro.setCodUnidadEjecutora("03036");
	        registro.setCategoriaPresupuestaria("370050001");
	        registro.setObjetoGasto("401060100");
	        registro.setMonto(BigDecimal.valueOf(3000));
	        ImpCiDTO.add(registro);
	        
	        registro = new ImputacionesCompromisoInicialDTO();
	        registro.setFuenteFinanciamiento("1");
	        registro.setCodUnidadEjecutora("03036");
	        registro.setCategoriaPresupuestaria("370050001");
	        registro.setObjetoGasto("401040800");
	        registro.setMonto(BigDecimal.valueOf(3000));
	        ImpCiDTO.add(registro);
	        
	        
	        Ci.setDetalles_compromiso(ImpCiDTO);
	        System.out.println("Se va a llamar a la clase cliente de Registro de Compromiso, Expediente " + Ci.getNumero_expediente());
	        CiDTO = Ci.RegistroCompromiso();
	        
	        System.out.println("Numero Expediente: " + CiDTO.getNumeroExpediente());
	        System.out.println("Numero Compromiso: " + CiDTO.getNumeroCompromiso());
	        System.out.println("-------------------------------");
	        System.out.println("Fue Guardado? " + CiDTO.getDetallesCompromiso().get(0).isGuardado());
	        System.out.println("Mensaje: " + CiDTO.getDetallesCompromiso().get(0).getMensajeRta());
	        
	        
	        
			
			//SocketAddress addr3 = new InetSocketAddress("socks.example.com", 1080);
			//Proxy proxy3 = new Proxy(Proxy.Type.SOCKS, addr3);
			//URL url3 = new URL("http://www.lapatilla.com/site/");			
			//URLConnection conn3 = url3.openConnection(proxy3);
			
			/*
			SocketAddress addr = new InetSocketAddress("proxyr.mf.gob.ve", 3128);
			Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);

			//System.setProperty("http.proxyHost", "proxyr.mf.gob.ve");
			//System.setProperty("http.proxyPort", "3128");
			URL url = new URL("http://sonnyt.com/uglyemail/");
			//URL url = new URL("http://10.79.6.231/InterfazNegociadoraWEB/inicio.jsp");
			//
			
			URL url2 = new URL("http://sonnyt.com/uglyemail/");
			URLConnection conn2 = url2.openConnection(Proxy.NO_PROXY);
			
			//URL url = new URL("http://java.example.org/");
			URLConnection conn = url.openConnection(proxy);
			//url.openConnection();
			Object jj = url.getContent();*/
			
			
			
			
			
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
			logger.error(null, e);			
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
 		        	
 		        	

 		        	clRegistroCompromisoInicial crearExpediente = new clRegistroCompromisoInicial();
 		        	ExpedienteTO expedienteTO = new ExpedienteTO();
 		        	UsuarioWFTO usuarioTO = new UsuarioWFTO();
 		        	SesionTO sesionTO = new SesionTO();
 		        	WorkItemTO workitemTO = new WorkItemTO();
 		         
					expedienteTO.setAnho(2014);
					expedienteTO.setIdOrganismo("37");
					expedienteTO.setIdProceso("P_RBT_REG_COMP");
					expedienteTO.setDenominacionProceso("Registro del Compromiso");
					expedienteTO.setIdUsuario("MPPDP_SARAHI");
					expedienteTO.setNombreUsuario("SARAHI");
					expedienteTO.setDescripcion("Registro del Compromiso");
					expedienteTO.setObservacion("Registro de Compromiso por Webservice");
					expedienteTO.setNombre("Registro de Compromiso por Webservice");
					expedienteTO.setInstancia("0");
					usuarioTO.setIdUsuario("MPPDP_SARAHI");
					usuarioTO.setIpUsuario("10.90.24.79");
					usuarioTO.setRol("R_ANA_ADMI_II");
 		         
 		         
 		         sesionTO.setIdUsuario("MPPDP_SARAHI");
 		         sesionTO.setIpMaquina("10.90.24.79");
 		         sesionTO.setMaquina("DESARROLLO WEBSERVICE DIGEMAFE");
 		         sesionTO.setPrograma("Registro del Compromiso");
 		         sesionTO.setFechaInicio(null);
 		         sesionTO.setFechaFinalizacion(null);
 		         sesionTO.setFechaMovimiento(null);
 		         //sesionTO.setIdSesion(null);

 		        crearExpediente.setExpedienteTO(expedienteTO);
 		        crearExpediente.setUsuarioTO(usuarioTO);
 		        crearExpediente.setSesionTO(sesionTO);
 		        crearExpediente.setIP("10.90.24.73");
 		        crearExpediente.setPuerto("8080");
 		         
 		         System.out.println("Se va a llamar a la clase cliente de CrearExpediente ");
 		         workitemTO = crearExpediente.CrearExpediente();
 		         
 		         System.out.println("Numero Expediente: " + workitemTO.getExpediente().getIdExpediente());
 		         System.out.println("Numero WorkItem: " + workitemTO.getIdWorkItem());
 		         System.out.println("Descripción    : " + workitemTO.getDescripcion());
 		         System.out.println("-------------------------------");

 		        	
   
 		           
 		           
 		        clRegistroCompromisoInicial Ci = new clRegistroCompromisoInicial();
 		        CompromisoInicialDTO CiDTO = new CompromisoInicialDTO();
 		        ArrayList<ImputacionesCompromisoInicialDTO> ImpCiDTO = new ArrayList<ImputacionesCompromisoInicialDTO>();
 		        ImputacionesCompromisoInicialDTO registro = new ImputacionesCompromisoInicialDTO();
 		        Ci.setAnho_fiscal(2014);
 		        Ci.setCod_Sigecof("37");
 		        Ci.setCod_unidad_administ("00011");
 		        Ci.setPersid_cuentadante(13863);
 		        Ci.setTipo_pago(3);
 		        Ci.setTipo_fondo(3);
 		        Ci.setTipo_documento(47);
 		        Ci.setNumero_documento("1006");
 		        Ci.setOrigen_presupuestario("S");
 		        Ci.setObservacion("Registro de compromiso realizado por el Webservice");
 		        Ci.setGaceta_credito_adicional(null);
 		        Ci.setDecreto_credito_adicional(null);
 		        Ci.setFecha_credito_adicional(null);
 		        Ci.setGaceta_rectificacion(null);
 		        Ci.setDecreto_rectificacion(null);
 		        Ci.setFecha_rectificacion(null);
 		        Ci.setNumero_expediente(workitemTO.getExpediente().getIdExpediente());
 		        //Ci.setNumero_compromiso(null);
 		        
 		            
 		        ImpCiDTO = new ArrayList<ImputacionesCompromisoInicialDTO>();
 		        registro.setFuenteFinanciamiento("1");
 		        registro.setCodUnidadEjecutora("00024");
 		        registro.setCategoriaPresupuestaria("370001001");
 		        registro.setObjetoGasto("401030100");
 		        registro.setMonto(BigDecimal.valueOf(3000));
 		        ImpCiDTO.add(registro);
 		        
 		        registro = new ImputacionesCompromisoInicialDTO();
 		        registro.setFuenteFinanciamiento("1");
 		        registro.setCodUnidadEjecutora("00024");
 		        registro.setCategoriaPresupuestaria("370001001");
 		        registro.setObjetoGasto("401020100");
 		        registro.setMonto(BigDecimal.valueOf(3000));
 		        ImpCiDTO.add(registro);
 		        
 		        registro = new ImputacionesCompromisoInicialDTO();
 		        registro.setFuenteFinanciamiento("1");
 		        registro.setCodUnidadEjecutora("00024");
 		        registro.setCategoriaPresupuestaria("370001001");
 		        registro.setObjetoGasto("401010100");
 		        registro.setMonto(BigDecimal.valueOf(3000));
 		        ImpCiDTO.add(registro);
 		        
 		        Ci.setDetalles_compromiso(ImpCiDTO);
 		        System.out.println("Se va a llamar a la clase cliente de Registro de Compromiso, Expediente " + Ci.getNumero_expediente());
 		        CiDTO = Ci.RegistroCompromiso();
 		        
 		        System.out.println("Numero Expediente: " + CiDTO.getNumeroExpediente());
 		        System.out.println("Numero Compromiso: " + CiDTO.getNumeroCompromiso());
 		        System.out.println("-------------------------------");
 		        System.out.println("Fue Guardado? " + CiDTO.getDetallesCompromiso().get(0).isGuardado());
 		        System.out.println("Mensaje: " + CiDTO.getDetallesCompromiso().get(0).getMensajeRta()); 
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		           
 		        	
 		        	
 		        	
 		        	Expediente expediente = new Expediente();
 		//        	expediente.setExpediente(workitemTO.getExpediente().getIdExpediente());
					expediente.setFechaRegistro(fecha);
					expediente.setAno(ano);
					expediente.setEstatus(0);
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
 		        	
 		        }else{ 		        	
 		        	throw new ExcepcionSigefirrhh("sinResultados");
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
	
	 private static void PDD_Compromiso() {
	        clRegistroCompromisoInicial PDDCompromiso = new clRegistroCompromisoInicial();
	        Boolean Resulta;
	        PDDCompromiso.setAnho_fiscal(2014);
	        PDDCompromiso.setCod_Sigecof("37");
	        PDDCompromiso.setNumero_expediente(4263);
	        PDDCompromiso.setEstatus(1);
	        Resulta = PDDCompromiso.PDDCompromiso();
	        System.out.println("PDD Compromiso = " + Resulta);
	    }
	   
	    private  static Integer Crear_Expediente() {
	        clRegistroCompromisoInicial CrearExpediente = new clRegistroCompromisoInicial();
	        ExpedienteTO expedienteTO = new ExpedienteTO();
	        UsuarioWFTO usuarioTO = new UsuarioWFTO();
	        SesionTO sesionTO = new SesionTO();
	        WorkItemTO workitemTO = new WorkItemTO();
	        
	        expedienteTO.setAnho(2014);
	        expedienteTO.setIdOrganismo("37");
	        expedienteTO.setIdProceso("P_RBT_REG_COMP");
	        expedienteTO.setDenominacionProceso("Registro del Compromiso");
	        expedienteTO.setIdUsuario("MPPDP_SARAHI");
	        expedienteTO.setNombreUsuario("SARAHI");
	        expedienteTO.setDescripcion("Registro del Compromiso");
	        expedienteTO.setObservacion("Registro de Compromiso por Webservice");
	        expedienteTO.setNombre("Registro de Compromiso por Webservice");
	        expedienteTO.setInstancia("0");
	        usuarioTO.setIdUsuario("MPPDP_SARAHI");
	        usuarioTO.setIpUsuario("10.90.24.79");
	        usuarioTO.setRol("R_ANA_ADMI_II");
	        
	        sesionTO.setIdUsuario("MPPDP_SARAHI");
	        sesionTO.setIpMaquina("10.90.24.79");
	        sesionTO.setMaquina("DESARROLLO WEBSERVICE DIGEMAFE");
	        sesionTO.setPrograma("Registro del Compromiso");
	        sesionTO.setFechaInicio(null);
	        sesionTO.setFechaFinalizacion(null);
	        sesionTO.setFechaMovimiento(null);
	        //sesionTO.setIdSesion(null);

	        CrearExpediente.setExpedienteTO(expedienteTO);
	        CrearExpediente.setUsuarioTO(usuarioTO);
	        CrearExpediente.setSesionTO(sesionTO);
	        CrearExpediente.setIP("10.90.24.73");
	        CrearExpediente.setPuerto("8080");
	        
	        System.out.println("Se va a llamar a la clase cliente de CrearExpediente ");
	        workitemTO = CrearExpediente.CrearExpediente();
	        
	        System.out.println("Numero Expediente: " + workitemTO.getExpediente().getIdExpediente());
	        System.out.println("Numero WorkItem: " + workitemTO.getIdWorkItem());
	        System.out.println("Descripción    : " + workitemTO.getDescripcion());
	        System.out.println("-------------------------------");
	        return workitemTO.getExpediente().getIdExpediente();
	    }


	    private  static void Regulariza_Compromiso() {
	        clRegularizacionCompromisoInicial Ci = new clRegularizacionCompromisoInicial();
	        CompromisoInicialDTO CiDTO = new CompromisoInicialDTO();
	        ArrayList<ImputacionesCompromisoInicialDTO> ImpCiDTO = new ArrayList<ImputacionesCompromisoInicialDTO>();
	        ImputacionesCompromisoInicialDTO registro = new ImputacionesCompromisoInicialDTO();
	        Ci.setAnho_fiscal(2014);
	        Ci.setCod_Sigecof("37");
	        Ci.setCod_unidad_administ("00011");
	        Ci.setPersid_cuentadante(13863);
	        Ci.setTipo_pago(3);
	        Ci.setTipo_fondo(3);
	        Ci.setTipo_documento(47);
	        Ci.setNumero_documento("1002");
	        Ci.setOrigen_presupuestario("S");
	        Ci.setObservacion("Registro de compromiso realizado por el Webservice");
	        Ci.setGaceta_credito_adicional(null);
	        Ci.setDecreto_credito_adicional(null);
	        Ci.setFecha_credito_adicional(null);
	        Ci.setGaceta_rectificacion(null);
	        Ci.setDecreto_rectificacion(null);
	        Ci.setFecha_rectificacion(null);
	        Ci.setNumero_expediente(Crear_Expediente());
	        Ci.setNumero_compromiso(1997);
	        
	        //Ci.setNumero_compromiso(null);
	                   
	        ImpCiDTO = new ArrayList<ImputacionesCompromisoInicialDTO>();
	        registro.setFuenteFinanciamiento("1");
	        registro.setCodUnidadEjecutora("00024");
	        registro.setCategoriaPresupuestaria("370001001");
	        registro.setObjetoGasto("401030100");
	        registro.setMonto(BigDecimal.valueOf(-500));
	        ImpCiDTO.add(registro);
	        
	        registro = new ImputacionesCompromisoInicialDTO();
	        registro.setFuenteFinanciamiento("1");
	        registro.setCodUnidadEjecutora("00024");
	        registro.setCategoriaPresupuestaria("370001001");
	        registro.setObjetoGasto("401020100");
	        registro.setMonto(BigDecimal.valueOf(200));
	        ImpCiDTO.add(registro);
	        
	        registro = new ImputacionesCompromisoInicialDTO();
	        registro.setFuenteFinanciamiento("1");
	        registro.setCodUnidadEjecutora("00024");
	        registro.setCategoriaPresupuestaria("370001001");
	        registro.setObjetoGasto("401010100");
	        registro.setMonto(BigDecimal.valueOf(0));
	        ImpCiDTO.add(registro);
	        
	        Ci.setDetalles_compromiso(ImpCiDTO);
	        System.out.println("Se va a llamar a la clase cliente de Registro de Compromiso, Expediente " + Ci.getNumero_expediente());
	        CiDTO = Ci.RegistroCompromiso();
	        
	        System.out.println("Numero Expediente: " + CiDTO.getNumeroExpediente());
	        System.out.println("Numero Compromiso: " + CiDTO.getNumeroCompromiso());
	        System.out.println("-------------------------------");
	        System.out.println("Fue Guardado? " + CiDTO.getDetallesCompromiso().get(0).isGuardado());
	        System.out.println("Mensaje: " + CiDTO.getDetallesCompromiso().get(0).getMensajeRta());
	    }



	    public static ImputacionesResNominaDTO registro = new ImputacionesResNominaDTO();
	    public static ArrayList<ImputacionesResNominaDTO> detalle = new ArrayList<ImputacionesResNominaDTO>();
	    public static ResumenNominaDTO maestro = new ResumenNominaDTO();
	    //public static SolicitudFondoTO maestro2 = new SolicitudFondoTO();
	    public static Integer i;

	    private static  void Resumen_Nomina()
	    {    
	        //BigDecimal cm = 0;
	        //Scanner in = new Scanner(System.in);
	        Integer numero;
	        String Categoria;
	        Number number;
	        BigDecimal big;
	        Integer Expediente = Crear_Expediente();
	        clResumenNomina metodo = new clResumenNomina();

	        EjercicioPresupuestarioTO ejercicioPresupuestario = new EjercicioPresupuestarioTO();
	        ejercicioPresupuestario.setAnho(2014);
	        maestro.setEjercicioPresupuestario(ejercicioPresupuestario);
	        OrganismoTO organismo = new OrganismoTO();
	        organismo.setIdOrganismo("37");
	        maestro.setOrganismo(organismo);
	        Uadto UnidadAdministradora = new Uadto();
	        UnidadAdministradora.setIdUEJ("00011");
	        maestro.setUad(UnidadAdministradora);
	        TipoFondoTO tipofondo = new TipoFondoTO();
	        tipofondo.setIdTipoFondo(3);
	        maestro.setTipoFondo(tipofondo);
	        TipoDocumentoTO tipodocumento = new TipoDocumentoTO();
	        tipodocumento.setIdTipoDocumento(48);
	        TipoNominaTO tiponomina = new TipoNominaTO();
	        tiponomina.setIdTipoNomina(2);
	        tiponomina.setTipoFondo(tipofondo);
	        tiponomina.setTipoDocumento(tipodocumento);
	        maestro.setTipoNomina(tiponomina);
	        ControlDivisasTO controldivisas = new ControlDivisasTO();
	        controldivisas.setEjercicioPresupuestario(ejercicioPresupuestario);
	        controldivisas.setOrganismo(organismo);
	        controldivisas.setDivisas("N");
	        maestro.setControlDivisas(controldivisas);
	        maestro.setCodigoMoneda("VEB");
	        maestro.setMes(9);
	        maestro.setObservacion("OBSERVACIONES NOMINA PRUEBA");
	        maestro.setTipoDocumento(tipodocumento);
	        maestro.setExpediente(Expediente);
	        maestro.setWorkItem(1);
	/*************************************************************/  
	        i = 1;
	        Detalles_Nomina("00024","370001001","01","401010100",1,BigDecimal.valueOf(48000),2014, Expediente, 1); i++;
	/*      
	        Detalles_Nomina("00011","370001001","01","401010100",1,Double.valueOf(Integer.toString(35000)),Expediente); i++;
	        Detalles_Nomina("00051","370001001","01","401010100",1,Double.valueOf(Integer.toString(22000)),Expediente); i++;
	        Detalles_Nomina("00063","370001001","01","401010100",1,Double.valueOf(Integer.toString(15000)),Expediente); i++;
	        Detalles_Nomina("00032","370001001","01","401010100",1,Double.valueOf(Integer.toString(8000)),Expediente);
	       
	        
	           for (i=0;i<detalle.size();i++) {
	            
	             registro = detalle.get(i);
	             System.out.println("Registro " + i + " - " + registro.getMensajeRta() + " | " + registro.getFuente_Financiamiento() + " | " + registro.getCod_unidad_ejecutora() + " | " + registro.getCategoria_Presupuestaria());
	           }
	 */          
	/**************************************************************/       
	        
	        System.out.println("Se va a llamar al Webservice pasando parametros" );
	        
	        maestro.setDetalleSolicitudFondo(detalle);
	        
	        metodo.setParametro1(maestro);
	        
	        maestro = metodo.EjecutaConsulta();
	        
	        System.out.println("Numero de registros devueltos: ");
	        //detalle = maestro.getDetalleSolicitudFondo();
	/*          for (int i=0;i<detalle.size();i++) {
	              
	             registro = detalle.get(i);
	             System.out.println("Registro " + i + " - " + registro.getMensajeRta() + "/" + registro.getFuente_Financiamiento() + "/" + registro.getCod_unidad_ejecutora() + "/" + registro.getCategoria_Presupuestaria() + "/" + registro.getGuardado() + "/" + registro.getMensajeRta());
	           }
	        System.out.println("Fin ");
	 */       
	/**************************************************************/   
	    }
	    
	    private  static void Detalles_Nomina(String UnidadEjecutora, 
	                                         String CategoriaPresupuestaria, 
	                                         String FuenteFinanciamiento,
	                                         String ObjetoGasto,
	                                         Integer Periodo,
	                                         BigDecimal Monto,
	                                         Integer Anho_Fiscal,
	                                         Integer Expediente,
	                                         Integer WorkItem) {
	            registro = new ImputacionesResNominaDTO();           
	            Integer FF;
	            FF = Integer.valueOf(FuenteFinanciamiento);
	            FuenteFinanciamientoTO fuenteFinanciamiento = new FuenteFinanciamientoTO();
	            fuenteFinanciamiento.setIdFuenteFin(FuenteFinanciamiento);
	            fuenteFinanciamiento.setIdFuenteFinanciamiento(FF);
	            registro.setFuenteFinanciamiento(fuenteFinanciamiento);
	            Uejto unidadejecutora = new Uejto();
	            unidadejecutora.setIdUEJ(UnidadEjecutora);
	            registro.setUej(unidadejecutora);
	            Uejlto unidadejecutoralocal = new Uejlto();
	            unidadejecutoralocal.setIdUEJ(UnidadEjecutora);
	            registro.setUejlto(unidadejecutoralocal);
	            CategoriaPresupuestariaTO categoriaPresupuestaria = new CategoriaPresupuestariaTO();
	            categoriaPresupuestaria.setIdCategoria(CategoriaPresupuestaria);
	            registro.setCategoriaPresupuestaria(categoriaPresupuestaria);
	            ObjetoGastoTO objetogasto = new ObjetoGastoTO();
	            objetogasto.setIdObjetoGasto(ObjetoGasto);
	            registro.setObjetoGasto(objetogasto);
	            registro.setMonto(Monto);
	            registro.setPeriodo(Periodo);
	            EjercicioPresupuestarioTO ejercicioPresupuestario = new EjercicioPresupuestarioTO();
	            ejercicioPresupuestario.setAnho(Anho_Fiscal);
	            registro.setEjercicioPresupuestario(ejercicioPresupuestario);
	            registro.setExpediente(ObjetoGasto);
	            registro.setExpediente(String.valueOf(Expediente));
	            registro.setWorkitem(WorkItem);
	            detalle.add(registro);
	            System.out.println("registro detalle No. " + i);
	        
	    }


	    private  static void Registra_Causado() {
	        clResumenNominaRendicion CausarNomina = new clResumenNominaRendicion();
	        ResumenRendicionNominaDTO resumenTO = new ResumenRendicionNominaDTO();
	        ArrayList<RetencionesRendNominaDTO> retencionesTO = new ArrayList<RetencionesRendNominaDTO>();
	        ArrayList<ImputacionesRendNominaDTO> imputacionesTO = new ArrayList<ImputacionesRendNominaDTO>();
	        RetencionesRendNominaDTO registroRetenciones = new RetencionesRendNominaDTO();
	        ImputacionesRendNominaDTO registroImputaciones = new ImputacionesRendNominaDTO();
	        HistoricoCausadoTO historicoTO = new HistoricoCausadoTO();
	        
	        
	        EjercicioPresupuestarioTO ejercicioPresupuestarioTO = new EjercicioPresupuestarioTO();
	        ejercicioPresupuestarioTO.setAnho(2014);
	        ejercicioPresupuestarioTO.setEstado("E");       // String 
	        ejercicioPresupuestarioTO.setEtapa(1);          // Integer
	        OrganismoTO organismoTO = new OrganismoTO();
	        organismoTO.setIdOrganismo("37");
	        Uadto uadto = new Uadto();
	        uadto.setIdUEJ("00011");
	        
	        TipoPagoTO tipoPagoTO = new TipoPagoTO();
	        tipoPagoTO.setIdTipoPago(3);
	        TipoFondoTO tipoFondoTO = new TipoFondoTO();
	        tipoFondoTO.setIdTipoFondo(3);
	        PersonaTO personaTO = new PersonaTO();
	        personaTO. .setIdPersona(13863);
	        EnteReceptorTO enteReceptor = new EnteReceptorTO();
	        
	        CompromisoTO compromisoTO = new CompromisoTO();
	        compromisoTO.setIdCompromiso(1997);
	        compromisoTO.setOrganismo(organismoTO);
	        compromisoTO.setEjercicioPresupuestario(ejercicioPresupuestarioTO);
	        compromisoTO.setUad(uadto);
	        compromisoTO.setTipoPago(tipoPagoTO);
	        compromisoTO.setTipoFondo(tipoFondoTO);
	        compromisoTO.setPersona(personaTO);
	        compromisoTO.setEnteReceptor(null);
	               
	        resumenTO.setCompromiso(compromisoTO);
	        resumenTO.setUnidadAdministradora(uadto);
	        resumenTO.setUnidadPagadora(uadto);
	        resumenTO.setBeneficiario(personaTO);
	        resumenTO.setTipopago(tipoPagoTO);
	        resumenTO.setTipofondo(tipoFondoTO);
	        resumenTO.setFechaPago("01/06/2014");
	        
	        
	        Integer expediente = Crear_Expediente();
	        //Integer expediente = 4166;
	        resumenTO.setExpediente(expediente);
	        resumenTO.setWorkitem(1);
	        resumenTO.setMedioPago(0);
	       
	        
	        resumenTO.setBeneInstFinanciera("003");  // InstitucionFinancieraTO
	        CuentaTO cuentaTO = new CuentaTO();
	        cuentaTO.setNumero("0001-0001-01-0101000001");
	        resumenTO.setBeneNumeroCuenta(cuentaTO);    // CuentaTO
	        resumenTO.setAutorizado(null);          // PersonaTO
	        resumenTO.setAutoInstFinanciera(null);  // InstitucionFinanciaraTO
	        resumenTO.setAutoNumeroCuenta(null);    // CuentaTO
	        TipoPasivoTO tipoPasivo = new TipoPasivoTO();
	        tipoPasivo.setIdTipoPasivo(1);
	        resumenTO.setTipopasivo(tipoPasivo);          // TipoPasivoTO
	        resumenTO.setEstaPagado(true);
	        //resumenTO.setCodigoEnteReceptor("A0216");  // String
	        resumenTO.setCodigoEnteReceptor(null);  // String
	        resumenTO.setEsExcluidoIva(true);
	        resumenTO.setExcepcionRetencionID(0);    // Integer
	        resumenTO.setAlicuota(BigDecimal.valueOf(100)); // BigDecimal
	        Integer TipoDocumento = 47;
	        String NumeroDocumento = "1005";
	        String Observacion = "Rendicion Causado Nomina Webservice";
	        resumenTO.setObservacion(Observacion);
	        
	        System.out.println("Se va a instanciar CausadoTO");
	        CausadoTO causadoTO = new CausadoTO();
	        System.out.println("Ya se instancio CausadoTO");
	        causadoTO.setId(0);  
	        causadoTO.setEjercicioPresupuestario(ejercicioPresupuestarioTO);
	        causadoTO.setOrganismo(organismoTO);
	        causadoTO.setIdExpediente(expediente);
	        
	        System.out.println("Ya se instancio CausadoTO");
	        historicoTO.setCausado(causadoTO);
	        TipoDocumentoTO tipoDocumento = new TipoDocumentoTO();
	        tipoDocumento.setIdTipoDocumento(47);
	        historicoTO.setTipoDocumento(tipoDocumento);
	        historicoTO.setExpediente(expediente);
	        resumenTO.setHistoricoCausado(historicoTO);
	        
	        ControlDivisasTO controldivisas = new ControlDivisasTO();
	        controldivisas.setEjercicioPresupuestario(ejercicioPresupuestarioTO);
	        controldivisas.setOrganismo(organismoTO);
	        controldivisas.setDivisas("N");
	        resumenTO.setControlDivisas(controldivisas);
	        TipoMonedaTO monedaTO = new TipoMonedaTO();
	        monedaTO.setCodigoMoneda("VEB");
	        
	        resumenTO.setTipoMoneda(monedaTO);
	        ConceptoPagoTO conceptoTO = new ConceptoPagoTO();
	        conceptoTO.setIdConceptoPago(1);
	        resumenTO.setConceptoPago(conceptoTO);
	        
	        resumenTO.setStdvId(0);
	        
	// a partir de aqui se definen los registros de detalle
	        registroImputaciones = new ImputacionesRendNominaDTO();
	        registroImputaciones.setEjercicioPresupuestario(ejercicioPresupuestarioTO);
	        registroImputaciones.setOrganismo(organismoTO);
	        Uejto unidadEjecutora = new Uejto();
	        unidadEjecutora.setIdUEJ("00024");
	        registroImputaciones.setUej(unidadEjecutora);
	        Uejlto unidadEjecutoraLocal = new Uejlto();
	        unidadEjecutoraLocal.setIdUEJ("00024");
	        registroImputaciones.setUejlto(unidadEjecutoraLocal);
	        CategoriaPresupuestariaTO categoria = new CategoriaPresupuestariaTO();
	        categoria.setIdCategoria("370001001");
	        registroImputaciones.setCategoriaPresupuestaria(categoria);
	        ObjetoGastoTO objetoGasto = new ObjetoGastoTO();
	        objetoGasto.setIdObjetoGasto("401010100");
	        registroImputaciones.setObjetoGasto(objetoGasto);
	        FuenteFinanciamientoTO fuenteFinanciamiento = new FuenteFinanciamientoTO();
	        fuenteFinanciamiento.setIdFuenteFinanciamiento(1);
	        registroImputaciones.setFuenteFinanciamiento(fuenteFinanciamiento);
	        registroImputaciones.setMonto(BigDecimal.valueOf(10));
	        registroImputaciones.setExpediente(Integer.toString(expediente));
	        registroImputaciones.setWorkitem(1);
	        
	        imputacionesTO.add(registroImputaciones);
	        
	        registroImputaciones = new ImputacionesRendNominaDTO();
	        registroImputaciones.setEjercicioPresupuestario(ejercicioPresupuestarioTO);
	        registroImputaciones.setOrganismo(organismoTO);
	        unidadEjecutora = new Uejto();
	        unidadEjecutora.setIdUEJ("00024");
	        registroImputaciones.setUej(unidadEjecutora);
	        unidadEjecutoraLocal = new Uejlto();
	        unidadEjecutoraLocal.setIdUEJ("00024");
	        registroImputaciones.setUejlto(unidadEjecutoraLocal);
	        categoria = new CategoriaPresupuestariaTO();
	        categoria.setIdCategoria("370001001");
	        registroImputaciones.setCategoriaPresupuestaria(categoria);
	        objetoGasto = new ObjetoGastoTO();
	        objetoGasto.setIdObjetoGasto("401020100");
	        registroImputaciones.setObjetoGasto(objetoGasto);
	        fuenteFinanciamiento = new FuenteFinanciamientoTO();
	        fuenteFinanciamiento.setIdFuenteFinanciamiento(1);
	        registroImputaciones.setFuenteFinanciamiento(fuenteFinanciamiento);
	        registroImputaciones.setMonto(BigDecimal.valueOf(10));
	        registroImputaciones.setExpediente(Integer.toString(expediente));
	        registroImputaciones.setWorkitem(1);
	        imputacionesTO.add(registroImputaciones);
	        
	        registroImputaciones = new ImputacionesRendNominaDTO();
	        registroImputaciones.setEjercicioPresupuestario(ejercicioPresupuestarioTO);
	        registroImputaciones.setOrganismo(organismoTO);
	        unidadEjecutora = new Uejto();
	        unidadEjecutora.setIdUEJ("00024");
	        registroImputaciones.setUej(unidadEjecutora);
	        unidadEjecutoraLocal = new Uejlto();
	        unidadEjecutoraLocal.setIdUEJ("00024");
	        registroImputaciones.setUejlto(unidadEjecutoraLocal);        
	        categoria = new CategoriaPresupuestariaTO();
	        categoria.setIdCategoria("370001001");
	        registroImputaciones.setCategoriaPresupuestaria(categoria);
	        objetoGasto = new ObjetoGastoTO();
	        objetoGasto.setIdObjetoGasto("401030100");
	        registroImputaciones.setObjetoGasto(objetoGasto);
	        fuenteFinanciamiento = new FuenteFinanciamientoTO();
	        fuenteFinanciamiento.setIdFuenteFinanciamiento(1);
	        registroImputaciones.setFuenteFinanciamiento(fuenteFinanciamiento);
	        registroImputaciones.setMonto(BigDecimal.valueOf(10));
	        registroImputaciones.setExpediente(Integer.toString(expediente));
	        registroImputaciones.setWorkitem(1);
	        imputacionesTO.add(registroImputaciones);
	        
	        System.out.println("Tamaño arreglo Imputaciones " + imputacionesTO.size());
	        System.out.println("Unidad ejecutora " + imputacionesTO.get(0).getUejlto().getIdUEJ());
	        System.out.println("Expediente " + imputacionesTO.get(0).getExpediente());
	        
	// A partir de aqui de definen los registros de retenciones
	        registroRetenciones = new RetencionesRendNominaDTO();
	        registroRetenciones.setEjercicioPresupuestario(ejercicioPresupuestarioTO);
	        registroRetenciones.setOrganismo(organismoTO);
	        registroRetenciones.setExpediente(expediente);
	        registroRetenciones.setWorkitem(1);
	        TipoRetencionTO tipoRetencion = new TipoRetencionTO();
	        tipoRetencion.setIdTipoRetencion(2);
	        registroRetenciones.setTipoRetencion(tipoRetencion);
	        RetencionTO retencion = new RetencionTO();
	        retencion.setIdRetencion(5);
	        retencion.setTipoRetencion(tipoRetencion);
	        retencion.setOrganismo(organismoTO);
	        retencion.setTipoRetencion(tipoRetencion);
	        retencion.setWorkitem(1);
	        registroRetenciones.setRetencion(retencion);
	        registroRetenciones.setBeneficiario(personaTO);
	        ReglaRetencionTO reglaRetencion = new ReglaRetencionTO();
	        reglaRetencion.setIdReglaRetencion(0);
	        registroRetenciones.setReglaRetencion(reglaRetencion);
	        retencionesTO.add(registroRetenciones);
	        
	        registroRetenciones = new RetencionesRendNominaDTO();
	        registroRetenciones.setEjercicioPresupuestario(ejercicioPresupuestarioTO);
	        registroRetenciones.setOrganismo(organismoTO);
	        registroRetenciones.setExpediente(expediente);
	        registroRetenciones.setWorkitem(1);
	        tipoRetencion = new TipoRetencionTO();
	        tipoRetencion.setIdTipoRetencion(4);
	        registroRetenciones.setTipoRetencion(tipoRetencion);
	        retencion = new RetencionTO();
	        retencion.setIdRetencion(10);
	        retencion.setOrganismo(organismoTO);
	        retencion.setTipoRetencion(tipoRetencion);
	        retencion.setWorkitem(1);
	        registroRetenciones.setRetencion(retencion);
	        registroRetenciones.setBeneficiario(personaTO);
	        registroRetenciones.setMonto(BigDecimal.valueOf(1));
	        reglaRetencion = new ReglaRetencionTO();
	        reglaRetencion.setIdReglaRetencion(0);
	        registroRetenciones.setReglaRetencion(reglaRetencion);
	        retencionesTO.add(registroRetenciones);
	        
	        registroRetenciones = new RetencionesRendNominaDTO();
	        registroRetenciones.setEjercicioPresupuestario(ejercicioPresupuestarioTO);
	        registroRetenciones.setOrganismo(organismoTO);
	        registroRetenciones.setExpediente(expediente);
	        registroRetenciones.setWorkitem(1);
	        tipoRetencion = new TipoRetencionTO();
	        tipoRetencion.setIdTipoRetencion(7);
	        registroRetenciones.setTipoRetencion(tipoRetencion);
	        retencion = new RetencionTO();
	        retencion.setIdRetencion(13);
	        retencion.setOrganismo(organismoTO);
	        retencion.setTipoRetencion(tipoRetencion);
	        retencion.setWorkitem(1);
	        
	        registroRetenciones.setRetencion(retencion);
	        registroRetenciones.setBeneficiario(personaTO);
	        registroRetenciones.setMonto(BigDecimal.valueOf(1));
	        reglaRetencion = new ReglaRetencionTO();
	        reglaRetencion.setIdReglaRetencion(0);
	        registroRetenciones.setReglaRetencion(reglaRetencion);
	        retencionesTO.add(registroRetenciones);
	        
	        CausarNomina.setResumenRendicionDTO(resumenTO);
	        CausarNomina.setImputacionesRendicionDTO(imputacionesTO);
	        CausarNomina.setRetencionesRendicionDTO(retencionesTO);
	              
	        
	        System.out.println("Se va a llamar al Cliente clResumenNominaRendicion");
	        resumenTO = CausarNomina.CausarNomina();
	        
	        System.out.println("Numero Expediente: " + resumenTO.getExpediente());
	        System.out.println("Numero Causado   : " + resumenTO.getCompromiso().getCausado().getId());
	        System.out.println("Observacion      : " + resumenTO.getObservacion());
	        System.out.println("-------------------------------");

	    }  


	
	
}

