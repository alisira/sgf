package sigefirrhh.struts.actionForm;

import org.apache.struts.action.ActionForm;

public class RegularizacionCompromisoForm extends ActionForm{    
	
	private static final long serialVersionUID = -7735259961764648388L;
	
	private Integer idCompromisoInicial;    
	private Integer idOrganismo;
	private Integer ano;  
    private Integer tarea;  
    private Integer estatus;  
    private Integer idUnidadAdministradora;    
    private Integer idCuentadante;
    private Integer idTipoPago;
    private Integer idTipoDocumento;
    private String documento;
    private String observacion;  
    private Integer oriPresu;
    private String gaceCrediAdi;
    private String decreCrediAdi; 
    private String fechaGaceCredi;
    private String gaceRecti;  
    private String decreRecti;  
    private String fechaGaceRecti;
    private Integer compromiso;
    //private Date fechaRegistro;
	private Integer expediente;
    private Integer idUsuario;        
    private Integer mes;
    private Integer idTipoNomina;
    private Integer idTipoFondo;
    private String codFrecuenPago;	
    
    //Detalle
    private Integer ff[];
    private Integer codCatePresu[];
    private Integer codUel[];
    private String denoUel[];    
    private String partida[];
    private String denoPartida[];
    private Double monto[];
    private Double totalResumen;
    
    
    
	public String getFechaGaceRecti() {
		return fechaGaceRecti;
	}
	public void setFechaGaceRecti(String fechaGaceRecti) {
		this.fechaGaceRecti = fechaGaceRecti;
	}
	public String getFechaGaceCredi() {
		return fechaGaceCredi;
	}
	public void setFechaGaceCredi(String fechaGaceCredi) {
		this.fechaGaceCredi = fechaGaceCredi;
	}
	public Integer getOriPresu() {
		return oriPresu;
	}
	public void setOriPresu(Integer oriPresu) {
		this.oriPresu = oriPresu;
	}
	public String getGaceCrediAdi() {
		return gaceCrediAdi;
	}
	public void setGaceCrediAdi(String gaceCrediAdi) {
		this.gaceCrediAdi = gaceCrediAdi;
	}
	public String getDecreCrediAdi() {
		return decreCrediAdi;
	}
	public void setDecreCrediAdi(String decreCrediAdi) {
		this.decreCrediAdi = decreCrediAdi;
	}
	public String getGaceRecti() {
		return gaceRecti;
	}
	public void setGaceRecti(String gaceRecti) {
		this.gaceRecti = gaceRecti;
	}
	public String getDecreRecti() {
		return decreRecti;
	}
	public void setDecreRecti(String decreRecti) {
		this.decreRecti = decreRecti;
	}
	public Integer getIdCompromisoInicial() {
		return idCompromisoInicial;
	}
	public void setIdCompromisoInicial(Integer idCompromisoInicial) {
		this.idCompromisoInicial = idCompromisoInicial;
	}
	public Integer getIdOrganismo() {
		return idOrganismo;
	}
	public void setIdOrganismo(Integer idOrganismo) {
		this.idOrganismo = idOrganismo;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public Integer getTarea() {
		return tarea;
	}
	public void setTarea(Integer tarea) {
		this.tarea = tarea;
	}
	public Integer getEstatus() {
		return estatus;
	}
	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	public Integer getIdUnidadAdministradora() {
		return idUnidadAdministradora;
	}
	public void setIdUnidadAdministradora(Integer idUnidadAdministradora) {
		this.idUnidadAdministradora = idUnidadAdministradora;
	}
	public Integer getIdCuentadante() {
		return idCuentadante;
	}
	public void setIdCuentadante(Integer idCuentadante) {
		this.idCuentadante = idCuentadante;
	}
	public Integer getIdTipoPago() {
		return idTipoPago;
	}
	public void setIdTipoPago(Integer idTipoPago) {
		this.idTipoPago = idTipoPago;
	}
	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}	
	public Integer getCompromiso() {
		return compromiso;
	}
	public void setCompromiso(Integer compromiso) {
		this.compromiso = compromiso;
	}	
	public Integer getExpediente() {
		return expediente;
	}
	public void setExpediente(Integer expediente) {
		this.expediente = expediente;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getMes() {
		return mes;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	
	public Integer getIdTipoNomina() {
		return idTipoNomina;
	}
	public void setIdTipoNomina(Integer idTipoNomina) {
		this.idTipoNomina = idTipoNomina;
	}
	public Integer getIdTipoFondo() {
		return idTipoFondo;
	}
	public void setIdTipoFondo(Integer idTipoFondo) {
		this.idTipoFondo = idTipoFondo;
	}
	public Integer[] getFf() {
		return ff;
	}
	public void setFf(Integer[] ff) {
		this.ff = ff;
	}
	public Integer[] getCodCatePresu() {
		return codCatePresu;
	}
	public void setCodCatePresu(Integer[] codCatePresu) {
		this.codCatePresu = codCatePresu;
	}
	public Integer[] getCodUel() {
		return codUel;
	}
	public void setCodUel(Integer[] codUel) {
		this.codUel = codUel;
	}
	public String[] getDenoUel() {
		return denoUel;
	}
	public void setDenoUel(String[] denoUel) {
		this.denoUel = denoUel;
	}
	public String[] getPartida() {
		return partida;
	}
	public void setPartida(String[] partida) {
		this.partida = partida;
	}
	public String[] getDenoPartida() {
		return denoPartida;
	}
	public void setDenoPartida(String[] denoPartida) {
		this.denoPartida = denoPartida;
	}
	public Double[] getMonto() {
		return monto;
	}
	public void setMonto(Double[] monto) {
		this.monto = monto;
	}
	public Double getTotalResumen() {
		return totalResumen;
	}
	public void setTotalResumen(Double totalResumen) {
		this.totalResumen = totalResumen;
	}
	public String getCodFrecuenPago() {
		return codFrecuenPago;
	}
	public void setCodFrecuenPago(String codFrecuenPago) {
		this.codFrecuenPago = codFrecuenPago;
	}
	
	
    
}