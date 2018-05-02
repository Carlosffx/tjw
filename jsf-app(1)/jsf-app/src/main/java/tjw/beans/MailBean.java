package tjw.beans;


import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import tjw.model.dao.MailDAO;
import tjw.model.dao.UserDAO;
import tjw.model.domain.Mail;
import tjw.model.domain.MailSender;
import tjw.util.JPAUtil;
import tjw.model.domain.User;

@ManagedBean ( name = "mailbean")
@SessionScoped
public class MailBean implements Serializable {
	
	private Mail mail;
	private List<Mail> mails;
    private String fromMail;
    private String username;
    private String password;
    private String toMail;
    private String subject;
    private String message;
    private MailDAO maildao;
    private String destinatario;
    private String assunto;
    private String mensagem;
    
    
	

	public MailDAO getMaildao() {
		return maildao;
	}

	public void setMaildao(MailDAO maildao) {
		this.maildao = maildao;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

	public List<Mail> getMails() {
		return mails;
	}

	public void setMails(List<Mail> mails) {
		this.mails = mails;
	}

	public String getFromMail() {
		return fromMail;
	}

	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToMail() {
		return toMail;
	}

	public void setToMail(String toMail) {
		this.toMail = toMail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@PostConstruct
	public void init() {
		mail = new Mail();

		MailDAO dao = new MailDAO(new JPAUtil().getEntitymanager());

		mails = dao.getAll();

		dao.closeEntityManager();
	}

	public void persist() {
		MailDAO dao = new MailDAO(new JPAUtil().getEntitymanager());

		if (mail.getId() == null)
			dao.save(mail);
		else dao.edit(mail.getId());

		mails = dao.getAll();

		dao.closeEntityManager();
	}

	public void remove(Long userId) {
		MailDAO dao = new MailDAO(new JPAUtil().getEntitymanager());

		dao.remove(userId);

		mails = dao.getAll();

		dao.closeEntityManager();	}

	
    public void send() {
        try {
            MailSender mailSender=new MailSender();
            mailSender.sendMail(fromMail, username, password, toMail, subject, message);
        } catch (Exception e) {
        }
    }
    
    public void buscarMensagens(User user) {
    	maildao.getMessagesByUsuario(user);
    }
    
    
    public void salvarMensagem() {
        Mail mail = new Mail( toMail, subject, message);
        MailDAO maildao = new MailDAO(new JPAUtil().getEntitymanager());
        maildao.save(mail);
    }
    
    public void salvarEnviar() {
    	send();
    	salvarMensagem();
    }

}
