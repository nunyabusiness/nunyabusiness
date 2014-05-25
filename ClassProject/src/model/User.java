package model;

import java.util.List;

public class User {
	/**
	 * The unigue ID for this user.
	 */
	private int myID;
	/**
	 * The role of this user.
	 * PC = 1
	 * SPC = 2
	 * Reviwer = 4
	 */
	private int myRole;
	/**
	 * The first name of the user.
	 */
	private String myFirstName;
	/**
	 * The last name of the user.
	 */
	private String myLastName;
	/**
	 * The users email.
	 */
	private String myEmail;
//	/**
//	 * The conference the user is part of.
//	 */
//	private Conference myConference;
	/**
	 * The papers that the user has authored and submitted to the conference.
	 */
	private List<Paper> mySubmittedPapers;
	/**
	 * The papers that the user is responsible for getting reviewed.
	 */
	private List<Paper> myAssignedPapers;
	
	
	/** Creates a new user with the following fields.
	 * @param id 
	 * @param role
	 * @param first
	 * @param last
	 * @param email
	 */
	public User(int id, int role, String first, String last, String email) {
		myID = id;	
		myFirstName = first;
		myLastName = last;
		myEmail = email;
		myRole = role;
	}
	
	public int getID() {
		return myID;
	}
	
	public int getRole() {
		return myRole;
	}
	
	public String getFirstName() {
		return myFirstName;
	}
	
	public String getLastName() {
		return myLastName;
	}
	
	public String getEmail() {
		return myEmail;
	}
	
//	public Conference getConference() {
//		return myConference;
//	}
	
	public void submitPaper(Paper paper) {
//		myConference.addPaper(paper);
		mySubmittedPapers.add(paper);
	}
	
	
//	int key;
//	String login;
//	String password;
//	Role role;
//	
//	public User(Role role){
//		this.role = role;
//	}
////	public void setLogin(String login){
////		this.login = login;
////	}
////	public void setPassword(String password){
////		this.password = password;
////	}
////	public boolean verifyCredentials(String login, String password){
////		return this.login.equals(login) && this.password.equals(password);
////	}
//	public String getLogin(){
//		return login;
//	}
//	public void setKey(int key){
//		this.key = key;
//	}
//	public String toString(){
//		return role.toString();
//	}
//	public void addRole(Role role){
//		// add some other roles.
//	}
}
