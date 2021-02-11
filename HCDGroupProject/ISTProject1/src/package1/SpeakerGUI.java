/**
 * Group Project - HCD Project
 * SpeakerGUI.java
 * Purpose: This class acts as the GUI class for the ISTProject1 java project. The interface outputs a window containing 
 * textfields and buttons for the user to interact with depending on their needs. The user can enter information for a record, 
 * or update/search a record that is in the database. The user can clear the text fields if desired, or generate a report for 
 * the school year.
 * 
 * @author Ryan Ward, <Redacted>, <Redacted>, <Redacted>
 * @version 1.1 02/11/2021
 */

// Package
package package1;

// Imports
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

// SpeakerGUI Class
public class SpeakerGUI extends JFrame {
	// GUI Components
	private JLabel name,
    			   email,
    			   organization,
    			   title,
    			   address,
    			   city,
    			   vDate,
    			   course,
    			   section,
    			   faculty_name,
    			   semester,
    			   exampleDate;
    
	private JTextField spkNameTF,
        			   spkEmailTF,
        			   spkOrgTF,
        			   spkTitleTF,
        			   spkAddressTF,
        			   spkCityTF,
        			   monthTF,
        			   dayTF,
        			   yearTF,
        			   fclNameTF,
        			   fclCourseTF,
        			   fclSectionTF; 

	private JButton submitButton, 
     				reportButton, 
     				searchButton, 
     				clearButton, 
     				updateButton;
	private JTextArea display;
	private ButtonGroup optionGroup = new ButtonGroup();
	private JRadioButton semesterOption[] = new JRadioButton[4];
	private String semesterOptionLabels[] = {"Fall","Spring","Summer 1","Summer 2"};

	private JPanel enterPersonPanel,
    			   radioButtonPanel, 
    			   displayPanel,
    			   buttonPanel;
	
	// Private Variables
	private int oldRecordPosition;
	private Boolean check;
	private String oldGift;
	
