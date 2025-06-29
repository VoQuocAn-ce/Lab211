/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * L01-Create a Java console program to manage students.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-05-21
 */
public class StudentManagement {

    LinkedHashMap<String, HashMap<String, Integer>> reportList = new LinkedHashMap<>();
    Scanner sc = new Scanner(System.in);
    private ArrayList<Student> listStudent = new ArrayList<>();

    /**
     * Retrieves the current list of students managed by this StudentManagement
     * instance. This method provides access to the internal `ArrayList` that
     * stores all `Student` objects.
     *
     * @return An `ArrayList` of `Student` objects, representing the current
     * student list.
     */
    public ArrayList<Student> getListStudent() {
        return listStudent;
    }

    /**
     * Sets the entire list of students for this StudentManagement instance.
     * This method replaces the current internal student list with the provided
     * `ArrayList`. It can be used to load a new set of students or completely
     * overwrite the existing data.
     *
     * @param listStudent The new `ArrayList` of `Student` objects to be set as
     * the managed list.
     */
    public void setListStudent(ArrayList<Student> listStudent) {
        this.listStudent = listStudent;
    }

    /**
     * Facilitates the creation of new student records and their associated
     * course enrollments. This method guides the user through inputting student
     * details, validates the input, adds the new student to the main student
     * list, and updates the course report. It also offers to sort the student
     * list if a certain number of students have been added.
     */
    public void create() {
        boolean condition = true;
        // Starts a while loop to allow creating multiple student records.
        while (condition) {
            int id = ValidInput.getId(listStudent, "id: ");
            String studentName = ValidInput.getName(listStudent, "Student name: ", id);
            int semester = ValidInput.getSemester("Semester: ");
            HashMap<String, Integer> courseName = ValidInput.getCourseName("Course name: ");
            Student newStudent = new Student(id, studentName, semester, courseName);
            listStudent.add(newStudent);
            // If the number of students in the main list is greater than 2,
            // prompts the user if they want to sort the list immediately.
            // This 'if' condition likely implies a threshold for when sorting becomes meaningful or efficient.
            if (listStudent.size() > 2) {
                String choice = ValidInput.getOneInTwoChoice("Do you want to order now (Y/N)?\n", "Y", "N");
                if (choice.equals("Y")) {
                    sortListStudent();
                    condition = false;
                } else if (choice.equals("N")) {
                    condition = true;
                }
            }
        }
    }

    /**
     * Reviews the list of students and generates a report of their courses.
     * This method iterates through the `listStudent`, aggregates course
     * information for each student, and stores it in the `reportList`. If a
     * student's name already exists in the `reportList`, their courses are
     * updated (presumably by combining or adding new courses); otherwise, the
     * student and their courses are added to the report.
     *
     * The `reportList` is cleared at the beginning of the method, meaning each
     * call will generate a fresh report based on the current `listStudent`.
     */
    public void reviewReport() {
        reportList.clear();
        // Iterates through each student in the list
        for (Student e : listStudent) {
            if (reportList.containsKey(e.getStudentName())) {
                addCourseNumber(e.getCourseName(), e.getStudentName());
            } else {
                reportList.put(e.getStudentName(), e.getCourseName());
            }
        }
    }

    /**
     * Adds or updates course numbers (e.g., scores, credits, or occurrences)
     * for a specific student within the `reportList`. This method iterates
     * through the courses provided in the `student` HashMap. For each course,
     * it retrieves the current count/value for that course for the given
     * `studentName` from the `reportList`. It then adds the incoming value for
     * that course to the current value and updates the `reportList`
     * accordingly. If a course does not exist for the student in the
     * `reportList`, it will be added with the incoming value. Null values for
     * course numbers (both current and incoming) are treated as 0.
     *
     * @param student A HashMap representing a collection of courses for a
     * student, where keys are course names (String) and values are the numbers
     * (Integer) to be added or combined (e.g., scores, credits, or count of
     * times taken).
     * @param studentName The name of the student for whom to update the course
     * numbers in the `reportList`.
     */
    public void addCourseNumber(HashMap<String, Integer> student, String studentName) {
        // Loop through each course entry in the 'student' HashMap (which likely represents new courses or updates)
        for (Map.Entry<String, Integer> e : student.entrySet()) {
            HashMap<String, Integer> current = this.reportList.get(studentName);
            Integer incoming = e.getValue();
            Integer currentNumber = current.get(e.getKey());

            if (currentNumber == null) {
                currentNumber = 0;
            }
            if (incoming == null) {
                incoming = 0;
            }

            this.reportList.get(studentName).put(e.getKey(), currentNumber + incoming);
        }
    }

