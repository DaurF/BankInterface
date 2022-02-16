import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;
public class User {
    private String firstName; // The First Name
    private String lastName; // The Last Name
    private String uuid; // ID
    private byte pinHash[]; // User's pin number
    private ArrayList<Account> accounts; // The list of accounts of the user

    public User(String firstName, String lastName, String pin, Bank bank) // Setter method
    {
        this.firstName = firstName;
        this.lastName = lastName;

        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes())
        } catch (NoSuchAlgorithmException e)
        {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

    }
}
