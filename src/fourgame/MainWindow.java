package fourgame;

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;

/**
 * Represents the main window. Allows the user to start new games of different sizes.
 */
public class MainWindow extends BaseWindow {
    
    private List<Window> gameWindows = new ArrayList<>();
    
    /**
     * Constructs the main window and sets up buttons to start new games of different sizes (3x3, 5x5, 7x7).
     */
    public MainWindow() {
        
        JButton small = new JButton();
        small.setText("3 x 3");
        small.addActionListener(getActionListener(3));
        
        JButton medium = new JButton();
        medium.setText("5 x 5");
        medium.addActionListener(getActionListener(5));

        JButton large = new JButton();
        large.setText("7 x 7");   
        large.addActionListener(getActionListener(7));
        
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(small);
        getContentPane().add(medium);
        getContentPane().add(large);
    }
    /**
    * Creates and returns an ActionListener for a specified size. 
    *
    * This ActionListener is used to handle the action event when a specific action is performed,
    * typically when a button or menu item is clicked. It creates a new Window of the given size
    * and adds it to the list of gameWindows, making the window visible.
    *
    * @param size The size parameter used to initialize the created Window.
    * @return An ActionListener that performs the specified action.
    */
    private ActionListener getActionListener(final int size) {
        return new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = new Window(size, MainWindow.this);
                window.setVisible(true);
                gameWindows.add(window);
            }
        };
    }
        
     /**
     * Returns a list of game windows created during the application's lifetime.
     *
     * @return A list of game windows.
     */
    public List<Window> getWindowList() {
        return gameWindows;
    }
    
    /**
     * Performs actions to exit the application when the main window is closed.
     */
    @Override
    protected void doUponExit() {
        System.exit(0);
    }
}