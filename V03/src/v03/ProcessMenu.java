/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v03;

/**
 * V03 - File program handling
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-18
 */
public class ProcessMenu {

    private FileManagement fm = new FileManagement();

    /**
     * Executes the main program loop, providing a menu-driven interface for
     * file processing operations. This method continuously displays a list of
     * options to the user and performs the corresponding file operation based
     * on the user's input until the user chooses to exit.
     */
    public void run() {
        boolean condition = true;
        // The loop continues as long as 'condition' is true.
        while (condition) {
            System.out.printf("========= File Processing =========\n"
                    + "1. Check Path\n"
                    + "2. Get file name with type java\n"
                    + "3. Get file with size greater than input\n"
                    + "4. Write more content to file\n"
                    + "5. Read file and count characters\n"
                    + "6. Exit\n");
            int choice = ValidInput.getPositiveNumberWithLimit("Please choice one option: ", 6);
            switch (choice) {
                case 1:
                    fm.checkFile();
                    break;
                case 2:
                    fm.getFileJava();
                    break;
                case 3:
                    fm.getFileWithSize();
                    break;
                case 4:
                    fm.addNewContent();
                    break;
                case 5:
                    fm.countWordInsideFile();
                    break;
                case 6:
                    condition = false;
            }
        }
    }

}
