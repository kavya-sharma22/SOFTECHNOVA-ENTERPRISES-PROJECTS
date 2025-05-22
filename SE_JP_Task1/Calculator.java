import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private StringBuilder expression;
    private double num1 = 0;
    private String operator = "";
    private boolean startNew = true;
    private boolean inputHasDecimal = false;

    public Calculator() {
        setTitle("Java Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        expression = new StringBuilder();

        // Buttons
        JPanel panel = new JPanel(new GridLayout(5, 4, 10, 10));
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "C", "+",
            "="
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 22));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if ("0123456789".contains(cmd)) {
            if (startNew || display.getText().equals("0")) {
                expression.setLength(0); // reset
                expression.append(cmd);
            } else {
                expression.append(cmd);
            }
            display.setText(expression.toString());
            startNew = false;
        } else if (cmd.equals(".")) {
            if (!inputHasDecimal) {
                expression.append(".");
                display.setText(expression.toString());
                inputHasDecimal = true;
                startNew = false;
            }
        } else if ("+-*/".contains(cmd)) {
            try {
                num1 = Double.parseDouble(expression.toString());
                operator = cmd;
                expression.append(" ").append(operator).append(" ");
                display.setText(expression.toString());
                inputHasDecimal = false;
                startNew = false;
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
        } else if (cmd.equals("=")) {
            try {
                String[] parts = expression.toString().split(" ");
                if (parts.length == 3) {
                    double num2 = Double.parseDouble(parts[2]);
                    double result = calculate(num1, num2, operator);
                    String resultStr = (inputHasDecimal || result % 1 != 0)
                            ? String.valueOf(result)
                            : String.valueOf((int) result);
                    String full = expression.toString() + " = " + resultStr;
                    display.setText(full);
                    expression.setLength(0);
                    expression.append(resultStr);
                    inputHasDecimal = result % 1 != 0;
                    startNew = true;
                }
            } catch (Exception ex) {
                display.setText("Error");
                expression.setLength(0);
                startNew = true;
            }
        } else if (cmd.equals("C")) {
            expression.setLength(0);
            display.setText("0");
            num1 = 0;
            operator = "";
            inputHasDecimal = false;
            startNew = true;
        }
    }

    private double calculate(double a, double b, String op) {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> b != 0 ? a / b : 0;
            default -> b;
        };
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
