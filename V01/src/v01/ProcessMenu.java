/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v01;

/**
 * V01 - Simulate ATM’s operation
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-09
 */
public class ProcessMenu {

    private BankManagement bm = new BankManagement();

    /**
     * The main entry point for the ATM simulation. This method presents the
     * initial menu to the user, allowing them to log in to an existing account,
     * create a new account, or quit the application. It continuously loops
     * until the user chooses to quit.
     */
    public void run() {
        boolean condition = true;
        int choice;
        // Loop continues as long as 'condition' is true.
        while (condition) {
            System.out.printf("Welcom to AnVy bank!\n"
                    + "1. Login\n"
                    + "2. Create new account\n"
                    + "3. Quit\n");
            choice = ValidInput.getPositiveNumberWithLimit("Please select function: ", 3);
            // Uses a switch statement to handle different menu choices.
            switch (choice) {
                case 1:
                    String accountNumber = ValidInput.getValidAccountNumberForLogin("Account number: ");
                    String pin = ValidInput.getValidPin("Pin: ");
                    // If login is successful (returns 1), call the private 'login' method to enter the logged-in menu.
                    if (bm.login(accountNumber, pin) == 1) {
                        login(accountNumber);
                    } else {
                        System.out.println("Account numbr or pin is not match!");
                    }
                    break;
                case 2:
                    bm.createNewAccount();
                    break;
                case 3:
                    System.out.println("See you later!");
                    condition = false;
            }
        }
    }

    /**
     * Handles the menu options available to a logged-in user. This method
     * displays options such as showing account information, transaction
     * history, transferring money, withdrawing money, and exiting the logged-in
     * session. It continuously loops until the user chooses to exit.
     *
     * @param accountNumber The account number of the currently logged-in user.
     */
    private void login(String accountNumber) {
        boolean condition = true;
        int choice;
        // Loop continues as long as 'condition' is true.
        while (condition) {
            System.out.printf("Welcome back %s!\n"
                    + "1. Show information\n"
                    + "2. Show transaction information\n"
                    + "3. Transfer money\n"
                    + "4. Withdraw money\n"
                    + "5. Exit\n", bm.getName(accountNumber));
            choice = ValidInput.getPositiveNumberWithLimit("Please select function: ", 5);
            // Uses a switch statement to handle different menu choices.
            switch (choice) {
                case 1:
                    bm.printOutInfor(accountNumber);
                    break;
                case 2:
                    bm.printOutTransactionHistory(accountNumber);
                    break;
                case 3:
                    bm.transferMoney(accountNumber);
                    break;
                case 4:
                    bm.withdrawMoney(accountNumber);
                    break;
                case 5:
                    System.out.println("Thank you for using my app!");
                    System.out.println("See you later!");
                    condition = false;
            }
        }
    }
}
