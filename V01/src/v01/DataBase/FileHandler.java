/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v01.DataBase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import v01.TransferHistory;
import v01.User;

/**
 * V01 - Simulate ATM’s operation
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-09
 */
public class FileHandler {

    /**
     * Saves the given string data to a specified file. If the file exists, its
     * content will be overwritten (append mode is false).
     *
     * @param data The string content to be written to the file.
     * @param fileName The name of the file to which the data will be saved.
     */
    public void saveToFile(String data, String fileName) {
        // Start of try-catch block to handle potential IOException.
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false));
            bw.write(data);
            bw.close();
            // Catch block for IOException, which might occur during file writing.
        } catch (IOException e) {
            System.err.println("Write file fail!");
        }
    }

    /**
     * Reads user information from "accounts.txt" and stores it in a HashMap.
     * Each line in the file is expected to contain account number, user name,
     * account balance, and currency, separated by commas.
     *
     * @return A HashMap where keys are account numbers (String) and values are
     * User objects.
     */
    public HashMap<String, User> readUserInformation() {
        HashMap<String, User> listUser = new HashMap<>();
        String line = "";
        // Start of try-catch block to handle potential IOException.
        try {
            BufferedReader br = new BufferedReader(new FileReader("accounts.txt"));
            // Loop indefinitely until an explicit break statement is encountered.
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] temp = line.split(",");
                String accountNumber = temp[0];
                String userName = temp[1];
                double accountBalance = Double.parseDouble(temp[2]);
                String currency = temp[3];
                User newCustomer = new User(accountNumber, userName, accountBalance, currency);
                listUser.put(accountNumber, newCustomer);
            }
            br.close();
            // Catch block for IOException, which might occur during file reading.
        } catch (IOException e) {
            System.err.println("Read file \"account\" fail!");
        }
        return listUser;
    }

    /**
     * Reads account login information (account number and PIN) from
     * "cards.txt". Each line in the file is expected to contain an account
     * number and a PIN, separated by commas.
     *
     * @return A HashMap where keys are account numbers (String) and values are
     * PINs (String).
     */
    public HashMap<String, String> readAccountForLogin() {
        HashMap<String, String> listAccount = new HashMap<>();
        String line = "";
        // Start of try-catch block to handle potential exceptions.
        try {
            BufferedReader br = new BufferedReader(new FileReader("cards.txt"));
            // Loop indefinitely until an explicit break statement is encountered.
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] temp = line.split(",");
                listAccount.put(temp[0], temp[1]);
            }
            br.close();
        } catch (Exception e) {
            System.err.println("Read file \"cards\" fail");
        }
        return listAccount;
    }

    /**
     * Reads transaction history from "transaction.txt" and organizes it by
     * account number into a HashMap where values are ArrayLists of
     * TransferHistory objects. Each line in the file is expected to contain
     * time, account number, money, money type, account balance, and content,
     * separated by commas.
     *
     * @return A HashMap where keys are account numbers (String) and values are
     * ArrayLists of TransferHistory objects.
     */
    public HashMap<String, ArrayList<TransferHistory>> readTransaction() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        HashMap<String, ArrayList<TransferHistory>> transactionHistoryList = new HashMap<>();
        String line = "";
        // Start of try-catch block to handle potential exceptions.
        try {
            BufferedReader br = new BufferedReader(new FileReader("transaction.txt"));
            // Loop indefinitely until an explicit break statement is encountered.
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] temp = line.split(",");
                LocalDateTime time = LocalDateTime.parse(temp[0], dtf);
                String accountNumber = temp[1];
                double money = Double.parseDouble(temp[2]);
                String moneyType = temp[3];
                double accountBalance = Double.parseDouble(temp[4]);
                String content = temp[5];
                TransferHistory newHistory = new TransferHistory(time, accountNumber, money, moneyType, accountBalance, content);
                if (transactionHistoryList.containsKey(accountNumber)) {
                    transactionHistoryList.get(accountNumber).add(newHistory);
                } else {
                    ArrayList<TransferHistory> historyList = new ArrayList<>();
                    historyList.add(newHistory);
                    transactionHistoryList.put(accountNumber, historyList);
                }
            }
            br.close();
            // Catch block for any Exception that might occur (e.g., IOException, parsing errors).
        } catch (Exception e) {
            System.err.println("Read file \"transaction\" fail");
        }

        return transactionHistoryList;
    }

    /**
     * Reads withdrawal fee information from "withdraw_fee.txt" and stores it in
     * a HashMap. Each line in the file is expected to contain a currency type
     * and its corresponding withdrawal fee, separated by commas.
     *
     * @return A HashMap where keys are currency types (String) and values are
     * withdrawal fees (Double).
     */
    public HashMap<String, Double> readWithdrawFee() {
        HashMap<String, Double> withdrawFee = new HashMap<>();
        String line = "";
        // Start of try-catch block to handle potential exceptions.
        try {
            BufferedReader br = new BufferedReader(new FileReader("withdraw_fee.txt"));
            // Loop indefinitely until an explicit break statement is encountered.
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] temp = line.split(",");
                withdrawFee.put(temp[0], Double.parseDouble(temp[1]));
            }
            br.close();
            // Catch block for any Exception that might occur (e.g., IOException, parsing errors).
        } catch (Exception e) {
            System.err.println("Read file \"withdraw_fee\" fail");
        }
        return withdrawFee;
    }

    /**
     * Reads exchange rate information from "exchange_rate.txt" and stores it in
     * a HashMap. Each line in the file is expected to contain a currency pair
     * (e.g., "USD-VND") and its corresponding exchange rate, separated by
     * commas.
     *
     * @return A HashMap where keys are currency pairs (String) and values are
     * exchange rates (Double).
     */
    public HashMap<String, Double> readExchangeRate() {
        HashMap<String, Double> exchangeRateList = new HashMap<>();
        String line = "";
        // Start of try-catch block to handle potential exceptions.
        try {
            BufferedReader br = new BufferedReader(new FileReader("exchange_rate.txt"));
            // Loop indefinitely until an explicit break statement is encountered.
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] temp = line.split(",");
                exchangeRateList.put(temp[0], Double.parseDouble(temp[1]));
            }
            br.close();
            // Catch block for any Exception that might occur (e.g., IOException, parsing errors).
        } catch (Exception e) {
            System.err.println("Read file \"exchange_rate\" fail");
        }
        return exchangeRateList;
    }
}
