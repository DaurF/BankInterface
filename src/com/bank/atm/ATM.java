package com.bank.atm;

import com.bank.bank.Bank;
import com.bank.user.User;
import com.bank.account.Account;

import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank theBank = new Bank("com.bank.bank.Bank of Aitu");
        User aUser = theBank.addUser("Yertay", "Duisebayev", "1234"); // add a user and creates a savings account
        Account newAccount = new Account("Checking", aUser, theBank); // add a checking account for user
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        User curUser;
        while (true) {

            curUser = ATM.mainMenuPrompt(theBank, sc); //stay in the login prompt until successful login
            ATM.printUserMenu(curUser, sc); // stay in the main menu until user quits

        }
    }

    /**
     * Print the com.bank.atm.ATM's login menu
     *
     * @param theBank the com.bank.bank.Bank object whose accounts to use
     * @param sc      the Scanner object to use for user input
     * @return the authenticated com.bank.user.User object
     */
    public static User mainMenuPrompt(Bank theBank, Scanner sc) {
        String userID;
        String pin;
        User authUser;
//prompt a user for user ID/pin until a correct one is reached
        do {
            System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
            System.out.print("Enter user ID: ");
            userID = sc.nextLine();
            System.out.print("Enter pin: ");
            pin = sc.nextLine();
// try to get a user object corresponding to the ID/pin
            authUser = theBank.userLogin(userID, pin);
            if (authUser == null) {
                System.out.println("Incorrect user ID/pin combination" +
                        "Please try again");
            }

        } while (authUser == null); // continue looping until successful login
        return authUser;
    }

    public static void printUserMenu(User theUser, Scanner sc) {
        theUser.printAccountsSummary(); // print a summary of user's accounts
        int choice;
        //com.bank.user.User menu
        do {
            System.out.printf("Welcome %s, What would you like to do?\n",
                    theUser.getFirstName());
            System.out.println(" 1) Show account transaction history");
            System.out.println(" 2) Withdraw");
            System.out.println(" 3) Deposit");
            System.out.println(" 4) Transfer");
            System.out.println(" 5) Quit");
            System.out.println();
            System.out.print("Enter choice ");
            choice = sc.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid. Please choose 1-5");
            }
        } while (choice < 1 || choice > 5);
        switch (choice) { //process a choice
            case 1:
                ATM.showTransHistory(theUser, sc);
                break;
            case 2:
                ATM.withdrawFunds(theUser, sc);
                break;
            case 3:
                ATM.depositFunds(theUser, sc);
                break;
            case 4:
                ATM.transferFunds(theUser, sc);
                break;
            case 5:
                sc.nextLine(); //gobble up rest of previous input
                break;
        }
        if (choice != 5) { // redisplay this menu unless user wants to quit
            ATM.printUserMenu(theUser, sc);
        }
    }

    /**
     * Show the transaction history for an account
     *
     * @param theUser the logged-in com.bank.user.User object
     * @param sc      the Scanner object used for user input
     */
    public static void showTransHistory(User theUser, Scanner sc) {
        int theAcct;
        //get account whose transaction history to look at
        do {
            System.out.printf("Enter the number (1-%d) of the account\n" +
                    "whose transactions you want to see: ", theUser.numAccounts());
            theAcct = sc.nextInt() - 1;
            if (theAcct < 0 || theAcct >= theUser.numAccounts()) {
                System.out.println("Invalid. Please try again");
            }
        } while (theAcct < 0 || theAcct >= theUser.numAccounts());
        theUser.printAcctTransHistory(theAcct);
    }

    public static void transferFunds(User theUser, Scanner sc) {
        int fromAcct;
        int toAcct;
        double amount;
        double acctBal;
        //get account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account\n" +
                    "to transfer from: ", theUser.numAccounts());
            fromAcct = sc.nextInt() - 1;
            if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
                System.out.println("Invalid. Please try again");
            }
        } while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
        acctBal = theUser.getAcctBalance(fromAcct);
        //get account to transfer to
        do {
            System.out.printf("Enter the number (1-%d) of the account\n" +
                    "to transfer to: ", theUser.numAccounts());
            toAcct = sc.nextInt() - 1;
            if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
                System.out.println("Invalid. Please try again");
            }
        } while (toAcct < 0 || toAcct >= theUser.numAccounts());

        //get amount to transfer
        do{
            System.out.printf("Enter the amount to withdraw  (max $%.02f): $", acctBal);
            amount = sc.nextDouble();
            if(amount < 0){
                System.out.println("Amount must be greater than zero");
            }else if(amount > acctBal){
                System.out.printf("Amount must not be greater than\n" +
                        "balance of $%.02f.\n", acctBal);
            }
        }while(amount <0 || amount > acctBal);
        //do the transfer
        theUser.addAcctTransaction(fromAcct, -1*amount, String.format(
                "Transfer to account %s", theUser.getAccountUUID(toAcct)));
        theUser.addAcctTransaction(toAcct, amount, String.format(
                "Transfer to account %s", theUser.getAccountUUID(fromAcct)));
    }

    /**
     * Process a fund withdraw from account
     * @param theUser the logged-in com.bank.user.User object
     * @param sc the Scanner object used for user input
     */
    public static void withdrawFunds(User theUser, Scanner sc) {
        int fromAcct;
        double amount;
        double acctBal;
        String memo;
        //get account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account\n" +
                    "to withdraw from: ", theUser.numAccounts());
            fromAcct = sc.nextInt() - 1;
            if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
                System.out.println("Invalid. Please try again");
            }
        } while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
        acctBal = theUser.getAcctBalance(fromAcct);
        //get amount to transfer
        do{
            System.out.printf("Enter the amount to transfer (max $%.02f): $", acctBal);
            amount = sc.nextDouble();
            if(amount < 0){
                System.out.println("Amount must be greater than zero");
            }else if(amount > acctBal){
                System.out.printf("Amount must not be greater than\n" +
                        "balance of $%.02f.\n", acctBal);
            }
        }while(amount <0 || amount > acctBal);
        sc.nextLine();
        //get a memo
        System.out.print("Enter a memo: ");
        memo = sc.nextLine();
        //do withdraw
        theUser.addAcctTransaction(fromAcct, -1*amount, memo);
    }

    /**
     * Process a fund deposit from account
     * @param theUser the logged-in com.bank.user.User object
     * @param sc the Scanner object used for user input
     */
    public static void depositFunds(User theUser, Scanner sc) {
        int toAcct;
        double amount;
        double acctBal;
        String memo;
        //get account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account\n" +
                    "to deposit in: ", theUser.numAccounts());
            toAcct = sc.nextInt() - 1;
            if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
                System.out.println("Invalid. Please try again");
            }
        } while (toAcct < 0 || toAcct >= theUser.numAccounts());
        acctBal = theUser.getAcctBalance(toAcct);
        //get amount to transfer
        do{
            System.out.printf("Enter the amount to transfer (max $%.02f): $", acctBal);
            amount = sc.nextDouble();
            if(amount < 0){
                System.out.println("Amount must be greater than zero");
            }
        }while(amount < 0);
        sc.nextLine();
        //get a memo
        System.out.print("Enter a memo: ");
        memo = sc.nextLine();
        //do withdraw
        theUser.addAcctTransaction(toAcct, amount, memo);
    }
}