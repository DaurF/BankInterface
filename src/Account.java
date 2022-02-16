import java.util.ArrayList;

public class Account {
    private String name; // Name of account

    private String uuid; // Account ID Number

    private User holder; // User that owns this account

    private ArrayList<Transaction> transactions; // The list of transactions for this account

    public Account(String name, User holder, Bank theBank)
    {
        // set the account name and holder

        this.name = name;
        this.holder = holder;

        // get new account UUID
        this.uuid = theBank.getNewAccountUUID();

        // init transactions
        this.transactions = new ArrayList<Transaction>();

        // add to holder and bank lists

        holder.addAccount(this);
        theBank.addAccount(this);
    }
}