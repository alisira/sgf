package sigefirrhh.persistencia.modelo;

import java.io.Serializable;
import java.util.Date;

public class Expediente implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8112858268063606114L;		
    
	private Integer expediente; 
	private Integer ano;
	private Integer idUsuario;
	private Integer estatus;
	private Date fechaReg;
    private Integer idOpcion;
    private String observacion;
    
    
    private Integer idOrganismo;

	public Integer getIdOrganismo() {
		return idOrganismo;
	}

	public void setIdOrganismo(Integer idOrganismo) {
		this.idOrganismo = idOrganismo;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Integer getExpediente() {
		return expediente;
	}

	public void setExpediente(Integer expediente) {
		this.expediente = expediente;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public Date getFechaReg() {
		return fechaReg;
	}

	public void setFechaReg(Date fechaReg) {
		this.fechaReg = fechaReg;
	}

	public Integer getIdOpcion() {
		return idOpcion;
	}

	public void setIdOpcion(Integer idOpcion) {
		this.idOpcion = idOpcion;
	}    
	
}