    /**
     * Generates and displays a report summarizing student course enrollments.
     * The report shows each student-course combination and the total number of
     * times that specific student has enrolled in that specific course. The
     * data is presented in a formatted table.
     */
    public void report() {
        reviewReport();
        System.out.printf("+-----+----------------+----------+------------------+\n"
                + "| No. | Student name   | Course   | Total of course  |\n"
                + "+-----+----------------+----------+------------------+\n"
        );
        int count = 1;
        // Iterate through each student's entry in the 'reportList'.
        // 'reportList' is a Map where keys are student names (String) and values
        // are HashMaps representing their courses (Course Name -> Total Count).
        for (Map.Entry<String, HashMap<String, Integer>> e : reportList.entrySet()) {
            // For each student, iterate through their individual course entries.
            // 'e.getValue()' retrieves the HashMap of courses for the current student.
            // 'o' represents a single Map.Entry where the key is the course name (String)
            // and the value is the total count/occurrence for that course (Integer).
            for (Map.Entry<String, Integer> o : e.getValue().entrySet()) {
                // Check if the total count for the current course is not null.
                // This prevents printing rows for courses that might somehow have a null value.
                if (o.getValue() != null) {
                    System.out.printf("| %3d | %-14s | %-8s | %16d |\n",
                            count,
                            e.getKey(),
                            o.getKey(),
                            o.getValue()
                    );
                    count++;
                }
                System.out.printf("+-----+----------------+----------+------------------+\n");
            }
        }
    }

    /**
     * Sorts the `listStudent` (an `ArrayList` of `Student` objects) in place.
     * This method relies on the `Student` class implementing the `Comparable`
     * interface and defining its natural ordering (e.g., by student getName or
     * ID). If `Student` does not implement `Comparable`, or if a custom sort
     * order is needed, a `Comparator` would need to be provided to
     * `Collections.sort()`.
     */
    public void sortListStudent() {
        Collections.sort(listStudent);
    }

    /**
     * Sorts a given `ArrayList` of `Student` objects by student getName in
     * ascending order. This method uses a lambda expression to define a custom
     * `Comparator` on the fly, allowing sorting based on the `studentName`
     * property.
     *
     * @param listO The `ArrayList` of `Student` objects to be sorted. This list
     * will be sorted in place.
     */
    public void sortListStudent(ArrayList<Student> listO) {
        Collections.sort(listO);
    }

    /**
     * Allows searching for students by getName and displays the found students
     * in a sorted table. The search is case-insensitive and performs a partial
     * match (contains).
     */
    public void searchStudent() {
        ArrayList<Student> listStudentFound = new ArrayList<>();
        String name = ValidInput.getAlphabet("name: ");
        // First search pass: Try to find students whose getName contains the search term as a whole word (case-insensitive)
        // or an exact full getName match.
        // Iterates through each student in the main 'listStudent'.
        for (Student e : listStudent) {
            String[] temp = e.getStudentName().split("\\s+");
            // Iterates through each word in the student's getName.
            for (String o : temp) {
                // This 'if' condition checks for two primary search criteria:
                // 1. If any word in the student's getName (o) exactly matches the search 'getName' (case-insensitive)
                //    AND the student 'e' is not already in 'listStudentFound'.
                //    OR
                // 2. If the student's full getName (e.getStudentName()) exactly matches the search 'getName' (case-insensitive)
                //    AND the student 'e' is not already in 'listStudentFound'.
                if (o.equalsIgnoreCase(name) && !listStudentFound.contains(e)
                        || e.getStudentName().equalsIgnoreCase(name) && !listStudentFound.contains(e)) {
                    listStudentFound.add(e);
                    break;
                }
            }
        }
        // Second search pass: If no students were found in the first pass (exact word/full getName match),
        // then perform a broader search for students whose names contain the search term as a substring (case-insensitive).
        // This 'if' condition executes only if 'listStudentFound' is currently empty.
        if (listStudentFound.isEmpty()) {
            // Iterates through each student in the main 'listStudent' again.
            for (Student e : listStudent) {
                // Checks if the student's full getName (converted to lowercase) contains the search 'getName' (converted to lowercase)
                // AND the student 'e' is not already in 'listStudentFound'.
                if (e.getStudentName().toLowerCase().contains(name.toLowerCase())
                        && !listStudentFound.contains(e)) {
                    listStudentFound.add(e);
                }
            }
        }
        // Iterates through each student in the sorted list of found students.
        sortListStudent(listStudentFound);
        System.out.printf("+-----+----------------+----------+----------------------+\n"
                + "| Id. | Student name   | Semester |     Course name      |\n"
                + "+-----+----------------+----------+----------------------+\n");
        for (Student e : listStudentFound) {
            System.out.printf("| %3d | %-14s | %8d | %20s |\n",
                    e.getId(),
                    e.getStudentName(),
                    e.getSemester(),
                    e.printOutCourse()
            );
        }
        System.out.printf("+-----+----------------+----------+----------------------+\n");
    }

