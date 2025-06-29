/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l01;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * L01-Create a Java console program to manage students.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-05-21
 */
public class Student implements Comparable<Student> {

    private int id;
    private String studentName;
    private int semester;
    private HashMap<String, Integer> courseName;

    /**
     * Default constructor for the Student class. Initializes a new Student
     * object without setting any initial values for its fields. This
     * constructor is often used when an object needs to be created first, and
     * its attributes will be populated later, for instance, when deserializing
     * data from a file or database, or when using setter methods.
     */
    public Student() {
    }

    /**
     * Constructor for the Student class, specifically for creating a student
     * record with their ID, name, enrolled semester, and course name. This
     * constructor is useful for representing a student's enrollment in a
     * particular course within a specific semester.
     *
     * @param id The unique identification number of the student.
     * @param studentName The full name of the student.
     * @param semester The semester in which the student is enrolled for this
     * course.
     * @param courseName The name and number of the course the student is
     * taking.
     */
    public Student(int id, String studentName, int semester, HashMap<String, Integer> courseName) {
        this.id = id;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
    }

    /**
     * Constructs a new Student object with the specified name and course
     * information.
     *
     * @param studentName The full name of the student.
     * @param courseName A HashMap where keys are course names (String) and
     * values are typically the scores or credits (Integer) obtained in those
     * courses.
     */
    public Student(String studentName, HashMap<String, Integer> courseName) {
        this.studentName = studentName;
        this.courseName = courseName;
    }

    /**
     * Retrieves the unique identification number of the student.
     *
     * @return The student's ID as an integer.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identification number for the student.
     *
     * @param id The new ID to be set for the student.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the full name of the student.
     *
     * @return The student's name as a String.
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Sets the full name of the student.
     *
     * @param studentName The new student name to be set.
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Retrieves the semester number in which the student is enrolled for a
     * particular course.
     *
     * @return The semester number as an integer.
     */
    public int getSemester() {
        return semester;
    }

    /**
     * Sets the semester number for the student's course enrollment.
     *
     * @param semester The new semester number to be set.
     */
    public void setSemester(int semester) {
        this.semester = semester;
    }

    /**
     * Retrieves the name of the course the student is taking.
     *
     * @return The course name as a String.
     */
    public HashMap<String, Integer> getCourseName() {
        return courseName;
    }

    /**
     * Overrides the default `toString()` method to provide a concise string
     * representation of the Student object's enrollment details. This format is
     * useful for displaying the student's ID, name, semester, and course name
     * on a single line.
     *
     * @return A formatted String containing the student's ID, name, semester,
     * and course name.
     */
    @Override
    public String toString() {
        return String.format("%d %s %d %s",
                id,
                studentName,
                semester,
                printOutCourse()
        );
    }

    /**
     * Generates a formatted string containing the student's ID, name, semester,
     * and course name. This method provides a more descriptive and labeled
     * output compared to the `toString()` method, suitable for displaying
     * detailed student enrollment information.
     *
     * @return A formatted String with labels for each piece of student
     * enrollment data, followed by a newline.
     */
    public String printOut() {
        return String.format("id: %d | Student name: %s | Semeester: %d | Course name: %s\n",
                id,
                studentName,
                semester,
                printOutCourse()
        );
    }

    /**
     * Generates a comma-separated string of course names associated with this
     * student. Only includes course names that have a non-null associated value
     * (e.g., score/count). If a course's value is null, it is skipped.
     *
     * @return A String containing the names of the courses, separated by
     * commas. Returns an empty string if there are no valid courses.
     */
    public String printOutCourse() {
        StringBuilder course = new StringBuilder();
        int i = 0;
        // Iterates through each course name (key) in the 'courseName' HashMap.
        for (String e : courseName.keySet()) {
            if (i > 0 && courseName.get(e) != null) {
                course.append(", ");
            }
            if (courseName.get(e) == null) {
                continue;
            }
            course.append(e);
            i++;
        }
        return course.toString();
    }

    /**
     * Updates all information (ID, student name, semester, and course name) for
     * the current Student object. This method effectively re-initializes the
     * object's attributes with new values.
     *
     * @param id The new unique identification number for the student.
     * @param studentName The new full name of the student.
     * @param semester The new semester number for the student's course
     * enrollment.
     * @param courseName The new name of the course the student is taking.
     */
    public void updateStudent(int id, String studentName, int semester, HashMap<String, Integer> courseName) {
        this.id = id;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
    }

    /**
     * Compares this `Student` object with another `Student` object based on the
     * last name of the student. This method implements the `compareTo` method
     * from the `Comparable` interface, allowing `Student` objects to be sorted.
     * The primary sorting criterion is the last name, assuming `getLastName`
     * correctly extracts it.
     *
     * @param o The `Student` object to be compared.
     * @return A negative integer, zero, or a positive integer as this object's
     * last name is less than, equal to, or greater than the specified object's
     * last name.
     */
    @Override
    public int compareTo(Student o) {
        String thisLastName = getLastName(studentName);
        String otherLastName = getLastName(o.getStudentName());
        return thisLastName.compareTo(otherLastName);
    }

    /**
     * Extracts the last name from a given full name string. This method assumes
     * that the last word in the full name string (separated by spaces)
     * represents the last name.
     *
     * @param name The full name of the student (e.g., "Nguyen Van A", "John
     * Doe").
     * @return The last name extracted from the full name. Returns an empty
     * string if the input name is null or empty.
     */
    public String getLastName(String name) {
        String lastName = "";
        if (name == null || name.isEmpty()) {
            return lastName;
        }
        String[] temp = name.split(" ");
        lastName = temp[temp.length - 1];
        if (lastName.isEmpty() || lastName == null) {
            return temp[temp.length - 2];
        } else {
            return lastName;
        }
    }

    /**
     * Overrides the default `equals()` method to define equality between two
     * `Student` objects. Two `Student` objects are considered equal if all
     * their core attributes (`id`, `studentName`, `courseName`, and `semester`)
     * are identical. This method ensures that objects representing the same
     * student enrollment are treated as equal.
     *
     * @param obj The object to compare with the current `Student` instance.
     * @return `true` if the specified object is equal to this `Student` object;
     * `false` otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Student o = (Student) obj;
        return id == o.getId()
                && studentName.equals(o.getStudentName())
                && courseName.equals(o.getCourseName())
                && semester == o.getSemester();
    }

    /**
     * Overrides the default `hashCode()` method to generate a hash code for
     * `Student` objects. This method is implemented to be consistent with the
     * `equals()` method: if two objects are equal according to the
     * `equals(Object)` method, then calling the `hashCode` method on each of
     * the two objects must produce the same integer result.
     *
     * The hash code is computed based on all fields that contribute to
     * equality: `id`, `studentName`, `courseName`, and `semester`.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + id;
        hash = 31 * hash + studentName.hashCode();
        hash = 31 * hash + courseName.hashCode();
        hash = 31 * hash + semester;
        return hash;
    }

}
