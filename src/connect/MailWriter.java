package connect;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MailWriter {
    private static MailWriter mailWriter;
    private FileOutputStream fileOutputStream;
    private ObjectOutputStream objectOutputStream;
    private static final String filePath = "G:\\University\\Semester_02\\CS\\CS 1040\\Code Snippets\\Assessments\\4_EmailClient\\Code\\Email Client\\data\\emailHistory.txt";


    private MailWriter(){
        try {
            this.fileOutputStream = new FileOutputStream(filePath);
            this.objectOutputStream = new ObjectOutputStream(this.fileOutputStream);
        } catch (IOException e) {
            System.err.println("Error : Could not create connection with the log file");
            e.printStackTrace();
        }
    }

    public static MailWriter connectWriter(){
        if(mailWriter==null){
            mailWriter = new MailWriter();
        }
        return mailWriter;
    }

    public void disconnectWriter(){
        try {
            this.objectOutputStream.close();
            this.fileOutputStream.close();
            mailWriter = null;
        } catch (IOException e) {
            System.err.println("Error : Could not disconnect the log file");
            e.printStackTrace();
        }
    }

    public void writeMail(ArrayList<Email> emails){
        try {
            this.objectOutputStream.writeObject(emails);
        } catch (IOException e) {
            System.err.println("Error : Could not write send mail to mail history");
            e.printStackTrace();
        }

    }

}
