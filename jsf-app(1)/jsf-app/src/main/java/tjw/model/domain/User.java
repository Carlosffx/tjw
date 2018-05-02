package tjw.model.domain;

import java.util.List;

import javax.persistence.*;


@Entity
@Table ( name = "usuario")
public class User {
	
	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	
	@Column
	private String login;
	
	@Column
	private String senha;
	
	@Column
	private String email;
	
	@OneToMany
	private List<Mail> mails;
	
	public User() {
		
	}
	
	public User(String login, String senha, String email) {
		this.login = login;
		this.senha = senha;
		this.email= email;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Mail> getMails() {
		return mails;
	}

	public void setMails(List<Mail> mails) {
		this.mails = mails;
	}

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

	public Long getId() {
		return id;
	}



}