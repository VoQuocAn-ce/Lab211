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
public class ProcessMenu {

    private Transformation tm = new Transformation();

    /**
     * Executes the main application loop, presenting a menu for number base
     * conversions.
     *
     * This method continuously displays options to the user for converting
     * binary, octal, or hexadecimal numbers to decimal. The loop continues
     * until the user chooses to exit.
     */
    public void run() {
        boolean condition = true;
        // The main application loop. It continues as long as 'condition' is true.
        while (condition) {
            System.out.printf("1. Convert binary number to decimal number\n"
                    + "2. Convert octal number to decimal number\n"
                    + "3. Convert hexadecimal number to decimal number\n"
                    + "4. Exit\n");
            int choice = ValidInput.positiveNumberWithLimit("Please choose number (1 – 4): ", 4);
            switch (choice) {
                case 1:
                    tm.convertBinary();
                    break;
                case 2:
                    tm.convertOctal();
                    break;
                case 3:
                    tm.convertHexadecimal();
                    break;
                case 4:
                    condition = false;
            }
        }
    }

}
