package sigefirrhh.struts.pdd;

import java.sql.SQLException;
import java.util.Map;

import org.postgresql.util.PSQLException;

import com.ibatis.common.jdbc.exception.NestedSQLException;

public interface Pdd {

	public String urlReporte(int expediente, int ano, int org);
	public int actualizaEstatus(int expediente, int ano, int org, int estatus) throws PSQLException, NestedSQLException, SQLException, Exception;
	public Map<Integer, String> opciones();
	
}
