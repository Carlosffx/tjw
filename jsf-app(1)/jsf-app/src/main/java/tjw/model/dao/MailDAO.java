package tjw.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import tjw.model.domain.Mail;
import tjw.model.domain.User;

public class MailDAO {
	private EntityManager entityManager;

	public MailDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void save(Mail mail) {
		entityManager.getTransaction().begin();
		entityManager.persist(mail);
		entityManager.getTransaction().commit();
	}
	
	public void remove(Long mailId) {
		entityManager.getTransaction().begin();
		entityManager.remove(getMailById(mailId));
		entityManager.getTransaction().commit();
	}
	
	public void edit(Long mailId) {
		entityManager.getTransaction().begin();
		entityManager.merge(getMailById(mailId));
		entityManager.getTransaction().commit();
	}
	
	public Mail getMailById(Long mailId) {
		return entityManager.find(Mail.class, mailId);
	}

	
	public List<Mail> getAll(){
		return entityManager.createQuery("from Mail u").getResultList();
	}
	
	public List<Mail> getMessagesByUsuario(User user){
		return entityManager.createQuery("select m from Mail m where m.user= user", Mail.class)
				.setParameter("user", user)
				.getResultList();
	}
	
	
	public void closeEntityManager() {
		entityManager.close();
	}
}

