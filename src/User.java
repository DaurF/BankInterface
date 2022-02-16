import java.util.ArrayList;

public class User {
    private String userName; // The First Name
    private String lastName; // The Last Name
    private String uuid; // ID
    private byte pinHash[]; // User's pin number
    private ArrayList<Account> accounts; // The list of accounts of the user
}
