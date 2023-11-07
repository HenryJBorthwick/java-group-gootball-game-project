package gui.gamehome;

import javax.swing.JFrame;

/**
 * The Gui interface provides default methods for showing and closing a JFrame.
 * It is intended to be implemented by GUI classes to provide common functionality.
 */
public interface Gui {
    
    /**
     * Shows the specified JFrame by setting it visible.
     * @param frame The JFrame to show
     */
    default void show(JFrame frame) {
        frame.setVisible(true);
    }

    /**
     * Closes the specified JFrame by disposing it.
     * @param frame The JFrame to close
     */
    default void close(JFrame frame) {
        frame.dispose();
    }
}
