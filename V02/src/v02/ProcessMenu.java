/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v02;

/**
 * V02 - Subsystem for listing and searching files by content.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-15
 */
public class ProcessMenu {

    private CSVManagement cm = new CSVManagement();

    /**
     * Runs the main menu loop for the CSV formatting program. It displays
     * options to the user and performs actions based on their choice.
     */
    public void run() {
        boolean condition = true;
        int choice;
        // Start of the main menu loop, continues as long as 'condition' is true.
        while (condition) {
            System.out.printf("======= Format CSV Prgram =======\n"
                    + "1. Import CSV\n"
                    + "2. Format Address\n"
                    + "3. Format Name\n"
                    + "4. Export CSV\n"
                    + "5. Exit\n");
            choice = ValidInput.getPositiveNumberWithLimit("Please choice one option: ", 5);
            // A switch statement to perform actions based on the user's 'choice'.
            switch (choice) {
                case 1:
                    cm.importCSV("Enter Path: ");
                    break;
                case 2:
                    cm.formatAddress();
                    break;
                case 3:
                    cm.formatName();
                    break;
                case 4:
                    cm.exportCSV();
                    break;
                case 5:
                    condition = false;
            }
        }
    }

}
