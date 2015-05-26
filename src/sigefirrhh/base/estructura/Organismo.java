package sigefirrhh.base.estructura;

import java.util.LinkedHashMap;
import java.util.Map;

import sigefirrhh.base.ubicacion.Ciudad;
/**
 * Organismos
* @jdo.persistence-capable
* 	identity-type="application"
*	objectid-class="OrganismoPK"
* @efordoclet-jdo
*  @efordoclet-application
*	bean="true"
*	form="true"
*   order="codOrganismo"
*   title="Organismos"
*/
public class Organismo  implements java.io.Serializable { 
	protected static final Map LISTA_SI_NO =
						new LinkedHashMap();
	static {
					LISTA_SI_NO.put("S", "SI");
					LISTA_SI_NO.put("N", "NO");
	}
	/**
	 * Id Autonumerico
	* @jdo.field
	* 	persistence-modifier="persistent"
	* 	primary-key="true"
	* @efordoclet-application
	* 	data="hidden:true"
	*/
	private long idOrganismo;
	/***
	 * 
	 * C�digo Organismo
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 4"
	* @efordoclet-application
	* 	find="form:true; max:4"
	* 	data="max:4; title:C�digo; required:true"
	*/
	private String codOrganismo;
	/**
	 * Nombre Actual Organismo
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 90"
	* @efordoclet-application
	* 	find="form:true; max:90; type:like"
	* 	data="max:90; title:Nombre; required:true"
	*/
	private String nombreOrganismo;
	/**
	 * Siglas Organismo
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 15"
	* @efordoclet-application
	* 	data="max:15; title:Siglas"
	*/
	private String nombreCorto;
	/**
	 * Numero RIF
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 12"
	* @efordoclet-application
	* 	data="max:12; title:N�RIF"
	*/
	private String rif;
	/**
	 * Numero NIT
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 12"
	* @efordoclet-application
	* 	data="max:12; title:N�NIT"
	*/
	private String nit;
	/**
	 * Direccion
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 100"
	* @efordoclet-application
	* 	data="max:100; title:Direcci�n"
	*/
	private String direccion;
	/**
	 * Zona Postal
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 10"
	* @efordoclet-application
	* 	data="max:10; title:Zona Postal"
	*/
	private String zonaPostal;
	/**
	 * Telefono
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 15"
	* @efordoclet-application
	* 	data="max:15; title:Tel�fono"
	*/
	private String telefono;
	/**
	 * Nombre Agente Retencion
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 50"
	* @efordoclet-application
	* 	data="max:50; title:Nombre Agente Retenci�n"
	*/
	private String nombreAgenteRetencion;
	/**
	 * Cedula Agente Retencion
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 10"
	* @efordoclet-application
	* 	data="max:10; title:C�dula Agente Retenci�n"
	*/
	private String cedulaAgenteRetencion;
	/**
	 * RIF Agente Retencion
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 12"
	* @efordoclet-application
	* 	data="max:12; title:N�RIF Agente Retenci�n"
	*/
	private String rifAgenteRetencion;
	/**
	 * C�digo Anterior MPD
	* @jdo.field
	* 	persistence-modifier="persistent"
	* 	key="length" value="max 10"
	* @efordoclet-application
	* 	data="title:C�digo Anterior MPD"
	*/
	private String codigoAnteriorMpd;
	/**
	 * C�digo SIGECOF
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 4"
	* @efordoclet-application
	* 	data="max:4; title:C�digo SiGECOF"
	*/
	private String codSigecof;
	/**
	 * Unidad Ejecutora Local
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 6"
	* @efordoclet-application
	* 	data="max:6; title:U.E.L."
	*/
	private String codUel;
	/**
	 * Nombre Director RRHH
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 50"
	* @efordoclet-application
	* 	data="max:50; title:Nombre Director RRHH"
	*/
	private String nombreRrhh;
	/**
	 * Cedula Director RRHH
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @efordoclet-application
	* 	data="title:C�dula Director RRHH"
	*/
	private int cedulaRrhh;
	/**
	 * Tel�fono Director RRHH
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 15"
	* @efordoclet-application
	* 	data="max:15; title:Tel�fono Director RRHH"
	*/
	private String telefonoRrhh;
	/**
	 * Gaceta Director RRHH
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 80"
	* @efordoclet-application
	* 	data="max:80; title:Gaceta Director RRHH"
	*/
	private String gacetaRrhh;
	/**
	 * Nombre Director Inform�tica
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 50"
	* @efordoclet-application
	* 	data="max:50; title:Nombre Director Inform�tica"
	*/
	private String nombreInformatica;
	/**
	 * Cedula Director Inform�tica
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @efordoclet-application
	* 	data="title:C�dula Director Inform�tica"
	*/
	private int cedulaInformatica;
	/**
	 * Tel�fono Director Inform�tica
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 15"
	* @efordoclet-application
	* 	data="max:15; title:Tel�fono Director Inform�tica"
	*/
	private String telefonoInformatica;
	/**
	 * Gaceta Director Inform�tica
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 80"
	* @efordoclet-application
	* 	data="max:80; title:Gaceta Director Inform�tica"
	*/
	private String gacetaInformatica;
	/**
	 * Nombre M�xima Autoridad
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 50"
	* @efordoclet-application
	* 	data="max:50; title:Nombre M�xima Autoridad"
	*/
	private String nombreMaximaAutoridad;
	/**
	 * Cedula Director Inform�tica
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @efordoclet-application
	* 	data="title:C�dula M�xima Autoridad"
	*/
	private int cedulaMaximaAutoridad;
	/**
	 * Tel�fono M�xima Autoridad
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 15"
	* @efordoclet-application
	* 	data="max:15; title:Tel�fono M�xima Autoridad"
	*/
	private String telefonoMaximaAutoridad;
	/**
	 * Gaceta M�xima Autoridad
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 80"
	* @efordoclet-application
	* 	data="max:80; title:Gaceta M�xima Autoridad"
	*/
	private String gacetaMaximaAutoridad;
	/**organismo
	 * Es un organo rector (S/N)
	* @jdo.field
	* 	persistence-modifier="persistent"
	* 	key="length" value="max 1"
	* @efordoclet-application
	* 	data="title:Organo Rector?; list:LISTA_SI_NO; required:true"
	*/
	private String organoRector="N";
	/**
	 * Aprobacion MPD (S/N)
	* @jdo.field
	* 	persistence-modifier="persistent"
	* 	key="length" value="max 1"
	* @efordoclet-application
	* 	data="title:Aprueba MPD?; list:LISTA_SI_NO; required:true"
	*/
	private String aprobacionMpd="S";
	/**
	 * Organismo Adscrito (S/N)
	* @jdo.field
	* 	persistence-modifier="persistent"
	* 	key="length" value="max 1"
	* @efordoclet-application
	* 	data="title:Es un organismo adscrito?; list:LISTA_SI_NO; required:true"
	*/
	private String organismoAdscrito="N";
	/**
	 * Relaci�n con Ciudad
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension    
	*    vendor-name="jpox"    
	*    key="column-name"    
	*    value="id_ciudad"
	* @efordoclet-application
	* 	data="title:Ciudad; relations:estado,pais"
	*/
	private Ciudad ciudad;
	/**
	 * Actualiza Expediente
	* @jdo.field
	* 	persistence-modifier="persistent"
	* 	key="length" value="max 1"
	* @efordoclet-application
	* 	data="data:hidden; title:Actualiza Expediente?; list:LISTA_SI_NO; required:true"
	*/
	private String actualizaExpediente="N";
	
	
	public String toString(){
			return this.codOrganismo + " " + 
			       this.nombreOrganismo;
			}
	
