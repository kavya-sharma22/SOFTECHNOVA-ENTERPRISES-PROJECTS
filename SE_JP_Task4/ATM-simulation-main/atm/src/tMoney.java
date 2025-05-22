import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

class TMoney extends JDialog {
    private JTextField accNo, moneyT;
    private JButton transButton, cancelButton;
    private List<UserData> userDataList;
    private String cardNum;
    private Validate v;

    /**
     * Constructor to initialize the Change PIN dialog.
     *
     * @param owner        The owner frame of the dialog.
     * @param cardNum      The card number of the user.
     * @param userDataList The list of user data.
     */
    public TMoney(Frame owner, String cardNum, List<UserData> userDataList) {

        super(owner, "Transfer Money", true);
        setIconImage(new ImageIcon("src/icon.png").getImage());
        this.cardNum = cardNum;
        this.userDataList = userDataList;
        v = new Validate();

        setLayout(new GridLayout(4, 2, 10, 10));
        accNo = new JTextField();
        moneyT = new JTextField();
        transButton = new JButton("Transfer Money");
        cancelButton = new JButton("Cancel");

        add(new JLabel("Enter Receiver's Amount Number:"));
        add(accNo);
        add(new JLabel("Enter Money:"));
        add(moneyT);
        add(transButton);
        add(cancelButton);

        // Action listener for the change button
        transButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!v.isAccountNumber(accNo.getText()) && UserData.exitsAccNo(accNo.getText())) { // Fixed closing parenthesis
                    JOptionPane.showMessageDialog(null, "Please enter valid account number..");
                }
                if (!v.isAmount(moneyT.getText())) {
                    JOptionPane.showMessageDialog(null, "Please enter valid money number..");
                }
                double myOldBal = Double.parseDouble(UserData.getData(cardNum, 5, userDataList));
                double reqAmount = Double.parseDouble(moneyT.getText());
                String rCard = UserData.getData(accNo.getText(), userDataList);
                double reOldBal = Double.parseDouble(UserData.getData(rCard, 5, userDataList));
                double d = myOldBal - reqAmount;
                if (1000 >= d) {
                    JOptionPane.showMessageDialog(null, "You must contain at least 1000 rupees in your account..");
                } else {
                    double myNewBal = myOldBal - reqAmount;
                    double reNewBal = reOldBal + reqAmount;
                    String eCheckS = UserData.getData(cardNum, 14, userDataList);
                    String eCheckR = UserData.getData(rCard, 14, userDataList);
                    UserData.setData(cardNum, 5, Double.toString(myNewBal), userDataList);
                    UserData.setData(rCard, 5, Double.toString(reNewBal), userDataList);
                    recordTransaction(UserData.getData(cardNum, 15, userDataList),reqAmount,accNo.getText(),reqAmount);
                    if ("Yes".equals(eCheckS)) {
                        String msg1= Emsg.moneyTransferSuccess(UserData.getData(cardNum,4,userDataList),UserData.getData(cardNum,15,userDataList),UserData.getData(rCard,15,userDataList),reqAmount,myNewBal);
                        new EmailService().sendEmail(UserData.getData(cardNum, 3, userDataList), "Money Transfer Successful", msg1, null,true); // Fixed closing parenthesis
                    }
                    if ("Yes".equals(eCheckR)) {
                        String msg2= Emsg.moneyReciverSuccess(UserData.getData(rCard,4,userDataList),UserData.getData(rCard,15,userDataList),UserData.getData(cardNum,15,userDataList),reqAmount,reNewBal);
                        new EmailService().sendEmail(UserData.getData(rCard, 3, userDataList), "Money Recived Successful", msg2, null,true); // Fixed closing parenthesis
                    }
                    JOptionPane.showMessageDialog(null,"Money Transfer Successful");
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
    private void recordTransaction(String accNumS,double amountS,String accNumR,double amountR ) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("records.txt", true))) {
            writer.write("Account Number: " + accNumS + "  TO  " + accNumR +"\n");
            writer.write(  "Transfer" +" Time: " + java.time.LocalDateTime.now() + "\n");
            writer.write( "Transferred"+" Amount: " + amountS + " ₹\n\n");
            writer.write("Account Number: " + accNumR + "  FROM  " + accNumS +"\n");
            writer.write(  "Received" +" Time: " + java.time.LocalDateTime.now() + "\n");
            writer.write( "Received"+" Amount: " + amountR + " ₹\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
