Vou modificar aqui e salvar
asdafsdfds
sdfsdfds
sdafdsfadsf
sdafsdaf

Vou modificar aqui, esse é o branch teste 2

package tjw.beans;

import javax.faces.bean.ManagedBean;


import tjw.model.dao.UserDAO;
import tjw.model.domain.User;
import tjw.util.JPAUtil;

@ManagedBean (name = "loginbean")
public class LoginBean {

    private String login;
    private String senha;
    
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
	
   public String doLogin() {
        UserDAO userDAO = new UserDAO(new JPAUtil().getEntitymanager());
        User user = userDAO.getUserByLoginAndPassword(login, senha);
        System.out.println(user.toString());
        if(user != null) {
        	
            return "Home.xhtml?facesRedirect=true";
            
            }
        return null;
    }


}
