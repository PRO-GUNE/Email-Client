package recipient_test;
import recipient.Greetable;
import recipient.RecipientFactory;
import recipient.Recipient;
import util.MailAddress;

import org.junit.Assert;
import org.junit.Test;

public class RecipientFactoryTest {
    // Test the make recipient functions
    private RecipientFactory recipientFactory;
    
    public RecipientFactoryTest() {
        this.recipientFactory = new RecipientFactory();
    }

    @Test
    public void makeRecipient_test(){
        MailAddress mail = new MailAddress("nimal@gmail.com");
        Recipient official = this.recipientFactory.makeRecipient("nimal",mail,"ceo"); 
        Assert.assertNotNull(official);
        Assert.assertTrue(official instanceof Recipient);
        Assert.assertFalse(official instanceof Greetable);

        Recipient officialFriend = this.recipientFactory.makeRecipient("nimal",mail,"ceo", "2000/09/21");
        Assert.assertNotNull(officialFriend);
        Assert.assertTrue(officialFriend instanceof Recipient);
        Assert.assertTrue(officialFriend instanceof Greetable);

        Recipient personnal = this.recipientFactory.makeRecipient("nimal","nima",mail,"2000/09/21");
        Assert.assertNotNull(personnal);
        Assert.assertTrue(personnal instanceof Recipient);
        Assert.assertTrue(personnal instanceof Greetable);

    }
}
