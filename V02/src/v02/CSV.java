/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v02;

/**
 * V02 - Subsystem for listing and searching files by content.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-15
 */
public class CSV {

    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;

    /**
     * Constructs a new CSV object with the specified details.
     *
     * @param id The ID of the entry.
     * @param name The name of the person/entity.
     * @param email The email address.
     * @param phone The phone number.
     * @param address The address.
     */
    public CSV(String id, String name, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    /**
     * Returns the ID of the CSV entry.
     *
     * @return The ID.
     */
    public String getId() {
        return id; // Returns the value of the 'id' field.
    }

    /**
     * Sets the ID of the CSV entry.
     *
     * @param id The new ID to set.
     */
    public void setId(String id) {
        this.id = id; // Sets the 'id' field to the provided 'id' parameter.
    }

    /**
     * Returns the name of the CSV entry.
     *
     * @return The name.
     */
    public String getName() {
        return name; // Returns the value of the 'name' field.
    }

    /**
     * Sets the name of the CSV entry.
     *
     * @param name The new name to set.
     */
    public void setName(String name) {
        this.name = name; // Sets the 'name' field to the provided 'name' parameter.
    }

    /**
     * Returns the email of the CSV entry.
     *
     * @return The email.
     */
    public String getEmail() {
        return email; // Returns the value of the 'email' field.
    }

    /**
     * Sets the email of the CSV entry.
     *
     * @param email The new email to set.
     */
    public void setEmail(String email) {
        this.email = email; // Sets the 'email' field to the provided 'email' parameter.
    }

    /**
     * Returns the phone number of the CSV entry.
     *
     * @return The phone number.
     */
    public String getPhone() {
        return phone; // Returns the value of the 'phone' field.
    }

    /**
     * Sets the phone number of the CSV entry.
     *
     * @param phone The new phone number to set.
     */
    public void setPhone(String phone) {
        this.phone = phone; // Sets the 'phone' field to the provided 'phone' parameter.
    }

    /**
     * Returns the address of the CSV entry.
     *
     * @return The address.
     */
    public String getAddress() {
        return address; // Returns the value of the 'address' field.
    }

    /**
     * Sets the address of the CSV entry.
     *
     * @param address The new address to set.
     */
    public void setAddress(String address) {
        this.address = address; // Sets the 'address' field to the provided 'address' parameter.
    }

    /**
     * Returns a comma-separated string of the CSV object's data, suitable for
     * writing to a CSV file. Each field is separated by a comma, and the string
     * ends with a newline character.
     *
     * @return A formatted string representing the CSV entry's data.
     */
    public String entryData() {
        return String.format("%s,%s,%s,%s,%s\n",
                id,
                name,
                email,
                phone,
                address
        );
    }

    /**
     * A private helper method to capitalize the first letter of a given string.
     * It iterates through the string to find the first lowercase letter and
     * capitalizes it. If no lowercase letter is found or the string is empty,
     * it returns the original string.
     *
     * @param text The input string to capitalize the first letter of.
     * @return The string with its first letter (if it's a lowercase letter)
     * capitalized.
     */
    private static String upcaseFirtLetter(String text) {
        // Loop through each character of the string.
        for (int i = 0; i < text.length(); i++) {
            // Check if the current character is a lowercase letter.
            if (text.charAt(i) >= 'a' && text.charAt(i) <= 'z') {
                if (i > 0) {
                    return text.substring(0, i) + Character.toUpperCase(text.charAt(i)) + text.substring(i + 1);
                } else if (i == 0) {
                    return Character.toUpperCase(text.charAt(i)) + text.substring(1);
                }
            }
        }
        return text;
    }

    /**
     * Formats the 'address' field by capitalizing the first letter of each word
     * and handling hyphens appropriately (no space before/after hyphens if part
     * of a word).
     */
    public void formatAddress() {
        String[] temp = address.toLowerCase().split("\\s+");
        // Loop through each word in the temporary array.
        for (int i = 0; i < temp.length; i++) {
            temp[i] = upcaseFirtLetter(temp[i]);
        }
        String finalText = "";
        // A flag to control spacing, particularly for hyphens.
        boolean check = false;
        // Loop through the processed words.
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].isEmpty()) {
                continue;
            }
            // If the word starts with a hyphen.
            if (temp[i].charAt(0) == '-') {
                // Append the word directly (no leading space).
                finalText += temp[i];
                if (temp[i].equals("-")) {
                    // Set check to true to prevent adding a space before the next word.
                    check = true;
                }
                continue;
            }
            if (i != 0 && check == false) {
                finalText += " ";
            }
            finalText += temp[i];
            if (temp[i].charAt(temp[i].length() - 1) == '-') {
                check = true;
            } else {
                check = false;
            }
        }
        address = finalText;
    }

    /**
     * Formats the 'name' field by capitalizing the first letter of each word.
     */
    public void formatName() {
        String[] temp = name.toLowerCase().split("\\s+");
        // Loop through each word in the temporary array.
        for (int i = 0; i < temp.length; i++) {
            temp[i] = upcaseFirtLetter(temp[i]);
        }
        String finalText = "";
        // Loop through the processed words.
        for (int i = 0; i < temp.length; i++) {
            finalText += temp[i];
            if (i != temp.length - 1) {
                finalText += " ";
            }
        }
        name = finalText;
    }

}
