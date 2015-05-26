package sigefirrhh.persistencia.dao;

import java.sql.SQLException;
import java.util.List;

import sigefirrhh.persistencia.modelo.CriterioBusqueda;

public interface GenericDAO {
   
    public Object guardar(Object reg) throws SQLException, Exception;
    public Object buscar(CriterioBusqueda reg, String clase) throws SQLException, Exception;
    /*public void create();
    public Object get(Object obj);
    public void delete(Object id);
    List<Object> findAll();
    public List<Object> listar(Object entity);
    public Object actualizaEstatus(Object obj);
    */
}
