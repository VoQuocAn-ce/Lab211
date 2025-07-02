/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s04;

/**
 * S04-Write a login function uses MD5 encryption for passwords (separate from
 * FPT Webmail software Project.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-05-25
 */
public class ProcessMenu {

    UserManagement um = new UserManagement();

    /**
     * Displays the main menu for the login program and handles user
     * interactions. This method runs a loop, presenting options to the user
     * until they choose to exit. It leverages other utility methods (like
     * `ValidInput` and methods within `um` - UserManager) to handle specific
     * actions.
     */
    public void menu() {
        boolean condition = true;
        // The 'while' loop continues as long as 'condition' is true,
        // keeping the menu active until the user chooses to exit.
        while (condition) {
            System.out.printf("========== Login Program =========\n"
                    + "1. Add User\n"
                    + "2. Login\n"
                    + "3) Exit\n"
            );
            int choice = ValidInput.positiveNumberWithLimit("Please choice one option: ", 3);
            switch (choice) {
                case 1:
                    um.addNewUser();
                    break;
                case 2:
                    System.out.println("------------- Login -------------");
                    String userName = ValidInput.validUserNameForLogin("Account: ");
                    String password = ValidInput.validPasswordForLogin("Password: ");
                    if (um.login(userName, password)) {
                        um.changePassword(userName);
                    } else {
                        System.out.println("**********************");
                        System.out.println("Incorrect password or user name does not match!");
                        System.out.println("**********************");
                    }
                    break;
                case 3:
                    condition = false;
            }
        }
    }
}
