package sigefirrhh.persistencia.modelo;

public class CompromisoInicialDetalle {
   
	private Integer idCompromisoInicialDetalle;
    private Integer idCompromisoInicial;   
    private Integer idPartidaUelEspecifica;   
    private Double monto;
    private Integer ff; 
    private String codPartida;
    private Integer codUnidadEjecutora;
    private String denoUnidadEjecutora;
    private Integer codCatePresu;
    private String  denoPartida;

    
	public String getCodPartida() {
		return codPartida;
	}
	public void setCodPartida(String codPartida) {
		this.codPartida = codPartida;
	}
	public Integer getCodUnidadEjecutora() {
		return codUnidadEjecutora;
	}
	public void setCodUnidadEjecutora(Integer codUnidadEjecutora) {
		this.codUnidadEjecutora = codUnidadEjecutora;
	}
	public String getDenoUnidadEjecutora() {
		return denoUnidadEjecutora;
	}
	public void setDenoUnidadEjecutora(String denoUnidadEjecutora) {
		this.denoUnidadEjecutora = denoUnidadEjecutora;
	}
	public Integer getCodCatePresu() {
		return codCatePresu;
	}
	public void setCodCatePresu(Integer codCatePresu) {
		this.codCatePresu = codCatePresu;
	}
	public String getDenoPartida() {
		return denoPartida;
	}
	public void setDenoPartida(String denoPartida) {
		this.denoPartida = denoPartida;
	}
	public Integer getFf() {
		return ff;
	}
	public void setFf(Integer ff) {
		this.ff = ff;
	}
	public Integer getIdCompromisoInicialDetalle() {
		return idCompromisoInicialDetalle;
	}
	public void setIdCompromisoInicialDetalle(Integer idCompromisoInicialDetalle) {
		this.idCompromisoInicialDetalle = idCompromisoInicialDetalle;
	}
	public Integer getIdCompromisoInicial() {
		return idCompromisoInicial;
	}
	public void setIdCompromisoInicial(Integer idCompromisoInicial) {
		this.idCompromisoInicial = idCompromisoInicial;
	}
	public Integer getIdPartidaUelEspecifica() {
		return idPartidaUelEspecifica;
	}
	public void setIdPartidaUelEspecifica(Integer idPartidaUelEspecifica) {
		this.idPartidaUelEspecifica = idPartidaUelEspecifica;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}   
 

	
}