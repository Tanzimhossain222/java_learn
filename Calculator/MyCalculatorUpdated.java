
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

enum Operation {
    ADD, SUBTRACT, MULTIPLY, DIVIDE, MODULO, NONE
}

public class MyCalculatorUpdated implements ActionListener, WindowListener {

    private final Frame frame;
    private final Label label;
    private final Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;

    private final Button bAdd, bSub, bMult, bDiv, bMod, bCalc, bClr, bPts, bNeg, bBack;
    private double result;
    private double num1, num2;

    private Operation currentOperation = Operation.NONE;

    private Button createButton(String label, int x, int y) {
        Button button = new Button(label);
        button.setBounds(x, y, 50, 50);
        return button;
    }

    public MyCalculatorUpdated() {
        frame = new Frame("MY CALCULATOR");
        label = new Label();
        label.setBackground(Color.LIGHT_GRAY);
        label.setBounds(50, 50, 260, 60);

        button1 = createButton("1", 50, 340);
        button2 = createButton("2", 120, 340);
        button3 = createButton("3", 190, 340);
        button4 = createButton("4", 50, 270);
        button5 = createButton("5", 120, 270);
        button6 = createButton("6", 190, 270);
        button7 = createButton("7", 50, 200);
        button8 = createButton("8", 120, 200);
        button9 = createButton("9", 190, 200);
        button0 = createButton("0", 120, 410);

        bNeg = createButton("+/-", 50, 410);
        bPts = createButton(".", 190, 410);
        bBack = createButton("back", 120, 130);
        bAdd = createButton("+", 260, 340);
        bSub = createButton("-", 260, 270);
        bMult = createButton("*", 260, 200);
        bDiv = createButton("/", 260, 130);
        bMod = createButton("%", 190, 130);
        bCalc = createButton("=", 245, 410);
        bClr = createButton("CE", 50, 130);
    }

    private void addButtonsToFrame(Button... buttons) {
        for (Button button : buttons) {
            frame.add(button);
        }
    }

    public void launchFrame() {
        frame.add(label);

        addButtonsToFrame(button1, button2, button3, button4, button5, button6, button7, button8, button9, button0,
                bNeg, bPts, bBack, bAdd, bSub, bMult, bDiv, bMod, bCalc, bClr);

        frame.setSize(350, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.addWindowListener(this);
    }

    private void addActionListeners(Button... buttons) {
        for (Button button : buttons) {
            button.addActionListener(this);
        }
    }

    public void addActions() {
        addActionListeners(button1, button2, button3, button4, button5, button6, button7, button8, button9, button0,
                bNeg, bPts, bBack, bAdd, bSub, bMult, bDiv, bMod, bCalc, bClr);
    }

    private void handleOperation(Operation operation) {
        try {
            num1 = Double.parseDouble(label.getText());
            label.setText("");
            currentOperation = operation;
        } catch (NumberFormatException ex) {
            label.setText("Invalid Input");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();

        if ((str.charAt(0) >= '0' && str.charAt(0) <= '9') || str.charAt(0) == '.') {
            handleNumberInput(str);
        } else if (e.getSource() == bBack) {
            handleBackspace();
        } else if (e.getSource() == bAdd) {
            handleOperation(Operation.ADD);
        } else if (e.getSource() == bSub) {
            handleOperation(Operation.SUBTRACT);
        } else if (e.getSource() == bMult) {
            handleOperation(Operation.MULTIPLY);
        } else if (e.getSource() == bDiv) {
            handleOperation(Operation.DIVIDE);
        } else if (e.getSource() == bMod) {
            handleOperation(Operation.MODULO);
        } else if (e.getSource() == bCalc) {
            handleCalculation();
        } else if (e.getSource() == bClr) {
            handleClear();
        } else if (e.getSource() == bNeg) {
            handleNegation();
        }
    }

    private void handleNumberInput(String str) {
        if (!str.equals(".")) {
            label.setText(label.getText() + str);
        } else if (!label.getText().contains(".")) {
            label.setText(label.getText() + str);
        }
    }

    private void handleBackspace() {
        String s = label.getText();
        label.setText("");
        for (int i = 0; i < s.length() - 1; i++) {
            label.setText(label.getText() + s.charAt(i));
        }
    }

    private void handleCalculation() {
        try {
            num2 = Double.parseDouble(label.getText());
        } catch (NumberFormatException ex) {
            label.setText("ENTER NUMBER FIRST ");
            return;
        }

        switch (currentOperation) {
            case ADD ->
                result = num1 + num2;
            case SUBTRACT ->
                result = num1 - num2;
            case MULTIPLY ->
                result = num1 * num2;
            case DIVIDE -> {
                if (num2 == 0) {
                    label.setText("Cannot Divide by Zero");
                    return;
                }
                result = num1 / num2;
            }
            case MODULO ->
                result = num1 % num2;
            default -> {
                label.setText("Invalid Input");
                return;
            }
        }

        label.setText(String.valueOf(result));
    }

    private void handleClear() {
        num1 = 0;
        num2 = 0;
        currentOperation = Operation.NONE;
        result = 0;
        label.setText("");
    }

    private void handleNegation() {
        try {
            double temp = Double.parseDouble(label.getText());
            temp = -temp;
            label.setText(String.valueOf(temp));
        } catch (NumberFormatException ex) {
            label.setText("Invalid Input");
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    public static void main(String[] args) {
        MyCalculator mc = new MyCalculator();
        mc.launchFrame();
        mc.addActions();
    }

}
