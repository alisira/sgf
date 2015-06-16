package sigefirrhh.struts.pdd;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.postgresql.util.PSQLException;

import com.ibatis.common.jdbc.exception.NestedSQLException;

import sigefirrhh.persistencia.dao.CompromisoInicialDAO;
import sigefirrhh.persistencia.dao.ExpedienteDAO;
import sigefirrhh.persistencia.dao.imple.CompromisoInicialDAOImple;
import sigefirrhh.persistencia.dao.imple.ExpedienteDAOImple;
import sigefirrhh.persistencia.modelo.CompromisoInicial;
import sigefirrhh.persistencia.modelo.Expediente;

public class PddCompromisoImp implements Pdd {

	public String urlReporte(int expediente, int ano, int org) {
		
		return "web/compromiso_inicial/reference.pdf";
	}
	
	@Override
	public int actualizaEstatus(int expediente, int ano, int org, int estatus) throws PSQLException, NestedSQLException, SQLException, Exception {	
		
		CompromisoInicial compromisoInicial = new CompromisoInicial();
		compromisoInicial.setExpediente(expediente);
		compromisoInicial.setAno(ano);
		compromisoInicial.setIdOrganismo(org);
		compromisoInicial.setEstatus(estatus);

		int resultado;
		CompromisoInicialDAO compromisoInicialDAO = new CompromisoInicialDAOImple();	    						
		resultado = compromisoInicialDAO.actualizarCompromisoInicial(compromisoInicial);
		
		if (resultado != 0){
			Expediente expedienteTO = new Expediente();
			expedienteTO.setExpediente(expediente);
			expedienteTO.setAno(ano);
			expedienteTO.setIdOrganismo(org);
			expedienteTO.setEstatus(estatus);			
			ExpedienteDAO expedienteDAO = new ExpedienteDAOImple();	    						
			resultado = expedienteDAO.actualizarExpediente(expedienteTO);
		}
		return resultado;
	}

	@Override
	public Map<Integer, String> opciones() {
		Map<Integer, String> opcionesPDD = new HashMap<Integer, String>();
		
		//Lo ideal es buscar estas opciones en el modelo de datos
		opcionesPDD.put(1, "Aprobar");
		opcionesPDD.put(2, "Anular");
		
		return opcionesPDD;
	}
	
	
}
