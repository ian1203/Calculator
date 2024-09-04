import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class Calculadora {

    private JTextField textField1;
    private JPanel Calculadora;
    private JButton ACButton;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton a7Button;
    private JButton a4Button;
    private JButton a1Button;
    private JButton exitButton;
    private JButton button11;
    private JButton a8Button;
    private JButton a5Button;
    private JButton a2Button;
    private JButton a0Button;
    private JButton button16;
    private JButton a9Button;
    private JButton a6Button;
    private JButton a3Button;
    private JButton button20;
    private JButton ANSButton;

    double resultado;
    StringBuilder equation = new StringBuilder();

    public Calculadora() {
        ACButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                equation.setLength(0);
            }
        });

        ActionListener numberActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                textField1.setText(textField1.getText() + button.getText() );
                equation.append(button.getText());
            }
        };

        a7Button.addActionListener(numberActionListener);
        a8Button.addActionListener(numberActionListener);
        a9Button.addActionListener(numberActionListener);
        a4Button.addActionListener(numberActionListener);
        a5Button.addActionListener(numberActionListener);
        a6Button.addActionListener(numberActionListener);
        a1Button.addActionListener(numberActionListener);
        a2Button.addActionListener(numberActionListener);
        a3Button.addActionListener(numberActionListener);
        a0Button.addActionListener(numberActionListener);

        ActionListener operatorActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                equation.append(button.getText());
                textField1.setText(textField1.getText() + button.getText() );
            }
        };

        button11.addActionListener(operatorActionListener); // Suma
        button2.addActionListener(operatorActionListener); // Resta
        button3.addActionListener(operatorActionListener); // División
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation.append("*");
                textField1.setText(textField1.getText() + "*");
            }
        }); // Multiplicación
        button16.addActionListener(operatorActionListener); // Modulo


        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().length() > 0) {
                    String currentText = textField1.getText();
                    textField1.setText(currentText.substring(0, currentText.length() - 1));
                    equation.deleteCharAt(equation.length() - 1);
                }

            }
        });

        button20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField1.getText().contains(".")) {
                    textField1.setText(textField1.getText() + button20.getText());
                    equation.append(button20.getText());
                }
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (equation.length() > 0) {
                    String[] elements = equation.toString().split("(?<=[-+/*%])|(?=[-+/*%])");
                    double tempResult = 0;
                    char operator = '+';
                    for (String element : elements) {
                        if (element.equals("+") || element.equals("-") || element.equals("*") || element.equals("/") || element.equals("%")) {
                            operator = element.charAt(0);
                        } else {
                            double num = Double.parseDouble(element);
                            switch (operator) {
                                case '+':
                                    tempResult += num;
                                    break;
                                case '-':
                                    tempResult -= num;
                                    break;
                                case '*':
                                    tempResult *= num;
                                    break;
                                case '/':
                                    if (num != 0)
                                        tempResult /= num;
                                    else {
                                        JOptionPane.showMessageDialog(null, "Error: 0/0 está indeterminado.");
                                        textField1.setText("");
                                        equation.setLength(0);
                                        return;
                                    }
                                    break;
                                case '%':
                                    tempResult %= num;
                                    break;
                            }
                        }
                    }
                    resultado = tempResult;
                    textField1.setText(textField1.getText() + "=" + String.valueOf(resultado));
                }
            }
        });


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane adiosPane = new JOptionPane("Adiós", JOptionPane.INFORMATION_MESSAGE);
                JDialog adiosDialog = adiosPane.createDialog("Mensaje");
                adiosDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                Timer timer = new Timer(3000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        adiosDialog.dispose();
                        System.exit(0);
                    }
                });
                timer.setRepeats(false);
                timer.start();
                adiosDialog.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculadora");
        frame.setSize(700, 300);
        frame.setContentPane(new Calculadora().Calculadora);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
