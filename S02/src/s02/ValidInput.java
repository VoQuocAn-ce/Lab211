/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s02;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * S02-String Array Manipulations.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-01
 */
public class ValidInput {

    static Scanner sc = new Scanner(System.in);

    /**
     * Prompts the user to enter a positive integer within a specified limit.
     * This method ensures that the input is a valid integer and falls within
     * the acceptable range [1, max]. It handles both `NumberFormatException`
     * for non-integer inputs and `IllegalArgumentException` for numbers outside
     * the defined range.
     *
     * @param message The prompt message displayed to the user.
     * @param max The upper limit (inclusive) for the acceptable integer input.
     * @return The validated positive integer entered by the user.
     */
    public static int positiveNumberWithLimit(String message, int max) {
        int n = 0;
        boolean condition = true;
        do {
            condition = true;
            try {
                System.out.printf(message);
                n = Integer.parseInt(sc.nextLine().trim());
                if (n < 1 || n > max) {//get value in interval [1, max + 1]
                    String error = String.format("Input must be form 1 to %d!", max);
                    throw new IllegalArgumentException(error);
                }
            } catch (NumberFormatException e) {
                System.out.println("**********************");
                System.out.printf("Input must be form 1 to %d!\n", max);
                System.out.println("**********************");
                condition = false;
            } catch (IllegalArgumentException e) {
                System.out.println("**********************");
                System.out.printf("%s\n", e.getMessage());
                System.out.println("**********************");
                condition = false;
            }
        } while (!condition);
        return n;
    }

    /**
     * Prompts the user for input and validates that it consists only of
     * alphabetic characters (A-Z, a-z) and spaces. This method repeatedly asks
     * for input until a valid alphabetical string is provided.
     *
     * @return The validated alphabetical string entered by the user.
     */
    public static String alphabet() {
        String text = "";
        Pattern pattern = Pattern.compile("[A-Z][a-z ]*");
        while (true) {
            text = sc.nextLine().trim();
            Matcher matcher = pattern.matcher(text);
            if (matcher.matches()) {
                return text;
            } else {
                System.out.println("**********************");
                System.out.println("The input must be a name!");
                System.out.println("**********************");
            }
        }
    }
}
