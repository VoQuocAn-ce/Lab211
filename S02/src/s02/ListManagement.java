/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s02;

/**
 * S02-String Array Manipulations.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-01
 */
public class ListManagement {

    private int n;
    private String[] listName;

    /**
     * The `ListManagement` class is responsible for initializing and managing a
     * list of names.
     *
     * This constructor performs the following actions: 1. Prompts the user to
     * enter a positive integer 'n' (representing the number of elements in the
     * list). This 'n' value has a maximum limit of 50. 2. Initializes a
     * two-dimensional `listName` array to store the names. This array will have
     * 'n' rows and 20 columns, where: - 'n' is the number of names to be
     * managed. - 20 is the assumed maximum length for each name string (or
     * storage unit, depending on future use).
     */
    public ListManagement() {
        n = ValidInput.positiveNumberWithLimit("Enter the value of n:\n", 50);
        listName = new String[n];
    }

    /**
     * This method is responsible for adding new entries (names) to the list. It
     * iterates through the 'n' available slots in the `listName` array and
     * populates the first column of each row with an alphabetical string.
     *
     * It's assumed that 'n' has been previously initialized, and `listName` is
     * a two-dimensional array where `listName[i][0]` is intended to store the
     * primary name for each entry.
     */
    public void addNew() {
        for (int i = 0; i < n; i++) {
            listName[i] = ValidInput.alphabet();
        }
    }

    /**
     * This method is responsible for printing out the names currently stored in
     * the list. It iterates through each entry and displays them to the
     * console, prefixed with a sequential number (1-based index) for better
     * readability.
     *
     * It's assumed that 'n' has been initialized and `listName` is a
     * two-dimensional array where `listName[i][0]` contains the name to be
     * printed.
     */
    public void printOut() {
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + ". " + listName[i]);
        }
    }

    /**
     * Swaps two names in the list based on their given indices. This method
     * facilitates reordering elements within the `listName` array.
     *
     * @param i The index of the first name to be swapped.
     * @param j The index of the second name to be swapped. It's assumed that
     * both 'i' and 'j' are valid indices within the bounds of the list.
     */
    public void swap(int i, int j) {
        String temp = listName[i];
        listName[i] = listName[j];
        listName[j] = temp;
    }

    /**
     * Sorts the names in the `listName` array using the Bubble Sort algorithm.
     * This method arranges the names in alphabetical order (case-insensitive).
     * It includes an optimization to stop early if no swaps occur in an inner
     * pass, indicating that the list is already sorted.
     */
    public void bubbleSort() {
        for (int i = 0; i < n - 1; i++) {
            boolean swaped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (listName[j].compareToIgnoreCase(listName[j + 1]) > 0) {
                    swap(j, j + 1);
                    swaped = true;
                }
            }
            if (swaped == false) {
                break;
            }
        }
    }
}
