package sigefirrhh.ibatis.dao;

import java.sql.SQLException;
import java.util.List;

import org.postgresql.util.PSQLException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import sigefirrhh.ibatis.modelo.CompromisoInicialDetalle;

import com.ibatis.common.jdbc.exception.NestedSQLException;

public class CompromisoInicialDetalleDAOImpl extends SqlMapClientDaoSupport {

    
    public CompromisoInicialDetalleDAOImpl() {
        super();
    }
    
    public List<CompromisoInicialDetalle> buscarResumenNomina(CompromisoInicialDetalle record) {
        List<CompromisoInicialDetalle> list = getSqlMapClientTemplate().queryForList("buscarCompromisoInicialDetalle", record);
        return list;
    }
    
    public Integer insertarCompromisoInicialDetalle(CompromisoInicialDetalle record) throws PSQLException, Exception, SQLException,NestedSQLException {
     	
    	int valor =0;       	
       	Integer tempInt  = (Integer) getSqlMapClient().queryForObject("insertar_compromiso_inicial_detalle", record);       	
       	valor =  Integer.valueOf(tempInt);
       	return valor;
    		
     }
    
}