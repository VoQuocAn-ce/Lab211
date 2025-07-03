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
public class V02 {

    /**
     * The main method is the entry point of the application. It initializes and
     * runs the ProcessMenu to start the CSV formatting program.
     *
     * @param args the command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        ProcessMenu pm = new ProcessMenu();
        pm.run();
    }

}
