package sigefirrhh.ibatis.conexion.db;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.ibatis.common.resources.Resources;
import java.io.Reader;
import java.io.IOException;
import org.apache.log4j.Logger;


public class MapaSQL {

	static Logger log = Logger.getLogger(MapaSQL.class.getName());	
 
	private static SqlMapClient sqlMapper;

	public static SqlMapClient mapeador () {

	  try {  
		 
		  Reader reader = Resources.getResourceAsReader("sigefirrhh/ibatis/conexion/db/SqlMapConfig.xml");	      	
		  sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);	        
		  reader.close(); 

	    } catch (IOException e) {	      	    	
	      throw new RuntimeException("Something bad happened while building the SqlMapClient instance." + e, e);
	    }

	  return sqlMapper;
  }

}