    /**
     * Facilitates the update of an existing student's enrollment information.
     * This method guides the user through selecting a student and a specific
     * enrollment record to modify, then updates the details and rebuilds the
     * report data.
     */
    public void updateInfor() {
        System.out.printf("+-----+----------------+\n"
                + "| ID. | Student name   |\n"
                + "+-----+----------------+\n");
        // Iterates through a map returned by 'deduplicate()' (assumed to provide unique student IDs and names).
        // This loop displays a summarized list of students for the user to choose from.
        for (Map.Entry<Integer, String> e : deduplicate().entrySet()) {
            System.out.printf("| %3d | %-14s |\n",
                    e.getKey(),
                    e.getValue()
            );
        }
        System.out.printf("+-----+----------------+\n");
        int idChoose = ValidInput.getPositiveNumber("Please enter id you want to update: ");
        System.out.printf("+-----+----------------+----------+----------------------+\n"
                + "| No. | Student name   | Semester | Course name          |\n"
                + "+-----+----------------+----------+----------------------+\n");
        int no = 1;
        int index = 0;
        ArrayList<Integer> indexList = new ArrayList<>();
        // Iterates through each student record in the main 'listStudent'.
        for (Student e : listStudent) {
            // Checks if the current student's ID matches the ID chosen by the user.
            if (e.getId() == idChoose) {
                System.out.printf("| %3d | %-14s | %8d | %20s |\n",
                        no,
                        e.getStudentName(),
                        e.getSemester(),
                        e.printOutCourse()
                );
                indexList.add(index);
                no += 1;
            }
            index += 1;
        }
        System.out.println("+-----+----------------+----------+----------------------+");
        int indexNeedToUpdae = ValidInput.getPositiveNumber("Please enter the item you want to update: ");
        int id = ValidInput.getId(listStudent, "id: ");
        String studentName = ValidInput.getName(listStudent, "Student name: ", id);
        int semester = ValidInput.getSemester("Semester: ");
        HashMap<String, Integer> courseName = ValidInput.getCourseName("Course name: ");
        listStudent.get(indexList.get(indexNeedToUpdae - 1)).updateStudent(id, studentName, semester, courseName);
        sortListStudent();
    }

    /**
     * Facilitates the deletion of student records. This method allows the user
     * to select a student by ID and then removes all enrollment records
     * associated with that student from both the main student list and the
     * report list.
     */
    public void deleteStudent() {
        System.out.printf("+-----+----------------+\n"
                + "| ID. | Student name   |\n"
                + "+-----+----------------+\n");
        // Iterates through a map returned by 'deduplicate()' (assumed to provide unique student IDs and names).
        // This loop displays a summarized list of students for the user to choose from.
        for (Map.Entry<Integer, String> e : deduplicate().entrySet()) {
            System.out.printf("| %3d | %-14s |\n",
                    e.getKey(),
                    e.getValue()
            );
        }
        System.out.printf("+-----+----------------+\n");
        int idNeedToDelete = ValidInput.getPositiveNumber("Please enter id that you want to delete: ");
        listStudent.removeIf(a -> a.getId() == idNeedToDelete);
        sortListStudent();
    }

    /**
     * Creates a `HashMap` that contains unique student IDs as keys and their
     * corresponding student names as values. This effectively "deduplicates"
     * the student list by ID, providing a list of unique students (assuming
     * each ID maps to a single student getName). If a student ID appears
     * multiple times in `listStudent` (e.g., due to multiple course
     * enrollments), only the first encountered getName for that ID will be
     * stored in the map.
     *
     * @return A `HashMap<Integer, String>` where keys are unique student IDs
     * and values are their names.
     */
    public HashMap<Integer, String> deduplicate() {
        HashMap<Integer, String> mapStudent = new HashMap<>();
        // Iterates through each Student object in the 'listStudent'.
        for (Student e : listStudent) {
            // Checks if the 'mapStudent' already contains the current student's ID as a key.
            // This 'if' condition is crucial for deduplication.
            if (mapStudent.containsKey(e.getId())) {
                continue;
            } else {
                mapStudent.put(e.getId(), e.getStudentName());
            }
        }
        return mapStudent;
    }
}
