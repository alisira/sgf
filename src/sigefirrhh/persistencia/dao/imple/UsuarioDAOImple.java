package sigefirrhh.persistencia.dao.imple;

import java.sql.Connection;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import sigefirrhh.persistencia.dao.UsuarioDAO;

public class UsuarioDAOImple extends GenericDAOImplHibernate implements UsuarioDAO {
	
	InitialContext cxt = null;
	DataSource ds = null;
	Connection conn = null;
	Statement stmt = null;
	
	public UsuarioDAOImple(){
		super();
		try{
    		this.cxt = new InitialContext();
    		this.ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/DBPoolSigefirrhh" );
    		
    	}catch (Exception e){
    		e.printStackTrace();
    	}
	}

	

}
