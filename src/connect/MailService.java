package connect;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import recipient.Greetable;
import recipient.Recipient;

public class MailService{
    // This is a singleton object that connects to the Email Service
    private static MailService mailService;
    private User user;
    private String email;
    private String password;
    private Session session;
    private ArrayList<Email> sentMails;

    
    private MailService(){
        // Create the file to save send emails if it is not available
        MailWriter.createFileIfNotAvailable();

        // Read the user email and password from the properties file
        try {
            InputStream input = new FileInputStream(".\\config\\config.properties");
            Properties userProp = new Properties();
            // load a properties file
            userProp.load(input);
    
            // get the property value and print it out
            String name = userProp.getProperty("user.name");
            String email = userProp.getProperty("user.email");
            String password = userProp.getProperty("user.password");

            // Create the user object
            this.user = new User(name, email, password);

        } catch (IOException e) {
    
            System.err.println("Error : Could read data from the properties file");
        }
        
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.sentMails = new ArrayList<Email>();
        
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
    
    public static MailService connectMailService(){
        if(mailService==null){
            mailService = new MailService();
        }
        return mailService;
    }

    public void sendGreeting(ArrayList<Recipient> recipients){
        for(Recipient recipient : recipients){
                Greetable greetable = (Greetable)recipient;
                Email mail = greetable.greet();
                sendMail(recipient, mail.getSubject(), mail.getContent());
        }
    }

    public void sendMail(Recipient recipient, String subjectString, String contentString){
        // Set up sender
        Message message = new MimeMessage(session);
        Email mail = recipient.formatMail(subjectString, contentString);

        String subject = mail.getSubject();
        String content = mail.getContent() + user.getName();
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

            // Add the mail to the sent mail list
            sentMails.add(mail);

        } catch (AddressException e) {
            System.err.println("Error : Invalid recipient address");
            e.printStackTrace();
        } catch (MessagingException e) {
            System.err.println("Error : Could not send the message successfully");
            // Retry connecting to the mail server
            System.out.println("Trying to reconnect");
            connectMailService();
            e.printStackTrace();
        }
    }
    
    public void writeSentMails(){
        MailWriter mailWriter = MailWriter.connectWriter();
        if(mailWriter != null){
            mailWriter.writeMail(sentMails);
            mailWriter.disconnectWriter();
        }
    }
    
    public void loadSentMails(){
        MailReader mailReader = MailReader.connectReader();
        if(mailReader.isNotNull()){
            sentMails = mailReader.readMail();
            mailReader.disconnectReader();
        }
    }

    public void printSentMails(){
        for(Email mail : sentMails)
            System.out.println(mail.toString());
    }

    public ArrayList<Email> filterMailByDate(String date){
        ArrayList<Email>  filteredMails = new ArrayList<Email>();
        for(Email mail : sentMails){
            if(mail.getTimeStamp().equals(date))
                filteredMails.add(mail);
        }

        return filteredMails;
    }
}

class User {
    private String email;
    private String password;
    private String name;
    
    
    public User(String name, String email, String password) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getName() {
        return name;
    }   
}