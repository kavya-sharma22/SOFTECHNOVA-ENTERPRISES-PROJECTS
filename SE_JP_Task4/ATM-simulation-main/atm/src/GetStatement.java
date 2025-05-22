import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * GetStatement class represents a dialog for obtaining account statements.
 */
class GetStatement extends JDialog {

    private String cardNum;
    private List<UserData> userDataList;
    private JRadioButton emailOption, localOption;
    private JButton getStatementButton, cancelButton;
    private ButtonGroup optionGroup;
    private String accNum;

    /**
     * Constructor for GetStatement dialog.
     *
     * @param owner       The parent frame.
     * @param cardNum     Card number of the user.
     * @param userDataList List of user data.
     */
    public GetStatement(Frame owner, String cardNum, List<UserData> userDataList) {
        super(owner, "Get Statement", true);
        setIconImage(new ImageIcon("src/icon.png").getImage());
        this.cardNum = cardNum;
        this.userDataList = userDataList;
        this.accNum = UserData.getData(cardNum, 15, userDataList);

        setLayout(new GridLayout(3, 1, 10, 10));

        JPanel optionPanel = new JPanel(new FlowLayout());
        emailOption = new JRadioButton("Send via Email");
        localOption = new JRadioButton("Save Locally as Text");
        optionGroup = new ButtonGroup();
        optionGroup.add(emailOption);
        optionGroup.add(localOption);
        optionPanel.add(emailOption);
        optionPanel.add(localOption);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        getStatementButton = new JButton("Get Statement");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(getStatementButton);
        buttonPanel.add(cancelButton);

        add(new JLabel("Select an option:"));
        add(optionPanel);
        add(buttonPanel);

        getStatementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (emailOption.isSelected()) {
                    sendStatementByEmail();
                } else if (localOption.isSelected()) {
                    saveStatementLocally();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an option.");
                }
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Method to send the account statement by email.
     */
    private void sendStatementByEmail() {
        String email = UserData.getData(cardNum, 3, userDataList);
        String msg=Emsg.accountStatement(UserData.getData(cardNum,4,userDataList),UserData.getData(cardNum,15,userDataList));

        List<String> attachments = new ArrayList<>();
        if(saveStatementTemp()) {
            String tempFileName = "temp_statement_" + cardNum + "_" + LocalDate.now() + ".txt";
            File tempFile = new File(tempFileName);
            if(tempFile.exists()) {
                attachments.add(tempFileName);
                EmailService emailService = new EmailService();
                emailService.sendEmail(email, "Account Statement", msg, attachments,true);
                JOptionPane.showMessageDialog(null, "Account statement sent to your email address.");
                Timer timer = new Timer(15000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!fileDel()) { // If fileDel() returns false
                            ((Timer)e.getSource()).stop(); // Stop the Timer
                            System.out.println("Failed to delete the file. Timer stopped.");
                        }
                    }
                });
                timer.start();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to generate the account statement file.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Failed to save the temporary account statement.");
        }
    }

    /**
     * Method to save the account statement locally as a text file.
     */
    private void saveStatementLocally() {
        String fileName = "account_statement_" + cardNum + "_" + LocalDate.now() + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            try (BufferedReader reader = new BufferedReader(new FileReader("records.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Account Number: " + accNum)) {
                        writer.write(line + "\n");
                        for (int i = 0; i < 3; i++) {
                            writer.write(reader.readLine() + "\n");
                        }
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Account statement saved as " + fileName + ".");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to save the temporary account statement.
     *
     * @return true if the file is saved successfully, false otherwise.
     */
    private boolean saveStatementTemp() {
        String fileName = "temp_statement_" + cardNum + "_" + LocalDate.now() + ".txt";
        boolean isFileSaved = false;
        try (FileWriter writer = new FileWriter(fileName)) {
            try (BufferedReader reader = new BufferedReader(new FileReader("records.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Account Number: " + accNum)) {
                        writer.write(line + "\n");
                        for (int i = 0; i < 3; i++) {
                            writer.write(reader.readLine() + "\n");
                        }
                        isFileSaved = true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return isFileSaved;
    }

    /**
     * Method to delete the temporary file.
     *
     * @return true if the file is deleted successfully, false otherwise.
     */
    private boolean fileDel(){
        String fileName = "temp_statement_" + cardNum + "_" + LocalDate.now() + ".txt";
        File f = new File(fileName);
        if(f.delete()) {
            System.out.println("Temporary file deleted successfully.");
            return true;
        } else {
            System.out.println("Failed to delete the temporary file.");
            return false;
        }
    }

}
