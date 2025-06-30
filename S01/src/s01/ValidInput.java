/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s01;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * S01-Manage student
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-05-17
 */
public class ValidInput {

    static Scanner sc = new Scanner(System.in);
    static SaveAndLoad sal = new SaveAndLoad();

    /**
     * Prompts the user for an integer input and validates it to be a positive
     * number within the range of 1 to 4 (inclusive), typically used for menu
     * selections. It repeatedly prompts until valid input is received.
     *
     * @param message The prompt message to display to the user.
     * @return A validated positive integer (1-4).
     */
    public static int positiveIntegerForMenu(String message) {
        int n = 0;
        boolean condition = true;
        do {
            condition = true;
            try {
                System.out.print(message);
                n = Integer.parseInt(sc.nextLine().trim());
                if (n > 4) {
                    throw new IllegalArgumentException("Please choose a number in (1-4)!");
                }
                if (n <= 0) {
                    throw new IllegalArgumentException("The input value must be greater than 0!");
                }
            } catch (NumberFormatException numberFormatException) {
                condition = false;
                System.out.println("**********************");
                System.out.println("The input value must be 1 to 4!");
                System.out.println("**********************");
            } catch (IllegalArgumentException e) {
                condition = false;
                System.out.println("**********************");
                System.out.printf("%s\n", e.getMessage());
                System.out.println("**********************");
            }

        } while (!condition);
        return n;
    }

