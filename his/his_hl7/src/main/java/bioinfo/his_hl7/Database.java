package bioinfo.his_hl7;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Database {
	private static Database db;
	private SessionFactory sessionFactory;
	
	protected static void setUp(Database db) throws Exception {
    	// A SessionFactory is set up once for an application!
    	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
    			.configure() // configures settings from hibernate.cfg.xml
    			.build();
    	try {
    		db.sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    	}
    	catch (Exception e) {
    		// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
    		// so destroy it manually.
    		StandardServiceRegistryBuilder.destroy( registry );
    	}
    }
	
	public static SessionFactory getSessionFactory() {
		if (db == null) {
			db = new Database();
		}
		if (db.sessionFactory == null) {
			try {
				setUp(db);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return db.sessionFactory;
	}
	
}
