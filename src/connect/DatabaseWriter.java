package connect;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import recipient.Recipient;

public class DatabaseWriter {
    private static DatabaseWriter databaseWriter;
    private ObjectOutputStream objectOutputStream;
    private FileOutputStream fileOutputStream;
    
    private DatabaseWriter() {
        try {
            this.fileOutputStream = new FileOutputStream("./clientList.ser");
            this.objectOutputStream = new ObjectOutputStream(this.fileOutputStream);
        } catch (IOException e) {
            System.err.println("Error : Could not create read connection to the database");
            e.printStackTrace();
        }
    }

    public static DatabaseWriter connectDBWriter(){
        if(databaseWriter==null){
            databaseWriter = new DatabaseWriter();
        }
        return databaseWriter;
    }

    public void closeDBWriter(){
        try {
            this.objectOutputStream.close();
            this.fileOutputStream.close();
            databaseWriter=null;
        } catch (IOException e) {
            System.err.println("Could not close connection");
            e.printStackTrace();
        }
        
    }

    public void writeRecipient(Recipient recipient){
        try {
            this.objectOutputStream.writeObject(recipient);
        } catch (IOException e) {
            System.err.println("Error : Could not write recipient to database");
            e.printStackTrace();
        }
    }
}
