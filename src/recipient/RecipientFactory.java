package recipient;

import java.util.ArrayList;

import connect.DatabaseReader;
import connect.DatabaseWriter;

public class RecipientFactory {
    
    private ArrayList<Recipient> recipients;    // Store all the recipients
    private int numNewRecipients;               // Store the number of newly added recipients

    public RecipientFactory(){
        this.recipients = new ArrayList<Recipient>();
        this.numNewRecipients = 0;

        // Create the file to store the recipients if it is not availble
        DatabaseWriter.createFileIfNotAvailable();
    }

    public Recipient makeRecipient(String recipientString){
        Recipient recipient = Recipient.parseRecipientFromString(recipientString);
        recipients.add(recipient);
        numNewRecipients+=1;
        return recipient;
    }

    public Recipient getRecipientByEmail(String email){
        for(Recipient recipient : recipients){
            if(recipient.getMail().equals(email))
                return recipient;
        }

        return null;
    }

    public ArrayList<Greetable> getRecipientByDate(String date){
        // Only greetable recipients have birthdays
        ArrayList<Greetable> bdayRecipients = new ArrayList<Greetable>();
        
        for(Recipient recipient : recipients){
            if(recipient instanceof Greetable){
                Greetable greetable=null;
                if(recipient.getType().equals("Personal"))
                    greetable = (Personal)recipient;
                else if(recipient.getType().equals("Official_friend"))
                    greetable = (OfficialFriend)recipient;

                if(Greetable.isSameDates(greetable.getbDay(),date)){
                    bdayRecipients.add(greetable);   
                }
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
            databaseWriter.writeRecipients(recipients, numNewRecipients);
            databaseWriter.closeDBWriter();
        }
    }

    public void printRecipients(){
        for(Recipient rec : recipients){
            System.out.println(rec.toString());
        }
    }
}
