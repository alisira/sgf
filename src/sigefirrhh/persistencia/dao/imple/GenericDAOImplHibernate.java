package sigefirrhh.persistencia.dao.imple;


import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.postgresql.util.PSQLException;

import com.ibatis.common.jdbc.exception.NestedSQLException;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import sigefirrhh.ibatis.conexion.db.MapaSQL;
import sigefirrhh.persistencia.dao.GenericDAO;
import sigefirrhh.persistencia.modelo.CriterioBusqueda;
import sigefirrhh.struts.addons.Comun;


public class GenericDAOImplHibernate extends SqlMapClientDaoSupport implements GenericDAO, Comun {

    private static SqlMapClient sqlMapper;
    static Logger log = Logger.getLogger(MapaSQL.class.getName());	

    private final static Logger LOGGER = Logger.getLogger(GenericDAOImplHibernate.class.getName());

    public GenericDAOImplHibernate() {
    	try {  
   		 
  		  Reader reader = Resources.getResourceAsReader("sigefirrhh/ibatis/conexion/db/SqlMapConfig.xml");	      	
  		  sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
  		  setSqlMapClient(sqlMapper);
  		  reader.close(); 

  	    } catch (IOException e) {	      	    	
  	      throw new RuntimeException("Something bad happened while building the SqlMapClient instance." + e, e);
  	    }

  	  
    }

    @Override
    public Object guardar(Object entity) throws SQLException, NestedSQLException, Exception  {
    	
    	int maxi = entity.getClass().getName().split("\\.").length - 1;
    	Integer valor;

  	   	valor = (Integer) getSqlMapClientTemplate().queryForObject("guardar"+entity.getClass().getName().split("\\.")[maxi], entity);
    	
  	  	//System.out.println("Clase: " + entity.getClass().getName().split("\\.")[maxi] + ", valor " + valor);  	   	

    	return valor;
    }

	@Override
	public Object buscar(CriterioBusqueda entity, String clase) throws SQLException, Exception {
		
		List lista = getSqlMapClient().queryForList("buscar"+clase, entity);
        return lista;
	}

	@Override
	public boolean validarAcceso(HttpServletRequest request, String funcion) {
		return false;
	}
    
    /*public List listar(Object entity)  {//Recuerda ponerlo en la interface
    	Session session = sessionFactory.getCurrentSession();
    	session.beginTransaction();
    	
		List lista =null;
        Criteria crit = session.createCriteria(entity.getClass());
        Method metodos[] = entity.getClass().getMethods();        
        for (int i = 0; i < metodos.length; i++) {
        	Method field = metodos[i];        	
        	
        	if (field.getName().indexOf("get") > -1){
        		//System.out.println(field.getName() +" - "+field.getReturnType()+" - "+field.getModifiers() +" - "+field.getDefaultValue() +" - "+field.getGenericReturnType());
        		
        		if (!field.getName().equals("getClass")){
	    			//if (field.getName().equals("getCompromisoInicial")){
		        			
        	        Class clazz=entity.getClass();
        			Method m;
        			
        			Integer rInt = 0;	        			
        			String rStr = "";
        			Date rDate = null;
        			
        			try {
						m = clazz.getDeclaredMethod(field.getName());	
						
						if (field.getReturnType().toString().equals("class java.lang.Integer")){
							rInt = (Integer)m.invoke(entity);
							if (rInt!=null){
								//System.out.println(field.getName() +" - " + rInt);
								//System.out.println(field.getName().substring(3).toLowerCase() +" - " + rInt);
								crit.add(Restrictions.eq(field.getName().substring(3, 4).toLowerCase() + field.getName().substring(4), rInt));
							}
						}else if (field.getReturnType().toString().equals("class java.lang.String")){
							rStr= (String)m.invoke(entity);
							if (rStr!=null){
								crit.add(Restrictions.eq(field.getName().substring(3, 4).toLowerCase() + field.getName().substring(4), rStr));
							}
						}else if (field.getReturnType().toString().equals("class java.util.Date")){
							rDate= (Date)m.invoke(entity);
							if (rDate!=null){
								crit.add(Restrictions.eq(field.getName().substring(3, 4).toLowerCase() + field.getName().substring(4), rDate));
							}
						}
						
					} catch (NoSuchMethodException e) {						
						e.printStackTrace();
					} catch (SecurityException e) {						
						e.printStackTrace();
					} catch (IllegalAccessException e) {						
						e.printStackTrace();
					} catch (IllegalArgumentException e) {						
						e.printStackTrace();
					} catch (InvocationTargetException e) {						
						e.printStackTrace();
					} 
        		}        		
        	}
            
        }
        
        //crit.add(Restrictions.eq("CompromisoInicial", comproIni.getCompromisoInicial()));
        //crit.add(Restrictions.eq("ano", comproIni.getAno()));
        //Restrictions.eq("CompromisoInicial", comproIni.getCompromisoInicial());
        lista = crit.list();       
        session.getTransaction().commit();
		return lista;
		
    }
    
    

    @Override
    public Object get(Object id)  {
        Session session = sessionFactory.getCurrentSession();
		return session;
    }

	@Override
	public void create() {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Object> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer actualizaEstatus(Object obj) {
		
		Class clazz=obj.getClass();       
		Method[] m = new Method[5];		
		try {
			
			m[0] = clazz.getDeclaredMethod("getIdOrganismo");
			m[1] = clazz.getDeclaredMethod("getAno");
			m[2] = clazz.getDeclaredMethod("getExpediente");
			m[3] = clazz.getDeclaredMethod("getEstatus");			
			
			Integer idOrganismo = (Integer) m[0].invoke(obj);
			if (idOrganismo == null){
				return -1;
			}
			Integer ano = (Integer) m[1].invoke(obj); public void create();
			if (ano == null){
				return -2;
			}
			Integer expediente = (Integer) m[2].invoke(obj);
			if (expediente == null){
				return -3;
			}
			
			Integer estatus = (Integer) m[3].invoke(obj);
			if (estatus == null){
				return -4;
			}						
			
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
	       
	        String hql = "update "+clazz.getName()+" set estatus = :nuevoEsta where idOrganismo = :org and ano = :ano and expediente = :exp";	        
	        Query query = session.createQuery(hql);
	        query.setInteger("org",idOrganismo);
	        query.setInteger("ano",ano);
	        query.setInteger("exp",expediente);
	        query.setInteger("nuevoEsta",estatus);
	        
	        int rowCount = query.executeUpdate();
	        session.getTransaction().commit();
	    	
	    	if (rowCount > 0){
	    		return rowCount;
	    	}else{
	    		return 0;
	    	}
		
		} catch (NullPointerException e) {
			System.out.println("Error grave: " + this.getClass().getName() + " a las " + hora);
			e.printStackTrace();			
		} catch (NoSuchMethodException e) {			
			e.printStackTrace();
		} catch (SecurityException e) {			
			e.printStackTrace();
		} catch (IllegalAccessException e) {			
			e.printStackTrace();
		} catch (IllegalArgumentException e) {			
			e.printStackTrace();
		} catch (InvocationTargetException e) {			
			e.printStackTrace();
		}	
		return null;
		
	}
	*/
}
