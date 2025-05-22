import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * FastWithdrawal class represents a dialog for fast withdrawal functionality.
 */
public class FastWithdrawal extends JDialog {

    // Buttons for withdrawal
    private JButton button100, button200, button500, button1000, button2000, button5000, button10000, cancelButton;

    // User data list and card number
    private List<UserData> userDataList;
    private String cardNum;

    // Validator
    private Validate v;

    /**
     * Constructor to initialize the Fast Withdrawal dialog.
     *
     * @param owner       The owner frame of the dialog.
     * @param cardNum     The card number of the user.
     * @param userDataList The list of user data.
     */
    public FastWithdrawal(Frame owner, String cardNum, List<UserData> userDataList) {
        super(owner, "Fast Withdrawal", true);
        setResizable(false);
        setSize(520, 400); // Adjusted size to accommodate new layout
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setIconImage(new ImageIcon("src/icon.png").getImage());
        this.cardNum = cardNum;
        this.userDataList = userDataList;
        v = new Validate(); // Instantiating Validator
        initComponents(); // Initializing components
    }

    // Initialize components
    private void initComponents() {
        // Buttons panel
        JPanel buttonsPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initializing buttons
        button100 = new JButton("₹100");
        button200 = new JButton("₹200");
        button500 = new JButton("₹500");
        button1000 = new JButton("₹1000");
        button2000 = new JButton("₹2000");
        button5000 = new JButton("₹5000");
        button10000 = new JButton("₹10000");
        cancelButton = new JButton("Cancel");

        // Adding buttons to the panel
        buttonsPanel.add(button100);
        buttonsPanel.add(button200);
        buttonsPanel.add(button500);
        buttonsPanel.add(button1000);
        buttonsPanel.add(button2000);
        buttonsPanel.add(button5000);
        buttonsPanel.add(button10000);
        buttonsPanel.add(cancelButton);

        // Add buttons panel to the dialog
        add(buttonsPanel, BorderLayout.CENTER);

        // Action listeners for withdrawal buttons
        button100.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processWithdrawal(100); // Process withdrawal of ₹100
            }
        });

        button200.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processWithdrawal(200); // Process withdrawal of ₹200
            }
        });

        button500.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processWithdrawal(500); // Process withdrawal of ₹500
            }
        });

        button1000.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processWithdrawal(1000); // Process withdrawal of ₹1000
            }
        });

        button2000.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processWithdrawal(2000); // Process withdrawal of ₹2000
            }
        });

        button5000.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processWithdrawal(5000); // Process withdrawal of ₹5000
            }
        });

        button10000.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processWithdrawal(10000); // Process withdrawal of ₹10000
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close dialog when cancel button is clicked
            }
        });
    }

    // Method to process withdrawal
    private void processWithdrawal(double withdrawalAmount) {
        String accNum = "";
        for (UserData userData : userDataList) {
            if (userData.getCardNumber().equals(cardNum)) {
                accNum = userData.getAccNum(); // Get account number associated with the card
                break;
            }
        }

        double oldBalance = Double.parseDouble(UserData.getData(cardNum, 5, userDataList));
        double newBalance = oldBalance - withdrawalAmount;

        // Check if withdrawal amount is valid and balance is sufficient
        if (withdrawalAmount >= 100 && withdrawalAmount <= 10000 && newBalance >= 1000) {
            UserData.setData(cardNum, 5, Double.toString(newBalance), userDataList); // Update balance

            // Record the withdrawal in the file
            FileHandler.recordTransaction(accNum, withdrawalAmount, "Fast Withdrawal");

            JOptionPane.showMessageDialog(null, "Please collect your money...");

            // Send email notification if enabled
            if ("Yes".equals(UserData.getData(cardNum, 14, userDataList))) {
                String msg=Emsg.moneyWithdrawn(UserData.getData(cardNum, 4, userDataList),Double.toString(withdrawalAmount),UserData.getData(cardNum, 15, userDataList),Double.toString(newBalance));
                new EmailService().sendEmail(UserData.getData(cardNum, 3, userDataList), "Withdrawal Confirmation", msg, null,true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Withdrawal amount must be between 100₹ and 10,000₹, and your balance should remain above 1000₹.");
        }
    }
}
