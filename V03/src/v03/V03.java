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
public class V03 {

    /**
     * The main entry point of the File Processing application. This method
     * creates an instance of `ProcessMenu` and starts the application's main
     * execution loop.
     *
     * @param args The command line arguments passed to the program. These are
     * not currently used in this application.
     */
    public static void main(String[] args) {
        ProcessMenu pm = new ProcessMenu();
        pm.run();
    }

}
