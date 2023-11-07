package gui.gamehome;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import game.core.Athlete;
import game.core.GameEnvironment;

import javax.swing.JTable;

/**
 * TakeBye is a GUI class that allows the user to take a bye in the game.
 * It shows a table with athletes information and allows the user to perform actions with athletes.
 */
public class TakeBye implements Gui {

    private JFrame frmTakeBye; // The main frame of this GUI.
    private GameEnvironment gameEnvironment; // The game environment instance
    private Integer index = null; // The index of the selected row in the player table

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
                    TakeBye window = new TakeBye(gameEnvironment);
                    window.frmTakeBye.setVisible(true); // Make the window visible
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor that creates a TakeBye GUI object and initializes it.
     * @param gameEnvironment The game environment object
     */
    public TakeBye(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment; 
        initialize(); // Initialize the GUI components
    }

    /**
     * Method that initializes the contents of the frame.
     */
    private void initialize() {
		frmTakeBye = new JFrame();
		frmTakeBye.setTitle("Take Bye");
		frmTakeBye.setBounds(100, 100, 655, 251);
		frmTakeBye.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTakeBye.getContentPane().setLayout(null);
		
			@SuppressWarnings("serial")
			JTable playerTable = new JTable(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null},
				},
				new String[] {
					"Position", "Name", "Offence", "Defence", "Stamina", "is injured"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			playerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			playerTable.getTableHeader().setReorderingAllowed(false);

			// Populate the table
			displayTeam(playerTable);

			// Add your table to a scroll pane in case data exceeds table size
			JScrollPane scrollPane = new JScrollPane(playerTable);
			scrollPane.setBounds(12, 43, 430, 147);  // Set the bounds manually
			frmTakeBye.getContentPane().add(scrollPane);
			playerTable.getSelectionModel().addListSelectionListener(e -> {
	            if (!e.getValueIsAdjusting()) {
	                index = playerTable.getSelectedRow();
	            }
	        });
			JButton btnTrainPlayer = new JButton("TAKE BYE");
			btnTrainPlayer.setBounds(465, 82, 167, 46);
			btnTrainPlayer.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        if (gameEnvironment.getPlayer().getWeeksRemaining() <= 1) {
			            EndScreen endScreen = new EndScreen(gameEnvironment);
			            endScreen.show(endScreen.getFrame());
		        	    close(getFrame());
		                
			        }
			        else {
				        if (index == null) {
				            gameEnvironment.getPlayer().getTeam().fullStamina();
				            updateGameEnvironmentAndSwitchToHome();
				            randomEvent();
				        } else {
				            gameEnvironment.getPlayer().getTeam().fullStamina();
				            Athlete selectedAthlete = gameEnvironment.getPlayer().getTeam().getActiveAthletes().get(index);
				            selectedAthlete.train();
				            updateGameEnvironmentAndSwitchToHome();
				            // Random event call and display
				            randomEvent();
			        }
			        }
			    }
			});
			frmTakeBye.getContentPane().add(btnTrainPlayer);
			JButton btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        MainMenu gameHome = new MainMenu(gameEnvironment);
		        
		        show(gameHome.getFrame());

		        // Close the current Club GUI using the method in the Gui interface
		        close(frmTakeBye);
		        
		    }
		});
		btnBack.setBounds(487, 154, 117, 25);
		frmTakeBye.getContentPane().add(btnBack);
		
		JLabel lblTrainHint = new JLabel("Select Player to Train");
		lblTrainHint.setBounds(30, 12, 167, 15);
		frmTakeBye.getContentPane().add(lblTrainHint);
	}
	
    /**
     * Method that displays the team in a given JTable.
     * @param table The JTable to populate with the team data
     */
	public void displayTeam(JTable table) {
	    // Clear the table
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0);

        for (Athlete athlete :  gameEnvironment.getPlayer().getTeam().getAthletes()) {
            Object[] athleteData = {
                athlete.getPosition(),
                athlete.getName(),
                athlete.getOffence(),
                athlete.getDefence(),
                athlete.getStamina(),
                athlete.isInjured()
            };

	        model.addRow(athleteData);
	    }
	}
	
	/**
     * Method that updates the game environment and switches to the home GUI.
     */
	private void updateGameEnvironmentAndSwitchToHome() {
	    gameEnvironment.getPlayer().setCurrentWeek(gameEnvironment.getPlayer().getCurrentWeek() + 1);
	    gameEnvironment.getPlayer().setRemainingWeeks(gameEnvironment.getPlayer().getWeeksRemaining() - 1);
	    
	    gameEnvironment.updateEnemyTeams(gameEnvironment.getPlayer().getCurrentWeek());
	    
	    MainMenu gameHome = new MainMenu(gameEnvironment);
	    
	    // Update Market
	    updateMarket();
	    
	    show(gameHome.getFrame());

	    // Close the current Club GUI using the method in the Gui interface
	    this.close(frmTakeBye);
	}
	
	/**
     * Method that updates the market in the game environment.
     */
	public void updateMarket() {
		gameEnvironment.updateRandTeam();
		gameEnvironment.updateMarketItems();
	}
	
	 /**
     * Method that calls and outputs a random event in the game.
     */
	public void randomEvent() {
	    // Random Event Call
	    String eventTitle = gameEnvironment.getRandomEvent();

	    // Check if a string was returned
	    if (eventTitle != null && !eventTitle.trim().isEmpty()) {
	        // Display the event title in a message dialog
	        JOptionPane.showMessageDialog(frmTakeBye, eventTitle, "Random Event", JOptionPane.INFORMATION_MESSAGE);
	    }
	}
	
	/**
     * Returns the main JFrame of this GUI.
     * @return The JFrame of this GUI
     */
	public JFrame getFrame() {
        return this.frmTakeBye;
    }
}
