package sigefirrhh.struts.pdd;

public interface Pdd {

	public String urlReporte(int expediente, int ano, int org);
	public String actuaEstatus(int expediente, int ano, int org, int estatus);
	
	
}
