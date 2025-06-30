/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s01;

/**
 * S01-Manage student
 * 
 * @author Vo Quoc An - ce190460
 * @since 2025-05-17
 */
public class S01 {

    /**
     * The main method is the entry point of the Java application.
     * It initializes and starts the student management process.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        ProcessS01 menuAndRun = new ProcessS01();
        menuAndRun.run();
    }

}
