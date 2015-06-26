package sigefirrhh.persistencia.dao.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.postgresql.util.PSQLException;

import com.ibatis.common.jdbc.exception.NestedSQLException;

import sigefirrhh.persistencia.dao.CompromisoInicialDAO;
import sigefirrhh.persistencia.modelo.CompromisoInicial;
import sigefirrhh.persistencia.modelo.CompromisoInicialDetalle;
import sigefirrhh.persistencia.modelo.CriterioBusqueda;
import sigefirrhh.persistencia.modelo.Opcion;

public class CompromisoInicialDAOImple extends GenericDAOImplHibernate implements CompromisoInicialDAO {
	
	InitialContext cxt = null;
	DataSource ds = null;
	Connection conn = null;
	Statement stmt = null;
	
	public CompromisoInicialDAOImple(){
		super();
		try{
    		this.cxt = new InitialContext();
    		this.ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/DBPoolSigefirrhh" );
    		
    	}catch (Exception e){
    		e.printStackTrace();
    	}
	}

	
	@Override
	public int actualizarCompromisoInicial(CompromisoInicial record) throws PSQLException, SQLException, NestedSQLException {
		Integer lista = (Integer) getSqlMapClientTemplate().queryForObject("actualizarCompromisoInicial", record);
		if (lista==null) lista = 0;		
        return lista;
	}
	
	
	public int actualizarCompromisoInicial_porsi(CompromisoInicial record)	throws PSQLException, Exception, SQLException, NestedSQLException {
		
		int resultado = 0;
    	
    	if ( ds == null ) {
    	   throw new Exception("Data source no encontrado!");
    	}else{
    		conn = ds.getConnection();
    		PreparedStatement ps = conn.prepareStatement("UPDATE compromisoinicial SET estatus=? where expediente= ? and id_organismo= ? and ano= ? ",PreparedStatement.RETURN_GENERATED_KEYS );
    		ps.setLong(1, record.getEstatus());
    		ps.setLong(2, record.getExpediente());
    	    ps.setLong(3, record.getIdOrganismo());
    	    ps.setLong(4, record.getAno());

    		ps.execute();
    		//ps.executeUpdate();

    		ResultSet rs = ps.getGeneratedKeys();
    		rs.next();
			resultado = rs.getInt(1);

    		conn.close();
    		
    	}    	

  	   	return resultado;  	
	}
	

}
