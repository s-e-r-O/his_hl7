package bioinfo.dal_hl7;

import java.util.List;

import org.hibernate.Session;

public class UtilsService {
	public static List<Object> getConsultsForPatient(Integer patientID) {
		Session session = Database.getSessionFactory().openSession();
		session.beginTransaction();
		List<Object> result = session.createQuery("from Consultation where Patient_id = " + patientID).list();
		session.getTransaction().commit();
		return result;
	}
}
