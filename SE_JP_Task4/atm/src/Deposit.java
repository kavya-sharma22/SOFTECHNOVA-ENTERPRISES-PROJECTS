import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * The Deposit class represents the dialog window for depositing money into the account.
 */
public class Deposit extends JDialog {

    // Components
    private JTextField textField; // Input field for amount to deposit
    private JButton depositButton, cancelButton; // Deposit and cancel buttons

    // Data
    private List<UserData> userDataList; // List of user data
    private String cardNum; // Card number of the user
    private Validate v; // Validator
    private String accNum; // Account number associated with the card

    /**
     * Constructor for Deposit class.
     *
     * @param owner       The owner frame of the dialog.
     * @param cardNum     The card number of the user.
     * @param userDataList The list of user data.
     */
    public Deposit(Frame owner, String cardNum, List<UserData> userDataList) {
        super(owner, "Deposit", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon("src/icon.png").getImage());
        this.cardNum = cardNum;
        this.userDataList = userDataList;
        v = new Validate();

        // Layout setup
        setLayout(new GridLayout(3, 2, 10, 10));
        textField = new JTextField();
        depositButton = new JButton("Deposit");
        cancelButton = new JButton("Cancel");

        // Adding components to the dialog
        add(new JLabel("Enter Amount to Deposit:"));
        add(textField);
        add(depositButton);
        add(cancelButton);

        // Action listeners
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accNum = UserData.getData(cardNum, 15, userDataList); // Initialize accNum here
                if (v.isAmount(textField.getText())) {
                    double oldBalance = Double.parseDouble(UserData.getData(cardNum, 5, userDataList));
                    double depositAmount = Double.parseDouble(textField.getText());
                    double newBalance = oldBalance + depositAmount;
                    UserData.setData(cardNum, 5, Double.toString(newBalance), userDataList); // Update balance

                    // Record the deposit in the file
                    FileHandler.recordTransaction(accNum, depositAmount, "Deposit");

                    JOptionPane.showMessageDialog(null, textField.getText() + " ₹ deposited successfully into your account.");

                    // Send email notification if enabled
                    if ("Yes".equals(UserData.getData(cardNum, 14, userDataList))) {
                        String msg=Emsg.moneyDeposited(UserData.getData(cardNum,4,userDataList),Double.toString(depositAmount),UserData.getData(cardNum,15,userDataList),Double.toString(newBalance));
                        new EmailService().sendEmail(UserData.getData(cardNum, 3, userDataList), "Deposit Confirmation", msg, null,true);
                    }

                    dispose(); // Dispose dialog after depositing
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Dispose dialog directly when cancel button is clicked
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

}
