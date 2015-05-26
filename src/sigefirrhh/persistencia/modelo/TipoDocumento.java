package sigefirrhh.persistencia.modelo;

public class TipoDocumento {
	private Integer codigo;
	private String denominacion;
	
	public TipoDocumento(Integer cod, String deno){
		this.codigo= cod;
		this.denominacion= deno;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public Integer Codigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
}
