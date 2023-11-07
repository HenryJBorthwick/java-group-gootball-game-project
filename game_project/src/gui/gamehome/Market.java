package gui.gamehome;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import game.core.*;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;

/**
 * The Market class represents the market interface within the game. 
 * It allows players to buy and sell items and athletes. 
 * This class extends the Gui interface.
 */
public class Market implements Gui {

    private JFrame frame;
    private JTable tblShopAthletes;
    private JTable tblShopItems;
    private JTable tblSellMain;
    private JTable tblSellReserve;
    private JTable tblSellItems;
    private JComboBox<String> cboxTeamSelect;
    private GameEnvironment gameEnvironment;
    private JTextField txtMoney;

    /**
     * Launch the application.
     * This is the main method that starts the application. It creates an instance of the GameEnvironment and Market
     * and makes the window visible.
     * 
     * @param args Command line arguments
     */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameEnvironment gameEnvironment = new GameEnvironment();
					Market window = new Market(gameEnvironment);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * The constructor for the Market class.
	 * It initializes the components and updates the GUI according to the game environment.
	 * @param gameEnvironment The current game environment
	 */
	public Market(GameEnvironment gameEnvironment) {
		// Carry across previous instance of gameEnvironment
		this.gameEnvironment = gameEnvironment;
		
	    initialize();

	    // Make it so that only one col is able to be selected across the buy tables
	    tblShopAthletes.getSelectionModel().addListSelectionListener(e -> {
	        if (!e.getValueIsAdjusting() && tblShopAthletes.getSelectedRow() != -1) {
	            tblShopItems.clearSelection();
	        }
	    });

	    tblShopItems.getSelectionModel().addListSelectionListener(e -> {
	        if (!e.getValueIsAdjusting() && tblShopItems.getSelectedRow() != -1) {
	            tblShopAthletes.clearSelection();
	        }
	    });
	    
	    // Make it so that only one col is able to be selcted across the sell tables
	    tblSellMain.getSelectionModel().addListSelectionListener(e -> {
	        if (!e.getValueIsAdjusting() && tblSellMain.getSelectedRow() != -1) {
	            tblSellReserve.clearSelection();
	            tblSellItems.clearSelection();
	        }
	    });

	    tblSellReserve.getSelectionModel().addListSelectionListener(e -> {
	        if (!e.getValueIsAdjusting() && tblSellReserve.getSelectedRow() != -1) {
	            tblSellMain.clearSelection();
	            tblSellItems.clearSelection();
	        }
	    });

	    tblSellItems.getSelectionModel().addListSelectionListener(e -> {
	        if (!e.getValueIsAdjusting() && tblSellItems.getSelectedRow() != -1) {
	            tblSellMain.clearSelection();
	            tblSellReserve.clearSelection();
	        }
	    });
	    
	    // important method update calls 
	    
	    this.updatePlayerMoney();
	    
	    this.updateAthleteMarket();
	    
	    this.updateItemMarket();
	    
	    this.updatePlayersMain();
	    
	    this.updatePlayersReserve();
	    
	    this.updatePlayersInventory();
	    	        
		// Add a mouse listener to the frame to clear selection when clicked outside of the tables
		frame.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        tblShopAthletes.clearSelection();
		        tblShopItems.clearSelection();
		        tblSellMain.clearSelection();
		        tblSellReserve.clearSelection();
		        tblSellItems.clearSelection();
		    }
		});
	}

	/**
	 * This method initializes the contents of the frame.
	 * It sets up the tables, buttons, and other components within the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1061, 549);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane sPaneShopAthlete = new JScrollPane();
		sPaneShopAthlete.setBounds(10, 58, 393, 109);
		frame.getContentPane().add(sPaneShopAthlete);
		
		tblShopAthletes = new JTable();
	    tblShopAthletes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
	    tblShopAthletes.getTableHeader().setReorderingAllowed(false);
	    sPaneShopAthlete.setViewportView(tblShopAthletes);
		tblShopAthletes.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Position", "Name", "Offence", "Defence", "Stamina", "Buy Price"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JScrollPane sPaneShopItems = new JScrollPane();
		sPaneShopItems.setBounds(12, 223, 391, 109);
		frame.getContentPane().add(sPaneShopItems);
		
	    tblShopItems = new JTable();
	    tblShopItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
	    tblShopItems.getTableHeader().setReorderingAllowed(false);
	    sPaneShopItems.setViewportView(tblShopItems);
		tblShopItems.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, ""},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "Name", "Effect", "Size", "Buy Price"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		sPaneShopItems.setViewportView(tblShopItems);
		
		JLabel lblAthleteMarket = new JLabel("Athlete Market:");
		lblAthleteMarket.setBounds(10, 42, 111, 15);
		frame.getContentPane().add(lblAthleteMarket);
		
		JLabel lblItemMarket = new JLabel("Item Market:");
		lblItemMarket.setBounds(10, 206, 111, 15);
		frame.getContentPane().add(lblItemMarket);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Open GameHome GUI
				MainMenu gameHome = new MainMenu(gameEnvironment);
				
				// Open Market GUI
				show(gameHome.getFrame());

		        // Close the current Club GUI using the method in the Gui interface
		        close(frame);
			}
		});
		btnBack.setBounds(10, 484, 117, 25);
		frame.getContentPane().add(btnBack);
		
		cboxTeamSelect = new JComboBox<>();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(new String[] { "Main", "Reserve" });
		cboxTeamSelect.setModel(model);
		cboxTeamSelect.setBounds(10, 360, 162, 25);
		frame.getContentPane().add(cboxTeamSelect);
		
		JLabel lblSelectTeam = new JLabel("Prefered Athlete Location:");
		lblSelectTeam.setBounds(10, 343, 162, 15);
		frame.getContentPane().add(lblSelectTeam);
		
		JButton btnBuy = new JButton("Buy");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get selected Athlete or item
				int selectedAthleteRow = tblShopAthletes.getSelectedRow();
				int selectedItemRow = tblShopItems.getSelectedRow();
				
				// Get selected location preference
				String selectedTeam = (String) cboxTeamSelect.getSelectedItem();
				
				// If selected athlete
				if(selectedAthleteRow != -1) {
					// Get the selected Athlete using index
					Athlete selectedAthlete = gameEnvironment.getCurrentRandTeam().getAthletes().get(selectedAthleteRow);
					
					// Get Athletes price
					int athletePrice = selectedAthlete.getPrice();
					
					// Check if both teams are full
					if (isMainTeamFull() && isReserveTeamFull()) {
						JOptionPane.showMessageDialog(frame, "Both teams are full. You cannot buy more athletes.", "Warning", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					// Check if reserve is full
					if (selectedTeam.equals("Reserve Team") && isReserveTeamFull()) {
						JOptionPane.showMessageDialog(frame, "The Reserve Team is full. You cannot add more athletes to it.", "Warning", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					// Selected team is not full

					// Try to purchase the athlete
					boolean outcome = gameEnvironment.purchase(selectedAthlete, athletePrice, selectedTeam);

					// Check if purchase was successful
					if (outcome) {
						// Update the athlete market
						updateAthleteMarket();
						
						// Refresh the players main team sell
						updatePlayersMain();
						
						// Refresh the players reserve team sell
						updatePlayersReserve();
						
				        // Refresh players money
				        updatePlayerMoney();
						
					} else {
						// Show warning message
						JOptionPane.showMessageDialog(frame, "You do not have enough money to buy this athlete.", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
				
				// If selected Item
				else if(selectedItemRow != -1) {
				    // An Item has been selected
				    Items selectedItem = gameEnvironment.getCurrentMarketItems().get(selectedItemRow);
				    
				    // Get item price
				    int itemPrice = selectedItem.getPrice();

				    // Try to purchase the item
				    boolean outcome = gameEnvironment.purchase(selectedItem, itemPrice);
				    
				    if(outcome) {
				    	// Update the items in market
				    	updateItemMarket();
				    	
				    	// Update the players sell
				    	updatePlayersInventory();
				    	
				        // Refresh players money
				        updatePlayerMoney();
				    	
				    } else {
				        // If the purchase was unsuccessful, show a warning dialog
				        JOptionPane.showMessageDialog(frame, "You do not have enough money to buy this item!", "Warning", JOptionPane.WARNING_MESSAGE);
				    }
				}

				else {
					// No athlete or item selected. 
					// Perform necessary actions, like showing a dialog saying "Please select an athlete or an item to purchase."
					JOptionPane.showMessageDialog(frame, "Please select an athlete or an item to buy.", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnBuy.setBounds(10, 417, 89, 23);
		frame.getContentPane().add(btnBuy);
		
		JScrollPane sPaneSellMain = new JScrollPane();
		sPaneSellMain.setBounds(564, 56, 432, 97);
		frame.getContentPane().add(sPaneSellMain);

		tblSellMain = new JTable();
		tblSellMain.getTableHeader().setReorderingAllowed(false);
		tblSellMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblSellMain.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Position", "Name", "Offence", "Defence", "Stamina", "Sell Price"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		sPaneSellMain.setViewportView(tblSellMain);
		
		JScrollPane sPaneSellReserve = new JScrollPane();
		sPaneSellReserve.setBounds(564, 180, 432, 97); 
		frame.getContentPane().add(sPaneSellReserve);

		tblSellReserve = new JTable();
		tblSellReserve.getTableHeader().setReorderingAllowed(false);
		tblSellReserve.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblSellReserve.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Position", "Name", "Offence", "Defence", "Stamina", "Sell Price"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		sPaneSellReserve.setViewportView(tblSellReserve);
		
		JScrollPane sPaneSellItems = new JScrollPane();
		sPaneSellItems.setBounds(564, 310, 391, 130);
		frame.getContentPane().add(sPaneSellItems);

		tblSellItems = new JTable();
		tblSellItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		tblSellItems.getTableHeader().setReorderingAllowed(false);
		sPaneSellItems.setViewportView(tblSellItems);
		tblSellItems.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, ""},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "Name", "Effect", "Size", "Sell Price"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JButton btnSell = new JButton("Sell");
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    // Get selected Athlete or item
			    int selectedMainTeamRow = tblSellMain.getSelectedRow();
			    int selectedReserveTeamRow = tblSellReserve.getSelectedRow();
			    int selectedItemRow = tblSellItems.getSelectedRow();

			 // If selected athlete from main team
			    if(selectedMainTeamRow != -1) {
			    	
			        // Get price from selected row in price column in main team table
			        int sellPrice = (int) tblSellMain.getValueAt(selectedMainTeamRow, 5);

			        // Update the players money 
			        gameEnvironment.getPlayer().updateMoney(sellPrice);

			        // Remove the athlete from main team
			        gameEnvironment.getPlayer().getTeam().getAthletes().remove(selectedMainTeamRow);

			        // Refresh the table to reflect changes
			        updatePlayersMain();
			        
			        // Refresh players money
			        updatePlayerMoney();
		
			    } else if(selectedReserveTeamRow != -1) {
			        // Get price from selected row in price column in reserve team table
			        int sellPrice = (int) tblSellReserve.getValueAt(selectedReserveTeamRow, 5);

			        // Update the players money 
			        gameEnvironment.getPlayer().updateMoney(sellPrice);

			        // Remove the athlete from main team
			        gameEnvironment.getPlayer().getReserveTeam().getAthletes().remove(selectedReserveTeamRow);

			        // Refresh the table to reflect changes
			        updatePlayersReserve();
			        
			        // Refresh players money
			        updatePlayerMoney();

			    } else if(selectedItemRow != -1) {
			        // Get price from selected row in price column in inventory table
			        int sellPrice = (int) tblSellItems.getValueAt(selectedItemRow, 4);

			        // Update the players money 
			        gameEnvironment.getPlayer().updateMoney(sellPrice);

			        // Remove the athlete from main team
			        gameEnvironment.getPlayer().getInventory().remove(selectedItemRow);

			        // Refresh the table to reflect changes
			        updatePlayersInventory();
			        
			        // Refresh players money
			        updatePlayerMoney();

			    } else {
			        // No athlete or item selected.
			        // Perform necessary actions, like showing a dialog saying "Please select an athlete or an item to sell."
			        JOptionPane.showMessageDialog(frame, "Please select an athlete or an item to sell.", "Warning", JOptionPane.WARNING_MESSAGE);
			    }
			}

		});
		btnSell.setBounds(564, 459, 117, 25);
		frame.getContentPane().add(btnSell);
		
		JLabel lblMainTeam = new JLabel("Main Team:");
		lblMainTeam.setBounds(564, 42, 137, 15);
		frame.getContentPane().add(lblMainTeam);
		
		JLabel lblMoney = new JLabel("Money:");
		lblMoney.setBounds(12, 0, 70, 15);
		frame.getContentPane().add(lblMoney);
		
		txtMoney = new JTextField();
		txtMoney.setEditable(false);
		txtMoney.setBounds(73, -2, 114, 19);
		frame.getContentPane().add(txtMoney);
		txtMoney.setColumns(10);
		
		JLabel lblReserveTeam = new JLabel("Reserve Team:");
		lblReserveTeam.setBounds(564, 165, 117, 15);
		frame.getContentPane().add(lblReserveTeam);
		
		JLabel lblInventory = new JLabel("Inventory:");
		lblInventory.setBounds(564, 297, 117, 15);
		frame.getContentPane().add(lblInventory);
	}
	
    /**
     * This method displays the details of each athlete in a team in a table.
     * The price of the athlete is modified based on the provided modifier.
     *
     * @param team The team whose athletes are to be displayed.
     * @param table The table where the athletes are to be displayed.
     * @param priceModifier The factor by which the price of the athletes is to be modified.
     */
	public void displayTeamTable(Team team, JTable table, double priceModifier) {
	    // Clear the table
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0);

	    // Display/Update Athlete Market Table
	 // Initialize ID
	    for (Athlete athlete : team.getAthletes()) {
	        Object[] athleteData = {
	            athlete.getPosition(),
	            athlete.getName(),
	            athlete.getOffence(),
	            athlete.getDefence(),
	            athlete.getStamina(),
	            (int)(athlete.getPrice() * priceModifier) // modify the price
	        };

	        model.addRow(athleteData);
	       // Increment ID for each athlete
	    }
	}
	
    /**
     * This method displays the details of each item in the provided list in a table.
     * The price of the item is modified based on the provided modifier.
     *
     * @param marketItems The list of items to be displayed.
     * @param table The table where the items are to be displayed.
     * @param priceModifier The factor by which the price of the items is to be modified.
     */
	public void displayItemTable(ArrayList<Items> marketItems, JTable table, double priceModifier) {
	    // Clear the table
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0);

	    // Display/Update Item Market Table
	    int id = 1; // Initialize ID
	    for (Items genItem : marketItems) {
	        Object[] itemData = {
	            id,
	            genItem.getName(),
	            genItem.getEffect(),
	            genItem.getEffectSize(),
	            (int)(genItem.getPrice() * priceModifier) // modify the price
	        };

	        model.addRow(itemData);
	        id++; // Increment ID for each item
	    }
	}
	
    /**
     * This method checks if the main team is full.
     *
     * @return True if the main team is full, False otherwise.
     */
    public boolean isMainTeamFull() {
        // Replace this with your actual code to check if main team is full
        if (gameEnvironment.getPlayer().getMainTeamSize() == 5)
            return true;
        else
            return false;
    }
    
    /**
     * This method checks if the reserve team is full.
     *
     * @return True if the reserve team is full, False otherwise.
     */
    public boolean isReserveTeamFull() {
        // Replace this with your actual code to check if reserve team is full
        if (gameEnvironment.getPlayer().getReserveTeamSize() == 5)
            return true;
        else 
            return false;
    }
    
    /**
     * This method updates the athlete market with the current random team.
     */
    public void updateAthleteMarket() {
	    // Get the current random team
	    Team marketTeam = gameEnvironment.getCurrentRandTeam();
	    
	    // Display Athletes available in market
	    this.displayTeamTable(marketTeam, tblShopAthletes, 1);
    }
    
    /**
     * This method updates the item market with the current market items.
     */
    public void updateItemMarket() {
	    // Get the current market items
	    ArrayList<Items> marketItems = gameEnvironment.getCurrentMarketItems();
	    
	    // Display Item available in market
	    this.displayItemTable(marketItems, tblShopItems, 1); 
    }
    
    /**
     * This method updates the players main team display.
     */
    public void updatePlayersMain() {
	    // Get players current main team
	    Team currentMainTeam = gameEnvironment.getPlayer().getTeam();
	    
	    // Display Team Athletes for sale
	    this.displayTeamTable(currentMainTeam, tblSellMain, 0.7);
    }
    
    /**
     * This method updates the players reserve team display.
     */
    public void updatePlayersReserve() {
    	// Get players current main team
	    Team reserveMainTeam = gameEnvironment.getPlayer().getReserveTeam();
	    
	    // Display current reserve athletes for sale
	    this.displayTeamTable(reserveMainTeam, tblSellReserve, 0.7);
    }
    
    /**
     * This method updates the players inventory display.
     */
    public void updatePlayersInventory() {
	    // Get players current inventory
	    ArrayList<Items> inventory = gameEnvironment.getPlayer().getInventory();
	    
	    // Display inventory items for sale
	    this.displayItemTable(inventory, tblSellItems, 0.7);
    }
    
    /**
     * This method updates the player's current money display.
     */
    public void updatePlayerMoney() {
        // Get players current money
        int playerMoney = gameEnvironment.getPlayer().getMoney();

        // Display available money 
        txtMoney.setText(String.valueOf(playerMoney));
    }
    
    /**
     * This method returns the current JFrame.
     *
     * @return The current JFrame.
     */
    public JFrame getFrame() {
        return this.frame;
    }
}
