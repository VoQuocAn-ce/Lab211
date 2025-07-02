/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s12;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * S12 - Convert binary, octal and hexadecimal to decimal
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-25
 */
public class ValidInput {

    private static Scanner sc = new Scanner(System.in);

    /**
     * Prompts the user for input and continuously validates it as a binary
     * number. A valid binary number must consist only of '0' and '1' digits.
     * The method will keep prompting until valid input is received.
     *
     * @param message The prompt message to display to the user (e.g., "Enter
     * binary number: ").
     * @return A {@code String} representing the validated binary number.
     */
    public static String getValidBinary(String message) {
        String content = "";
        Pattern pattern = Pattern.compile("[01]+");
        // Loop indefinitely until valid content is provided.
        while (true) {
            System.out.printf(message);
            content = sc.nextLine().trim();
            if (content == null || content.isEmpty()) {
                continue;
            }
            Matcher matcher = pattern.matcher(content);
            if (matcher.matches()) {
                return content;
            } else {
                System.out.println("**********************");
                System.out.println("Content must only contain 0 and 1.");
                System.out.println("**********************");
            }
        }
    }

    /**
     * Prompts the user for input and continuously validates it as an octal
     * number. A valid octal number must consist only of digits from '0' to '7'.
     * The method will keep prompting the user until valid input is received.
     *
     * @param message The prompt message to display to the user (e.g., "Enter
     * octal number: ").
     * @return A {@code String} representing the validated octal number.
     */
    public static String getValidOctal(String message) {
        String content = "";
        Pattern pattern = Pattern.compile("[0-7]+");
        // Loop indefinitely until valid content is provided.
        while (true) {
            System.out.printf(message);
            content = sc.nextLine().trim();
            if (content == null || content.isEmpty()) {
                continue;
            }
            Matcher matcher = pattern.matcher(content);
            if (matcher.matches()) {
                return content;
            } else {
                System.out.println("**********************");
                System.out.println("Content must only contain 0 to 7.");
                System.out.println("**********************");
            }
        }
    }

    /**
     * Prompts the user for input and continuously validates it as a hexadecimal
     * number. A valid hexadecimal number must consist only of digits '0'
     * through '9' and letters 'A' through 'F' (case-insensitive, as input is
     * converted to uppercase). The method will keep prompting the user until
     * valid input is received.
     *
     * @param message The prompt message to display to the user (e.g., "Enter
     * hexadecimal number: ").
     * @return A {@code String} representing the validated hexadecimal number in
     * uppercase.
     */
    public static String getValidHexadecimal(String message) {
        String content = "";
        Pattern pattern = Pattern.compile("[0-9A-F]+");
        // Loop indefinitely until valid content is provided.
        while (true) {
            System.out.printf(message);
            content = sc.nextLine().trim().toUpperCase();
            if (content == null || content.isEmpty()) {
                continue;
            }
            Matcher matcher = pattern.matcher(content);
            if (matcher.matches()) {
                return content;
            } else {
                System.out.println("**********************");
                System.out.println("Content must only contain 0 to 9 and A-F.");
                System.out.println("**********************");
            }
        }
    }

    /**
     * Prompts the user for an integer input and validates that it is a positive
     * number (greater than 0) and less than or equal to a specified maximum
     * value. It repeatedly prompts until valid input is received.
     *
     * @param message The prompt message to display to the user.
     * @param max The maximum allowed value for the input number (inclusive).
     * @return A validated positive integer within the specified limit.
     */
    public static int positiveNumberWithLimit(String message, int max) {
        int n = 0;
        boolean condition = true;
        // Loop continues until a valid integer within the specified range is entered.
        do {
            condition = true;
            try {
                System.out.printf(message);
                n = Integer.parseInt(sc.nextLine().trim());
                if (n < 1 || n > max) {//get value in interval [1, max + 1]
                    String error = String.format("A number must be a positive and smaller %d", max + 1);
                    throw new IllegalArgumentException(error);
                }
            } catch (NumberFormatException e) {
                System.out.println("**********************");
                System.out.println("Input must be from 1 to 3!");
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
}
