package com.bank.bank;

import com.bank.user.User;
import com.bank.account.Account;

import java.util.ArrayList;
import java.util.Random;

public class Bank implements IBank
{
    private String name;

    private ArrayList<User> users;

    private ArrayList<Account> accounts;


    /**
     * Create a new com.bank.bank.Bank object with empty lists of users and accounts
     * @param name the name of the com.bank.bank.Bank
     */
    public Bank(String name){
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }
    /**
     * Generate a new universally unique ID for a user.
     * @return  the uuid
     */
    public String getNewUserUUID()
    {
        // inits
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
                if(uuid.compareTo(u.getUUID()) == 0)
                {
                    nonUnique = true;
                    break;
                }
            }
        } while(nonUnique);

        return uuid;

    }

    public String getNewAccountUUID() {
        // inits
        String uuid;
        Random rng = new Random();
        int len = 10;
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
            for(Account a : this.accounts)
            {
                if(uuid.compareTo(a.getUUID()) == 0)
                {
                    nonUnique = true;
                    break;
                }
            }
        } while(nonUnique);

        return uuid;
    };

    /**
     * Add an account
     * @param anAcct the account to add
     */
    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);
    }

    /**
     * Create a new user of the bank
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param pin       the user's pin
     * @return          the new com.bank.user.User object
     */
    public User addUser(String firstName, String lastName, String pin)
    {
        // create a new com.bank.user.User object and add it to our list
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        // create a savings account for the user and add to com.bank.user.User and com.bank.bank.Bank
        // account lists
        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);

        return newUser;
    }

    /**
     * Get the com.bank.user.User object associated with a particular userID and pin, if they are valid
     * @param userID    the UUID of the user to log in
     * @param pin       the pin of the user
     * @return          the com.bank.user.User is object, if the login is successful, or null, if it isn't
     */
    public User userLogin(String userID, String pin)
    {
        // search through list of users
        for (User u : this.users)
        {
            // check user ID is correct
            if(u.getUUID().compareTo(userID) == 0 && u.validatePin(pin))
            {
                return u;
            }
        }

        // if we haven't found the user or have an incorrect pin
        return null;
    }

    /**
     * Get the name of the bank
     * @return the name of the bank
     */
    public String getName()
    {
        return this.name;
    }
}
