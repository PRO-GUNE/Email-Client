package connect;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MailWriter {
    private static MailWriter mailWriter;
    private FileOutputStream fileOutputStream;
    private ObjectOutputStream objectOutputStream;

    private MailWriter(){
        try {
            this.fileOutputStream = new FileOutputStream("./emailHistory.ser");
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

    public void writeMmail(Email email){

    }

}
