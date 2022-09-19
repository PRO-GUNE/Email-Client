package connect;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

import recipient.Recipient;

public class Email implements Serializable{
    private String timeStamp;
    
    private String subject;
    private String content;
    private String recipient;
    private String email;
    
    private static final long serialversionUID = 129348938L;
    
    
    public Email(String subject, String content, Recipient recipient) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        this.timeStamp = formatter.format(new Date());
        this.subject = subject;
        this.content = content;
        this.recipient = recipient.toString();
        this.email = recipient.getMail();
    }
    
    public Email(String subject, String content, String email){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        this.timeStamp = formatter.format(new Date());
        this.subject = subject;
        this.content = content;
        this.recipient = email;
        this.email = email;
    }
    
    public String parseToString(){
        return String.format("Subject: %s \t To: %s", this.subject, this.recipient);
    }
    
    public String filterByDate(String date){
        if(this.timeStamp.equals(date))
        return this.parseToString();
        
        return null;
    }
    
    public String getEmail() {  return email;}
    public String getRecipient() { return recipient;}
    public String getContent() { return content;}
    public String getSubject() { return subject;}
    public String getTimeStamp() { return timeStamp.toString();}
    
    @Override
    public String toString(){
        return String.format("Time:%s Subject:%s To:%s", timeStamp.toString(), subject, recipient.toString());
    }
}