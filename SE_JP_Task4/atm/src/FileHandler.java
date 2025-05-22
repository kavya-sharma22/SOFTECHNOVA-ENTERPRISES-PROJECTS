import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * FileHandler class manages reading from and writing to files.
 */
public class FileHandler {
    // Set to store registered account numbers for ensuring uniqueness
    private static Set<String> registeredAccountNumbers = new HashSet<>();

    /**
     * Method to write user data to a file.
     *
     * @param name         Name of the user.
     * @param fatherName   Father's name of the user.
     * @param dob          Date of birth of the user.
     * @param email        Email of the user.
     * @param city         City of the user.
     * @param state        State of the user.
     * @param pinCode      PIN code of the user.
     * @param address      Address of the user.
     * @param gender       Gender of the user.
     * @param maritalState Marital status of the user.
     * @param cardNum      Card number of the user.
     * @param pinNum       PIN number of the user.
     * @param amount       Starting amount in the account.
     * @param eCheck       Email service check.
     * @param accNum       Account number of the user.
     */
    public static void writeUserDataToFile(String name, String fatherName, String dob, String email, String city,
                                           String state, String pinCode, String address, String gender, String maritalState,
                                           String cardNum, String pinNum, String amount, String eCheck, String accNum) {
        String fileName = "user_data.txt";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        String entry = "Entry Time: " + timestamp + "\n" +
                "Account Number: " + accNum + "\n" +
                "Name: " + name + "\n" +
                "Father's Name: " + fatherName + "\n" +
                "Date of Birth: " + dob + "\n" +
                "Email: " + email + "\n" +
                "City: " + city + "\n" +
                "State: " + state + "\n" +
                "Pin Code: " + pinCode + "\n" +
                "Address: " + address + "\n" +
                "Gender: " + gender + "\n" +
                "Marital Status: " + maritalState + "\n" +
                "Card Number: " + cardNum + "\n" +
                "PIN: " + pinNum + "\n" +
                "Start Amount: " + amount + "\n" +
                "Email Service: " + eCheck + "\n\n";
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName, true)); // Append mode
            writer.println(entry);
            writer.close();
            System.out.println("User data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing user data to file: " + e.getMessage());
        }
    }
    /**
     * Records the transaction in the file.
     *
     * @param amount          The amount of money involved in the transaction.
     * @param transactionType The type of transaction (e.g., deposit, withdrawal).
     * @param accNum          The account number associated with the card.
     */
    static public void recordTransaction(String accNum, double amount, String transactionType) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("records.txt", true))) {
            writer.write("Account Number: " + accNum + "\n");
            writer.write(transactionType + " Time: " + java.time.LocalDateTime.now() + "\n");
            writer.write(transactionType + " Amount: " + amount + " ₹\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Method to generate a unique account number.
     *
     * @return A unique account number.
     */
    static String generateAccNum() {
        Random random = new Random();
        StringBuilder accNumBuilder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            accNumBuilder.append(random.nextInt(10)); // Append a random digit (0-9)
        }
        String accNum = accNumBuilder.toString();
        // Ensure uniqueness
        while (registeredAccountNumbers.contains(accNum)) {
            accNumBuilder = new StringBuilder();
            for (int i = 0; i < 16; i++) {
                accNumBuilder.append(random.nextInt(10));
            }
            accNum = accNumBuilder.toString();
        }
        registeredAccountNumbers.add(accNum);
        return accNum;
    }
}
