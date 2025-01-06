import java.awt.*;
import java.awt.event.*;

public class BoarderExample implements WindowListener {
    private Frame f;
    private Button b1, b2, b3, b4, b5;

    public BoarderExample() {
        f = new Frame("Flow Layout");
        b1 = new Button("Button 1");
        b2 = new Button("Button 2");
        b3 = new Button("Button 3");
        b4 = new Button("Button 4");
        b5 = new Button("Button 5");
    }

    public void launchFrame() {
        f.setLayout(new BorderLayout());

        f.add(b1, BorderLayout.NORTH);
        f.add(b2, BorderLayout.SOUTH);
        f.add(b3, BorderLayout.WEST);
        f.add(b4, BorderLayout.EAST);
        f.add(b5, BorderLayout.CENTER);
        f.addWindowListener(this);
        f.setSize(250, 150);
        f.setBackground(Color.red);
        f.setVisible(true);
    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public static void main(String args[]) {
        BoarderExample be = new BoarderExample();
        be.launchFrame();
    }
  
}
