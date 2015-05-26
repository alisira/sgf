package sigefirrhh.base.ubicacion;

/**
 * Parroquias
* @jdo.persistence-capable
* 	identity-type="application"
*	objectid-class="ParroquiaPK"
* @efordoclet-jdo
*  @efordoclet-application
*	bean="true"
*	form="true"
*   order="nombre"
*   title="Parroquias"
*   modify="false"
*   add="false"
*   delete="false"
*/
public class Parroquia  implements java.io.Serializable {
	/**
	 * Id Autonumerico
	* @jdo.field
	* 	persistence-modifier="persistent"
	* 	primary-key="true"
	* @efordoclet-application
	* 	data="hidden:true"
	*/
	private long idParroquia;
	/**
	 * Código Parroquia
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 2"
	* @efordoclet-application
	* 	find="form:true; max:2"
	* 	data="max:2; title:Código; required:true"
	*/
	private String codParroquia;
	/**
	 * Nombre
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 40"
	* @efordoclet-application
	* 	find="form:true; max:40; type:like"
	* 	data="max:40; title:Nombre; required:true"
	*/
	private String nombre;
	/**
	 * Abreviatura
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 3"
	* @efordoclet-application
	* 	data="max:3; title:Abreviatura"
	*/
	private String abreviatura;
	/**
	 * Relación con Municipio
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension    
	*    vendor-name="jpox"    
	*    key="column-name"    
	*    value="id_municipio"
	* @efordoclet-application
	* 	find="form:true; relations:estado,pais"
	* 	data="title:Municipio; relations:estado,pais; required:true"
	*/
	private Municipio municipio;
	
	public String toString(){
		return this.nombre + " " + 
			   this.codParroquia + " " +
			   this.municipio.getNombre() + " ";
	}
	/**
	 * @return
	 */
	public String getAbreviatura() {
		return abreviatura;
	}

	/**
	 * @return
	 */
	public String getCodParroquia() {
		return codParroquia;
	}

	/**
	 * @return
	 */
	public long getIdParroquia() {
		return idParroquia;
	}

	/**
	 * @return
	 */
	public Municipio getMunicipio() {
		return municipio;
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
	public void setAbreviatura(String string) {
		abreviatura = string;
	}

	/**
	 * @param string
	 */
	public void setCodParroquia(String string) {
		codParroquia = string;
	}

	/**
	 * @param l
	 */
	public void setIdParroquia(long l) {
		idParroquia = l;
	}

	/**
	 * @param municipio
	 */
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

}
