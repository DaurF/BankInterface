package com.bank.account;

import com.bank.bank.Bank;
import com.bank.transaction.Transaction;
import com.bank.user.User;

import java.util.ArrayList;

public class Account implements IAccount {
    private String name; // Name of account

    private String uuid; // com.bank.account.Account ID Number

    private User holder; // com.bank.user.User that owns this account

    private ArrayList<Transaction> transactions; // The list of transactions for this account

    /**
     * Create a new com.bank.account.Account
     * @param name  the name of the account
     * @param holder    the com.bank.user.User object that holds this account
     * @param theBank   the bank that issues the account
     */

    public Account(String name, User holder, Bank theBank)
    {
        // set the account name and holder

        this.name = name;
        this.holder = holder;

        // get new account UUID
        this.uuid = theBank.getNewAccountUUID();

        // init transactions
        this.transactions = new ArrayList<Transaction>();


    }

    /**
     * Get the account ID
     * @return the uuid
     */
    @Override
    public String getUUID()
    {
        return this.uuid;
    }

    /**
     *  Get summary line for the account
     * @return  the string summary
     */
    @Override
    public String getSummaryLine()
    {
        // get the account's balance
        double balance = this.getBalance();

        // format the summary line, depending on the whether the balance is negative
        if (balance >= 0)
        {
            return String.format("%s : $%.02f : %s", this.uuid, balance, this.name);
        } else
        {
            return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name);
        }
    }

    /**
     * Get the balance of this account by adding the amounts of the transactions
     * @return  the balance value
     */
    @Override
    public double getBalance()
    {
        double balance = 0;
        for(Transaction t : this.transactions)
        {
            balance += t.getAmount();
        }

        return balance;
    }

    /**
     * Print the transaction history of the account
     */
    @Override
    public void printTransHistory()
    {
        System.out.printf("\ncom.bank.transaction.Transaction history for account %s\n", this.uuid);
        for(int t = this.transactions.size()-1; t >= 0; t--)
        {
            System.out.println(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }

    /**
     * Add a new transaction in this account
     * @param amount    the amount transacted
     * @param memo      the transaction memo
     */
    @Override
    public void addTransaction(double amount, String memo)
    {
        // create new transaction object and add it to our list
        Transaction newTrans = new Transaction(amount, memo, this);
        this.transactions.add(newTrans);
    }
}