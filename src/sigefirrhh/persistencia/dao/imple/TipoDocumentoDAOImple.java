package sigefirrhh.persistencia.dao.imple;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import sigefirrhh.persistencia.dao.TipoDocumentoDAO;
import sigefirrhh.persistencia.modelo.CriterioBusqueda;
import sigefirrhh.persistencia.modelo.TipoDocumento;


public class TipoDocumentoDAOImple extends GenericDAOImplHibernate implements TipoDocumentoDAO {
	
	InitialContext cxt = null;
	DataSource ds = null;
	Connection conn = null;
	Statement stmt = null;
	
	public TipoDocumentoDAOImple(){
		super();
		/*try{
    		this.cxt = new InitialContext();
    		this.ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/DBPoolSigefirrhh" );
    		
    	}catch (Exception e){
    		e.printStackTrace();
    	}*/
	}

	public Object buscarTipoDocuTempo() {
		
		List<TipoDocumento> lista = new ArrayList<TipoDocumento>();
		lista.add(new TipoDocumento(0, "Resumen de Nomina"));
		lista.add(new TipoDocumento(1, "Memorandum"));
		lista.add(new TipoDocumento(2, "Oficio"));		
		
        return lista ;
	} 
	
	

}
