package sigefirrhh.struts.action;

//import

import org.apache.struts.action.*;
import org.apache.struts.util.MessageResources;
import org.postgresql.util.PSQLException;

/*
import sigecof.ConsultaDisponibilidadPresupuestaria;
import sigecof.DisponibilidadPresupuestariaDTO;
import sigecof.clDisponibilidadPresupuestaria;
*/

import sigefirrhh.base.estructura.Organismo;


//import sigefirrhh.ibatis.modelo.GastoProyectado;
import sigefirrhh.login.LoginSession;
import sigefirrhh.persistencia.dao.GastoProyectadoDAO;
import sigefirrhh.persistencia.dao.imple.GastoProyectadoDAOImple;
import sigefirrhh.persistencia.modelo.GastoProyectado;
import sigefirrhh.persistencia.modelo.CriterioBusqueda;
import sigefirrhh.sistema.ExcepcionSigefirrhh;
import sigefirrhh.sistema.ValidadorSesion;
import sigefirrhh.struts.actionForm.GastoProyectadoForm;
import sigefirrhh.struts.addons.Comun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceException;

import com.ibatis.common.jdbc.exception.NestedSQLException;

import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;


public class GastoProyectadoAction extends Action  implements Serializable, Comun {

	private static final long serialVersionUID = -4298746168227316826L;
	
	HttpSession session = null;
	String fwd = null;			
	PrintWriter out = null;
	MessageResources messageResources = null;	
		

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {				

		HttpSession session = request.getSession();
		messageResources = getResources(request);
		String errorTem = null;//Variable que guarda temporalmente el mensaje de excepcion en el caso
	
		try {
			
			out = response.getWriter();
	        response.setContentType("text/xml");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setStatus(HttpServletResponse.SC_OK);
	        out.write("<root>");
			
			CriterioBusqueda criterio = new CriterioBusqueda();
			//System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
						
    		if (validarAcceso(request, "nuevo")){
			
		        GastoProyectadoForm forma = (GastoProyectadoForm) form;					
				
				if (forma.getCedula() !=null && !forma.getCedula().equals("")) {
					if (forma.getCedula().indexOf(' ') != -1){							
						for (int i = 0; i < forma.getCedula().trim().split("\\ ").length; i++) {
							if (forma.getCedula().trim().split("\\ ")[i].trim().length() > 0){									
								criterio.addCedula(Integer.valueOf(forma.getCedula().trim().split("\\ ")[i].trim())); 
							}
						}						
					}else{							
						criterio.addCedula(Integer.parseInt(forma.getCedula().trim()));
					}											
				}					
				
				if (forma.getCodFrecuenciaPago() !=null && !forma.getCodFrecuenciaPago().equals("")) {
					if (forma.getCodFrecuenciaPago().indexOf(' ') != -1){							
						for (int i = 0; i < forma.getCodFrecuenciaPago().trim().split("\\ ").length; i++) {
							if (forma.getCodFrecuenciaPago().trim().split("\\ ")[i].trim().length() > 0){									
								criterio.addCodFrecuenPago(Integer.valueOf(forma.getCodFrecuenciaPago().trim().split("\\ ")[i].trim()));
							}
						}						
					}else{							
						criterio.addCodFrecuenPago(Integer.valueOf(forma.getCodFrecuenciaPago().trim()));
					}												
				}
	
				Organismo org = new Organismo();
				org = ((LoginSession) session.getAttribute("loginSession")).getOrganismo();					
				criterio.addIdOrganismo((int) org.getIdOrganismo());					
				
				criterio.addAno(ano);
				//semanasCalcular = ((12 -mes) * 30)/7;	
				criterio.addMesesCalcu(12 -mes);
				criterio.addQuinceCalcu((12 -mes) * 2);
				criterio.addSemaCalcu(52 - semana);										
				
				GastoProyectadoDAO gastoProyectadoDAO = new GastoProyectadoDAOImple();					
				List<GastoProyectado> listadoGasto = (List<GastoProyectado>) gastoProyectadoDAO.proyectarGasto(criterio);
				
				if (listadoGasto.size() > 0){
					for (int i = 0;i < listadoGasto.size(); i++){							
						out.write("<cod_cate_presu>" + listadoGasto.get(i).getCodCatePresu() + "</cod_cate_presu>");
						out.write("<cod_unidad_ejecutora>" + listadoGasto.get(i).getCodUnidadEjecutora() + "</cod_unidad_ejecutora>");
						out.write("<deno_unidad_ejecutora>" + listadoGasto.get(i).getDenoUnidadEjecutora() + "</deno_unidad_ejecutora>");
						out.write("<cod_partida>" + listadoGasto.get(i).getCodPartida() + "</cod_partida>");
						out.write("<deno_partida>" + listadoGasto.get(i).getDenoPartida() + "</deno_partida>");
						out.write("<id_organismo>" + listadoGasto.get(i).getIdOrganismo() + "</id_organismo>");
						out.write("<monto>" + listadoGasto.get(i).getMonto() + "</monto>");
						out.write("<ff>" + 1 + "</ff>");
						out.write("<dispo_presu>" + 1000 + "</dispo_presu>");
						
						//dispoPresu.setCategoria_Presupuestaria(Categoria_Presupuestaria);
						//System.out.println(i.getCodCatePresu() );
						
						/*							
						clDisponibilidadPresupuestaria dispoPresu = new clDisponibilidadPresupuestaria();
						dispoPresu.setAnho_fiscal(ano);
						dispoPresu.setCategoria_Presupuestaria(String.valueOf(i.getCodCatePresu()));
						//dispoPresu.setCod_unidad_administ(String.valueOf(60006));
						//dispoPresu.setCod_Unidad_Ejecutora(String.valueOf(i.getCodUnidadEjecutora()));
						dispoPresu.setCod_unidad_administ("60008");
						dispoPresu.setCod_Unidad_Ejecutora("60008");
						dispoPresu.setFuente_Financiamiento(String.valueOf(1));
						dispoPresu.setObjeto_Gasto(i.getCodPartida().replace(".", ""));
						dispoPresu.setId_organismo("60");
						DisponibilidadPresupuestariaDTO dpDTO = new DisponibilidadPresupuestariaDTO();
						dpDTO = dispoPresu.EjecuteConsulta();
						//System.out.println(dpDTO.getObjetoGasto() + " " +  dpDTO.getDisponibilidadImputacion());
						*/
						
						//b++;
					}
				}else{						
					throw new ExcepcionSigefirrhh("sinResultados");
				}	        
	        }
		
		} catch (NestedSQLException e) {
			System.out.println("Error Grave: " + this.getClass().getName() + " a las " + hora);
			e.printStackTrace();
			errorTem = messageResources.getMessage("errors.comunicacion");
			
		} catch (PSQLException e) {				
			System.out.println("Error Grave: " + this.getClass().getName() + " a las " + hora);
			e.printStackTrace();			
			errorTem = messageResources.getMessage("errors.aplicacion");
			
		} catch (SQLException e) {
			System.out.println("Error Grave: " + this.getClass().getName() + " a las " + hora);
			e.printStackTrace();			
			errorTem = messageResources.getMessage("errors.aplicacion");
			
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

