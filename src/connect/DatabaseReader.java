package connect;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import recipient.Recipient;

public class DatabaseReader {
    private static DatabaseReader databaseReader;
    private ObjectInputStream objectInputStream;
    private FileInputStream fileInputStream;

    private DatabaseReader(){
        try {
            this.fileInputStream = new FileInputStream("./clientList.ser");
            this.objectInputStream = new ObjectInputStream(this.fileInputStream);
        } catch (IOException e) {
            System.err.println("Error : Could not create file write connection");
            e.printStackTrace();
        }
    }

    public static DatabaseReader connectDBReader(){
        if(databaseReader==null){
            databaseReader = new DatabaseReader();
        }
        return databaseReader;
    }

    public void closeDBReader(){
        try {
            this.fileInputStream.close();
            this.objectInputStream.close();
            databaseReader = null;
        } catch (IOException e) {
            System.err.println("Error : Could not close database write connection successfully");
            e.printStackTrace();
        }
    }

    public Recipient readRecipient(){
        Recipient recipient = null;
        try {
            recipient = (Recipient) this.objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            System.err.println("Error : Could not find the class recipient");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error : Could not read from the database");
            e.printStackTrace();
        }
        return recipient;
    }
}
