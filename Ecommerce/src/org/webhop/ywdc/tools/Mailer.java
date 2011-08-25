package org.webhop.ywdc.tools;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer 
{
	
	public String user;
	

	
	public String password;
	public String host;
	public String port;
	public String to;
	public String from;
	public String subject;
	public String html;

	public Mailer()
	{
	
	}
	public void sendMail()
	{
		Properties props = new Properties();
		props.put("mail.smtp.user", user);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.auth", "true");
		
		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		

		try
		{
		Authenticator auth = new SMTPAuthenticator();
		Session session = Session.getInstance(props, auth);
		

		MimeMessage msg = new MimeMessage(session);
		
		msg.setContent(html, "text/html");

		msg.setSubject(subject);
		msg.setFrom(new InternetAddress(from));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		Transport.send(msg);
		}
		catch (Exception mex)
		{
		mex.printStackTrace();
		}
		
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	private class SMTPAuthenticator extends javax.mail.Authenticator
	{
		public PasswordAuthentication getPasswordAuthentication()
		{
			return new PasswordAuthentication(user, password);
		}
	}

}
