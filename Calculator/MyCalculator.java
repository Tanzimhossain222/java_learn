
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MyCalculator  implements ActionListener, WindowListener {

    Frame frame;
    Label label;
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;

    Button bAdd, bSub, bMult, bDiv, bMod, bCalc, bClr, bPts, bNeg, bBack;
    double result;
    double num1, num2, check;

    public MyCalculator() {
        frame = new Frame("MY CALCULATOR");
        label = new Label();
        label.setBackground(Color.LIGHT_GRAY);
        label.setBounds(50, 50, 260, 60);

        button1 = new Button("1");
        button1.setBounds(50, 340, 50, 50);

        button2 = new Button("2");
        button2.setBounds(120, 340, 50, 50);

        button3 = new Button("3");
        button3.setBounds(190, 340, 50, 50);

        button4 = new Button("4");
        button4.setBounds(50, 270, 50, 50);

        button5 = new Button("5");
        button5.setBounds(120, 270, 50, 50);

        button6 = new Button("6");
        button6.setBounds(190, 270, 50, 50);

        button7 = new Button("7");
        button7.setBounds(50, 200, 50, 50);

        button8 = new Button("8");
        button8.setBounds(120, 200, 50, 50);

        button9 = new Button("9");
        button9.setBounds(190, 200, 50, 50);

        button0 = new Button("0");
        button0.setBounds(120, 410, 50, 50);

        bNeg = new Button("+/-");
        bNeg.setBounds(50, 410, 50, 50);

        bPts = new Button(".");
        bPts.setBounds(190, 410, 50, 50);

        bBack = new Button("back");
        bBack.setBounds(120, 130, 50, 50);

        bAdd = new Button("+");
        bAdd.setBounds(260, 340, 50, 50);

        bSub = new Button("-");
        bSub.setBounds(260, 270, 50, 50);

        bMult = new Button("*");
        bMult.setBounds(260, 200, 50, 50);

        bDiv = new Button("/");
        bDiv.setBounds(260, 130, 50, 50);

        bMod = new Button("%");
        bMod.setBounds(190, 130, 50, 50);

        bCalc = new Button("=");
        bCalc.setBounds(245, 410, 65, 50);

        bClr = new Button("CE");
        bClr.setBounds(50, 130, 65, 50);
    }

    public void launchFrame() {
        frame.add(label);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.add(button6);
        frame.add(button7);
        frame.add(button8);
        frame.add(button9);
        frame.add(button0);
        frame.add(bNeg);
        frame.add(bPts);
        frame.add(bBack);
        frame.add(bAdd);
        frame.add(bSub);
        frame.add(bMult);
        frame.add(bDiv);
        frame.add(bMod);
        frame.add(bCalc);
        frame.add(bClr);

        frame.setSize(350, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.addWindowListener(this);
    }

    public void addActions() {
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        button9.addActionListener(this);
        button0.addActionListener(this);
        bNeg.addActionListener(this);
        bPts.addActionListener(this);
        bBack.addActionListener(this);
        bAdd.addActionListener(this);
        bSub.addActionListener(this);
        bMult.addActionListener(this);
        bDiv.addActionListener(this);
        bMod.addActionListener(this);
        bCalc.addActionListener(this);
        bClr.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String str = e.getActionCommand();

        if ((str.charAt(0) >= '0' && str.charAt(0) <= '9') || str.charAt(0) == '.') {

            if (!str.equals(".")) {
                label.setText(label.getText() + str);
            } else if (!label.getText().contains(".")) {
                label.setText(label.getText() + str);
            }
        }

        if (e.getSource() == bBack) {
            String s = label.getText();
            label.setText("");
            // first clear the label then add the string except the last character
            for (int i = 0; i < s.length() - 1; i++) {
                label.setText(label.getText() + s.charAt(i));
            }
        }

        if (e.getSource() == bAdd) {
            try {
                num1 = Double.parseDouble(label.getText());
                label.setText("");
                check = 1;
            } catch (NumberFormatException ex) {
                label.setText("Invalid Input");
            }
        }

        if (e.getSource() == bSub) {
            try {
                num1 = Double.parseDouble(label.getText());
                label.setText("");
                check = 2;
            } catch (NumberFormatException ex) {
                label.setText("Invalid Input");
            }
        }

        if (e.getSource() == bMult) {
            try {
                num1 = Double.parseDouble(label.getText());
                label.setText("");
                check = 3;
            } catch (NumberFormatException ex) {
                label.setText("Invalid Input");
            }
        }

        if (e.getSource() == bDiv) {
            try {
                num1 = Double.parseDouble(label.getText());
                label.setText("");
                check = 4;
            } catch (NumberFormatException ex) {
                label.setText("Invalid Input");
            }
        }

        if (e.getSource() == bMod) {
            try {
                num1 = Double.parseDouble(label.getText());
                label.setText("");
                check = 5;
            } catch (NumberFormatException ex) {
                label.setText("Invalid Input");
            }
        }

        if (e.getSource() == bCalc) {
            try {
                num2 = Double.parseDouble(label.getText());
            } catch (NumberFormatException ex) {
                label.setText("ENTER NUMBER FIRST ");
                return;
            }

            switch ((int) check) {
                case 1 ->
                    result = num1 + num2;
                case 2 ->
                    result = num1 - num2;
                case 3 ->
                    result = num1 * num2;
                case 4 -> {
                    if (num2 == 0) {
                        label.setText("Cannot Divide by Zero");
                        return;
                    }
                    result = num1 / num2;
                }
                case 5 ->
                    result = num1 % num2;
                default -> {
                    label.setText("Invalid Input");
                    return;
                }
            }

            label.setText(String.valueOf(result));

            //   if (check == 1) {
            //       result = num1 + num2;
            //   } else if (check == 2) {
            //       result = num1 - num2;
            //   } else if (check == 3) {
            //       result = num1 * num2;
            //   } else if (check == 4) {
            //       result = num1 / num2;
            //   } else if (check == 5) {
            //       result = num1 % num2;
            //   }
        }

        if (e.getSource() == bClr) {
            num1 = 0;
            num2 = 0;
            check = 0;
            result = 0;

            label.setText("");

        }

        if (e.getSource() == bNeg) {
            try {
                double temp = Double.parseDouble(label.getText());
                temp = -temp;
                label.setText(String.valueOf(temp));
            } catch (NumberFormatException ex) {
                label.setText("Invalid Input");
            }
        }

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}

    public static void main(String[] args) {
        MyCalculator mc = new MyCalculator();
        mc.launchFrame();
        mc.addActions();
    }

}
