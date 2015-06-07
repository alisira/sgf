package sigefirrhh.persistencia.dao;

import java.sql.SQLException;
import java.util.List;
import org.postgresql.util.PSQLException;
import com.ibatis.common.jdbc.exception.NestedSQLException;
import sigefirrhh.persistencia.modelo.CriterioBusqueda;
import sigefirrhh.persistencia.modelo.Opcion;

public interface OpcionDAO extends GenericDAO {		
	
	public List<Opcion> buscarOpcionExpediente (CriterioBusqueda criterio) throws PSQLException, Exception, SQLException,NestedSQLException;
}
