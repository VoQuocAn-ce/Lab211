/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v05;

/**
 * V05 - Doctor management program.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-26
 */
public class Doctor {

    private String id;
    private String name;
    private String specialization;
    private int availability;

    /**
     * Represents a doctor with their ID, name, specialization, and
     * availability.
     *
     * @param id The unique identifier for the doctor.
     * @param name The name of the doctor.
     * @param Specialization The medical specialization of the doctor (e.g.,
     * Cardiology, Pediatrics).
     * @param availability The availability status of the doctor, possibly
     * representing the number of slots or a status code.
     */
    public Doctor(String id, String name, String Specialization, int availability) {
        this.id = id;
        this.name = name;
        this.specialization = Specialization;
        this.availability = availability;
    }

    /**
     * Returns the ID of the doctor.
     *
     * @return The doctor's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the doctor.
     *
     * @param id The new ID for the doctor.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the name of the doctor.
     *
     * @return The doctor's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the doctor.
     *
     * @param name The new name for the doctor.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the specialization of the doctor.
     *
     * @return The doctor's specialization.
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Sets the specialization of the doctor.
     *
     * @param Specialization The new specialization for the doctor.
     */
    public void setSpecialization(String Specialization) {
        this.specialization = Specialization;
    }

    /**
     * Returns the availability of the doctor.
     *
     * @return The doctor's availability.
     */
    public int getAvailability() {
        return availability;
    }

    /**
     * Sets the availability of the doctor.
     *
     * @param availability The new availability for the doctor.
     */
    public void setAvailability(int availability) {
        this.availability = availability;
    }

    /**
     * Returns a formatted string representation of the doctor's data, suitable
     * for entry into a data source or file. The format is
     * "Doctor,id,name,specialization,availability\n".
     *
     * @return A string containing the doctor's data.
     */
    public String entryData() {
        return String.format("Doctor,%s,%s,%s,%d\n",
                id,
                name,
                specialization,
                availability
        );
    }

    /**
     * Generates a unique search key for the doctor by concatenating their ID,
     * name, specialization, and availability. This key can be used for quick
     * lookups or comparisons.
     *
     * @return A string representing the concatenated search key for the doctor.
     */
    public String generateSearchKey() {
        return String.format("%s%s%s%d",
                id,
                name,
                specialization,
                availability
        );
    }

    /**
     * Checks if any of the doctor's key fields (name, specialization, or
     * availability) are empty or uninitialized. If a field is empty, a message
     * is printed to the console indicating which field is missing.
     *
     * @return true if any of the fields are empty or invalid, false otherwise.
     */
    public boolean checkEmpty() {
        int i = 1;
        if (name.isEmpty()) {
            System.out.println("Name is empty");
            i++;
        }
        if (specialization.isEmpty()) {
            System.out.println("Specialization is empty");
            i++;
        }
        if (availability == -1) {
            System.out.println("Availability is empty");
            i++;
        }
        return i != 1;
    }
}
