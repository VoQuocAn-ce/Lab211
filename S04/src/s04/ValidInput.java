/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s04;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * S04-Write a login function uses MD5 encryption for passwords (separate from
 * FPT Webmail software Project.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-05-25
 */
public class ValidInput {

    static Scanner sc = new Scanner(System.in);

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

    /**
     * Prompts the user for input and validates that the input consists only of
     * alphabetic characters and spaces. It repeatedly asks for input until a
     * valid string is provided.
     *
     * @param message The prompt message to display to the user before awaiting
     * input.
     * @return A string containing only alphabetic characters and spaces,
     * trimmed of leading/trailing whitespace.
     */
    public static String alphabet(String message) {
        String text = "";
        Pattern pattern = Pattern.compile("[A-Za-z ]{1,}");
        // Start an infinite `while` loop that continues until valid input is received and returned.
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
                System.out.println("Invalid input! the input must be a alphabet!");
                System.out.println("**********************");
            }
        }
    }

    /**
     * Prompts the user to enter a username and validates it against several
     * criteria: 1. It must not be empty. 2. It must not already exist in the
     * provided list of accounts. 3. It must match a specific regular expression
     * pattern. The method repeatedly asks for input until a username that
     * satisfies all conditions is provided.
     *
     * @param message The prompt message to display to the user (e.g., "Enter a
     * new username: ").
     * @param accountList A HashMap where keys are existing usernames (String)
     * and values are their associated passwords (String). This is used to check
     * for username uniqueness.
     * @return A validated and unique username string, trimmed of
     * leading/trailing whitespace.
     */
    public static String validUserName(String message, HashMap<String, String> accountList) {
        String text = "";
        Pattern pattern = Pattern.compile("\\S{2,}");
        // Start an infinite `while` loop that continues until valid and unique input is received and returned.
        while (true) {
            System.out.printf(message);
            text = sc.nextLine().trim();
            if (accountList.containsKey(text)) {
                System.out.println("**********************");
                System.out.println("User name is used!");
                System.out.println("**********************");
                continue;
            }
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
     * Prompts the user for a username specifically for login purposes and
     * validates its format. This method ensures the input is not empty and
     * consists only of alphabetic characters and spaces. It repeatedly asks for
     * input until a valid format is provided. Unlike a registration process,
     * this method does not check for the uniqueness of the username against a
     * list of existing accounts.
     *
     * @param message The prompt message to display to the user (e.g., "Enter
     * your username: ").
     * @return A validated username string. This string will only contain
     * alphabetic characters and spaces, and will have leading/trailing
     * whitespace removed. It is suitable for use in a login attempt.
     */
    public static String validUserNameForLogin(String message) {
        String text = "";
        Pattern pattern = Pattern.compile("\\S{2,}");
        // Starts an infinite `while` loop that continues until valid input is received and returned.
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
                System.out.println("Invalid input! the input must be a alphabet!");
                System.out.println("**********************");
            }
        }
    }

    /**
     * Prompts the user for a password and validates it based on emptiness and
     * length. If the password is less than 8 characters, it offers the user a
     * choice to continue or re-enter. This method repeatedly asks for input
     * until a non-empty password is provided, or the user chooses to proceed
     * with a short password.
     *
     * @param message The prompt message to display to the user (e.g., "Enter
     * password: ").
     * @return A non-empty password string, trimmed of leading/trailing
     * whitespace.
     */
    public static String validPassword(String message) {
        String text = "";
        // Starts an infinite `while` loop that continues until valid input is received and returned.
        while (true) {
            System.out.printf(message);
            text = sc.nextLine().trim();
            if (text == null || text.isEmpty()) {
                System.out.println("**********************");
                System.out.println("Input must not be empty!");
                System.out.println("**********************");
                continue;
            }
            // This 'if' statement checks if the length of the 'text' (password) is less than 8 characters.
            // This is a common requirement for password strength.
            if (text.length() < 8) {
                String choice = twoChoice("Password less than 8 characters.\nDo you want to continue (Y/N)?", "Y", "N");
                if (choice.equals("N")) {
                    continue;
                } else if (choice.equals("Y")) {
                    return text;

                }
            }
            return text;
        }
    }

    /**
     * Prompts the user for a password specifically for login attempts and
     * validates that it's not empty. This method is simpler than a password
     * validation for registration, as it only ensures that some input is
     * provided. It repeatedly asks for input until a non-empty string is
     * entered.
     *
     * @param message The prompt message to display to the user (e.g., "Enter
     * your password: ").
     * @return A non-empty password string, trimmed of leading/trailing
     * whitespace, suitable for use in a login attempt.
     */
    public static String validPasswordForLogin(String message) {
        String text = "";
        // Starts an infinite `while` loop that continues until valid input is received and returned.
        while (true) {
            System.out.printf(message);
            text = sc.nextLine().trim();
            if (text == null || text.isEmpty()) {
                System.out.println("**********************");
                System.out.println("Input must not be empty!");
                System.out.println("**********************");
                continue;
            }
            return text;
        }
    }

    /**
     * Prompts the user for a name and validates that the input is not empty.
     * This method is a general-purpose input validator for fields that simply
     * require some text to be entered. It repeatedly asks for input until a
     * non-empty string is provided.
     *
     * @param message The prompt message to display to the user (e.g., "Enter
     * your full name: ").
     * @return A non-empty string representing the validated name, including any
     * leading/trailing whitespace and special characters, as it does not
     * perform trimming or specific character validation.
     */
    public static String validName(String message) {
        String text = "";
        // Starts an infinite `while` loop that continues until valid input is received and returned.
        while (true) {
            System.out.printf(message);
            text = sc.nextLine();
            if (text == null || text.isEmpty()) {
                System.out.println("**********************");
                System.out.println("Input must not be empty!");
                System.out.println("**********************");
                continue;
            }
            return text;
        }
    }

    /**
     * Prompts the user for a phone number and validates its format. This method
     * ensures the phone number consists only of digits and has a length of
     * either 10 or 11 characters. It repeatedly asks for input until a valid
     * phone number is provided.
     *
     * @param message The prompt message to display to the user (e.g., "Enter
     * phone number: ").
     * @return A validated phone number string, consisting only of digits and
     * trimmed of whitespace.
     */
    public static String validPhone(String message) {
        String phone = "";
        boolean condition = true;
        // The `do-while` loop ensures the prompt is shown at least once and continues
        // until valid input is received (`condition` remains true after a successful attempt).
        do {
            condition = true;
            // The `try-catch` block handles potential `IllegalArgumentException` thrown during validation.
            try {
                System.out.printf(message);
                Pattern pattern = Pattern.compile("[+0]\\d+");
                phone = sc.nextLine().trim();
                Matcher matcher = pattern.matcher(phone);
                if (!matcher.matches()) {
                    throw new IllegalArgumentException("Invalid phone number!");
                }
                // Checks if the phone number length is less than 1. Although the regex `\\d+` implies at least one digit,
                // this check specifically ensures it's not empty, which `trim()` handles but redundancy can be useful.
                if (phone.length() < 1) {
                    throw new IllegalArgumentException("Invalid phone number!");
                }
                // Checks if the phone number's length is outside the acceptable range (10 to 11 digits).
                // If the length is not between 10 and 11, an `IllegalArgumentException` is thrown.
                if (phone.length() < 10 || phone.length() > 11) {
                    throw new IllegalArgumentException("Phone number must be 10 or 11 number!");
                }
            }// The `catch` block for `IllegalArgumentException` handles any validation errors.
            catch (IllegalArgumentException e) {
                System.out.println("**********************");
                System.out.printf("%s\n", e.getMessage());
                System.out.println("**********************");
                condition = false;
            }
        } while (!condition);// The loop continues as long as `condition` is `false` (meaning validation failed).
        return phone;
    }

    /**
     * Prompts the user for an email address and validates its format. This
     * method ensures the input is not empty and generally conforms to a basic
     * email pattern. It repeatedly asks for input until a valid email format is
     * provided.
     *
     * @param message The prompt message to display to the user (e.g., "Enter
     * your email: ").
     * @return A validated email string, trimmed of leading/trailing whitespace.
     */
    public static String validEmail(String message) {
        String text = "";
        Pattern pattern = Pattern.compile("\\w{1,}([.]\\w{1,})*@\\w{1,}.\\w{1,}([.]\\w{1,})*");
        // Starts an infinite `while` loop that continues until valid input is received and returned.
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
                System.out.printf("Invalid email!\nvalid email: (XXXX@XXX.XXX)\nex: abc@gmail.com\n");
                System.out.println("**********************");
            }
        }
    }

    /**
     * Prompts the user for an address and validates its format. This method
     * ensures the input is not empty, converts it to title case, and then
     * validates it against a specific address pattern. It repeatedly asks for
     * input until a valid address format is provided.
     *
     * @param message The prompt message to display to the user (e.g., "Enter
     * your address: ").
     * @return A formatted and validated address string, with each word
     * capitalized and trimmed of leading/trailing whitespace.
     */
    public static String validAddress(String message) {
        String text = "";
        Pattern pattern = Pattern.compile("(\\w{1,} )*(\\w{1,} )*(\\w{1,})*(\\w{2,}\\s\\w{2,})(\\s\\w{1,})*(/(\\w{2,}\\s\\w{2,})(\\s\\w{1,})*)*");
        // Starts an infinite `while` loop that continues until valid input is received and returned.
        while (true) {
            System.out.printf(message);
            text = sc.nextLine().trim().toLowerCase();
            if (text == null || text.isEmpty()) {
                System.out.println("**********************");
                System.out.println("Input must not be empty!");
                System.out.println("**********************");
                continue;
            }
            // Splits the input `text` into an array of strings (`temp`) based on one or more whitespace characters.
            // This prepares individual words for capitalization.
            String[] temp = text.split("\\s+");
            int i = 0;
            // Iterate through each string (`e`) in the `temp` array.
            // This 'for-each' loop processes each word.
            for (String e : temp) {
                temp[i] = firstUpcase(temp[i]);
                i += 1;
            }
            text = String.join(" ", temp);
            Matcher matcher = pattern.matcher(text);
            if (matcher.matches()) {
                return text;
            } else {
                System.out.println("**********************");
                System.out.printf("Invalid input! ex: (Tinh Bien/An Giang) or (Ha Noi)\n"
                        + "Address must not abbreviated\n");
                System.out.println("**********************");
            }
        }
    }

    /**
     * Prompts the user for a date of birth (DOB) and validates its format and
     * age range. This method ensures the DOB is in "dd/MM/yyyy" format and
     * represents an age between 13 and 120 years old (inclusive). It repeatedly
     * asks for input until a valid DOB is provided.
     *
     * @param message The prompt message to display to the user (e.g., "Enter
     * your date of birth (dd/MM/yyyy): ").
     * @return A validated and formatted date of birth string in "dd/MM/yyyy"
     * format, with day and month components zero-padded if necessary.
     */
    public static Date validDob(String message) {
        String dob = "";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        int now = LocalDate.now().getYear();
        formatter.setLenient(false);
        // Starts an infinite `while` loop that continues until valid input is received and returned.
        while (true) {
            // The `try-catch` block handles potential `ParseException` that occurs if the date string
            // cannot be parsed according to the specified format or if it represents an invalid date.
            try {
                System.out.print(message);
                dob = sc.nextLine().trim();
                if (dob.isEmpty() || dob == null) {
                    System.out.println("**********************");
                    System.out.println("DOB must not be empty or null!");
                    System.out.println("**********************");
                    continue;
                }
                // Splits the `dob` string by one or more "/" characters.
                // This separates day, month, and year into `temp` array elements.
                String[] temp = dob.split("[/]+");
                // Loop through the day and month parts (indices 0 and 1) of the `temp` array.
                // This 'for' loop ensures that single-digit day or month values are padded with a leading '0'.
                // For example, "1/1/2000" becomes "01/01/2000".
                for (int i = 0; i < temp.length; i++) {
                    if (temp[i].length() < 2) {
                        temp[i] = "0" + temp[i].trim();
                    }
                }
                dob = String.join("/", temp);
                // Attempt to parse the formatted `dob` string into a `Date` object.
                // If `formatter.setLenient(false)` is active, this will throw a `ParseException`
                // for invalid dates (e.g., "31/02/2000").
                Date date = formatter.parse(dob);
                // Validates the year component to ensure the age is within a reasonable range (13-120 years).
                // `Integer.parseInt(temp[2].trim())` extracts and converts the year part to an integer.
                // The 'if' condition checks if the calculated age falls outside the acceptable range.
                if (now - Integer.parseInt(temp[2].trim()) < 13 || now - Integer.parseInt(temp[2].trim()) > 120) {
                    System.out.println("**********************");
                    System.out.println("Invalid year: The age must be between 13 and 120 years old!");
                    System.out.println("**********************");
                    continue;
                }
                return date;
            } catch (ParseException e) {
                System.out.println("**********************");
                System.out.println("Invalid input! ex: (01/10/2005)");
                System.out.println("**********************");
            }
        }
    }

    /**
     * Converts the first character of a given string to uppercase, leaving the
     * rest of the string as is.
     *
     * @param text The input string whose first character needs to be
     * capitalized.
     * @return A new string with its first character converted to uppercase. If
     * the input string is empty, the behavior of `text.charAt(0)` would lead to
     * an `IndexOutOfBoundsException`. It's advisable to add a check for an
     * empty or null string for robustness.
     */
    public static String firstUpcase(String text) {
        Character.toUpperCase(text.charAt(0));
        return Character.toUpperCase(text.charAt(0)) + text.substring(1);
    }

    /**
     * Prompts the user with a message and offers two predefined
     * single-character choices (e.g., 'Y' or 'N'). It repeatedly asks for input
     * until one of the valid choices is entered (case-insensitively).
     *
     * @param message The prompt message to display to the user, typically
     * asking a yes/no question.
     * @param A The first valid single-character choice (e.g., "Y").
     * @param B The second valid single-character choice (e.g., "N").
     * @return The user's valid choice, matching the case of the provided 'A' or
     * 'B' parameter. Returns an empty string if an unexpected error occurs
     * (e.g., due to an IllegalArgumentException, though the current catch block
     * only prints an error and doesn't explicitly stop execution).
     */
    public static String twoChoice(String message, String A, String B) {
        // The `try-catch` block handles a potential `IllegalArgumentException` thrown if the input length is not 1.
        try {
            // Starts an infinite `while` loop that continues until valid input is received and returned.
            while (true) {
                System.out.printf(message);
                String choice = sc.nextLine().trim();
                // This `if` statement checks if the length of the `choice` string is not exactly 1.
                // If it's not a single character, it's considered invalid.
                if (choice.length() != 1) {
                    throw new IllegalArgumentException("The input must be one alphabet");
                }
                if (choice.equalsIgnoreCase(A)) {
                    return A;
                } else if (choice.equalsIgnoreCase(B)) {
                    return B;
                } else {
                    System.out.printf("Please (%s) or (%s)", A, B);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("**********************");
            System.out.printf("%s\n", e.getMessage());
            System.out.println("**********************");
        }
        return "";
    }
}
