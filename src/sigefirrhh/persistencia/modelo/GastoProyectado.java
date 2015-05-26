package sigefirrhh.persistencia.modelo;

public class GastoProyectado {
	
	
	private Integer idPartidaUelEspecifica;
    private Integer codUnidadEjecutora;    
    private String denoUnidadEjecutora;    
    private Integer codCatePresu;
    private Double monto;
	private Integer cedula;
    private String codConcepto;
    private String descripcion;
    private Integer idTipoPersonal;   
    private Integer codFrecuenciaPago;
    private String codPartida;
    private String denoPartida;
    private int ff;
    
    private Integer idUel;
    private Integer idOrganismo;
    private Integer idPartida;    
    
    
	public String getDenoUnidadEjecutora() {
		return denoUnidadEjecutora;
	}
	public void setDenoUnidadEjecutora(String denoUnidadEjecutora) {
		this.denoUnidadEjecutora = denoUnidadEjecutora;
	}
	public Integer getIdPartida() {
		return idPartida;
	}
	public void setIdPartida(Integer idPartida) {
		this.idPartida = idPartida;
	}
	public Integer getIdOrganismo() {
		return idOrganismo;
	}
	public void setIdOrganismo(Integer idOrganismo) {
		this.idOrganismo = idOrganismo;
	}
	public Integer getIdUel() {
		return idUel;
	}
	public void setIdUel(Integer idUel) {
		this.idUel = idUel;
	}
	public String getCodPartida() {
		return codPartida;
	}
	public void setCodPartida(String codPartida) {
		this.codPartida = codPartida;
	}
	public Integer getIdPartidaUelEspecifica() {
		return idPartidaUelEspecifica;
	}
	public void setIdPartidaUelEspecifica(Integer idPartidaUelEspecifica) {
		this.idPartidaUelEspecifica = idPartidaUelEspecifica;
	}
	public Integer getCodUnidadEjecutora() {
		return codUnidadEjecutora;
	}
	public void setCodUnidadEjecutora(Integer codUnidadEjecutora) {
		this.codUnidadEjecutora = codUnidadEjecutora;
	}
	
	public Integer getCodCatePresu() {
		return codCatePresu;
	}
	public void setCodCatePresu(Integer codCatePresu) {
		this.codCatePresu = codCatePresu;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public Integer getCedula() {
		return cedula;
	}
	public void setCedula(Integer cedula) {
		this.cedula = cedula;
	}
	public String getCodConcepto() {
		return codConcepto;
	}
	public void setCodConcepto(String codConcepto) {
		this.codConcepto = codConcepto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getIdTipoPersonal() {
		return idTipoPersonal;
	}
	public void setIdTipoPersonal(Integer idTipoPersonal) {
		this.idTipoPersonal = idTipoPersonal;
	}
	public Integer getCodFrecuenciaPago() {
		return codFrecuenciaPago;
	}
	public void setCodFrecuenciaPago(Integer codFrecuenciaPago) {
		this.codFrecuenciaPago = codFrecuenciaPago;
	}
	public String getDenoPartida() {
		return denoPartida;
	}
	public void setDenoPartida(String denoPartida) {
		this.denoPartida = denoPartida;
	}
	public int getFf() {
		return ff;
	}
	public void setFf(int ff) {
		this.ff = ff;
	}
    
	
}