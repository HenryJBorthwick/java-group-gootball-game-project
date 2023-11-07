package gui.match;

import gui.gamehome.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import game.core.GameEnvironment;
import game.core.Team;

/**
 * The Game class represents the GUI for a game in the game environment.
 * It allows the user to view the game results.
 */
public class Game implements Gui {

    private JFrame frame;
    private GameEnvironment gameEnvironment;
    private JTextField txtMatchComplete;
    private JTextField txtHintTheBest;
    private JTextField txtHintTakingA;
    private String winloss;

    /**
     * Create the application.
     * @param gameEnvironment The game environment instance
     * @param teamID The ID of the team
     */
    public Game(GameEnvironment gameEnvironment, int teamID) {
        this.gameEnvironment = gameEnvironment;
        this.winloss = this.getResult(gameEnvironment, teamID);
        initialize();
    }


    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnGetResults = new JButton("Get Results!");
        btnGetResults.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open GameResult GUI
                GameResult gameResults = new GameResult(gameEnvironment, winloss);
                show(gameResults.getFrame());

                // Close the current Game GUI using the method in the Gui interface
                close(frame);
            }
        });

        btnGetResults.setBounds(157, 179, 154, 25);
        frame.getContentPane().add(btnGetResults);

        txtMatchComplete = new JTextField();
        txtMatchComplete.setText("player are playing!");
        txtMatchComplete.setBounds(149, 23, 162, 19);
        txtMatchComplete.setEditable(false); // Make it not editable
        frame.getContentPane().add(txtMatchComplete);
        txtMatchComplete.setColumns(10);

        txtHintTheBest = new JTextField();
        txtHintTheBest.setText("Hint the best teams have 2 Forwards and 3 Defenders");
        txtHintTheBest.setBounds(43, 69, 358, 19);
        txtHintTheBest.setEditable(false); // Make it not editable
        frame.getContentPane().add(txtHintTheBest);
        txtHintTheBest.setColumns(10);

        txtHintTakingA = new JTextField();
        txtHintTakingA.setText("Hint Taking a Bye can better Your Players");
        txtHintTakingA.setBounds(63, 111, 317, 19);
        txtHintTakingA.setEditable(false); // Make it not editable
        frame.getContentPane().add(txtHintTakingA);
        txtHintTakingA.setColumns(10);
    }

    /**
     * Retrieves the result of the game based on the team ID.
     * @param gameEnvironment The game environment instance
     * @param teamID The ID of the team
     * @return The result of the game
     */
    public String getResult(GameEnvironment gameEnvironment, int teamID) {
        Team userteam = gameEnvironment.getPlayer().getTeam();
        Team enemyTeam = null;

        if (teamID == 1) {
            enemyTeam = gameEnvironment.getEnemyTeam1();
        } else if (teamID == 2) {
            enemyTeam = gameEnvironment.getEnemyTeam2();
        } else if (teamID == 3) {
            enemyTeam = gameEnvironment.getEnemyTeam3();
        }

        String result = userteam.compareTeams(userteam, enemyTeam);

        return result;
    }

    /**
     * Returns the main JFrame of this GUI.
     * @return The JFrame of this GUI
     */
    public JFrame getFrame() {
        return this.frame;
    }
}
