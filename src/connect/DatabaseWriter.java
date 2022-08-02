package connect;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import recipient.Recipient;

public class DatabaseWriter {
    private static DatabaseWriter databaseWriter;
    private FileWriter fileWriter;
    private static final String filePath = ".\\data\\clientList.txt";
    
    private DatabaseWriter(){
        try {
            this.fileWriter = new FileWriter(filePath);
        } catch (IOException e){
            System.err.println("Error : Could not open the file");
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
            this.fileWriter.close();
            databaseWriter = null;
        } catch (IOException e) {
            System.err.println("Error : Could not close the file");
            e.printStackTrace();
        }

    }

    public void writeRecipients(ArrayList<Recipient> recipients){
        for(Recipient rec : recipients){
            try {
                fileWriter.write(rec.toString());
            } catch (IOException e) {
                System.err.println("Error : Could not write to the file");
                e.printStackTrace();
            }
        }
    }

    public static void createFileIfNotAvailable(){
        File file = new File(filePath);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error : Could not create the clientList.txt file");
                e.printStackTrace();
            }
        }
    }
}
