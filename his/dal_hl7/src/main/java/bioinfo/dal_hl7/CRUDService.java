package bioinfo.dal_hl7;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;

public class CRUDService<T> {
	public Integer save(T o) {
		Integer id = null;
		Session session = Database.getSessionFactory().openSession();
		session.beginTransaction();
		id = (Integer)session.save(o);
		session.getTransaction().commit();
		return id;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> get(String query) {
		Session session = Database.getSessionFactory().openSession();
		session.beginTransaction();
		List<T> result = (List<T>)session.createQuery(query).list();
		session.getTransaction().commit();
		return result;
	}
	
	public void delete(T o) {
		Session session = Database.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(o);
		session.getTransaction().commit();
	}
}
