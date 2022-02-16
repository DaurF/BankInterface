import java.util.Date;

public class Transaction {

    private double amount; //Amount of this transaction
    private Date timestamp; // Time and date of this transaction
    private String memo; //Memo for this transaction
    private Account inAccount; //Account in which the transaction was performed

    public Transaction(double amount, Account inAccount){ //Create new transaction
        this.amount = amount; // set the amount
        this.inAccount = inAccount; // set the inAccount
        this.timestamp = new Date();
        this.memo = "";
    }

    public Transaction(double amount, String memo, Account inAccount){ //Create new transaction
        this(amount, inAccount); //call two arg constructor first
        this.memo = memo; //set the memo


    }
}
