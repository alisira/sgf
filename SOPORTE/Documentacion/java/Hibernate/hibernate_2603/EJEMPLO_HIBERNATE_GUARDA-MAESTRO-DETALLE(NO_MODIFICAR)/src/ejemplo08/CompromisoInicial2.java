/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo08;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="compromisoinicial")
public class CompromisoInicial2 implements Serializable  {  
    /**
	 * 
	 */
	private static final long serialVersionUID = -4889076316115181467L;

	@Id
    @Column(name="id_compromiso_inicial")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="compromisoinicial_id_compromiso_inicial_seq")
    private int idCompromisoInicial;

    @Column(name="id_organismo")
    private Integer idOrganismo;

    @Column(name="ano")
    private Integer ano;

    @Column(name="tarea")
    private Integer tarea;

    @Column(name="estatus")
    private Integer estatus;

    @Column(name="id_unidad_administradora")
    private Integer idUnidadAdministradora;

    @Column(name="id_cuentadante")
    private Integer idCuentadante;

    @Column(name="id_tipo_pago")
    private Integer idTipoPago;

    @Column(name="id_tipo_documento")
    private Integer idTipoDocumento;

    @Column(name="documento")
    private String documento;

    @Column(name="observacion")
    private String observacion;

    @Column(name="origen_presupuestario")
    private String origenPresupuestario;

    @Column(name="gaceta_cred_adicional")
    private String gacetaCredAdicional;

    @Column(name="decreto_cred_adicional")
    private String decretoCredAdicional;

    @Column(name="fecha_cred_adicional")
    private Date fechaCredAdicional;

    @Column(name="gaceta_rectificacion")
    private String gacetaRectificacion;

    @Column(name="decreto_rectificacion")
    private String decretoRectificacion;

    @Column(name="fecha_rectificacion")
    private Date fechaRectificacion;

    @Column(name="expediente")
    private Integer expediente;

    @Column(name="compromiso")
    private Integer compromiso;

    @Column(name="fecha_registro")
    private Date fechaRegistro;
    
    @OneToMany(mappedBy="compromisoInicial",cascade= CascadeType.ALL)
    //@JoinColumn(name="id_compromiso_inicial")
    private Set<CompromisoInicialDetalle2> compromisoInicialDetalle;    
    
    public CompromisoInicial2(Integer idCompromisoInicial, Integer idOrganismo, Integer ano,
    		Integer tarea,
    		Integer estatus,
    		Integer idUnidadAdministradora,
    		Integer idCuentadante,
    		Integer idTipoPago,
    		Integer idTipoDocumento,
    		String documento,
    		String observacion,
    		String origenPresupuestario,
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
    	this.origenPresupuestario=origenPresupuestario ;	    
    	this.gacetaCredAdicional=gacetaCredAdicional ;	    
    	this.decretoCredAdicional=decretoCredAdicional ;	    
    	this.fechaCredAdicional=fechaCredAdicional ;	    
    	this.gacetaRectificacion= gacetaRectificacion;	   
    	this.decretoRectificacion= decretoRectificacion;	    
    	this.fechaRectificacion=fechaRectificacion ;	    
    	this.expediente= expediente;	    
    	this.compromiso= compromiso;	    
    	this.fechaRegistro=fechaRegistro ;
    }
	
	public CompromisoInicial2(){
		
	}

    public int getIdCompromisoInicial() {
        return idCompromisoInicial;
    }

    public void setIdCompromisoInicial(int idCompromisoInicial) {
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

    public String getOrigenPresupuestario() {
        return origenPresupuestario;
    }

    public void setOrigenPresupuestario(String origenPresupuestario) {
        this.origenPresupuestario = origenPresupuestario;
    }

    public String getGacetaCredAdicional() {
        return gacetaCredAdicional;
    }

    public void setGacetaCredAdicional(String gacetaCredAdicional) {
        this.gacetaCredAdicional = gacetaCredAdicional;
    }

    public String getDecretoCredAdicional() {
        return decretoCredAdicional;
    }

    public void setDecretoCredAdicional(String decretoCredAdicional) {
        this.decretoCredAdicional = decretoCredAdicional;
    }

    public Date getFechaCredAdicional() {
        return fechaCredAdicional;
    }

    public void setFechaCredAdicional(Date fechaCredAdicional) {
        this.fechaCredAdicional = fechaCredAdicional;
    }

    public String getGacetaRectificacion() {
        return gacetaRectificacion;
    }

    public void setGacetaRectificacion(String gacetaRectificacion) {
        this.gacetaRectificacion = gacetaRectificacion;
    }

    public String getDecretoRectificacion() {
        return decretoRectificacion;
    }

    public void setDecretoRectificacion(String decretoRectificacion) {
        this.decretoRectificacion = decretoRectificacion;
    }

    public Date getFechaRectificacion() {
        return fechaRectificacion;
    }

    public void setFechaRectificacion(Date fechaRectificacion) {
        this.fechaRectificacion = fechaRectificacion;
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Set<CompromisoInicialDetalle2> getCompromisoInicialDetalle() {
        return compromisoInicialDetalle;
    }

    public void setCompromisoInicialDetalle(Set<CompromisoInicialDetalle2> compromisoInicialDetalle) {
        this.compromisoInicialDetalle = compromisoInicialDetalle;
    }
	
    
   
}