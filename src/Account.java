import java.util.ArrayList;

public class Account {
    private String name; // Name of account

    private double balance; // Current balance

    private String uuid; // Account ID Number

    private User holder; // User that owns this account

    private ArrayList<Transaction> transactions; // The list of transactions for this account
}