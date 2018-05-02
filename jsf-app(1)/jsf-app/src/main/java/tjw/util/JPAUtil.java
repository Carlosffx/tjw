package tjw.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private EntityManagerFactory factory;
	
	public JPAUtil() {
			factory = Persistence.createEntityManagerFactory("tjw");
	}

	public EntityManager getEntitymanager() {
		try {
			return factory.createEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("pode ser em PHP?");
			return null;
		}
	}
	public void closeEntityManagerFactory() {
		factory.close();
	}
}