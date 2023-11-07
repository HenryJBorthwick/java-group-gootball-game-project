package gui.setup;
import gui.gamehome.Gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JSlider;
import javax.swing.JTextField;

import game.core.*;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The GameSetUp class represents the game configuration GUI window shown to the user.
 */
public class GameSetUp implements Gui {

	JFrame frmGameSetUp;
	private JTextField txtSeason;
	private JTextField txtDifficulty;
	private GameEnvironment gameEnvironment;
	
	
	/**
    * Main method for debugging. Launches the application.
    * makes a new GameEnvironment() to run
    * 
    *  @param args command-line arguments (not used)
    */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameEnvironment gameEnvironment = new GameEnvironment();
					GameSetUp gameSetUp = new GameSetUp(gameEnvironment);
					 gameSetUp.frmGameSetUp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	 /**
     * Constructor of the GameSetUp class. Takes a  GameEnvironment instance on input then saves it locally then calls the initialize() method.
     * 
     * @param gameEnvironment an instance of the GameEnvironment class.
     */
	public GameSetUp(GameEnvironment gameEnvironment) {
		// Carry across previous instance of gameEnvironment
		this.gameEnvironment = gameEnvironment;
		initialize();
	}

	/**
	 * Initializes the contents of the GameSetUp frame. This includes setup for various components such as labels, 
	 * text fields, sliders and buttons along with their respective functionalities. The layout and properties 
	 * for each component are also set in this method. Listeners for slider interactions and button clicks are 
	 * also implemented within this method.
	 */
	private void initialize() {
		frmGameSetUp = new JFrame();
		frmGameSetUp.setTitle("Game set up");
		frmGameSetUp.setBounds(100, 100, 450, 300);
		frmGameSetUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGameSetUp.getContentPane().setLayout(null);
		
		JLabel lblTeamName = new JLabel("Team name:");
		lblTeamName.setBounds(32, 50, 131, 14);
		frmGameSetUp.getContentPane().add(lblTeamName);
		
		JLabel lblSeasonLength = new JLabel("Season Length:");
		lblSeasonLength.setBounds(32, 109, 131, 14);
		frmGameSetUp.getContentPane().add(lblSeasonLength);
		
		JLabel lblDifficulty = new JLabel("Difficulty:");
		lblDifficulty.setBounds(66, 147, 78, 14);
		frmGameSetUp.getContentPane().add(lblDifficulty);
		
		JFormattedTextField frmtdtxtfldEnterTeamName = new JFormattedTextField();
		frmtdtxtfldEnterTeamName.setText("enter team name");
		frmtdtxtfldEnterTeamName.setBounds(132, 45, 217, 26);
		frmGameSetUp.getContentPane().add(frmtdtxtfldEnterTeamName);
		
		// Season Slider Length
		JSlider sldSeason = new JSlider();
		sldSeason.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Increase or decrease values as slider is clicked
				int number;
				
				number = sldSeason.getValue();
				txtSeason.setText(Integer.toString(number)); //set the text to new length
			}
		});
		sldSeason.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				//Increase or decrease values as slider is dragged
				int number;
				
				number = sldSeason.getValue();
				txtSeason.setText(Integer.toString(number));
			}
		});
		sldSeason.setValue(5);  //set min and max for season length
		sldSeason.setMinimum(5);
		sldSeason.setMaximum(15);
		sldSeason.setBounds(148, 109, 200, 26);
		frmGameSetUp.getContentPane().add(sldSeason);
		
		txtSeason = new JTextField();
		txtSeason.setText("5");
		txtSeason.setEditable(false);
		txtSeason.setBounds(360, 109, 46, 26);
		frmGameSetUp.getContentPane().add(txtSeason);
		txtSeason.setColumns(10);
		
		// Difficulty Slider Stuff
		JSlider sldDifficulty = new JSlider();
		
		sldDifficulty.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        updateDifficultyLabel(sldDifficulty, txtDifficulty);
		    }
		});
		sldDifficulty.addMouseMotionListener(new MouseMotionAdapter() {
		    @Override
		    public void mouseDragged(MouseEvent e) {
		        updateDifficultyLabel(sldDifficulty, txtDifficulty);
		    }
		});
		sldDifficulty.setMinimum(1);
		sldDifficulty.setMaximum(3);
		sldDifficulty.setValue(1);
		sldDifficulty.setBounds(148, 147, 200, 26);
		frmGameSetUp.getContentPane().add(sldDifficulty);

		txtDifficulty = new JTextField();
		txtDifficulty.setText("Easy");
		txtDifficulty.setEditable(false);
		txtDifficulty.setBounds(360, 145, 86, 20);
		frmGameSetUp.getContentPane().add(txtDifficulty);
		txtDifficulty.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Open Welcome GUI
				Welcome welcome = new Welcome();
			    welcome.show(welcome.getFrame());
			    //Close GameSetUp GUI
			    close(getFrame());
			}
		});
		btnBack.setBounds(81, 221, 117, 25);
		frmGameSetUp.getContentPane().add(btnBack);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Get the team name from the form
		        String teamName = frmtdtxtfldEnterTeamName.getText();

		        // Validate team name length
		        if (teamName.length() < 3 || teamName.length() > 15) {
		            JOptionPane.showMessageDialog(null, "The team name should be between 3 and 15 characters long.", "Input Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Validate team name characters
		        if (!teamName.matches("[a-zA-Z0-9_ ]*")) {
		            JOptionPane.showMessageDialog(null, "The team name should not include special characters.", "Input Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Get the season length from the form
		        int seasonLength = sldSeason.getValue();

		        // Get the difficulty from the form
		        int difficulty = sldDifficulty.getValue();

		        // Set team name
		        gameEnvironment.setTeamName(teamName);

		        //GUI Version 
		        gameEnvironment.getPlayer().setRemainingWeeks(seasonLength);

		        // Set difficulty
		        gameEnvironment.setDifficulty(difficulty);

		        // Create the new TeamSetUp GUI
		        TeamSetUp teamSetUp = new TeamSetUp(gameEnvironment);

		        // Show the new GUI using the method in the Gui interface
		        teamSetUp.show(teamSetUp.getFrame());

		     // Close the current GameSetUp GUI using the method in the Gui interface
		        GameSetUp.this.close(GameSetUp.this.getFrame());
		    }
		});
		btnNext.setBounds(289, 221, 117, 25);
		frmGameSetUp.getContentPane().add(btnNext);

	}
	
	/**
	 * This method updates the difficulty label based on the current value of the difficulty slider.
	 * The difficulty label is displayed on the GUI and indicates the selected difficulty level.
	 *
	 * @param slider  the JSlider component representing the difficulty slider
	 * @param textField the JTextField component displaying the selected difficulty
	 */
	private void updateDifficultyLabel(JSlider slider, JTextField textField) {
	    int value = slider.getValue();
	    String difficulty;
	    switch (value) {
	        case 1:
	            difficulty = "Easy";
	            break;
	        case 2:
	            difficulty = "Medium";
	            break;
	        case 3:
	            difficulty = "Hard";
	            break;
	        default:
	            throw new IllegalArgumentException("Unexpected value: " + value);
	    }
	    textField.setText(difficulty);
	}

	/**
	 * This method returns the JFrame of the current GUI.
	 *
	 * @return the JFrame component of the current GUI
	 */
	public JFrame getFrame() {
	    return frmGameSetUp;
	}

}