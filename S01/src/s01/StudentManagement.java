/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s01;

import java.util.ArrayList;

/**
 * S01-Manage student
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-05-17
 */
public class StudentManagement {

    SaveAndLoad sar = new SaveAndLoad();
    private ArrayList<Student> listStudent;

    /**
     * Constructor for the StudentManagement class. When a StudentManagement
     * object is created, it automatically attempts to load existing student
     * data from a file.
     */
    public StudentManagement() {
        listStudent = sar.readFile();
    }

    /**
     * Retrieves the current list of students managed by this StudentManagement
     * instance.
     *
     * @return An ArrayList of Student objects, representing the current student
     * list.
     */
    public ArrayList<Student> getListStudent() {
        return listStudent;
    }

    /**
     * Prints the details of all students currently in the managed list to the
     * console. Each student's information is displayed, separated by a dashed
     * line.
     */
    public void printOut() {
        System.out.println("---------------------");
        for (Student e : listStudent) {
            System.out.print(e.toString());
            System.out.println("--------------------");
        }
    }

    /**
     * Sorts the list of students in ascending alphabetical order by their
     * names. This method uses a Bubble Sort algorithm.
     */
    public void sortList() {
        for (int i = 0; i < listStudent.size(); i++) {
            for (int j = 0; j < listStudent.size() - 1; j++) {
                if (listStudent.get(j).getName().compareToIgnoreCase(listStudent.get(j + 1).getName()) > 0) {
                    swap(j, j + 1);
                }
            }
        }
    }

    /**
     * Swaps two Student objects within the 'listStudent' ArrayList at specified
     * indices. This is a helper method typically used by sorting algorithms.
     *
     * @param i The index of the first student to be swapped.
     * @param j The index of the second student to be swapped.
     */
    public void swap(int i, int j) {
        Student temp = listStudent.get(i);
        listStudent.set(i, listStudent.get(j));
        listStudent.set(j, temp);
    }

    /**
     * Allows the user to search for students by name. It prompts the user to
     * enter a student's name and then displays details for all students whose
     * names contain the entered search string.
     */
    public void lookUpStudent() {
        String nameStudent = ValidInput.name("Please enter student name: ");
        for (Student e : listStudent) {
            String[] temp = e.getName().split(" ");
            for (String o : temp) {
                if (nameStudent.equals(o)) {
                    System.out.print(e.toString());
                    System.out.println("------------------------");
                }
            }
        }
    }

    /**
     * Saves the current state of the student list to a file. This method
     * delegates the actual file saving operation to a separate data access
     * object (DAO) or utility class.
     */
    public void saveNew() {
        sar.saveFile(listStudent);
    }

}
