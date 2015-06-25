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
    
    public List<GastoProyectado> proyectarGasto(CriterioBusqueda criterioBusqueda) throws PSQLException, SQLException,NestedSQLException {
    		
		if (criterioBusqueda.getAno().size() < 1 ) {
    		throw new RuntimeException("Favor llenar el ano");    	
    	}
		if (criterioBusqueda.getCodFrecuenPago().size() < 1 ) {    	
   		 	throw new RuntimeException("Favor llenar la frecuencia de pago");    	
    	}    	
    	if (criterioBusqueda.getMesesCalcu().size() < 1 ) {    	
    		throw new RuntimeException("Favor llenar los meses a calcular");      		 
    	}
    	if (criterioBusqueda.getSemaCalcu().size() < 1 ) {    	
    		throw new RuntimeException("Favor llenar las semanas a calcular");       
       	}    	
    	if (criterioBusqueda.getQuinceCalcu().size() < 1 ) {    	
    		throw new RuntimeException("Favor llenar las quincenas a calcular");       	
       	}
    	
    	List<GastoProyectado> record =null;
    	
		record = (List<GastoProyectado>) getSqlMapClient().queryForList("gasto_proyectado", criterioBusqueda);		
    	
        return record;
    }
    

}