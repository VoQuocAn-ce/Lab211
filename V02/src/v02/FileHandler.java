/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * V02 - Subsystem for listing and searching files by content.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-15
 */
public class FileHandler {

    /**
     * Checks if a file at the given path is empty.
     *
     * @param path The path to the file.
     * @return True if the file exists and its length is 0 bytes, false
     * otherwise.
     */
    public boolean checkEmpty(String path) {
        File file = new File(path);
        // Checks if the file's length is 0 bytes.
        if (file.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Imports data from a CSV file specified by the path into an ArrayList of
     * CSV objects. Each line in the CSV file is expected to represent a CSV
     * object's data (id, name, email, phone, address) separated by commas.
     *
     * @param path The path to the CSV file to be imported.
     * @return An ArrayList of CSV objects populated with data from the file.
     * Returns an empty list if read fails.
     */
    public ArrayList<CSV> importCSV(String path) {
        ArrayList<CSV> csvList = new ArrayList<>();
        // Start of try-catch block to handle potential IOException during file reading.
        try {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(path));
            // Loop indefinitely until an explicit break statement is encountered.
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] temp = line.split(",");
                String id = temp[0];
                String name = temp[1];
                String email = temp[2];
                String phone = temp[3];
                String address = temp[4];
                CSV newCSV = new CSV(id, name, email, phone, address);
                csvList.add(newCSV);
            }
            // Catch block for IOException, which might occur during file reading.
        } catch (IOException e) {
            System.out.println("read fail");
        }
        System.out.println("Import: Done");
        return csvList;
    }

    /**
     * Exports an ArrayList of CSV objects to a specified file. The data from
     * each CSV object is formatted into a comma-separated line and written to
     * the file. If the file exists, its content will be overwritten.
     *
     * @param csvList The ArrayList of CSV objects to be exported.
     * @param path The path to the file where the data will be exported.
     */
    public void exportCSV(ArrayList<CSV> csvList, String path) {
        String line = "";
        // Iterates through each CSV object in the provided ArrayList.
        for (CSV e : csvList) {
            line += e.entryData();
        }
        // Start of try-catch block to handle potential IOException during file writing.
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path, false));
            bw.write(line);
            bw.close();
            // Catch block for IOException, which might occur during file writing.
        } catch (IOException e) {
            System.out.println("Write fail");
        }
    }

}
