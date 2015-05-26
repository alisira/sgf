package sigefirrhh.struts.actionForm;

import org.apache.struts.action.ActionForm;

public class UnidadAdministradoraForm  extends ActionForm{
   
   
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -7750877928813366637L;
	private Integer idUnidadAdministradora;    
    private Integer ano;    
    private Integer idOrganismo;    
    private Integer codUnidadAdministradora;    
    private String denominacion;    
    private String codPagadora;    
    private String vigente;
    private Integer estatus_guardado;
    
	public Integer getEstatus_guardado() {
		return estatus_guardado;
	}
	public void setEstatus_guardado(Integer estatus_guardado) {
		this.estatus_guardado = estatus_guardado;
	}
	public Integer getIdUnidadAdministradora() {
		return idUnidadAdministradora;
	}
	public void setIdUnidadAdministradora(Integer idUnidadAdministradora) {
		this.idUnidadAdministradora = idUnidadAdministradora;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public Integer getIdOrganismo() {
		return idOrganismo;
	}
	public void setIdOrganismo(Integer idOrganismo) {
		this.idOrganismo = idOrganismo;
	}
	public Integer getCodUnidadAdministradora() {
		return codUnidadAdministradora;
	}
	public void setCodUnidadAdministradora(Integer codUnidadAdministradora) {
		this.codUnidadAdministradora = codUnidadAdministradora;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public String getCodPagadora() {
		return codPagadora;
	}
	public void setCodPagadora(String codPagadora) {
		this.codPagadora = codPagadora;
	}
	public String getVigente() {
		return vigente;
	}
	public void setVigente(String vigente) {
		this.vigente = vigente;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    

	
    
}