package bean;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	public void send(String u_email,String u_password, String u_name,String sub, String msg) throws MessagingException {

		// create an instance of Properties Class
		Properties props = new Properties();

		// thong so ket noi smtp server
		props.put("mail.smtp.host", "smtp.gmail.com");
		// below mentioned mail.smtp.port is optional
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

// ket noi smtp server
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("huyenpttfx13136@funix.edu.vn", "WmOVdjvMyc2a");
			}
		});

		/*
		 * Create an instance of MimeMessage, it accept MIME types and headers
		 */
		
		
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress("huyenpttfx13136@funix.edu.vn"));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(u_email));
		message.setSubject(sub);;
		message.setContent(msg, "text/html;charset=UTF-8");

		/* Transport class is used to deliver the message to the recipients */

		Transport.send(message);

	}
}
