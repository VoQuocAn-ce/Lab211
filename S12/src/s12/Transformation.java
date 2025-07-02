/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s12;

/**
 * S12 - Convert binary, octal and hexadecimal to decimal
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-25
 */
public class Transformation {

    private String numbers;

    /**
     * Converts a binary number, input by the user, into its decimal equivalent.
     *
     * This method prompts the user to enter a binary number, validates the
     * input, and then performs the conversion by iterating through each digit
     * of the binary number, calculating its decimal contribution based on its
     * position, and summing these contributions. The final decimal result is
     * then printed to the console.
     */
    public void convertBinary() {
        numbers = ValidInput.getValidBinary("Enter binary number: ");
        double result = 0;
        int n = 0;
        char[] num = numbers.toCharArray();
        // Iterates through the binary digits from right to left (least significant to most significant).
        for (int i = num.length - 1; i >= 0; i--) {
            result += (double) (num[i] - 48) * Math.pow(2, n);
            n++;
        }
        System.out.println("Decimal number is: " + (int) result);
    }

    /**
     * Converts an octal number, input by the user, into its decimal equivalent.
     *
     * This method prompts the user to enter an octal number, validates the
     * input, and then performs the conversion. It iterates through each digit
     * of the octal number from right to left, calculating its decimal
     * contribution based on its positional value (powers of 8), and sums these
     * contributions. The final decimal result is then printed to the console.
     */
    public void convertOctal() {
        numbers = ValidInput.getValidOctal("Enter octal number: ");
        double result = 0;
        int n = 0;
        char[] num = numbers.toCharArray();
        // Iterates through the octal digits from right to left (least significant to most significant).
        for (int i = num.length - 1; i >= 0; i--) {
            result += (double) (num[i] - 48) * Math.pow(8, n);
            n++;
        }
        System.out.println("Decimal number is: " + (int) result);
    }

    /**
     * Converts a hexadecimal number, input by the user, into its decimal
     * equivalent.
     *
     * This method prompts the user to enter a hexadecimal number, validates the
     * input, and then performs the conversion. It iterates through each digit
     * of the hexadecimal number from right to left. For each digit, it
     * determines its integer value (handling both 0-9 and A-F/a-f characters)
     * and calculates its decimal contribution based on its positional value
     * (powers of 16). These contributions are summed up, and the final decimal
     * result is printed to the console.
     */
    public void convertHexadecimal() {
        numbers = ValidInput.getValidHexadecimal("Enter hexadecimal number: ");
        double result = 0;
        int n = 0;
        char[] num = numbers.toCharArray();
        // Iterates through the hexadecimal digits from right to left (least significant to most significant).
        for (int i = num.length - 1; i >= 0; i--) {
            if (num[i] >= '0' && num[i] <= '9') {
                result += (double) (num[i] - 48) * Math.pow(16, n);
                n++;
            } else {
                result += (double) (num[i] - 55) * Math.pow(16, n);
                n++;
            }
        }
        System.out.println("Decimal number is: " + (int) result);
    }

}
