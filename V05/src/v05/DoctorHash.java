/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v05;

import java.util.HashMap;
import java.util.Map;

/**
 * V05 - Doctor management program.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-26
 */
public class DoctorHash {

    private HashMap<String, Doctor> doctorList;
    private FileHandler fh = new FileHandler();

    private String path;

    /**
     * Constructor for `DoctorHash`. This constructor initializes the doctor
     * management system by prompting the user to enter the path to the doctor
     * database. It continuously prompts until a valid and non-empty database
     * file is provided. Once a valid database is loaded, it prints a success
     * message and displays the loaded doctor list.
     */
    public DoctorHash() {
        // Loop indefinitely until a valid database path is provided and loaded
        while (true) {
            System.out.printf("========== Doctor Management ==========\n"
                    + "Please enter the path to the doctor database...\n");
            path = ValidInput.getPath("Path: ");
            doctorList = fh.readFromFile(path);
            if (doctorList.isEmpty()) {
                System.out.println("Database is invalid, Please selected valid database!");
                continue;
            } else {
                System.out.println("Input data successful....!");
                printOut(doctorList);
                break;
            }
        }
    }

    /**
     * Prints out the details of all doctors in the provided HashMap to the
     * console. The output is formatted into columns: Code, Name,
     * Specialization, and Availability.
     *
     * @param docList A HashMap where keys are doctor IDs (or another unique
     * identifier) and values are Doctor objects.
     */
    public void printOut(HashMap<String, Doctor> docList) {
        System.out.printf("  Code      Name       Specialization      Availability\n");
        // Iterate through each entry in the 'docList' HashMap.
        // Each entry consists of a key (String, likely the doctor's ID) and a value (Doctor object).
        for (Map.Entry<String, Doctor> e : docList.entrySet()) {
            System.out.printf("  %-7s   %-8s   %-23s   %-12d  \n",
                    e.getValue().getId(),
                    e.getValue().getName(),
                    e.getValue().getSpecialization(),
                    e.getValue().getAvailability()
            );
        }
    }

    /**
     * Saves the current list of doctors to the file system. This method uses
     * the `fh` (presumably a FileHandler instance) to write the contents of the
     * `doctorList` (a HashMap of doctor objects) to the specified `path`.
     */
    public void Save() {
        fh.saveToFile(doctorList, path);
    }

    /**
     * Handles user input for doctor operations, supporting adding, updating,
     * and deleting doctor records. This method guides the user to provide
     * necessary information based on the desired operation.
     *
     * @param update A boolean flag; if true, the method attempts to update an
     * existing doctor record.
     * @param delete A boolean flag; if true, the method attempts to prepare a
     * doctor record for deletion.
     * @return A Doctor object representing the new doctor to be added, the
     * updated doctor, or the doctor to be deleted.
     * @throws Exception If the doctor code does not exist for an update or
     * delete operation, or if input validation fails.
     */
    public Doctor handleInput(boolean update, boolean delete) throws Exception {
        String id = ValidInput.getValidId("Enter code: ");
        if (update) {
            if (!doctorList.containsKey(id)) {
                throw new Exception("Doctor code doesn’t exist");
            }
            String name = ValidInput.getValidName("Enter name: ");
            if (name.isEmpty()) {
                name = doctorList.get(id).getName();
            }
            String specialization = ValidInput.getValidSpecialization("Enter specialization: ");
            if (specialization.isEmpty()) {
                specialization = doctorList.get(id).getSpecialization();
            }
            int availability = ValidInput.getPositiveNumberAnd0("Enter availability: ");
            if (availability == -1) {
                availability = doctorList.get(id).getAvailability();
            }
            Doctor newDoctor = new Doctor(id, name, specialization, availability);
            return newDoctor;
        } else if (delete) {
            if (!doctorList.containsKey(id)) {
                throw new Exception("Doctor code doesn’t exist");
            }
            return doctorList.get(id);
        }
        if (doctorList.containsKey(id)) {
                throw new Exception(String.format("Doctor code [%s] is duplicate", id));
            }
        String name = ValidInput.getValidName("Enter name: ");
        String specialization = ValidInput.getValidSpecialization("Enter specialization: ");
        int availability = ValidInput.getPositiveNumberAnd0("Enter availability: ");
        Doctor newDoctor = new Doctor(id, name, specialization, availability);
        return newDoctor;
    }

