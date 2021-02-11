/**
 * Group Project - HCD Project
 * InfoRecord.java
 * Purpose: This class acts as the InfoRecord object for the ISTProject1 java project. This object holds the information 
 * entered in the GUI, storing the information provided for the speaker & faculty, as well as the semester and date. This 
 * object can return and set any of it's class variables, and has methods to set groups of data based on their 
 * relevance. This class also has a variable and methods that store the row ID found when searching/updating records in 
 * the database.
 * 
 * @author Ryan Ward, <Redacted>, <Redacted>, <Redacted>
 * @version 1.1 02/11/2021
 */

// Package
package package1;

// InfoRecord Class
public class InfoRecord {
	// Private Variables
	private String speaker_name;
	private String title;
	private String organization;
	private String address;
	private String city;
	private String email;
	private int day;
	private int month;
	private int year;
	private int rowID;
	private String gift;
	private String faculty_name;
	private String course;
	private String section;
	private String semesterSpeakerInv;
	
	// Constructor
	/* Utilizing default constructor */
	
	// Methods
	/**
	 * Sets the variables related to speaker information
	 * 
	 * @param speaker_name	Speaker Name
	 * @param email			Speaker Email
	 * @param organization	Speaker Organization
	 * @param title			Speaker Title
	 * @param address		Speaker Address
	 * @param city			Speaker City
	 */
	public void setSpeakerInfo(String speaker_name, String email, String organization, String title, String address, 
			String city) {
		// Set Values
		this.speaker_name = speaker_name;
		this.title = title;
		this.organization = organization;
		this.address = address;
		this.city = city;
		this.email = email;
		
	}
	
	/**
	 * This method sets the variables related to faculty information
	 * 
	 * @param faculty_name			Faculty Name
	 * @param course				Course Name
	 * @param section				Section Number
	 * @param semesterSpeakerInv	Semester Invited
	 */
	public void setFacultyInfo(String faculty_name, String course, String section, String semesterSpeakerInv)
	{
		// Set Values
		this.faculty_name = faculty_name;
		this.course = course;
		this.section = section;
		this.semesterSpeakerInv = semesterSpeakerInv;
	}
	
	/**
	 * This method sets the date variables of the object
	 * 
	 * @param month		Month invited
	 * @param day		Day invited
	 * @param year		Year invited
	 */
	public void setDate(int month, int day, int year)
	{
		// Set Values
		this.month = month;
		this.day = day;
		this.year = year;
	}
	
	/**
	 * This method returns the information related to the speaker
	 * 
	 * @return	Array of speaker information
	 */
	public String[] getSpeakerInfo()
    {
        // Get Information
		String speaker_info[] = new String[6];
        speaker_info[0]= speaker_name;
        speaker_info[1] =  title;
        speaker_info[2] =  organization;
        speaker_info[3] =  address;
        speaker_info[4] =  city;
        speaker_info[5] =  email;
        
        // Return Values
        return speaker_info; 
    }
	
	/**
	 * This method returns the information related to the faculty member
	 * 
	 * @return 	Array of faculty information
	 */
	public String[] getFacultyInfo()
	{
		// Get Information
		String faculty_info[] = new String[4];
		faculty_info[0] = faculty_name;
		faculty_info[1] = course;
		faculty_info[2] = section;
		faculty_info[3] = semesterSpeakerInv;
		
		// Return Values
		return faculty_info;
	}
	
	/**
	 * This method returns the date the speaker is invited to talk on as an array
	 * 
	 * @return	Date of Speaking
	 */
	public String[] getDate() 
	{
		// Get Information
		String spk_date[] = new String[3];
		spk_date[0] = Integer.toString(month);
		spk_date[1] = Integer.toString(day);
		spk_date[2] = Integer.toString(year);
		
		// Return Values
		return spk_date;
	}
	
	/**
	 * This method overrides the toString method for the class, used in the search and report functions in the GUI class
	 * 
	 * @return	Formatted String containing all object information
	 */
	public String toString() {
		// Return Information
		String[] date = getDate();
		return "Speaker Name: " + speaker_name + "\n" + "Title: " + title + "\n" + "Organization: " +
		organization + "\n" + "Address: " + address + "\n" + "City: " + city + "\n" + "Email: " + email + "\n" + "Date: " +
		date[0] + "/" + date[1] + "/" + date[2] +  "\n" + "Semester: " + semesterSpeakerInv + "\n" + "Faculty Member in Charge: " + faculty_name + "\n" + "Course: "	+
		course + "\n" + "Section: " + section + "\n" + "Gift: " + gift;
		
	}
	
	// Mutators & Accessors
	/**
	 * @return the gift
	 */
	public String getGift() {
		return gift;
	}
	
	/**
	 * 
	 * @param newGift Sets the new gift for the record
	 */
	public void setGift(String newGift) {
		gift = newGift;
	}
	
	/**
	 * @return the speaker_name
	 */
	public String getSpeaker_name() {
		return speaker_name;
	}

	/**
	 * @param speaker_name the speaker_name to set
	 */
	public void setSpeaker_name(String speaker_name) {
		this.speaker_name = speaker_name;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the organization
	 */
	public String getOrganization() {
		return organization;
	}

	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(String organization) {
		this.organization = organization;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the faculty_name
	 */
	public String getFaculty_name() {
		return faculty_name;
	}

	/**
	 * @param faculty_name the faculty_name to set
	 */
	public void setFaculty_name(String faculty_name) {
		this.faculty_name = faculty_name;
	}

	/**
	 * @return the course
	 */
	public String getCourse() {
		return course;
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse(String course) {
		this.course = course;
	}

	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}

	/**
	 * @return the semesterSpeakerInv
	 */
	public String getSemesterSpeakerInv() {
		return semesterSpeakerInv;
	}

	/**
	 * @param semesterSpeakerInv the semesterSpeakerInv to set
	 */
	public void setSemesterSpeakerInv(String semesterSpeakerInv) {
		this.semesterSpeakerInv = semesterSpeakerInv;
	}
	
	/**
	 * @return The row ID
	 */
	public int getRowID() {
		return rowID;
	}
	
	/**
	 * @param newID
	 */
	public void setRowID(int newID) {
		rowID = newID;
	}
}
// End of Class