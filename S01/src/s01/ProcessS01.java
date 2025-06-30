/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s01;

/**
 * S01-Manage student
 * 
 * @author Vo Quoc An - ce190460
 * @since 2025-05-17
 */
public class ProcessS01 {

    /**
     * It presents a menu to the user and dispatches actions based on their choice.
     */
    public void run() {
        boolean condition = true;
        StudentManagement sm = new StudentManagement();
        while (condition) {
            sm.sortList();
            System.out.printf("=====Student Management=====\n"
                    + "1. Enter student list\n"
                    + "2. Look up student\n"
                    + "3. Display student list\n"
                    + "4. Quit\n"
            );
            int choice = ValidInput.positiveIntegerForMenu("Please choose menu (1-4): ");
            switch (choice) {
                case 1:
                    String code = ValidInput.validStudentcode();
                    String name = ValidInput.name("Student name: ");
                    String dareOfBirth = ValidInput.validFormatDate();
                    float learningPoint = ValidInput.validLearningPoint();
                    Student newStudent = new Student(code, name, dareOfBirth, learningPoint);
                    sm.getListStudent().add(newStudent);
                    sm.sortList();
                    sm.saveNew();
                    break;
                case 2:
                    sm.lookUpStudent();
                    break;
                case 3:
                    sm.printOut();
                    break;
                case 4:
                    condition = false;
                    break;
                
            }
        }
    }
}
