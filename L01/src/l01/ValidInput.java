/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * L01-Create a Java console program to manage students.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-05-21
 */
public class ValidInput {

    static Scanner sc = new Scanner(System.in);

    /**
     * Prompts the user for a string input and validates that it contains only
     * alphabetic characters (A-Z, a-z) and spaces. It repeatedly prompts until
     * valid input is received.
     *
     * @param message The prompt message to display to the user.
     * @return A validated string containing only letters and spaces.
     */
    public static String getAlphabet(String message) {
        String text = "";
        // Compiles a regular expression pattern.
        // '[A-Za-z ]+':
        //   - '[A-Za-z ]': Matches any uppercase letter (A-Z), any lowercase letter (a-z), or a space.
        //   - '+': Matches one or more occurrences of the characters defined in the character set.
        // This pattern ensures the input consists only of letters and spaces.
        Pattern pattern = Pattern.compile("[A-Za-z ]+");
        // Starts an infinite while loop that continues until valid input is received.
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
     * Prompts the user for an integer input and validates that it is a positive
     * number (greater than 0). It repeatedly prompts until valid input is
     * received.
     *
     * @param message The prompt message to display to the user.
     * @return A validated positive integer.
     */
    public static int getPositiveNumber(String message) {
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
                    throw new IllegalArgumentException("A number must be a positive integer");
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
     * Prompts the user for an integer input and validates that it is a positive
     * number (greater than 0) and less than or equal to a specified maximum
     * value. It repeatedly prompts until valid input is received.
     *
     * @param message The prompt message to display to the user.
     * @param max The maximum allowed value for the input number (inclusive).
     * @return A validated positive integer within the specified limit.
     */
    public static int getPositiveNumberWithLimit(String message, int max) {
        int n = 0;
        boolean condition = true;
        // Starts a do-while loop. The code inside 'do' block executes at least once.
        do {
            condition = true;
            // Starts a try-catch block to handle potential input errors.
            try {
                System.out.printf(message);
                n = Integer.parseInt(sc.nextLine().trim());
                if (n < 1 || n > max) {//get value in interval [1, max + 1]
                    String error = String.format("Input must be form 1 to %d!", max);
                    throw new IllegalArgumentException(error);
                }
                // Catches a NumberFormatException, which occurs if the input string cannot be parsed into an integer.
            } catch (NumberFormatException e) {
                System.out.println("**********************");
                System.out.printf("Input must be form 1 to %d!\n", max);
                System.out.println("**********************");
                condition = false;
                // Catches an IllegalArgumentException, which is thrown if 'n' is outside the [1, max] range.
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
     * Prompts the user for an integer ID and validates it. The ID must be a
     * positive integer. Additionally, it checks if the entered ID is already in
     * use by a student in the provided list. If it is, it prompts the user
     * whether they want to continue with a different ID or exit the input
     * process. It repeatedly prompts until a valid and (optionally) unique ID
     * is provided.
     *
     * @param listStudent An ArrayList of Student objects to check for existing
     * IDs.
     * @param message The prompt message to display to the user.
     * @return A validated integer ID.
     */
    public static int getId(ArrayList<Student> listStudent, String message) {
        int n = 0;
        boolean condition = true;
        // Starts a do-while loop. The code inside 'do' block executes at least once.
        do {
            condition = true;
            // Starts a try-catch block to handle potential input format errors.
            try {
                System.out.printf(message);
                String num = sc.nextLine().trim();
                if (num.length() > 7) {
                    throw new IllegalArgumentException("The input must be 1 to 1 000 000");
                }
                n = Integer.parseInt(num);
                if (n < 1) {
                    throw new IllegalArgumentException("A number must be a positive integer");
                }
                // Catches a NumberFormatException if the input string cannot be converted to an integer.
            } catch (NumberFormatException e) {
                System.out.println("**********************");
                System.out.println("Id must be a number!");
                System.out.println("**********************");
                condition = false;
                // Catches an IllegalArgumentException thrown by the checks for length or positivity.
            } catch (IllegalArgumentException e) {
                System.out.println("**********************");
                System.out.printf("%s\n", e.getMessage());
                System.out.println("**********************");
                condition = false;
            }
            String nameUsed = "";
            // Iterates through each student in the provided 'listStudent' to check for ID duplication.
            for (Student e : listStudent) {
                if (e.getId() == n) {
                    nameUsed = e.getStudentName();
                }
            }
            // Checks if 'nameUsed' is not empty, meaning the entered ID is already in use.
            if (!nameUsed.isEmpty()) {
                System.out.printf("Id (%d) is used by %s!\n", n, nameUsed);
                String choice = getOneInTwoChoice("Do you want to continue(Y/N)?\n", "Y", "N");
                if (choice.equalsIgnoreCase("Y")) {
                } else if (choice.equalsIgnoreCase("N")) {
                    condition = false;
                }
            }
            // The loop continues as long as 'condition' is false (meaning an invalid input or user chose to retry).
        } while (!condition);
        return n;
    }

    /**
     * This method serves two purposes: 1. If a student with the given `getId`
     * exists in `listStudent`, it returns that student's getName. 2. If no
     * student with the given `getId` is found, it then prompts the user for a
     * new student getName, validates it (must contain only letters and spaces,
     * and be at least 2 characters long), and formats it (capitalizing the
     * first letter of each word) before returning. It repeatedly prompts for
     * input until a valid getName is provided.
     *
     * @param listStudent An ArrayList of Student objects to search through.
     * @param message The prompt message to display to the user for entering a
     * getName.
     * @param id The ID to search for within the `listStudent`.
     * @return The getName of the student found by ID, or a newly validated and
     * formatted getName if no student is found by ID.
     */
    public static String getName(ArrayList<Student> listStudent, String message, int id) {
        String name = "";
        // First, check if a student with the given 'getId' already exists in 'listStudent'.
        // This suggests an update scenario where the user might not want to change the getName.
        for (Student e : listStudent) {
            if (e.getId() == id) {
                return e.getStudentName();
            }
        }
        Pattern pattern = Pattern.compile("[A-Za-z ]+");
        // Starts an infinite while loop that continues until valid getName input is received.
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
            // Iterates through each word in the 'temp' array (individual words from the input getName).
            for (String e : temp) {
                name += upcaseFirstetter(e);
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
     * Prompts the user to select a course from a predefined list of options. It
     * displays a menu of available courses with corresponding numbers and then
     * validates the user's choice to ensure it's a valid option.
     *
     * @param message The prompt message to display to the user (e.g., "Please
     * choice a course: ").
     * @return The getName of the selected course as a String.
     */
    public static HashMap<String, Integer> getCourseName(String message) {
        String text = "";
        int i = 1;
        HashMap<Integer, String> course = new HashMap<>();
        HashMap<String, Integer> courseOfStudent = new HashMap<>();
        // Starts an infinite while loop that continues until the user chooses to exit after selecting at least one course.
        while (true) {
            course.put(1, "Java");
            course.put(2, "C/C++");
            course.put(3, ".Net");
            course.put(4, "Exit");
            System.out.printf("+-----+----------------+\n"
                    + "| No. |    Option      |\n"
                    + "+-----+----------------+\n");
            // Iterates through the 'course' HashMap to display the menu options to the user.
            for (Map.Entry<Integer, String> e : course.entrySet()) {
                System.out.printf("| %3d | %14s |\n",
                        e.getKey(),
                        e.getValue()
                );
            }
            System.out.printf("+-----+----------------+\n");
            int choice = getPositiveNumberWithLimit("Please choice a option: ", 4);
            // Checks if it's not the first course selection attempt (i > 1) AND the user chose 'Exit' (choice == 4).
            if (i > 1 && choice == 4) {
                break;
            } else if (i == 1 && choice == 4) {
                System.out.println("**********************");
                System.out.println("no subjects selected!");
                System.out.println("**********************");
                continue;
            }
            int numberOfCourse = ValidInput.getPositiveNumber("Please enter number of course: ");
            courseOfStudent.put(course.get(choice), numberOfCourse);
            i++;
        }
        return courseOfStudent;
    }

    /**
     * Converts the first character of a given string to uppercase, leaving the
     * rest of the string unchanged. This method is useful for formatting names
     * or other strings where initial capitalization is desired.
     *
     * @param text The input string to capitalize.
     * @return A new string with its first character capitalized. If the input
     * string is empty, calling `text.charAt(0)` would result in an
     * `IndexOutOfBoundsException`. It is recommended to add a check for empty
     * or null strings for robustness in a production environment.
     */
    public static String upcaseFirstetter(String text) {
        Character.toUpperCase(text.charAt(0));
        return Character.toUpperCase(text.charAt(0)) + text.substring(1);
    }

    /**
     * Prompts the user for a choice between two single-character options (e.g.,
     * 'Y' or 'N'). It validates the input to ensure it's a single character and
     * matches one of the two specified options (case-insensitive). It
     * repeatedly prompts until valid input is received.
     *
     * @param message The prompt message to display to the user.
     * @param A The first valid single-character option (e.g., "Y").
     * @param B The second valid single-character option (e.g., "N").
     * @return The chosen option (either A or B, in its original case as passed
     * to the method).
     */
    public static String getOneInTwoChoice(String message, String A, String B) {
        // Starts a try-catch block to handle potential IllegalArgumentException.
        // In this specific structure, the catch block is reached only if an
        // IllegalArgumentException is thrown *inside* the while loop and is not handled by returning.
        try {
            // Starts an infinite while loop that continues until valid input is received.
            while (true) {
                System.out.printf(message);
                String choice = sc.nextLine().trim();
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
            // Catches an IllegalArgumentException.
            // This catch block will only be executed if 'throw new IllegalArgumentException(...)' inside
            // the `if (choice.length() != 1)` condition is triggered.
        } catch (IllegalArgumentException e) {
            System.out.println("**********************");
            System.out.printf("%s\n", e.getMessage());
            System.out.println("**********************");
        }
        return "";
    }

    /**
     * Prompts the user to enter a getSemester number and validates it to be a
     * positive integer within a predefined limit (up to 3, assuming there are 3
     * semesters). This method reuses the `getPositiveNumberWithLimit` utility
     * for input validation.
     *
     * @param message The prompt message to display to the user (e.g., "Enter
     * getSemester: ").
     * @return A validated integer representing the getSemester number.
     */
    public static int getSemester(String message) {
        int n = 0;
        n = getPositiveNumberWithLimit(message, 3);
        return n;
    }

}
