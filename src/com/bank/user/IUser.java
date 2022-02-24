package com.bank.user;

import com.bank.account.Account;

public interface IUser {
    public void addAccount(Account acct);
    public String getUUID();
    public boolean validatePin(String pin);
    public String getFirstName();
    public void printAccountsSummary();
    public int numAccounts();
    public void printAcctTransHistory(int acctInd);
    public double getAcctBalance(int AcctInd);
    public String getAccountUUID(int AcctInd);
}