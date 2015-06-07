package sigefirrhh.persistencia.modelo;

public class Opcion {
   
    private Integer idOpcion;
    private String codigoOpcion;
    private String descripcion;
    private String ruta;    
    private String tipo;
    private String uri;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column opcion.jerarquia
     *
     * @ibatorgenerated Tue Apr 21 15:12:33 VET 2015
     */
    private String jerarquia;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column opcion.id_opcion
     *
     * @return the value of opcion.id_opcion
     *
     * @ibatorgenerated Tue Apr 21 15:12:33 VET 2015
     */
    public Integer getIdOpcion() {
        return idOpcion;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column opcion.id_opcion
     *
     * @param idOpcion the value for opcion.id_opcion
     *
     * @ibatorgenerated Tue Apr 21 15:12:33 VET 2015
     */
    public void setIdOpcion(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column opcion.codigo_opcion
     *
     * @return the value of opcion.codigo_opcion
     *
     * @ibatorgenerated Tue Apr 21 15:12:33 VET 2015
     */
    public String getCodigoOpcion() {
        return codigoOpcion;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column opcion.codigo_opcion
     *
     * @param codigoOpcion the value for opcion.codigo_opcion
     *
     * @ibatorgenerated Tue Apr 21 15:12:33 VET 2015
     */
    public void setCodigoOpcion(String codigoOpcion) {
        this.codigoOpcion = codigoOpcion == null ? null : codigoOpcion.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column opcion.descripcion
     *
     * @return the value of opcion.descripcion
     *
     * @ibatorgenerated Tue Apr 21 15:12:33 VET 2015
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column opcion.descripcion
     *
     * @param descripcion the value for opcion.descripcion
     *
     * @ibatorgenerated Tue Apr 21 15:12:33 VET 2015
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion == null ? null : descripcion.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column opcion.ruta
     *
     * @return the value of opcion.ruta
     *
     * @ibatorgenerated Tue Apr 21 15:12:33 VET 2015
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column opcion.ruta
     *
     * @param ruta the value for opcion.ruta
     *
     * @ibatorgenerated Tue Apr 21 15:12:33 VET 2015
     */
    public void setRuta(String ruta) {
        this.ruta = ruta == null ? null : ruta.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column opcion.tipo
     *
     * @return the value of opcion.tipo
     *
     * @ibatorgenerated Tue Apr 21 15:12:33 VET 2015
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column opcion.tipo
     *
     * @param tipo the value for opcion.tipo
     *
     * @ibatorgenerated Tue Apr 21 15:12:33 VET 2015
     */
    public void setTipo(String tipo) {
        this.tipo = tipo == null ? null : tipo.trim();
    }
    
    public String getJerarquia() {
        return jerarquia;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column opcion.jerarquia
     *
     * @param jerarquia the value for opcion.jerarquia
     *
     * @ibatorgenerated Tue Apr 21 15:12:33 VET 2015
     */
    public void setJerarquia(String jerarquia) {
        this.jerarquia = jerarquia == null ? null : jerarquia.trim();
    }

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}