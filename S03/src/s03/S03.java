/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s03;

import s03.Database.DictionaryLibrary;

/**
 * S03-English – English dictionary
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-01
 */
public class S03 {

    /**
     * The main entry point of the application. This is where the program
     * execution begins.
     *
     * @param args The command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        ProcessMenu pm = new ProcessMenu();
        pm.menu();
    }

}