    /**
     * Prompts the user for a string input and validates that it contains only
     * alphabetic characters (A-Z, a-z) and spaces. It repeatedly prompts until
     * valid input is received.
     *
     * @param message The prompt message to display to the user.
     * @return A validated string containing only letters and spaces.
     */
    public static String alphabet(String message) {
        String text = "";
        Pattern pattern = Pattern.compile("[A-Za-z ]+");
        while (true) {
            System.out.printf(message);
            text = sc.nextLine().trim();
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
     * Prompts the user to enter a date of birth in a specific format
     * (dd-MMM-uuuu), validates the format, the month, the day within the month,
     * and the age (must be 18-100 years old). It repeatedly prompts until a
     * valid date is provided.
     *
     * @return A validated date of birth string in "dd-MMM-uuuu" format.
     */
    public static String validFormatDate() {
        List<String> validMonths = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");//Save months to check if user entered correct month
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-uuuu", Locale.ENGLISH)
                .withResolverStyle(ResolverStyle.STRICT);//create format for LocalDate
        String sence = "";
        Pattern pattern = Pattern.compile("\\d{2}-\\S+-\\d{4}");
        boolean condition = true;
        while (condition) {
            try {
                LocalDate today = LocalDate.now();
                System.out.print("Date of birth: ");
                sence = sc.nextLine().trim();
                Matcher matcher = pattern.matcher(sence);
                if (!matcher.matches()) {
                    throw new IllegalArgumentException("Invalid format! Please enter date as (e.g., 01-Jan-2005)");
                }
                String[] temp = sence.split("-");////Separate with "-" to capitalize the first letter
                temp[1] = firstUpcase(temp[1]);
                sence = "";
                int index = 0;
                for (String e : temp) {
                    sence += firstUpcase(e);
                    if (index != temp.length - 1) {
                        sence += "-";
                    }
                    index += 1;
                }
                if (!validMonths.contains(temp[1])) {
                    throw new IllegalArgumentException("Invalid Month! Please enter date as (e.g., 01-Jan-2005)");
                }
                if (today.getYear() - Integer.parseInt(temp[2]) < 18) {
                    System.out.println("**********************");
                    System.out.println("This year is invalid; the student's age must be 18 or older.");
                    System.out.println("**********************");
                    continue;
                }
                if (today.getYear() - Integer.parseInt(temp[2]) > 100) {
                    System.out.println("**********************");
                    System.out.println("This year is invalid; the student's age must be smaller 100 years.");
                    System.out.println("**********************");
                    continue;
                }
                LocalDate dob = LocalDate.parse(sence, formatter);
                return sence;
            } catch (DateTimeParseException e) {
                System.out.println("**********************");
                System.out.println("Invalid day!");
                System.out.println("**********************");
            } catch (IllegalArgumentException e) {
                System.out.println("**********************");
                System.out.printf("%s\n", e.getMessage());
                System.out.println("**********************");
            }
        }
        return sence;
    }

    /**
     * Prompts the user for a name input and validates it to ensure it contains
     * only alphabetic characters and spaces, and has a minimum length of 2
     * characters. It also formats the name by capitalizing the first letter of
     * each word. The method repeatedly prompts until valid input is received.
     *
     * @param message The prompt message to display to the user (e.g., "Student
     * name: ").
     * @return A validated and formatted name string.
     */
    public static String name(String message) {
        String name = "";
        Pattern pattern = Pattern.compile("[A-Z][a-z]*( [A-Z][a-z]*)*");
        while (true) {
            System.out.printf(message);
            name = sc.nextLine().trim().toLowerCase();
            if (name == null || name.isEmpty()) {
                System.out.println("**********************");
                System.out.println("Input cannot be empty or null");
                System.out.println("**********************");
                continue;
            }
            String[] temp = name.split("\\s+");//Separate with " " to capitalize the first letter
            name = "";
            int index = 0;
            for (String e : temp) {
                name += firstUpcase(e);
                if (index != temp.length - 1) {
                    name += " ";
                }
                index += 1;
            }
            Matcher matcher = pattern.matcher(name);
            if (matcher.matches() && name.length() > 0) {
                return name;
            } else {
                System.out.println("**********************");
                System.out.println("Name must only contain letters and spaces.");
                System.out.println("**********************");
            }
        }
    }

    /**
     *
     * @param text
     * @return
     */
    public static String firstUpcase(String text) {
        Character.toUpperCase(text.charAt(0));
        return Character.toUpperCase(text.charAt(0)) + text.substring(1);
    }

    /**
     * Converts the first character of a given string to uppercase, leaving the
     * rest of the string unchanged.
     *
     * @param text The input string to capitalize.
     * @return A new string with its first character capitalized. If the input
     * string is empty, the behavior depends on `text.charAt(0)` which would
     * throw an `IndexOutOfBoundsException`. It's recommended to add a check for
     * empty or null strings for robustness.
     */
    public static String validStudentcode() {
        String text = "";
        boolean condition = true;
        List<String> listCode = new ArrayList<>();
        for (Student e : sal.readFile()) {
            listCode.add(e.getCode());
        }
        Pattern pattern = Pattern.compile("[A-Z]{2}\\d{3}");
        while (condition) {
            System.out.print("Student Code: ");
            text = sc.nextLine().trim().toUpperCase();
            Matcher matcher = pattern.matcher(text);
            if (matcher.matches() && !listCode.contains(text)) {//Returns student code if the student code does not exist in the file and is correct in format
                return text;
            } else if (!matcher.matches()) {
                System.out.println("**********************");
                System.out.println("Invalid input! ex: SV001");
                System.out.println("**********************");
            }
            if (listCode.contains(text)) {
                System.out.println("**********************");
                System.out.println("This code is used!");
                System.out.println("**********************");
            }
        }
        return text;
    }

    /**
     * Prompts the user to enter a learning point (score) and validates that it
     * is a floating-point number within the range of 0 to 10 (inclusive). It
     * repeatedly prompts until valid input is received.
     *
     * @return A validated float representing the learning point.
     */
    public static float validLearningPoint() {
        float point = 0;
        boolean condition = true;
        do {
            condition = true;
            try {
                System.out.print("Learning poitn: ");
                point = Float.parseFloat(sc.nextLine());
                if (point < 0 || point > 10) {//Check to make sure the score is a number greater than or equal to 0 and less than or equal to 10
                    throw new IllegalArgumentException("The point (0-10)!");
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("**********************");
                System.out.println("The point must be a number!");
                System.out.println("**********************");
                condition = false;
            } catch (IllegalArgumentException e) {
                System.out.println("**********************");
                System.out.printf("%s\n", e.getMessage());
                System.out.println("**********************");
                condition = false;
            }
        } while (!condition);
        return point;
    }

}
