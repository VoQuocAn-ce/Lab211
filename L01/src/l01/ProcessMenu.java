/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l01;

/**
 * L01-Create a Java console program to manage students.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-05-21
 */
public class ProcessMenu {

    private StudentManagement sm = new StudentManagement();

    /**
     * Displays the main run for the Student Management application and handles
     * user choices. The run allows users to perform various operations like
     * creating, finding, sorting, updating, deleting, and reporting on student
     * data. The run loop continues until the user chooses to exit.
     */
    public void run() {
        boolean condition = true;
        // Starts a while loop that continues as long as 'condition' is true.
        // This loop displays the main run and handles user input.
        while (condition) {
            System.out.printf("        WELCOME TO STUDENT MANAGEMENT\n"
                    + "1. Create\n"
                    + "2. Find and Sort\n"
                    + "3. Update/Delete\n"
                    + "4. Report\n"
                    + "5. Exit\n"
            );
            int choice = ValidInput.getPositiveNumberWithLimit("        Please choose: ", 5);
            // Uses a switch statement to execute different code blocks based on the user's 'choice'.
            switch (choice) {
                case 1:
                    sm.create();
                    break;
                case 2:
                    sm.searchStudent();
                    break;
                case 3:
                    String choice2 = ValidInput.getOneInTwoChoice("Do you want to update (U) or delete (D) student?\n", "U", "D");
                    // Checks if the user chose "U" (Update).
                    if (choice2.equals("U")) {
                        sm.updateInfor();
                        break;
                    } // Checks if the user chose "D" (Delete).
                    else if (choice2.equals("D")) {
                        sm.deleteStudent();
                        break;
                    }
                case 4:
                    sm.report();
                    break;
                case 5:
                    // Sets the 'condition' to false to exit the main while loop,
                    // effectively ending the program's run display.
                    condition = false;

            }
        }
    }
}
