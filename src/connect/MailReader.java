package connect;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MailReader {
    private static MailReader mailReader;
    private FileInputStream fileInputStream;
    private ObjectInputStream objectInputStream;

    private MailReader(){
        try {
            this.fileInputStream = new FileInputStream("./emailHistory.ser");
            this.objectInputStream = new ObjectInputStream(this.fileInputStream);
        } catch (IOException e) {
            System.err.println("Error : Could not connect to the logger file");
            e.printStackTrace();
        }
    }

    public static MailReader connectReader(){
        if(mailReader==null){
            mailReader = new MailReader();
        }
        return mailReader;
    }

    public void disconnectReader(){
        try {
            this.objectInputStream.close();
            this.fileInputStream.close();
            mailReader = null;
        } catch (IOException e) {
            System.err.println("Error : Could not disconnect the log file");
            e.printStackTrace();
        }
    }

    public Email readMail(){
        Email email = null;
        try {
            email = (Email) this.objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            System.err.println("Error : Could not find the class Email");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error : Could not read from the log file");
            e.printStackTrace();
        }
        return email;
    }

}
