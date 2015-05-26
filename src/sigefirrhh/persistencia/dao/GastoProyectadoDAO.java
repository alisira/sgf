package sigefirrhh.persistencia.dao;

import java.sql.SQLException;
import java.util.List;

import org.postgresql.util.PSQLException;

import sigefirrhh.persistencia.modelo.GastoProyectado;
import sigefirrhh.persistencia.modelo.CriterioBusqueda;

import com.ibatis.common.jdbc.exception.NestedSQLException;

public interface GastoProyectadoDAO extends GenericDAO {		
	
	public List<GastoProyectado> proyectarGasto(CriterioBusqueda parametros) throws PSQLException, Exception, SQLException,NestedSQLException;
	   	   
}
