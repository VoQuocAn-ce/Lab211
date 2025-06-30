/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s02;

/**
 * S02-String Array Manipulations.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-01
 */
public class ProcessMenu {

    private ListManagement lm;

    /**
     * Constructor for the `ProcessMenu` class. This constructor is responsible
     * for initializing an instance of `ListManagement`.
     */
    public ProcessMenu() {
        lm = new ListManagement();
    }

    /**
     * Orchestrates the main flow of list operations for the user. This method
     * guides the user through adding new names, displaying them, sorting them
     * alphabetically, and then displaying the sorted list. It acts as a simple
     * menu-like sequence of actions.
     */
    public void menu() {
        lm.addNew();
        System.out.println("List input name:");
        lm.printOut();
        lm.bubbleSort();
        System.out.println("List sort name:");
        lm.printOut();
    }
}
