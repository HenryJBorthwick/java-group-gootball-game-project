package gui.gamehome;

import gui.match.*;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

import game.core.GameEnvironment;
import game.core.Team;

import java.awt.Font;

/**
 * Match class implements the Gui interface and represents the Match screen of the game.
 * It displays information about different teams in the game.
 */
public class Match implements Gui {

    private JFrame frmNextMatch; // The main frame of the match GUI
    private JTextArea txtTeam1, txtTeam2, txtTeam3; // Text areas for displaying team information
    private GameEnvironment gameEnvironment; // The game environment instance 

    /**
     * The main method that launches the application.
     * 
     * @param args command-line arguments (not used)
     */    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Instantiate the game environment and the match window
                    GameEnvironment gameEnvironment = new GameEnvironment();
                    Match window = new Match(gameEnvironment);
                    window.frmNextMatch.setVisible(true); // Make the window visible
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

     /**
      * Constructor of the Match class. Takes a GameEnvironment instance as input,
      * saves it locally, then calls the initialize() and getEnemyTeams() methods.
      *
      * @param gameEnvironment an instance of the GameEnvironment class.
      */
    public Match(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment; // Initialize the game environment
        initialize(); // Initialize the GUI components
        getEnemyTeams(); // Display the enemy teams in the text areas
    } 

    /**
     * Initializes the contents of the frame.
     */
    private void initialize() {
        // Create and set up the main frame
        frmNextMatch = new JFrame();
        frmNextMatch.setTitle("Next Match");
        frmNextMatch.setBounds(100, 100, 450, 300);
        frmNextMatch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmNextMatch.getContentPane().setLayout(null);

        // Create and set up the Back button
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainMenu gameHome = new MainMenu(gameEnvironment);
                show(gameHome.getFrame());
                close(frmNextMatch); // Close the current GUI
            }
        });

        // Create and set up the Play Team 3 button
        JButton btnPlayTeam3 = new JButton("Play Team 3");
        btnPlayTeam3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 if (gameEnvironment.getPlayer().getTeam().isTeamFull()) {  
                     if (gameEnvironment.getPlayer().getTeam().teamNotInjured()) {
                         Game game = new Game(gameEnvironment, 3);
                         show(game.getFrame());
                        
                         close(frmNextMatch);
                     } else {
                         JOptionPane.showMessageDialog(frmNextMatch, "Some members of your team are injured. Please ensure all team members are healthy before starting the game!", "Warning", JOptionPane.WARNING_MESSAGE);
                     }
                 } else {
                     JOptionPane.showMessageDialog(frmNextMatch, "Your team is not full. Please fill your team before starting the game!", "Warning", JOptionPane.WARNING_MESSAGE);
                 }   
             }
        });

        // Set the bounds and add the buttons to the frame
        btnPlayTeam3.setBounds(289, 164, 122, 35);
        frmNextMatch.getContentPane().add(btnPlayTeam3);
        
        btnBack.setBounds(173, 222, 117, 25);
        frmNextMatch.getContentPane().add(btnBack);

        // Create and set up the Play Team 2 button
        JButton btnPlayTeam2 = new JButton("Play Team 2");
        btnPlayTeam2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 if (gameEnvironment.getPlayer().getTeam().isTeamFull()) {  
                     if (gameEnvironment.getPlayer().getTeam().teamNotInjured()) {
                         Game game = new Game(gameEnvironment, 2);
                         show(game.getFrame());
                        
                         close(frmNextMatch);
                     } else {
                         JOptionPane.showMessageDialog(frmNextMatch, "Some members of your team are injured. Please ensure all team members are healthy before starting the game!", "Warning", JOptionPane.WARNING_MESSAGE);
                     }
                 } else {
                     JOptionPane.showMessageDialog(frmNextMatch, "Your team is not full. Please fill your team before starting the game!", "Warning", JOptionPane.WARNING_MESSAGE);
                 }   
             }
        });
        
        btnPlayTeam2.setBounds(173, 164, 122, 35);
        frmNextMatch.getContentPane().add(btnPlayTeam2);
        
        JButton btnPlayTeam1 = new JButton("Play Team 1");
        btnPlayTeam1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (gameEnvironment.getPlayer().getTeam().isTeamFull()) {  
                    if (gameEnvironment.getPlayer().getTeam().teamNotInjured()) {
                        Game game = new Game(gameEnvironment, 1);
                        show(game.getFrame());
                        
                        close(frmNextMatch);
                    } else {
                        JOptionPane.showMessageDialog(frmNextMatch, "Some members of your team are injured. Please ensure all team members are healthy before starting the game!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frmNextMatch, "Your team is not full. Please fill your team before starting the game!", "Warning", JOptionPane.WARNING_MESSAGE);
                }   
            }
        });

        // Set the bounds and add the button to the frame
        btnPlayTeam1.setBounds(56, 164, 122, 35);
        frmNextMatch.getContentPane().add(btnPlayTeam1);
        // Set up the team information text areas
        txtTeam1 = new JTextArea();
        txtTeam1.setEditable(false);
        txtTeam1.setBounds(34, 37, 128, 115);
        txtTeam1.setFont(new Font("monospaced", Font.PLAIN, 11));
        frmNextMatch.getContentPane().add(txtTeam1);

        txtTeam2 = new JTextArea();
        txtTeam2.setEditable(false);
        txtTeam2.setBounds(168, 37, 114, 115);
        txtTeam2.setFont(new Font("monospaced", Font.PLAIN, 11));
        frmNextMatch.getContentPane().add(txtTeam2);

        txtTeam3 = new JTextArea();
        txtTeam3.setEditable(false);
        txtTeam3.setBounds(289, 37, 122, 115);
        txtTeam3.setFont(new Font("monospaced", Font.PLAIN, 11));
        frmNextMatch.getContentPane().add(txtTeam3);
    }

    /**
     * Retrieves and displays details of enemy teams
     */
    public void getEnemyTeams() {
        Team Team_1 = gameEnvironment.getEnemyTeam1();
        Team Team_2 = gameEnvironment.getEnemyTeam2();
        Team Team_3 = gameEnvironment.getEnemyTeam3();

        txtTeam1.setText(Team_1.toDisplay());
        txtTeam2.setText(Team_2.toDisplay());
        txtTeam3.setText(Team_3.toDisplay());
    }

    /**
     * Checks the readiness of the player's team for a match
     * @return A boolean value indicating if the team is full and not injured
     */
    public boolean checkTeam() {
        return gameEnvironment.getPlayer().getTeam().isTeamFull() && gameEnvironment.getPlayer().getTeam().teamNotInjured();
    }
    
    /**
     * Returns the main JFrame of this GUI.
     * @return The JFrame of this GUI
     */
    public JFrame getFrame() {
        return this.frmNextMatch;
    }
}
