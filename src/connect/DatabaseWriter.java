package connect;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import recipient.Recipient;

public class DatabaseWriter {
    private static DatabaseWriter databaseWriter;
    private ObjectOutputStream objectOutputStream;
    private FileOutputStream fileOutputStream;
    private static final String filePath = "G:\\University\\Semester_02\\CS\\CS 1040\\Code Snippets\\Assessments\\4_EmailClient\\Code\\Email Client\\data\\clientList.txt";


    
    private DatabaseWriter() {
        try {
            this.fileOutputStream = new FileOutputStream(filePath);
            this.objectOutputStream = new ObjectOutputStream(this.fileOutputStream);
        }catch(FileNotFoundException e){
            System.err.println("Error : File was not found");
            e.printStackTrace();
        } catch(EOFException e){
            System.err.println("Warning : End of file reached wihout an object being read");
        }
        catch (IOException e) {
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

    public void writeRecipient(ArrayList<Recipient> recipients){
        try {
            this.objectOutputStream.writeObject(recipients);
        } catch (IOException e) {
            System.err.println("Error : Could not write recipient to database");
            e.printStackTrace();
        }
    }
}
