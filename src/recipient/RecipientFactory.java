package recipient;

import java.util.ArrayList;

import connect.DatabaseReader;
import connect.DatabaseWriter;

public class RecipientFactory {
    private ArrayList<Recipient> recipients;
    
    public RecipientFactory(){
        this.recipients = new ArrayList<Recipient>();

        // Create the file to store the recipients if it is not availble
        DatabaseWriter.createFileIfNotAvailable();
    }

    public Recipient makeRecipient(String recipientString){
        Recipient recipient = Recipient.parseRecipientFromString(recipientString);
        recipients.add(recipient);
        return recipient;
    }

    public Recipient getRecipientByEmail(String email){
        for(Recipient recipient : recipients){
            if(recipient.getMail().equals(email))
                return recipient;
        }

        return null;
    }

    public ArrayList<Recipient> getRecipientByDate(String date){
        // Only greetable recipients have birthdays
        ArrayList<Recipient> bdayRecipients = new ArrayList<Recipient>();
        
        for(Recipient recipient : recipients){
            if(recipient instanceof Greetable && 
                Greetable.isSameDates(recipient.getbDay(), date)){
                    bdayRecipients.add(recipient);
                }
        }
        
        return bdayRecipients;
    }

    public int getNumRecipients(){
        return recipients.size();
    }

    public void loadRecipients(){
        DatabaseReader databaseReader = DatabaseReader.connectDBReader();
        if(databaseReader != null){
            recipients = databaseReader.readRecipients();
            databaseReader.closeDBReader();
        }
    }
    
    public void writeRecipients(){
        DatabaseWriter databaseWriter = DatabaseWriter.connectDBWriter();
        if(databaseWriter != null){
            databaseWriter.writeRecipients(recipients);
            databaseWriter.closeDBWriter();
        }
    }

    public void printRecipients(){
        for(Recipient rec : recipients){
            System.out.println(rec.toString());
        }
    }
}
