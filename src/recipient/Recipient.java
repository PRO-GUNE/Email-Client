package recipient;

import connect.Email;

/**
 * Recipient - This is the class to represent the Recipient
 * There are two recipient types - official and personnal
 * Some official people are close freinds.
 * Official friends and close friends should recieve get a birthday greeting
 * 
 * Implementation - Sub classes o recipeients - Offcial, OfficialFriend, Personnal
 * Greetable interface - has a method to greet on the bday - it is implemented by the OfficialFriend and Personnal classes
 */
public abstract class Recipient{
    protected String name;
    protected String email;
    protected String title;
    protected String nickName;    
    protected String bDay;
    protected String type;
    
    public static int recipientCount;
    
    public Recipient(String name, String email){
      this.name = name;
      this.email = email;
      recipientCount++;
    }
    
    public String getTitle() {    return title;}
    public String getNickName() {      return nickName;}
    public String getbDay() {      return bDay;}
    public String getMail(){   return email;}
    public String getName(){   return name; } 
    public String getType(){   return type; }

    public abstract Email formatMail(String subject, String content);

    public abstract String toString();
    
    public static Recipient parseRecipientFromString(String recipientString){
      String[] data = recipientString.split(":");
      String[] args = data[1].split(",");
      String type = data[0];

      // Create the recipient
      Recipient recipient = null;
        if(type.equalsIgnoreCase("Official"))
            recipient = new Official(args[0], args[1], args[2]);
        else if(type.equalsIgnoreCase("Official_friend"))
            recipient = new OfficialFriend(args[0], args[1], args[2], args[3]);
        else if(type.equalsIgnoreCase("Personnal"))
            recipient = new Personnal(args[0], args[1], args[2], args[3]);
        
        return recipient;
  }
  }
