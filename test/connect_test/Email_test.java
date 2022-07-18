package connect_test;

import javax.mail.internet.MimeMessage;

import org.junit.Assert;
import org.junit.Test;

import connect.Email;
import connect.MailService;
import recipient.Recipient;
import user.User;

public class Email_test {
    // This module tests the email sending and receiving
    
    @Test
    public void sendEmail(){
        User user = new User("Chathura", "cmggun567@gmail.com", "uzxlvgylnicxseyn");
        MailService mailService = MailService.connectMailService(user);
        Assert.assertNotNull(mailService);
        Assert.assertNotNull(mailService.getSession());
        Recipient recipient = new Recipient("chathura", "cmg456@gmail.com");
        Assert.assertNotNull(recipient);
        Email mail = new Email("wishing you", "Happy birthday", recipient);
        Assert.assertNotNull(mail);

        // Send the mail
        mailService.sendMail(mail);
    }
}
