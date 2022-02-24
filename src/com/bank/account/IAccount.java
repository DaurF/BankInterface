package com.bank.account;

public interface IAccount {
    public String getUUID();
    public String getSummaryLine();
    public double getBalance();
    public void printTransHistory();
    public void addTransaction(double a, String b);
}
