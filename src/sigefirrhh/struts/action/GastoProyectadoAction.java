package sigefirrhh.struts.action;

//import

import org.apache.struts.action.*;
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
	Object[] error= null;		
	PrintWriter out = null;

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {				

		HttpSession session = request.getSession();
		Object[] error= new Object[2];
	
		try {
			
			out = response.getWriter();
	        response.setContentType("text/xml");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setStatus(HttpServletResponse.SC_OK);
	        out.write("<root>");
			
			CriterioBusqueda criterio = new CriterioBusqueda();
			//System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			
			String resp = validarAcceso(request);
    		if (resp == "valido"){
			
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
					error[0] = (String) "sinresultados";
				}
	        	
	        }else{
	        	error[0] = resp;
	        }
		
		} catch (NestedSQLException e) {				
			error[0] = (String) "Error de comunicacion con el servidor, comuniquese con el administrador, disculpe las molestias";
			error[1]= e;
		} catch (PSQLException e) {				
			error[0] = (String) "PSQLException Error de comunicacion con el servidor, comuniquese con el administrador, disculpe las molestias";
			error[1]= e;
		} catch (SQLException e) {
			error[0] = (String) "SQLException Error de comunicacion con el servidor, comuniquese con el administrador, disculpe las molestias";
			error[1]= e;			
		} catch (Exception e) {
			error[0] = (String) "Error de aplicacion, comuniquese con el administrador, disculpe las molestias";
			error[1]= e;
				
		} finally{
			
			
			if (((String) error[0]) != null){
				if (error[1] != null){
					System.out.println("Error Grave: " + this.getClass().getName() + " a las " + hora);
					((Throwable) error[1]).printStackTrace();
					out.write("<error>");
		        	out.write(error[0].toString());
					out.write("</error>");
					fwd = "error";
				}else{
					
					out.write("<error>");
					if (error[0].equals("sesioncerrada")){			        	
			        	out.write("Por razones de seguridad su sesion ha sido cerrada, favor iniciar sesion desde pagina inicial, gracias" );
						fwd = "sesionCerrada";
			        }else if(error[0].equals("datosincompletos")){
			        	out.write("Datos Incompletos, favor corregir, gracias" );
						fwd = "datosIncompletos";
			        }else if(error[0].equals("sinresultados")){			        	
			        	out.write("Busqueda no Obtuvo resultados, vuelva a intentar");						
						fwd = "sinresultados";
			        }
					out.write("</error>");

					System.out.println("Incidencia: " + this.getClass().getName() + " a las " + hora);
					System.out.println(error[0]);
				}
				
			}
			
			out.write("</root>");
			out.flush();
		}
		
		return null;
	}
	
	@Override
	public String validarAcceso(HttpServletRequest request) {
		String resp= null;
		
		HttpSession session = request.getSession();
        if (session.getAttribute("loginSession") != null){
        	if (((LoginSession) session.getAttribute("loginSession")).isValid()){
        		ValidadorSesion vs = new ValidadorSesion();
        		if (vs.validarPermiso(request)){
        			resp ="valido";
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

