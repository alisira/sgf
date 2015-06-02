package sigefirrhh.struts.actionForm;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import sigefirrhh.persistencia.modelo.CompromisoInicialDetalle;

public class CompromisoInicialForm extends ActionForm{
	
	private static final long serialVersionUID = -7735259961764648388L;
	
	private Integer idCompromisoInicial;
	private Integer idOrganismo;
	private Integer ano;
    private Integer tarea;  
    private Integer estatus;  
    private Integer idUnidadAdministradora;
    private Integer codUnidadAdministradora;
    private String denoUniAdmi;
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
    private String denoTipoFondo;
    private String codFrecuenPago;
   

    //Detalle
    private Integer ff[];
    private Integer codCatePresu[];
    private Integer codUel[];
    private String denoUel[];
    private String partida[];
    private String denoPartida[];
    private Double dispo[];
    private Double monto[];
    private Double totalResumen;


	public CompromisoInicialForm(Integer tamaño) {
		this.ff = new Integer [tamaño];
		this.codCatePresu = new Integer [tamaño];
		this.codUel = new Integer [tamaño];
		this.denoUel = new String [tamaño];    
		this.partida = new String [tamaño];
		this.denoPartida = new String [tamaño];
		this.dispo = new Double [tamaño];
		this.monto = new Double [tamaño];

	}
	
	public CompromisoInicialForm() {
		super();
	}

	public void setFf(Integer ff, Integer indice) {
		this.ff[indice] = ff;
	}
	public void setCodCatePresu(Integer codCatePresu, Integer indice) {		
		this.codCatePresu[indice] = codCatePresu;
	}
	public void setCodUel(Integer codUel, Integer indice) {		
		this.codUel[indice] = codUel;		
	}
	public void setDenoUel (String valor, Integer indice) {		
		this.denoUel [indice] = valor;		
	}
	public void setPartida(String valor, Integer indice) {		
		this.partida[indice] = valor;		
	}
	public void setDenoPartida(String valor, Integer indice) {		
		this.denoPartida[indice] = valor;		
	}
	public void setDispo(Double valor, Integer indice) {		
		this.dispo[indice] = valor;		
	}
	public void setMonto(Double valor, Integer indice) {		
		this.monto[indice] = valor;		
	}

	
	
	public Integer getCodUnidadAdministradora() {
		return codUnidadAdministradora;
	}

	public void setCodUnidadAdministradora(Integer codUnidadAdministradora) {
		this.codUnidadAdministradora = codUnidadAdministradora;
	}

	public String getDenoTipoFondo() {
		return denoTipoFondo;
	}

	public void setDenoTipoFondo(String denoTipoFondo) {
		this.denoTipoFondo = denoTipoFondo;
	}

	public String getDenoUniAdmi() {
		return denoUniAdmi;
	}

	public void setDenoUniAdmi(String denoUniAdmi) {
		this.denoUniAdmi = denoUniAdmi;
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

	public String getFechaGaceCredi() {
		return fechaGaceCredi;
	}

	public void setFechaGaceCredi(String fechaGaceCredi) {
		this.fechaGaceCredi = fechaGaceCredi;
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

	public String getFechaGaceRecti() {
		return fechaGaceRecti;
	}

	public void setFechaGaceRecti(String fechaGaceRecti) {
		this.fechaGaceRecti = fechaGaceRecti;
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

	public String getCodFrecuenPago() {
		return codFrecuenPago;
	}

	public void setCodFrecuenPago(String codFrecuenPago) {
		this.codFrecuenPago = codFrecuenPago;
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

	public Double[] getDispo() {
		return dispo;
	}

	public void setDispo(Double[] dispo) {
		this.dispo = dispo;
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
		
	
    
}