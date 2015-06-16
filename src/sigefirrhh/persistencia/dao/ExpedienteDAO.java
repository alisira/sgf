package sigefirrhh.persistencia.dao;

import java.sql.SQLException;
import java.util.List;
import org.postgresql.util.PSQLException;
import sigefirrhh.persistencia.modelo.CriterioBusqueda;
import sigefirrhh.persistencia.modelo.Expediente;
import sigefirrhh.persistencia.modelo.Opcion;
import com.ibatis.common.jdbc.exception.NestedSQLException;

public interface ExpedienteDAO extends GenericDAO {		

	public List<Opcion> buscarExpedienteOpcion (CriterioBusqueda criterio) throws PSQLException, Exception, SQLException,NestedSQLException;
	public int actualizarExpediente(Expediente record) throws PSQLException, Exception, SQLException,NestedSQLException;	

}
