package sigefirrhh.persistencia.dao.imple;

import java.sql.Connection;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import sigefirrhh.persistencia.dao.OpcionDAO;
import sigefirrhh.persistencia.dao.RolOpcionDAO;

public class OpcionDAOImple extends GenericDAOImplHibernate implements OpcionDAO {
	
	InitialContext cxt = null;
	DataSource ds = null;
	Connection conn = null;
	Statement stmt = null;
	
	public OpcionDAOImple(){
		super();
		try{
    		this.cxt = new InitialContext();
    		this.ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/DBPoolSigefirrhh" );
    		
    	}catch (Exception e){
    		e.printStackTrace();
    	}
	}

	

}
