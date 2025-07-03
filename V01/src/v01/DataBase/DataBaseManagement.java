/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v01.DataBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import v01.TransferHistory;
import v01.User;

/**
 * V01 - Simulate ATM’s operation
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-09
 */
public class DataBaseManagement {

    private FileHandler ras = new FileHandler();

    /**
     * Reads user information from a file and returns it as a HashMap. The
     * HashMap maps account numbers (String) to User objects.
     *
     * @return A HashMap where keys are account numbers (String) and values are
     * User objects.
     */
    public HashMap<String, User> readUserInformation() {
        return ras.readUserInformation();
    }

    /**
     * Reads account login information (account number and PIN) from a file and
     * returns it as a HashMap. The HashMap maps account numbers (String) to
     * PINs (String).
     *
     * @return A HashMap where keys are account numbers (String) and values are
     * PINs (String).
     */
    public HashMap<String, String> readAccountForLogin() {
        return ras.readAccountForLogin();
    }

    /**
     * Reads transaction history from a file and returns it as a HashMap. The
     * HashMap maps account numbers (String) to a list of TransferHistory
     * objects.
     *
     * @return A HashMap where keys are account numbers (String) and values are
     * ArrayLists of TransferHistory objects.
     */
    public HashMap<String, ArrayList<TransferHistory>> readTransaction() {
        return ras.readTransaction();
    }

    /**
     * Reads withdrawal fee information from a file and returns it as a HashMap.
     * The HashMap maps currency types (String) to their respective withdrawal
     * fees (Double).
     *
     * @return A HashMap where keys are currency types (String) and values are
     * withdrawal fees (Double).
     */
    public HashMap<String, Double> readWithdrawFee() {
        return ras.readWithdrawFee();
    }

    /**
     * Reads exchange rate information from a file and returns it as a HashMap.
     * The HashMap maps currency pairs (String, e.g., "VND-USD") to their
     * exchange rates (Double).
     *
     * @return A HashMap where keys are currency pairs (String) and values are
     * exchange rates (Double).
     */
    public HashMap<String, Double> readExchangeRate() {
        return ras.readExchangeRate();
    }

    /**
     * Saves user information from a HashMap to a file. It iterates through the
     * HashMap, formats each User object's data, and writes it to
     * "accounts.txt".
     *
     * @param userList A HashMap where keys are account numbers (String) and
     * values are User objects to be saved.
     */
    public void saveToAccount(HashMap<String, User> userList) {
        String text = "";
        // Iterates through each entry in the userList HashMap.
        for (Map.Entry<String, User> e : userList.entrySet()) {
            text += e.getValue().entryData();
        }
        ras.saveToFile(text, "accounts.txt");
    }

    /**
     * Saves account login information (account numbers and PINs) from a HashMap
     * to a file. It iterates through the HashMap, formats each account number
     * and PIN, and writes it to "cards.txt".
     *
     * @param accountList A HashMap where keys are account numbers (String) and
     * values are PINs (String) to be saved.
     */
    public void saveToCard(HashMap<String, String> accountList) {
        String text = "";
        // Iterates through each entry in the accountList HashMap.
        for (Map.Entry<String, String> e : accountList.entrySet()) {
            text += String.format("%s,%s\n", e.getKey(), e.getValue());
        }
        ras.saveToFile(text, "cards.txt");
    }

    /**
     * Saves transaction history from a HashMap to a file. It iterates through
     * the historyList, and for each account, it iterates through its list of
     * TransferHistory objects, formats their data, and writes it to
     * "transaction.txt".
     *
     * @param historyList A HashMap where keys are account numbers (String) and
     * values are ArrayLists of TransferHistory objects to be saved.
     */
    public void saveToTransactionHistory(HashMap<String, ArrayList<TransferHistory>> historyList) {
        String text = "";
        // Iterates through each entry (account number and its list of histories) in the historyList HashMap.
        for (Map.Entry<String, ArrayList<TransferHistory>> e : historyList.entrySet()) {
            // For each account, iterate through its ArrayList of TransferHistory objects.
            for (TransferHistory o : e.getValue()) {
                text += o.entryData();
            }
        }
        ras.saveToFile(text, "transaction.txt");
    }
}
