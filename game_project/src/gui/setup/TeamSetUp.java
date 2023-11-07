package gui.setup;
import gui.gamehome.*;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import game.core.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import gui.gamehome.Gui;

/**
 * This class provides the GUI for setting up the team. The user can view and purchase athletes
 * to add to their team.
 */
public class TeamSetUp implements Gui {
	JFrame frmTeamSetUp;
	private JTextField txtMoney;
	private JTextField txtMainTeamSize;
	private JTextField txtName;
	private JTextField txtOffence;
	private JTextField txtDefence;
	private JTextField txtStamina;
	private JTextField txtPrice;
	private GameEnvironment gameEnvironment;
	private Athlete randAthlete;
	private JTextField txtPosition;
	
    /**
     * The main method that launches the application.
     * 
     * @param args command-line arguments (not used)
     */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					GameEnvironment gameEnvironment = new GameEnvironment();
					TeamSetUp teamSetUp =  new TeamSetUp(gameEnvironment);
					
					 teamSetUp.frmTeamSetUp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    /**
     * Creates the TeamSetUp application.
     *
     * @param game The GameEnvironment object representing the current game state.
     */
	public TeamSetUp(GameEnvironment game) {
	    this.gameEnvironment = game;
	    Athlete rand = gameEnvironment.getRandAthlete();
	    this.randAthlete = rand;
	    initialize();
	    displayAthlete();   
	}

