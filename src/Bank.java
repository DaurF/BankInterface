import java.util.ArrayList;
import java.util.Random;

public class Bank
{
    private String name;

    private ArrayList<User> users;

    private ArrayList<Account> accounts;

    /**
     * Generate a new universally unique ID for a user.
     * @return  the uuid
     */
    public String getNewUserUUID()
    {
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;

        // continue looping until we get a unique ID
        do {
            // generate the number
            uuid = "";
            for(int i = 0; i < len; i++)
            {
                uuid += ((Integer)rng.nextInt(10)).toString();
            }

            // check to be sure it is unique
            nonUnique = false;
            for(User u : this.users)
            {
                if(uuid.compareTo(u.getUUID()) == 0;)
                {
                    nonUnique = true;
                    break;
                }
            }
        } while(nonUnique)

        return uuid;

    }

    public String getNewAccountUUID()
    {

    }

    /**
     * Add an account
     * @param anAcct    the account to add
     */
    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);
    }
}
