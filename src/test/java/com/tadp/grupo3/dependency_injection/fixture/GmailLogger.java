package com.tadp.grupo3.dependency_injection.fixture;

public class GmailLogger {

	private MailSender mailSender;
	private Usuario usuario;

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	private void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
