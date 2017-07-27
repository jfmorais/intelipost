package intelipost.login.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import intelipost.login.dao.LoginDAO;
import intelipost.login.util.SessaoUtil;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;
	
	private String pwd;
	private String msg;
	private String user;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	//valida o usuario
	public String validateUsernamePassword() {
		boolean redis = LoginDAO.procuraRedis(user, pwd);//TODO procura no repositorio do redis 
		boolean valid = false;
		if(redis) {
			System.out.println("achou no redis");
			// TODO encontrou no respositorio do redis, portanto nao vai ao banco fazer a consulta
			return "admin";
		}else {
			valid = LoginDAO.validate(user, pwd);
			if (valid) {
				HttpSession session = SessaoUtil.getSession();
				session.setAttribute("username", user);
				return "admin";
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Usuario ou senha incorretos",
								"Por favor entre com as informacoes corretas"));
				return "login";
			}
		}
		
	}

	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessaoUtil.getSession();
		session.invalidate();
		return "login";
	}
}
