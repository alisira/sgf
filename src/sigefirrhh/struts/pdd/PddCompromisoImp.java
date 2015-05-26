package sigefirrhh.struts.pdd;

public class PddCompromisoImp implements Pdd {

	public String urlReporte(int expediente, int ano, int org) {
		
		return "web/compromiso_inicial/reference.pdf";
	}
	
	public String actuaEstatus(int expediente, int ano, int org, int estatus) {		
		return null;
	}
}
