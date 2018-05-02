package tjw.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import tjw.model.domain.User;

public class UserDAO {
	private EntityManager entityManager;

	public UserDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void save(User user) {
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
	}
	
	public void remove(Long userId) {
		entityManager.getTransaction().begin();
		entityManager.remove(getUserById(userId));
		entityManager.getTransaction().commit();
	}
	
	public void edit(Long userId) {
		entityManager.getTransaction().begin();
		entityManager.merge(getUserById(userId));
		entityManager.getTransaction().commit();
	}
	
    public User getUserByLoginAndPassword(String login, String senha) {
        return entityManager.createQuery("select c from User c where c.login = :login and c.senha = :senha",
                User.class)
                .setParameter("login", login)
                .setParameter("senha", senha)
                .getSingleResult();
    }
	
	public User getUserById(Long userId) {
		return entityManager.find(User.class, userId);
	}
	
	
	public List<User> getAll(){
		return entityManager.createQuery("from User u").getResultList();
	}
	
	public void closeEntityManager() {
		entityManager.close();
	}
}