package recipient;

import connect.Email;

public class OfficialFriend extends Official implements Greetable{
    
    public OfficialFriend(String name, String email, String title, String bDay) {
        super(name, email, title);
        this.bDay = bDay;
        this.type = "Official_friend";
    }

    
    @Override
    public Email formatMail(String subject, String content) {
        String sendContent = String.format("Dear %s,\n%s\n", this.title, content);
        Email mail = new Email(subject, sendContent, this);
        return mail;
    }
    
    @Override
    public Email greet() {
        String content = "Wish you a Happy Birthday.\n";
        String subject = "Happy Birthday";

        Email mail = new Email(subject, content, this);
        return mail;
    }

    @Override
    public String toString() {
        return String.format("%s: %s,%s,%s,%s\n", type, name, email, title, bDay);
    }
    
    
}

