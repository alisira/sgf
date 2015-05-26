package sigefirrhh.sistema;

/**
 * Roles
* @jdo.persistence-capable
* 	identity-type="application"
*	objectid-class="RolPK"
* @efordoclet-jdo
*  @efordoclet-application
*	bean="true"
*	form="true"
*   title="Roles"
*/
public class Rol  implements java.io.Serializable {
	/**
	 * Id Autonumerico
	* @jdo.field
	* 	persistence-modifier="persistent"
	* 	primary-key="true"
	* @efordoclet-application
	* 	data="hidden:true"
	*/
	private long idRol;
	/**
	 * C�digo Rol
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 4"
	* @efordoclet-application
	* 	find="form:true; max:4"
	* 	data="title:C�digo: required:true; max:4"
	*/
	private String codigoRol;
	/**
	 * Nombre del Rol
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 60"
	* @efordoclet-application
	* find="form:true; type:like; max:60"
	* 	data="title:Nombre: required:true; max:60"
	*/
	private String nombre;
	
	public String toString(){
		return codigoRol + " - " + nombre;
	}
	/**
	 * @return
	 */
	public String getCodigoRol() {
		return codigoRol;
	}

	/**
	 * @return
	 */
	public long getIdRol() {
		return idRol;
	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param string
	 */
	public void setCodigoRol(String string) {
		codigoRol = string;
	}

	/**
	 * @param l
	 */
	public void setIdRol(long l) {
		idRol = l;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

}
