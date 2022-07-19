package recipient;

import connect.Email;

public class Official extends Recipient{
    
    public Official(String name, String email, String title) {
        super(name, email);
        this.title = title;
        this.type = "Official";
    }
    
    public Email formatMail(String subject, String content){
        String sendContent = String.format("Dear %s,\n%s\n", this.title, content);
        Email mail = new Email(subject, sendContent, this);
        return mail;
    }

    @Override
    public String toString() {
        return String.format("%s: %s,%s,%s", type, name, email, title);
    }
    

}
