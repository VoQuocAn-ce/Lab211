/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s12;

/**
 * S12 - Convert binary, octal and hexadecimal to decimal
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-25
 */
public class S12 {

    /**
     * The main entry point for the Number Base Conversion application.
     *
     * This method initializes an instance of {@code ProcessMenu} and starts the
     * main application loop by invoking its {@code run()} method. This sets up
     * the interactive menu where the user can perform various base conversion
     * operations.
     *
     * @param args Command line arguments (not utilized in this application).
     */
    public static void main(String[] args) {
        ProcessMenu pm = new ProcessMenu();
        pm.run();
    }

}
