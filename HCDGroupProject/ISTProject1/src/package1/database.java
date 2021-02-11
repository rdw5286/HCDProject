/**
 * Group Project - HCD Project
 * database.java
 * Purpose: This class acts as the database connection for the ISTProject1 java project. The database class performs SQL 
 * queries and connects to the Microsoft Access database stored on the computer/system. The class can return the gifts 
 * obtained by speaker, add/update/search a record, or return the entire database that is set in a specific frame of time.
 * 
 * @author Ryan Ward, <Redacted>, <Redacted>, <Redacted>
 * @version 1.1 02/11/2021
 */

// Package
package package1;

// Imports
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

// database Class
public class database {
	// Private Variables
	static final String DATABASE_URL = "jdbc:ucanaccess://D:/Microsoft Access Files/speaker.accdb";
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	PreparedStatement insertSpkInfo = null;

	// Constructor
	public database() {
		try {
			// Establish connection to database
			System.out.println("Starting database connection");
			connection = DriverManager.getConnection(DATABASE_URL);
			System.out.println("Made a connection");
			// Create statement for querying the database
			statement = connection.createStatement();
			System.out.println("Established statement");

			// Test Query
			resultSet = statement.executeQuery(
					"SELECT spkName, spkEmail,spkOrg,spkTitle, spkAddress, spkCity, month, day, year, fclName, fclCourse, fclSection, semester FROM Info");
			System.out.println("Have a resultSet");
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Methods
	/**
	 * This method adds a record to the database given the parameters passed by the
	 * method containing all necessary information for the row.
	 * 
	 * @param speaker_name       The speaker's name
	 * @param email              The speaker's email
	 * @param organization       The speaker's organization
	 * @param title              The speaker's title
	 * @param a8ddress           The speaker's address
	 * @param city               The speaker's city
	 * @param month              The visiting month
	 * @param day                The visiting day
	 * @param year               The visiting year
	 * @param faculty_name       The faculty's name
	 * @param course             The course name
	 * @param section            The section number
	 * @param semesterSpeakerInv The visiting semester
	 * @param gift               The speaker's gift
	 * @return If the record insertion was successful, the method will return 1. If
	 *         it was unsuccessful, the method will return 0.
	 */
	public int addSpkInfo(String speaker_name, String email, String organization, String title, String a8ddress,
			String city, int month, int day, int year, String faculty_name, String course, String section,
			String semesterSpeakerInv, String gift) {
		int result = 0;
		try {
			// Create Query
			connection = DriverManager.getConnection(DATABASE_URL);
			insertSpkInfo = connection.prepareStatement(
					"INSERT INTO Info (spkName, spkEmail,spkOrg,spkTitle, spkAddress, spkCity, month, day, year, fclName, fclCourse, fclSection, semester,gift)"
							+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			// Set Values
			insertSpkInfo.setString(1, speaker_name);
			insertSpkInfo.setString(2, email);
			insertSpkInfo.setString(3, organization);
			insertSpkInfo.setString(4, title);
			insertSpkInfo.setString(5, a8ddress);
			insertSpkInfo.setString(6, city);
			insertSpkInfo.setInt(7, month);
			insertSpkInfo.setInt(8, day);
			insertSpkInfo.setInt(9, year);
			insertSpkInfo.setString(10, faculty_name);
			insertSpkInfo.setString(11, course);
			insertSpkInfo.setString(12, section);
			insertSpkInfo.setString(13, semesterSpeakerInv);
			insertSpkInfo.setString(14, gift);

			// Execute Query
			result = insertSpkInfo.executeUpdate();
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Insert Failed",
					JOptionPane.ERROR_MESSAGE);
			result = 0;
		} finally {
			try {
				// Check Result
				if (result == 1) {
					JOptionPane.showMessageDialog(null, "Record Insert Completed", speaker_name,
							JOptionPane.INFORMATION_MESSAGE);
				} else if (result == 0) {
					JOptionPane.showMessageDialog(null, "Record Insert Failed", speaker_name,
							JOptionPane.ERROR_MESSAGE);
				}
				// Close Database
				statement.close();
				connection.close();
				System.out.println("Closing Statement and connection to database");
			} catch (SQLException sqlex) {
				JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Close Error",
						JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
		}
		// Return Result
		return result;
	}

	/**
	 * This method will update a row in the database matching the ID given in the
	 * parameters. The rest of the parameters are used to store the new information
	 * to be set in the selected row.
	 * 
	 * @param speaker_name       The speaker's name
	 * @param email              The speaker's email
	 * @param organization       The speaker's organization
	 * @param title              The speaker's title
	 * @param a8ddress           The speaker's address
	 * @param city               The speaker's city
	 * @param month              The visiting month
	 * @param day                The visiting day
	 * @param year               The visiting year
	 * @param faculty_name       The faculty's name
	 * @param course             The course name
	 * @param section            The section number
	 * @param semesterSpeakerInv The visiting semester
	 * @param gift               The speaker's gift
	 * @param rowId              The old record's ID
	 * @return If the record update was successful, the method will return 1. If it
	 *         was unsuccessful, the method will return 0.
	 */
	public int updateInfo(String speaker_name, String email, String organization, String title, String a8ddress,
			String city, int month, int day, int year, String faculty_name, String course, String section,
			String semesterSpeakerInv, String gift, int rowId) {
		PreparedStatement updateInfo = null;
		int result = 1;
		try {
			// Create Query
			updateInfo = connection.prepareStatement(
					"UPDATE Info SET (spkName, spkEmail,spkOrg,spkTitle, spkAddress, spkCity, month, day, year, fclName, fclCourse, fclSection, semester,gift)"
							+ " = (?,?,?,?,?,?,?,?,?,?,?,?,?,?) WHERE (ID) = (?)",
					Statement.RETURN_GENERATED_KEYS);
			// Set Values
			updateInfo.setString(1, speaker_name);
			updateInfo.setString(2, email);
			updateInfo.setString(3, organization);
			updateInfo.setString(4, title);
			updateInfo.setString(5, a8ddress);
			updateInfo.setString(6, city);
			updateInfo.setInt(7, month);
			updateInfo.setInt(8, day);
			updateInfo.setInt(9, year);
			updateInfo.setString(10, faculty_name);
			updateInfo.setString(11, course);
			updateInfo.setString(12, section);
			updateInfo.setString(13, semesterSpeakerInv);
			updateInfo.setString(14, gift);
			updateInfo.setInt(15, rowId);
			
			// Execute Query
			result = updateInfo.executeUpdate();
		} catch(SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Update Failed",
					JOptionPane.ERROR_MESSAGE);
			result = 0;
		} finally {
			try {
				// Check Result
				if(result == 1) {
					JOptionPane.showMessageDialog(null, "Record Update Completed", "Update Complete",
							JOptionPane.INFORMATION_MESSAGE);
				} else if(result == 0) {
					JOptionPane.showMessageDialog(null, "Record Update Failed", "Update Failed", JOptionPane.ERROR_MESSAGE);
				}
				// Close Database
				statement.close();
				connection.close();
				System.out.println("Closing Statement and connection to database");
			} catch (SQLException sqlex) {
				JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Close Error",
						JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
		}
		
		// Return Result
		return result;
	}

	/**
	 * This method searches the database for a record or records that have the matching speaker name and date and returns 
	 * an array of InfoRecord objects listing all records that match the search terms.
	 * 
	 * @param speaker_name	The speaker's name
	 * @param month			The visiting month
	 * @param day			The visiting day
	 * @param year			The visiting year
	 * @param check			True if method is used for getting an old record's position when updating a record, false otherwise
	 * @return	Array of InfoRecord objects matching the search terms provided
	 */
	public ArrayList<InfoRecord> searchInfo(String speaker_name, int month, int day, int year, Boolean check) {
		String searchInfo = null;
		ArrayList<InfoRecord> recordList = null;
		try {
			// Create Query
			searchInfo = "SELECT * FROM Info";
			connection = DriverManager.getConnection(DATABASE_URL);
			statement = connection.createStatement();
			// Execute Query
			ResultSet rs = statement.executeQuery(searchInfo);
			
			// Find Matching Records
			recordList = new ArrayList<InfoRecord>();
			while (rs.next()) {
				if (rs.getString("spkName").equals(speaker_name) && rs.getInt("month") == month
						&& rs.getInt("day") == day && rs.getInt("year") == year) {
					// Create Temporary Variables
					String tempSpkName = rs.getString("spkName");
					String tempSpkEmail = rs.getString("spkEmail");
					String tempSpkOrg = rs.getString("spkOrg");
					String tempSpkTitle = rs.getString("spkTitle");
					String tempSpkAddress = rs.getString("spkAddress");
					String tempSpkCity = rs.getString("spkCity");
					int tempMonth = rs.getInt("month");
					int tempDay = rs.getInt("day");
					int tempYear = rs.getInt("year");
					String tempFclName = rs.getString("fclName");
					String tempFclCourse = rs.getString("fclCourse");
					String tempFclSection = rs.getString("fclSection");
					String tempSemester = rs.getString("semester");
					String tempGift = rs.getString("gift");
					
					// Create Temporary InfoRecord Object
					InfoRecord tempRecord;
					tempRecord = new InfoRecord();
					// Set InfoRecord Values
					tempRecord.setSpeakerInfo(tempSpkName, tempSpkEmail, tempSpkOrg, tempSpkTitle, tempSpkAddress,
							tempSpkCity);
					tempRecord.setFacultyInfo(tempFclName, tempFclCourse, tempFclSection, tempSemester);
					tempRecord.setDate(tempMonth, tempDay, tempYear);
					tempRecord.setGift(tempGift);
					if (check) {
						// Method used to get old record for update
						int tempID = rs.getInt("ID");
						tempRecord.setRowID(tempID);
					}
					
					// Add InfoRecord to Array
					recordList.add(tempRecord);
				}
			}
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Search Failed",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				// Close Database
				statement.close();
				connection.close();
				System.out.println("Closing Statement and connection to database");
			} catch (SQLException sqlex) {
				JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Close Error",
						JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
		}
		
		// Return InfoRecord Array
		return recordList;
	}

	/**
	 * Returns all records in the database that are entered as part of the school year input by the user. The year 
	 * value represents the fall semester year, and all other semesters take place in the following year as usually done.
	 * 
	 * @param year	The year to generate a report for
	 * @return		InfoRecord array containing all appropriate records
	 */
	public ArrayList<InfoRecord> getDatabase(int year) {
		String getInfo = null;
		ArrayList<InfoRecord> recordList = null;
		try {
			// Create Query
			getInfo = "SELECT * FROM Info;";
			connection = DriverManager.getConnection(DATABASE_URL);
			statement = connection.createStatement();
			// Execute Query
			ResultSet rs = statement.executeQuery(getInfo);

			// Find Matching Records
			recordList = new ArrayList<InfoRecord>();
			while (rs.next()) {
				if ((rs.getInt("year") == year && rs.getString("semester").equals("Fall"))
						|| (rs.getInt("year") == year + 1 && rs.getString("semester").equals("Spring"))
						|| (rs.getInt("year") == year + 1 && rs.getString("semester").equals("Summer 1"))
						|| (rs.getInt("year") == year + 1 && rs.getString("semester").equals("Summer 2"))) {
					// Create Temporary Variables
					String tempSpkName = rs.getString("spkName");
					String tempSpkEmail = rs.getString("spkEmail");
					String tempSpkOrg = rs.getString("spkOrg");
					String tempSpkTitle = rs.getString("spkTitle");
					String tempSpkAddress = rs.getString("spkAddress");
					String tempSpkCity = rs.getString("spkCity");
					int tempMonth = rs.getInt("month");
					int tempDay = rs.getInt("day");
					int tempYear = rs.getInt("year");
					String tempFclName = rs.getString("fclName");
					String tempFclCourse = rs.getString("fclCourse");
					String tempFclSection = rs.getString("fclSection");
					String tempSemester = rs.getString("semester");
					String tempGift = rs.getString("gift");
					
					// Create Temporary InfoRecord Object
					InfoRecord tempRecord;
					tempRecord = new InfoRecord();
					// Set InfoRecord Values
					tempRecord.setSpeakerInfo(tempSpkName, tempSpkEmail, tempSpkOrg, tempSpkTitle, tempSpkAddress,
							tempSpkCity);
					tempRecord.setFacultyInfo(tempFclName, tempFclCourse, tempFclSection, tempSemester);
					tempRecord.setDate(tempMonth, tempDay, tempYear);
					tempRecord.setGift(tempGift);
					
					// Add InfoRecord to Array
					recordList.add(tempRecord);
				}
			}
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Return Failed",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				// Close Database
				statement.close();
				connection.close();
				System.out.println("Closing Statement and connection to database");
			} catch (SQLException sqlex) {
				JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Close Error",
						JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
		}
		
		// Return Array
		return recordList;
	}
	
	/**
	 * Returns a String array containing all gifts received by a specified speaker
	 * 
	 * @param speaker_name	Name of the speaker searched for
	 * @return	Array of previous gifts received by the speaker
	 */
	public ArrayList<String> returnGift(String speaker_name) {
		String returnGift;
		ArrayList<String> prevGifts = null;
		try {
			// Create Query
			returnGift = "SELECT spkName, gift FROM Info";
			connection = DriverManager.getConnection(DATABASE_URL);
			statement = connection.createStatement();
			// Execute Query
			ResultSet resultSet = statement.executeQuery(returnGift);
			
			// Find Matching Records
			prevGifts = new ArrayList<String>();
			while (resultSet.next()) {
				if (resultSet.getString("spkName").equals(speaker_name)) {
					prevGifts.add(resultSet.getString("gift"));
				}
			}
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Gift Selection Failed",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				// Close Database
				statement.close();
				connection.close();
				System.out.println("Closing Statement and connection to database");
			}catch (SQLException sqlex) {
				JOptionPane.showMessageDialog(null, sqlex.getMessage(), "Database Close Error",
						JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
		}
		
		// Return Gifts
		return prevGifts;
	}
}
// End of Class