package sigefirrhh.sistema;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Opciones del Sistema
* @jdo.persistence-capable
* 	identity-type="application"
*	objectid-class="OpcionPK"
* @efordoclet-jdo
*  @efordoclet-application
*	bean="true"
*	form="true"
*   title="Opciones"
*/
public class Opcion  implements java.io.Serializable {
	protected static final Map LISTA_TIPO =
					new LinkedHashMap();
		
			static {
	
				LISTA_TIPO.put("D", "DATOS");
				LISTA_TIPO.put("R", "REPORTE");
				LISTA_TIPO.put("P", "PROCESOS");
			
			}
	/**
	 * Id Autonumerico
	* @jdo.field
	* 	persistence-modifier="persistent"
	* 	primary-key="true"
	* @efordoclet-application
	* 	data="hidden:true"
	*/
	private long idOpcion;
	/**
	 * Código Opcion
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 15"
	* @efordoclet-application
	* 	find="form:true; max:15"
	* 	data="title:Código; required:true"
	*/
	private String codigoOpcion;
	/**
	 * Nombre de Opcion
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 60"
	* @efordoclet-application
	* 	find="form:true; type:like; max:60"
	* 	data="title:Descripción; required:true; max:60"
	*/
	private String descripcion;
	/**
	 * Ruta de la Opcion
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 200"
	* @efordoclet-application
	* 	data="title:Ruta; required:true; max:200"
	*/
	private String ruta;
	/**
	 * Ruta de la Opcion
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 1"
	* @efordoclet-application
	* 	data="title:Tipo; required:true;  list:LISTA_TIPO"
	*/
	private String tipo;
	private String jerarquia;
	private String uri;
	
	public String toString(){
		return codigoOpcion + " - " + descripcion + " - " + tipo;
	}
	
	/**
	 * @return
	 */
	public String getCodigoOpcion() {
		return codigoOpcion;
	}

	/**
	 * @return
	 */
	public long getIdOpcion() {
		return idOpcion;
	}


	/**
	 * @param string
	 */
	public void setCodigoOpcion(String string) {
		codigoOpcion = string;
	}

	/**
	 * @param l
	 */
	public void setIdOpcion(long l) {
		idOpcion = l;
	}


	public String getDescripcion() {
		return descripcion;
	}

	public String getRuta() {
		return ruta;
	}

	public String getTipo() {
		return tipo;
	}

	public void setDescripcion(String string) {
		descripcion = string;
	}

	public void setRuta(String string) {
		ruta = string;
	}

	public void setTipo(String string) {
		tipo = string;
	}

	public String getJerarquia() {
		return jerarquia;
	}

	public void setJerarquia(String jerarquia) {
		this.jerarquia = jerarquia;
	}

	public String getUri() {		
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