    /**
     * Initializes the contents of the frame.
     */
	private void initialize() {
		frmTeamSetUp = new JFrame();
		frmTeamSetUp.setTitle("Team set up");
		frmTeamSetUp.setBounds(100, 100, 450, 300);
		frmTeamSetUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTeamSetUp.getContentPane().setLayout(null);
		JLabel lblMoney = new JLabel("Money:");
		lblMoney.setBounds(12, 12, 70, 15);
		frmTeamSetUp.getContentPane().add(lblMoney);
		
		txtMoney = new JTextField();
		txtMoney.setEditable(false);
		txtMoney.setBounds(75, 10, 47, 19);
		// Update Players Money
	    this.displayPlayerMoney();
		frmTeamSetUp.getContentPane().add(txtMoney);
		txtMoney.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(12, 41, 70, 15);
		frmTeamSetUp.getContentPane().add(lblName);
		
		JLabel lblAthlete = new JLabel("Choose starting 5 Athletes:");
		lblAthlete.setBounds(139, 12, 208, 15);
		frmTeamSetUp.getContentPane().add(lblAthlete);
		
		JLabel lblMainTeamSize = new JLabel("Main team size:");
		lblMainTeamSize.setBounds(12, 214, 135, 15);
		frmTeamSetUp.getContentPane().add(lblMainTeamSize);
		
		txtMainTeamSize = new JTextField();
		txtMainTeamSize.setEditable(false);
		txtMainTeamSize.setText("0");
		txtMainTeamSize.setBounds(136, 212, 29, 19);
		// Display main team size
		this.displayMainTeamSize();
		frmTeamSetUp.getContentPane().add(txtMainTeamSize);
		txtMainTeamSize.setColumns(10);
		
		JLabel lblOffence = new JLabel("Offence:");
		lblOffence.setBounds(12, 68, 70, 15);
		frmTeamSetUp.getContentPane().add(lblOffence);
		
		JLabel lblDefence = new JLabel("Defence:");
		lblDefence.setBounds(12, 95, 70, 15);
		frmTeamSetUp.getContentPane().add(lblDefence);
		
		JLabel lblStamina = new JLabel("Stamina:");
		lblStamina.setBounds(12, 122, 70, 15);
		frmTeamSetUp.getContentPane().add(lblStamina);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(12, 187, 70, 15);
		frmTeamSetUp.getContentPane().add(lblPrice);
		
		txtName = new JTextField();
		txtName.setBounds(114, 39, 114, 19);
		frmTeamSetUp.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtOffence = new JTextField();
		txtOffence.setEditable(false);
		txtOffence.setBounds(114, 66, 47, 17);
		frmTeamSetUp.getContentPane().add(txtOffence);
		txtOffence.setColumns(10);
		
		txtDefence = new JTextField();
		txtDefence.setEditable(false);
		txtDefence.setColumns(10);
		txtDefence.setBounds(114, 94, 47, 17);
		frmTeamSetUp.getContentPane().add(txtDefence);
		
		txtStamina = new JTextField();
		txtStamina.setEditable(false);
		txtStamina.setColumns(10);
		txtStamina.setBounds(114, 121, 47, 17);
		frmTeamSetUp.getContentPane().add(txtStamina);
		
		txtPrice = new JTextField();
		txtPrice.setEditable(false);
		txtPrice.setColumns(10);
		txtPrice.setBounds(114, 185, 47, 17);
		frmTeamSetUp.getContentPane().add(txtPrice);
		
		txtPosition = new JTextField();
		txtPosition.setEditable(false);
		txtPosition.setBounds(118, 143, 88, 15);
		frmTeamSetUp.getContentPane().add(txtPosition);
		txtPosition.setColumns(10);
		
		// Display initial random athlete
		this.displayAthlete();
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Open GameSetUp GUI
				 GameSetUp gameSetUp = new GameSetUp(gameEnvironment);
			     gameSetUp.show(gameSetUp.getFrame());
			        //Close TeamSetUp GUI
			     close(getFrame());
			}
		});
		btnBack.setBounds(12, 241, 117, 25);
		frmTeamSetUp.getContentPane().add(btnBack);
		
		JButton btnNewButton = new JButton("Buy Athlete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get the athlete name from the form
				String athleteName = txtName.getText();

				// Validate athlete name length
				if (athleteName.length() < 3 || athleteName.length() > 15) {
				    JOptionPane.showMessageDialog(null, "The athlete name should be between 3 and 15 characters long.", "Input Error", JOptionPane.ERROR_MESSAGE);
				    return;
				}

				// Validate athlete name characters
				if (!athleteName.matches("[a-zA-Z0-9_ ]*")) {
				    JOptionPane.showMessageDialog(null, "The athlete name should not include special characters.", "Input Error", JOptionPane.ERROR_MESSAGE);
				    return;
				}
				// Change randAthlete name
				randAthlete.setName(athleteName);
				
				// Add selected athlete to main team
				gameEnvironment.getPlayer().addToMain(randAthlete);
				
				// Update Player Money
				gameEnvironment.getPlayer().deductMoney(randAthlete.getPrice());
				
				// Check if main team is full
				// If team is full, then proceed to GameHome GUI
				if (gameEnvironment.getPlayer().getMainTeamSize() == 5) {
					
				    MainMenu gameHome = new MainMenu(gameEnvironment);
				    
				    gameHome.show(gameHome.getFrame());

				    // Close the current TeamSetUp GUI using the method in the Gui interface
				    close(getFrame());

				} // else do nothing

				// Update displayed money
				displayPlayerMoney();

		        // Display the new athlete's information to text
		        displayAthlete();
		        
		        // Update players main team size
		        displayMainTeamSize();
			}
		});
		btnNewButton.setBounds(159, 241, 117, 25);
		frmTeamSetUp.getContentPane().add(btnNewButton);
		
		JButton btnNextAthlete = new JButton("Next Athlete");
		btnNextAthlete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        // Display the new athlete's information
		        displayAthlete();
			}
		});
		btnNextAthlete.setBounds(311, 241, 127, 25);
		frmTeamSetUp.getContentPane().add(btnNextAthlete);
		
		JLabel lblHintChoose = new JLabel("Hint: Choose 2 forwards and 3 backs");
		lblHintChoose.setBounds(100, 170, 271, 15);
		frmTeamSetUp.getContentPane().add(lblHintChoose);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setBounds(12, 143, 70, 15);
		frmTeamSetUp.getContentPane().add(lblPosition);		
	}
	
    /**
     * Displays an Athlete's stats in the appropriate text fields.
     */
	public void displayAthlete() {
		// Get random athlete
		randAthlete = gameEnvironment.getRandAthlete();
		
		// Get random athletes stats
		String tempName = randAthlete.getName();
	
		
		int tempOffence = randAthlete.getOffence();
		int tempDefence = randAthlete.getDefence();
		int tempStamina = randAthlete.getStamina();
		String tempPosition = randAthlete.getPosition();
		int tempPrice = randAthlete.getPrice();
		
		// Set the text of the text fields
		txtName.setText(tempName);
		txtOffence.setText(String.valueOf(tempOffence));
		txtDefence.setText(String.valueOf(tempDefence));
		txtStamina.setText(String.valueOf(tempStamina));
		txtPosition.setText(String.valueOf(tempPosition));
		txtPrice.setText(String.valueOf(tempPrice));
	}
	
    /**
     * Updates and displays the player's current money.
     */
	public void displayPlayerMoney() {
		txtMoney.setText(String.valueOf(gameEnvironment.getPlayer().getMoney()));
	}
	
    /**
     * Updates and displays the number of athletes in the main team.
     */
	public void displayMainTeamSize() {
		txtMainTeamSize.setText(String.valueOf(gameEnvironment.getPlayer().getMainTeamSize()));
	}
	
    /**
     * Returns the JFrame of this GUI.
     *
     * @return the JFrame of this GUI.
     */
	public JFrame getFrame() {
        return this.frmTeamSetUp;
    }
}
