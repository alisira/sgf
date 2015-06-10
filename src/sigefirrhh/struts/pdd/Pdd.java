package sigefirrhh.struts.pdd;

import java.util.Map;

public interface Pdd {

	public String urlReporte(int expediente, int ano, int org);
	public String actuaEstatus(int expediente, int ano, int org, int estatus);
	public Map<Integer, String> opciones();
	
}
