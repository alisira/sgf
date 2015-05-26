package sigefirrhh.base.ubicacion;

/**
 * Regiones de los continentes
* @jdo.persistence-capable
* 	identity-type="application"
*	objectid-class="RegionContinentePK"
* @efordoclet-jdo
*  @efordoclet-application
*	bean="true"
*	form="true"
*   order="nombre"
*   title="Regiones Continentales"
*   modify="false"
*   add="false"
*   delete="false"
*/
public class RegionContinente  implements java.io.Serializable {
	/**
	 * Id Autonumerico
	* @jdo.field
	* 	persistence-modifier="persistent"
	* 	primary-key="true"
	* @efordoclet-application
	* 	data="hidden:true"
	*/
	private long idRegionContinente;
	/**
	 * Código Region
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 2"
	* @efordoclet-application
	* 	find="form:true; max:2"
	* 	data="max:2; title:Código; required:true"
	*/
	private String codRegionContinente;
	/**
	 * Nombre
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 40"
	* 	@efordoclet-application
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
	 * Relación con Continente
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension    
	*    vendor-name="jpox"    
	*    key="column-name"    
	*    value="id_continente"
	* @efordoclet-application
	* 	find="form:true"
	* 	data="title:Continente; required:true"
	*/
	private Continente continente;
	
	public String toString(){
			return this.nombre + " " + 
				   this.codRegionContinente;
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
	public String getCodRegionContinente() {
		return codRegionContinente;
	}

	/**
	 * @return
	 */
	public Continente getContinente() {
		return continente;
	}

	/**
	 * @return
	 */
	public long getIdRegionContinente() {
		return idRegionContinente;
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
	public void setCodRegionContinente(String string) {
		codRegionContinente = string;
	}

	/**
	 * @param continente
	 */
	public void setContinente(Continente continente) {
		this.continente = continente;
	}

	/**
	 * @param l
	 */
	public void setIdRegionContinente(long l) {
		idRegionContinente = l;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

}
