import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The SignIn class represents the dialog window for signed-in users.
 */
class SignIn extends JDialog {
    private String cardNum;
    private List<UserData> userDataList;
    private JButton depositB, withdrawB, changePinB, fastWithdrawalB, requestBalanceB, requestStatementB,trasB, logOutB;
    private JLabel welcomeLabel;
    private JPanel buttonsPanel, welcomePanel;

    /**
     * Constructor for SignIn class.
     *
     * @param owner       The owner frame of the dialog.
     * @param cardNum     The card number of the signed-in user.
     * @param userDataList The list of user data.
     */
    public SignIn(Frame owner, String cardNum, List<UserData> userDataList) {
        super(owner, true);
        setTitle("Welcome to Your Account");
        setResizable(false);
        setSize(520, 400); // Adjusted size to accommodate new layout
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setIconImage(new ImageIcon("src/icon.png").getImage());
        this.cardNum = cardNum;
        this.userDataList = userDataList;
        initComponents();
    }

    /**
     * Initializes all the components of the SignIn dialog.
     */
    private void initComponents() {
        // Welcome label panel
        welcomePanel = new JPanel(new BorderLayout());
        welcomeLabel = new JLabel("Welcome, " + UserData.getData(cardNum, 4, userDataList), SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Buttons panel
        buttonsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        depositB = new JButton("Deposit");
        withdrawB = new JButton("Withdraw");
        changePinB = new JButton("Change PIN");
        fastWithdrawalB = new JButton("Fast Withdrawal");
        requestBalanceB = new JButton("Request Balance");
        requestStatementB = new JButton("Request Statement");
        trasB=new JButton("Transfer Money");
        logOutB = new JButton("Log Out");

        // Adding buttons to the panel
        buttonsPanel.add(depositB);
        buttonsPanel.add(withdrawB);
        buttonsPanel.add(changePinB);
        buttonsPanel.add(fastWithdrawalB);
        buttonsPanel.add(requestBalanceB);
        buttonsPanel.add(requestStatementB);
        buttonsPanel.add(trasB);
        buttonsPanel.add(logOutB);

        // Adding panels to the dialog
        add(welcomePanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.CENTER);

        depositB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Deposit d = new Deposit(((Frame) SignIn.this.getParent()), cardNum, userDataList);
                d.setVisible(true);
            }
        });
        withdrawB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Withdrawal w = new Withdrawal(((Frame) SignIn.this.getParent()), cardNum, userDataList);
                w.setVisible(true);
            }
        });
        requestBalanceB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Your current Amount is ₹" + UserData.getData(cardNum, 5, userDataList));
            }
        });
        changePinB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangePIN c = new ChangePIN(((Frame) SignIn.this.getParent()), cardNum, userDataList);
                c.setVisible(true);
            }
        });
        fastWithdrawalB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FastWithdrawal f = new FastWithdrawal(((Frame) SignIn.this.getParent()), cardNum, userDataList);
                f.setVisible(true);
            }
        });
        requestStatementB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GetStatement gs = new GetStatement(((Frame) SignIn.this.getParent()), cardNum, userDataList);
                gs.setVisible(true);
            }
        });
        trasB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TMoney t=new TMoney(((Frame) SignIn.this.getParent()), cardNum, userDataList);
                t.setVisible(true);
            }
        });
        logOutB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(SignIn.this, "Logging out, please wait a few seconds...");

                // Create a new thread to handle the logout delay
                Thread logoutThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // Set the thread priority to low
                            Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
                            // Wait for 5 seconds
                            Thread.sleep(5000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        } finally {
                            // Dispose the dialog in the Event Dispatch Thread
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    dispose();
                                }
                            });
                        }
                    }
                });

                // Start the thread
                logoutThread.start();
            }
        });
    }


}
