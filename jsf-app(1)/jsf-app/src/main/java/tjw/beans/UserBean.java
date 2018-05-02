package tjw.beans;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import tjw.model.dao.UserDAO;
import tjw.model.domain.User;
import tjw.util.JPAUtil;

@ManagedBean( name = "userbean")
@SessionScoped
public class UserBean {
	
	private String login;
	
	private String senha;
	
	private String email;
	
	private User user;
	
	private List<User> users;
	

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@PostConstruct
	public void init() {
		user = new User();

		UserDAO dao = new UserDAO(new JPAUtil().getEntitymanager());

		users = dao.getAll();

		dao.closeEntityManager();
	}

	public void persist() {
		UserDAO dao = new UserDAO(new JPAUtil().getEntitymanager());

		if (user.getId() == null)
			dao.save(user);
		else dao.edit(user.getId());

		users = dao.getAll();

		dao.closeEntityManager();
	}

	public void remove(Long userId) {
		UserDAO dao = new UserDAO(new JPAUtil().getEntitymanager());

		dao.remove(userId);

		users = dao.getAll();

		dao.closeEntityManager();
	}
	
    public String fazerCadastro() {
        User user = new User(login, senha, email);
        UserDAO userDAO = new UserDAO(new JPAUtil().getEntitymanager());
        userDAO.save(user);
        User usuarioCadastrado = userDAO.getUserByLoginAndPassword(login, senha);
        if (usuarioCadastrado != null) {
        	
            return "Home.xhtml?facesRedirect=true";
        }
        return null;
    }
    
    
}