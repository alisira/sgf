package sigefirrhh.sistema;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;	

public class EscribirArchivo {
	
	public static String escribir(String nombreArchivo, String foto){
		try{
			
			//Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
			File archivo=new File("./" + nombreArchivo + ".log");
		//System.out.println(archivo);
			
			//Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
			FileWriter escribir=new FileWriter(archivo,true);
			//FileWriter escribir=new FileWriter(archivo);
		//System.out.println(escribir);
			
			BufferedWriter writer = new BufferedWriter(escribir);
			
			writer.append(foto);
			writer.newLine(); 
			
			
			
			
		writer.close();
			//Cerramos la conexion
			escribir.close();
		}catch(Exception e){
			System.out.println("Error al escribir");
		}
		return foto;	
	}
	
	public static String leer(String nombreArchivo){
		return nombreArchivo;
		
	}
	
} 
