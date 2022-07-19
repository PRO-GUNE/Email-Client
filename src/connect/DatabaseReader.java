package connect;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import recipient.Recipient;

public class DatabaseReader {
    private static DatabaseReader databaseReader;
    private Scanner scanner;
    private static final String filePath = "G:\\University\\Semester_02\\CS\\CS 1040\\Code Snippets\\Assessments\\4_EmailClient\\Code\\Email Client\\data\\clientList.txt";
    
    private DatabaseReader(){
        try {
            this.scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            System.err.println("Error : Could not open the file");
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
        scanner.close();
        databaseReader = null;

    }

    public ArrayList<Recipient> readRecipients(){
        ArrayList<Recipient> recipients = new ArrayList<Recipient>();
        while (scanner.hasNextLine()) {
            recipients.add(Recipient.parseRecipientFromString(scanner.nextLine()));
        }
    
        return recipients;
    }
}
