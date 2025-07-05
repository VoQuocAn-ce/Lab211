/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v05;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * V05 - Doctor management program.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-26
 */
public class ProcessMenu {

    private DoctorHash dh = new DoctorHash();

    /**
     * Runs the main loop of the Doctor Management application. This method
     * displays a menu of options to the user (Add, Update, Delete, Search,
     * Exit) and processes their choice, delegating to appropriate methods in
     * the `DoctorHash` (dh) instance. It continues to loop until the user
     * chooses to exit.
     */
    public void run() {
        boolean condition = true;
        // Start the main application loop. It will continue as long as 'condition' is true.
        while (condition) {
            try {
                System.out.printf("========== Doctor Management ==========\n"
                        + " 1.   Add Doctor\n"
                        + " 2.   Update Doctor\n"
                        + " 3.   Delete Doctor\n"
                        + " 4.   Search Doctor\n"
                        + " 5.   Exit\n");
                int choice = ValidInput.getPositiveNumberWithLimit("Please select function: ", 5);
                switch (choice) {
                    case 1:
                        dh.addDoctor(dh.handleInput(false, false));
                        break;
                    case 2:
                        dh.updateDoctor(dh.handleInput(true, false));
                        break;
                    case 3:
                        dh.deleteDoctor(dh.handleInput(false, true));
                        break;
                    case 4:
                        dh.printOut(dh.searchDoctor(ValidInput.getValidContent("Enter text: ")));
                        break;
                    case 5:
                        condition = false;
                        break;

                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
