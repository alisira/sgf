package sigefirrhh.struts.pdd;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PddCompromisoImp implements Pdd {

	public String urlReporte(int expediente, int ano, int org) {
		
		return "web/compromiso_inicial/reference.pdf";
	}
	
	public String actuaEstatus(int expediente, int ano, int org, int estatus) {		
		return null;
	}

	@Override
	public Map<Integer, String> opciones() {
		Map<Integer, String> opcionesPDD = new HashMap<Integer, String>();
		
		//Lo ideal es buscar estas opciones en el modelo de datos
		opcionesPDD.put(1, "Aprobar");
		opcionesPDD.put(2, "Anular");
		
		return opcionesPDD;
	}
}
