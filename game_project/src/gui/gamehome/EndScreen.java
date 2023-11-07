package gui.gamehome;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import game.core.GameEnvironment;

import javax.swing.JButton;

import gui.setup.Welcome;

/**
 * EndScreen is a GUI class that represents the end screen of the game.
 * It displays the final score, points, and provides options to start a new game or terminate the program.
 */
public class EndScreen implements Gui{
    private JFrame frmEndGame;
    private GameEnvironment gameEnvironment;
    private JTextField txtTotalScore;
    private JTextField txtPoints;
    private JButton btnEnd;
    private JTextField txtTeamName;
    
    /**
     * The main method that launches the application.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GameEnvironment gameEnvironment = new GameEnvironment();
                EndScreen window = new EndScreen(gameEnvironment);
                window.frmEndGame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Constructor that creates an EndScreen GUI object and initializes it.
     * @param gameEnvironment The game environment object
     */
    public EndScreen(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        initialize(); // Initialize the GUI components
    }
    
    /**
     * Method that initializes the contents of the frame.
     */
    private void initialize() {
        frmEndGame = new JFrame();
        frmEndGame.setTitle("END GAME");
        frmEndGame.setBounds(100, 100, 450, 300);
        frmEndGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmEndGame.getContentPane().setLayout(null);

        JLabel lblThanksForPlaying = new JLabel("Thanks For Playing");
        lblThanksForPlaying.setBounds(153, 12, 134, 15);
        frmEndGame.getContentPane().add(lblThanksForPlaying);

        txtTotalScore = new JTextField();
        txtTotalScore.setText("Winnings  = " + gameEnvironment.getPlayer().getTotalMoney());
        txtTotalScore.setBounds(236, 70, 114, 19);
        txtTotalScore.setEditable(false);
        frmEndGame.getContentPane().add(txtTotalScore);
        txtTotalScore.setColumns(10);

        JButton btnNewGame = new JButton("New Game !");
        btnNewGame.setBounds(250, 126, 160, 61);
        btnNewGame.addActionListener(e -> {
        	 Welcome welcomeGUI = new Welcome();
        	    welcomeGUI.show(welcomeGUI.getFrame());
        	    close(getFrame());
        });
        frmEndGame.getContentPane().add(btnNewGame);

        txtPoints = new JTextField();
        txtPoints.setText("Points = " + getPoints());
        txtPoints.setBounds(95, 70, 114, 19);
        txtPoints.setEditable(false);
        frmEndGame.getContentPane().add(txtPoints);
        txtPoints.setColumns(10);
        gameEnvironment.updateEnemyTeams(gameEnvironment.getPlayer().getCurrentWeek());

        btnEnd = new JButton("Terminate :/");
        btnEnd.setBounds(32, 126, 177, 61);
        btnEnd.addActionListener(e -> System.exit(0));
        frmEndGame.getContentPane().add(btnEnd);

        txtTeamName = new JTextField();
        txtTeamName.setText("Team " + gameEnvironment.getTeamName());
        txtTeamName.setEditable(false);
        txtTeamName.setBounds(123, 37, 213, 19);
        frmEndGame.getContentPane().add(txtTeamName);
        txtTeamName.setColumns(10);

        JLabel lblSeasonLen = new JLabel("Season Length  == " + (gameEnvironment.getPlayer().getCurrentWeek()));
        lblSeasonLen.setBounds(123, 211, 219, 35);
        frmEndGame.getContentPane().add(lblSeasonLen);
    }

    /**
     * Returns the main JFrame of this GUI.
     * @return The JFrame of this GUI
     */
    public JFrame getFrame() {
        return this.frmEndGame;
    }

    /**
     * Returns the points earned by the player.
     * @return The points earned by the player
     */
    public int getPoints() {
        return gameEnvironment.getPlayer().getPoints();
    }

    /**
     * Returns the total money earned by the player.
     * @return The total money earned by the player
     */
    public int getMoney() {
        return gameEnvironment.getPlayer().getTotalMoney();
    }
}