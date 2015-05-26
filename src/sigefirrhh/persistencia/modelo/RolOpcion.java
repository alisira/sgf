package sigefirrhh.persistencia.modelo;

import java.util.LinkedHashMap;
import java.util.Map;

import sigefirrhh.sistema.Rol;

public class RolOpcion  implements java.io.Serializable {
	
	
		protected static final Map LISTA_SI_NO =new LinkedHashMap();
		
		static {	
			LISTA_SI_NO.put("S", "SI");
			LISTA_SI_NO.put("N", "NO");			
		}
	
	private long idRolOpcion;
	private long idRol;
	private long idOpcion;
	private  Rol rol;	
	private String consultar="N";
	private String agregar="N";	
	private String modificar="N";	
	private String eliminar="N";	
	private String ejecutar="N";
	
	public long getIdRolOpcion() {
		return idRolOpcion;
	}


	/**
	 * @return
	 */
	public Rol getRol() {
		return rol;
	}

	/**
	 * @param l
	 */
	public void setIdRolOpcion(long l) {
		idRolOpcion = l;
	}
	
	/**
	 * @param rol
	 */
	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getAgregar() {
		return agregar;
	}

	public String getConsultar() {
		return consultar;
	}

	public String getEjecutar() {
		return ejecutar;
	}

	public String getEliminar() {
		return eliminar;
	}

	public String getModificar() {
		return modificar;
	}

	public void setAgregar(String string) {
		agregar = string;
	}

	public void setConsultar(String string) {
		consultar = string;
	}

	public void setEjecutar(String string) {
		ejecutar = string;
	}

	public void setEliminar(String string) {
		eliminar = string;
	}

	public void setModificar(String string) {
		modificar = string;
	}
	public long getIdRol() {
		return idRol;
	}
	public void setIdRol(long idRol) {
		this.idRol = idRol;
	}
	public long getIdOpcion() {
		return idOpcion;
	}
	public void setIdOpcion(long idOpcion) {
		this.idOpcion = idOpcion;
	}

}
