
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class EventHandle implements WindowListener, ActionListener {

    private Frame f;
    private Button b1, b2, b3;
    private TextField tf;

    public EventHandle() {
        f = new Frame("Button Handle");
        b1 = new Button("YES");
        b1.setActionCommand("yes button");

        b2 = new Button("NO");
        b2.setActionCommand("no button");

        b3 = new Button("Clear");
        b3.setActionCommand("clear button");

        tf = new TextField(30);
    }

    public void launchFrame() {
        b1.addActionListener(this);
        b1.setForeground(Color.white);
        b1.setBackground(Color.blue);

        b2.addActionListener(this);
        b2.setForeground(Color.red);
        b2.setBackground(Color.yellow);

        b3.addActionListener(this);
        b3.setForeground(Color.blue);
        b3.setBackground(Color.yellow);

        tf.setForeground(Color.blue);
        tf.setBackground(Color.white);

        f.setLayout(new FlowLayout());
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(tf);
        f.addWindowListener(this);
        f.setSize(250, 150);
        f.setBackground(Color.red);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String str;

        if (e.getActionCommand() == "yes button") {
            str = "You press YES button";
            tf.setText(str);
        }
        if (e.getActionCommand() == "no button") {
            str = "You press NO button";
            tf.setText(str);
        }
        if (e.getActionCommand() == "clear button") {
          str = "";
          tf.setText(str);
        }

    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void windowOpened(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}

    public static void main(String[] args) {
        EventHandle eh = new EventHandle();
        eh.launchFrame();
    }



  }
