package org.webhop.ywdc;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		
		
		Mailer m2 = new Mailer();
		
		m2.setUser("thebravedave@gmail.com");
		m2.setPassword("11t3chno");
		m2.setFrom("thebravedave@gmail.com");
		m2.setTo("thebravedave@gmail.com");
		m2.setPort("465");
		m2.setSubject("testing mailer2");
		m2.setHost("smtp.gmail.com");
		m2.setHtml("<b>Testing mailer2</b><br /><img src=\"http://www.webwombat.com.au/lifestyle/fashion_beauty/images/victoria-secret-models-5.jpg\">");
		m2.sendMail();

	}

}
