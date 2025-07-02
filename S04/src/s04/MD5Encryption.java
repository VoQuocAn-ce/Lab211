/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s04;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * S04-Write a login function uses MD5 encryption for passwords (separate from
 * FPT Webmail software Project.
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-05-25
 */
public class MD5Encryption {

    /**
     * Encrypts (hashes) a given password string using the MD5 algorithm. MD5 is
     * a cryptographic hash function that produces a 128-bit (16-byte) hash
     * value.
     *
     * @param password The plain-text password string to be hashed.
     * @return A hexadecimal string representation of the MD5 hash of the input
     * password. Returns an empty string if the "MD5" algorithm is not available
     * on the system.
     *
     * Note: MD5 is considered cryptographically broken and is not suitable for
     * securing passwords in modern applications due to its vulnerability to
     * collision attacks. For password storage, stronger hashing algorithms like
     * bcrypt, scrypt, or Argon2 should be used.
     */
    public String encryption(String password) {
        // Use a try-catch block to handle the potential NoSuchAlgorithmException.
        // This exception occurs if the requested cryptographic algorithm (MD5 in this case)
        // is not available in the environment.
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            StringBuilder hex = new StringBuilder();
            // Iterate through each byte in the `messageDigest` byte array.
            // Each byte represents a part of the MD5 hash.
            for (byte e : messageDigest) {
                hex.append(String.format("%02x", e));
            }
            return hex.toString();
        }// Catch block for NoSuchAlgorithmException.
        // This specific exception is caught if "MD5" is not a recognized algorithm. 
        catch (NoSuchAlgorithmException e) {
            System.out.println("**********************");
            System.out.println("No such algorithm!");
            System.out.println("**********************");
        }
        return "";
    }
}
