package sigefirrhh.struts.addons;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

public interface Comun {
	
	public String validarAcceso(HttpServletRequest request, String funcion);
	// Obtenemos la hora
	Locale l = new Locale("es","VE");
	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Caracas"),l);
	//System.out.println("FECHA: " + cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DATE) + " " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND));
	String hora = cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) +  cal.get(Calendar.MILLISECOND);
	//int ano = cal.get(Calendar.YEAR);//Obtiene el a�o actual
	int mes = cal.get(Calendar.MONTH)+1;//Mes actual
	int semana = cal.get(Calendar.WEEK_OF_YEAR);	
	
	int ano = 2014;

}
