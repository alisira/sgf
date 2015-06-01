package sigefirrhh.login;

import java.util.Collection;

import sigefirrhh.base.estructura.Organismo;
import sigefirrhh.sistema.Usuario;
/**
 * Login Session
 *
 */

public class LoginSession {

	private String usuario;
	private Organismo organismo;
	private boolean servicioPersonal;
	private Collection colUsuarioRol;
	private long idUsuario;
	private boolean autenticado;
	private boolean consultar;
	private boolean agregar;
	private boolean modificar;
	private boolean eliminar;
	private boolean ejecutar;
	private boolean passwordVencido;
	private String administrador;
	private Usuario usuarioObject;
	
	public Organismo getOrganismo() {
		return organismo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setOrganismo(Organismo organismo) {
		this.organismo = organismo;
	}

	public void setUsuario(String string) {
		usuario = string;
	}
	
	public long getIdOrganismo() {
		return organismo.getIdOrganismo();
	}
	
	public String getURLLogo() {
		if (this.organismo!=null) {
			return "/images/logo/" + organismo.getCodOrganismo() + ".gif";
		} else {
			return "/images/logo/blank.gif";
		}
	}
	
	public String getURLNombre() {
		if (this.organismo!=null) {
			return "/images/logo/" + organismo.getCodOrganismo() + "-n.gif";
		} else {
			return "/images/logo/blank-n.gif";
		}
	}
	
	public boolean isSupervisor() {
		return this.usuario.equals("admin");
	}

	public boolean isValid() {
		
		return this.usuario!=null;
	}

	public boolean isServicioPersonal() {
		return servicioPersonal;
	}

	public void setServicioPersonal(boolean b) {
		servicioPersonal = b;
	}

	public Collection getColUsuarioRol() {
		return colUsuarioRol;
	}

	public void setColUsuarioRol(Collection collection) {
		colUsuarioRol = collection;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long l) {
		idUsuario = l;
	}

	public void setPasswordVencido(boolean b) {
		passwordVencido = b;
	}


	

	public boolean isAutenticado() {
		return autenticado;
	}

	public void setAutenticado(boolean b) {
		autenticado = b;
	}

		
	public String getAdministrador() {
		return administrador;
	}

	public boolean isAgregar() {
		return agregar;
	}

	public boolean isConsultar() {
		return consultar;
	}

	public boolean isEjecutar() {
		return ejecutar;
	}

	public boolean isEliminar() {
		return eliminar;
	}

	public boolean isModificar() {
		return modificar;
	}

	public void setAdministrador(String string) {
		administrador = string;
	}

	public void setAgregar(boolean b) {
		agregar = b;
	}

	public void setConsultar(boolean b) {
		consultar = b;
	}

	public void setEjecutar(boolean b) {
		ejecutar = b;
	}

	public void setEliminar(boolean b) {
		eliminar = b;
	}

	public void setModificar(boolean b) {
		modificar = b;
	}

	/**
	 * @return
	 */
	public Usuario getUsuarioObject() {
		return usuarioObject;
	}

	/**
	 * @param usuario
	 */
	public void setUsuarioObject(Usuario usuario) {
		usuarioObject = usuario;
	}

	public boolean isPasswordVencido() {
		return passwordVencido;
	}
}