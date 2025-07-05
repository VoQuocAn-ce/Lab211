/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v05;

/**
 * V05 - Doctor management program.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-26
 */
public class V05 {

    /**
     * The main entry point for the Doctor Management application. This method
     * creates an instance of `ProcessMenu` and starts the application's main
     * loop.
     *
     * @param args the command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        ProcessMenu pm = new ProcessMenu();
        pm.run();
    }

}
