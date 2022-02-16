import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;
public class User {
    private String firstName; // The First Name
    private String lastName; // The Last Name
    private String uuid; // ID
    private byte pinHash[]; // User's pin number
    public ArrayList<Account> accounts; // The list of accounts of the user

    /**
     * Create a new user
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param pin the user's account pin number
     * @param bank the bank object that the user is a customer of
     */

    public User(String firstName, String lastName, String pin, Bank bank) // Setter method
    {
        this.firstName = firstName;
        this.lastName = lastName;

        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e)
        {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        // get a unique ID for the user
        this.uuid = Bank.getNewUserUUID();

        // create empty list of accounts
        this.accounts = new ArrayList<Account>();

        // print log message
        System.out.println("New user %s, %s with ID %s created.\n", lastName, firstName, this.uuid);
    }

    public void addAccount(Account anAcct)
    {
        this.accounts.add(anAcct);
    }
}
