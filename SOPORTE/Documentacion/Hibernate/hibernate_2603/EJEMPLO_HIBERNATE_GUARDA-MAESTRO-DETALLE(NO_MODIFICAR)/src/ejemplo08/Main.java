
package ejemplo08;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Lorenzo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        /*Profesor profesor = new Profesor();
        profesor.setId(40);
        profesor.setNombre("Lauro");
        profesor.setApe1("Viva");
        profesor.setApe2("Lópes");
        
        Set<CorreoElectronico> correosElectronicos = new HashSet<>();
        correosElectronicos.add(new CorreoElectronico(4, "laura@yahoo.com", profesor));
        correosElectronicos.add(new CorreoElectronico(5, "laura@hotmail.com", profesor));
        correosElectronicos.add(new CorreoElectronico(6, "laura@gmail.com", profesor));
        
        
        correosElectronicos.add(new CorreoElectronico("lauro@yahoo.com", profesor));
        correosElectronicos.add(new CorreoElectronico("lauro@hotmail.com", profesor));
        correosElectronicos.add(new CorreoElectronico("lauro@gmail.com", profesor));
        
        profesor.setCorreosElectronicos(correosElectronicos);

        */

        //session.save(profesor);
        //session.saveOrUpdate(profesor);
        
        
        
        CompromisoInicialLight compromisoInicial = new CompromisoInicialLight();
        compromisoInicial.setAno(2014);
        compromisoInicial.setTarea(1);
        compromisoInicial.setIdOrganismo(11);        
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            String dateInString = "31-08-1982 10:20:56";
            Date date = sdf.parse(dateInString);
           
        }catch(Exception e){
            
        }        
       	
        //compromisoInicial.setObservacion(formaPeti.getObservacion());				
        
        Set<CompromisoInicialDetalleLight> compromisoInicialDetalles=new HashSet<>();
        compromisoInicialDetalles.add(new CompromisoInicialDetalleLight(44.0));
        compromisoInicial.setCompromisoInicialDetalle(compromisoInicialDetalles);
        
        //session.save(compromisoInicial);


        //////Primera Prueba///////        
        CompromisoInicial profesor = new CompromisoInicial();       
        profesor.setAno(2014);
        profesor.setIdOrganismo(27);
        profesor.setTarea(1);        
        Set<CompromisoInicialDetalle> correosElectronicos = new HashSet<>();
        correosElectronicos.add(new CompromisoInicialDetalle(profesor, 77.0));
        correosElectronicos.add(new CompromisoInicialDetalle(profesor, 77.0));
        correosElectronicos.add(new CompromisoInicialDetalle(profesor, 77.0));        
        profesor.setCorreosElectronicos(correosElectronicos);
        //////Fin de Primera Prueba///////
        
        session.save(profesor);        
        session.getTransaction().commit();
        session.close();

    }
    
}