	// Constructor
	/**
	 * SpeakerGUI Constructor used to initialize and create the interface for the program
	 */
	public SpeakerGUI() {
		// Create JFrame
		super("School of Business Administration");
		setLayout(new GridLayout(4, 4, 4, 4));
		
		// Create Panels
		enterPersonPanel = createEnterContent();
		add(enterPersonPanel);
		radioButtonPanel = createRadioButtonPanel();
		add(radioButtonPanel);
		displayPanel = createDisplayPanel();
		add(displayPanel);
		buttonPanel = createButtonPanel();
		add(buttonPanel);
		
		// Set Class Variables
		check = false;
		oldGift = "";
		oldRecordPosition = -1;
		
		// Setup JFrame
		setSize(512, 512);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	// Panel Methods
	/**
	 * Creates the speaker info panel containing all labels and textfields regarding the content input for the speaker
	 * @return The speaker panel
	 */
	public JPanel createEnterContent() 
 	{
 		// Define Labels
		name = new JLabel("Name: ",SwingConstants.RIGHT);
 		organization = new JLabel("Organization: ",SwingConstants.RIGHT);
 		address = new JLabel("Address: ",SwingConstants.RIGHT);
 		vDate = new JLabel("Visiting Date: ",SwingConstants.RIGHT);
 		title = new JLabel("Title: ",SwingConstants.RIGHT);
 		email = new JLabel("Email: ",SwingConstants.RIGHT);
 		city = new JLabel("City: ",SwingConstants.RIGHT);
 		exampleDate = new JLabel("Month/Day/Year");
 		
 		// Define TextFields
 		spkNameTF = new JTextField(25);
 		spkEmailTF = new JTextField(25);		
 		spkOrgTF = new JTextField(25);
 		spkTitleTF = new JTextField(25);
 		spkAddressTF = new JTextField(25);
 		spkCityTF = new JTextField(25);
 		monthTF = new JTextField(2);
 		dayTF = new JTextField(2);
 		yearTF = new JTextField(4);
 		
 		// Add Components to Panel
        enterPersonPanel = new JPanel();
 		enterPersonPanel.setLayout(new GridLayout(4,6,4,4));
 		enterPersonPanel.setBorder(BorderFactory.createTitledBorder("Speaker Information"));
 		enterPersonPanel.setBackground(Color.lightGray);
 		enterPersonPanel.add(name);
 	    enterPersonPanel.add(spkNameTF);
 	    enterPersonPanel.add(email);
		enterPersonPanel.add(spkEmailTF);
 		enterPersonPanel.add(organization);
 		enterPersonPanel.add(spkOrgTF);
 		enterPersonPanel.add(title);
 		enterPersonPanel.add(spkTitleTF);
 		enterPersonPanel.add(address);
 	    enterPersonPanel.add(spkAddressTF);
 		enterPersonPanel.add(city);
 		enterPersonPanel.add(spkCityTF);
 		enterPersonPanel.add(vDate);
 		
 		// Date TextFields
 		JPanel datePanel = new JPanel();
 		datePanel.setLayout(new GridLayout(1,3,3,1));
 		datePanel.setBackground(Color.lightGray);
 		datePanel.add(monthTF);
 		datePanel.add(dayTF);
 		datePanel.add(yearTF);
 	    enterPersonPanel.add(datePanel);
 	    enterPersonPanel.add(exampleDate);
 	    
 	    // Return Panel
 		return enterPersonPanel;
 	} 
    
	/**
	 * Creates the faculty info panel containing all input spaces for the information required by the faculty
	 * @return The faculty panel
	 */
    public JPanel createRadioButtonPanel() 
 	{
    	// Define Labels
    	faculty_name = new JLabel("Name: ",SwingConstants.RIGHT);
  		course = new JLabel("Course: ",SwingConstants.RIGHT);
  		section = new JLabel("Section: ",SwingConstants.RIGHT);
 		semester = new JLabel("Semester: ",SwingConstants.RIGHT);
 		
 		// Define TextFields
  		fclNameTF = new JTextField(20);
  		fclCourseTF = new JTextField(20);		
  		fclSectionTF = new JTextField(20);
 		
  		// Define Panel
 		radioButtonPanel = new JPanel();
 		radioButtonPanel.setLayout(new GridLayout(1,2));
 		radioButtonPanel.setBorder(BorderFactory.createTitledBorder("Faculty Information"));
 		radioButtonPanel.setBackground(Color.lightGray);
 		
 		// Define TextField Panel & Add Components
 		JPanel fclEntryPanel = new JPanel();
 		fclEntryPanel.setLayout(new GridLayout(3,2,4,10));
 		fclEntryPanel.setBackground(Color.lightGray);
 		fclEntryPanel.add(faculty_name);
 		fclEntryPanel.add(fclNameTF);
 		fclEntryPanel.add(course); 		
 		fclEntryPanel.add(fclCourseTF);
 		fclEntryPanel.add(section);
 		fclEntryPanel.add(fclSectionTF);
 		radioButtonPanel.add(fclEntryPanel);
        
 		// Define Semester Panel & Add Components
    	JPanel semesterPanel = new JPanel();
    	semesterPanel.setLayout(new GridLayout(4,2));
 		semesterPanel.setBackground(Color.lightGray);
    	semesterPanel.add(semester);
 		for(int i = 0; i < semesterOption.length-1; i++)
 		{
 			semesterOption[i] = new JRadioButton(semesterOptionLabels[i]);
 			semesterOption[i].setBackground(Color.lightGray);
 			semesterPanel.add(semesterOption[i]);
 	 		semesterPanel.add(new JLabel(""));
 			optionGroup.add(semesterOption[i]);
 		}
 		int i2 = semesterOption.length-1;
 		semesterOption[i2] = new JRadioButton(semesterOptionLabels[i2]);
 		semesterOption[i2].setBackground(Color.lightGray);
 		semesterPanel.add(semesterOption[i2]);
 		optionGroup.add(semesterOption[i2]);
 		semesterOption[0].setSelected(true);
 		radioButtonPanel.add(semesterPanel);
 		
 		// Return Panel
 		return radioButtonPanel;
 	}
    
    /**
     * Creates the button panel used to store all buttons which perform the program's actions
     * @return The button panel
     */
    public JPanel createButtonPanel() 
 	{
 		// Create Panel
    	ButtonHandler handler = new ButtonHandler();
 		buttonPanel = new JPanel();
 		buttonPanel.setLayout(new GridLayout(4,6,7,8));
 		buttonPanel.setBorder(BorderFactory.createTitledBorder(""));
 		buttonPanel .setBackground(Color.lightGray);
 		
 		// Add Components
 		submitButton = new JButton("Submit");
 		buttonPanel.add(submitButton);
 		submitButton.addActionListener(handler);
 		
 		reportButton = new JButton("Create Report");
 		buttonPanel.add(reportButton);
 		reportButton.addActionListener(handler);
 		
 		searchButton = new JButton("Search");
 		buttonPanel.add(searchButton);
 		searchButton.addActionListener(handler);
 		
 		updateButton = new JButton("Update");
 		buttonPanel.add(updateButton);
 		updateButton.addActionListener(handler);
 		
 		clearButton = new JButton("Clear");
 		buttonPanel.add(clearButton);
 		clearButton.addActionListener(handler);
 		
 		// Return Panel
 		return buttonPanel;
 	}
    
    /**
     * Creates the display panel used by the search method, returning the information on the record found
     * @return The display panel
     */
 	public JPanel createDisplayPanel()
 	{
 		// Create Panel & Add Components
 		displayPanel = new JPanel();
 		displayPanel.setBorder(BorderFactory.createTitledBorder(""));
 		displayPanel.setBackground(Color.LIGHT_GRAY);
 		display = new JTextArea(10,30);
 		display.setEditable(false);
 		JScrollPane scroll = new JScrollPane(display);
 		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
 		scroll.setPreferredSize(new Dimension(500,100));
 		displayPanel.add(scroll);
 		
 		// Return Panel
 		return displayPanel;
 	}
	
	// ButtonHandler Class
	class ButtonHandler implements ActionListener {
		/**
		 * Default method used to catch all actions performed by the program
		 */
		public void actionPerformed(ActionEvent e) {
			// Open Database
			database infoDB = new database();
			
			// Determine Source
			Object source = e.getSource();
			if (submitButton.equals(source)) {
				// Submit Information
				try {
					// Check Text Fields
					String[] entries = {spkNameTF.getText().trim(), spkEmailTF.getText().trim(),spkCityTF.getText().trim(),
							spkAddressTF.getText().trim(),spkTitleTF.getText().trim(),spkOrgTF.getText().trim(),
							fclNameTF.getText().trim(),fclCourseTF.getText().trim(),fclSectionTF.getText().trim()};
					for (String entry: entries) {
						if (entry.equals("")) {
							JOptionPane.showMessageDialog(null, "A Text Field is Empty", "Empty Field", JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
					
					// Get Speaking Date
					int month = Integer.parseInt(monthTF.getText());
					int day = Integer.parseInt(dayTF.getText());
					int year = Integer.parseInt(yearTF.getText());
					// Check Speaking Date
					if(!checkDate(month,day,year)) {
						return;
					}
						
					// Format Speaking Date
					String monthStr, dayStr, yearStr;
					if (month < 10) {
						monthStr = "0" + month;
					}
					else {
						monthStr = "" + month;
					}
					if (day < 10) {
						dayStr = "0" + day;
					}
					else {
						dayStr = "" + day;
					}
					yearStr = "" + year;
					// Set Speaking Date & Current Date
					String myDate = yearStr + "-" + monthStr + "-" + dayStr + "T07:00:00.00";
					LocalDateTime localDateTime = LocalDateTime.parse(myDate);
					long speakTime = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
					Date date = new Date();
					long curTime = date.getTime();
					
					// Check Speaking Date VS Current Date
					final int TIME_DIF = 1000*(7*24*60*60);
					System.out.println(TIME_DIF);
					if (TIME_DIF > (speakTime - curTime)) {
						JOptionPane.showMessageDialog(null, "Current date must be at least 1 week before speaking date.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
					}
					else {
						// Create Object & Set Info
						InfoRecord newRecord = new InfoRecord();
						newRecord.setSpeakerInfo(spkNameTF.getText(),spkEmailTF.getText(),spkOrgTF.getText(),
								spkTitleTF.getText(), spkAddressTF.getText(),spkCityTF.getText());
						String semester = "";
						for (int i = 0; i<semesterOption.length; i++) {
							if (semesterOption[i].isSelected()) {
								semester = semesterOption[i].getText();
							}
						}
						newRecord.setFacultyInfo(fclNameTF.getText(),fclCourseTF.getText(),fclSectionTF.getText(),semester);
						newRecord.setDate(month,day,year);
						
						// Determine Submit Style
						if (check) {
							// Modify Database
							newRecord.setGift(oldGift);
							infoDB.updateInfo(newRecord.getSpeaker_name(), newRecord.getEmail(), newRecord.getOrganization(), 
									newRecord.getTitle(), newRecord.getAddress(), newRecord.getCity(), month, day, year,
									newRecord.getFaculty_name(), newRecord.getCourse(), newRecord.getSection(), 
									newRecord.getSemesterSpeakerInv(), newRecord.getGift(), oldRecordPosition);
							
							// Display Recorded Information
							JOptionPane.showMessageDialog(null, "Record Updated Successfully.\n\n Recorded Info:\n" + newRecord.toString(), "Record Updated", JOptionPane.INFORMATION_MESSAGE);
							
							// Reset Program State
							check = false;
						}
						else {
							// Choose Gift
							ArrayList<String> prevGifts = infoDB.returnGift(newRecord.getSpeaker_name());
							if (prevGifts != null) {
								newRecord.setGift(chooseGift(prevGifts));
						
								// Save Information
								infoDB.addSpkInfo(newRecord.getSpeaker_name(), newRecord.getEmail(), newRecord.getOrganization(), 
									newRecord.getTitle(), newRecord.getAddress(), newRecord.getCity(), month, day, year,
									newRecord.getFaculty_name(), newRecord.getCourse(), newRecord.getSection(), 
									newRecord.getSemesterSpeakerInv(), newRecord.getGift());
								
								// Display Recorded Information
								JOptionPane.showMessageDialog(null, "Record Submitted Successfully & Permit Request Sent.\n\n Recorded Info:\n" + newRecord.toString(), "Record Submitted", JOptionPane.INFORMATION_MESSAGE);
							} 
							else {
								JOptionPane.showMessageDialog(null, "Unable to Add Record", "Program Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						
						// Clear Text Fields
						clearTFS();
					}
				} catch(NumberFormatException error) {
					JOptionPane.showMessageDialog(null, error.getMessage(), "Invalid Date", JOptionPane.ERROR_MESSAGE);
				} catch(Exception error) {
					JOptionPane.showMessageDialog(null, error.getMessage(), "Invalid Info", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if (clearButton.equals(source)) {
				// Clear Text Fields
				clearTFS();
			}
			else if (reportButton.equals(source)) {
				 // Creates a new InfoRecord array object called recordList
				 int reportYear = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Report Year (2020-2021 -> 2020)", "Generate Report", JOptionPane.QUESTION_MESSAGE));
				 ArrayList<InfoRecord> recordList = infoDB.getDatabase(reportYear);
				 
				 if (recordList != null) {
					 // Declaring a new empty string to hold all of the records 
					 String list = "";
					 // For loop to loop through each row of database
					 for (int i = 0; i < recordList.size(); i++) 
					 {
						 //assigning each record to list and adding a new line
						 list = list + "Record #" + (i+1) + "\n" + recordList.get(i).toString() + "\n\n\n"; 
					 }
				 
					 // Creating a new BufferedWriter object and creating a try catch
					 // Creating a new file called Record.txt if it does not already exist
					 try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\Record.txt"), "utf-8"))) {
						 writer.write(list); //writing all of the records to the file 
					 } catch (IOException f) {
						 JOptionPane.showMessageDialog(null, f.getMessage(), "Unable to Write to File", JOptionPane.ERROR_MESSAGE);
						 f.printStackTrace();
					 }
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "Unable to Generate Report", "Program Error", JOptionPane.ERROR_MESSAGE);
				 }
			}
			else if (searchButton.equals(source)) {
				// Search Speaker
				String searchName = JOptionPane.showInputDialog(null, "Enter Speaker Name:", "Search Terms", JOptionPane.QUESTION_MESSAGE);
				String searchDate = "";
				int searchMonth = -1, searchDay = -1, searchYear = -1;
				try {	
					// These lines make up a form for inputting the date
					searchDate = JOptionPane.showInputDialog(null, "Enter Visiting Date (YYYY-MM-DD):", "Search Terms", JOptionPane.QUESTION_MESSAGE);
					LocalDateTime searchDateForm = LocalDateTime.parse(searchDate+"T07:00:00.00");
					searchMonth = searchDateForm.getMonthValue();
					searchDay = searchDateForm.getDayOfMonth();
					searchYear = searchDateForm.getYear();   
					
					// If statement that checks if the date is in the valid format
					if (!checkDate(searchMonth, searchDay, searchYear)) {
						// Invalid Date
						searchDate = "";
					}
				} catch (Exception e1) {
					searchDate = "";
				}
				while (searchDate.equals("")) {
					try {
						searchDate = JOptionPane.showInputDialog(null, "Enter Valid Visiting Date (YYYY-MM-DD):", "Invalid Entry", JOptionPane.WARNING_MESSAGE);
						LocalDateTime searchDateForm = LocalDateTime.parse(searchDate+"T07:00:00.00");
						searchMonth = searchDateForm.getMonthValue();
						searchDay = searchDateForm.getDayOfMonth();
						searchYear = searchDateForm.getYear();
						if (!checkDate(searchMonth, searchDay, searchYear)) {
							// Invalid Date
							searchDate = "";
						}
					} catch(Exception e2) {
						searchDate = "";
					}
				}
				
				// Find Record(s)
				ArrayList<InfoRecord> searchRecords = infoDB.searchInfo(searchName, searchMonth, searchDay, searchYear, false);
				// Determine Search Result
				if (searchRecords != null) {
					if (searchRecords.size() == 0) {
						// No Matching Record
						JOptionPane.showMessageDialog(null, "No Records Match Search Terms, Canceling Search", "Search Result", JOptionPane.INFORMATION_MESSAGE);
					}
					else if (searchRecords.size() == 1) {
						// One Matching Record
						display.setText(searchRecords.get(0).toString()); 
					}
					else {
						// Multiple Matching Records
						int pos;
						String[] options = new String[searchRecords.size()];
						for (int i = 0; i < searchRecords.size(); i++) {
							options[i] = "Course: " + searchRecords.get(i).getCourse() + "-" + searchRecords.get(i).getSection();
						}
						// Ask user to select correct record.
						pos = JOptionPane.showOptionDialog(null, "Multiple Records Found, Choose Class & Section", "Multiple Records", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
						
						// Modify Program State
						if (pos != -1) {
							display.setText(searchRecords.get(pos).toString()); 
						}
						else {
							JOptionPane.showMessageDialog(null, "No Selected Record, Cancelling Search", "Search Result", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Unable to Search for Record", "Program Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				// Get Search Terms for Update
				String searchName = JOptionPane.showInputDialog(null, "Enter Old Speaker Name:", "Search Terms", JOptionPane.QUESTION_MESSAGE);
				String searchDate = "";
				int searchMonth = -1, searchDay = -1, searchYear = -1;
				// Check Date
				try {
					searchDate = JOptionPane.showInputDialog(null, "Enter Old Visiting Date (YYYY-MM-DD):", "Search Terms", JOptionPane.QUESTION_MESSAGE);
					LocalDateTime searchDateForm = LocalDateTime.parse(searchDate+"T07:00:00.00");
					searchMonth = searchDateForm.getMonthValue();
					searchDay = searchDateForm.getDayOfMonth();
					searchYear = searchDateForm.getYear();
					if (!checkDate(searchMonth, searchDay, searchYear)) {
						// Invalid Date
						searchDate = "";
					}
				} catch (Exception e1) {
					searchDate = "";
				}
				// Loops if Date is Invalid
				while (searchDate.equals("")) {
					try {
						searchDate = JOptionPane.showInputDialog(null, "Enter Valid Visiting Date (YYYY-MM-DD):", "Invalid Entry", JOptionPane.WARNING_MESSAGE);
						LocalDateTime searchDateForm = LocalDateTime.parse(searchDate+"T07:00:00.00");
						searchMonth = searchDateForm.getMonthValue();
						searchDay = searchDateForm.getDayOfMonth();
						searchYear = searchDateForm.getYear();
						if (!checkDate(searchMonth, searchDay, searchYear)) {
							// Invalid Date
							searchDate = "";
						}
					} catch(Exception e2) {
						searchDate = "";
					}
				}
				
				// Find Record(s)
				ArrayList<InfoRecord> oldRecords = infoDB.searchInfo(searchName, searchMonth, searchDay, searchYear, true);
				// Determine Search Result
				if (oldRecords != null) {
					if (oldRecords.size() == 0) {
						// No Matching Record
						JOptionPane.showMessageDialog(null, "No Records Match Search Terms, Cancelling Update", "Update Result", JOptionPane.INFORMATION_MESSAGE);
					}
					else if (oldRecords.size() == 1) {
						// One Matching Record
						oldRecordPosition = oldRecords.get(0).getRowID();
						oldGift = oldRecords.get(0).getGift();
						check = true;
						// Fill in Text Fields
						fillFields(oldRecords.get(0));
					}
					else {
						// Multiple Matching Records
						int pos;
						String[] options = new String[oldRecords.size()];
						for (int i = 0; i < oldRecords.size(); i++) {
							options[i] = "Course: " + oldRecords.get(i).getCourse() + "-" + oldRecords.get(i).getSection();
						}
						// Ask user to select correct record.
						pos = JOptionPane.showOptionDialog(null, "Multiple Records Found, Choose Class & Section", "Multiple Records", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
						
						// Modify Program State
						if (pos != -1) {
							oldRecordPosition = oldRecords.get(pos).getRowID();
							oldGift = oldRecords.get(pos).getGift();
							check = true;
							// Fill in Text Fields
							fillFields(oldRecords.get(pos));
						}
						else {
							JOptionPane.showMessageDialog(null, "No Selected Record, Cancelling Update", "Update Result", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Unable to Update Record", "Program Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	// Methods
	/**
	 * Clears the text fields
	 */
	public void clearTFS() {
		spkNameTF.setText("");
		spkEmailTF.setText("");
		spkOrgTF.setText("");
		spkTitleTF.setText("");
		spkAddressTF.setText("");
		spkCityTF.setText("");
		fclNameTF.setText("");
		fclCourseTF.setText("");
		fclSectionTF.setText("");
		monthTF.setText("");
		dayTF.setText("");
		yearTF.setText("");
		semesterOption[0].setSelected(true);
	}
	
	/**
	 * Determines which gift to give a speaker when visiting
	 * 
	 * @param gifts List of old gifts the speaker has received
	 * @return The selected gift for the speaker's visit
	 */
	public String chooseGift(ArrayList<String> gifts) {
		String gift;
		if (gifts.size() > 2) {
			gift = gifts.get(gifts.size()-3) + "";
		}
		else if (gifts.size() == 2) {
			gift = "PS Portfolio Binder";
		}
		else if (gifts.size() == 1) {
			gift = "Compact Umbrella with PS Logo";
		}
		else {
			gift = "PS Coffee Mug with Hershey Kisses";
		}
		return gift;
	}
	
	/**
	 * This method checks the values provided for the speaking date and determines if said date exists.
	 * 
	 * @param month	Month of Speaking Date
	 * @param day	Day of Speaking Date
	 * @param year	Year of Speaking Date
	 * @return		Validity of Date (True or False)
	 */
	public Boolean checkDate(int month, int day, int year) {
		// Check Date Validity
		if (month < 1 || month > 12 || day > 31 || day < 1 || year < 1970) {
			// Invalid General Date
			JOptionPane.showMessageDialog(null, "Nonexistant Date Entered", "Invalid Date", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if ((month == 4 || month == 6 || month == 9 || month == 11) && (day > 30)) {
			// Invalid 30 Day Date
			JOptionPane.showMessageDialog(null, "Nonexistant Date Entered", "Invalid Date", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (month == 2 && ((day > 29 && (year % 4 == 0)) || (day > 28 && (year % 4 != 0)))) {
			// Invalid February Date
			JOptionPane.showMessageDialog(null, "Nonexistant Date Entered", "Invalid Date", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		// Date is Valid
		return true;
	}
	
	/**
	 * Fills out the text fields using the information given in the passed record. Used for the update method to see the 
	 * information in an old record.
	 * 
	 * @param record  Old Record
	 */
	public void fillFields(InfoRecord record) {
		// Text Fields
		spkNameTF.setText(record.getSpeaker_name());
		spkEmailTF.setText(record.getEmail());
		spkOrgTF.setText(record.getOrganization());
		spkCityTF.setText(record.getCity());
		spkTitleTF.setText(record.getTitle());
		spkAddressTF.setText(record.getAddress());
		fclNameTF.setText(record.getFaculty_name());
		fclCourseTF.setText(record.getCourse());
		fclSectionTF.setText(record.getSection());
		dayTF.setText(record.getDay()+"");
		monthTF.setText(record.getMonth()+"");
		yearTF.setText(record.getYear()+"");
		
		// Radio Buttons
		String semChoice = record.getSemesterSpeakerInv();
		for (int i = 0; i < semesterOptionLabels.length; i++) {
			if (semesterOptionLabels[i].equals(semChoice)) {
				semesterOption[i].setSelected(true);
			}
		}
	}
	
	/**
	 * Initiates the program
	 * 
	 * @param args	Default parameter for main method
	 */
	public static void main(String[] args) {
		// Create Interface
		SpeakerGUI window = new SpeakerGUI();
	}
}
// End of Class