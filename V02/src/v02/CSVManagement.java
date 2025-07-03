/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v02;

import java.util.ArrayList;

/**
 * V02 - Subsystem for listing and searching files by content.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-15
 */
public class CSVManagement {

    private String path;
    private ArrayList<CSV> CSVList = new ArrayList<>();
    private FileHandler fh = new FileHandler();

    /**
     * Default constructor for CSVManagement.
     */
    public CSVManagement() {
    }

    /**
     * Handles the import of a CSV file. It prompts the user for a file path,
     * clears any existing CSV data, and then uses the FileHandler to import the
     * CSV data into CSVList.
     *
     * @param message The message displayed to the user when prompting for the
     * file path.
     */
    public void importCSV(String message) {
        CSVList.clear();
        System.out.println("--------- Import CSV -------");
        path = ValidInput.getPath(message);
        CSVList = fh.importCSV(path);
    }

    /**
     * Formats the address field for all CSV entries in the loaded list. It
     * checks if a CSV has been imported and if the file is not empty before
     * proceeding.
     */
    public void formatAddress() {
        System.out.println("--------- Format Address");
        // Checks if a file path has been set (i.e., if a CSV has been imported).
        if (path == null || path.isEmpty()) {
            System.out.println("Program need import CSV");
            return;
        }
        // Checks if the imported CSV file is empty using the FileHandler.
        if (fh.checkEmpty(path)) {
            System.out.println("File is empty");
            return;
        }
        // Iterates through each CSV object in the CSVList.
        for (CSV e : CSVList) {
            e.formatAddress();
        }
        System.out.println("-------Format: Done");
    }

    /**
     * Formats the name field for all CSV entries in the loaded list. It checks
     * if a CSV has been imported and if the file is not empty before
     * proceeding.
     */
    public void formatName() {
        System.out.println("--------- Format Name");
        // Checks if a file path has been set (i.e., if a CSV has been imported).
        if (path == null || path.isEmpty()) {
            System.out.println("Program need import CSV");
            return;
        }
        // Checks if the imported CSV file is empty using the FileHandler.
        if (fh.checkEmpty(path)) {
            System.out.println("File is empty");
            return;
        }
        // Iterates through each CSV object in the CSVList.
        for (CSV e : CSVList) {
            e.formatName();
        }
        System.out.println("-------Format: Done");
    }

    /**
     * Exports the current CSV data (CSVList) to a new file specified by the
     * user. It checks if a CSV has been imported before proceeding.
     */
    public void exportCSV() {
        // Checks if a file path has been set (i.e., if a CSV has been imported).
        if (path == null || path.isEmpty()) {
            System.out.println("Program need import CSV");
            return;
        }
        String path = ValidInput.getPathForExport("Enter Path: ");
        fh.exportCSV(CSVList, path);
    }
}
