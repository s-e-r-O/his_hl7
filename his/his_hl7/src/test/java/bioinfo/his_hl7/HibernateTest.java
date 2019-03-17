package bioinfo.his_hl7;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import junit.framework.TestCase;

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
		insertUser(1,"Mark","Johnson","mark.johnson@gmail.com","mjohnson");
        listUsers();
	}
	
	private int insertUser(int id, String fname, String lname, String email, String username)
    {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer userIdSaved = null;
        try {
            tx = session.beginTransaction();
            User u = new User(id,fname,lname,email,username);
            userIdSaved = (Integer) session.save(u);
            tx.commit();
        } catch(HibernateException ex) {
            if(tx != null)
                tx.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
         
        return userIdSaved;
         
    }
     
    private void listUsers()
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> result = (List<User>)session.createQuery("from User").list();
        for (User user : result) {
        	System.out.println( "User: " + user.getFirstname() + " (" + user.getId()+")");
        }        
        session.getTransaction().commit();
        session.close();
        super.assertEquals("Mark (1)", result.get(0).getFirstname() + " (" + result.get(0).getId()+")");
    }

}
