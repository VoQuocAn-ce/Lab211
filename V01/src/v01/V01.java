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
public class V01 {

    /**
     * The main method is the entry point of the ATM simulation application. It
     * creates an instance of the ProcessMenu class and starts the application
     * by calling its `run()` method.
     *
     * @param args the command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        ProcessMenu pm = new ProcessMenu();
        pm.run();
    }
}
