import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class AdvancedCalculator extends JFrame implements ActionListener, KeyListener {
    private JTextField display;
    private JTextArea historyArea;
    private StringBuilder input;
    private double memory = 0.0;

    public AdvancedCalculator() {
        setTitle("Advanced Java Calculator");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        input = new StringBuilder();

        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        display.addKeyListener(this);
        add(display, BorderLayout.NORTH);

        historyArea = new JTextArea(5, 20);
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(historyArea);
        add(scroll, BorderLayout.SOUTH);

        String[] buttons = {
                "7", "8", "9", "/", "MC",
                "4", "5", "6", "*", "MR",
                "1", "2", "3", "-", "M+",
                "0", ".", "=", "+", "M-",
                "C", "CE", "DEL", "±", "%",
                "√", "x²", "1/x", "^", "(" , ")"
        };

        JPanel panel = new JPanel(new GridLayout(7, 5, 5, 5));
        for (String b : buttons) {
            JButton btn = new JButton(b);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setFocusable(true);
        addKeyListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "=":
                try {
                    double result = evaluate(input.toString());
                    String resultStr = (result % 1 == 0) ? String.valueOf((int) result) : String.valueOf(result);
                    historyArea.append(input + " = " + resultStr + "\n");
                    display.setText(resultStr);
                    input.setLength(0);
                    input.append(resultStr);
                } catch (Exception ex) {
                    display.setText("Error");
                }
                break;
            case "C":
                input.setLength(0);
                display.setText("0");
                break;
            case "CE":
                if (input.length() > 0) {
                    int lastOp = Math.max(input.lastIndexOf("+"),
                            Math.max(input.lastIndexOf("-"),
                            Math.max(input.lastIndexOf("*"),
                            Math.max(input.lastIndexOf("/"), input.lastIndexOf("^")))));
                    input.delete(lastOp + 1, input.length());
                    display.setText(input.toString());
                }
                break;
            case "DEL":
                if (input.length() > 0) {
                    input.deleteCharAt(input.length() - 1);
                    display.setText(input.length() == 0 ? "0" : input.toString());
                }
                break;
            case "±":
                if (input.length() > 0 && Character.isDigit(input.charAt(0))) {
                    if (input.charAt(0) == '-') input.deleteCharAt(0);
                    else input.insert(0, '-');
                    display.setText(input.toString());
                }
                break;
            case "√":
                try {
                    double val = evaluate(input.toString());
                    double res = Math.sqrt(val);
                    input.setLength(0);
                    input.append(res);
                    display.setText(String.valueOf(res));
                } catch (Exception ex) {
                    display.setText("Error");
                }
                break;
            case "x²":
                try {
                    double val = evaluate(input.toString());
                    double res = val * val;
                    input.setLength(0);
                    input.append(res);
                    display.setText(String.valueOf(res));
                } catch (Exception ex) {
                    display.setText("Error");
                }
                break;
            case "1/x":
                try {
                    double val = evaluate(input.toString());
                    if (val == 0) throw new ArithmeticException();
                    double res = 1 / val;
                    input.setLength(0);
                    input.append(res);
                    display.setText(String.valueOf(res));
                } catch (Exception ex) {
                    display.setText("Error");
                }
                break;
            case "%":
                try {
                    double val = evaluate(input.toString());
                    double res = val / 100;
                    input.setLength(0);
                    input.append(res);
                    display.setText(String.valueOf(res));
                } catch (Exception ex) {
                    display.setText("Error");
                }
                break;
            case "M+":
                memory += evaluateSilent();
                break;
            case "M-":
                memory -= evaluateSilent();
                break;
            case "MR":
                input.setLength(0);
                input.append(memory);
                display.setText(String.valueOf(memory));
                break;
            case "MC":
                memory = 0;
                break;
            default:
                input.append(cmd);
                display.setText(input.toString());
        }
    }

    private double evaluateSilent() {
        try {
            return evaluate(input.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    // Evaluate expressions with + - * / ^ and parentheses
    private double evaluate(String expr) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < expr.length()) ? expr.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expr.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                double x = parsePower();
                return x;
            }

            double parsePower() {
                double x = parsePrimary();
                if (eat('^')) x = Math.pow(x, parsePower());
                return x;
            }

            double parsePrimary() {
                if (eat('+')) return parsePrimary();
                if (eat('-')) return -parsePrimary();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expr.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                return x;
            }
        }.parse();
    }

    // Keyboard support
    @Override
    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();
        if ((key >= '0' && key <= '9') || "+-*/().^".indexOf(key) >= 0) {
            input.append(key);
            display.setText(input.toString());
        } else if (key == '\n' || key == '=') {
            actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "="));
        } else if (key == '\b') {
            actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "DEL"));
        }
    }

    @Override public void keyPressed(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdvancedCalculator::new);
    }
}
