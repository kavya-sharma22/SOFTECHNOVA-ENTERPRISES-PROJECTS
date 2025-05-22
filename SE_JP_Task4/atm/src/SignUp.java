import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class SignUp extends JDialog {
    private JPanel personalDetailsPanel, cardDetailsPanel;
    private JLabel banner, page1, page2;
    private JLabel nameL, fatherNameL, dobL, emailL, cityL, stateL, pinCodeL, addressL, genderL, maritalL;
    private JTextField nameI, fatherNameI, dobI, emailI, cityI, pinCodeI, stateI, addressI;
    private JRadioButton maleRadioButton, femaleRadioButton, otherRadioButton, marriedRadioButton, singleRadioButton, noToSayRadioButton;
    private JButton cancel, next, back, submit, cancel1;
    private JLabel cardNumLabel, pinNumLabel, startAmountL;
    private JTextField cardNumInput, pinNumInput, startAmountI;
    private JCheckBox eCheckL;
    private Set<String> registeredCardNumbers;
    private Validate v = new Validate();
    private String eCheck,accountNumber;
    private List<UserData> userDataList;
    public SignUp(Frame owner,List<UserData> userDataList) {
        super(owner, true);
        setTitle("NEW ACCOUNT APPLICATION FORM");
        setResizable(false);
        setSize(520, 720);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon("src/icon.png").getImage());
        setLocationRelativeTo(null);
        setLayout(null);
        this.userDataList = userDataList;
        initComponents();
    }

    private void initComponents() {
        registeredCardNumbers = fetchRegisteredCardNumbers();

        personalDetailsPanel = new JPanel();
        cardDetailsPanel = new JPanel();
        personalDetailsPanel.setSize(520, 720);
        personalDetailsPanel.setLayout(null);

        banner = new JLabel(" APPLICATION FORM ", SwingConstants.CENTER);
        banner.setFont(new Font("Verdana", Font.BOLD, 14));
        banner.setBounds(20, 20, 480, 30);

        page1 = new JLabel("Page 1: Personal Details", SwingConstants.CENTER);
        page1.setBounds(20, 60, 480, 20);

        nameL = new JLabel("Name:");
        nameL.setBounds(20, 100, 120, 25);
        nameI = new JTextField();
        nameI.setBounds(150, 100, 320, 25);

        fatherNameL = new JLabel("Father's Name:");
        fatherNameL.setBounds(20, 140, 120, 25);
        fatherNameI = new JTextField();
        fatherNameI.setBounds(150, 140, 320, 25);

        dobL = new JLabel("Date of Birth (yyyy-mm-dd):");
        dobL.setBounds(20, 180, 180, 25);
        dobI = new JTextField();
        dobI.setBounds(200, 180, 270, 25);

        genderL = new JLabel("Gender:");
        genderL.setBounds(20, 220, 120, 25);
        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setBounds(150, 220, 80, 25);
        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setBounds(250, 220, 100, 25);
        otherRadioButton = new JRadioButton("Other");
        otherRadioButton.setBounds(360, 220, 80, 25);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderGroup.add(otherRadioButton);

        maritalL = new JLabel("Marital Status:");
        maritalL.setBounds(20, 260, 120, 25);
        singleRadioButton = new JRadioButton("Single");
        singleRadioButton.setBounds(150, 260, 80, 25);
        marriedRadioButton = new JRadioButton("Married");
        marriedRadioButton.setBounds(250, 260, 100, 25);
        noToSayRadioButton = new JRadioButton("Prefer not to say");
        noToSayRadioButton.setBounds(360, 260, 150, 25);
        ButtonGroup maritalGroup = new ButtonGroup();
        maritalGroup.add(singleRadioButton);
        maritalGroup.add(marriedRadioButton);
        maritalGroup.add(noToSayRadioButton);

        emailL = new JLabel("Email:");
        emailL.setBounds(20, 300, 120, 25);
        emailI = new JTextField();
        emailI.setBounds(150, 300, 320, 25);

        cityL = new JLabel("City:");
        cityL.setBounds(20, 340, 120, 25);
        cityI = new JTextField();
        cityI.setBounds(150, 340, 320, 25);

        pinCodeL = new JLabel("Pin Code:");
        pinCodeL.setBounds(20, 380, 120, 25);
        pinCodeI = new JTextField();
        pinCodeI.setBounds(150, 380, 320, 25);

        stateL = new JLabel("State:");
        stateL.setBounds(20, 420, 120, 25);
        stateI = new JTextField();
        stateI.setBounds(150, 420, 320, 25);

        addressL = new JLabel("Address:");
        addressL.setBounds(20, 460, 120, 25);
        addressI = new JTextField();
        addressI.setBounds(150, 460, 320, 25);

        cancel = new JButton("Cancel");
        cancel.setBounds(260, 500, 100, 30);

        next = new JButton("Next");
        next.setBounds(370, 500, 100, 30);

        personalDetailsPanel.add(banner);
        personalDetailsPanel.add(page1);
        personalDetailsPanel.add(nameL);
        personalDetailsPanel.add(nameI);
        personalDetailsPanel.add(fatherNameL);
        personalDetailsPanel.add(fatherNameI);
        personalDetailsPanel.add(dobL);
        personalDetailsPanel.add(dobI);
        personalDetailsPanel.add(genderL);
        personalDetailsPanel.add(maleRadioButton);
        personalDetailsPanel.add(femaleRadioButton);
        personalDetailsPanel.add(otherRadioButton);
        personalDetailsPanel.add(maritalL);
        personalDetailsPanel.add(singleRadioButton);
        personalDetailsPanel.add(marriedRadioButton);
        personalDetailsPanel.add(noToSayRadioButton);
        personalDetailsPanel.add(emailL);
        personalDetailsPanel.add(emailI);
        personalDetailsPanel.add(cityL);
        personalDetailsPanel.add(cityI);
        personalDetailsPanel.add(pinCodeL);
        personalDetailsPanel.add(pinCodeI);
        personalDetailsPanel.add(stateL);
        personalDetailsPanel.add(stateI);
        personalDetailsPanel.add(addressL);
        personalDetailsPanel.add(addressI);
        personalDetailsPanel.add(cancel);
        personalDetailsPanel.add(next);

        add(personalDetailsPanel);

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Are You Sure ? ", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean a = verify1(nameI.getText(), fatherNameI.getText(), dobI.getText(), emailI.getText(), cityI.getText(), stateI.getText(), pinCodeI.getText(), addressI.getText(), v.getSelectedRadioButtonText(genderGroup), v.getSelectedRadioButtonText(maritalGroup));
                if (a) {
                    personalDetailsPanel.setVisible(false);
                    add(cardDetailsPanel);
                    cardDetailsPanel.setVisible(true);
                }
            }
        });

        // Page 2
        cardDetailsPanel.setSize(520, 720);
        cardDetailsPanel.setLayout(null);
        page2 = new JLabel("Page 2: Card Details", SwingConstants.CENTER);
        page2.setBounds(20, 60, 480, 20);
        cardDetailsPanel.add(page2);

        cardNumLabel = new JLabel("Choose Your Card No. (12-digit):");
        cardNumLabel.setBounds(20, 100, 220, 25);
        cardDetailsPanel.add(cardNumLabel);

        cardNumInput = new JTextField(12);
        cardNumInput.setBounds(250, 100, 220, 25);
        cardDetailsPanel.add(cardNumInput);

        pinNumLabel = new JLabel("Choose Your PIN No. (6-digit):");
        pinNumLabel.setBounds(20, 150, 200, 25);
        cardDetailsPanel.add(pinNumLabel);

        pinNumInput = new JPasswordField(6);
        pinNumInput.setBounds(250, 150, 220, 25);
        cardDetailsPanel.add(pinNumInput);

        startAmountL = new JLabel("Start Amount:");
        startAmountL.setBounds(20, 200, 120, 25);
        cardDetailsPanel.add(startAmountL);

        startAmountI = new JTextField();
        startAmountI.setBounds(250, 200, 220, 25);
        cardDetailsPanel.add(startAmountI);

        eCheckL = new JCheckBox("Stay Updated With Email...");
        eCheckL.setBounds(150, 240, 320, 25);
        cardDetailsPanel.add(eCheckL);

        cancel1 = new JButton("Cancel");
        cancel1.setBounds(150, 270, 100, 30);
        cardDetailsPanel.add(cancel1);

        back = new JButton("Back");
        back.setBounds(260, 270, 100, 30);
        cardDetailsPanel.add(back);

        submit = new JButton("Submit");
        submit.setBounds(370, 270, 100, 30);
        cardDetailsPanel.add(submit);

        cancel1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Are You Sure ? ", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardDetailsPanel.setVisible(false);
                personalDetailsPanel.setVisible(true);
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((verify2(cardNumInput.getText(), pinNumInput.getText(), startAmountI.getText(), eCheckL.isSelected()))) {
                    // Generate account number
                    accountNumber = FileHandler.generateAccNum();
                    if(accountNumber!=null){
                        System.out.println("Done");
                    }
                    JOptionPane.showMessageDialog(null, "Registration successful...");
                    dispose();
                    FileHandler.writeUserDataToFile(nameI.getText(), fatherNameI.getText(), dobI.getText(), emailI.getText(), cityI.getText(), stateI.getText(), pinCodeI.getText(), addressI.getText(), v.getSelectedRadioButtonText(genderGroup), v.getSelectedRadioButtonText(maritalGroup), cardNumInput.getText(), pinNumInput.getText(), startAmountI.getText(), eCheck,accountNumber);
                    registeredCardNumbers.add(cardNumInput.getText());

                    // Send email with the correct account number

                    String msg=Emsg.registrationSuccess(nameI.getText(),accountNumber,cardNumInput.getText(),pinNumInput.getText());
                    if (eCheckL.isSelected()) {
                        new EmailService().sendEmail(emailI.getText(), "Successfully Registered", msg, null,true);
                    }
                    dispose(); // Close the current window

                }else {}
            }
        });

    }

    private Set<String> fetchRegisteredCardNumbers() {
        Set<String> cardNumbers = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("user_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Card Number:")) {
                    cardNumbers.add(line.substring(line.indexOf(":") + 2).trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching registered card numbers.");
        }
        return cardNumbers;
    }

    boolean verify1(String name, String fatherName, String dob, String email, String city, String state, String pinCode, String address, String gender, String maritalState) {
        if (name.isEmpty() || fatherName.isEmpty() || dob.isEmpty() || email.isEmpty() || city.isEmpty() || state.isEmpty() || pinCode.isEmpty() || address.isEmpty() || gender.isEmpty() || maritalState.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Make Sure You Are Not Leaving Any Field Empty! ");
            return false;
        }
        if (!v.isPlainText(name) || !v.isPlainText(fatherName) || !v.isPlainText(city) || !v.isPlainText(state)) {
            JOptionPane.showMessageDialog(null, "Check Your Name, Father's Name, City, State Fields. They Can Only Contain [A-Z], [a-z], and White Spaces.");
            return false;
        }
        if (!v.isDate(dob)) {
            JOptionPane.showMessageDialog(null, "Make Sure You Entered Date Of Birth In Correct Format (yyyy-mm-dd)!");
            return false;
        }
        if (!v.isEmail(email)) {
            JOptionPane.showMessageDialog(null, "Please Enter a Valid Email!");
            return false;
        }
        if (!v.isPin(pinCode)) {
            JOptionPane.showMessageDialog(null, "Please Enter a Valid PIN Code!");
            return false;
        }
        return true;
    }

    boolean verify2(String cardNum, String pinNum, String amount, boolean eCheck) {
        if (!v.isCard(cardNum)) {
            JOptionPane.showMessageDialog(null, "Please Choose a Valid Card Number!");
            return false;
        }

        if (userDataList.stream().anyMatch(userData -> userData.getCardNumber().equals(cardNum))) {
            JOptionPane.showMessageDialog(null, "Card number already registered. Please choose a different card number.");
            return false;
        }

        if (!v.isPin(pinNum)) {
            JOptionPane.showMessageDialog(null, "Please Choose a Valid PIN Number!");
            return false;
        }

        if (v.isAmount(amount)) {
            if (!v.isAmountNeg(amount)) {
                JOptionPane.showMessageDialog(null, "Starting Amount Should Not Be Negative...");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please Enter a Valid Starting Amount !");
            return false;
        }

        if (!(eCheck)) {
            int i = JOptionPane.showConfirmDialog(null, "Are You Sure You Not Want To Email Services ? ", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (i == JOptionPane.YES_OPTION) {
                this.eCheck = "No";
                return true;
            } else {
                return false;
            }
        } else {
            this.eCheck = "Yes";
            return v.otpVerify(emailI.getText(), (Frame) SignUp.this.getParent());
        }
    }

}
