package sigefirrhh.persistencia.dao.imple;

import java.sql.SQLException;
import java.util.List;

import org.postgresql.util.PSQLException;

import com.ibatis.common.jdbc.exception.NestedSQLException;

import sigefirrhh.persistencia.dao.CompromisoInicialDetalleDAO;
import sigefirrhh.persistencia.modelo.CompromisoInicialDetalle;
import sigefirrhh.persistencia.modelo.CriterioBusqueda;

public class CompromisoInicialDetalleDAOImple extends GenericDAOImplHibernate implements CompromisoInicialDetalleDAO {
    
    public CompromisoInicialDetalleDAOImple() {
        super();
    }
    
	@Override
	public List<CompromisoInicialDetalle> buscarExt(
			CriterioBusqueda criterioBusqueda) throws PSQLException,
			Exception, SQLException, NestedSQLException {
		List<CompromisoInicialDetalle> lista = getSqlMapClient().queryForList("buscarCompromisoInicialDetalleExt", criterioBusqueda);
        return lista;
		
	}
}