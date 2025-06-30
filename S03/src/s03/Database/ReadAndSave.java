/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s03.Database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * S03-English – English dictionary
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-01
 */
public class ReadAndSave {

    /**
     * Reads all lines from a specified file and returns them as an ArrayList of
     * strings. This method is designed for reading text-based data, such as
     * vocabulary words or meanings, line by line. It includes basic error
     * handling for file reading issues.
     *
     * @param fileName The path or name of the file to be read.
     * @return An ArrayList of strings, where each string represents a line read
     * from the file. Returns an empty ArrayList if the file cannot be read or
     * is empty.
     */
    public ArrayList<String> readFile(String fileName) {
        ArrayList<String> list = new ArrayList<>();
        // Use a try-catch block to handle potential IOException that might occur during file input/output operations.
        try {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            // Enter a 'while (true)' loop to continuously read lines until the end of the file is reached.
            while (true) {
                line = br.readLine();
                // Check if the `line` read is null.
                // If it is, it means there are no more lines to read in the file.
                if (line == null) {
                    break;
                }
                list.add(line);
            }
            br.close();
            // Catch block specifically for IOException. This handles errors like the file not being found,
            // permission issues, or other problems during file reading.
        } catch (IOException e) {
            System.out.println("Read file error!");
        }
        return list;
    }

    /**
     * Writes the contents of an ArrayList of strings to a specified file. Each
     * string in the list is written on a new line in the file. This method is
     * typically used to persist data, such as vocabulary words or meanings, to
     * a file.
     *
     * @param list The ArrayList of strings to be written to the file.
     * @param fileName The path or name of the file to which the data will be
     * written.
     */
    public void writeFile(ArrayList<String> list, String fileName) {
        String line = "";
        // Iterate through each String element 'e' in the provided 'list'.
        // This 'for-each' loop efficiently processes every item in the ArrayList.
        for (String e : list) {
            line += e + "\n";
        }
        // Use a try-catch block to handle potential IOException that might occur during file output operations.
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write(line);
            bw.close();
        } // Catch block specifically for IOException. This handles errors like permission issues,
        // disk full, or other problems during file writing.
        catch (IOException e) {
            System.out.println("");
        }
    }

    /**
     * Clears the content of a specified file, effectively making it empty. This
     * method achieves this by opening the file in overwrite mode and writing an
     * empty string to it.
     *
     * @param fileName The path or name of the file whose content is to be
     * cleared.
     */
    public void clear(String fileName) {
        String line = "";
        // Use a try-catch block to handle potential IOException that might occur during file output operations.
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false));
            bw.write(line);
            bw.close();
        } // Catch block specifically for IOException. This handles errors like the file not being found,
        // permission issues, disk full, or other problems during file writing.
        catch (IOException e) {
            System.out.println("");
        }
    }

}
