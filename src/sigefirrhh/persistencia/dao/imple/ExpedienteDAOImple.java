package sigefirrhh.persistencia.dao.imple;

import java.sql.SQLException;
import java.util.List;

import org.postgresql.util.PSQLException;

import com.ibatis.common.jdbc.exception.NestedSQLException;

import sigefirrhh.persistencia.dao.ExpedienteDAO;
import sigefirrhh.persistencia.modelo.CriterioBusqueda;
import sigefirrhh.persistencia.modelo.Opcion;

public class ExpedienteDAOImple extends GenericDAOImplHibernate implements ExpedienteDAO {
	
	public ExpedienteDAOImple(){
		super();
	}

	@Override
	public List<Opcion> buscarExpedienteOpcion(CriterioBusqueda criterioBusqueda) throws PSQLException, Exception, SQLException, NestedSQLException {
		List<Opcion> lista = getSqlMapClient().queryForList("buscarOpcionExpediente", criterioBusqueda);
        return lista;
	}	

}
