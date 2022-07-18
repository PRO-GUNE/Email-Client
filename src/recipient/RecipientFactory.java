package recipient;

import util.MailAddress;

public class RecipientFactory {
    // Overloaded functions to create appropriate recipient
    public Recipient makeRecipient(String name,MailAddress email,String title){
        return new Official(name, email.getAddress(), title);
    };

    public Recipient makeRecipient(String name,MailAddress email,String title,String bday){
        return new OfficialFriend(name, email.getAddress(), title, bday);
    }

    public Recipient makeRecipient(String name,String nickname,MailAddress email,String bday){
        return new Personnal(name, nickname, email.getAddress(), bday);
    }
}
