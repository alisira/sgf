package sigefirrhh.base.ubicacion;

/**
 * Municipios
* @jdo.persistence-capable
* 	identity-type="application"
*	objectid-class="MunicipioPK"
* @efordoclet-jdo
*  @efordoclet-application
*	bean="true"
*	form="true"
*   order="nombre"
*   title="Municipios"
*   modify="false"
*   add="false"
*   delete="false"
*/
public class Municipio  implements java.io.Serializable {
	/**
	 * Id Autonumerico
	* @jdo.field
	* 	persistence-modifier="persistent"
	* 	primary-key="true"
	* @efordoclet-application
	* 	data="hidden:true"
	*/
	private long idMunicipio;
	/**
	 * Código Municipio
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 2"
	* @efordoclet-application
	* 	find="form:true; max:2"
	* 	data="max:2; title:Código; required:true"
	*/
	private String codMunicipio;
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
	 * Relación con Estado
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension    
	*    vendor-name="jpox"    
	*    key="column-name"    
	*    value="id_estado"
	* @efordoclet-application
	* 	find="form:true; relations:pais"
	* 	data="title:Estado; relations:pais; required:true"
	*/
	private Estado estado;

	public String toString(){
		return this.nombre + " " + 
			   this.codMunicipio + " " +
			   this.estado.getNombre();
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
	public String getCodMunicipio() {
		return codMunicipio;
	}

	/**
	 * @return
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @return
	 */
	public long getIdMunicipio() {
		return idMunicipio;
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
	public void setCodMunicipio(String string) {
		codMunicipio = string;
	}

	/**
	 * @param estado
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * @param l
	 */
	public void setIdMunicipio(long l) {
		idMunicipio = l;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

}
