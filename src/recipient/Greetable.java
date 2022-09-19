package recipient;

import connect.Email;

public interface Greetable {
    public Email greet();
    public String getbDay();

    public static boolean isSameDates(String date1, String date2){
        return (date1.substring(5, date1.length()).
                    equalsIgnoreCase(date2.substring(5, date1.length())));
    };
}

