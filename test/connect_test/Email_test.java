package connect_test;

import org.junit.Test;

import connect.MailService;
import user.User;

public class Email_test {
    // This module tests the email sending and receiving
    
    @Test
    public void sendEmail(){
        User user = new User("Chathura", "cmggun567@gmail.com", "cmggun123");
        MailService mailService = MailService.connectMailService(user);
        
    }
}
