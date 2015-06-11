package sigefirrhh.struts.actionForm;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RegularizacionCompromisoForm extends ActionForm{

	private static final long serialVersionUID = -5087744671381475045L;

    private Integer idCompromisoInicial;
    private Integer idRegularizacionCompromisoInicial;
    private Long expediente;
    private Long compromiso;
    private Integer ano;
    private Integer tarea;
    private Integer estatus;
    private Integer idTipoDocu;
    private Integer idTipoFondo;
    private String documento;
    private String observacion;
    private String gacetaCredAdicional;
    private String decretoCredAdicional;
    private Date fechaCredAdicional;
    private String gacetaRectificacion;
    private String decretoRectificacion;
    private Date fechaRectificacion;
    private Date fechaRegistro;
    private String origenPresupuestario;
    private String tituloApli;
    
    //Detalle
    private Integer ff[];
    private Integer codCatePresu[];
    private Integer codUel[];
    private String denoUel[];
    private String partida[];
    private String denoPartida[];
    private Double dispo[];
    private Double monto[];    

    
    public String getTituloApli() {
		return tituloApli;
	}

	public void setTituloApli(String tituloApli) {
		this.tituloApli = tituloApli;
	}

	public Integer getIdTipoDocu() {
		return idTipoDocu;
	}

	public void setIdTipoDocu(Integer idTipoDocu) {
		this.idTipoDocu = idTipoDocu;
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

	public void setIdTipoFondo(Integer idTipoFondo) {
		this.idTipoFondo = idTipoFondo;
	}

	public Integer getIdTipoFondo() {
		return idTipoFondo;
	}

	public Integer getIdCompromisoInicial() {
        return idCompromisoInicial;
    }

    public void setIdCompromisoInicial(Integer idCompromisoInicial) {
        this.idCompromisoInicial = idCompromisoInicial;
    }

    public Integer getIdRegularizacionCompromisoInicial() {
        return idRegularizacionCompromisoInicial;
    }

    public void setIdRegularizacionCompromisoInicial(Integer idRegularizacionCompromisoInicial) {
        this.idRegularizacionCompromisoInicial = idRegularizacionCompromisoInicial;
    }

    public Long getExpediente() {
        return expediente;
    }

    public void setExpediente(Long expediente) {
        this.expediente = expediente;
    }
    
    public Long getCompromiso() {
        return compromiso;
    }

    public void setCompromiso(Long compromiso) {
        this.compromiso = compromiso;
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

  
    public String getDocumento() {
        return documento;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column regularizacioncompromiso.documento
     *
     * @param documento the value for regularizacioncompromiso.documento
     *
     * @ibatorgenerated Fri May 29 15:27:21 VET 2015
     */
    public void setDocumento(String documento) {
        this.documento = documento == null ? null : documento.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column regularizacioncompromiso.observacion
     *
     * @return the value of regularizacioncompromiso.observacion
     *
     * @ibatorgenerated Fri May 29 15:27:21 VET 2015
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column regularizacioncompromiso.observacion
     *
     * @param observacion the value for regularizacioncompromiso.observacion
     *
     * @ibatorgenerated Fri May 29 15:27:21 VET 2015
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion == null ? null : observacion.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column regularizacioncompromiso.gaceta_cred_adicional
     *
     * @return the value of regularizacioncompromiso.gaceta_cred_adicional
     *
     * @ibatorgenerated Fri May 29 15:27:21 VET 2015
     */
    public String getGacetaCredAdicional() {
        return gacetaCredAdicional;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column regularizacioncompromiso.gaceta_cred_adicional
     *
     * @param gacetaCredAdicional the value for regularizacioncompromiso.gaceta_cred_adicional
     *
     * @ibatorgenerated Fri May 29 15:27:21 VET 2015
     */
    public void setGacetaCredAdicional(String gacetaCredAdicional) {
        this.gacetaCredAdicional = gacetaCredAdicional == null ? null : gacetaCredAdicional.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column regularizacioncompromiso.decreto_cred_adicional
     *
     * @return the value of regularizacioncompromiso.decreto_cred_adicional
     *
     * @ibatorgenerated Fri May 29 15:27:21 VET 2015
     */
    public String getDecretoCredAdicional() {
        return decretoCredAdicional;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column regularizacioncompromiso.decreto_cred_adicional
     *
     * @param decretoCredAdicional the value for regularizacioncompromiso.decreto_cred_adicional
     *
     * @ibatorgenerated Fri May 29 15:27:21 VET 2015
     */
    public void setDecretoCredAdicional(String decretoCredAdicional) {
        this.decretoCredAdicional = decretoCredAdicional == null ? null : decretoCredAdicional.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column regularizacioncompromiso.fecha_cred_adicional
     *
     * @return the value of regularizacioncompromiso.fecha_cred_adicional
     *
     * @ibatorgenerated Fri May 29 15:27:21 VET 2015
     */
    public Date getFechaCredAdicional() {
        return fechaCredAdicional;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column regularizacioncompromiso.fecha_cred_adicional
     *
     * @param fechaCredAdicional the value for regularizacioncompromiso.fecha_cred_adicional
     *
     * @ibatorgenerated Fri May 29 15:27:21 VET 2015
     */
    public void setFechaCredAdicional(Date fechaCredAdicional) {
        this.fechaCredAdicional = fechaCredAdicional;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column regularizacioncompromiso.gaceta_rectificacion
     *
     * @return the value of regularizacioncompromiso.gaceta_rectificacion
     *
     * @ibatorgenerated Fri May 29 15:27:21 VET 2015
     */
    public String getGacetaRectificacion() {
        return gacetaRectificacion;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column regularizacioncompromiso.gaceta_rectificacion
     *
     * @param gacetaRectificacion the value for regularizacioncompromiso.gaceta_rectificacion
     *
     * @ibatorgenerated Fri May 29 15:27:21 VET 2015
     */
    public void setGacetaRectificacion(String gacetaRectificacion) {
        this.gacetaRectificacion = gacetaRectificacion == null ? null : gacetaRectificacion.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column regularizacioncompromiso.decreto_rectificacion
     *
     * @return the value of regularizacioncompromiso.decreto_rectificacion
     *
     * @ibatorgenerated Fri May 29 15:27:21 VET 2015
     */
    public String getDecretoRectificacion() {
        return decretoRectificacion;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column regularizacioncompromiso.decreto_rectificacion
     *
     * @param decretoRectificacion the value for regularizacioncompromiso.decreto_rectificacion
     *
     * @ibatorgenerated Fri May 29 15:27:21 VET 2015
     */
    public void setDecretoRectificacion(String decretoRectificacion) {
        this.decretoRectificacion = decretoRectificacion == null ? null : decretoRectificacion.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column regularizacioncompromiso.fecha_rectificacion
     *
     * @return the value of regularizacioncompromiso.fecha_rectificacion
     *
     * @ibatorgenerated Fri May 29 15:27:21 VET 2015
     */
    public Date getFechaRectificacion() {
        return fechaRectificacion;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column regularizacioncompromiso.fecha_rectificacion
     *
     * @param fechaRectificacion the value for regularizacioncompromiso.fecha_rectificacion
     *
     * @ibatorgenerated Fri May 29 15:27:21 VET 2015
     */
    public void setFechaRectificacion(Date fechaRectificacion) {
        this.fechaRectificacion = fechaRectificacion;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column regularizacioncompromiso.fecha_registro
     *
     * @return the value of regularizacioncompromiso.fecha_registro
     *
     * @ibatorgenerated Fri May 29 15:27:21 VET 2015
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column regularizacioncompromiso.fecha_registro
     *
     * @param fechaRegistro the value for regularizacioncompromiso.fecha_registro
     *
     * @ibatorgenerated Fri May 29 15:27:21 VET 2015
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column regularizacioncompromiso.origen_presupuestario
     *
     * @return the value of regularizacioncompromiso.origen_presupuestario
     *
     * @ibatorgenerated Fri May 29 15:27:21 VET 2015
     */
    public String getOrigenPresupuestario() {
        return origenPresupuestario;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column regularizacioncompromiso.origen_presupuestario
     *
     * @param origenPresupuestario the value for regularizacioncompromiso.origen_presupuestario
     *
     * @ibatorgenerated Fri May 29 15:27:21 VET 2015
     */
    public void setOrigenPresupuestario(String origenPresupuestario) {
        this.origenPresupuestario = origenPresupuestario == null ? null : origenPresupuestario.trim();
    }
	
    public void reset(ActionMapping map, HttpServletRequest req){
        this.idCompromisoInicial=null;    
        this.idRegularizacionCompromisoInicial=null;    
        this.expediente=null;
        this.compromiso=null;
        this.ano=null;
        this.tarea=null;
        this.estatus=null;
        this.idTipoDocu=null;
        this.documento=null;
        this.observacion=null;
        this.gacetaCredAdicional=null;
        this.decretoCredAdicional=null;
        this.fechaCredAdicional=null;
        this.gacetaRectificacion=null;
        this.decretoRectificacion=null;
        this.fechaRectificacion=null;
        this.fechaRegistro=null;
        this.origenPresupuestario=null;
    }
    
}