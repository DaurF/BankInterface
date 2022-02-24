package com.bank.bank;

import com.bank.user.User;
import com.bank.account.Account;

public interface IBank {
    public String getNewUserUUID();
    public String getNewAccountUUID();
    public void addAccount(Account a);
    public User addUser(String firstName, String lastName, String pin);
    public User userLogin(String userID, String pin);
    public String getName();

}
