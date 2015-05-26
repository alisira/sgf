package sigefirrhh.persistencia.modelo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModeloUtil {
	
public Object llenarBean(Object destino, Object fuente)  {
	
	
    	
        Method metodos[] = fuente.getClass().getMethods();        
        for (int i = 0; i < metodos.length; i++) {
        	Method field = metodos[i];        	
        	
        	if (field.getName().indexOf("get") > -1){
        		//System.out.println(field.getName() +" - "+field.getReturnType()+" - "+field.getModifiers() +" - "+field.getDefaultValue() +" - "+field.getGenericReturnType());
        		
        		if (!field.getName().toString().equals("getClass") && !field.getName().toString().equals("getMultipartRequestHandler")
        				&& !field.getName().toString().equals("getServletWrapper")){
		        			
        	        Class clazz=destino.getClass();        	       
        			Method m;
        			Integer rInt = 0;	        			
        			String rStr = "";
        			Date rDate = null;
        			
        			try {
						
						if (field.getReturnType().toString().equals("class java.lang.Integer")){
							rInt = (Integer)field.invoke(fuente);
							if (rInt!=null ){
								//System.out.println(field.getName() +" - " + rInt);
								Class[] cArg = new Class[1];
								cArg[0] = Integer.class;
								m = clazz.getDeclaredMethod("set"+field.getName().toString().substring(3), cArg);
						        m.invoke(destino, rInt);								
							}
						}else if (field.getReturnType().toString().equals("class java.lang.String")){
							//Esta seccion realiza la conversion de la fuente que viene tipo string y la transforma al mismo tipo del destino
							rStr= (String)field.invoke(fuente);
							if (rStr!=null && !rStr.toString().equals("")){
								//System.out.println(field.getName() +" - " + rStr);
								
								m = clazz.getDeclaredMethod(field.getName().toString());

								Class[] cArg = new Class[1];
								if(m.getReturnType().toString().equals("class java.lang.String")){
									cArg[0] = String.class;
									m = clazz.getDeclaredMethod("set"+field.getName().toString().substring(3), cArg);
							        m.invoke(destino, rStr);
								} else if(m.getReturnType().toString().equals("class java.util.Date")){
									cArg[0] = Date.class;									
									m = clazz.getDeclaredMethod("set"+field.getName().toString().substring(3), cArg);
							        try {
										m.invoke(destino, new SimpleDateFormat("dd/M/yyyy").parse(rStr));
									} catch (ParseException e) {										
										e.printStackTrace();
									}
								} else if(m.getReturnType().toString().equals("class java.lang.Integer")){
									cArg[0] = Integer.class;									
									m = clazz.getDeclaredMethod("set"+field.getName().toString().substring(3), cArg);							        
									m.invoke(destino, Integer.valueOf(rStr));
									    
								} else {
									System.out.println("Revisar error conversion tipos 111:" + field.getName().toString());	
									System.out.println(field.getReturnType().toString() + " - " + m.getReturnType().toString());
								}								
							}
							
						}else if (field.getReturnType().equals("class java.util.Date")){
							rDate= (Date)field.invoke(fuente);
							if (rDate!=null){
								Class[] cArg = new Class[1];
								cArg[0] = String.class;
								m = clazz.getDeclaredMethod("set"+field.getName().toString().substring(3), cArg);
						        m.invoke(destino, rDate);								
							}
						}
					
        			} catch (NoSuchMethodException e) {
        				        				
        				Class n2=fuente.getClass();        				
        				System.out.println("No existe el metodo " + field.getName().toString() + " en el bean destino:");
        				System.out.println("Bean destino:" + clazz.getName() + ", bean fuente:" + n2.getName().toString() );
        				continue;
						//e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} 
        		}        		
        	}
        }
		return destino;
    }
}
