package fourgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * Represent the game Window where the actual game takes place.
 */
public class Window extends BaseWindow {
    
    private final int size;
    private final MainWindow mainWindow;
    private JButton[][] cells;
    private int[][] table;
    private boolean playerRed;
    private int scoreRed;
    private int scoreBlue;
    private JLabel currentPlayerLabel;
    private JLabel redScoreLabel;
    private JLabel blueScoreLabel;

    /**
     * Creates a new game window with the specified size.
     * @param size The size of the game board (3x3, 5x5, 7x7).
     * @param mainWindow The main window containing this window.
     */
    public Window(int size, MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        mainWindow.getWindowList().add(this);
        this.size = size;
        this.playerRed = true;
        this.scoreRed = 0;
        this.scoreBlue = 0;
        this.table = new int[size][size];
        this.cells = new JButton[size][size];
        
        JPanel panel = new JPanel(new GridLayout(size, size));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new JButton();
                cells[i][j].setText("0"); // Initially, all fields are 0
                cells[i][j].addActionListener(new CellListener(i, j));
                panel.add(cells[i][j]);
            }
        }
        
        // Add labels to display current player and scores
        getContentPane().add(panel);
        currentPlayerLabel = new JLabel("Current Player: Red");
        redScoreLabel = new JLabel("Red Player's Score: 0");
        blueScoreLabel = new JLabel("Blue Player's Score: 0");

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.add(currentPlayerLabel);
        infoPanel.add(redScoreLabel);
        infoPanel.add(blueScoreLabel);

        getContentPane().add(infoPanel, BorderLayout.NORTH);
    }


    /**
    * Listener for cell button clicks. Handles cell clicks and updates the game state.
    */
    private class CellListener implements ActionListener {
        private int row, col;

        public CellListener(int x, int y) {
            this.row = x;
            this.col = y;
        }

        /**
        * Handles the action event when a player selects a field on the game board.
        *
        * If a player chooses a field, the value of the field and its neighbors are incremented by one
        * (if the value is less than 4). If the value of a cell reaches 4, it is colored to indicate a
        * winning move and the score for the respective player is updated. The current player's turn
        * is switched, and the method checks if the game is over, displaying the winner and starting a
        * new game if necessary. Finally, it updates the score labels.
        *
        * @param e The ActionEvent triggered by the player's selection.
        */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(table[row][col]>=4)
            {
                already4();
                return;
            }
                
        /*If a player chooses a field, then the value of the field and its neighbours incremented by one (if the value is less than 4)*/
        for (int r = Math.max(0, row - 1); r <= Math.min(size - 1, row + 1); r++) {
            for (int c = Math.max(0, col - 1); c <= Math.min(size - 1, col + 1); c++) {
                if (table[r][c] < 4) {
                    table[r][c]++;
                    cells[r][c].setText(String.valueOf(table[r][c]));
                }
                // Check if a cell reaches 4 and colorize it
                if (table[r][c] == 4) {
                    table[r][c]++;
                    if (playerRed) {
                        scoreRed++;
                    } else {
                        scoreBlue++;
                    }
                    cells[r][c].setBackground(playerRed ? Color.RED : Color.BLUE);
                }
                else if(table[r][c]>4)
                {
                    //do nothing 
                }
            }
        }                      
                    // Switch players
                    playerRed = !playerRed;
                    if (isGameOver()) {
                        showWinner();
                        newGame();
                    }
                updateLabels();
            }
        }
    /**
    * Checks if the game is over by examining the numbers of all cells in the table.
    *
    * @return True if the game is over, false otherwise.
    */
    private boolean isGameOver() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (table[i][j] < 4) {
                    return false;
                }
            }
        }
       
        return true;
    }
    
    private void already4(){
        JOptionPane.showMessageDialog(this, "This cell already has a value of 4. Please click another cell!");
    }
    /**
     * Shows a message dialog indicating the winner of the game.
     */
    private void showWinner() {
        updateLabels();
        
        {
            String winner = (scoreRed > scoreBlue) ? "Red Player" : "Blue Player";
            JOptionPane.showMessageDialog(this, winner + " wins!");
        }
        
    }
    
    /**
     * Starts a new game by creating a new game window and disposing of the current window.
     */
    private void newGame() {
        Window newWindow = new Window(size, mainWindow);
        newWindow.setVisible(true);
        //remove this window and create new one for new game
        this.dispose();
        mainWindow.getWindowList().remove(this);
    }
    /**
    * Updates the text of labels to reflect the current player and the scores of the Red and Blue players.
    */
    private void updateLabels(){
        String currentPlayer = playerRed ? "Red" : "Blue";
        currentPlayerLabel.setText("Current Player: " + currentPlayer);
        redScoreLabel.setText("Red Player's Score: " + scoreRed);
        blueScoreLabel.setText("Blue Player's Score: " + scoreBlue);
    }

    @Override
    protected void doUponExit() {
        super.doUponExit();
        mainWindow.getWindowList().remove(this);
    }
    
}