package bioinfo.his_hl7;

import java.util.List;

import org.hibernate.Session;

import bioinfo.dal_hl7.Database;
import junit.framework.TestCase;
import upb.bio.models.Patient;

public class HibernateTest extends TestCase {
	
	public HibernateTest(String testName) {
		super(testName);
	}

	@SuppressWarnings("unchecked")
	public void testBasicUsage() {
		Session session = Database.getSessionFactory().openSession();
		session.beginTransaction();
		List<Patient> result = session.createQuery("from Patient").list();
		super.assertNotNull(result);
        session.getTransaction().commit();
        session.close();
	}

}
