package recipient;
import user.User;

class OfficialFriend extends Recipient implements Greetable{
    private String bday;
    private String title;

    public OfficialFriend(String name, String email, String title, String bday) {
        super(name, email);
        this.bday = bday;
        this.title = title;
    }


    @Override
    public void greet(User user) {
        String content = "Wish you a Happy Birthday.\n"+user.getName();
        String subject = "Happy Birthday";
    }
}

