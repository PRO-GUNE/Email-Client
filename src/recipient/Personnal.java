package recipient;

import connect.Email;

public class Personnal extends Recipient implements Greetable{
    public Personnal(String name, String nickname, String email, String bDay) {
        super(name, email);
        this.nickName = nickname;
        this.bDay = bDay;
        this.type = "Personnal";
    }

    @Override
    public Email greet() {
        String content = "Hugs and love on your birthday.\n";
        String subject = "Happy Birthday";
        Email mail = new Email(subject, content, this);
        return mail;
    }

    @Override
    public Email formatMail(String subject, String content) {
        String sendContent = String.format("Dear %s,\n%s\n", this.nickName, content);
        Email mail = new Email(subject, sendContent, this);
        return mail;
        
    }

    @Override
    public String toString() {
        return String.format("%s: %s,%s,%s,%s", type, name, nickName, email, bDay);

    }

}
