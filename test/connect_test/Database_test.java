package connect_test;

import org.junit.Assert;
import org.junit.Test;

import connect.DatabaseReader;
import connect.DatabaseWriter;
import recipient.Recipient;

public class Database_test {
    // Test for reading and writing to the Database
    private DatabaseReader dReader;
    private DatabaseWriter dWriter;
    
    @Test
    public void DatabaseWriter_test(){
        Recipient recipient = new Recipient("nimal", "nimal@gmail.com");
        this.dWriter = DatabaseWriter.connectDBWriter();
        Assert.assertNotNull(this.dWriter);
        this.dWriter.writeRecipient(recipient);
        this.dWriter.closeDBWriter();
    }

    @Test
    public void DatabaseReader_test(){
        this.dReader = DatabaseReader.connectDBReader();
        Assert.assertNotNull(this.dReader);
        Recipient recipient = this.dReader.readRecipient();
        Assert.assertNotNull(recipient);
        Assert.assertTrue(recipient instanceof Recipient);
        Assert.assertEquals("nimal", recipient.getName());
        Assert.assertEquals("nimal@gmail.com", recipient.getMail());
        this.dReader.closeDBReader();
    }

    
}