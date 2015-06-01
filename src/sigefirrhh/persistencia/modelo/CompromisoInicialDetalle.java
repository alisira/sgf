package sigefirrhh.persistencia.modelo;

public class CompromisoInicialDetalle {
   
	private Integer idCompromisoInicialDetalle;
    private Integer idCompromisoInicial;   
    private Integer idPartidaUelEspecifica;   
    private Double monto;
    private Integer ff;    
    
    
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