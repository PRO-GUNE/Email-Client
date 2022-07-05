package recipient;

class Personnal extends Recipient implements Greetable{
    private String nickname;
    private String bday;
    
    public Personnal(String name, String nickname, String email, String bday) {
        super(name, email);
        this.nickname = nickname;
        this.bday = bday;
    }

    @Override
    public void greet() {
        // Logic to send the email
    }
}
