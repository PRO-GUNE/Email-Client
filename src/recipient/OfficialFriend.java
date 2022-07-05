package recipient;

class OfficialFriend extends Recipient implements Greetable{
    private String bday;
    private String title;

    public OfficialFriend(String name, String email, String title, String bday) {
        super(name, email);
        this.bday = bday;
        this.title = title;
    }


    @Override
    public void greet() {
        // Logic to sent the greeting mail.
    }
}

