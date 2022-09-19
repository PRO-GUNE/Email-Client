package connect;

import java.util.ArrayList;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import recipient.Greetable;
import recipient.Recipient;

public class MailService{
 // This is a singleton object that connects to the Email Service
 private static MailService mailService;
 private MailUser user;
 private String email = "cmggun567@gmail.com";
 private String password = "vevhjqahkaolvrge";
 private String name = "Chathura";
 private Session session;
 private ArrayList<Email> sentMails;
 
 private MailService(){
     // Create the file to save send emails if it is not available
     MailWriter.createFileIfNotAvailable();

     // Create the user object
     this.user = new MailUser(name, email, password);

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

 public void sendGreeting(ArrayList<Greetable> greetables, String date){
     System.out.println("Sending Birthday Wishes asynchronously");
     ArrayList<Email> sentmails = filterMailByDate(date);

     
     for(Greetable greetable : greetables){
         Recipient recipient = (Recipient)greetable;
         boolean sent = false;
         for(Email mail : sentmails){
             if(mail.getSubject().equals("Happy Birthday") && mail.getEmail().equals(recipient.getMail())){
                 sent = true;
                 break;
             }
         }
         if(!sent){
             Email mail = greetable.greet();
             sendMail(recipient, mail.getSubject(), mail.getContent());
         }
     }
 }

 public void sendUnformattedMail(String email, String subject, String content){
     // Setup the sender
     Message message = new MimeMessage(session);
     Email mail = new Email(subject, content, email);

     try {
         // Create the message
         message.setFrom(new InternetAddress(email));
         message.setRecipients(
                 Message.RecipientType.TO,
                 InternetAddress.parse(email)
         );
         message.setSubject(subject);
         message.setText(content);

         // Send the message asynchronously
         Thread sender = new Thread(new AsyncTransport(message), "sender");
         sender.start();

     } catch (AddressException e) {
         System.err.println("Error : Invalid recipient address");
         
     } catch (MessagingException e) {
         System.err.println("Error : Could not send the message successfully");
         // Retry connecting to the mail server
         System.out.println("Trying to reconnect");
         connectMailService();   
     } finally{
         // Add the email to the sent list
         sentMails.add(mail);
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

         // Send the message asynchronously
         Thread sender = new Thread(new AsyncTransport(message), "sender");
         sender.start();

     } catch (AddressException e) {
         System.err.println("Error : Invalid recipient address");
         
     } catch (MessagingException e) {
         System.err.println("Error : Could not send the message successfully");
         // Retry connecting to the mail server
         System.out.println("Trying to reconnect");
         connectMailService();   
     } finally{
         // Add the email to the sent list
         sentMails.add(mail);
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

// Class to represent the person sending the mail
class MailUser {
    private String email;
    private String password;
    private String name;
    
    
    public MailUser(String name, String email, String password) {
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

// Class to implement concurrency when sending the mail
class AsyncTransport implements Runnable{
    private Message message;

    public AsyncTransport(Message message){
        this.message = message;
    }
    @Override
    public void run() {
        try {
            Transport.send(this.message);
        } catch (MessagingException e) {
            System.err.println("Error : Could not send mail");   
        }
    }

}