package connect;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MailReader {
    private static MailReader mailReader;
    private FileInputStream fileInputStream;
    private ObjectInputStream objectInputStream;
    private static final String filePath = ".\\data\\emailHistory.ser";


    private MailReader(){
        try {
            this.fileInputStream = new FileInputStream(filePath);
            this.objectInputStream = new ObjectInputStream(this.fileInputStream);
        } catch(EOFException e){
            System.err.println("Warning : The history file is empty");
            return;
        } 
        catch (IOException e) {
            System.err.println("Error : Could not connect to the logger file");
            
        }
    }

    public boolean isNotNull(){
        return (mailReader.objectInputStream!=null);
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
            
        }
    }

    public ArrayList<Email> readMail(){
        ArrayList<Email> emails = new ArrayList<Email>();
        try {
            Object obj = this.objectInputStream.readObject();
            ArrayList<?> ar = (ArrayList<?>) obj;

            for(Object mail : ar)
                emails.add((Email) mail);
                
        } catch (ClassNotFoundException e) {
            System.err.println("Error : Could not find the class Email");
            
        } catch (IOException e) {
            System.err.println("Error : Could not read from the log file");
            
        }
        return emails;
    }

}
