package sigefirrhh.persistencia.modelo;

import java.io.Serializable;
import java.util.Date;

public class CompromisoInicial extends ModeloUtil implements Serializable  {  

    private static final long serialVersionUID = -4889076316115181467L;
    
    private Integer idCompromisoInicial;    
    private Integer idOrganismo;    
    private Integer ano;
    private Integer tarea;
    private Integer estatus;
    private Integer idUnidadAdministradora;
    private Integer idCuentadante;
    private Integer idTipoPago;  
    private Integer idTipoFondo;
    private Integer idTipoDocumento;
    private String documento;
    private String observacion;
    private Integer oriPresu;
    private String gaceCrediAdi;
    private String decreCrediAdi;
    private Date fechaGaceCredi;
    private String gaceRecti;
    private String decreRecti;
    private Date fechaGaceRecti;
    private Integer expediente;
    private Integer compromiso;
    private Date fechaRegistro;
    
      
    public CompromisoInicial(Integer idCompromisoInicial, Integer idOrganismo, Integer ano,
    		Integer tarea,
    		Integer estatus,
    		Integer idUnidadAdministradora,
    		Integer idCuentadante,
    		Integer idTipoPago,
    		Integer idTipoDocumento,
    		String documento,
    		String observacion,
    		Integer origenPresupuestario,
    		String gacetaCredAdicional,
    		String decretoCredAdicional,
    		Date fechaCredAdicional,
    		String gacetaRectificacion,
    		String decretoRectificacion,
    		Date fechaRectificacion,
    		Integer expediente,
    		Integer compromiso,
    		Date fechaRegistro){

    	this.idCompromisoInicial= idCompromisoInicial;	    
    	this.idOrganismo=idOrganismo ;	    
    	this.ano=ano ;	    
    	this.tarea= tarea;	    
    	this.estatus= estatus;	    
    	this.idUnidadAdministradora= idUnidadAdministradora;	    
    	this.idCuentadante= idCuentadante;	    
    	this.idTipoPago=idTipoPago ;	    
    	this.idTipoDocumento=idTipoDocumento ;	    
    	this.documento=documento;	    
    	this.observacion= observacion;	    
    	this.oriPresu=origenPresupuestario ;	    
    	this.gaceCrediAdi=gacetaCredAdicional ;	    
    	this.decreCrediAdi=decretoCredAdicional ;	    
    	this.fechaGaceCredi=fechaCredAdicional ;	    
    	this.gaceRecti= gacetaRectificacion;	   
    	this.decreRecti= decretoRectificacion;	    
    	this.fechaGaceRecti=fechaRectificacion ;	    
    	this.expediente= expediente;	    
    	this.compromiso= compromiso;	    
    	this.fechaRegistro=fechaRegistro ;
    }
		
	public CompromisoInicial(){	
	}
	
    public Integer getIdTipoFondo() {
		return idTipoFondo;
	}

	public void setIdTipoFondo(Integer idTipoFondo) {
		this.idTipoFondo = idTipoFondo;
	}

	public Integer getOriPresu() {
		return oriPresu;
	}

	public void setOriPresu(Integer oriPresu) {
		this.oriPresu = oriPresu;
	}

	public Integer getIdCompromisoInicial() {
        return idCompromisoInicial;
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

	public Integer getCompromiso() {
		return compromiso;
	}

	public void setCompromiso(Integer compromiso) {
		this.compromiso = compromiso;
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
        this.documento = documento == null ? null : documento.trim();
    }
    public Date getFechaGaceCredi() {
		return fechaGaceCredi;
	}

	public void setFechaGaceCredi(Date fechaGaceCredi) {
		this.fechaGaceCredi = fechaGaceCredi;
	}

	public Date getFechaGaceRecti() {
		return fechaGaceRecti;
	}

	public void setFechaGaceRecti(Date fechaGaceRecti) {
		this.fechaGaceRecti = fechaGaceRecti;
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
	
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}