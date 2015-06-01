package sigefirrhh.persistencia.dao.imple;

import java.sql.Connection;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import sigefirrhh.persistencia.dao.RolOpcionDAO;

public class RolOpcionDAOImple extends GenericDAOImplHibernate implements RolOpcionDAO {
	
	InitialContext cxt = null;
	DataSource ds = null;
	Connection conn = null;
	Statement stmt = null;
	
	public RolOpcionDAOImple(){
		super();
		/*try{
    		this.cxt = new InitialContext();
    		this.ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/DBPoolSigefirrhh" );
    		
    	}catch (Exception e){
    		e.printStackTrace();
    	}*/
	}

	

}
