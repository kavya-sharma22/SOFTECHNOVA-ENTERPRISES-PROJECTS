import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The Main class represents the main GUI window of the ATM simulation application.
 */
public class Main extends JFrame {
    private JLabel cardNumLabel, pinNumLabel, banner;
    private JButton signIn, signUp, clear;
    private JTextField cardNumInput;
    private JPasswordField pinNumInput;
    private Validate validator;
    private List<UserData> userDataList;

    /**
     * Constructor for the Main class.
     * Initializes the main GUI window.
     */
    public Main() {
        setTitle("ATM Simulation APP");
        setSize(520, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setIconImage(new ImageIcon("src/icon.png").getImage());
        initComponents();
    }

    /**
     * Initializes all the components of the main GUI window.
     */
    private void initComponents() {
        // Fetch user data from the file
        userDataList = UserData.fetchUserData();

        banner = new JLabel("Welcome To ATM");
        banner.setHorizontalAlignment(SwingConstants.CENTER);
        banner.setOpaque(true);
        banner.setBackground(Color.gray);
        banner.setBounds(20, 20, 450, 50);
        add(banner);

        cardNumLabel = new JLabel("Enter Card No.:");
        cardNumLabel.setBounds(20, 100, 120, 25);
        add(cardNumLabel);

        cardNumInput = new JTextField(12);
        cardNumInput.setBounds(150, 100, 320, 25);
        add(cardNumInput);

        pinNumLabel = new JLabel("Enter PIN No.:");
        pinNumLabel.setBounds(20, 150, 120, 25);
        add(pinNumLabel);

        pinNumInput = new JPasswordField(12);
        pinNumInput.setBounds(150, 150, 320, 25);
        add(pinNumInput);

        signIn = new JButton("Sign In");
        signIn.setBounds(150, 200, 100, 30);
        add(signIn);

        signUp = new JButton("Sign Up");
        signUp.setBounds(260, 200, 100, 30);
        add(signUp);

        clear = new JButton("Clear");
        clear.setBounds(370, 200, 100, 30);
        add(clear);

        // ActionListener for clear button
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear the card number and PIN input fields
                cardNumInput.setText("");
                pinNumInput.setText("");
            }
        });

        // ActionListener for sign up button
        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open sign up dialog
                SignUp signUpDialog = new SignUp(Main.this, userDataList);
                signUpDialog.setVisible(true);
            }
        });

        // ActionListener for sign in button
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fetch user data
                userDataList = UserData.fetchUserData();
                validator = new Validate();
                String cardNumber = cardNumInput.getText();
                String pin = new String(pinNumInput.getPassword());

                if (validator.isCard(cardNumber) && validator.isPin(pin)) {
                    if (validator.authenticateUser(cardNumber, pin, userDataList)) {
                        String eCheck = UserData.getData(cardNumber, 14, userDataList);
                        if ("Yes".equals(eCheck)) {
                            String email = UserData.getData(cardNumber, 3, userDataList);
                            if (validator.otpVerify(email, Main.this)) {
                                JOptionPane.showMessageDialog(null, "Authentication Successful!");
                                // Send login confirmation email
                                String msg=Emsg.logAlert(UserData.getData(cardNumber, 4, userDataList));
                                new EmailService().sendEmail(email, "Login Notification",msg,null,true);
                                cardNumInput.setText("");
                                pinNumInput.setText("");
                                // Open sign in dialog
                                SignIn signInDialog = new SignIn(Main.this, cardNumber, userDataList);
                                signInDialog.setVisible(true);
                            } else {
                                cardNumInput.setText("");
                                pinNumInput.setText("");
                                JOptionPane.showMessageDialog(null, "Email verification failed.");
                            }
                        } else {
                            cardNumInput.setText("");
                            pinNumInput.setText("");
                            JOptionPane.showMessageDialog(null, "Login Successful!");
                            // Open sign in dialog
                            SignIn signInDialog = new SignIn(Main.this, cardNumber, userDataList);
                            signInDialog.setVisible(true);
                        }
                    } else {
                        cardNumInput.setText("");
                        pinNumInput.setText("");
                        JOptionPane.showMessageDialog(null, "Authentication Failed. Invalid Card Number or PIN.");
                    }
                } else {
                    cardNumInput.setText("");
                    pinNumInput.setText("");
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Card Number And PIN....");
                }
            }
        });
    }

    /**
     * The main method to start the application.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    /**
     * Static block to display a splash screen on application start.
     */
    static {
        JWindow splashScreen = new JWindow();
        splashScreen.setSize(520, 360);

        ImageIcon splashIcon = new ImageIcon(Main.class.getResource("/icon.png"));
        JLabel splashLabel = new JLabel(splashIcon);
        splashScreen.add(splashLabel);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        splashScreen.setLocation(
                (screenSize.width - splashScreen.getWidth()) / 2,
                (screenSize.height - splashScreen.getHeight()) / 2
        );

        splashScreen.setVisible(true);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        splashScreen.dispose();
    }

    /**
     * Restarts the application.
     */
    private void restartApplication() {
        dispose(); // Close the current window
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true); // Launch a new instance of Main
            }
        });
    }
}
