package util;

public class MailAddress {
    private String address;

    public String getAddress() {
        return address;
    }

    public MailAddress(String address) {
        if(address.contains("@") && address.contains(".com"))
            this.address = address;
        this.address=null;
    }
}
