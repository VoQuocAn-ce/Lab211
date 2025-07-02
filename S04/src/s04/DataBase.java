/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * S04-Write a login function uses MD5 encryption for passwords (separate from
 * FPT Webmail software Project.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-05-25
 */
public class DataBase {

    /**
     * Reads user data from "DataBase.txt" and returns it as an ArrayList of
     * User objects. Each line in the file is expected to represent a user, with
     * fields separated by commas.
     *
     * @return An ArrayList containing User objects read from the database file.
     * Returns an empty ArrayList if the file is empty or no user data is found,
     * or if an IOException occurs during file reading.
     */
    public ArrayList<User> readFromDB() {
        ArrayList<User> userList = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        // Use a try-catch block to handle potential IOException (file reading errors)
        // and ParseException (errors during date string parsing).
        try {
            BufferedReader br = new BufferedReader(new FileReader("Database.txt"));
            String line;
            // Start an infinite loop to read lines until the end of the file or an empty line is encountered.
            while (true) {
                line = br.readLine();
                // Check if the `line` read is null (end of file) or empty.
                // If either condition is true, it means there are no more valid data lines to process.
                if (line == null || line.isEmpty()) {
                    break;
                }
                String[] temp = line.split(",");
                if (temp[0].equals("User")) {
                    String userName = temp[1];
                    String password = temp[2];
                    String name = temp[3];
                    String phone = temp[4];
                    String email = temp[5];
                    String address = temp[6];
                    Date dob = formatter.parse(temp[7]);
                    User newUser = new User(userName, password, name, phone, email, address, dob);
                    userList.add(newUser);
                }
            }
            br.close();
        } // Catch block for IOException. This handles general input/output errors (e.g., file not found, permission issues).
        catch (IOException e) {
            System.out.println("**************");
            System.out.println("Read form database fail!");
            System.out.println("**************");
        } // Catch block for ParseException. This specifically handles errors that occur if the date string
        // (temp[7]) does not conform to the "dd/MM/yyyy" format expected by the SimpleDateFormat.
        catch (ParseException e) {
            System.out.println("");
        }

        return userList;
    }

    /**
     * Reads user login credentials (username and password) from "DataBase.txt"
     * and stores them in a HashMap. This method is specifically designed for
     * login purposes, returning a map where the key is the username and the
     * value is the corresponding password.
     *
     * @return A HashMap where keys are usernames (String) and values are
     * passwords (String). Returns an empty HashMap if the file is empty, no
     * user records are found, or if an IOException occurs during file reading.
     */
    public HashMap<String, String> readFromDBForLogin() {
        MD5Encryption enc = new MD5Encryption();
        HashMap<String, String> accountList = new HashMap<>();
        // Use a try-catch block to handle potential IOException during file reading.
        try {
            BufferedReader br = new BufferedReader(new FileReader("Database.txt"));
            String line;
            // Start an infinite loop to read lines until the end of the file or an empty line is encountered.
            while (true) {
                line = br.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }
                String[] temp = line.split(",");
                // Check if the first element of the split line is "User".
                // This acts as a filter to ensure that only lines representing user data are processed.
                if (temp[0].equals("User")) {
                    String userName = temp[1];
                    String password = temp[2];
                    accountList.put(userName, password);
                }
            }
            br.close();
        }// Catch block for IOException. This handles general input/output errors (e.g., file not found, permission issues). 
        catch (IOException e) {
            System.out.println("**************");
            System.out.println("Read form database fail!");
            System.out.println("**************");
        }

        return accountList;
    }

    /**
     * Updates the "DataBase.txt" file with the current list of users. This
     * method overwrites the existing content of the file with the data from the
     * provided ArrayList of User objects.
     *
     * @param userList An ArrayList of User objects representing the current
     * state of users to be written to the database file.
     */
    public void uppdateDB(ArrayList<User> userList) {
        String database = "";
        // Iterate through each User object 'e' in the provided 'userList'.
        // This 'for-each' loop processes every user in the list to prepare their data for writing.
        for (User e : userList) {
            database += e.entryData();
        }
        if (database == null || database.isEmpty()) {
            return;
        }
        // Use a try-catch block to handle potential IOException that might occur during file writing operations.
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Database.txt", false));
            bw.write(database);
            bw.close();
        } // Catch block for IOException. This handles general input/output errors (e.g., permission issues, disk full).
        catch (IOException e) {
            System.out.println("**************");
            System.out.println("Write into database fail!");
            System.out.println("**************");
        }
    }
}
