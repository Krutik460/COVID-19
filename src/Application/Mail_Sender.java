package Application;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Mail_Sender {
	
	
	public static void sendMail(String HTMLcode, String recepient, String state) {
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String myEmail = "updatesforcovid@gmail.com";
		String myPassword = "krutikpatel2000";
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myEmail, myPassword);
			}
		});
		
		Message message = prepeareMessage(session, myEmail, recepient, HTMLcode, state);
		try {
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	
	private static Message prepeareMessage(Session session, String myEmail, String recepient, String HTMLcode, String state) {
		Message message = new MimeMessage(session);
		
		
		try {
			message.setFrom(new InternetAddress(myEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			
			message.setSubject(state + " : COVID-19 UPDATE");
			
			message.setContent(HTMLcode, "text/html; charset=\"UTF-8\"");
			
			return message;
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
