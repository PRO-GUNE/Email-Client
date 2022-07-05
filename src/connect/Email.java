package connect;

import recipient.Recipient;

public class Email {
    // This is a singleton object that connects to the Email Service
    private static Email email;
    private static final 
    private String address;
    private String password;

    private Email(String address, String password){
        this.address = address;
        this.password = password;
    }

    public static Email connectDB(String address, String password){
        if(email==null){
            email = new Email(address, password);
        }
        return email;
    }

    public void sendMail(Recipient recipient){
        // Logic to send the email
    }
}
