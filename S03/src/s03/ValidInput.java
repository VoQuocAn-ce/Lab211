/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s03;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * S03-English – English dictionary
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-01
 */
public class ValidInput {

    static Scanner sc = new Scanner(System.in);

    /**
     * Prompts the user for a vocabulary word and validates that it contains
     * only alphanumeric characters (letters and numbers). This method
     * repeatedly asks for input until a valid non-empty string consisting of
     * only alphanumeric characters is provided.
     *
     * @param message The prompt message displayed to the user.
     * @return The validated vocabulary word entered by the user.
     */
    public static String vocabulary(String message) {
        String vocabulary = "";
        // Start an infinite loop that continues until valid input is received and returned.
        while (true) {
            System.out.print(message);
            vocabulary = sc.nextLine().trim();
            Pattern pattern = Pattern.compile("[0-9A-Za-z]+");
            if (vocabulary == null || vocabulary.isEmpty()) {
                System.out.println("*******************");
                System.out.println("The input must not be empty!");
                System.out.println("*******************");
                continue;
            }
            Matcher matcher = pattern.matcher(vocabulary);
            // Check if the entire input string matches the defined pattern (i.e., contains only letters).
            if (matcher.matches()) {
                return vocabulary;
            } else {
                // If the input does not match the pattern (contains non-alphabetic characters).
                System.out.println("*******************");
                System.out.println("The input must be a word!");
                System.out.println("*******************");
            }

        }
    }

    /**
     * Prompts the user for a meaning (definition) and ensures the input is not
     * empty. This method repeatedly asks for input until a non-empty string is
     * provided.
     *
     * @param message The prompt message displayed to the user.
     * @return The validated non-empty string entered by the user, representing
     * a meaning.
     */
    public static String meaning(String message) {
        String meanning = "";
        // Start an infinite loop that continues until valid input is received and returned.
        while (true) {
            System.out.print(message);
            meanning = sc.nextLine().trim();
            // Check if the input is null or empty.
            if (meanning == null || meanning.isEmpty()) {
                System.out.println("*******************");
                System.out.println("The input must not be empty!");
                System.out.println("*******************");
                continue;
            }
            return meanning;
        }
    }

    /**
     * Prompts the user to enter a positive integer specifically for menu
     * selection, ensuring the input is within the valid range of 1 to 4. This
     * method robustly handles invalid input, such as non-integer values or
     * numbers outside the specified range, by prompting the user to re-enter.
     *
     * @param message The prompt string displayed to the user.
     * @return The validated integer choice (1, 2, 3, or 4).
     */
    public static int positiveIntegerForMenu(String message) {
        int n = 0;
        // Initialize a boolean flag to control the do-while loop.
        boolean condition = true;
        // Use a do-while loop to repeatedly ask for input until a valid one is provided.
        do {
            condition = true;
            try {
                System.out.print(message);
                n = Integer.parseInt(sc.nextLine().trim());
                // If the parsed integer is greater than 4, it's an invalid menu choice.
                if (n > 4) {
                    throw new IllegalArgumentException("Please choose a number in (1-4)!");
                }
                // If the parsed integer is less than or equal to 0, it's also an invalid menu choice.
                if (n <= 0) {
                    throw new IllegalArgumentException("The input value must be greater than 0!");
                }
                // Catch block for NumberFormatException, which occurs if the input cannot be parsed into an integer.
            } catch (NumberFormatException numberFormatException) {
                condition = false;
                System.out.println("**********************");
                System.out.println("The input value must be 1 to 4!");
                System.out.println("**********************");
                // Catch block for IllegalArgumentException, which is thrown by the 'if' conditions above.
            } catch (IllegalArgumentException e) {
                condition = false;
                System.out.println("**********************");
                System.out.printf("%s\n", e.getMessage());
                System.out.println("**********************");
            }

        } while (!condition);
        return n;
    }
}
