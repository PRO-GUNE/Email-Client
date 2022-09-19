package recipient;

import connect.Email;

public class Official extends Recipient{    
    protected String title;

    public Official(String name, String email, String title) {
        super(name, email);
        this.title = title;
        this.type = "Official";
    }

    public String getTitle() {    return title;}
    
    @Override
    public Email formatMail(String subject, String content){
        String sendContent = String.format("Dear %s,\n%s\n", this.title, content);
        Email mail = new Email(subject, sendContent, this);
        return mail;
    }

    @Override
    public String toString() {
        return String.format("%s: %s,%s,%s\n", type, name, email, title);
    }
    

}