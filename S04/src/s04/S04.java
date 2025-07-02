/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s04;

/**
 * S04-Write a login function uses MD5 encryption for passwords (separate from
 * FPT Webmail software Project.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-05-25
 */
public class S04 {

    /**
     * The main entry point of the application. This method is responsible for
     * initiating the program's execution flow, specifically by creating an
     * instance of `ProcessMenu` and invoking its `menu` method.
     *
     * @param args The command line arguments passed to the program (not used in
     * this specific implementation).
     */
    public static void main(String[] args) {
        ProcessMenu pm = new ProcessMenu();
        pm.menu();
    }

}
