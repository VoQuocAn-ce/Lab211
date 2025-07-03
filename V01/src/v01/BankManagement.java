/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v01;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import v01.DataBase.DataBaseManagement;

/**
 * V01 - Simulate ATM’s operation
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-09
 */
public class BankManagement {

    private HashMap<String, User> userList;
    private HashMap<String, String> accountList;
    private HashMap<String, ArrayList<TransferHistory>> transactionHistory;
    private HashMap<String, Double> withdrawFee;
    private HashMap<String, Double> exchangeRate;
    private DataBaseManagement dbm = new DataBaseManagement();

    /**
     * Constructs a new BankManagement object. This constructor is responsible
     * for initializing the bank management system. It immediately calls the
     * {@code loadData()} method upon instantiation, which typically means it
     * will load existing bank account data or other necessary information from
     * a persistent storage (like a file or database) into memory when a
     * BankManagement object is created.
     */
    public BankManagement() {
        loadData();
    }

    /**
     * Creates a new bank account for a user. This method prompts the user for
     * necessary details such as account number, PIN, user name, and money type.
     * It generates a random initial account balance. The new account
     * information is then added to the in-memory data structures (`userList`
     * and `accountList`) and subsequently saved to persistent storage. After
     * saving, it reloads the data to ensure consistency.
     */
    public void createNewAccount() {
        Random random = new Random();
        System.out.println("=============");
        System.out.println("");
        String accountNumber = ValidInput.getValidAccountNumberForCreate("Account number: ", accountList);
        String pin = ValidInput.getValidPin("PIN: ");
        String userName = ValidInput.getValidName("User name: ");
        String moneyType = ValidInput.getMonneyType("Money type: ");
        double accountBalance = Math.abs(random.nextInt(1000) * random.nextInt(1000000000));
        User newUser = new User(accountNumber, userName, accountBalance, moneyType);
        userList.put(accountNumber, newUser);
        accountList.put(accountNumber, pin);
        System.out.println("Create new account successful!");
        SaveData();
        loadData();
    }

    /**
     * Facilitates the transfer of money from an origin account to a beneficiary
     * account. This method prompts the user for the beneficiary's account
     * number, the amount to transfer, and a transaction content. It displays
     * the current balance of the origin account. It handles transfers between
     * accounts with the same currency and also performs currency conversion if
     * the accounts have different money types, using predefined exchange rates.
     * Transaction details are recorded for both the origin and destination
     * accounts, and all data is then saved to persistent storage.
     *
     * @param origin The account number of the originating account from which
     * money is transferred.
     */
    public void transferMoney(String origin) {
        DecimalFormat df = new DecimalFormat("#.###");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        System.out.println("=============");
        System.out.println("");
        String destination = ValidInput.getValidAccountNumberForTransfer(origin, "beneficiary account number: ", accountList);
        System.out.printf("%s(%s): %s%s\n",
                origin,
                userList.get(origin).getUserName(),
                df.format(userList.get(origin).getAccountBalance()),
                userList.get(origin).getCurrency()
        );
        System.out.printf("   =========> %s(%s)\n",
                destination,
                userList.get(destination).getUserName()
        );
        double money;
        String content = "";
        LocalDateTime now = LocalDateTime.now();
        String time = now.format(dtf);
        // Checks if the origin and destination accounts have the same currency.
        if (userList.get(origin).getCurrency().equals(userList.get(destination).getCurrency())) {
            while (true) {
                money = ValidInput.getInvalidMonney("Amount to transfer: ");
                content = ValidInput.getValidContent("Content: ", userList.get(origin));
                if (money > userList.get(origin).getAccountBalance()) {
                    System.out.println("Account balance not enough");
                    continue;
                }
                userList.get(origin).deductMonney(money);
                break;
            }
            userList.get(destination).addMonney(money);
        } else {
            while (true) {
                money = ValidInput.getInvalidMonney("Amount to transfer: ");
                content = ValidInput.getValidContent("Content: ", userList.get(origin));
                if (money > userList.get(origin).getAccountBalance()) {
                    System.out.println("Account balance not enough");
                    continue;
                }
                userList.get(origin).deductMonney(money);
                break;
            }
            userList.get(destination).addMonney(money * (exchangeRate.get(userList.get(origin).getCurrency() + "->" + userList.get(destination).getCurrency())));
        }
        // Create and record transaction history for the destination account (money coming in).
        // Note: The money transferred to the destination needs to be in the destination's currency for history.
        // This recalculates the value for the destination history entry.
        TransferHistory newHistory = new TransferHistory(now, origin, (-1) * money, userList.get(origin).getCurrency(), userList.get(origin).getAccountBalance(), content);
        if (transactionHistory.containsKey(origin)) {
            transactionHistory.get(origin).add(newHistory);
        } else {
            ArrayList<TransferHistory> transferHistory = new ArrayList<>();
            transferHistory.add(newHistory);
            transactionHistory.put(origin, transferHistory);
        }
        newHistory = new TransferHistory(now, destination, money * (exchangeRate.get(userList.get(origin).getCurrency() + "->" + userList.get(destination).getCurrency())), userList.get(destination).getCurrency(), userList.get(destination).getAccountBalance(), content);
        if (transactionHistory.containsKey(destination)) {
            transactionHistory.get(destination).add(newHistory);
        } else {
            ArrayList<TransferHistory> transferHistory = new ArrayList<>();
            transferHistory.add(newHistory);
            transactionHistory.put(destination, transferHistory);
        }
        SaveData();
    }

