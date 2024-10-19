package fourgame;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
* Provides a common framework for application window, e.g asking user if he really wants to exit
*/
public class BaseWindow extends JFrame {
    /**
     * Constructs a base window with a title, size, and close operation.
     */
    public BaseWindow() {
        setTitle("Four Game");
        setSize(400, 450);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                showExitConfirmation();
            }
        });
    }
    
     /**
     * Displays an exit confirmation dialog when the window is closed by the user.
     */
    private void showExitConfirmation() {
        int n = JOptionPane.showConfirmDialog(this, "Do you really want to quit?",
                "Really?", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            doUponExit();
        }
    }
    
     /**
     * Performs actions when the window is closed - dispose the window.
     */
    protected void doUponExit() {
        this.dispose();
    }
    
}
