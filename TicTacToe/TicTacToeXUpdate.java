
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

public class TicTacToeXUpdate implements ActionListener, WindowListener {

    private static final String PLAYER_X_DEFAULT_NAME = "Player X";
    private static final String PLAYER_O_DEFAULT_NAME = "Player O";
    private static final Color X_COLOR = Color.BLUE;
    private static final Color O_COLOR = Color.RED;
    private static final Color WIN_COLOR = Color.YELLOW;
    private static final Color DEFAULT_BUTTON_COLOR = Color.WHITE;

    private final Frame frame;
    private final Label statusLabel;
    private final Button resetButton;
    private final Button[] buttons = new Button[9];

    private String playerX = PLAYER_X_DEFAULT_NAME;
    private String playerO = PLAYER_O_DEFAULT_NAME;

    private boolean isXTurn = true;
    private Dialog nameDialog;

    public TicTacToeXUpdate() {
        frame = new Frame("Tic Tac Toe");
        statusLabel = new Label("Welcome to Tic Tac Toe!");
        resetButton = new Button("Reset");
        showNameDialog();
    }

    public void launchGame() {
        frame.setLayout(new BorderLayout());
        frame.setSize(300, 400);
        frame.setVisible(true);
        frame.addWindowListener(this);

        // Create the grid panel for the game
        Panel gridPanel = createGridPanel();
        // Style and configure the status label
        configureStatusLabel();
        // Configure reset button
        configureResetButton();

        // Add components to the frame
        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.NORTH);
        frame.add(resetButton, BorderLayout.SOUTH);
    }

    private Panel createGridPanel() {
        Panel gridPanel = new Panel();
        gridPanel.setLayout(new GridLayout(3, 3));

        // Initialize buttons and add them to the grid panel
        for (int i = 0; i < 9; i++) {
            buttons[i] = createButton();
            gridPanel.add(buttons[i]);
        }
        return gridPanel;
    }

    private Button createButton() {
        Button button = new Button("");
        button.setFont(new Font("Arial", Font.BOLD, 40));
        button.setBackground(DEFAULT_BUTTON_COLOR);
        button.addActionListener(this);
        return button;
    }

    private void configureStatusLabel() {
        statusLabel.setAlignment(Label.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setBackground(Color.LIGHT_GRAY);
    }

    private void configureResetButton() {
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.addActionListener(this);
    }

    private void showNameDialog() {
        nameDialog = new Dialog(frame, "Enter Player Names", true);
        nameDialog.setSize(400, 300);
        nameDialog.setLayout(new GridLayout(4, 2));
        nameDialog.addWindowListener(this);

        Label playerXLabel = new Label("Player X Name:");
        Label playerOLabel = new Label("Player O Name:");

        TextField playerXField = createNameTextField(playerX);
        TextField playerOField = createNameTextField(playerO);

        nameDialog.add(playerXLabel);
        nameDialog.add(playerXField);
        nameDialog.add(playerOLabel);
        nameDialog.add(playerOField);

        Button startButton = new Button("Start Game");
        startButton.addActionListener(e -> startGame(playerXField, playerOField));

        nameDialog.add(startButton);
        nameDialog.setVisible(true);
    }

    private TextField createNameTextField(String defaultName) {
        TextField textField = new TextField(defaultName);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.setText("");
            }
        });
        return textField;
    }

    private void startGame(TextField playerXField, TextField playerOField) {
        String xName = playerXField.getText().trim();
        String oName = playerOField.getText().trim();

        if (!xName.isEmpty()) {
            playerX = xName;
        }
        if (!oName.isEmpty()) {
            playerO = oName;
        }

        statusLabel.setText(playerX + "'s turn");
        nameDialog.setVisible(false);
        nameDialog.dispose();
        launchGame();
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

        // Update the button based on the current turn
        updateButton(clickedButton);

        // Check for a winner or a draw
        if (checkWinner()) {
            statusLabel.setText((isXTurn ? playerX : playerO) + " wins!");
            disableButtons();
        } else if (isDraw()) {
            statusLabel.setText("It's a draw!");
        } else {
            // Switch turns
            isXTurn = !isXTurn;
            statusLabel.setText((isXTurn ? playerX : playerO) + "'s turn");
        }
    }

    private void updateButton(Button clickedButton) {
        clickedButton.setLabel(isXTurn ? "X" : "O");
        clickedButton.setForeground(isXTurn ? X_COLOR : O_COLOR);
    }

    private boolean checkWinner() {
        int[][] winningCombinations = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
            {0, 4, 8}, {2, 4, 6} // Diagonals
        };

        for (int[] combo : winningCombinations) {
            if (isWinningCombo(combo)) {
                highlightWinningCombination(combo);
                return true;
            }
        }
        return false;
    }

    private boolean isWinningCombo(int[] combo) {
        String b1 = buttons[combo[0]].getLabel();
        String b2 = buttons[combo[1]].getLabel();
        String b3 = buttons[combo[2]].getLabel();
        return !b1.equals("") && b1.equals(b2) && b2.equals(b3);
    }

    private void highlightWinningCombination(int[] combo) {
        for (int index : combo) {
            buttons[index].setBackground(WIN_COLOR);
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
            button.setBackground(DEFAULT_BUTTON_COLOR);
        }
        isXTurn = true;
        statusLabel.setText(playerX + "'s turn");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    // Empty methods for windowListener implementation
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
        new TicTacToe().launchGame();
    }
}
