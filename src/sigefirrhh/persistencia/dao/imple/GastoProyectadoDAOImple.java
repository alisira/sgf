package sigefirrhh.persistencia.dao.imple;

import java.sql.SQLException;
import java.util.List;

import org.postgresql.util.PSQLException;

import sigefirrhh.persistencia.modelo.GastoProyectado;
import sigefirrhh.persistencia.modelo.CriterioBusqueda;
import sigefirrhh.persistencia.dao.GastoProyectadoDAO;

import com.ibatis.common.jdbc.exception.NestedSQLException;

public class GastoProyectadoDAOImple extends GenericDAOImplHibernate implements GastoProyectadoDAO  {

    
    public GastoProyectadoDAOImple() {
        super();
    }
    
    
    public List<GastoProyectado> proyectarGasto(CriterioBusqueda parametros) throws PSQLException, Exception, SQLException,NestedSQLException {
    	    	
    	if (parametros.getAno() == null ){
    		throw new Exception("Favor llenar el ano");    	
    	}    	
    	if (parametros.getCodFrecuenPago() == null ){
   		 	throw new Exception("Favor llenar la frecuencia de pago");    	
    	}    	
    	if (parametros.getMesesCalcu() == null ){
    		throw new Exception("Favor llenar los meses a calcular");      		 
    	}
    	if (parametros.getSemaCalcu() == null ){
    		throw new Exception("Favor llenar las semanas a calcular");       
       	}
    	
    	if (parametros.getQuinceCalcu() == null ){
    		throw new Exception("Favor llenar las quincenas a calcular");       	
       	}
    	
    	List<GastoProyectado> record =null;
    	
		record = (List<GastoProyectado>) getSqlMapClient().queryForList("gasto_proyectado", parametros);		
    	
        return record;
    }
    

}