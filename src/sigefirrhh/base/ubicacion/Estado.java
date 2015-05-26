package sigefirrhh.base.ubicacion;

/**
 * Estados
* @jdo.persistence-capable
* 	identity-type="application"
*	objectid-class="EstadoPK"
* @efordoclet-jdo
*  @efordoclet-application
*	bean="true"
*	form="true"
*   order="nombre"
*   title="Estados"
*   modify="false"
*   add="false"
*   delete="false"
*/
public class Estado  implements java.io.Serializable {
	/**
	 * Id Autonumerico
	* @jdo.field
	* 	persistence-modifier="persistent"
	* 	primary-key="true"
	* @efordoclet-application
	* 	data="hidden:true"
	*/
	private long idEstado;
	/**
	 * Código Estado
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 2"
	* @efordoclet-application
	* 	find="form:true; max:2"
	* 	data="max:2; title:Código; required:true"
	*/
	private String codEstado;
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
	 * Relación con Pais
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension    
	*    vendor-name="jpox"    
	*    key="column-name"    
	*    value="id_pais"
	* @efordoclet-application
	* 	find="form:true"
	* 	data="title:País; required:true"
	*/
	private Pais pais;
	
	public String toString(){
			return this.nombre + " " + 
				   this.codEstado + " " +
				   this.pais.getNombre();
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
	public String getCodEstado() {
		return codEstado;
	}

	/**
	 * @return
	 */
	public long getIdEstado() {
		return idEstado;
	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return
	 */
	public Pais getPais() {
		return pais;
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
	public void setCodEstado(String string) {
		codEstado = string;
	}

	/**
	 * @param l
	 */
	public void setIdEstado(long l) {
		idEstado = l;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

	/**
	 * @param pais
	 */
	public void setPais(Pais pais) {
		this.pais = pais;
	}

}
