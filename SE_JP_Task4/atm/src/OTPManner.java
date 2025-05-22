import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * OtpManner class represents a dialog for OTP verification.
 */
class OtpManner extends JDialog {
    private JLabel l1;
    private JPasswordField passI;
    private JButton check, resend;
    private int otp;
    private boolean e = false; // Flag to indicate if verification is successful
    private EmailService emailService; // Email service for sending OTP
    private Validate v;

    /**
     * Constructor to initialize the OTP verification dialog.
     *
     * @param owner    The parent frame.
     * @param receiver The email address to which the OTP will be sent.
     */
    public OtpManner(Frame owner, String receiver) {
        super(owner, true);
        setTitle("Verification");
        setSize(520, 160);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon("src/icon.png").getImage());
        setLocationRelativeTo(null);
        setLayout(null);
        v = new Validate();

        // Initialize components
        l1 = new JLabel("Enter Your OTP :");
        passI = new JPasswordField();
        check = new JButton("Check");
        resend = new JButton("Resend");

        // Set positions of components
        l1.setBounds(20, 20, 480, 30);
        passI.setBounds(20, 60, 200, 30);
        check.setBounds(240, 60, 100, 30);
        resend.setBounds(350, 60, 100, 30);

        // Add components to dialog
        add(l1);
        add(passI);
        add(check);
        add(resend);

        // Initialize email service
        emailService = new EmailService();

        // Action listener for check button
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (isValidOTP(passI.getText())) {
                    int pass = Integer.parseInt(passI.getText());
                    if (otp == pass) {
                        e = true;
                        dispose(); // Close the dialog if verification is successful
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect OTP! OR Need 6-Digit OTP"+"\n(TIP:Use Resend"); // Display error message for incorrect OTP
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid OTP Format!"); // Display error message for invalid OTP format
                }
            }
        });

        // Action listener for resend button
        resend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateAndSendOTP(receiver);
                JOptionPane.showMessageDialog(null, "OTP resend successfully...");// Generate and send a new OTP
            }
        });

        generateAndSendOTP(receiver); // Automatically send the first OTP when the dialog is created
    }

    /**
     * Method to check if the entered OTP is in the correct format and matches the generated OTP.
     *
     * @param otpInput The OTP entered by the user.
     * @return true if the OTP is valid, false otherwise.
     */
    private boolean isValidOTP(String otpInput) {
        return v.isPin(otpInput);
    }

    /**
     * Method to generate and send OTP.
     *
     * @param receiver The email address to which the OTP will be sent.
     */

    private void generateAndSendOTP(String receiver) {
        otp = generateOTP(); // Generate a new OTP
        String msg=Emsg.otpMessage(Integer.toString(otp));
        emailService.sendEmail(receiver, "Verification", msg, null,true); // Send OTP via email
    }

    /**
     * Method to check if verification is successful.
     *
     * @return true if verification is successful, false otherwise.
     */
    public boolean isVerified() {
        return e;
    }

    /**
     * Method to generate a random OTP.
     *
     * @return The generated OTP.
     */
    private int generateOTP() {
        Random random = new Random();
        int generatedOTP = 0;

        for (int i = 0; i < 6; i++) {
            generatedOTP = generatedOTP * 10 + random.nextInt(10); // Generates a new digit and appends it to otp
        }
        return generatedOTP;
    }
}
