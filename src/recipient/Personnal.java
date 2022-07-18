package recipient;

import java.io.IOException;

import connect.Email;
import user.User;

class Personnal extends Recipient implements Greetable{
    private String nickname;
    private String bday;
    
    public Personnal(String name, String nickname, String email, String bday) {
        super(name, email);
        this.nickname = nickname;
        this.bday = bday;
    }

    @Override
    public void greet(User user) {
        try {
            Email email = Email.connectEmail(user);
            String content = "Hugs and love on your birthday.\n"+user.getName();
            String subject = "Happy Birthday";

            email.sendMail(this, subject, content);
        } catch (IOException e) {
            System.err.println("Error : Could not send greeting mail to personnal friend");
            e.printStackTrace();
        }
        
    }
}
