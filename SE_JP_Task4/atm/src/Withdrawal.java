import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Withdrawal class represents a dialog for withdrawing money from an account.
 */
public class Withdrawal extends JDialog {

    private JTextField textField;
    private JButton withdrawButton, cancelButton;
    private List<UserData> userDataList;
    private String cardNum;
    private Validate v;
    private String accNum;

    /**
     * Constructor to initialize the Withdrawal dialog.
     *
     * @param owner        The parent frame.
     * @param cardNum      The card number for the account.
     * @param userDataList The list of user data.
     */
    public Withdrawal(Frame owner, String cardNum, List<UserData> userDataList) {
        super(owner, "Withdrawal", true);
        setIconImage(new ImageIcon("src/icon.png").getImage());
        this.cardNum = cardNum;
        this.userDataList = userDataList;
        v = new Validate();

        // Fetch account number from userDataList
        for (UserData userData : userDataList) {
            if (userData.getCardNumber().equals(cardNum)) {
                accNum = userData.getAccNum();
                break;
            }
        }

        setLayout(new GridLayout(3, 2, 10, 10));
        textField = new JTextField();
        withdrawButton = new JButton("Withdraw");
        cancelButton = new JButton("Cancel");

        add(new JLabel("Enter Amount to Withdraw:"));
        add(textField);
        add(withdrawButton);
        add(cancelButton);

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (v.isAmount(textField.getText())) {
                    double oldBalance = Double.parseDouble(UserData.getData(cardNum, 5, userDataList));
                    double withdrawalAmount = Double.parseDouble(textField.getText());

                    // Maximum withdrawal limit is 10,000₹
                    if (withdrawalAmount <= 10000) {
                        // Check if the withdrawal amount doesn't cause the balance to go below the start amount of 1000₹
                        if (oldBalance - withdrawalAmount >= 1000) {
                            double newBalance = oldBalance - withdrawalAmount;
                            UserData.setData(cardNum, 5, Double.toString(newBalance), userDataList); // Update balance

                            // Record the withdrawal in the file
                            FileHandler.recordTransaction(accNum, withdrawalAmount, "Withdrawal");

                            JOptionPane.showMessageDialog(null, "Please collect your  money...");

                            if ("Yes".equals(UserData.getData(cardNum, 14, userDataList))) {
                                String msg=Emsg.moneyWithdrawn(UserData.getData(cardNum, 4, userDataList),Double.toString(withdrawalAmount),UserData.getData(cardNum, 15, userDataList),Double.toString(newBalance));
                                new EmailService().sendEmail(UserData.getData(cardNum, 3, userDataList), "Withdrawal Confirmation", msg, null,true);
                            }

                            dispose(); // Close the dialog after withdrawal
                        } else {
                            JOptionPane.showMessageDialog(null, "Your balance cannot go below 1000₹.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Maximum withdrawal limit is 10,000₹.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog when Cancel button is clicked
            }
        });

        pack();
        setLocationRelativeTo(null);
    }
}
