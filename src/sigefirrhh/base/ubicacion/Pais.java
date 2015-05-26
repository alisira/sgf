package sigefirrhh.base.ubicacion;


/**
 * Paises
* @jdo.persistence-capable
* 	identity-type="application"
*	objectid-class="PaisPK"
* @efordoclet-jdo
*  @efordoclet-application
*	bean="true"
*	form="true"
*   order="nombre"
*   title="Países"
*   modify="false"
*   add="false"
*   delete="false"
*/
public class Pais  implements java.io.Serializable {
	/**
	 * Id Autonumerico
	* @jdo.field
	* 	persistence-modifier="persistent"
	* 	primary-key="true"
	* @efordoclet-application
	* 	data="hidden:true"
	*/
	private long idPais;
	/**
	 * Código Pais
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 3"
	* @efordoclet-application
	* 	find="form:true; max:3"
	* 	data="max:3; title:Código; required:true"
	*/
	private String codPais;
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
	 * Moneda
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 30"
	* @efordoclet-application
	* 	data="max:30; title:Moneda"
	*/
	private String moneda;
	/**
	 * Moneda (singular)
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 30"
	* @efordoclet-application
	* 	data="max:30; title:Moneda Singular"
	*/
	private String monedaSing;
	/**
	 * Moneda (plural)
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 30"
	* @efordoclet-application
	* 	data="max:30; title:Moneda Plural"
	*/
	private String monedaPlur;
	/**
	 * Símbolo
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 6"
	* @efordoclet-application
	* 	data="max:6; title:Símbolo Moneda"
	*/
	private String simbolo;
	/**
	 * Fracción
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 20"
	* @efordoclet-application
	* 	data="max:20; title:Fracción de moneda"
	*/
	private String fraccion;

	/**
	 * Relación con RegionContinente
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension    
	*    vendor-name="jpox"    
	*    key="column-name"    
	*    value="id_region_continente"
	* @efordoclet-application"
	* 	data="title:Región Continental"
	*/
	private RegionContinente regionContinente;
	
	public String toString(){
		return this.nombre + " " + this.codPais;
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
	public String getCodPais() {
		return codPais;
	}

	/**
	 * @return
	 */
	public long getIdPais() {
		return idPais;
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
	public void setCodPais(String string) {
		codPais = string;
	}

	/**
	 * @param l
	 */
	public void setIdPais(long l) {
		idPais = l;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

	/**
	 * @return
	 */
	public String getFraccion() {
		return fraccion;
	}

	/**
	 * @return
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * @return
	 */
	public String getMonedaPlur() {
		return monedaPlur;
	}

	/**
	 * @return
	 */
	public String getMonedaSing() {
		return monedaSing;
	}

	/**
	 * @return
	 */
	public String getSimbolo() {
		return simbolo;
	}

	/**
	 * @param string
	 */
	public void setFraccion(String string) {
		fraccion = string;
	}

	/**
	 * @param string
	 */
	public void setMoneda(String string) {
		moneda = string;
	}

	/**
	 * @param string
	 */
	public void setMonedaPlur(String string) {
		monedaPlur = string;
	}

	/**
	 * @param string
	 */
	public void setMonedaSing(String string) {
		monedaSing = string;
	}

	/**
	 * @param string
	 */
	public void setSimbolo(String string) {
		simbolo = string;
	}

	/**
	 * @return
	 */
	public RegionContinente getRegionContinente() {
		return regionContinente;
	}

	/**
	 * @param continente
	 */
	public void setRegionContinente(RegionContinente continente) {
		regionContinente = continente;
	}

}