    /**
     * Adds a new doctor to the system. This method validates the doctor object
     * before adding it to the `doctorList`. It checks for duplicate IDs, an
     * uninitialized database, and empty doctor data. If validation passes, the
     * doctor is added, and the changes are saved to file.
     *
     * @param doctor The Doctor object to be added.
     * @return true if the doctor was successfully added, false otherwise.
     */
    public boolean addDoctor(Doctor doctor) {
        try {
            if (doctorList.containsKey(doctor.getId())) {
                throw new Exception(String.format("Doctor code [%s] is duplicate", doctor.getId()));
            }
            if (doctorList.isEmpty()) {
                throw new IllegalStateException("Database does not exist");
            }
            if (doctor.checkEmpty()) {
                throw new Exception("Data does not exist");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        doctorList.put(doctor.getId(), doctor);
        Save();
        return true;
    }

    /**
     * Updates an existing doctor's information in the system. This method
     * validates the provided doctor object and ensures the doctor to be updated
     * actually exists in the database. If validations pass, the doctor's record
     * is updated, and changes are saved to the file.
     *
     * @param doctor The Doctor object containing the updated information. The
     * ID of this object is used to identify which doctor to update.
     * @return true if the doctor was successfully updated, false otherwise.
     * @throws Exception If the doctor code does not exist or if the database is
     * uninitialized.
     */
    public boolean updateDoctor(Doctor doctor) {
        try {
            if (!doctorList.containsKey(doctor.getId())) {
                throw new Exception("Doctor code doesn’t exist");
            }
            if (doctorList.isEmpty()) {
                throw new IllegalStateException("Database does not exist");
            }
            if (doctor.checkEmpty()) {
                throw new Exception("Data does not exist");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        doctorList.replace(doctor.getId(), doctor);
        Save();
        return true;
    }

    /**
     * Deletes a doctor's record from the system. This method validates the
     * existence of the doctor and the integrity of the database before
     * proceeding with the deletion. If valid, the doctor is removed, and
     * changes are saved.
     *
     * @param doctor The Doctor object to be deleted. The ID of this object is
     * used to identify which doctor to remove.
     * @return true if the doctor was successfully deleted, false otherwise.
     * @throws Exception If the doctor code does not exist, the database is
     * uninitialized, or the doctor object's data is invalid.
     */
    public boolean deleteDoctor(Doctor doctor) {
        try {
            if (!doctorList.containsKey(doctor.getId())) {
                throw new Exception("Doctor code doesn’t exist");
            }
            if (doctorList.isEmpty()) {
                throw new IllegalStateException("Database does not exist");
            }
            if (doctor.checkEmpty()) {
                throw new Exception("Data does not exist");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        doctorList.remove(doctor.getId());
        Save();
        return true;
    }

    /**
     * Searches for doctors whose combined information (ID, name,
     * specialization, availability) contains the given input string
     * (case-insensitive).
     *
     * @param input The search string to look for within doctor records.
     * @return A HashMap containing Doctor objects that match the search
     * criteria, where the key is the doctor's ID and the value is the Doctor
     * object. Returns an empty HashMap if no matches are found or if the
     * database does not exist.
     */
    public HashMap<String, Doctor> searchDoctor(String input) {
        HashMap<String, Doctor> foundList = new HashMap<>();
        try {
            if (doctorList.isEmpty()) {
                throw new IllegalStateException("Database does not exist");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return foundList;
        }
        for (Map.Entry<String, Doctor> e : doctorList.entrySet()) {
            if (e.getValue().generateSearchKey().toLowerCase().contains(input.toLowerCase())) {
                foundList.put(e.getKey(), e.getValue());
            }
        }
        return foundList;
    }

    /**
     * Checks if a doctor is available based on their availability status. This
     * method considers a doctor available if their availability value is zero
     * or greater.
     *
     * @param doctor The Doctor object whose availability is to be checked.
     * @return true if the doctor's availability is greater than or equal to 0,
     * false otherwise.
     */
    public boolean checkAvailability(Doctor doctor) {
        return doctor.getAvailability() >= 0;
    }
}
