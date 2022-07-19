package recipient;

import connect.Email;

public interface Greetable {
    public Email greet();

    public static boolean isSameDates(String date1, String date2){
        return (date1.substring(3, date1.length()-1).
                    equalsIgnoreCase(date2.substring(3, date2.length()-1)));
    };
}
