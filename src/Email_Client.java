import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import connect.Email;
import connect.MailService;
import recipient.Recipient;
import recipient.RecipientFactory;

// your index number

//import libraries

public class Email_Client {
    private static boolean isRunning;
    public static void main(String[] args) {
        // Set the email client to running mode
        isRunning = true;
        
        // Create a recipientFactory
        RecipientFactory recipientFactory = new RecipientFactory();
        // load the current recipients
        recipientFactory.loadRecipients();

        // Create the mail service
        MailService mailService = MailService.connectMailService();
        // Load mail history
        mailService.loadSentMails();

        // Create the scanner to get user input
        Scanner scanner = new Scanner(System.in);  

        while(isRunning){
            
            // Check today's date and sent birthday greetings to the recipients
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            String date = formatter.format(new Date());
            ArrayList<Recipient> bDayRecipientList = recipientFactory.getRecipientByDate(date);
            mailService.sendGreeting(bDayRecipientList);
    
            // Create an instance of Recipient
            Recipient recipient = null;
            
            System.out.println("Enter option type: \n"
                    + "1 - Adding a new recipient\n"
                    + "2 - Sending an email\n"
                    + "3 - Printing out all the recipients who have birthdays\n"
                    + "4 - Printing out details of all the emails sent\n"
                    + "5 - Printing out the number of recipient objects in the application\n"
                    + "Any other key to exit");
    
            int option = scanner.nextInt();
            scanner.nextLine();
            
            switch(option){
                case 1:
                    String recipientString = scanner.nextLine();
                    // Create the recipient
                    recipient = recipientFactory.makeRecipient(recipientString);
                    break;
                case 2:
                        // input format - email,subject,content
                        String[] emailString = scanner.nextLine().split(",", 3);
                        String email = emailString[0];
                        String subjectString = emailString[1];
                        String contentString = emailString[2];
    
                        // code to send an email
                        recipient = recipientFactory.getRecipientByEmail(email);
                        mailService.sendMail(recipient, subjectString, contentString);
                        break;
                    case 3:
                        // input format - yyyy/MM/dd (ex: 2018/09/17)
                        date = scanner.nextLine();
                        
                        // code to print recipients who have birthdays on the given date
                        ArrayList<Recipient> bDayRecipients = recipientFactory.getRecipientByDate(date);
                        for(Recipient rec : bDayRecipients){
                            System.out.println(rec.toString());
                        } 
                        break;
                    case 4:
                        // input format - yyyy/MM/dd (ex: 2018/09/17)
                        String searchDate = scanner.nextLine();
                        // Get the filtered mail
                        ArrayList<Email> filteredMails = mailService.filterMailByDate(searchDate);
                        for(Email mail : filteredMails)
                            System.out.println(mail.toString());
                        
                        break;
                    case 5:
                        // code to print the number of recipient objects in the application
                        System.out.println(recipientFactory.getNumRecipients());
                        break;
                    
                    default:
                        isRunning=false;
                        break;
    
              }
              // start email client
              // code to create objects for each recipient in clientList.txt
              // use necessary variables, methods and classes
              mailService.writeSentMails();
              recipientFactory.writeRecipients();
      
            }

        // Close the scanner connection 
        scanner.close();

      }
}

// create more classes needed for the implementation (remove the  public access modifier from classes when you submit your code)