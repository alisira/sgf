package menu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class menu {

	public static void main(String[] args) {

		
		
		// TODO Auto-generated method stub
		String vtOpciones[][] = {
	            {"1", "Opcion _1"},
	            {"11", "Opcion 1_1"},
	            {"12", "Opcion 1_2"},
	            {"121", "Opcion 12_1"},
	            {"122", "Opcion 12_2"},
	            {"13", "Opcion 1_3"},
	            {"2", "Opcion _2"},
	            {"21", "Opcion 2_1"},
	            {"22", "Opcion 2_2"},
	            {"221", "Opcion 22_1"},
	            {"3","Opcion _3"}
	            };
		String menu = "<ul>";
		
		for (int i=0; i<vtOpciones.length; i++) {
			if (TieneHijos2(i, vtOpciones)) {
				//System.out.println("indice:" + i + " valor:" + vtOpciones[i][0] + " SI");				
				menu += "<li>" + vtOpciones[i][1] + "<ul>";				
			} else {
				menu += "<li>" + vtOpciones[i][1] + "</li>";
				//System.out.println("indice:" + i + " valor:" + vtOpciones[i][0] + " NO");
			}
			//System.out.println(TieneHijos2(i, vtOpciones));
			
			if (i+1 < vtOpciones.length){
		    	 if (vtOpciones[i+1][0].length() < vtOpciones[i][0].length()){
		    		 		    		 
		    		 int cont = vtOpciones[i][0].length() - vtOpciones[i+1][0].length() ;
		    		 for (int y=0; y<cont; y++) {
		    			 menu +="</ul></li>";	 
		    		 }
		    	 }
		     }
	    	// System.out.println(m.toString()+ " " + resp);			
			
        }
		menu +="</ul>";
		
		System.out.println(menu);     
		
	} 

//<ul><li>Opcion _1<ul><li>Opcion 1_1</li><li>Opcion 1_2<ul><li>Opcion 12_1</li><li>Opcion 12_2</li>          <li>Opcion 1_3</li>          <li>Opcion _2<ul><li>Opcion 2_1</li><li>Opcion 2_2<ul><li>Opcion 22_1</li>                    <li>Opcion _3</li></ul>
//<ul><li>Opcion _1<ul><li>Opcion 1_1</li><li>Opcion 1_2<ul><li>Opcion 12_1</li><li>Opcion 12_2</li></ul></li><li>Opcion 1_3</li></ul></li><li>Opcion _2<ul><li>Opcion 2_1</li><li>Opcion 2_2<ul><li>Opcion 22_1</li></ul></li><li>Opcion _3</li></ul></li>
//<ul><li>Opcion _1<ul><li>Opcion 1_1</li><li>Opcion 1_2<ul><li>Opcion 12_1</li><li>Opcion 12_2</li></ul></li><li>Opcion 1_3</li></ul></li><li>Opcion _2<ul><li>Opcion 2_1</li><li>Opcion 2_2<ul><li>Opcion 22_1</li></ul></li></ul></li><li>Opcion _3</li></ul>
//<ul><li>Opcion _1<ul><li>Opcion 1_1</li><li>Opcion 1_2<ul><li>Opcion 12_1</li><li>Opcion 12_2</li></ul></li><li>Opcion 1_3</li></ul></li><li>Opcion _2<ul><li>Opcion 2_1</li><li>Opcion 2_2<ul><li>Opcion 22_1</li></ul></li></ul></li><li>Opcion _3</li></ul>

			
	
	private static boolean TieneHijos2(int indiceActual, String vtOpc[][]) {	
		 
		//Pattern p = Pattern.compile("^1.*");
		
		//System.out.println(indiceActual + " " );
		
		Pattern p = Pattern.compile("^" + vtOpc[indiceActual][0]+ ".*");
		
		 //^1.*?$			 
		 
	     
	     
	     boolean resp = false;
	     
	     /*for (int i=indiceActual+1; i<vtOpc.length; i++) {
				if (m.find()) {
					resp = true;
				} else {
					resp = false;
				}
	        }*/
	     if (indiceActual+1 < vtOpc.length){
	    	 Matcher m = p.matcher(vtOpc[indiceActual+1][0]); // get a matcher object
	    	 if (m.find()) {
					resp = true;
				} else {
					resp = false;
				}
	    	// System.out.println(m.toString()+ " " + resp);
	     }
	     
		return resp;
	     
	     
	     
	     
	    /* m = p.matcher("122"); // get a matcher object
	     count = 0;
	
	     while(m.find()) {
	       count++;
	       System.out.println(m.toString());
	       System.out.println("Match number "+count);
	       System.out.println("start(): "+m.start());
	       System.out.println("end(): "+m.end());
	     }
	     */
	     
	     
		
	     
	     
	     
	}
	

private static boolean TieneHijos(String Item, int numItems, String vtOpc[][]) {
	/* Cuenta el número de veces que aparece la clave dada iniciando
	           otras claves. Si ésta aparece más de una vez, la Opcion tiene	subopciones */
	int NVeces = 0;
	int LonItem = Item.length();
	for (int i=0; i<numItems; i++) {
		if ( vtOpc[i][0].length() >= LonItem) {
			if (vtOpc[i][0].substring(0,LonItem).equals(Item)) {
				NVeces++;
				if (NVeces > 1) {
					return true;
				}
			}
		}

	}
	return (NVeces > 1);
}
	// F

}


