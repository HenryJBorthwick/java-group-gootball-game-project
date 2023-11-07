package gui.gamehome;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import game.core.Athlete;
import game.core.GameEnvironment;
import game.core.Items;
import game.core.Team;

import javax.swing.JButton;
import javax.swing.JTable;

/**
 * Class representing the Club GUI. This class includes the main() method which launches the GUI.
 * It sets up a JFrame with tables displaying players, reserve players, and inventory of the club.
 */
public class Club implements Gui {

	private JFrame frame;
	private GameEnvironment gameEnvironment;
	private int indexMain= -1;
	private int indexRes= -1;
	private int indexItem= -1;

    /**
     * Main method to launch the GUI. It is used for debugging purposes.
     * @param args Command line arguments
     */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameEnvironment gameEnvironment = new GameEnvironment();
					Club window = new Club(gameEnvironment);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    /**
     * Constructor for the Club GUI.
     * @param gameEnvironment The current game environment.
     */
	public Club(GameEnvironment gameEnvironment) {
	   this.gameEnvironment = gameEnvironment;
		initialize();
	}

    /**
     * Initialize the contents of the frame. This includes setting up the layout, labels,
     * buttons, and tables of the GUI.
     */
	@SuppressWarnings("serial")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 714, 563);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblStartTeam = new JLabel(gameEnvironment.getTeamName());
		lblStartTeam.setBounds(244, 11, 122, 15);
		frame.getContentPane().add(lblStartTeam);
		
		JLabel lblInventory = new JLabel("Inventory");
		lblInventory.setBounds(212, 326, 95, 15);
		frame.getContentPane().add(lblInventory);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		    	MainMenu gameHome = new MainMenu(gameEnvironment);			
		    	
		    	show(gameHome.getFrame());

		        // Close the current Club GUI using the method in the Gui interface
		        close(frame);
		    
			}
		});
		btnBack.setBounds(508, 433, 133, 44);
		frame.getContentPane().add(btnBack);
		
		DefaultTableModel model = new DefaultTableModel(
			    new Object[][] {},
			    new String[] {"Position", "Name", "Offence", "Defence", "Stamina", "is injured"}
			);

			JTable table_Main = new JTable(new DefaultTableModel(
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
			table_Main.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table_Main.getTableHeader().setReorderingAllowed(false);

			// Populate the table
			displayTeam(table_Main, gameEnvironment.getPlayer().getTeam());

		
			JScrollPane scrollPaneMain = new JScrollPane(table_Main);
			scrollPaneMain.setBounds(28, 43, 524, 116); 
			frame.getContentPane().add(scrollPaneMain);
			table_Main.getSelectionModel().addListSelectionListener(e -> {
	            if (!e.getValueIsAdjusting()) {
	                indexMain = table_Main.getSelectedRow();
	            }
	        });
			DefaultTableModel model_Res = new DefaultTableModel(
				    new Object[][] {},
				    new String[] {"Position", "Name", "Offence", "Defence", "Stamina", "is injured"}
				);

				JTable table_Res = new JTable(new DefaultTableModel(
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
				table_Res.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table_Res.getTableHeader().setReorderingAllowed(false);

				// Populate the Reserve Team table
				displayTeam(table_Res, gameEnvironment.getPlayer().getReserveTeam());

				JScrollPane scrollPaneRes = new JScrollPane(table_Res);
				scrollPaneRes.setBounds(28, 195, 524, 116);
				frame.getContentPane().add(scrollPaneRes);

				table_Res.getSelectionModel().addListSelectionListener(e -> {
				    if (!e.getValueIsAdjusting()) {
				        indexRes = table_Res.getSelectedRow();
				    }
				});
				DefaultTableModel model_Item = new DefaultTableModel(
				    new Object[][] {},
				    new String[] {"ID", "Name", "Effect", "Effect Size", "Price"}
				);

					JTable table_Item = new JTable(new DefaultTableModel(
						new Object[][] {
							{null, null, null, null, null},
						},
						new String[] {
							"ID", "Name", "Effect", "Effect Size", "Price"
						}
					) {
						boolean[] columnEditables = new boolean[] {
							false, false, false, false, false
						};
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					});
					table_Item.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table_Item.getTableHeader().setReorderingAllowed(false);

					// Populate the Item table
					displayItemTable(gameEnvironment.getPlayer().getInventory(), table_Item );  

					JScrollPane scrollPaneItem = new JScrollPane(table_Item);
					scrollPaneItem.setBounds(28, 352, 416, 116); 
					frame.getContentPane().add(scrollPaneItem);

					table_Item.getSelectionModel().addListSelectionListener(e -> {
					    if (!e.getValueIsAdjusting()) {
					        indexItem= table_Item.getSelectedRow();
					        
					    }
					});
					
        
		JLabel Res_Team = new JLabel("Res Team");
		Res_Team.setBounds(261, 170, 72, 14);
		frame.getContentPane().add(Res_Team);
		
	
		JButton btnSwap = new JButton("Swap");
		btnSwap.setBounds(575, 166, 95, 38);
		frame.getContentPane().add(btnSwap);
		btnSwap.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (indexMain == -1) {
		            JOptionPane.showMessageDialog(frame, "No player from the main team is selected for swapping.", "Warning", JOptionPane.WARNING_MESSAGE);
		        } else if (indexRes == -1) {
		            JOptionPane.showMessageDialog(frame, "No player from the reserve team is selected for swapping.", "Warning", JOptionPane.WARNING_MESSAGE);
		        } else if (indexItem != -1) {
		            JOptionPane.showMessageDialog(frame, "An item is currently selected. Please unselect the item before swapping players.", "Warning", JOptionPane.WARNING_MESSAGE);
		        } else {
		        	gameEnvironment.getPlayer().swapAthletes(indexMain, indexRes);
		        	
		        	
		        	model.setRowCount(0);
		            model_Res.setRowCount(0);


	               // get updated tables 
		           displayTeam(table_Main, gameEnvironment.getPlayer().getTeam());
		           displayTeam(table_Res, gameEnvironment.getPlayer().getReserveTeam());

	               // Reset indices
	               indexMain = -1;
	               indexRes = -1;
		        }
		    }
		});
		JButton btnUseItem = new JButton("USE Item");
		btnUseItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (indexItem == -1) { // No item selected 
		            JOptionPane.showMessageDialog(frame, "No item is selected. Please select an item.", "Warning", JOptionPane.WARNING_MESSAGE);
		        } else if (indexRes != -1 && indexMain != -1) {// one player from both teams selected
		            JOptionPane.showMessageDialog(frame, "You cannot select players from both teams.", "Warning", JOptionPane.WARNING_MESSAGE);
		        } else if (indexMain == -1 && indexRes == -1) { // no player selected 
		            JOptionPane.showMessageDialog(frame, "No player is selected. Please select a player.", "Warning", JOptionPane.WARNING_MESSAGE);
		        } else {
		        	
		            if (indexMain != -1) { // main team
		                int indexTeam = 1;
		                gameEnvironment.getPlayer().useItem(indexMain, indexTeam, indexItem);
		                gameEnvironment.getPlayer().getInventory().remove(indexItem);
		                
		            } else { // res team
		                int indexTeam = 2;
		                gameEnvironment.getPlayer().useItem(indexRes, indexTeam, indexItem);
		                gameEnvironment.getPlayer().getInventory().remove(indexItem);
		            }
		            
		            //clear data
		            model.setRowCount(0);
		            model_Res.setRowCount(0);
		            model_Item.setRowCount(0);

		            // Repopulate the tables
		            displayTeam(table_Main, gameEnvironment.getPlayer().getTeam());
			        displayTeam(table_Res, gameEnvironment.getPlayer().getReserveTeam());
		            displayItemTable(gameEnvironment.getPlayer().getInventory(), table_Item);

		            // Reset indices
		            indexMain = -1;
		            indexRes = -1;
		            indexItem = -1;
		            
		        }
		    }
		});
		btnUseItem.setBounds(493, 354, 108, 38);
		frame.getContentPane().add(btnUseItem);
		
		frame.getContentPane().addMouseListener(new MouseAdapter() { // if blank mouse click unselected all tables 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        if (e.getSource() == frame.getContentPane()) {
		            indexMain = -1;
		            indexRes = -1;
		            indexItem = -1;

		            // If you also want to clear the table selection visually:
		            table_Main.clearSelection();
		            table_Res.clearSelection();
		            table_Item.clearSelection();
		        }
		    }
		});

	}
	
    /**
     * This method displays a list of items in a given JTable.
     * The method receives a list of Items and a JTable as parameters, then populates the table with the item data.
     * @param marketItems The list of items to be displayed in the table.
     * @param table The JTable in which the items will be displayed.
     */
	public void displayItemTable(ArrayList<Items> marketItems, JTable table) {
	    // Clear the table
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0);

	    
	    int id = 1; // Initialize ID
	    for (Items genItem : marketItems) {
	        Object[] itemData = {
	            id,
	            genItem.getName(),
	            genItem.getEffect(),
	            genItem.getEffectSize(),
	            genItem.getPrice(), 
	        };

	        model.addRow(itemData);
	        id++; // Increment ID for each item
	    }
	}
	
    /**
     * This method displays a team of athletes in a given JTable.
     * The method receives a Team object and a JTable as parameters, then populates the table with the athlete data.
     * @param table The JTable in which the team will be displayed.
     * @param team The team of athletes to be displayed in the table.
     */
	public void displayTeam(JTable table,Team team) {
	    // Clear the table
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0);

        for (Athlete athlete :  team.getAthletes()) {
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
     * This method returns the JFrame of the GUI.
     * @return The JFrame of the GUI.
     */
	public JFrame getFrame() {
        return this.frame;
    }
}
