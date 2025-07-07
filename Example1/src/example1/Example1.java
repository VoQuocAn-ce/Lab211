/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vo Quoc An - ce190460
 * @since  May 15, 2025
 */
public class Example1 {

    /**
     * the main method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProcessExample process = new ProcessExample();
        try {
            process.run();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

}
