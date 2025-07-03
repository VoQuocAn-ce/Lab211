/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v03;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * V03 - File program handling
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-18
 */
public class ValidInput {

    private static Scanner sc = new Scanner(System.in);

    /**
     * Prompts the user for an integer input and validates it to be a positive
     * number within a specified limit (inclusive of 1, exclusive of max + 1).
     *
     * @param message The message to display to the user as a prompt.
     * @param max The maximum allowed value (exclusive, so the range is [1,
     * max]).
     * @return A valid integer within the specified range.
     */
    public static int getPositiveNumberWithLimit(String message, int max) {
        int n = 0;
        boolean condition = true;
        // Start of a do-while loop, which ensures the code inside runs at least once.
        do {
            condition = true;
            // Start of a try-catch block to handle potential exceptions during input parsing.
            try {
                System.out.printf(message);
                n = Integer.parseInt(sc.nextLine().trim());
                if (n < 1 || n > max) {//get value in interval [1, max + 1]
                    String error = String.format("A number must be a positive and smaller %d", max + 1);
                    throw new IllegalArgumentException(error);
                }
                // Catches NumberFormatException if the input cannot be parsed as an integer.
            } catch (NumberFormatException e) {
                System.out.println("**********************");
                System.out.println("Input must be from 1 to 3!");
                System.out.println("**********************");
                condition = false;
                // Catches IllegalArgumentException thrown if the number is out of the valid range.
            } catch (IllegalArgumentException e) {
                System.out.println("**********************");
                System.out.printf("%s\n", e.getMessage());
                System.out.println("**********************");
                condition = false;
            }
            // The loop continues as long as 'condition' is false (i.e., input was invalid).
        } while (!condition);
        return n;
    }

    /**
     * Prompts the user for a file path and validates it. It checks if the path
     * matches a typical Windows file path format and if the file exists.
     *
     * @param message The message to display to the user as a prompt.
     * @return A validated file path string.
     */
    public static String getPath(String message) {
        String text = "";
        Pattern pattern = Pattern.compile("[a-zA-Z]{1}:(\\S+|\\\\\\S+(\\\\\\S+)*)");
        // Start an infinite `while` loop that continues until valid and unique input is received and returned.
        while (true) {
            System.out.printf(message);
            text = sc.nextLine().trim();
            if (text == null || text.isEmpty()) {
                System.out.println("**********************");
                System.out.println("Input must not be empty!");
                System.out.println("**********************");
                continue;
            }
            Matcher matcher = pattern.matcher(text);
            if (matcher.matches()) {
                // Splits the path by backslashes.
                String[] temp = text.split("\\\\");
                // Reconstructs the path using File.separator to ensure cross-platform compatibility
                // (though the regex is Windows-specific). This line 'String.join("\\", temp)'
                // effectively just puts the backslashes back as they were split,
                // it doesn't truly make it cross-platform.
                File file = new File(String.join("\\", temp));
                // Checks if the file specified by the path actually exists on the file system.
                if (!file.exists()) {
                    System.out.println("Path doesn't exist");
                    continue;
                }
                return String.join("\\", temp);
            } else {
                System.out.println("**********************");
                System.out.println("Invalid path!(ex: d:\\summer\\csv1.csv)");
                System.out.println("**********************");
            }
        }
    }

    /**
     * Prompts the user for a file path and validates it. It checks if the path
     * matches a typical Windows file path format and if the file exists.
     *
     * @param message The message to display to the user as a prompt.
     * @return A validated file path string.
     */
    public static String getPathForCheck(String message) {
        String text = "";
        Pattern pattern = Pattern.compile("[a-zA-Z]{1}:(\\S+|\\\\\\S+(\\\\\\S+)*)");
        // Start an infinite `while` loop that continues until valid and unique input is received and returned.
        while (true) {
            System.out.printf(message);
            text = sc.nextLine().trim();
            if (text == null || text.isEmpty()) {
                System.out.println("**********************");
                System.out.println("Input must not be empty!");
                System.out.println("**********************");
                continue;
            }
            Matcher matcher = pattern.matcher(text);
            if (matcher.matches()) {
                // Splits the path by backslashes.
                String[] temp = text.split("\\\\");
                // Reconstructs the path using File.separator to ensure cross-platform compatibility
                // (though the regex is Windows-specific). This line 'String.join("\\", temp)'
                // effectively just puts the backslashes back as they were split,
                // it doesn't truly make it cross-platform.
                return String.join("\\", temp);
            } else {
                System.out.println("**********************");
                System.out.println("Invalid path!(ex: d:\\summer\\csv1.csv)");
                System.out.println("**********************");
            }
        }
    }

    public static int getSize(String message) {
        int n = 0;
        boolean condition = true;
        // Starts a do-while loop. The code inside 'do' block executes at least once.
        do {
            condition = true;
            // Starts a try-catch block to handle potential input errors.
            try {
                System.out.printf(message);
                n = Integer.parseInt(sc.nextLine().trim());
                if (n < 1) {
                    throw new IllegalArgumentException("A size must be greater than 0!");
                }
                // Catches a NumberFormatException, which occurs if the input string cannot be parsed into an integer.
            } catch (NumberFormatException e) {
                System.out.println("Size if digit");
                condition = false;
                // Catches an IllegalArgumentException, which is thrown if 'n' is not positive.
            } catch (IllegalArgumentException e) {
                System.out.println("**********************");
                System.out.printf("%s\n", e.getMessage());
                System.out.println("**********************");
                condition = false;
            }
            // The loop continues as long as 'condition' is false (i.e., input was invalid).
        } while (!condition);
        return n;
    }

    /**
     * Prompts the user for content input and validates it to ensure it contains
     * only letters (A-Z, a-z), numbers (0-9), and spaces. The method will
     * repeatedly prompt the user until valid content is provided.
     *
     * @param message The prompt message to display to the user (e.g., "Enter
     * Content: ").
     * @return A validated String containing only letters, numbers, and spaces.
     */
    public static String getValidContent(String message) {
        String content = "";
        Pattern pattern = Pattern.compile("[A-Za-z0-9 ]+");
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
                System.out.println("Content must only contain letters, number and spaces.");
                System.out.println("**********************");
            }
        }
    }

}