	/**
	 * @return
	 */
	public String getAprobacionMpd() {
		return aprobacionMpd;
	}

	/**
	 * @return
	 */
	public String getCedulaAgenteRetencion() {
		return cedulaAgenteRetencion;
	}
	
	/**
	 * @return
	 */
	public Ciudad getCiudad() {
		return ciudad;
	}

	/**
	 * @return
	 */
	public String getCodOrganismo() {
		return codOrganismo;
	}

	/**
	 * @return
	 */
	public String getCodSigecof() {
		return codSigecof;
	}

	/**
	 * @return
	 */
	public String getCodUel() {
		return codUel;
	}

	/**
	 * @return
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @return
	 */
	public long getIdOrganismo() {
		return idOrganismo;
	}

	/**
	 * @return
	 */
	public String getNit() {
		return nit;
	}

	/**
	 * @return
	 */
	public String getNombreAgenteRetencion() {
		return nombreAgenteRetencion;
	}

	/**
	 * @return
	 */
	public String getOrganoRector() {
		return organoRector;
	}

	/**
	 * @return
	 */
	public String getRif() {
		return rif;
	}

	/**
	 * @return
	 */
	public String getRifAgenteRetencion() {
		return rifAgenteRetencion;
	}

	/**
	 * @return
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @return
	 */
	public String getZonaPostal() {
		return zonaPostal;
	}

	/**
	 * @param string
	 */
	public void setAprobacionMpd(String string) {
		aprobacionMpd = string;
	}

	/**
	 * @param string
	 */
	public void setCedulaAgenteRetencion(String string) {
		cedulaAgenteRetencion = string;
	}

	/**
	 * @param ciudad
	 */
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * @param string
	 */
	public void setCodOrganismo(String string) {
		codOrganismo = string;
	}

	/**
	 * @param string
	 */
	public void setCodSigecof(String string) {
		codSigecof = string;
	}

	/**
	 * @param string
	 */
	public void setCodUel(String string) {
		codUel = string;
	}

