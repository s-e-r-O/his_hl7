package upb.bio;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class App 
{
    public static void main( String[] args )
    {
    	SessionFactory sessionFactory;
    	// A SessionFactory is set up once for an application!
    	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
    			.configure() // configures settings from hibernate.cfg.xml
    			.build();
    	try {
    		sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
            sessionFactory.close();
    	}
    	catch (Exception e) {
    		// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
    		// so destroy it manually.
    		StandardServiceRegistryBuilder.destroy( registry );
    		e.printStackTrace();
    	}
    	
    }
}
