package connect;

import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;
import java.util.Properties;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

// Recipient Class
import recipient.Recipient;
import user.User;

public class Email {
    // This is a singleton object that connects to the Email Service
    private static Email emailObj;
    private static ObjectOutputStream EmailStream;
    private static FileOutputStream EmailLog;
    private String email;
    private String password;
    private Session session;

    private Email(User user) throws IOException{
        this.email = user.getEmail();
        this.password = user.getPassword();
        EmailLog = new FileOutputStream("./emailHistory.ser"); 
        EmailStream = new ObjectOutputStream(EmailLog);

        // Create emailObj connection
        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        
        try {
            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(email, password);
                        }
                    });
            this.session = session;
            
        } catch (Exception e) {
            System.err.println("Error : Invalid User Credentials");
        }
    }

    public static Email connectEmail(User user) throws IOException{
        if(emailObj==null){
            emailObj = new Email(user);
        }
        return emailObj;
    }

    public void sendMail(Recipient recipient, String subject, String content) throws IOException{
        // Set up sender
        Message message = new MimeMessage(session);
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

            // Write the message into the local storage
            Email.EmailStream.writeUTF(message.toString());

        } catch (AddressException e) {
            System.err.println("Error : Invalid recipient address");
            e.printStackTrace();
        } catch (MessagingException e) {
            System.err.println("Error : Could not send the message successfully");
            e.printStackTrace();
        }
    }
}
