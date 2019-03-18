package bioinfo.his_hl7;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import junit.framework.TestCase;
import upb.bio.models.Patient;

public class HibernateTest extends TestCase {
	private SessionFactory sessionFactory;
	
	public HibernateTest(String testName) {
		super(testName);
	}
	
	@Override
	protected void setUp() throws Exception {
    	// A SessionFactory is set up once for an application!
    	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
    			.configure() // configures settings from hibernate.cfg.xml
    			.build();
    	try {
    		sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    	}
    	catch (Exception e) {
    		// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
    		// so destroy it manually.
    		StandardServiceRegistryBuilder.destroy( registry );
    	}
    }
	
	@Override
	protected void tearDown() throws Exception {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}

	@SuppressWarnings("unchecked")
	public void testBasicUsage() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Patient> result = session.createQuery("from Patient").list();
		super.assertNotNull(result);
        session.getTransaction().commit();
        session.close();
	}

}
