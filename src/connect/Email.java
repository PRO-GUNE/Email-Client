package connect;

import java.io.Serializable;
import java.time.LocalDate;

import recipient.Recipient;

public class Email implements Serializable{
    private LocalDate timeStamp;
    
    private String subject;
    private String content;
    private String recipient;
    
    
    public Email(String subject, String content, Recipient recipient) {
        this.timeStamp = LocalDate.now();
        this.subject = subject;
        this.content = content;
        this.recipient = recipient.toString();
    }
    
    public String parseToString(){
        return String.format("Subject: %s \t To: %s", this.subject, this.recipient);
    }
    
    public String filterByDate(LocalDate date){
        if(this.timeStamp.equals(date))
        return this.parseToString();
        
        return null;
    }
    
    public String getRecipient() { return recipient;}
    public String getContent() { return content;}
    public String getSubject() { return subject;}
    public String getTimeStamp() { return timeStamp.toString();}
    
    @Override
    public String toString(){
        return String.format("Time:%s Subject:%s To:%s", timeStamp.toString(), subject, recipient);
    }
}
