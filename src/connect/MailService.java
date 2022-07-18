package connect;
import java.io.Serializable;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import recipient.Recipient;
import user.User;

public class MailService implements Serializable{
    // This is a singleton object that connects to the Email Service
    private static MailService mailService;
    private String email;
    private String password;
    private Session session;

    private MailService(User user){
        this.email = user.getEmail();
        this.password = user.getPassword();

        // Create mailService connection
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });
        this.session = session;
    }

    public static MailService connectMailService(User user){
        if(mailService==null){
            mailService = new MailService(user);
        }
        return mailService;
    }

    public void sendMail(Email mail){
        // Set up sender
        Message message = new MimeMessage(session);
        Recipient recipient = mail.getRecipient();
        String subject = mail.getSubject();
        String content = mail.getContent();
        try {
            // Create the message
            message.setFrom(new InternetAddress(email));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipient.getMail())
            );
            message.setSubject(subject);
            message.setText(content);
            // Send the message
            Transport.send(message);

        } catch (AddressException e) {
            System.err.println("Error : Invalid recipient address");
            e.printStackTrace();
        } catch (MessagingException e) {
            System.err.println("Error : Could not send the message successfully");
            e.printStackTrace();
        }
    }
}
