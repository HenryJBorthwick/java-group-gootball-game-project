package gui.setup;


import gui.gamehome.Gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.EventQueue;
import javax.swing.JFrame;

import game.core.GameEnvironment;

import javax.swing.JButton;

/**
 * The Welcome class represents the initial GUI window shown to the user.
 */
public class Welcome  implements Gui{

    JFrame frmWelcomr;
    private GameEnvironment gameEnvironment;
    

    /**
     * Main method that launches the application.
     * Requires no GameEnvironment to run as makes it in constructor.
     * Is ran from Main class as entry point into application.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Welcome window = new Welcome();
                    window.frmWelcomr.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }); 
    }
    
    /**
     * Constructor of the Welcome class. Initializes a new GameEnvironment instance and calls the initialize() method.
     */
    public Welcome() {
        GameEnvironment gameEnv = new GameEnvironment();
        this.gameEnvironment = gameEnv;
        initialize();
    }
    
    /**
     * Initializes the contents of the GUI frame, including the creation and configuration of necessary GUI components.
     */
    private void initialize() {
        
        frmWelcomr = new JFrame();
        frmWelcomr.setTitle("Welcome");
        frmWelcomr.setBounds(100, 100, 450, 300);
        frmWelcomr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmWelcomr.getContentPane().setLayout(null);
        
        JButton btnQuit = new JButton("Quit");
        btnQuit.addMouseListener(new MouseAdapter() {
            
            /**
             * Event handler for mouse click on the Quit button.
             * will close the GUI application .
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                //Close Welcome GUI
                System.exit(0);
            }
        });
        btnQuit.setBounds(169, 141, 89, 23);
        frmWelcomr.getContentPane().add(btnQuit);
        
        JButton btnPlay = new JButton("Play");
        btnPlay.addMouseListener(new MouseAdapter() {                

            /**
             * Event handler for mouse click on the Play button.
             * Will open the GameSetUp GUI and close dispose this GUI.
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                //Open GameSetUp GUI
            	GameSetUp gameSetUp = new GameSetUp(gameEnvironment);
                gameSetUp.show(gameSetUp.getFrame());
                //Close Welcome GUI
                close(getFrame());
            }
        });
        btnPlay.setBounds(169, 70, 89, 23);
        frmWelcomr.getContentPane().add(btnPlay);
    }
    
	/**
	 * This method returns the JFrame of the current GUI.
	 *
	 * @return the JFrame component of the current GUI
	 */
    public JFrame getFrame() {
        return this.frmWelcomr;
    }
}