package gui.gamehome;

import java.awt.EventQueue;
import javax.swing.*;

import game.core.*;

/**
 * This class represents the main menu GUI of the game. It extends the Gui interface.
 */
public class MainMenu implements Gui  {

    private JFrame frame;
    private JTextField txtMoney;
    private JTextField txtCurrentWeek;
    private JTextField txtRemainingWeeks;
    private GameEnvironment gameEnvironment;
    
    /**
     * The main method that launches the application.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GameEnvironment gameEnvironment = new GameEnvironment();
                MainMenu gameHome = new MainMenu(gameEnvironment);
                gameHome.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Constructor for MainMenu class.
     *
     * @param gameEnvironment An instance of GameEnvironment class.
     */
    public MainMenu(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        initialize();
    }
    
    /**
     * This method initializes the GUI components.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        txtMoney = new JTextField();
        txtMoney.setEditable(false);
        txtMoney.setText("Current Money");
        txtMoney.setBounds(90, 9, 108, 19);
        this.displayPlayerMoney();
        frame.getContentPane().add(txtMoney);
        txtMoney.setColumns(10);

        JLabel lblMainMenu = new JLabel("Main Menu");
        lblMainMenu.setBounds(177, 40, 100, 15);
        frame.getContentPane().add(lblMainMenu);

        JButton btnClub = new JButton("Club");
        btnClub.addActionListener(e -> {
            Club club = new Club(gameEnvironment);
            
            this.show(club.getFrame());
            this.close(this.getFrame());

        });
        btnClub.setBounds(50, 94, 117, 25);
        frame.getContentPane().add(btnClub);

        JButton btnMatch = new JButton("Match");
        btnMatch.addActionListener(e -> {
            Match match = new Match(gameEnvironment);
            this.show(match.getFrame());
            this.close(this.getFrame());
        });
        btnMatch.setBounds(246, 94, 117, 25);
        frame.getContentPane().add(btnMatch);

        JButton btnMarket = new JButton("Market");
        btnMarket.addActionListener(e -> {
            Market buySellAthleteItems = new Market(gameEnvironment);
            this.show(buySellAthleteItems.getFrame());
            this.close(this.getFrame());
        });
        btnMarket.setBounds(50, 158, 117, 25);
        frame.getContentPane().add(btnMarket);

        JButton btnBye = new JButton("Bye");
        btnBye.addActionListener(e -> {
        	TakeBye bye = new TakeBye(gameEnvironment);
        	this.show(bye.getFrame());
            this.close(this.getFrame());
            frame.dispose();
        });
        btnBye.setBounds(246, 158, 117, 25);
        frame.getContentPane().add(btnBye);

        txtCurrentWeek = new JTextField();
        txtCurrentWeek.setEditable(false);
        txtCurrentWeek.setText("Current week");
        txtCurrentWeek.setBounds(121, 195, 93, 19);
        this.displayCurrentWeek();
        frame.getContentPane().add(txtCurrentWeek);
        txtCurrentWeek.setColumns(10);

        txtRemainingWeeks = new JTextField();
        txtRemainingWeeks.setEditable(false);
        txtRemainingWeeks.setText("weeks remaining");
        txtRemainingWeeks.setBounds(146, 226, 75, 19);
        this.displayRemainingWeeks();
        frame.getContentPane().add(txtRemainingWeeks);
        txtRemainingWeeks.setColumns(10);

        JLabel lblMoney = new JLabel("Money:");
        lblMoney.setBounds(31, 11, 70, 15);
        frame.getContentPane().add(lblMoney);

        JLabel lblCurrentWeek = new JLabel("Current week:");
        lblCurrentWeek.setBounds(12, 195, 117, 15);
        frame.getContentPane().add(lblCurrentWeek);

        JLabel lblRemainingWeeks = new JLabel("Remaining weeks:");
        lblRemainingWeeks.setBounds(12, 228, 143, 15);
        frame.getContentPane().add(lblRemainingWeeks);

        JButton btnEndGame = new JButton("End Game");
        btnEndGame.addActionListener(e -> {
            int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to end the game?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmation == 0) {
               
                EndScreen endScreen = new EndScreen(gameEnvironment);
                
                endScreen.show(endScreen.getFrame());
        	    close(getFrame());
                
                
            }
        });
        btnEndGame.setBounds(307, 227, 117, 23);
        frame.getContentPane().add(btnEndGame);
    }

    /**
     * This method updates and displays the player's current money in the GUI.
     */
    public void displayPlayerMoney() {
        txtMoney.setText(String.valueOf(gameEnvironment.getPlayer().getMoney()));
    }

    /**
     * This method updates and displays the player's current week in the GUI.
     */
    public void displayCurrentWeek() {
        txtCurrentWeek.setText(String.valueOf(gameEnvironment.getPlayer().getCurrentWeek()));
    }

    /**
     * This method updates and displays the player's remaining weeks in the GUI.
     */
    public void displayRemainingWeeks() {
        txtRemainingWeeks.setText(String.valueOf(gameEnvironment.getPlayer().getWeeksRemaining()));
    }
    
    /**
     * Getter for the JFrame object.
     * @return the JFrame object
     */
    public JFrame getFrame() {
        return this.frame;
    }
}
