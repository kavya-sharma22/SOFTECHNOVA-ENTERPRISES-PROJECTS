import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validate class provides various validation methods for different purposes.
 */
class Validate {

    /**
     * Method to validate PIN code.
     *
     * @param pinCode The PIN code to validate.
     * @return true if the PIN is valid, false otherwise.
     */
    boolean isPin(String pinCode) {
        if (pinCode == null || pinCode.isEmpty()) {
            return false;
        }
        if (!pinCode.matches("\\d+")) {
            return false;
        }
        return pinCode.length() == 6;
    }

    /**
     * Method to get the text of the selected radio button in a ButtonGroup.
     *
     * @param group The ButtonGroup containing the radio buttons.
     * @return The text of the selected radio button.
     */
    String getSelectedRadioButtonText(ButtonGroup group) {
        for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    /**
     * Method to check if a string contains only plain text.
     *
     * @param str The string to check.
     * @return true if the string contains only plain text, false otherwise.
     */
    boolean isPlainText(String str) {
        for (char c : str.toCharArray()) {
            if (!(Character.isLetter(c) || Character.isWhitespace(c))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to check if a string is in date format (yyyy-MM-dd).
     *
     * @param str The string to check.
     * @return true if the string is a valid date, false otherwise.
     */
    boolean isDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        try {
            sdf.parse(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method to validate an email address.
     *
     * @param email The email address to validate.
     * @return true if the email address is valid, false otherwise.
     */
    boolean isEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Method to validate a 12-digit card number.
     *
     * @param card The card number to validate.
     * @return true if the card number is valid, false otherwise.
     */
    boolean isCard(String card) {
        if (card == null || card.isEmpty()) {
            return false;
        }
        if (!card.matches("\\d+")) {
            return false;
        }
        return card.length() == 12;
    }

    /**
     * Method to validate a 16-digit account number.
     *
     * @param card The account number to validate.
     * @return true if the account number is valid, false otherwise.
     */
    boolean isAccountNumber(String card) {
        if (card == null || card.isEmpty()) {
            return false;
        }
        if (!card.matches("\\d+")) {
            return false;
        }
        return card.length() == 16;
    }

    /**
     * Method to validate an amount (numeric string).
     *
     * @param amount The amount to validate.
     * @return true if the amount is valid, false otherwise.
     */
    boolean isAmount(String amount) {
        if (amount == null || amount.isEmpty()) {
            return false;
        }
        return amount.matches("\\d+");
    }

    /**
     * Method to validate if the amount is non-negative.
     *
     * @param amount The amount to validate.
     * @return true if the amount is non-negative, false otherwise.
     */
    boolean isAmountNeg(String amount) {
        try {
            double d = Double.parseDouble(amount);
            return d >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Method to authenticate a user using card number and PIN.
     *
     * @param cardNumber  The card number to authenticate.
     * @param pin         The PIN to authenticate.
     * @param userDataList The list of user data to search for authentication.
     * @return true if the user is authenticated, false otherwise.
     */
    static boolean authenticateUser(String cardNumber, String pin, List<UserData> userDataList) {
        for (UserData userData : userDataList) {
            if (userData.getCardNumber().equals(cardNumber) && userData.getPin().equals(pin)) {
                return true; // User authenticated
            }
        }
        return false; // User not found or authentication failed
    }

    /**
     * Method to verify OTP through email.
     *
     * @param email The email address to send OTP.
     * @param owner The parent frame.
     * @return true if OTP verification is successful, false otherwise.
     */
    boolean otpVerify(String email, Frame owner) {
        if (!isInternetAvailable()) {
            JOptionPane.showMessageDialog(null, "No internet connection available.");
            return false;
        }
        JOptionPane.showMessageDialog(null, "OTP Sent to " + email);
        OtpManner otpManner = new OtpManner(owner, email);
        otpManner.setVisible(true);
        return otpManner.isVerified();
    }

    /**
     * Method to check if internet is available.
     *
     * @return true if internet is available, false otherwise.
     */
    static boolean isInternetAvailable() {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("www.google.com", 80), 2000);
            socket.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
