/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v01;

import java.text.DecimalFormat;

/**
 * V01 - Simulate ATM’s operation
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-09
 */
public class User {

    private String accountNumber;
    private String userName;
    private double accountBalance;
    private String currency;

    /**
     * Constructs a new User object with the specified account details.
     *
     * @param accountNumber The unique account number for the user.
     * @param userName The name of the account holder.
     * @param accountBalance The initial monetary balance of the account.
     * @param currency The currency type of the account (e.g., "VND", "USD").
     */
    public User(String accountNumber, String userName, double accountBalance, String currency) {
        this.accountNumber = accountNumber;
        this.userName = userName;
        this.accountBalance = accountBalance;
        this.currency = currency;
    }

    /**
     * Returns the account number of this user.
     *
     * @return The account number as a String.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the account number for this user.
     *
     * @param accountNumber The new account number as a String.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Returns the user name (account holder's name) of this user.
     *
     * @return The user name as a String.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user name (account holder's name) for this user.
     *
     * @param userName The new user name as a String.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns the current account balance of this user.
     *
     * @return The account balance as a double.
     */
    public double getAccountBalance() {
        return accountBalance;
    }

    /**
     * Sets the account balance for this user by *adding* the provided amount to
     * the current balance. Note: This method seems to be intended for *adding*
     * to the balance, not for directly setting it to a specific value. If the
     * intention was to set an absolute value, the operator should be '=', not
     * '+='.
     *
     * @param accountBalance The amount to be added to the current account
     * balance.
     */
    public void setAccountBalance(double accountBalance) {
        // This line adds the provided 'accountBalance' parameter to the existing 'this.accountBalance'.
        // If the intent was to *set* the balance to a new absolute value, this should be:
        // this.accountBalance = accountBalance;
        this.accountBalance += accountBalance;
    }

    /**
     * Returns the currency type of the account balance.
     *
     * @return The currency type as a String (e.g., "VND", "USD").
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the currency type for this user's account.
     *
     * @param currency The new currency type as a String.
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Formats the user's account data into a comma-separated string, suitable
     * for saving to a file or database. The format includes account number,
     * user name, formatted account balance, and currency.
     *
     * @return A String representing the user's data in a CSV-like format.
     */
    public String entryData() {
        DecimalFormat df = new DecimalFormat("#.###");
        return String.format("%s,%s,%s,%s\n",
                accountNumber,
                userName,
                df.format(accountBalance),
                currency
        );
    }

    /**
     * Adds a specified amount of money to the user's account balance.
     *
     * @param monney The amount of money (double) to be added.
     */
    public void addMonney(double monney) {
        this.accountBalance += monney;
    }

    /**
     * Deducts a specified amount of money from the user's account balance.
     *
     * @param monney The amount of money (double) to be deducted.
     */
    public void deductMonney(double monney) {
        this.accountBalance -= monney;
    }

    /**
     * Prints out the user's account information to the console in a
     * human-readable format. This includes account number, user name, formatted
     * account balance, and money type.
     */
    public void printOut() {
        DecimalFormat df = new DecimalFormat("#.###");
        System.out.println("Account number: " + accountNumber);
        System.out.println("User name: " + userName);
        System.out.println("Account balance: " + df.format(accountBalance));
        System.out.println("Monney type: " + currency);
    }
}
