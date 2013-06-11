package com.tadp.grupo3.dependency_injection.fixture;

public class MailSender {
	String usuario;
	String password;
	String smtp;
	Integer puerto;

	public MailSender(String usuario, String password, String smtp,
			Integer puerto) {
		this.usuario = usuario;
		this.password = password;
		this.smtp = smtp;
		this.puerto = puerto;
	}

	public MailSender() {

	}

	public String getUsuario() {
		return usuario;
	}

	public String getPassword() {
		return password;
	}

	public String getSmtp() {
		return smtp;
	}

	public Integer getPuerto() {
		return puerto;
	}

	public void setUsuario(String unUsuario) {
		this.usuario = unUsuario;
	}

	public void setPassword(String unaPassword) {
		this.usuario = unaPassword;
	}

	public void setSmtp(String unSmtp) {
		this.smtp = unSmtp;
	}

	public void setPuerto(Integer unPuerto) {
		this.puerto = unPuerto;
	}
}
