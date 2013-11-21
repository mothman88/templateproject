package net.mothman.project.service;

import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @author mothman88
 *
 */
public class EmailAlertService {
	
    private String to;
    private String from;
    private String host;
    private String port;
    private String disable;
    
    /**
	 * @param to
	 * @param from
	 * @param host
	 * @param port
	 * @param disable
	 */
	public EmailAlertService(String from, String to, String host, String port, String disable) {
		this.to = to;
		this.from = from;
		this.host = host;
		this.port = port;
		this.disable = disable;
	}

	public boolean sendMessage(String subject, String text) {
        if ((to == null) || (from == null) || (host == null) || (port == null)) {
            return false;
        }
        
        if(disable.equals("1")) {
        	return false;
        }
        
        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host.trim());
        properties.setProperty("mail.smtp.port", port.trim());
        
        // Get the default Session object.
        Session session = null;
        	
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            StringTokenizer st = new StringTokenizer(to, ",");
            while (st.hasMoreTokens()) {
               String recipient = st.nextToken().trim();
               message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            }          

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(text);

            // Send message
            Transport.send(message);

            return true;
        } catch (MessagingException mex) {
            return false;
        }
    }
}