/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s04;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * S04-Write a login function uses MD5 encryption for passwords (separate from
 * FPT Webmail software Project.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-05-25
 */
public class UserManagement {

    private ArrayList<User> userList;
    private DataBase dbi = new DataBase();
    private HashMap<String, String> accountList;

    /**
     * Constructs a new UserManagement object. This constructor is responsible
     * for initializing the user data by loading existing user records and
     * account login details from the database (or file). It ensures that the
     * UserManagement instance starts with up-to-date information.
     */
    public UserManagement() {
        userList = dbi.readFromDB();
        accountList = dbi.readFromDBForLogin();
    }

    /**
     * Guides the user through the process of adding a new user to the system.
     * This involves collecting various user details, validating each input,
     * encrypting the password, creating a new User object, adding it to the
     * in-memory list, and then saving the updated data.
     */
    public void addNewUser() {
        MD5Encryption enc = new MD5Encryption();
        System.out.println("---------- Add User ----------");
        String userName = ValidInput.validUserName("Account:", accountList);
        String password = ValidInput.validPassword("Password:");
        password = enc.encryption(password);
        String name = ValidInput.validName("Name:");
        String phone = ValidInput.validPhone("Phone:");
        String email = ValidInput.validEmail("Email:");
        String address = ValidInput.validAddress("Address:");
        Date dob = ValidInput.validDob("DOB:");
        User newUser = new User(userName, password, name, phone, email, address, dob);
        userList.add(newUser);
        newData();
    }

    /**
     * Attempts to log a user into the system by verifying their username and
     * password. This method checks if the provided username exists and if the
     * hashed version of the provided password matches the stored hashed
     * password for that user.
     *
     * @param userName The username entered by the user during the login
     * attempt.
     * @param password The plain-text password entered by the user during the
     * login attempt.
     * @return {@code true} if the username exists and the password matches the
     * stored hashed password; {@code false} otherwise.
     *
     * Note: For enhanced security, the hashing algorithm used for passwords
     * (MD5 in this case) should be a more robust, slow hashing function like
     * bcrypt, scrypt, or Argon2.
     */
    public boolean login(String userName, String password) {
        MD5Encryption enc = new MD5Encryption();
        //Perform the login validation using an 'if' statement.
        // The condition checks two things:
        // 1. `accountList.containsKey(userName)`: Verifies if the provided username exists as a key in the `accountList`.
        // 2. `enc.encryption(password).equals(accountList.get(userName))`:
        //    - Encrypts the plain-text `password` provided by the user.
        //    - Compares this newly generated encrypted password with the encrypted password
        //      stored in `accountList` for the given `userName`.
        // The '&&' (logical AND) ensures both conditions must be true for the login to be successful.
        if (accountList.containsKey(userName) && enc.encryption(password).equals(accountList.get(userName))) {
            return true;
        }
        return false;
    }

    /**
     * Provides functionality for a logged-in user to change their password. It
     * identifies the user by their username, prompts them if they wish to
     * change their password, then validates the old password and ensures the
     * new password is entered consistently before updating it.
     *
     * @param userName The username of the currently logged-in user whose
     * password is to be changed.
     */
    public void changePassword(String userName) {
        MD5Encryption enc = new MD5Encryption();
        // Iterate through the `userList` to find the `User` object corresponding to the given `userName`.
        // The 'for-each' loop checks every user in the list.
        for (User e : userList) {
            // Check if the current user's username matches the `userName` passed to the method.
            // This 'if' statement finds the correct user in the list.
            if (e.getUserName().equals(userName)) {
                System.out.println("------------ Welcome ------------");
                System.out.printf("Hi %s, do you want change password now? Y/N: ",
                        e.getName()
                );
                String choice = ValidInput.twoChoice("", "Y", "N");
                if (choice.equals("Y")) {
                    String oldPassword = ValidInput.validPasswordForLogin("Old password: ");
                    String newPassword = ValidInput.validPassword("new password: ");
                    String renwePassword = ValidInput.validPasswordForLogin("renew password: ");
                    // Validate the old password and the new passwords.
                    // The 'if' statement checks two conditions:
                    // 1. `e.getPassword().equals(enc.encryption(oldPassword))`: Checks if the stored encrypted password
                    //    for the user matches the encrypted version of the `oldPassword` entered by the user.
                    // 2. `newPassword.equals(renewPassword)`: Checks if the new password and its re-entered confirmation match.
                    if (e.getPassword().equals(enc.encryption(oldPassword)) && newPassword.equals(renwePassword)) {
                        e.setPassword(enc.encryption(newPassword));
                        newData();
                    } else {
                        // If passwords don't match or the old password was incorrect.
                        System.out.println("**********************");
                        System.out.println("Incorrect password or new password does not match!");
                        System.out.println("**********************");
                    }
                } else if (choice.equals("N")) {
                    return;
                }
            }
        }
    }

    /**
     * Updates the persistent storage with the latest user data and refreshes
     * the in-memory login credentials. This method is crucial for ensuring that
     * any changes made to user information (e.g., adding a new user, changing a
     * password) are saved and that the login system has the most current
     * account details.
     */
    public void newData() {
        dbi.uppdateDB(userList);
        accountList = dbi.readFromDBForLogin();
    }
}
