package sigefirrhh.persistencia.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CriterioBusqueda {
    
    private List<Integer> idCompromisoInicial;
    private List<Integer> idCompromisoInicialDetalle;
    private List<Integer> idOrganismo;   
    private List<Integer> ano;   
    private List<Integer> tarea;
    private List<Integer> estatus;
    private List<Integer> idUnidadAdministradora;
    private List<Integer> idCuentadante;
    private List<Integer> idTipoPago;
    private List<Integer> idTipoDocumento;
    private List<String> documento;
    private List<String> observacion;
    private List<String> origenPresupuestario;
    private List<String> gacetaCredAdicional;
    private List<String> decretoCredAdicional;
    private List<Date> fechaCredAdicional;
    private List<String> gacetaRectificacion;
    private List<String> decretoRectificacion;
    private List<Date> fechaRectificacion;
    private List<Integer> expediente;
    private List<Integer> compromiso;
    private List<Date> fechaRegistro;
    private List<Integer> cedula;
    private List<Integer> codFrecuenPago;    
    private List<Integer> mesesCalcu;
    private List<Integer> semaCalcu;
    private List<Integer> quinceCalcu;
    private List<Integer> idDependencia;
    private List<String> denominacion;
    private List<String> vigente;
    private List<String> codUnidadAdministradora;
    
    private List<Long> idRolOpcion;	
	private List<Long> idRol;
	private List<String> uri;
	private List<String> ruta;
    
	public CriterioBusqueda(){
    	this.codFrecuenPago = new  ArrayList<Integer>();
    	this.idCompromisoInicial= new  ArrayList<Integer>();  
        this.idOrganismo= new ArrayList <Integer> ();   
        this.ano= new ArrayList <Integer> ();   
        this.tarea= new ArrayList <Integer> ();   
        this.estatus= new ArrayList <Integer> ();   
        this.idUnidadAdministradora= new ArrayList <Integer> ();   
        this.idCuentadante= new ArrayList <Integer> ();   
        this.idTipoPago= new ArrayList <Integer> ();   
        this.idTipoDocumento= new ArrayList <Integer> ();   
        this.documento= new  ArrayList<String>();
        this.observacion= new  ArrayList<String>();
        this.origenPresupuestario= new  ArrayList<String>();       
        this.gacetaCredAdicional= new  ArrayList<String>();
        this.decretoCredAdicional= new  ArrayList<String>();
        this.fechaCredAdicional= new  ArrayList<Date>();
        this.gacetaRectificacion= new  ArrayList<String>();
        this.decretoRectificacion= new  ArrayList<String>();
        this.fechaRectificacion= new ArrayList<Date>();
        this.expediente= new ArrayList <Integer> ();   
        this.compromiso= new ArrayList <Integer> ();   
        this.fechaRegistro= new  ArrayList<Date>();
        this.cedula= new ArrayList <Integer> ();        
        this.mesesCalcu= new ArrayList <Integer> ();   
        this.semaCalcu= new ArrayList <Integer> ();   
        this.quinceCalcu= new ArrayList <Integer> ();   
        this.idDependencia= new ArrayList <Integer> ();
        this.denominacion= new  ArrayList<String>();
        this.vigente= new  ArrayList<String>();
        this.codUnidadAdministradora= new  ArrayList<String>();
        this.idRolOpcion= new   ArrayList<Long>();
        this.idRol = new ArrayList<Long>();	
    	this.uri= new  ArrayList<String>();
    	this.idCompromisoInicialDetalle= new ArrayList<Integer>();
    	this.ruta= new  ArrayList<String>();
    }
	
	public List<String> getRuta() {
		return this.ruta;
	}
	public void addRuta(String ruta) {
		this.ruta.add(ruta);
	}
	
	public List<Integer> getIdCompromisoInicialDetalle() {
		return this.idCompromisoInicialDetalle;
	}
	public void addCodUnidadAdministradora(Integer idCompromisoInicialDetalle) {
		this.idCompromisoInicialDetalle.add(idCompromisoInicialDetalle);
	}
	
	public List<String> getCodUnidadAdministradora() {
		return codUnidadAdministradora;
	}
	public void addCodUnidadAdministradora(String codUnidadAdministradora) {
		this.codUnidadAdministradora.add(codUnidadAdministradora);
	}
	
	public List<String> getVigente() {
		return vigente;
	}
	public void addVigente(String vigente) {
		this.vigente.add(vigente);
	}
	
	public List<String> getDenominacion() {
		return denominacion;
	}
	public void addDenominacion(String denominacion) {
		this.denominacion.add(denominacion);
	}

	public List<Integer> getCodFrecuenPago() {
		return codFrecuenPago;
	}	
	public void addCodFrecuenPago(Integer codFrecuenPago) {
		this.codFrecuenPago.add(codFrecuenPago);
	}
	
	public List<Integer> getIdCompromisoInicial() {
		return idCompromisoInicial;
	}
	public void addIdCompromisoInicial(Integer idCompromisoInicial) {
		this.idCompromisoInicial.add(idCompromisoInicial);
	}
	
	public List<Integer> getIdOrganismo() {
		return idOrganismo;
	}
	public void addIdOrganismo(Integer idOrganismo ) {
		this.idOrganismo.add(idOrganismo);
	}
	
	public List<Integer> getAno() {
		return ano;
	}
	public void addAno(Integer ano ) {
		this.ano.add(ano);
	}
	
	public List<Integer> getTarea() {
		return tarea;
	}
	public void addTarea(Integer tarea ) {
		this.tarea.add(tarea);
	}
	
	public List<Integer> getEstatus() {
		return estatus;
	}
	public void addEstatus(Integer estatus ) {
		this.estatus.add(estatus);
	}
	
	public List<Integer> getIdUnidadAdministradora() {
		return idUnidadAdministradora;
	}
	public void addIdUnidadAdministradora(Integer idUnidadAdministradora) {
		this.idUnidadAdministradora.add(idUnidadAdministradora);
	}
	
	public List<Integer> getIdCuentadante() {
		return idCuentadante;
	}
	public void addIdCuentadante(Integer idCuentadante ) {
		this.idCuentadante.add(idCuentadante);
	}
	
	public List<Integer> getIdTipoPago() {
		return idTipoPago;
	}
	public void addIdTipoPago(Integer idTipoPago) {
		this.idTipoPago.add(idTipoPago);
	}
	
	public List<Integer> getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void addIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento.add(idTipoDocumento);
	}
	
	public List<String> getDocumento() {
		return documento;
	}
	public void addDocumento(String documento ) {
		this.documento.add(documento);
	}
	
	public List<String> getObservacion() {
		return observacion;
	}
	public void addObservacion(String observacion) {
		this.observacion.add(observacion);
	}
	
	public List<String> getOrigenPresupuestario() {
		return origenPresupuestario;
	}
	public void addOrigenPresupuestario(String origenPresupuestario ) {
		this.origenPresupuestario.add(origenPresupuestario);
	}
	
	public List<String> getGacetaCredAdicional() {
		return gacetaCredAdicional;
	}
	public void addGacetaCredAdicional(String gacetaCredAdicional) {
		this.gacetaCredAdicional.add(gacetaCredAdicional);
	}
	
	public List<String> getDecretoCredAdicional() {
		return decretoCredAdicional;
	}
	public void addDecretoCredAdicional(String decretoCredAdicional) {
		this.decretoCredAdicional.add(decretoCredAdicional);
	}
	
	public List<Date> getFechaCredAdicional() {
		return fechaCredAdicional;
	}
	public void addFechaCredAdicional(Date fechaCredAdicional ) {
		this.fechaCredAdicional.add(fechaCredAdicional);
	}
	
	public List<String> getGacetaRectificacion() {
		return gacetaRectificacion;
	}
	public void addGacetaRectificacion(String GacetaRectificacion) {
		this.gacetaRectificacion.add(GacetaRectificacion);
	}
	
	public List<String> getDecretoRectificacion() {
		return decretoRectificacion;
	}
	public void addDecretoRectificacion(String DecretoRectificacion) {
		this.decretoRectificacion.add(DecretoRectificacion);
	}
	
	public List<Date> getFechaRectificacion() {
		return fechaRectificacion;
	}
	public void addFechaRectificacion(Date FechaRectificacion) {
		this.fechaRectificacion.add(FechaRectificacion);
	}
	
	public List<Integer> getExpediente() {
		return expediente;
	}
	public void addExpediente(Integer Expediente) {
		this.expediente.add(Expediente);
	}
	
	public List<Integer> getCompromiso() {
		return compromiso;
	}
	public void addCompromiso(Integer Compromiso ) {
		this.compromiso.add(Compromiso);
	}
	
	public List<Date> getFechaRegistro() {
		return fechaRegistro;
	}
	public void addFechaRegistro(Date FechaRegistro ) {
		this.fechaRegistro.add(FechaRegistro);
	}
	
	public List<Integer> getCedula() {
		return cedula;
	}
	public void addCedula(Integer Cedula ) {
		this.cedula.add(Cedula);
	}
	
	public List<Integer> getMesesCalcu() {
		return mesesCalcu;
	}
	public void addMesesCalcu(Integer MesesCalcu ) {
		this.mesesCalcu.add(MesesCalcu);
	}
	
	public List<Integer> getSemaCalcu() {
		return semaCalcu;
	}
	public void addSemaCalcu(Integer SemaCalcu) {
		this.semaCalcu.add(SemaCalcu);
	}
	
	public List<Integer> getQuinceCalcu() {
		return quinceCalcu;
	}
	public void addQuinceCalcu(Integer QuinceCalcu) {
		this.quinceCalcu.add(QuinceCalcu);
	}
	
	public List<Integer> getIdDependencia() {
		return idDependencia;
	}
	public void addIdDependencia(Integer IdDependencia ) {
		this.idDependencia.add(IdDependencia);
	}


	public List<Long> getIdRolOpcion() {
		return idRolOpcion;
	}


	public void addIdRolOpcion(Long idRolOpcion) {
		this.idRolOpcion.add(idRolOpcion);
	}


	public List<Long> getIdRol() {
		return idRol;
	}


	public void addIdRol(Long idRol) {
		this.idRol.add(idRol);
	}


	public List<String> getUri() {
		return uri;
	}
	public void addUri(String uri) {
		this.uri.add(uri);
	}
    
}