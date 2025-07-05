/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v05;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * V05 - Doctor management program.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-26
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
                System.out.printf("A number must be a positive and smaller %d\n", max + 1);
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
     * Prompts the user for a doctor ID and validates the input against a
     * specific format. The ID must start with "DOC" followed by a number (e.g.,
     * "DOC 1", "DOC 123"). The method repeatedly prompts the user until a valid
     * ID is entered.
     *
     * @param message The message displayed to the user as a prompt for input.
     * @return A valid doctor ID in the specified format.
     */
    public static String getValidId(String message) {
        String content = "";
        Pattern pattern = Pattern.compile("DOC [1-9][0-9]*");
        // Loop indefinitely until valid content is provided.
        while (true) {
            System.out.printf(message);
            content = sc.nextLine().trim().toUpperCase();

            Matcher matcher = pattern.matcher(content);
            if (matcher.matches()) {
                return content;
            } else {
                System.out.println("**********************");
                System.out.println("The id must be \"DOC\" + a number");
                System.out.println("**********************");
            }
        }
    }

    /**
     * Prompts the user for a name. It validates that the input contains only
     * letters and spaces, and is at least 2 characters long. It also converts
     * the input to title case (first letter of each word capitalized).
     *
     * @param message The message displayed to the user as a prompt.
     * @return A valid, formatted name as a String.
     */
    public static String getValidName(String message) {
        String name = "";
        Pattern pattern = Pattern.compile("[A-Za-z ]+");
        // Loop indefinitely until a valid name is provided.
        while (true) {
            System.out.printf(message);
            name = sc.nextLine().trim().toLowerCase();
            if (name == null || name.isEmpty()) {
                return name;
            }
            String[] temp = name.split("\\s+");
            name = "";
            int index = 0;
            // Iterate through each word in the 'temp' array.
            for (String e : temp) {
                name += firstUpcase(e);
                if (index != temp.length - 1) {
                    name += " ";
                }
                index += 1;
            }
            Matcher matcher = pattern.matcher(name);
            if (matcher.matches()) {
                return name;
            } else {
                System.out.println("**********************");
                System.out.println("Name must only contain letters and spaces.");
                System.out.println("**********************");
            }
        }
    }

    /**
     * Converts the first character of a given string to uppercase and returns
     * the modified string.
     *
     * @param text The input string.
     * @return The string with its first character capitalized.
     */
    public static String firstUpcase(String text) {
        Character.toUpperCase(text.charAt(0));
        return Character.toUpperCase(text.charAt(0)) + text.substring(1);
    }

    /**
     * Prompts the user to select a specialization from a predefined list and
     * returns the chosen specialization.
     *
     * @param message The message displayed to the user as a prompt for input.
     * @return The selected specialization as a String. Returns an empty string
     * if the user chooses to exit.
     */
    public static String getValidSpecialization(String message) {
        String text = "";
        HashMap<Integer, String> specializations = new HashMap<>();
        // Starts an infinite while loop that continues until the user chooses to exit after selecting at least one specialization.
        specializations.put(1, "Orthopedics");
        specializations.put(2, "Obstetrics");
        specializations.put(3, "Orthodontic");
        specializations.put(4, "General Practitione");
        specializations.put(5, "Cardiologist");
        specializations.put(6, "Exit");
        System.out.printf("+-----+--------------------------+\n"
                + "| No. |    Option                |\n"
                + "+-----+--------------------------+\n");
        // Iterates through the 'course' HashMap to display the menu options to the user.
        for (Map.Entry<Integer, String> e : specializations.entrySet()) {
            System.out.printf("| %3d | %24s |\n",
                    e.getKey(),
                    e.getValue()
            );
        }
        System.out.printf("+-----+--------------------------+\n");
        int choice = getPositiveNumberWithLimit(message, 6);
        if (choice == 6) {
            return "";
        }
        return specializations.get(choice);
    }


    /**
     * Prompts the user for an integer input and validates that it is a positive
     * number (greater than 0). It repeatedly prompts until valid input is
     * received.
     *
     * @param message The prompt message to display to the user.
     * @return A validated positive integer.
     */
    public static int getPositiveNumberAnd0(String message) {
        int n = 0;
        boolean condition = true;
        String num = "";
        // Starts a do-while loop. The code inside 'do' block executes at least once.
        do {
            condition = true;
            // Starts a try-catch block to handle potential input errors.
            try {
                System.out.printf(message);
                num = sc.nextLine().trim();
                if (num == null || num.isEmpty()) {
                    return -1;
                }
                n = Integer.parseInt(num);
                if (n < 0) {
                    throw new IllegalArgumentException("A number must be graeter than -1");
                }
                // Catches a NumberFormatException, which occurs if the input string cannot be parsed into an integer.
            } catch (NumberFormatException e) {
                System.out.println("**********************");
                System.out.println("Input must be a number!");
                System.out.println("**********************");
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
     * Prompts the user for a file path and validates it. It checks if the path
     * matches a typical Windows file path format and if the file exists.
     *
     * @param message The message to display to the user as a prompt.
     * @return A validated file path string.
     */
    public static String getPath(String message) {
        String text = "";
        Pattern pattern = Pattern.compile("[a-zA-Z]{1}:(\\S+|\\\\\\S+(\\\\\\S+)*)\\.\\S{1,}");
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
                    System.out.println("File is not exist");
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
