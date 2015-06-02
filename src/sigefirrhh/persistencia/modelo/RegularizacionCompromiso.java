package sigefirrhh.persistencia.modelo;

import java.io.Serializable;
import java.util.Date;

public class RegularizacionCompromiso extends ModeloUtil implements Serializable  {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6510162406631538908L;
	private Integer idCompromisoInicial;
    private Integer idRegularizacionCompromisoInicial;
    private Integer expediente;
    private Integer compromiso;
    private Integer idOrganismo;    
    private Integer ano;
    private Integer tarea;
    private Integer estatus;
    private Integer idTipoDocu;
    private String documento;
    private String observacion;
    private String gaceCrediAdi;
    private String decreCrediAdi;
    private Date fechaGaceCredi;
    private String gaceRecti;
    private String decreRecti;
    private Date fechaGaceRecti;    
    private Date fechaRegistro;
    private Integer oriPresu;    
    
	public Integer getIdCompromisoInicial() {
		return idCompromisoInicial;
	}
	public void setIdCompromisoInicial(Integer idCompromisoInicial) {
		this.idCompromisoInicial = idCompromisoInicial;
	}
	public Integer getIdRegularizacionCompromisoInicial() {
		return idRegularizacionCompromisoInicial;
	}
	public void setIdRegularizacionCompromisoInicial(
			Integer idRegularizacionCompromisoInicial) {
		this.idRegularizacionCompromisoInicial = idRegularizacionCompromisoInicial;
	}
	public Integer getExpediente() {
		return expediente;
	}
	public void setExpediente(Integer expediente) {
		this.expediente = expediente;
	}
	public Integer getCompromiso() {
		return compromiso;
	}
	public void setCompromiso(Integer compromiso) {
		this.compromiso = compromiso;
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
	public Integer getIdTipoDocu() {
		return idTipoDocu;
	}
	public void setIdTipoDocu(Integer idTipoDocu) {
		this.idTipoDocu = idTipoDocu;
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
	public Date getFechaGaceCredi() {
		return fechaGaceCredi;
	}
	public void setFechaGaceCredi(Date fechaGaceCredi) {
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
	public Date getFechaGaceRecti() {
		return fechaGaceRecti;
	}
	public void setFechaGaceRecti(Date fechaGaceRecti) {
		this.fechaGaceRecti = fechaGaceRecti;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Integer getOriPresu() {
		return oriPresu;
	}
	public void setOriPresu(Integer oriPresu) {
		this.oriPresu = oriPresu;
	}
    
    
}