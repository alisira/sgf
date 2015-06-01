package sigefirrhh.persistencia.modelo;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import sigefirrhh.persistencia.modelo.TipoDocumento;

public class Td extends ActionForm{	

	private static final long serialVersionUID = 8286784040425545382L;
	List<TipoDocumento> tipoDocumento = new ArrayList<TipoDocumento>();
	
	public Td(){			
		this.tipoDocumento.add(new TipoDocumento(0, "Resumen de Nomina"));
		this.tipoDocumento.add(new TipoDocumento(1, "Memorandum"));
		this.tipoDocumento.add(new TipoDocumento(2, "Oficio"));
	}
	
	public List<TipoDocumento> getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumentos(List<TipoDocumento> tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
}
