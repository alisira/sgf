package sigefirrhh.persistencia.dao;

import java.sql.SQLException;
import java.util.List;

import org.postgresql.util.PSQLException;

import sigefirrhh.persistencia.modelo.CompromisoInicial;
import sigefirrhh.persistencia.modelo.CompromisoInicialDetalle;
import sigefirrhh.persistencia.modelo.CriterioBusqueda;

import com.ibatis.common.jdbc.exception.NestedSQLException;

public interface CompromisoInicialDetalleDAO extends GenericDAO {
	public List<CompromisoInicialDetalle> buscarExt(CriterioBusqueda criterioBusqueda) throws PSQLException, Exception, SQLException,NestedSQLException;
	
}
