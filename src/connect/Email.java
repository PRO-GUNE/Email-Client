package connect;

import java.io.Serializable;
import java.time.LocalDate;
import recipient.Recipient;

public class Email implements Serializable{
    private LocalDate timeStamp;
    private String subject;
    private String content;
    private Recipient recipient;
    

    
    public Email(String subject, String content, Recipient recipient) {
        this.timeStamp = LocalDate.now();
        this.subject = subject;
        this.content = content;
        this.recipient = recipient;
    }

    public String parseToString(){
        return String.format("Subject: %s \t To: %s", this.subject, this.recipient.getName());
    }
    
    public String filterByDate(LocalDate date){
        if(this.timeStamp.equals(date))
        return this.parseToString();
        
        return null;
    }
    public String getContent() {
        return content;
    }
    
    public String getSubject() {
        return subject;
    }
    public Recipient getRecipient() {
        return recipient;
    }
}
