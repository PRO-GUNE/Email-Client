package recipient;

class OfficialFriend extends Recipient implements Greetable{
    private String bday;
    private String title;

    @Override
    public void greet() {
        // Logic to sent the greeting mail.
    }
}

