/**
 * Individual Project - HCD Project
 * MachineGUI.java
 * Purpose: This class acts as the GUI class for the CopyMachineProject java project. The GUI acts as the interactive
 * 			medium between the user and the program, and allows the user to select a variety of options when creating copies,
 * 			and also permits the user to scan and send a document to a specified email. Other features are implemented, such
 * 			as button highlighting and other quality of life add-ons that make it easier to interact with the UI.
 * References: JButton Text Manipulation - https://stackoverflow.com/questions/49959836/how-to-add-multiple-line-as-a-text-on-button-in-java-frames
 * 			   Random Integer Import - https://www.educative.io/edpresso/how-to-generate-random-numbers-in-java
 * 			   JOptionPane Change Font - https://stackoverflow.com/questions/26913923/how-do-you-change-the-size-and-font-of-a-joptionpane
 * 
 * @author Ryan Ward
 * @version 1.1 02/11/2021
 */
// Imports
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

// MachineGUI Class
public class MachineGUI extends JFrame {
	// GUI Components
	private JPanel sizeSidePanel, 
		   		   sortStaplePanel, 
		   		   quantityPanel,
				   buttonPanel,
				   topPanel,
				   bottomPanel,
				   sizePanel,
				   sidePanel,
				   sortPanel,
				   staplePanel,
				   loginPanel,
				   successPanel,
				   cardPanel;
	private JLabel quantityLabel,
				   limitLabel,
				   IDLabel,
				   successLabel;
	private JTextArea display;
	private JTextField quantityTF,
			   		   idTF;
	private JButton copyButton, scanButton, loginButton,
					size1Button,size2Button,size3Button,
					side1Button,side2Button,side3Button,side4Button,
					sort1Button,sort2Button,
					staple1Button,staple2Button,staple3Button,staple4Button;
	private CardLayout loginLayout;
	
	// GUI Styling Variables
	private Font buttonFont = new Font("Calibri", Font.PLAIN, 20);
	private Font labelFont = new Font("Calibri", Font.BOLD, 20);
	private Font textFieldFont = new Font("Arial", Font.PLAIN, 22);
	private Font displayFont = new Font("Times New Roman", Font.PLAIN, 18);
	private Font titleFont = new Font("Arial", Font.ITALIC, 20);
	private Font quantityFont = new Font("Calibri", Font.BOLD, 26);
	private Font paneFont = new Font("Calibri", Font.PLAIN, 20);
	private Color lightBlue = new Color(173,216,230);
	private Color darkBlue = new Color(111,187,211);
	private Color panelBC = new Color(187,209,216);
	
	// Class Variables
	private String[] message;
	private ActionHandler listener;
	private User currentUser;
	private User[] users;
	private Random rand = new Random();
	
