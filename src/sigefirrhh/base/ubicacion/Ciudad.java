package sigefirrhh.base.ubicacion;

/**
 * Ciudades
* @jdo.persistence-capable
* 	identity-type="application"
*	objectid-class="CiudadPK"
* @efordoclet-jdo
* @efordoclet-application
*	bean="true"
*	form="true"
*   order="nombre"
*   title="Ciudades"
*   modify="false"
*   add="false"
*   delete="false"
*/
public class Ciudad  implements java.io.Serializable {
	/**
	 * Id Autonumerico
	* @jdo.field
	* 	persistence-modifier="persistent"
	* 	primary-key="true"
	* @efordoclet-application
	* 	data="hidden:true"
	*/
	private long idCiudad;

	/**
	 * Código Ciudad
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 3"
	* @efordoclet-application
	* 	find="form:true; max:3"
	* 	data="max:3; title:Código; required:true"
	*/
	private String codCiudad;
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
	/**
	 * Multiplicador
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @efordoclet-application	
	* 	data="title:Multiplicador"
	*/
	private double multiplicador;
	/**
	 * Fluctuación
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @efordoclet-application	
	* 	data="title:Fluctuación"
	*/
	private double fluctuacion;
	
	public String toString(){
		return this.nombre + " " + 
		       this.codCiudad + " " +
		       this.estado.getNombre();
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getCodCiudad() {
		return codCiudad;
	}

	public void setCodCiudad(String codCiudad) {
		this.codCiudad = codCiudad;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public double getFluctuacion() {
		return fluctuacion;
	}

	public void setFluctuacion(double fluctuacion) {
		this.fluctuacion = fluctuacion;
	}

	public long getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(long idCiudad) {
		this.idCiudad = idCiudad;
	}

	public double getMultiplicador() {
		return multiplicador;
	}

	public void setMultiplicador(double multiplicador) {
		this.multiplicador = multiplicador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
