package sigefirrhh.struts.actionForm;

import org.apache.struts.action.ActionForm;


public class TipoDocumentoFormX extends ActionForm{
	
	private static final long serialVersionUID = 6412466995638803063L;
	private Integer odigo[];
	private String denominacion[];
	
	public TipoDocumentoFormX(){
		this.odigo[0]= 0;
		this.denominacion[0]= "Resumen de Nomina";
		this.odigo[1]= 1;
		this.denominacion[1]= "Memorandum";
		this.odigo[2]= 2;
		this.denominacion[20]= "Oficio";
		
	}

	public Integer[] getCodigo() {
		return odigo;
	}

	public void setCodigo(Integer[] codigo) {
		this.odigo = codigo;
	}

	public String[] getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String[] denominacion) {
		this.denominacion = denominacion;
	}
	
}
