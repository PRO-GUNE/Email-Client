package recipient;

class Official extends Recipient{
    private String title;

    public Official(String name, String email, String title) {
        super(name, email);
        this.title = title;
    }


    
}
