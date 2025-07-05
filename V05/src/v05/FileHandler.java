/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * V05 - Doctor management program.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-26
 */
public class FileHandler {

    /**
     * Saves the provided HashMap of doctor objects to a specified file path.
     * Each doctor's data is written to the file using their `entryData()`
     * method.
     *
     * @param doctorList A HashMap where keys are doctor IDs (or other unique
     * identifiers) and values are Doctor objects. This map contains the data to
     * be written to the file.
     * @param path The file path (String) where the doctor data will be saved.
     */
    public void saveToFile(HashMap<String, Doctor> doctorList, String path) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (Map.Entry<String, Doctor> e : doctorList.entrySet()) {
                bw.write(e.getValue().entryData());
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Writr fail!");
        }
    }

    /**
     * Reads doctor data from a specified file and populates a HashMap with
     * Doctor objects. The method expects each line in the file to represent a
     * doctor's data, with fields separated by commas, starting with "Doctor".
     *
     * @param path The file path (String) from which to read the doctor data.
     * @return A HashMap where keys are doctor IDs (String) and values are
     * Doctor objects. Returns an empty HashMap if the file cannot be read or no
     * valid doctor data is found.
     */
    public HashMap<String, Doctor> readFromFile(String path) {
        HashMap<String, Doctor> doctorList = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = "";
            // Loop indefinitely until read all data from file.
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] temp = line.split(",");
                if (!temp[0].equals("Doctor")) {
                    continue;
                }
                String id = temp[1];
                String name = temp[2];
                String specialization = temp[3];
                int availability = Integer.parseInt(temp[4]);
                Doctor newDoctor = new Doctor(id, name, specialization, availability);
                doctorList.put(id, newDoctor);
            }
        } catch (IOException e) {
            System.out.println("Read fail!");
        }
        return doctorList;
    }

}
