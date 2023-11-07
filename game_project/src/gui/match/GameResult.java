package gui.match;

import gui.gamehome.EndScreen;
import gui.gamehome.Gui;
import gui.gamehome.MainMenu;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;

import game.core.GameEnvironment;
import game.core.Player;
import game.core.Team;

/**
 * The GameResult class represents the GUI for displaying game results.
 */
public class GameResult implements Gui {

    private JFrame frmResults;
    private JTextField txtYourTeamOne;
    private JTextField txtPointsGained;
    private JTextField txtMoneyGained;

    private int money; 
    private int points; 
    private GameEnvironment gameEnvironment;
    private String injured; 
    private String result;

    /**
     * Create the application.
     * @param gameEnvironment The game environment instance
     * @param result The result of the game
     */
    public GameResult(GameEnvironment gameEnvironment, String result) {
        this.gameEnvironment = gameEnvironment;
        this.result = result;
        decreaseStamina(result);
        int numberInjured = gameEnvironment.getPlayer().getTeam().getNumberInjured();

        if (numberInjured == 5) {
            this.points = 0;
            this.money = 0;
            this.result = "Loss";
        } else {
            this.points = gameEnvironment.getPoints(result);
            this.money = gameEnvironment.getMoney(result);
        }

        addWeek();

        updateMarket();

        gameEnvironment.updateEnemyTeams(gameEnvironment.getPlayer().getCurrentWeek());

        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmResults = new JFrame();
        frmResults.setTitle("Results !");
        frmResults.setBounds(100, 100, 450, 300);
        frmResults.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmResults.getContentPane().setLayout(null);

        txtYourTeamOne = new JTextField();
        txtYourTeamOne.setText("Result: " + result);
        txtYourTeamOne.setBounds(132, 12, 215, 19);
        txtYourTeamOne.setEditable(false); // Make it not editable
        frmResults.getContentPane().add(txtYourTeamOne);
        txtYourTeamOne.setColumns(10);

        JButton btnBac = new JButton("Back To Menu");
        btnBac.setBounds(145, 217, 162, 25);
        btnBac.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Check if remaining weeks is zero
                if (gameEnvironment.getPlayer().getWeeksRemaining() - 1 == 0) {
                    EndScreen endScreen = new EndScreen(gameEnvironment);
                    endScreen.show(endScreen.getFrame());
            	    close(getFrame());
                    
                
                } else {
                    // Otherwise, open GameHome
                    MainMenu gameHome = new MainMenu(gameEnvironment);
                    show(gameHome.getFrame());
                    // Close the current Club GUI using the method in the Gui interface
                    close(frmResults);
                    // Random event and call
                    randomEvent();
                }
            }
        });

        frmResults.getContentPane().add(btnBac);

        JTextArea txtUpdatedTeamStats = new JTextArea();
        txtUpdatedTeamStats.setText("Injured Players:\n" + this.injured); // Set the text here
        txtUpdatedTeamStats.setBounds(38, 43, 172, 162);
        txtUpdatedTeamStats.setEditable(false); // Make it not editable
        frmResults.getContentPane().add(txtUpdatedTeamStats);

        txtPointsGained = new JTextField();
        txtPointsGained.setText("Points Gained: " + points);
        txtPointsGained.setBounds(240, 60, 172, 19);
        txtPointsGained.setEditable(false);
        frmResults.getContentPane().add(txtPointsGained);
        txtPointsGained.setColumns(10);

        txtMoneyGained = new JTextField();
        txtMoneyGained.setText("Money Gained: " + money);
        txtMoneyGained.setBounds(240, 103, 172, 19);
        txtMoneyGained.setEditable(false);
        frmResults.getContentPane().add(txtMoneyGained);
        txtMoneyGained.setColumns(10);
    }

    /**
     * Update the market.
     */
    public void updateMarket() {
        gameEnvironment.updateRandTeam();
        gameEnvironment.updateMarketItems();
    }

    /**
     * Add a week to the player's current week and decrease the team's stamina based on the result.
     */
    public void addWeek() {
        Player player = gameEnvironment.getPlayer();
        player.setCurrentWeek(player.getCurrentWeek() + 1);
        player.setRemainingWeeks(player.getWeeksRemaining() - 1);
    }

    /**
     * Decrease the team's stamina based on the result and get the injured players.
     * @param result The result of the game
     */
    private void decreaseStamina(String result) {
        Team mainTeam = gameEnvironment.getPlayer().getTeam();
        if ("Win".equals(result)) {
            mainTeam.changeTeamStamina(10);
        } else {
            mainTeam.changeTeamStamina(20);
        }

        String injured = mainTeam.getInjured();
        this.injured = injured;
    }

    /**
     * Display a random event message.
     */
    public void randomEvent() {
        // Random Event Call
        String eventTitle = gameEnvironment.getRandomEvent();

        // Check if a string was returned
        if (eventTitle != null && !eventTitle.trim().isEmpty()) {
            // Display the event title in a message dialog
            JOptionPane.showMessageDialog(frmResults, eventTitle, "Random Event", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Returns the main JFrame of this GUI.
     * @return The JFrame of this GUI
     */
    public JFrame getFrame() {
        return this.frmResults;
    }
}
