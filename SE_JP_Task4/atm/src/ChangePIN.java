import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * ChangePIN class represents a dialog for changing PIN.
 */
public class ChangePIN extends JDialog {

    private JPasswordField currentPinField, newPinField;
    private JButton changeButton, cancelButton;
    private List<UserData> userDataList;
    private String cardNum;
    private Validate v;

    /**
     * Constructor to initialize the Change PIN dialog.
     *
     * @param owner       The owner frame of the dialog.
     * @param cardNum     The card number of the user.
     * @param userDataList The list of user data.
     */
    public ChangePIN(Frame owner, String cardNum, List<UserData> userDataList) {
        super(owner, "Change PIN", true);
        setIconImage(new ImageIcon("src/icon.png").getImage());
        this.cardNum = cardNum;
        this.userDataList = userDataList;
        v = new Validate();

        setLayout(new GridLayout(4, 2, 10, 10));
        currentPinField = new JPasswordField();
        newPinField = new JPasswordField();
        changeButton = new JButton("Change");
        cancelButton = new JButton("Cancel");

        add(new JLabel("Enter Your Current PIN:"));
        add(currentPinField);
        add(new JLabel("Enter Your New PIN:"));
        add(newPinField);
        add(changeButton);
        add(cancelButton);

        // Action listener for the change button
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentPin = new String(currentPinField.getPassword());
                String storedPin = UserData.getData(cardNum, 2, userDataList);

                if (currentPin.equals(storedPin)) { // Check if current PIN matches the stored PIN
                    String newPin = new String(newPinField.getPassword());

                    if (v.isPin(newPin)) { // Check if the new PIN is valid
                        UserData.setData(cardNum, 2, newPin, userDataList); // Update PIN in the userDataList
                        updatePinInFile(cardNum, newPin); // Update PIN in the user_data.txt file
                        JOptionPane.showMessageDialog(null, "PIN changed successfully.");

                        // Send email notification if enabled
                        if ("Yes".equals(UserData.getData(cardNum, 14, userDataList))) {
                            String msg=Emsg.pinChange(UserData.getData(cardNum, 4, userDataList));
                            new EmailService().sendEmail(UserData.getData(cardNum, 3, userDataList), "Security Alert: PIN Changed",
                                    msg, null,true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a valid 6-digit PIN.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect current PIN. Please try again.");
                }
            }
        });

        // Action listener for the cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Method to update PIN in the userDataList.
     *
     * @param cardNum The card number associated with the PIN.
     * @param newPin  The new PIN to be updated.
     */
    private void updatePinInFile(String cardNum, String newPin) {
        for (int i = 0; i < userDataList.size(); i++) {
            if (userDataList.get(i).getCardNumber().equals(cardNum)) {
                userDataList.get(i).setPin(newPin); // Update the pin in the userDataList
                break;
            }
        }
    }
}