    /**
     * Allows a user to withdraw money from their account. This method displays
     * the current balance of the originating account, prompts for the amount to
     * withdraw, calculates the total deduction (including withdrawal fees),
     * updates the account balance, and records the transaction in the history.
     * All changes are then saved to persistent storage.
     *
     * @param origin The account number of the originating account from which
     * money is withdrawn.
     */
    public void withdrawMoney(String origin) {
        DecimalFormat df = new DecimalFormat("#.###");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        System.out.println("=============");
        System.out.println("");
        System.out.printf("%s(%s): %s %s\n",
                origin,
                userList.get(origin).getUserName(),
                df.format(userList.get(origin).getAccountBalance()),
                userList.get(origin).getCurrency()
        );
        double money;
        String content = "Withdraw money";
        LocalDateTime now = LocalDateTime.now();
        String time = now.format(dtf);
        while (true) {
            money = ValidInput.getInvalidMonney("Amount to withdraw: ");
            if (money + withdrawFee.get(userList.get(origin).getCurrency()) > userList.get(origin).getAccountBalance()) {
                System.out.println("Account balance not enough");
                continue;
            }
            userList.get(origin).deductMonney(money + withdrawFee.get(userList.get(origin).getCurrency()));
            break;
        }
        TransferHistory newHistory = new TransferHistory(now, origin, (-1) * (money + withdrawFee.get(userList.get(origin).getCurrency())), userList.get(origin).getCurrency(), userList.get(origin).getAccountBalance(), content);
        // Checks if transaction history already exists for the origin account.
        if (transactionHistory.containsKey(origin)) {
            transactionHistory.get(origin).add(newHistory);
        } else {
            ArrayList<TransferHistory> transferHistory = new ArrayList<>();
            transferHistory.add(newHistory);
            transactionHistory.put(origin, transferHistory);
        }
        SaveData();
    }

    /**
     * Prints out the current information of a specific user's account. This
     * typically includes details such as account number, user name, current
     * balance, and currency.
     *
     * @param user The account number (String) of the user whose information is
     * to be printed.
     */
    public void printOutInfor(String user) {
        System.out.println("==============");
        userList.get(user).printOut();
        System.out.println("==============");
    }

    /**
     * Prints out the transaction history for a specified user account. If no
     * transaction history exists for the given user, the method returns without
     * printing. Otherwise, it iterates through and prints each transfer history
     * entry for that user.
     *
     * @param user The account number (String) of the user whose transaction
     * history is to be printed.
     */
    public void printOutTransactionHistory(String user) {
        System.out.println("=============");
        // Checks if the transactionHistory HashMap contains any entries for the given user's account.
        if (!transactionHistory.containsKey(user)) {
            return;
        }
        // Iterates through each TransferHistory object in the ArrayList associated with the user.
        for (TransferHistory e : transactionHistory.get(user)) {
            e.printOut();
            System.out.println(" ");
            System.out.println("==============");
            System.out.println(" ");
        }
    }

    /**
     * Authenticates a user by checking if the provided account number and PIN
     * match an existing account in the system.
     *
     * @param accountNumber The account number entered by the user.
     * @param pin The PIN entered by the user.
     * @return 1 if the account number and PIN are valid and match, 0 otherwise.
     */
    public int login(String accountNumber, String pin) {
        if (accountList.containsKey(accountNumber) && pin.equals(accountList.get(accountNumber))) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Retrieves the user name associated with a given account number.
     *
     * @param accountNumber The account number (String) for which to retrieve
     * the user name.
     * @return The user name (String) of the account holder, or null if the
     * account number is not found.
     */
    public String getName(String accountNumber) {
        return userList.get(accountNumber).getUserName();
    }

    /**
     * Loads initial data for the bank management system from a persistent
     * storage. This method retrieves various sets of data, such as user
     * information, account login details, transaction history, withdrawal fees,
     * and exchange rates, by interacting with a database manager. It populates
     * the corresponding lists and variables within the BankManagement instance.
     */
    public void loadData() {
        userList = dbm.readUserInformation();
        accountList = dbm.readAccountForLogin();
        transactionHistory = dbm.readTransaction();
        withdrawFee = dbm.readWithdrawFee();
        exchangeRate = dbm.readExchangeRate();
    }

    /**
     * Saves the current state of the bank management system's data back to
     * persistent storage. This method takes the in-memory data (user
     * information, account login details, and transaction history) and persists
     * it using a database manager, ensuring data integrity across application
     * sessions.
     */
    public void SaveData() {
        dbm.saveToAccount(userList);
        dbm.saveToCard(accountList);
        dbm.saveToTransactionHistory(transactionHistory);
    }
}
