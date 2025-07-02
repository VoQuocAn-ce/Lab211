/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s04;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

/**
 * S04-Write a login function uses MD5 encryption for passwords (separate from
 * FPT Webmail software Project.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-05-25
 */
public class User {

    private String userName;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Date dob;

    /**
     * Constructs a new User object with the provided details. This constructor
     * initializes all the essential attributes of a user, converting the date
     * of birth string into a proper date object.
     *
     * @param userName The user's unique username.
     * @param password The user's password (should ideally be hashed before
     * being stored or passed here).
     * @param name The user's full name.
     * @param phone The user's phone number.
     * @param email The user's email address.
     * @param address The user's physical address.
     * @param dob The user's date of birth as a String, expected to be in a
     * format parsable by `parseDate`.
     */
    public User(String userName, String password, String name, String phone, String email, String address, Date dob) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dob = dob;
    }

    /**
     * Retrieves the username of this user.
     *
     * @return A {@code String} representing the user's username.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets or updates the username for this user.
     *
     * @param userName The new username to set for the user.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Retrieves the password of this user.
     *
     * @return A {@code String} representing the user's password. Note: In a
     * real-world application, it's generally not recommended to expose
     * passwords directly, especially in plain text. Hashed passwords would be
     * retrieved instead.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets or updates the password for this user.
     *
     * @param password The new password to set for the user. Note: For security,
     * consider hashing the password before storing it or setting it here.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the full name of this user.
     *
     * @return A {@code String} representing the user's full name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets or updates the full name for this user.
     *
     * @param name The new full name to set for the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the phone number of this user.
     *
     * @return A {@code String} representing the user's phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets or updates the phone number for this user.
     *
     * @param phone The new phone number to set for the user.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retrieves the email address of this user.
     *
     * @return A {@code String} representing the user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets or updates the email address for this user.
     *
     * @param email The new email address to set for the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the physical address of this user.
     *
     * @return A {@code String} representing the user's physical address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets or updates the physical address for this user.
     *
     * @param address The new physical address to set for the user.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Retrieves the date of birth of this user.
     *
     * @return A {@code Date} object representing the user's date of birth.
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Sets or updates the date of birth for this user by parsing a string. It
     * relies on an assumed {@code parseDate} method to convert the string into
     * a {@code Date} object.
     *
     * @param dob The date of birth as a {@code String} (e.g., "dd/MM/yyyy").
     */
    public void setDob(String dob) {
        this.dob = parseDate(dob); // Assumes a 'parseDate' helper method exists in this class.
    }

    /**
     * Formats the user's data into a comma-separated string suitable for
     * storage in a text-based database. Each piece of user information is
     * separated by a comma, and the entire entry starts with "User," indicating
     * the record type.
     *
     * @return A {@code String} representing the user's data in
     * "User,userName,password,name,phone,email,address,dob\n" format. The date
     * of birth (dob) is formatted using a {@code SimpleDateFormat} instance,
     * which must be initialized elsewhere in the class.
     */
    public String entryData() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        return String.format("User,%s,%s,%s,%s,%s,%s,%s\n",
                userName,
                password,
                name,
                phone,
                email,
                address,
                formatter.format(dob)
        );
    }

    /**
     * Parses a date string into a {@code Date} object using a predefined
     * formatter. This method is a helper for converting string representations
     * of dates (like DOB) into a structured date format for internal use.
     *
     * @param dob The date of birth as a {@code String} to be parsed. It's
     * expected to be in the format configured by the {@code formatter}
     * instance.
     * @return A {@code Date} object if the parsing is successful. Returns
     * {@code null} if the input string cannot be parsed according to the
     * formatter's rules (e.g., invalid format, non-existent date like
     * "31/02/2023").
     */
    private Date parseDate(String dob) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        // Use a try-catch block to handle the ParseException that might occur if the input string
        // does not conform to the specified date format.
        try {
            return formatter.parse(dob);
        }// Catch block for ParseException. This exception is thrown by `formatter.parse()`
        // if the input `dob` string cannot be parsed into a Date object using the given format. 
        catch (ParseException e) {
            return null;
        }
    }
}
