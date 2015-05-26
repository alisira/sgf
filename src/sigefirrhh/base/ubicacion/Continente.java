package sigefirrhh.base.ubicacion;


/**
 * Continentes
* @jdo.persistence-capable
* 	identity-type="application"
*	objectid-class="ContinentePK"
* @efordoclet-jdo
*  @efordoclet-application
*	bean="true"
*	form="true"
*   order="nombre"
*   title="Continentes"
*   modify="false"
*   add="false"
*   delete="false"
*/
public class Continente  implements java.io.Serializable {
	/**
	 * Id Autonumerico
	* @jdo.field
	* 	persistence-modifier="persistent"
	* 	primary-key="true"
	* @efordoclet-application
	* 	data="hidden:true"
	*/
	private long idContinente;
	/**
	 * Código Continente
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 1"
	* @efordoclet-application
	* 	find="form:true; max:1"
	* 	data="max:1; title:Código; required:true"
	*/
	private String codContinente;
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

	public String toString(){
		return this.nombre + " " + this.codContinente;

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
	public String getCodContinente() {
		return codContinente;
	}

	/**
	 * @return
	 */
	public long getIdContinente() {
		return idContinente;
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
	public void setCodContinente(String string) {
		codContinente = string;
	}

	/**
	 * @param l
	 */
	public void setIdContinente(long l) {
		idContinente = l;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

}