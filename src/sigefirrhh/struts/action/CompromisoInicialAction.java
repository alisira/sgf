package sigefirrhh.struts.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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






















import sigecof.ExpedienteTO;
import sigecof.SesionTO;
import sigecof.UsuarioWFTO;
import sigecof.WSGestionExpediente;
import sigecof.WorkItemTO;
import sigecof.clGestionExpediente;
import sigecof.clRegistroCompromisoInicial;
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
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
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
			
			URL url3 = new URL("http://www.lapatilla.com/site/");			
			
			URL url;
			URLConnection uc;
			//String urlString="http://10.79.6.231/InterfazNegociadoraWEB/inicio.jsp";
			String urlString="http://www.lapatilla.com/site/";
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
 		        	
 		        	
 		        	/*
 		        	
 		        	//Creacion de expediente con el cliente del compromiso
 		        	clRegistroCompromisoInicial clCompromisoInicial = new clRegistroCompromisoInicial();
 		        	
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
				
 		        	clCompromisoInicial.setExpedienteTO(expedienteTO);
 		        	clCompromisoInicial.setUsuarioTO(usuarioTO);
 		        	clCompromisoInicial.setSesionTO(sesionTO);
 		        	
 		        	System.out.println("Se va a llamar a la clase cliente de CrearExpediente ");
 		        	workitemTO = clCompromisoInicial.CrearExpediente();
				   
 		        	System.out.println("Numero Expediente: " + workitemTO.getExpediente().getIdExpediente());
 		        	System.out.println("Numero WorkItem: " + workitemTO.getIdWorkItem());
 		        	System.out.println("Descripción    : " + workitemTO.getDescripcion());
 		        	System.out.println("-------------------------------");

 		        	
 		        	
 		        	
 		        	clGestionExpediente CrearExpediente = new clGestionExpediente();
 		          
 		           
 		           expedienteTO.setAnho(2014);
 		           expedienteTO.setIdOrganismo("37");
 		           expedienteTO.setIdProceso("P_FOND_RES_NEM");
 		           expedienteTO.setDenominacionProceso("RESUMEN NOMINA");
 		           expedienteTO.setIdUsuario("FMIGDALIA");
 		           expedienteTO.setDescripcion("Empleados");
 		           expedienteTO.setObservacion("2da. Quincena de Abril 2014");
 		           expedienteTO.setNombre("Registro de Resumen de Nomina");
 		           
 		           usuarioTO.setIdUsuario("FMIGDALIA");
 		           usuarioTO.setIpUsuario("10.90.24.79");
 		           usuarioTO.setRol("R_ANA_REGIST");
 		           
 		           sesionTO.setIdUsuario("FMIGDALIA");
 		           sesionTO.setIpMaquina("10.90.24.79");
 		           sesionTO.setMaquina("DESARROLLO WEBSERVICE DIGEMAFE");
 		           sesionTO.setPrograma("CREAEXPEDIENTE");
 		           //sesionTO.setIdSesion(null);

 		           CrearExpediente.setExpedienteTO(expedienteTO);
 		           CrearExpediente.setUsuarioTO(usuarioTO);
 		           CrearExpediente.setSesionTO(sesionTO);
 		           //CrearExpediente.setIP(messageResources.getMessage("ip.sigecof"));
 		           System.out.println("Se va a llamar a la clase cliente de CrearExpediente ");

 		           

System.setProperty("http.proxyHost", "proxyr.mf.gob.ve");
System.setProperty("http.proxyPort", "3128");

// Next connection will be through proxy.
URL url = new URL("http://sonnyt.com/uglyemail/");
InputStream in = url.openStream();

//System.out.println(in.read());




SocketAddress addr = new InetSocketAddress("proxyr.mf.gob.ve", 3218);
Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);


//URL url = new URL("http://java.example.org/");
URLConnection conn = url.openConnection(proxy);

conn.connect();

System.out.println("prueba: " + conn.getContentLength());
// Now, let's 'unset' the proxy.
//System.setProperty("http.proxyHost", null);
 		           
 		           
 		         
 		           
 		           workitemTO = CrearExpediente.CrearExpediente();
 		           
 		           System.out.println("Numero Expediente: " + workitemTO.getExpediente().getIdExpediente());
 		           System.out.println("Numero WorkItem: " + workitemTO.getIdWorkItem());
 		           System.out.println("Descripción    : " + workitemTO.getDescripcion());
 		           System.out.println("-------------------------------");
 		        	
 		        	
 	*/
 		           
 		           
 		        	
 		        	
 		        	
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
	
}

