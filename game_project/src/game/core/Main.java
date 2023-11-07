package game.core;

import gui.setup.Welcome;

/**
 * The Main class serves as the entry point for the application. It initiates the game
 * by creating and showing a Welcome window.
 */
public class Main {
    
    /**
     * The main method that serves as the entry point for the application. It creates
     * a new Welcome object and displays its associated frame.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Welcome welcome = new Welcome();
        welcome.show(welcome.getFrame());
    }
}
