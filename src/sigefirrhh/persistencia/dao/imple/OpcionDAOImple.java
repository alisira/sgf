package sigefirrhh.persistencia.dao.imple;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.postgresql.util.PSQLException;

import com.ibatis.common.jdbc.exception.NestedSQLException;

import sigefirrhh.persistencia.dao.OpcionDAO;
import sigefirrhh.persistencia.modelo.CriterioBusqueda;
import sigefirrhh.persistencia.modelo.Opcion;

public class OpcionDAOImple extends GenericDAOImplHibernate implements OpcionDAO {
	
	InitialContext cxt = null;
	DataSource ds = null;
	Connection conn = null;
	Statement stmt = null;
	
	public OpcionDAOImple(){
		super();
		/*try{
    		this.cxt = new InitialContext();
    		this.ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/DBPoolSigefirrhh" );
    		
    	}catch (Exception e){
    		e.printStackTrace();
    	}*/
	}

	@Override
	public List<Opcion> buscarOpcionExpediente(CriterioBusqueda criterioBusqueda) throws PSQLException, Exception, SQLException, NestedSQLException {
		List<Opcion> lista = getSqlMapClient().queryForList("buscarOpcionExpediente", criterioBusqueda);
        return lista;
	}

	@Override
	public List<Opcion> buscarOpcionUsadoenExpediente(
			CriterioBusqueda criterioBusqueda) throws PSQLException, Exception,
			SQLException, NestedSQLException {
		List<Opcion> lista = getSqlMapClient().queryForList("buscarOpcionUsadoenExpediente", criterioBusqueda);
        return lista;
	}	

}
