/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * S01-Manage student
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-05-17
 */
public class SaveAndLoad {

    /**
     * Saves the provided list of students to a text file named "Student.txt".
     * Each student's data is converted into a string format suitable for file
     * storage.
     *
     * @param listStudent An ArrayList of Student objects to be saved to the
     * file.
     */
    public void saveFile(ArrayList<Student> listStudent) {
        try {
            String text = "";
            for (Student e : listStudent) {
                text += e.entryData();//convert from ArrayList to a string to write to file
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter("Student.txt"));
            bw.write(text);
            bw.close();
        } catch (IOException e) {
            System.err.println("File write fail!");
        }
    }

    /**
     * Reads student data from the "Student.txt" file and populates an ArrayList
     * of Student objects. Each line in the file is expected to represent a
     * student's data, with fields separated by commas.
     *
     * @return An ArrayList containing Student objects read from the file.
     * Returns an empty list if the file is empty, does not exist, or if an
     * error occurs during reading.
     */
    public ArrayList<Student> readFile() {
        ArrayList<Student> listStudent = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("Student.txt"));
            String line;
            while (true) {
                line = br.readLine();
                if (line == null) {//Check if the read line is null, if null then stop reading immediately
                    break;
                }
                String[] temp = line.split(",");
                if (temp[0].equals("Student")) {
                    String code = temp[1];
                    String name = temp[2];
                    String dateOfBirth = temp[3];
                    float learningPoint = Float.parseFloat(temp[4]);
                    Student student = new Student(code, name, dateOfBirth, learningPoint);
                    listStudent.add(student);
                }
            }
            br.close();
        } catch (IOException e) {
            System.err.println("File read fail!");
        }
        return listStudent;
    }

    /**
     * Clears the content of the "Student.txt" file. This effectively empties
     * the file, making it ready for new data or simulating a reset of the
     * student list stored in the file.
     */
    public void clear() {
        String text = "";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Student.txt", false));
            bw.write(text); //insert empty string to delete all data in file
            bw.close();
        } catch (IOException e) {
            System.err.println("File write fail!");
        }
    }
}
