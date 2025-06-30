/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s03;

import s03.Database.DictionaryLibrary;

/**
 * S03-English – English dictionary
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-01
 */
public class ProcessMenu {

    /**
     * Displays the main menu for the English-English Dictionary application and
     * handles user interaction based on their choices. This method runs in a
     * loop until the user decides to exit.
     */
    public void menu() {
        DictionaryLibrary dl = new DictionaryLibrary();
        boolean condition = true;
        // The main loop that keeps the menu running until the user decides to exit.
        while (condition) {
            System.out.printf("==========English – English Dictionary==========\n"
                    + "1. Create a new word\n"
                    + "2. Edit a word\n"
                    + "3. Look up meaning\n"
                    + "4. Exit\n"
            );
            int choice = ValidInput.positiveIntegerForMenu("Please choose number (1 – 4): ");
            switch (choice) {
                case 1:
                    dl.create();
                    break;
                case 2:
                    dl.edit();
                    break;
                case 3:
                    dl.lookUp();
                    break;
                case 4:
                    condition = false;
            }
        }
    }
}
