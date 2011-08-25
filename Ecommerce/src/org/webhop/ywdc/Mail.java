package org.webhop.ywdc;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	/**
	 * @param args
	 * @throws MessagingException 
	 */
	public static void main(String[] args) throws MessagingException
	{
		 
	      
	        
	        
		Properties props = new Properties();
    	props.put("mail.smtp.host", "smtp.gmail.com");
    	props.put("mail.smtp.socketFactory.port", "465");
    	props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.smtp.port", "465");
 
    	Session mailSession = Session.getDefaultInstance(props, new javax.mail.Authenticator() 
    	{
    		protected PasswordAuthentication getPasswordAuthentication()
		{ return new PasswordAuthentication("thebravedave@gmail.com", "11t3chno");	}
    	});		
	        
	     
	     //   Session mailSession = Session.getDefaultInstance(props, null);
	        mailSession.setDebug(true);
	        Transport transport = mailSession.getTransport();

	        MimeMessage message = new MimeMessage(mailSession);
	        message.setSubject("HTML  mail with images");
	        message.setFrom(new InternetAddress("me@sender.com"));
	        message.setContent("<h1>Hello world</h1>", "text/html");
	        message.addRecipient(Message.RecipientType.TO,
	             new InternetAddress("thebravedave@gmail.com"));

	        transport.connect();
	        transport.sendMessage(message,
	            message.getRecipients(Message.RecipientType.TO));
	        transport.close();


	        
	        
	        
	        
	        
	        
	        /*
	         * 
	         *
	    Properties props = new Properties();
    	props.put("mail.smtp.host", mailHost);
    	props.put("mail.smtp.socketFactory.port", port);
    	props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.smtp.port", port);
 
    	session = Session.getDefaultInstance(props, new javax.mail.Authenticator() 
    	{
    		protected PasswordAuthentication getPasswordAuthentication()
		{ return new PasswordAuthentication(mailUser, mailPassword);	}
    	});		
		/*
    	 MimeMessage message = new MimeMessage(mailSession);
	        message.setSubject("HTML  mail with images");
	        message.setFrom(new InternetAddress("me@sender.com"));
	        message.setContent("<h1>Hello world</h1>", "text/html");
	        message.addRecipient(Message.RecipientType.TO,
	             new InternetAddress("thebravedave@gmail.com"));

	        transport.connect();
	        transport.sendMessage(message,
	            message.getRecipients(Message.RecipientType.TO));
    	
  
		
		  Message message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(sender));
		    message.setRecipients(Message.RecipientType.TO, 
	                        InternetAddress.parse(recipient));
		    message.setSubject(subject);
		    message.setText(messageText);
	
		    Transport.send(message);
	         */
	        
	        
	        
	}

}
