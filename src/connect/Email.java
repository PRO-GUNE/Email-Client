package connect;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import recipient.Recipient;

public class Email {
    // This is a singleton object that connects to the Email Service
    private static Email email;
    private static FileOutputStream Email_Stream;
    private String address;
    private String password;

    private Email(String address, String password) throws FileNotFoundException{
        this.address = address;
        this.password = password;
        Email_Stream = new FileOutputStream("../../lib/emailHistory.txt"); 
    }

    public static Email connectDB(String address, String password) throws FileNotFoundException{
        if(email==null){
            email = new Email(address, password);
        }
        return email;
    }

    public void sendMail(Recipient recipient){
        // Logic to send the email
    }
}
