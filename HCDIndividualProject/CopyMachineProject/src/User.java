/**
 * Individual Project - HCD Project
 * User.java
 * Purpose: This class acts as the User object for the CopyMachineProject program. This class creates an instance of a user, 
 * 			containing the ID, total copies, and number of pages left which can be used in a given period. The preset users 
 * 			are created randomly in the GUI class, and any new users will be made using the constructor provided. While the period
 * 			itself and count/limit reset are not implemented, methods and code can be made to implement such features fairly 
 * 			easily without editing the original code.
 * References: JButton Text Manipulation - https://stackoverflow.com/questions/49959836/how-to-add-multiple-line-as-a-text-on-button-in-java-frames
 * 			   Random Integer Import - https://www.educative.io/edpresso/how-to-generate-random-numbers-in-java
 * 			   JOptionPane Change Font - https://stackoverflow.com/questions/26913923/how-do-you-change-the-size-and-font-of-a-joptionpane
 * 
 * @author Ryan Ward
 * @version 1.1 02/11/2021
 */

// User Class
public class User {
	// Private Variables
	private String userID;
	private int pageLimit;
	private int totalCopies;
	
	// Constructor
	/**
	 * The constructor used to initialize a User object
	 * @param id	  ID of user
	 * @param limit	  Paper limit of user
	 * @param total	  Total copies of user
	 */
	public User(String id, int limit, int total) {
		userID = id;
		pageLimit = limit;
		totalCopies = total;
	}
	
	// Accessors
	/**
	 * @return the totalCopies
	 */
	public int getTotalCopies() {
		return totalCopies;
	}
	
	/**
	 * @return the pageLimit
	 */
	public int getPageLimit() {
		return pageLimit;
	}
	
	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}
	
	// Mutators
	/**
	 * @param pageLimit the pageLimit to set
	 */
	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	/**
	 * @param totalCopies the totalCopies to set
	 */
	public void setTotalCopies(int totalCopies) {
		this.totalCopies = totalCopies;
	}
	
	/**
	 * This method should be used sparingly, as ID's are important identifiers for the system and 
	 * may cause issues if changed
	 * @param newID
	 */
	public void setUserID(String newID) {
		this.userID = newID;
	}
}
// End of Class