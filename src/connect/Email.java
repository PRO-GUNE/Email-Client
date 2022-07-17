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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

// Recipient Class
import recipient.Recipient;

public class Email {
    // This is a singleton object that connects to the Email Service
    private static Email email;
    private static FileOutputStream Email_Stream;
    private String address;
    private String password;
    private Session session;

    private Email(String address, String password) throws FileNotFoundException{
        this.address = address;
        this.password = password;
        Email_Stream = new FileOutputStream("../../lib/emailHistory.txt"); 

        // Create email connection
        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(address, password);
                    }
                });
        this.session = session;
    }

    public static Email connectDB(String address, String password) throws FileNotFoundException{
        if(email==null){
            email = new Email(address, password);
        }
        return email;
    }

    public void sendMail(Recipient recipient, String subject, String content){
        // Set up sender
        Message message = new MimeMessage(session);
        try {
            // Create the message
            message.setFrom(new InternetAddress(address));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipient.getMail())
            );
            message.setSubject(subject);
            message.setText(content);
            // Send the message
            Transport.send(message);
        } catch (AddressException e) {
            System.err.println("Error : Invalid Address/Password Provided");
            e.printStackTrace();
        } catch (MessagingException e) {
            System.err.println("Error : Could not send the message successfully");
            e.printStackTrace();
        }
    }
}