	/**
	 * @param string
	 */
	public void setDireccion(String string) {
		direccion = string;
	}

	/**
	 * @param l
	 */
	public void setIdOrganismo(long l) {
		idOrganismo = l;
	}

	/**
	 * @param string
	 */
	public void setNit(String string) {
		nit = string;
	}

	/**
	 * @param string
	 */
	public void setNombreAgenteRetencion(String string) {
		nombreAgenteRetencion = string;
	}

	/**
	 * @param string
	 */
	public void setOrganoRector(String string) {
		organoRector = string;
	}

	/**
	 * @param string
	 */
	public void setRif(String string) {
		rif = string;
	}

	/**
	 * @param string
	 */
	public void setRifAgenteRetencion(String string) {
		rifAgenteRetencion = string;
	}

	/**
	 * @param string
	 */
	public void setTelefono(String string) {
		telefono = string;
	}

	/**
	 * @param string
	 */
	public void setZonaPostal(String string) {
		zonaPostal = string;
	}

	public String getNombreOrganismo() {
		return nombreOrganismo;
	}

	public void setNombreOrganismo(String string) {
		nombreOrganismo = string;
	}

	public String getOrganismoAdscrito() {
		return organismoAdscrito;
	}

	public void setOrganismoAdscrito(String string) {
		organismoAdscrito = string;
	}

	/**
	 * @return
	 */
	public int getCedulaInformatica() {
		return cedulaInformatica;
	}

	/**
	 * @return
	 */
	public int getCedulaMaximaAutoridad() {
		return cedulaMaximaAutoridad;
	}

	/**
	 * @return
	 */
	public int getCedulaRrhh() {
		return cedulaRrhh;
	}

	/**
	 * @return
	 */
	public String getNombreInformatica() {
		return nombreInformatica;
	}

	/**
	 * @return
	 */
	public String getNombreMaximaAutoridad() {
		return nombreMaximaAutoridad;
	}

	/**
	 * @return
	 */
	public String getNombreRrhh() {
		return nombreRrhh;
	}

	/**
	 * @param i
	 */
	public void setCedulaInformatica(int i) {
		cedulaInformatica = i;
	}

	/**
	 * @param i
	 */
	public void setCedulaMaximaAutoridad(int i) {
		cedulaMaximaAutoridad = i;
	}

	/**
	 * @param i
	 */
	public void setCedulaRrhh(int i) {
		cedulaRrhh = i;
	}

	/**
	 * @param string
	 */
	public void setNombreInformatica(String string) {
		nombreInformatica = string;
	}

	/**
	 * @param string
	 */
	public void setNombreMaximaAutoridad(String string) {
		nombreMaximaAutoridad = string;
	}

	/**
	 * @param string
	 */
	public void setNombreRrhh(String string) {
		nombreRrhh = string;
	}

	/**
	 * @return
	 */
	public String getCodigoAnteriorMpd() {
		return codigoAnteriorMpd;
	}

	/**
	 * @param string
	 */
	public void setCodigoAnteriorMpd(String string) {
		codigoAnteriorMpd = string;
	}

	/**
	 * @return
	 */
	public String getActualizaExpediente() {
		return actualizaExpediente;
	}

	/**
	 * @param string
	 */
	public void setActualizaExpediente(String string) {
		actualizaExpediente = string;
	}

	/**
	 * @return
	 */
	public String getTelefonoInformatica() {
		return telefonoInformatica;
	}

	/**
	 * @return
	 */
	public String getTelefonoMaximaAutoridad() {
		return telefonoMaximaAutoridad;
	}

	
	/**
	 * @param string
	 */
	public void setTelefonoInformatica(String string) {
		telefonoInformatica = string;
	}

	/**
	 * @param string
	 */
	public void setTelefonoMaximaAutoridad(String string) {
		telefonoMaximaAutoridad = string;
	}

	

	/**
	 * @return
	 */
	public String getGacetaInformatica() {
		return gacetaInformatica;
	}

	/**
	 * @return
	 */
	public String getGacetaMaximaAutoridad() {
		return gacetaMaximaAutoridad;
	}

	/**
	 * @return
	 */
	public String getGacetaRrhh() {
		return gacetaRrhh;
	}

	/**
	 * @param string
	 */
	public void setGacetaInformatica(String string) {
		gacetaInformatica = string;
	}

	/**
	 * @param string
	 */
	public void setGacetaMaximaAutoridad(String string) {
		gacetaMaximaAutoridad = string;
	}

	/**
	 * @param string
	 */
	public void setGacetaRrhh(String string) {
		gacetaRrhh = string;
	}

	

	/**
	 * @return
	 */
	public String getTelefonoRrhh() {
		return telefonoRrhh;
	}

	/**
	 * @param string
	 */
	public void setTelefonoRrhh(String string) {
		telefonoRrhh = string;
	}

	public String getNombreCorto() {
		return nombreCorto;
	}
	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}
}
