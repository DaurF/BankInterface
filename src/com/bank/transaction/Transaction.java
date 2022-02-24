package com.bank.transaction;

import com.bank.account.Account;

import java.util.Date;

public class Transaction implements ITransaction {

    private double amount; //Amount of this transaction
    private Date timestamp; // Time and date of this transaction
    private String memo; //Memo for this transaction
    private Account inAccount; //com.bank.account.Account in which the transaction was performed

    /**
     *
     * @param amount the amount transacted
     * @param inAccount the account the transaction belongs to
     */
    public Transaction(double amount, Account inAccount){ //Create new transaction
        this.amount = amount; // set the amount
        this.inAccount = inAccount; // set the inAccount
        this.timestamp = new Date();
        this.memo = "";
    }

    /**
     *
     * @param amount the amount transacted
     * @param memo the memo for the transaction
     * @param inAccount the account the transaction belongs to
     */
    public Transaction(double amount, String memo, Account inAccount){ //Create new transaction
        this(amount, inAccount); //call two arg constructor first
        this.memo = memo; //set the memo

    }

    /**
     * Get the amount of yhe transaction
     * @return the amount$
     */
    public double getAmount(){
        return this.amount;
    }

    /**
     * Get string summarizing the transaction
     * @return the summary string
     */
    public String getSummaryLine(){
        if(this.amount >= 0){
            return String.format("%s : $%.02f : %s", this.timestamp.toString(), this.amount, this.memo);
        }else{
            return String.format("%s : $(%.02f) : %s", this.timestamp.toString(), -this.amount, this.memo);
        }
    }
}
