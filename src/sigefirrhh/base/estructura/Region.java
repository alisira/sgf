package sigefirrhh.base.estructura;

/**
* Reiones
* @jdo.persistence-capable
* 	identity-type="application"
*	objectid-class="RegionPK"
* @efordoclet-jdo
*  @efordoclet-application
*	bean="true"
*	form="true"
*   order="nombre"
*   title="Regiones"
*/
public class Region  implements java.io.Serializable {
	/**
	 * Id Autonumerico
	* @jdo.field
	* 	persistence-modifier="persistent"
	* 	primary-key="true"
	* @efordoclet-application
	* 	data="hidden:true"
	*/
	private long idRegion;
	/**
	 * Código de Sede
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 2"
	* @efordoclet-application
	* 	find="form:true; max:2"
	* 	data="max:2; title:Código; required:true"
	*/
	private String codRegion;
	/**
	 * Nombre
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 90"
	* @efordoclet-application
	* 	find="form:true; max:60; type:like"
	* 	data="max:60; title:Nombre; required:true"
	*/
	private String nombre;
	
	/**
	 * Relación con Organismo
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension    
	*    vendor-name="jpox"    
	*    key="column-name"    
	*    value="id_organismo"
	* @efordoclet-application
	* 	find="bean:true; required:true"
	*/
	private Organismo organismo;
	
	
	public String toString(){
			return this.nombre + " - " + this.codRegion;
			}


	public String getCodRegion() {
		return codRegion;
	}


	public void setCodRegion(String codRegion) {
		this.codRegion = codRegion;
	}


	public long getIdRegion() {
		return idRegion;
	}


	public void setIdRegion(long idRegion) {
		this.idRegion = idRegion;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Organismo getOrganismo() {
		return organismo;
	}


	public void setOrganismo(Organismo organismo) {
		this.organismo = organismo;
	}

	
}
