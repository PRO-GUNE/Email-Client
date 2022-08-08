package recipient;

import connect.Email;

/**
 * Recipient - This is the class to represent the Recipient
 * There are two recipient types - official and personnal
 * Some official people are close freinds.
 * Official friends and close friends should recieve get a birthday greeting
 * 
 * Implementation - Sub classes o recipeients - Offcial, OfficialFriend, Personal
 * Greetable interface - has a method to greet on the bday - it is implemented by the OfficialFriend and Personal classes
 */
public abstract class Recipient{
    protected String name;
    protected String email;
    protected String type;
    
    public static int recipientCount;
    
    public Recipient(String name, String email){
      this.name = name;
      this.email = email;
      recipientCount++;
    }
    
    public String getMail(){   return email;}
    public String getName(){   return name; } 
    public String getType(){   return type; }

    public abstract Email formatMail(String subject, String content);

    public abstract String toString();
    
    public static Recipient parseRecipientFromString(String recipientString){
      String[] data = recipientString.split(":");
      data[1] = data[1].replaceAll(" ", "");      // Remove whitespace from the arguments
      String[] args = data[1].split(",");
      String type = data[0];


      // Create the recipient
      Recipient recipient = null;
        if(type.equalsIgnoreCase("Official"))
            recipient = new Official(args[0], args[1], args[2]);
        else if(type.equalsIgnoreCase("Official_friend"))
            recipient = new OfficialFriend(args[0], args[1], args[2], args[3]);
        else if(type.equalsIgnoreCase("Personal"))
            recipient = new Personal(args[0], args[1], args[2], args[3]);
        
        return recipient;
  }
}
