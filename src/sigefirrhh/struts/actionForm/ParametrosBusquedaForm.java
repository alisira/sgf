package sigefirrhh.struts.actionForm;

import org.apache.struts.action.ActionForm;

public class ParametrosBusquedaForm extends ActionForm{    
	
	private static final long serialVersionUID = 7519218840547369255L;
	private String expediente;
	private Integer estatus;
	private int idTipoExpediente;
	private String fechaDesde;
	private String fechaHasta;
	private Integer id_usuario;
	private Integer mes;
	private Integer cate_presu;
	private Integer ff;
	private int partida;
	private String deno_partida;
	private Integer cod_concepto;
	private String deno_concepto;
	private Integer cod_tipo_concepto;	
	private Integer cod_tipo_nomina;
	private Integer uel;
	private Double compromiso;
	private Double causado;
	private Double pagado;
	private Integer ano;
	private Integer forma_pago;
	private Integer idDependencia;
    private String vigente;    
    private String ubicaGeografica;
    private String fechaFin;
    private String fechaCreacion;
    private Integer idTipoDependencia;
    private Integer idUnidadFuncional;
    private Integer idDependenciaAnterior;
    private String denominacion;    
    private Integer idRegion;
	private Integer cod_order_by;
	private String proceso;
	private Integer decision;
	private Integer idOpcion;
	private String tituloApli;
	private String foto;
	private String horaIni;
	private String horaFin;
	private String velocidad;
	
	public String getTituloApli() {
		return tituloApli;
	}
	public void setTituloApli(String tituloApli) {
		this.tituloApli = tituloApli;
	}
	public Integer getIdOpcion() {
		return idOpcion;
	}
	public void setIdOpcion(Integer idOpcion) {
		this.idOpcion = idOpcion;
	}
	public String getExpediente() {
		return expediente;
	}
	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}
	public Integer getEstatus() {
		return estatus;
	}
	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	public Integer getDecision() {
		return decision;
	}
	public void setDecision(Integer decision) {
		this.decision = decision;
	}
	public String getProceso() {
		return proceso;
	}
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getIdTipoExpediente() {
		return idTipoExpediente;
	}
	public void setIdTipoExpediente(int idTipoExpediente) {
		this.idTipoExpediente = idTipoExpediente;
	}
	public Integer getIdDependencia() {
		return idDependencia;
	}
	public void setIdDependencia(Integer idDependencia) {
		this.idDependencia = idDependencia;
	}
	public String getVigente() {
		return vigente;
	}
	public void setVigente(String vigente) {
		this.vigente = vigente;
	}
	public String getUbicaGeografica() {
		return ubicaGeografica;
	}
	public void setUbicaGeografica(String ubicaGeografica) {
		this.ubicaGeografica = ubicaGeografica;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Integer getIdTipoDependencia() {
		return idTipoDependencia;
	}
	public void setIdTipoDependencia(Integer idTipoDependencia) {
		this.idTipoDependencia = idTipoDependencia;
	}
	public Integer getIdUnidadFuncional() {
		return idUnidadFuncional;
	}
	public void setIdUnidadFuncional(Integer idUnidadFuncional) {
		this.idUnidadFuncional = idUnidadFuncional;
	}
	public Integer getIdDependenciaAnterior() {
		return idDependenciaAnterior;
	}
	public void setIdDependenciaAnterior(Integer idDependenciaAnterior) {
		this.idDependenciaAnterior = idDependenciaAnterior;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public Integer getIdRegion() {
		return idRegion;
	}
	public void setIdRegion(Integer idRegion) {
		this.idRegion = idRegion;
	}
	public String getDeno_partida() {
		return deno_partida;
	}
	public void setDeno_partida(String deno_partida) {
		this.deno_partida = deno_partida;
	}
	public String getDeno_concepto() {
		return deno_concepto;
	}
	public void setDeno_concepto(String deno_concepto) {
		this.deno_concepto = deno_concepto;
	}
	public Integer getCod_order_by() {
		return cod_order_by;
	}
	public void setCod_order_by(Integer cod_order_by) {
		this.cod_order_by = cod_order_by;
	}
	public ParametrosBusquedaForm(int partida){
		this.partida = partida;
	}	
	public ParametrosBusquedaForm() {		
	}		

	public Integer getCod_tipo_concepto() {
		return cod_tipo_concepto;
	}
	public void setCod_tipo_concepto(Integer cod_tipo_concepto) {
		this.cod_tipo_concepto = cod_tipo_concepto;
	}
	public Integer getForma_pago() {
		return forma_pago;
	}
	public void setForma_pago(Integer forma_pago) {
		this.forma_pago = forma_pago;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public Integer getUel() {
		return uel;
	}
	public void setUel(Integer uel) {
		this.uel = uel;
	}
	public Double getCompromiso() {
		return compromiso;
	}
	public void setCompromiso(Double compromiso) {
		this.compromiso = compromiso;
	}
	public Double getCausado() {
		return causado;
	}
	public void setCausado(Double causado) {
		this.causado = causado;
	}
	public Double getPagado() {
		return pagado;
	}
	public void setPagado(Double pagado) {
		this.pagado = pagado;
	}
	public Integer getCod_tipo_nomina() {
		return cod_tipo_nomina;
	}
	public void setCod_tipo_nomina(Integer cod_tipo_nomina) {
		this.cod_tipo_nomina = cod_tipo_nomina;
	}
	public Integer getCod_concepto() {
		return cod_concepto;
	}
	public void setCod_concepto(Integer cod_concepto) {
		this.cod_concepto = cod_concepto;
	}
	public Integer getCate_presu() {
		return cate_presu;
	}
	public void setCate_presu(Integer cate_presu) {
		this.cate_presu = cate_presu;
	}
	public Integer getFf() {
		return ff;
	}
	public void setFf(Integer ff) {
		this.ff = ff;
	}
	public int getPartida() {
		return partida;
	}
	public void setPartida(int partida) {
		this.partida = partida;
	}
	public Integer getMes() {
		return mes;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	public Integer getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}	
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	
	public String getFechaHasta() {
		return fechaHasta;
	}
	
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getHoraIni() {
		return horaIni;
	}
	public void setHoraIni(String horaIni) {
		this.horaIni = horaIni;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	public String getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(String velocidad) {
		this.velocidad = velocidad;
	}
	
}
