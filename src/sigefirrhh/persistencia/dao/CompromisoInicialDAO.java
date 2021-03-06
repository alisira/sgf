package sigefirrhh.persistencia.dao;

import java.sql.SQLException;

import org.postgresql.util.PSQLException;

import sigefirrhh.persistencia.modelo.CompromisoInicial;

import com.ibatis.common.jdbc.exception.NestedSQLException;

public interface CompromisoInicialDAO extends GenericDAO {
	
	 public int actualizarCompromisoInicial(CompromisoInicial record) throws PSQLException, SQLException, NestedSQLException;	
	 
}
