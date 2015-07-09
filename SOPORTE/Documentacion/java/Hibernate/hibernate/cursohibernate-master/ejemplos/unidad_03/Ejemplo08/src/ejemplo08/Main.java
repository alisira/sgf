
package ejemplo08;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import org.hibernate.criterion.MatchMode;
import java.util.Iterator;

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


        Profesor profesor = new Profesor(10, "Laura", "Vivó", "López");
        List<CorreoElectronico> correosElectronicos = new ArrayList<>();
        correosElectronicos.add(new CorreoElectronico(3, "laura@yahoo.com", profesor));
        correosElectronicos.add(new CorreoElectronico(2, "laura@hotmail.com", profesor));
        correosElectronicos.add(new CorreoElectronico(1, "laura@gmail.com", profesor));

        profesor.setCorreosElectronicos(correosElectronicos);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        List<Profesor> lista =null;
        List<CorreoElectronico> listaCorreo =null;        
        
        Criteria crit = session.createCriteria(Profesor.class);
        crit.setMaxResults(50);
        lista = crit.list();
        //System.out.println("Primer Ejemplo: " + lista.size());
        //System.out.println("Primer Ejemplo: " + lista.get(0).getCorreosElectronicos().size() );
        //System.out.println(lista.get(0).getNombre() + " " + lista.get(0).getCorreosElectronicos());        
       
        Criteria crit2 = session.createCriteria(Profesor.class);
        crit2.setMaxResults(50);
        crit2.add(Restrictions.ilike("nombre", "raquel", MatchMode.ANYWHERE ));
        lista = crit2.list();
        System.out.println("Segundo Ejemplo: " + lista.size());
        Iterator it = lista.iterator();
        while(it.hasNext()){
            Profesor proTemp = (Profesor) it.next();
            
            //System.out.println(proTemp.getId()+ " " + proTemp.getNombre()+ " " + proTemp.getApe1() + " " + proTemp.getApe2() + " " + proTemp.getCorreosElectronicos());
            
            System.out.println( proTemp.getNombre());
            
            listaCorreo = proTemp.getCorreosElectronicos();
            //System.out.println(listaCorreo.size());            
            Iterator it2 = listaCorreo.iterator();
            while(it2.hasNext()){
                CorreoElectronico correoTemp = (CorreoElectronico) it2.next();
                System.out.println(correoTemp.getDireccionCorreo());
            }
        }


        //Profesor pro = (Profesor) session.get(Profesor.class, 13);
        //System.out.println(pro.getNombre());
        //session.

        session.getTransaction().commit();
        session.close();

    }
}
