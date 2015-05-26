package sigefirrhh.struts.pdd;

import java.util.HashMap;
import java.util.Map;

public class ContenedorPDD {

	private static Map<String, Object> components = initMap();
	
	private static Map<String, Object> initMap(){
		components = new HashMap<String, Object>();
		
		Pdd pddCompromiso = new PddCompromisoImp();
		components.put("pddCompromiso", pddCompromiso);
		return components;
		
	}
	
	public static Object getComponents(String componente){
		return components.get(componente);
	}
	
}
