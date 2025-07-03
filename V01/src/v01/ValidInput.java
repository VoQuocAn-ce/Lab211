/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * V01 - Simulate ATM’s operation
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-09
 */
public class ValidInput {

    private static Scanner sc = new Scanner(System.in);

    /**
     * Prompts the user for a positive integer within a specified limit. It
     * repeatedly prompts until a valid integer (greater than or equal to 1 and
     * less than or equal to 'max') is entered.
     *
     * @param message The message displayed to the user as a prompt.
     * @param max The maximum allowed value for the input (inclusive).
     * @return A valid integer entered by the user.
     */
    public static int getPositiveNumberWithLimit(String message, int max) {
        int n = 0;
        boolean condition = true;
        // Start of the do-while loop, which ensures the code block executes at least once.
        do {
            condition = true;
            // Begin a try block to catch potential exceptions during input parsing and validation.
            try {
                System.out.printf(message);
                n = Integer.parseInt(sc.nextLine().trim());
                if (n < 1 || n > max) {//get value in interval [1, max + 1]
                    String error = String.format("A number must be a positive and smaller %d", max + 1);
                    throw new IllegalArgumentException(error);
                }
                // Catch block for when the input cannot be parsed into an integer.
            } catch (NumberFormatException e) {
                System.out.println("**********************");
                System.out.println("Input must be from 1 to 3!");
                System.out.println("**********************");
                condition = false;
                // Catch block for when an IllegalArgumentException is thrown (e.g., number out of range).
            } catch (IllegalArgumentException e) {
                System.out.println("**********************");
                System.out.printf("%s\n", e.getMessage());
                System.out.println("**********************");
                condition = false;
            }
            // The loop continues as long as 'condition' is false (meaning invalid input was received).
        } while (!condition);
        return n;
    }

