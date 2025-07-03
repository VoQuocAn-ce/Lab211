/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v03;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * V03 - File program handling
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-18
 */
public class FileHandler {

    /**
     * Calculates the size of a file in kilobytes (KB).
     *
     * @param path The absolute or relative path to the file.
     * @return The size of the file in kilobytes (KB). Returns 0.0 if the file
     * does not exist, is a directory, or an I/O error occurs.
     */
    public double sizeOfFile(String path) {
        File file = new File(path);
        long sizeByte = file.length();
        double sizeKB = (double) sizeByte / 1024;
        return sizeKB;
    }

    /**
     * Checks if a given path points to an existing file, a directory, or
     * neither.
     *
     * @param path The absolute or relative path to check.
     *
     */
    public void checkFileOrDirec(String path) {
        File file = new File(path);
        if (file.isFile()) {
            System.out.println("Path to file");
        } else if (file.isDirectory()) {
            System.out.println("Path to Directory");
        } else {
            System.out.println("Path dosen't exist");
        }
    }

    /**
     * Filters files within a specified directory based on their file extension.
     * This method takes a directory path and a desired file extension, then
     * returns an array of file names (Strings) that match the given extension.
     *
     * @param path The absolute or relative path to the directory to search
     * within.
     * @param extension The file extension to filter by (e.g., ".txt", ".java",
     * ".jpg"). It should include the leading dot.
     * @return An array of Strings, where each String is the name of a file in
     * the specified directory that ends with the given extension. Returns null
     * if the path does not denote a directory, or if an I/O error occurs.
     * Returns an empty array if no files match the criteria.
     */
    public String[] filterFilesByExtension(String path, String extension) {
        File folder = new File(path);
        FilenameFilter filterForFile = (dir, name) -> name.endsWith(extension);
        String[] fileList = folder.list(filterForFile);
        return fileList;
    }

    /**
     * Retrieves an array of files from a specified directory that have a size
     * greater than a given threshold.
     *
     * @param path The absolute or relative path to the directory to search
     * within.
     * @param size The minimum size (in kilobytes, KB) that a file must exceed
     * to be included in the returned array.
     * @return An array of `File` objects representing the files in the
     * specified directory whose size is greater than the given `size` (in KB).
     * Returns `null` if the `path` does not denote a directory, or if an I/O
     * error occurs. Returns an empty array if no files meet the size criteria.
     */
    public File[] getAllFile(String path, int size) {
        File folder = new File(path);
        // Create a FileFilter to define the criteria for accepting files.
        // The filter accepts files whose length (in bytes) is greater than
        // the specified 'size' converted from KB to bytes (size * 1024).
        File[] allFile = folder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.length()) > 1024 * size;
            }
        });
        return allFile;
    }

    /**
     * Appends new content to an existing file. This method takes a file path
     * and a string of text, then writes the text to the end of the specified
     * file, followed by a new line. If the file does not exist, it will be
     * created.
     *
     * @param path The absolute or relative path to the file to which content
     * will be added.
     * @param text The string content to be appended to the file.
     */
    public void addContentIntoFile(String path, String text) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
            bw.write(text);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println("Add fail!");
        }
        System.out.println("Write done");
    }

    /**
     * Counts the total number of words in a text file. A "word" is defined by
     * the Scanner's default delimiter, which is whitespace.
     *
     * @param path The absolute or relative path to the text file.
     * @return The total number of words found in the file. Returns 0 if the
     * file cannot be opened or read (e.g., file not found, permission issues),
     * in which case an error message will be printed to the console.
     */
    public int countWord(String path) {
        int countNumber = 0;
        try {
            Scanner sc = new Scanner(new File(path));
            // Loop while there are more words (tokens) to read in the file.
            while (sc.hasNext()) {
                sc.next();
                countNumber++;
            }
        } catch (IOException e) {
            System.out.println("Count fail!");
        }
        return countNumber;
    }

}