	// Constructor
	/**
	 * The constructor used to define the MachineGUI class and create the GUI the program
	 */
	public MachineGUI() {
		// Define Class Variables
		message = new String[6];
		message[5] = "";
		listener = new ActionHandler();
		users = new User[5];
		for (int i=1000; i<users.length+999; i++) {
			int limit = rand.nextInt(100);
			if (limit >= 99) {
				users[i-1000] = new User(String.valueOf(i), limit, (100 - limit));
			}
			else {
				users[i-1000] = new User(String.valueOf(i), limit, rand.nextInt(99 - limit) + 1);
			}
		}
		users[4] = new User("1005", 56, 36);
		
		// Create Components
		createLoginPanel();
		add(cardPanel, BorderLayout.NORTH);
		topPanel = createTopPanel();
		add(topPanel, BorderLayout.CENTER);
		bottomPanel = createBottomPanel();
		add(bottomPanel, BorderLayout.EAST);
		updateMessage();
		
		// Manage JFrame
		setTitle("Copy Machine");
		setSize(920,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	// Tier 1 Panel Methods
	/**
	 * Creates the topPanel used in the GUI
	 * @return	The topPanel
	 */
	public JPanel createTopPanel() {
		// Method Variables
		JPanel topP = new JPanel(new GridLayout(3,1));
		TitledBorder border = BorderFactory.createTitledBorder("");
		border.setTitleFont(titleFont);
		topP.setBorder(border);
		topP.setBackground(panelBC);
		
		// Create Inner Panels
		sizeSidePanel = createSizeSidePanel();
		sortStaplePanel = createSortStaplePanel();
		quantityPanel = createQuantityPanel();
				
		// Add Components
		topP.add(sizeSidePanel);
		topP.add(sortStaplePanel);
		topP.add(quantityPanel);
		
		// Return Top Panel
		return topP;
	}
	
	/**
	 * Creates the bottomPanel used in the GUI
	 * @return	The bottomPanel
	 */
	public JPanel createBottomPanel() {
		// Method Variables
		JPanel botP = new JPanel(new BorderLayout());
		botP.setBackground(panelBC);
		
		// Create Inner Panel
		buttonPanel = new JPanel();
		copyButton = new JButton("Copy");
		copyButton.setFont(buttonFont);
		copyButton.setEnabled(false);
		scanButton = new JButton("Scan");
		scanButton.setFont(buttonFont);
		scanButton.setEnabled(false);
		
		// Add Components
		copyButton.addActionListener(listener);
		scanButton.addActionListener(listener);
		buttonPanel.add(copyButton);
		buttonPanel.add(scanButton);
		botP.add(buttonPanel, BorderLayout.SOUTH);
		
		display = new JTextArea(10,18);
		display.setEditable(false);
		display.setFont(displayFont);
		botP.add(display, BorderLayout.CENTER);
		
		// Return Bottom Panel
		return botP;
	}
	
	// Tier 2 Panel Methods
	/**
	 * Creates the sizeSidePanel used in the GUI
	 * @return	The sizeSidePanel
	 */
	public JPanel createSizeSidePanel() {
		// Method Variables
		JPanel SSP = new JPanel(new GridLayout(1,2));
		SSP.setBackground(panelBC);
		
		// Create Inner Panels
		sizePanel = createSizePanel();
		TitledBorder border1 = BorderFactory.createTitledBorder("Choose Size");
		border1.setTitleFont(titleFont);
		sizePanel.setBorder(border1);
		
		sidePanel = createSidePanel();
		TitledBorder border2 = BorderFactory.createTitledBorder("Side Style");
		border2.setTitleFont(titleFont);
		sidePanel.setBorder(border2);
				
		// Add Components
		SSP.add(sizePanel);
		SSP.add(sidePanel);
		
		// Return SizeSide Panel
		return SSP;
	}
	
	/**
	 * Creates the sortStaplePanel used in the GUI
	 * @return	The sortStaplePanel
	 */
	public JPanel createSortStaplePanel() {
		// Method Variables
		JPanel SSP = new JPanel(new GridLayout(1,2));
		SSP.setBackground(panelBC);
		
		// Create Inner Panels
		sortPanel = createSortPanel();
		TitledBorder border1 = BorderFactory.createTitledBorder("Sorting Style");
		border1.setTitleFont(titleFont);
		sortPanel.setBorder(border1);
		
		staplePanel = createStaplePanel();
		TitledBorder border2 = BorderFactory.createTitledBorder("Stapling Style");
		border2.setTitleFont(titleFont);
		staplePanel.setBorder(border2);
				
		// Add Components
		SSP.add(sortPanel);
		SSP.add(staplePanel);
		
		// Return SizeSide Panel
		return SSP;
	}
	
	/**
	 * Creates the quantityPanel used in the GUI
	 * @return	The QuantityPanel
	 */
	public JPanel createQuantityPanel() {
		// Method Variables
		JPanel QPanel = new JPanel();
		QPanel.setLayout(new BoxLayout(QPanel, BoxLayout.Y_AXIS));
		QPanel.setBackground(panelBC);
		QPanel.setAlignmentY(TOP_ALIGNMENT);
		
		// Create Inner Components
		quantityLabel = new JLabel("Quantity:");
		quantityLabel.setFont(quantityFont);
		quantityTF = new JTextField(10);
		quantityTF.setFont(quantityFont);
		quantityTF.addActionListener(listener);
		
		limitLabel = new JLabel("Limit:");
		limitLabel.setFont(quantityFont);
				
		// Add Components
		QPanel.add(quantityLabel);
		QPanel.add(quantityTF);
		QPanel.add(limitLabel);
		
		// Return SizeSide Panel
		message[4] = "Quantity: Unknown";
		return QPanel;
	}
	
	/**
	 * Creates the loginPanel used in the GUI
	 */
	public void createLoginPanel() {
		// Method Variables
		cardPanel = new JPanel();
		loginPanel = new JPanel();
		loginLayout = new CardLayout();
		cardPanel.setLayout(loginLayout);
		loginPanel.setBackground(Color.lightGray);
		
		// Create Inner Components
		IDLabel = new JLabel("ID: ");
		IDLabel.setFont(labelFont);
		idTF = new JTextField(24);
		idTF.setFont(textFieldFont);
		loginButton = new JButton("Login");
		loginButton.setFont(buttonFont);
		loginButton.addActionListener(listener);
		
		// Format Layout
		cardPanel.add(loginPanel, "Login");
		createSuccessPanel();
		cardPanel.add(successPanel, "Success");
				
		// Add Components
		loginPanel.add(IDLabel);
		loginPanel.add(idTF);
		loginPanel.add(loginButton);
	}
	
	/**
	 * Creates the successPanel used in the GUI
	 */
	public void createSuccessPanel() {
		successPanel = new JPanel();
		successPanel.setBackground(Color.lightGray);
		successLabel = new JLabel("Login Successful, Welcome...");
		successPanel.add(successLabel);
	}
	
	// Tier 3 Panel Methods
	/**
	 * Creates the sizePanel used in the GUI
	 * @return	The sizePanel
	 */
	public JPanel createSizePanel() {
		// Method Variables
		JPanel sizeP = new JPanel(new GridLayout(3,1,0,5));
		sizeP.setBackground(panelBC);
		
		// Create Inner Components
		size1Button = new JButton("8 1/2 X 11");
		size1Button.setFont(buttonFont);
		size2Button = new JButton("8 1/2 X 14");
		size2Button.setFont(buttonFont);
		size3Button = new JButton("Letterhead");
		size3Button.setFont(buttonFont);
		
		// Set Default Option
		size1Button.setSelected(true);
		size1Button.setBackground(darkBlue);
		size2Button.setBackground(lightBlue);
		size3Button.setBackground(lightBlue);
		size1Button.addActionListener(listener);
		size2Button.addActionListener(listener);
		size3Button.addActionListener(listener);
				
		// Add Components
		sizeP.add(size1Button);
		sizeP.add(size2Button);
		sizeP.add(size3Button);
		
		// Return Size Panel
		message[0] = "Paper Size: 8 1/2 X 11";
		return sizeP;
	}
	
	/**
	 * Creates the sidePanel used in the GUI
	 * @return	The sidePanel
	 */
	public JPanel createSidePanel() {
		// Method Variables
		JPanel sideP = new JPanel(new GridLayout(2,2,5,5));
		sideP.setBackground(panelBC);
		
		// Create Inner Components
		side1Button = new JButton("<html>1-Sided&emsp&emsp<br>&emsp --> 1-Sided</html>");
		side1Button.setFont(buttonFont);
		side1Button.addActionListener(listener);
		
		side2Button = new JButton("<html>2-Sided&emsp&emsp<br>&emsp --> 1-Sided</html>");
		side2Button.setFont(buttonFont);
		side2Button.addActionListener(listener);
		
		side3Button = new JButton("<html>1-Sided&emsp&emsp<br>&emsp --> 2-Sided</html>");
		side3Button.setFont(buttonFont);
		side3Button.addActionListener(listener);
		
		side4Button = new JButton("<html>2-Sided&emsp&emsp<br>&emsp --> 2-Sided</html>");
		side4Button.setFont(buttonFont);
		side4Button.addActionListener(listener);
		
		// Set Default Option
		side1Button.setSelected(true);
		side1Button.setBackground(darkBlue);
		side2Button.setBackground(lightBlue);
		side3Button.setBackground(lightBlue);
		side4Button.setBackground(lightBlue);
				
		// Add Components
		sideP.add(side1Button);
		sideP.add(side2Button);
		sideP.add(side3Button);
		sideP.add(side4Button);
		
		// Return Side Panel
		message[1] = "Side Style: 1-Sided --> 1-Sided";
		return sideP;
	}
	
	/**
	 * Creates the sortPanel used in the GUI
	 * @return	The sortPanel
	 */
	public JPanel createSortPanel() {
		// Method Variables
		JPanel sortP = new JPanel(new GridLayout(2,1,0,5));
		sortP.setBackground(panelBC);
		
		// Create Inner Components
		sort1Button = new JButton("Original Order (1,2,3,1,2,3)");
		sort1Button.setFont(buttonFont);
		sort1Button.addActionListener(listener);
		sort2Button = new JButton("Group Pages (1,1,2,2,3,3)");
		sort2Button.setFont(buttonFont);
		sort2Button.addActionListener(listener);
		
		// Set Default Option
		sort1Button.setSelected(true);
		sort1Button.setBackground(darkBlue);
		sort2Button.setBackground(lightBlue);
		
		// Add Components
		sortP.add(sort1Button);
		sortP.add(sort2Button);
		
		// Return Sort Panel
		message[2] = "Sorting Style: Original Order";
		return sortP;
	}
	
	/**
	 * Creates the staplePanel used in the GUI
	 * @return	The staplePanel
	 */
	public JPanel createStaplePanel() {
		// Method Variables
		JPanel stapleP = new JPanel(new GridLayout(2,2,5,5));
		stapleP.setBackground(panelBC);
		
		// Create Inner Components
		staple1Button = new JButton("<html>Portrait&emsp<br>&emsp Top-Left</html>");
		staple1Button.setFont(buttonFont);
		staple1Button.addActionListener(listener);
		
		staple2Button = new JButton("<html>Portrait&emsp<br>&emsp Top-Right</html>");
		staple2Button.setFont(buttonFont);
		staple2Button.addActionListener(listener);
		
		staple3Button = new JButton("<html>Landscape&emsp<br>&emsp Top-Left</html>");
		staple3Button.setFont(buttonFont);
		staple3Button.addActionListener(listener);
		
		staple4Button = new JButton("<html>Landscape&emsp<br>&emsp Top-Right</html>");
		staple4Button.setFont(buttonFont);
		staple4Button.addActionListener(listener);
		
		// Set Default Option
		staple1Button.setSelected(true);
		staple1Button.setBackground(darkBlue);
		staple2Button.setBackground(lightBlue);
		staple3Button.setBackground(lightBlue);
		staple4Button.setBackground(lightBlue);
				
		// Add Components
		stapleP.add(staple1Button);
		stapleP.add(staple2Button);
		stapleP.add(staple3Button);
		stapleP.add(staple4Button);		
		
		// Return Staple Panel
		message[3] = "Stapling Style: Portrait Top-Left";
		return stapleP;
	}
	
	// Action Listener
	class ActionHandler implements ActionListener {
		// Action Method
		/**
		 * The default action method used by the ActionListener
		 */
		public void actionPerformed(ActionEvent action) {
			// Check Source
			Object source = action.getSource();
			if (source.equals(copyButton)) {
				JLabel paneLabel = new JLabel("");
				paneLabel.setFont(paneFont);
				try {
					// Get Quantity
					int setQuantity = Integer.parseInt(quantityTF.getText());
					if (setQuantity <= 0) {
						paneLabel.setText("Quantity Value not Sensible");
						JOptionPane.showMessageDialog(null, paneLabel, "Value Error", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					
					// Check Limit
					int pageCount = 2; // This line would be used to obtain the number of pages the user is printing using 
								   	   // the machine's detection software. A default value has been put here to make the 
								       // program run.
					int maxCopies = currentUser.getPageLimit()/pageCount;// Division of 2 integers rounds 
																		   // down to nearest whole number, 
																		   // giving max copies possible
					// Determine Quantity to Print
					int targetCopies,targetPapers;
					if (setQuantity > maxCopies) {
						paneLabel.setText("Due to your available limit, you will only be able to create " + maxCopies + " copies. Proceed?");
						int answer = JOptionPane.showConfirmDialog(null, paneLabel, "Important Message", JOptionPane.OK_CANCEL_OPTION);
						if (answer != 0) {
							// Cancel Process
							paneLabel.setText("Copying Cancelled");
							JOptionPane.showMessageDialog(null, paneLabel, "Process Ended", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						targetCopies = maxCopies;
					}
					else {
						targetCopies = setQuantity;
					}
					targetPapers = targetCopies * pageCount;
						
					// Confirm
					paneLabel.setText("Begin copying documents using selected layout?");
					int finalAnswer = JOptionPane.showConfirmDialog(null, paneLabel, "Confirm Choice", JOptionPane.YES_NO_OPTION);
					if (finalAnswer != 0) {
						paneLabel.setText("Copying Cancelled");
						JOptionPane.showMessageDialog(null, paneLabel, "Process Ended", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					
					// Change User History
					currentUser.setTotalCopies(currentUser.getTotalCopies() + targetCopies);
					if (side1Button.isSelected() || side2Button.isSelected()) {
						// 1-Sided Paper
						currentUser.setPageLimit(currentUser.getPageLimit() - targetPapers);
					}
					else {
						// 2-Sided Paper
						currentUser.setPageLimit(currentUser.getPageLimit() - ((int) Math.ceil(targetPapers/2)));
					}
					
					// Begin Copying - Theses next few lines would call any methods or programs that handle starting the 
					// printing process. In this scenario, targetPapers is the number of sides used in the program. As such, 
					// the total paper quantity used would be dependent on which printing style for sides they have 
					// selected. targetCopies shows the number of copies created, and as such is added to the user's history.
					
					// Update GUI
					paneLabel.setText("Copying complete, please take documents.");
					JOptionPane.showMessageDialog(null, paneLabel, "Process Complete", JOptionPane.INFORMATION_MESSAGE);
					limitLabel.setText("Limit: " + currentUser.getPageLimit());
					message[5] = "Result: Copying Complete";
				} catch (NumberFormatException error) {
					/* Considering the layout of a copying machine's buttons, this should never be an issue. However, 
					it is implemented in case an error occurs when reading the text field.*/
					JOptionPane.showMessageDialog(null, "Invalid Quantity Value", "Quantity Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if (source.equals(scanButton)) {
				// Get Destination
				JLabel inputLabel = new JLabel("Enter Destination");
				inputLabel.setFont(paneFont);
				String destination = JOptionPane.showInputDialog(null, inputLabel);
				if (destination == null) {return;} // User Clicks Window Close Button
				
				// Confirm Scan
				JLabel confirmLabel = new JLabel("Send Document to " + destination + "?");
				confirmLabel.setFont(paneFont);
				int	answer = JOptionPane.showConfirmDialog(null, confirmLabel, "Confirm Scan", JOptionPane.YES_NO_OPTION);
				if (answer != 0) {
					JLabel stopLabel = new JLabel("Scan Cancelled.");
					stopLabel.setFont(paneFont);
					JOptionPane.showMessageDialog(null, stopLabel, "Process Ended", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				/* Scan Document & Send - The next few lines would use the machine's features to scan the provided document, and 
				 * then send it to the entered email address provided by the user.*/
				
				// Update GUI
				JLabel sentLabel = new JLabel("Scanning complete, documents sent to destination.");
				sentLabel.setFont(paneFont);
				JOptionPane.showMessageDialog(null, sentLabel, "Process Complete", JOptionPane.INFORMATION_MESSAGE);
				message[5] = "Result: Scan Complete";
			}
			else if (source.equals(size1Button)) {
				// Size Selected: 8 1/2 X 11 
				setSizeButton(size1Button);
				message[0] = "Paper Size: 8 1/2 X 11";
			}
			else if (source.equals(size2Button)) {
				// Size Selected: 8 1/2 X 14
				setSizeButton(size2Button);
				message[0] = "Paper Size: 8 1/2 X 14";
			}
			else if (source.equals(size3Button)) {
				// Size Selected: Letterhead
				setSizeButton(size3Button);
				message[0] = "Paper Size: Letterhead";
			}
			else if (source.equals(side1Button)) {
				// Side Style Selected: 1 to 1
				setSideButton(side1Button);
				message[1] = "Side Style: 1-Sided --> 1-Sided";
			}
			else if (source.equals(side2Button)) {
				// Side Style Selected: 2 to 1
				setSideButton(side2Button);
				message[1] = "Side Style: 2-Sided --> 1-Sided";
			}
			else if (source.equals(side3Button)) {
				// Side Style Selected: 1 to 2
				setSideButton(side3Button);
				message[1] = "Side Style: 1-Sided --> 2-Sided";
			}
			else if (source.equals(side4Button)) {
				// Side Style Selected: 2 to 2
				setSideButton(side4Button);
				message[1] = "Side Style: 2-Sided --> 2-Sided";
			}
			else if (source.equals(sort1Button)) {
				// Sorting Documents in Original Order
				sort1Button.setSelected(true);
				sort1Button.setBackground(darkBlue);
				sort2Button.setSelected(false);
				sort2Button.setBackground(lightBlue);
				message[2] = "Sorting Style: Original Order";
			}
			else if (source.equals(sort2Button)) {
				// Sorting Documents in Groups
				sort1Button.setSelected(false);
				sort1Button.setBackground(lightBlue);
				sort2Button.setSelected(true);
				sort2Button.setBackground(darkBlue);
				message[2] = "Sorting Style: Group Pages";
			}
			else if (source.equals(staple1Button)) {
				// Staple using Portrait Layout in Top-Left
				setStapleButton(staple1Button);
				message[3] = "Stapling Style: Portrait Top-Left";
			}
			else if (source.equals(staple2Button)) {
				// Staple using Portrait Layout in Top-Right
				setStapleButton(staple2Button);
				message[3] = "Stapling Style: Portrait Top-Right";
			}
			else if (source.equals(staple3Button)) {
				// Staple using Landscape Layout in Top-Left
				setStapleButton(staple3Button);
				message[3] = "Stapling Style: Landscape Top-Left";
			}
			else if (source.equals(staple4Button)) {
				// Staple using Landscape Layout in Top-Right
				setStapleButton(staple4Button);
				message[3] = "Stapling Style: Landscape Top-Right";
			}
			else if (source.equals(loginButton)) {
				// Check if user exists
				String id = idTF.getText();
				int userPos = findUser(id);
				
				// Login
				if (userPos != -1) {
					currentUser = users[userPos];
				}
				else {
					// New User
					JLabel noUserLabel = new JLabel("User ID Doesn't Exist, Creating New User");
					JOptionPane.showMessageDialog(null, noUserLabel, "User Not Found", JOptionPane.INFORMATION_MESSAGE);
					currentUser = new User(id, 100, 0);
				}
				
				// Change GUI
				changeLogPanel();
			}
			else if (source.equals(quantityTF)) {
				// Change Quantity on Display
				message[4] = "Quantity: " + quantityTF.getText();
			}
			else {
				// Action Error
				JLabel errorLabel = new JLabel("Action Could Not Be Processed");
				JOptionPane.showMessageDialog(null, errorLabel, "Button Error", JOptionPane.ERROR_MESSAGE);
				message[5] = "Result: Unable to Complete";
			}
			// Update Display
			updateMessage();
		}
	}
	// End of Class
	
	// Methods
	/**
	 * Change the login panel to welcome user.
	 */
	public void changeLogPanel() {
		successLabel.setText("Login Successful, Welcome User " + currentUser.getUserID());
		successLabel.setFont(titleFont);
		limitLabel.setText("Limit: " + currentUser.getPageLimit());
		loginLayout.last(cardPanel);
		copyButton.setEnabled(true);
		scanButton.setEnabled(true);
	}
	
	/**
	 * Update display message based on buttons selected.
	 */
	public void updateMessage() {
		display.setText(
				message[0] + "\n\n" + message[1] + "\n\n" + message[2] + "\n\n" +
				message[3] + "\n\n" + message[4] + "\n\n" + message[5] + "\n\n"
				);
	}
	
	/**
	 * Find the provided user ID within the known user array.
	 * @param searchID	The ID of our desired user
	 * @return			Returns position of desired user if found, otherwise returns -1 if nonexistant
	 */
	public int findUser(String searchID) {
		for (int i=0; i<users.length; i++) {
			if (users[i].getUserID().equals(searchID)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Changes size button colors based on selection
	 * @param source	Selected size button
	 */
	public void setSizeButton(JButton source) {
		// Remove Current
		size1Button.setSelected(false);
		size2Button.setSelected(false);
		size3Button.setSelected(false);
		size1Button.setBackground(lightBlue);
		size2Button.setBackground(lightBlue);
		size3Button.setBackground(lightBlue);
		
		// Select New
		source.setSelected(true);
		source.setBackground(darkBlue);
	}
	
	/**
	 * Changes side button colors based on selection
	 * @param source	Selected side button
	 */
	public void setSideButton(JButton source) {
		// Remove Current
		side1Button.setSelected(false);
		side2Button.setSelected(false);
		side3Button.setSelected(false);
		side4Button.setSelected(false);
		side1Button.setBackground(lightBlue);
		side2Button.setBackground(lightBlue);
		side3Button.setBackground(lightBlue);
		side4Button.setBackground(lightBlue);
		
		// Select New
		source.setSelected(true);
		source.setBackground(darkBlue);
	}
	
	/**
	 * Changes staple button colors based on selection
	 * @param source	Selected staple button
	 */
	public void setStapleButton(JButton source) {
		// Remove Current
		staple1Button.setSelected(false);
		staple2Button.setSelected(false);
		staple3Button.setSelected(false);
		staple4Button.setSelected(false);
		staple1Button.setBackground(lightBlue);
		staple2Button.setBackground(lightBlue);
		staple3Button.setBackground(lightBlue);
		staple4Button.setBackground(lightBlue);
		
		// Select New
		source.setSelected(true);
		source.setBackground(darkBlue);
	}
	
	// Main Method
	/**
	 * The main method used to begin the program
	 * @param args	Default parameter for main method
	 */
	public static void main(String[] args) {
		// Main Code
		MachineGUI window = new MachineGUI();
	}
}
// End of Class