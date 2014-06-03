package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Steven Bradley
 *
 */
public class User {
	/**
	 * The unigue ID for this user.
	 */
	public int myID;
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

	/**
	 * The papers that the user has authored and submitted to the conference.
	 */
	
	/**
	 * The papers that the user is responsible for getting reviewed.
	 */
	private List<Integer> my_assignedPapers;
	
	
	
//	public Set<Integer> author_submittedPapers;
//	public Set<Integer> pc_assignedSpc;
//	
//	public Set<Integer> spc_assignedPapers;
//	public Set<Integer> spc_assignedReviewers;
	
	public int spc_pc;
	
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
		
		my_assignedPapers = new ArrayList<Integer>();
		
//		author_submittedPapers = new HashSet<Integer>();
//		pc_assignedSpc = new HashSet<Integer>();
//		
//		
//		spc_assignedPapers = new HashSet<Integer>();
//		spc_assignedReviewers = new HashSet<Integer>();;
//		spc_pc = 0; // nobody by default;
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
	
	
	public void submitPaper(int the_paperId) {
		my_assignedPapers.add(the_paperId);
	}
	
//	public void assignSpc_to_PC(int the_spcId){
//		pc_assignedSpc.add(the_spcId);
//	}
//	public void assignManuscript_to_SPC(int the_paperId){
//		spc_assignedPapers.add(the_paperId);
//	}
//	public void assignPC_to_SPC(int the_pcId){
//		spc_pc = the_pcId;
//	}
//	public void assignReviewer_to_SPC(int the_reviewerId){
//		spc_assignedReviewers.add(the_reviewerId);
//	}

}
