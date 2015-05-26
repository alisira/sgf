package sigefirrhh.sistema;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import sigefirrhh.base.estructura.Region;
/**
 * Usuarios
* @jdo.persistence-capable
* 	identity-type="application"
*	objectid-class="UsuarioPK"
* @efordoclet-jdo
* @efordoclet-application
*	bean="true"
*	form="true"
* delete="false"
*	order="apellidos"
*   title="Usuarios"
*/
public class Usuario  implements java.io.Serializable {
	protected static final Map LISTA_SINO =
				new LinkedHashMap();
	
				static {
					LISTA_SINO.put("S", "SI");
					LISTA_SINO.put("N", "NO");
				}
	/**
	 * Id Autonumerico
	* @jdo.field
	* 	persistence-modifier="persistent"
	* 	primary-key="true"
	* @efordoclet-application
	* 	data="hidden:true"
	*/
	private long idUsuario;
	/**
	 * C�digo Usuario
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 25"
	* @efordoclet-application
	* 	find="form:true; max:25; type:like"
	* 	data="max:25; title:Usuario; required:true"
	*/
	private String usuario;
	/**
	 * Password
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 25"
	* @efordoclet-application
	* 	data="max:25; title:Contrase�a; required:true"
	*/
	private String password;
	/**
	 * C�dula
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @efordoclet-application
	* 	data="title:C�dula; required:true; max:8"
	*/
	private int cedula;
	/**
	 * Apellidos
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 25"
	* @efordoclet-application
	* 	find="form:true; max:25; type:like"
	* 	data="max:25; title:Apellidos; required:true"
	* 
	*/
	private String apellidos;
	/**
	 * Nombres
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 25"
	* @efordoclet-application
	* 	data="max:25; title:Nombres; required:true"
	*/
	private String nombres;
	/**
	 * Relaci�n con Region
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension    
	*    vendor-name="jpox"    
	*    key="column-name"    
	*    value="id_region"
	* @efordoclet-application
	* 	data="title:Region"
	*/
	private  Region region;
	/**
	 * Telefono
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @jdo.field-vendor-extension vendor-name="jpox"
	* 	key="length" value="max 25"
	* @efordoclet-application
	* 	data="max:25; title:Telefono"
	*/
	private String telefono;
	
	/**
	 * Administrador (S/N)
	* @jdo.field
	* 	persistence-modifier="persistent"
	* 	key="length" value="max 1"
	* @efordoclet-application
	* 	data="title:Usuario Administrador ?; list:LISTA_SINO; required:true"
	*/
	private String administrador="N";
	/**
	 * Vigencia del Password 
	* @jdo.field
	* 	persistence-modifier="persistent"
	* 	key="length" value="max 1"
	* @efordoclet-application
	*/
	private Date cambioPassword;

	/**
	 * Activo (S/N)
	* @jdo.field
	* 	persistence-modifier="persistent"
	* 	key="length" value="max 1"
	* @efordoclet-application
	* 	data="title:Activo ?; list:LISTA_SINO; required:true"
	*/
	private String activo="S";

	//RJB 
	/**
	 * Contador de intentos de login antes de desactivar cuenta 
	* @jdo.field
	* 	persistence-modifier="persistent"
	* @efordoclet-application
	* 	data="title:Intentos; max:3"
	*/
	private int intentos=0;
	
	
	private long idOrganismo;

	//RJB 
	/**
	 * Devuelve la cantidad de intentos de login fallidos desde el �ltimo login correcto
	 * @return cantidad de intentos fallidos
	 */
	public int getIntentos() {
		return intentos;
	}

