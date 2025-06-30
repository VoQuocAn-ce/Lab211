/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s01;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * S01-Manage student
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-05-17
 */
public class Student {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyy", Locale.ENGLISH);

    private String code;
    private String name;
    private LocalDate dateOfBirth;
    private float learningPoint;

    /**
     * Constructor for the Student class. Initializes a new Student object with
     * the provided details.
     *
     * @param code The unique identification code of the student.
     * @param name The full name of the student.
     * @param dateOfBirth The date of birth of the student as a String, which
     * will be parsed into a LocalDate object.
     * @param learningPoint The learning point (score/grade) of the student.
     */
    public Student(String code, String name, String dateOfBirth, float learningPoint) {
        this.code = code;
        this.name = name;
        this.dateOfBirth = LocalDate.parse(dateOfBirth, formatter);
        this.learningPoint = learningPoint;
    }

    /**
     * Default constructor for the Student class. Initializes a new Student
     * object with default values (fields will be null or zero). This is often
     * used when creating an object before populating its fields, for example,
     * when reading data from a file or database.
     */
    public Student() {
    }

    /**
     * Retrieves the student's unique identification code.
     *
     * @return The student's code as a String.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the student's unique identification code.
     *
     * @param code The new student code to be set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Retrieves the student's full name.
     *
     * @return The student's name as a String.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the student's full name.
     *
     * @param name The new student name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the student's date of birth.
     *
     * @return The student's date of birth as a LocalDate object.
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the student's date of birth.
     *
     * @param dateOfBirth The new date of birth to be set as a LocalDate object.
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = LocalDate.parse(dateOfBirth, formatter);
    }

    /**
     * Retrieves the student's learning point (e.g., score or grade).
     *
     * @return The student's learning point as a float.
     */
    public float getLearningPoint() {
        return learningPoint;
    }

    /**
     * Sets the student's learning point.
     *
     * @param learningPoint The new learning point to be set for the student.
     */
    public void setLearningPoint(float learningPoint) {
        this.learningPoint = learningPoint;
    }

    /**
     * Overrides the default `toString()` method to provide a formatted string
     * representation of the Student object's details. This is useful for
     * displaying student information in a readable format, for example, when
     * printing to the console.
     *
     * @return A formatted String containing the student's code, name, date of
     * birth, and learning point.
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return String.format("Student code: %s\n"
                + "Student name: %s\n"
                + "Date of birth: %s\n"
                + "Learning point: %s\n",
                code, name, dateOfBirth.format(formatter), df.format(learningPoint)
        );
    }

    /**
     * Formats the student's data into a comma-separated string suitable for
     * file entry. The string includes a "Student" identifier, code, name,
     * formatted date of birth, and learning point, followed by a newline
     * character.
     *
     * @return A String representing the student's data in a specific delimited
     * format.
     */
    public String entryData() {
        DecimalFormat df = new DecimalFormat("#.##");
        return String.format("Student,%s,%s,%s,%s\n",
                code,
                name,
                dateOfBirth.format(formatter),
                df.format(learningPoint)
        );
    }

}