    /**
     * Prompts the user for an account number specifically for login. It
     * validates that the input is a 14-digit number.
     *
     * @param message The message displayed to the user as a prompt.
     * @return A valid 14-digit account number as a String.
     */
    public static String getValidAccountNumberForLogin(String message) {
        String text = "";
        Pattern pattern = Pattern.compile("\\d{14}");
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
                return text;
            } else {
                System.out.println("**********************");
                System.out.println("Invalid input!");
                System.out.println("**********************");
            }
        }
    }

    /**
     * Prompts the user for an account number for transferring money. It
     * validates that the input is a 14-digit number and that the account number
     * exists in the provided `accountList`.
     *
     * @param message The message displayed to the user as a prompt.
     * @param accountList A HashMap containing existing account numbers and
     * their PINs.
     * @return A valid and existing 14-digit account number as a String.
     */
    public static String getValidAccountNumberForTransfer(String origin, String message, HashMap<String, String> accountList) {
        String text = "";
        Pattern pattern = Pattern.compile("\\d{14}");
        // Start an infinite `while` loop that continues until valid and unique input is received and returned.
        while (true) {
            System.out.printf(message);
            text = sc.nextLine().trim();
            // Check if the input string is null or empty.
            if (text == null || text.isEmpty()) {
                System.out.println("**********************");
                System.out.println("Input must not be empty!");
                System.out.println("**********************");
                continue;
            }
            Matcher matcher = pattern.matcher(text);
            if (matcher.matches()) {
                // If the format is correct, check if the account number exists in the provided HashMap.
                if (!accountList.containsKey(text)) {
                    System.out.println("**********************");
                    System.out.println("Account number is not exist!");
                    System.out.println("**********************");
                    continue;
                } else if (text.equals(origin)) {
                    System.out.println("Can't tranfer to yourself");
                    continue;
                }
                return text;
            } else {
                System.out.println("**********************");
                System.out.println("Invalid input!");
                System.out.println("**********************");
            }
        }
    }

    /**
     * Prompts the user for a new account number for creation. It validates that
     * the input is a 14-digit number and that the account number does *not*
     * already exist in the provided `accountList`.
     *
     * @param message The message displayed to the user as a prompt.
     * @param accountList A HashMap containing existing account numbers and
     * their PINs.
     * @return A valid and unique 14-digit account number as a String.
     */
    public static String getValidAccountNumberForCreate(String message, HashMap<String, String> accountList) {
        String text = "";
        Pattern pattern = Pattern.compile("\\d{14}");
        // Start an infinite `while` loop that continues until valid and unique input is received and returned.
        while (true) {
            System.out.printf(message);
            text = sc.nextLine().trim();
            // Check if the input string is null or empty.
            if (text == null || text.isEmpty()) {
                System.out.println("**********************");
                System.out.println("Input must not be empty!");
                System.out.println("**********************");
                continue;
            }
            Matcher matcher = pattern.matcher(text);
            if (matcher.matches()) {
                // If the format is correct, check if the account number exists in the provided HashMap.
                if (accountList.containsKey(text)) {
                    System.out.println("**********************");
                    System.out.println("Account number is used!");
                    System.out.println("**********************");
                    continue;
                }
                return text;
            } else {
                System.out.println("**********************");
                System.out.println("Invalid input!");
                System.out.println("**********************");
            }
        }
    }

    /**
     * Prompts the user for a PIN. It validates that the input is a 6-digit
     * number.
     *
     * @param message The message displayed to the user as a prompt.
     * @return A valid 6-digit PIN as a String.
     */
    public static String getValidPin(String message) {
        String text = "";
        Pattern pattern = Pattern.compile("\\d{6}");
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
                return text;
            } else {
                System.out.println("**********************");
                System.out.println("Invalid input!");
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
                System.out.println("**********************");
                System.out.println("Input cannot be empty or null");
                System.out.println("**********************");
                continue;
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
            if (matcher.matches() && name.length() > 1) {
                return name;
            } else if (name.length() < 2) {
                System.out.println("**********************");
                System.out.println("Invalid name! Name must be longer than 2 characters!");
                System.out.println("**********************");
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
     * Prompts the user to select a money type (currency) from a predefined
     * list. It displays a menu of options and validates the user's choice.
     *
     * @param message The message displayed to the user as a prompt (currently
     * unused in the method implementation).
     * @return The selected currency type as a String (e.g., "VND", "USD",
     * "Yen").
     */
    public static String getMonneyType(String message) {
        String text = "";
        int i = 1;
        HashMap<Integer, String> MonneyType = new HashMap<>();
        MonneyType.put(1, "VND");
        MonneyType.put(2, "USD");
        MonneyType.put(3, "Yen");
        System.out.printf("+-----+----------------+\n"
                + "| No. |    Option      |\n"
                + "+-----+----------------+\n");
        // Iterate through each entry (key-value pair) in the 'MonneyType' HashMap.
        for (Map.Entry<Integer, String> e : MonneyType.entrySet()) {
            System.out.printf("| %3d | %14s |\n",
                    e.getKey(),
                    e.getValue()
            );
        }
        System.out.printf("+-----+----------------+\n");
        int choice = getPositiveNumberWithLimit("Please choice a option: ", 3);
        return MonneyType.get(choice);
    }

    /**
     * Prompts the user for a monetary amount. It validates that the input is a
     * positive number.
     *
     * @param message The message displayed to the user as a prompt.
     * @return A valid positive double representing the monetary amount.
     */
    public static double getInvalidMonney(String message) {
        double n = 0;
        boolean condition = true;
        // Start of the do-while loop, which ensures the code block executes at least once.
        do {
            condition = true;
            // Begin a try block to catch potential exceptions during input parsing and validation.
            try {
                System.out.printf(message);
                n = Double.parseDouble(sc.nextLine().trim());
                if (n <= 0) {
                    throw new IllegalArgumentException("A number must be greater than 0!");
                }
                // Catch block for when the input cannot be parsed into a double.
            } catch (NumberFormatException e) {
                System.out.println("**********************");
                System.out.println("Input must be a number!");
                System.out.println("**********************");
                condition = false;
                // Catch block for when an IllegalArgumentException is thrown (e.g., number not greater than 0).
            } catch (IllegalArgumentException e) {
                System.out.println("**********************");
                System.out.printf("%s\n", e.getMessage());
                System.out.println("**********************");
                condition = false;
            }
            // The loop continues as long as 'condition' is false (meaning invalid input was received).
        } while (!condition);
        return n;
    }

    /**
     * Prompts the user for transaction content/description. It validates that
     * the input contains only letters, numbers, and spaces. If the input is
     * empty or null, it returns a default content string.
     *
     * @param message The message displayed to the user as a prompt.
     * @param user The User object for whom the content is being generated (used
     * for default content).
     * @return A valid content string.
     */
    public static String getValidContent(String message, User user) {
        String name = "";
        Pattern pattern = Pattern.compile("[A-Za-z0-9 ]+");
        // Loop indefinitely until valid content is provided.
        while (true) {
            System.out.printf(message);
            name = sc.nextLine().trim().toLowerCase();
            if (name == null || name.isEmpty()) {
                return String.format("%s transfer monney", user.getUserName());
            }
            Matcher matcher = pattern.matcher(name);
            if (matcher.matches()) {
                return name;
            } else {
                System.out.println("**********************");
                System.out.println("Name must only contain letters, number and spaces.");
                System.out.println("**********************");
            }
        }
    }

}
