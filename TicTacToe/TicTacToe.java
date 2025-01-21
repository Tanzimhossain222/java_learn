
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TicTacToe implements ActionListener, WindowListener {

    private final Frame frame;
    private final Label statusLabel;
    private final Button resetButton;
    private final Button[] buttons = new Button[9];

    private String playerXName = "Player X";
    private String playerOName = "Player O";

    private boolean isXTurn = true;
    private Dialog nameDialog;

    public TicTacToe() {
        frame = new Frame("Tic Tac Toe");
        statusLabel = new Label("Welcome to Tic Tac Toe!");
        resetButton = new Button("Reset");
        showNameDialog();
    }

    public void launchGame() {
        frame.setLayout(new BorderLayout());
        frame.setSize(300, 400);

        // Create the grid panel
        Panel gridPanel = new Panel();
        gridPanel.setLayout(new GridLayout(3, 3));

        // Initialize buttons and add them to the grid panel
        for (int i = 0; i < 9; i++) {
            buttons[i] = new Button("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 40));
            buttons[i].setBackground(Color.WHITE); // Set initial button background
            buttons[i].addActionListener(this);
            gridPanel.add(buttons[i]);
        }

        // Style the status label
        statusLabel.setAlignment(Label.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setBackground(Color.LIGHT_GRAY);

        // Style and configure the reset button
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.addActionListener(this);

        // Add components to the frame
        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.NORTH);
        frame.add(resetButton, BorderLayout.SOUTH);

        // Display the frame
        frame.setVisible(true);
        frame.addWindowListener(this);

    }

    private void showNameDialog() {
        nameDialog = new Dialog(frame, "Enter Player Names", true);
        nameDialog.setSize(400, 300);
        nameDialog.setLayout(new GridLayout(4, 2));
        nameDialog.addWindowListener(this);

        Label playerXLabel = new Label("Player X Name:");
        Label playerOLabel = new Label("Player O Name:");

        TextField playerXField = new TextField(playerXName);
        TextField playerOField = new TextField(playerOName);

        // Clear the text fields when clicked
        playerXField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                playerXField.setText("");
            }
        });

        playerOField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                playerOField.setText("");
            }
        });

        // Add the components to the dialog
        nameDialog.add(playerXLabel);
        nameDialog.add(playerXField);
        nameDialog.add(playerOLabel);
        nameDialog.add(playerOField);

        Button startButton = new Button("Start Game");

        startButton.addActionListener(_ -> {
            String xName = playerXField.getText().trim();
            String oName = playerOField.getText().trim();

            if (!xName.isEmpty()) {
                playerXName = xName;
            }
            if (!oName.isEmpty()) {
                playerOName = oName;
            }

            statusLabel.setText(playerXName + "'s turn");
            nameDialog.setVisible(false);
            nameDialog.dispose();
            launchGame();
        });

        nameDialog.add(startButton);
        nameDialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            resetGame();
            return;
        }

        Button clickedButton = (Button) e.getSource();

        // Ignore clicks on already occupied buttons
        if (!clickedButton.getLabel().equals("")) {
            return;
        }

        // Update the button's label and color based on the current turn
        clickedButton.setLabel(isXTurn ? "X" : "O");
        clickedButton.setForeground(isXTurn ? Color.BLUE : Color.RED);

        // Check for a winner or a draw
        if (checkWinner()) {
            statusLabel.setText((isXTurn ? playerXName : playerOName) + " wins!");
            disableButtons();
        } else if (isDraw()) {
            statusLabel.setText("It's a draw!");
        } else {
            // Switch turns
            isXTurn = !isXTurn;
            statusLabel.setText((isXTurn ? playerXName : playerOName) + "'s turn");
        }

    }

    private boolean checkWinner() {
        int[][] winningCombinations = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
            {0, 4, 8}, {2, 4, 6} // Diagonals
        };

        for (int[] combo : winningCombinations) {
            String b1 = buttons[combo[0]].getLabel();
            String b2 = buttons[combo[1]].getLabel();
            String b3 = buttons[combo[2]].getLabel();

            if (!b1.equals("") && b1.equals(b2) && b2.equals(b3)) {
                highlightWinningCombination(combo); // Highlight the winning buttons
                return true;
            }
        }
        return false;
    }

    private void highlightWinningCombination(int[] combo) {
        for (int index : combo) {
            buttons[index].setBackground(Color.YELLOW); // Highlight the winning buttons
        }
    }

    private boolean isDraw() {
        for (Button button : buttons) {
            if (button.getLabel().equals("")) {
                return false;
            }
        }
        return true;
    }

    private void disableButtons() {
        for (Button button : buttons) {
            button.setEnabled(false);
        }
    }

    private void resetGame() {
        for (Button button : buttons) {
            button.setLabel("");
            button.setEnabled(true);
            button.setBackground(Color.WHITE); // Reset background color
        }
        isXTurn = true;
        statusLabel.setText(playerXName + "'s turn");
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
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
        TicTacToe game = new TicTacToe();
        game.launchGame();
    }

}
