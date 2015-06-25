package sigefirrhh.sistema;

public class ExcepcionSigefirrhh extends Exception {
	
	private static final long serialVersionUID = 187818323291828170L;
	String errorActual;
	
    public ExcepcionSigefirrhh(String errorTem){
    	errorActual = errorTem;
    };
 
    public String toString(){
        return errorActual;
    }
}