	//RJB 
	/**
	 * Establece la cantidad de intentos de login fallidos
	 * @param intentos
	 */
	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}
	
	//RJB 
	/**
	 * Fecha de Vencimiento de la cuenta 
	* @jdo.field
	* 	persistence-modifier="persistent"
	* 	key="length" value="max 1"
	* @efordoclet-application
	*/
	private Date fechaVence;
	
	//RJB 
	/**
	 * Establece la fecha de vencimiento de la cuenta de usuario
	 * @param fecha
	 */
	public void setFechaVence(Date fecha){
		this.fechaVence=fecha;
	}
	
	//RJB 
	/**
	 * Devuelve la fecha de vencimiento de la cuenta, puede ser null
	 * @return la fecha de vencimiento de la cuenta o null
	 */
	public Date getFechaVence(){
		return this.fechaVence;
	}	
	
	public String toString(){
					return this.usuario + "  -  " +
					this.apellidos + "  -  " +
					this.nombres + " - " + cedula;
			}

	/**
	 * @return
	 */
	public String getAdministrador() {
		return administrador;
	}

	/**
	 * @return
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @return
	 */
	public int getCedula() {
		return cedula;
	}

	/**
	 * @return
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @return
	 */
	public long getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @return
	 */
	public String getNombres() {
		return nombres;
	}

	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	public Date getCambioPassword() {
		return this.cambioPassword;
	}

	
	/**
	 * @param string
	 */
	public void setAdministrador(String string) {
		administrador = string;
	}

	/**
	 * @param string
	 */
	public void setApellidos(String string) {
		apellidos = string;
	}

	/**
	 * @param i
	 */
	public void setCedula(int i) {
		cedula = i;
	}

	/**
	 * @param string
	 */
	public void setUsuario(String string) {
		usuario = string;
	}

	/**
	 * @param l
	 */
	public void setIdUsuario(long l) {
		idUsuario = l;
	}

	/**
	 * @param string
	 */
	public void setNombres(String string) {
		nombres = string;
	}

	/**
	 * @param string
	 */
	public void setPassword(String string) {
		password = string;
	}

	/**
	 * @return
	 */
	public String getActivo() {
		return activo;
	}

	/**
	 * @return
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param string
	 */
	public void setActivo(String string) {
		activo = string;
	}

	/**
	 * @param string
	 */
	public void setTelefono(String string) {
		telefono = string;
	}

	/**
	 * @return
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * @param region
	 */
	public void setRegion(Region region) {
		this.region = region;
	}

	public long getIdOrganismo() {
		return idOrganismo;
	}

	public void setIdOrganismo(long idOrganismo) {
		this.idOrganismo = idOrganismo;
	}

	public void setCambioPassword(Date fecha) {
		this.cambioPassword = fecha;
	}

	 public boolean cambiaPassword() {  

		 Calendar cal = Calendar.getInstance();  

		 cal.setTime(new Date());  
		 int minuendMonth =  cal.get(Calendar.MONTH);  
		 int minuendYear = cal.get(Calendar.YEAR); 
		 int minuedDay = cal.get(Calendar.DAY_OF_MONTH);
		 cal.setTime(this.cambioPassword);  
		 int subtrahendMonth =  cal.get(Calendar.MONTH);  
		 int subtrahendYear = cal.get(Calendar.YEAR);  
		 int subtraDay = cal.get(Calendar.DAY_OF_MONTH);
		 
		 int ajuste = 0; // ajuste porsi no se ha cumplido el mes todav�a
		 if(minuedDay < subtraDay){ 
			 ajuste = -1;
		 }
		 
		 int diferencia = ((minuendYear - subtrahendYear) * cal.getMaximum(Calendar.MONTH)) + (minuendMonth - subtrahendMonth) + ajuste;
		 

		 if(diferencia>=3) {
			 return true;
		 }else{
			 return false;
		 }
		 
	 }

	 //RJB
	 /**
	  * Devuelve verdadero en caso de que el usuario est� en la obligaci�n de cambiar la clave
	  * @param diasDuracionClave cada cuantos dias se debe cambiar la clave
	  * @return
	  */
	public boolean cambiaPasswordDias(int diasDuracionClave) {
			Calendar c = Calendar.getInstance();
			Date hoy = c.getTime();		
			//Establecer la fecha de cambio de clave
			c.setTime(this.cambioPassword);
			//Sumar los d�as de duracion de la clave
			c.add(Calendar.DATE, diasDuracionClave);
			//Tomar la fecha en que qued�
			Date fechaVenceClave = c.getTime();
			//Verificar si hay que cambiar la clave
			if (hoy.after(fechaVenceClave)){
				//La fecha venci� antes de hoy
				return true;
			}else{
				//La fecha vence despu�s de hoy
				return false;
			}
	}	
	
	
}
