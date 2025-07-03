/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v01;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * V01 - Simulate ATM’s operation
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-09
 */
public class TransferHistory {

    private LocalDateTime time;
    private String accountNumber;
    private double money;
    private String moneyType;
    private double accountBalance;
    private String content;

    /**
     * Constructs a new TransferHistory object with the details of a
     * transaction.
     *
     * @param time The LocalDateTime when the transaction occurred.
     * @param origin The account number for which this history record is being
     * created (could be sender or receiver).
     * @param money The amount of money involved in this transaction. A negative
     * value indicates money leaving the account, a positive value indicates
     * money entering the account.
     * @param moneyType The currency type of the 'money' (e.g., "VND", "USD").
     * @param accountBalance The balance of the 'origin' account immediately
     * after this transaction.
     * @param content A descriptive string for the transaction (e.g., "Transfer
     * to...", "Withdrawal").
     */
    public TransferHistory(LocalDateTime time, String origin, double money, String moneyType, double accountBalance, String content) {
        this.time = time;
        this.accountNumber = origin;
        this.money = money;
        this.moneyType = moneyType;
        this.accountBalance = accountBalance;
        this.content = content;
    }

    /**
     * Returns the timestamp of the transaction.
     *
     * @return The LocalDateTime object representing the transaction time.
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Sets the timestamp of the transaction.
     *
     * @param time The new LocalDateTime object for the transaction time.
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * Returns the account number associated with this transaction history
     * record. Note: The method name 'getOrigin' is a remnant from when
     * 'accountNumber' was named 'origin' in the constructor, but it correctly
     * returns the 'accountNumber' field.
     *
     * @return The account number as a String.
     */
    public String getOrigin() {
        return accountNumber;
    }

    /**
     * Sets the account number associated with this transaction history record.
     * Note: The method name 'setOrigin' is a remnant from when 'accountNumber'
     * was named 'origin' in the constructor, but it correctly sets the
     * 'accountNumber' field.
     *
     * @param origin The new account number as a String.
     */
    public void setOrigin(String origin) {
        this.accountNumber = origin;
    }

    /**
     * Returns the amount of money involved in this transaction. A negative
     * value indicates money leaving the account, a positive value indicates
     * money entering the account.
     *
     * @return The transaction amount as a double.
     */
    public double getMoney() {
        return money;
    }

    /**
     * Sets the amount of money involved in this transaction.
     *
     * @param money The new transaction amount as a double.
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * Returns the currency type of the money involved in the transaction.
     *
     * @return The money type (currency) as a String.
     */
    public String getMoneyType() {
        return moneyType;
    }

    /**
     * Sets the currency type of the money involved in the transaction.
     *
     * @param moneyType The new money type (currency) as a String.
     */
    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType;
    }

    /**
     * Returns the account balance after this transaction.
     *
     * @return The account balance as a double.
     */
    public double getAccountBalance() {
        return accountBalance;
    }

    /**
     * Sets the account balance after this transaction.
     *
     * @param accountBalance The new account balance as a double.
     */
    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    /**
     * Returns the content or description of the transaction.
     *
     * @return The transaction content as a String.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content or description of the transaction.
     *
     * @param content The new transaction content as a String.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Formats the transaction history data into a comma-separated string,
     * suitable for saving to a file or database. The format includes time,
     * account number, transaction amount, money type, ending balance, and
     * content.
     *
     * @return A String representing the transaction data in a CSV-like format.
     */
    public String entryData() {
        DecimalFormat df = new DecimalFormat("#.###");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        return String.format("%s,%s,%s,%s,%s,%s\n",
                time.format(dtf),
                accountNumber,
                df.format(money),
                moneyType,
                df.format(accountBalance),
                content
        );
    }

    /**
     * Prints out the detailed information of a single transaction history entry
     * to the console. It formats the output differently based on whether the
     * transaction amount is a deduction (negative) or an addition (positive),
     * indicating it as either a debit or credit from the perspective of the
     * account holder for whom the history is displayed.
     */
    public void printOut() {
        DecimalFormat df = new DecimalFormat("#.###");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        if (money < 0) {
            System.out.printf("Time: %s\nBeneficiary account: %s\nTransaction amount: %s %s\nEnding balance: %s %s\nContent: %s\n",
                    time.format(dtf),
                    accountNumber,
                    df.format(money),
                    moneyType,
                    df.format(accountBalance),
                    moneyType,
                    content
            );
        } else if (money > 0) {
            System.out.printf("Time: %s\nBeneficiary account: %s\nTransaction amount: +%s %s\nEnding balance: %s %s\nContent: %s\n",
                    time.format(dtf),
                    accountNumber,
                    df.format(money),
                    moneyType,
                    df.format(accountBalance),
                    moneyType,
                    content
            );
        }
    }
}
