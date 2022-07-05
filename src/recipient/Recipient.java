package recipient;
/**
 * Recipient - This is the class to represent the Recipient
 * There are two recipient types - official and personnal
 * Some official people are close freinds.
 * Official friends and close friends should recieve get a birthday greeting
 * 
 * Implementation - Sub classes o recipeients - Offcial, OfficialFriend, Personnal
 * Greetable interface - has a method to greet on the bday - it is implemented by the OfficialFriend and Personnal classes
 */
public class Recipient {
    protected String name;
    protected String email;
}

