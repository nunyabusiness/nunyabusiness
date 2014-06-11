package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 * @author Anton Bardakhanov, Steven Bradley
 *
 */
public class Conference extends Observable {

	public static final GregorianCalendar DEADLINE = new GregorianCalendar(
			2014, 5, 21);

	private User currentUser;
	private Connection c;

	//private Map<Integer, User> my_users;
	//private Map<Integer, Paper> my_papers;

	// we going to parse paperId from text file;
	//int lastPaperId = 0;
	//int lastReviewId = 0;

	public Conference() {	
		currentUser = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:db/nunya.sqlite");
			c.setAutoCommit(true);
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		} 
		
		//my_users = new HashMap<Integer, User>();
		//my_papers = new HashMap<Integer, Paper>();
	}
	
	public void startTest() {
		try {
			c.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public void endTest() {
		try {
			Statement stmt = c.createStatement();
			stmt.executeUpdate("rollback");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param theID
	 * @return
	 */
	public User getUser(int theID) {
		User u = null;
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE userID = '" + theID + "'");
			
			u = new User(rs.getInt("userID"), rs.getInt("roleID"), 
						rs.getString("firstName"), rs.getString("lastName"), 
						rs.getString("email"));
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return u;
	}
	
	/**
	 * @author Steven Bradley
	 * @param theID
	 * @return
	 */
	public Paper getPaper(int theID) {
		Paper p = null;
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM paper WHERE paperID = '" + theID + "'");
			
			if (rs.next())
				p = new Paper(rs.getInt("paperID"), rs.getInt("userID"), rs.getString("title"),
					rs.getString("abstract"), rs.getString("fileName"));
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return p;
	}

//	public void addUser(User the_user) {
//		my_users.put(the_user.myID, the_user);
//		// myUserList.add(user);
//
//	}

	// US01. As an Author, I want to submit a manuscript to a conference.
	/**
	 * @author Steven Bradley
	 * @param title
	 * @param theAbstract
	 * @param filename
	 * @throws BusinessRuleException
	 */
	public void addPaper(String title, String theAbstract, String filename) throws BusinessRuleException {
//		my_currentUser.submitPaper(my_papers.size() + 1);
//		my_papers.put(my_papers.size() + 1 , new Paper(my_papers.size() + 1, my_currentUser.getID(), title, Abstract, filename));			
		if (getPapersByAuthor(currentUser.getID()).size() < 4) {
			try {
				Statement stmt = c.createStatement();
				stmt.executeUpdate("INSERT INTO paper (userID, title, abstract, fileName) VALUES ('" + currentUser.getID() + "', '" 
						+ title + "', '" + theAbstract + "', '" + filename + "')");

				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 	
		} else {
			throw new BusinessRuleException("You may only have 4 papers submitted to a conference.");
		}

		setChanged();
		notifyObservers(ConfChangeType.PAPER_ADDED);
	}
	
//	// US01. As an Author, I want to submit a manuscript to a conference.
//	public void addPaper(Paper that) throws BusinessRuleException {
//		User cur = my_users.get(that.getAuthorID());
//		cur.submitPaper(that.getId());
//		my_papers.put(that.getId(), that);
//	}
	
	/**
	 * @author Steven Bradley
	 * Method which updates the database to delete a paper and any parallel reviews or 
	 * recommendations.
	 * 
	 * @param that the paper to be deleted.
	 */
	public void removePaper(Paper that) {
		try {
			Statement stmt = c.createStatement();
			stmt.executeUpdate("DELETE FROM paper WHERE paperID = '" + that.getId() + "'");
			stmt.executeUpdate("DELETE FROM review WHERE paperID = '" + that.getId() + "'");
			stmt.executeUpdate("DELETE FROM recommendation WHERE paperID = '" + that.getId() + "'");
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
		
		setChanged();
		notifyObservers(ConfChangeType.PAPER_REMOVED);
	}

	// US02. As a Program Chair I want to designate a Subprogram Chair for a
	// manuscript.
	public void assignSpc(int the_spcKey, int the_paperKey) throws BusinessRuleException {
		if (getPapersBySpc(the_spcKey).size() < 4) { //ensure less than 4 papers assigned to spc
			try {
				if (getPaper(the_paperKey).getAuthorID() != the_spcKey) { // spc != author
					Statement stmt = c.createStatement();			

					stmt.executeUpdate("INSERT INTO recommendation (paperId, userID) VALUES "
							+ "('" + the_paperKey + "', '" + the_spcKey + "')");

					stmt.close();
				} else {
					throw new BusinessRuleException("A user cannot be the subproram chair for their own paper.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 		

			setChanged();
			notifyObservers(ConfChangeType.SPC_ASSIGNED);
		} else {
			throw new BusinessRuleException("A subprogram chair may only have 4 papers assigned to them.");
		}
	}


	// US03. As a Subprogram Chair, I want to assign a paper to reviewers.
	public void assignReviewerToPaper(int the_reviewerKey, int the_paperKey) throws BusinessRuleException {
		if (getReviewerList(the_reviewerKey).size() < 4) {
			try {
				if (getPaper(the_paperKey).getAuthorID() != the_reviewerKey) {
					Statement stmt = c.createStatement();
					stmt.executeUpdate("INSERT INTO review (paperId, userID) VALUES "
							+ "('" + the_paperKey + "', '" + the_reviewerKey + "')");

					stmt.close();			

					setChanged();
					notifyObservers(ConfChangeType.REVIEWER_ASSIGNED);
				} else {
					throw new BusinessRuleException("Cannot assign a reviewer to his/her own paper.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 		
		} else {
			throw new BusinessRuleException("Reviewer cannot have more than 4 papers assigned to them");
		}
	}

	// US04. As a Reviewer, I want to view a list of manuscripts to which I have
	// been assigned.
	public List<Paper> getReviewerList(int reviewerKey) {
		List<Paper> list = new ArrayList<Paper>();
		
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM review WHERE userID = '" + reviewerKey +"'");
			
			while (rs.next()) {
				list.add(getPaper(rs.getInt("paperID")));
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
		
		return list;
	}

	// US05. As a Reviewer, I want to submit a review for a manuscript to which
	// I have been assigned.
	// US06. As an Author I want to obtain the reviews for the manuscripts that
	// I have submitted.
	public void submitReview(int paperId, Review review) {
		//my_papers.get(paperId).submitReviewToPaper(review);
		try {
			Statement stmt = c.createStatement();
			//ResultSet rs = stmt.executeQuery("SELECT * FROM ");
			
			stmt.executeUpdate("UPDATE review SET score = '" + review.getScore() 
					+ "', comment = '" + review.getComment() + "' WHERE userID = '" + currentUser.getID() 
					+ "' AND paperID = '" + paperId + "'");
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		
		setChanged();
		notifyObservers(ConfChangeType.REVIEW_ADDED);
	}

//	// US07. As an Author, I want to make changes to my submission, including
//	// unsubmitting my manuscript.
//	public void makeChangesToManuscript(Paper p) {
//		my_papers.put(p.getId(), p);
//	}

	// US08. As a Subprogram Chair, I want to submit my recommendation for a
	// paper.
	public void spcSubmitRecommendation(int paperKey, Recommendation r) {
//		if (my_papers.containsKey(paperKey)) {
//			my_papers.get(paperKey).setRecommendation(r);
//			
//			setChanged();
//			notifyObservers(ConfChangeType.REVIEW_ADDED);
//		} else {
//			throw new NullPointerException("No such paper");
//		}
		try {
			Statement stmt = c.createStatement();
			stmt.executeUpdate("UPDATE recommendation SET score = " + r.getState() 
					+ ", comment = '" + r.getRationale() + "' WHERE userID = '" + currentUser.getID() 
					+ "' AND paperID = '" + paperKey + "'");
			
			stmt.close();
			
			setChanged();
			notifyObservers(ConfChangeType.REVIEW_ADDED);
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		
	}

	// US09. As a Program Chair, I want to view a list of all submitted
	// manuscripts and the acceptance status
	// US11. As a Program Chair, I want to see which papers are assigned to
	// which Subprogram chairs.
	// Please see US02
	/**
	 * @author Steven Bradley
	 * @return the list of all papers in the conference.
	 */
	public ArrayList<Paper> getAllPapers() {
		// return collection of papers
		// status can be obtained by getStatus()
		//return new ArrayList<Paper>(my_papers.values());
		ArrayList<Paper> list = new ArrayList<Paper>();
		
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM paper");
			
			while (rs.next()) {
				list.add(new Paper(rs.getInt("paperID"), rs.getInt("userID"), rs.getString("title"),
						rs.getString("abstract"), rs.getString("fileName")));
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		
		return list;		
	}
	
//	public ArrayList<User> getAllUsers() {
//		return new ArrayList<User>(my_users.values());
//	}

	/**
	 * Returns the current logged in user. May not be necessary for later
	 * iterations of program, but considering using now for the sake of testing
	 * ID levels, etc.
	 * 
	 * (Erik)
	 * 
	 * @return myCurrentUser.
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Method which logs out the current user of the program.
	 * 
	 * (Erik)
	 */
	public void logout() {
		currentUser = null;

		setChanged();
		notifyObservers(ConfChangeType.LOGOUT);
	}

	// US10. As a Program Chair, I want to make an acceptance decision (yes or
	// no) on a submitted manuscript.
	public void submitDecision(int paperKey, int decision) {
		//my_papers.get(paperKey).setDecision(decision);
		try {
			Statement stmt = c.createStatement();
			stmt.executeUpdate("UPDATE paper SET decision = " + decision 
					+ " WHERE paperID = '" + paperKey +"'");
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
	
		setChanged();
		notifyObservers(ConfChangeType.DECISION_MADE);
	}
	
	public void changeUserRole(int theUserID, int theRole) {
		//my_users.get(theUserID).setRole(theRole);
		try {
			Statement stmt = c.createStatement();
			stmt.executeUpdate("UPDATE user SET roleID = " + theRole 
					+ " WHERE userID = '" + theUserID + "'");
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		
	}

	/**
	 * Method which logs in a new user for the conference.
	 * 
	 * @author Erik
	 * @param theUser
	 *            The user to be logged in.
	 */
	// US12. As a user, I want to log in.
	public void login(final int theUserID) {
		boolean userFound = false;

		// for (User u : myUserList) {
//		for (User u : my_users.values()) {
//			if (theUserID == u.getID()) {
//				userFound = true;
//				currentUser = u;
//
//				setChanged();
//				notifyObservers(ConfChangeType.LOGIN_SUCCESSFUL);
//			}
//		}
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE userID = '" + theUserID + "'");
			
			if (rs.next()) {
				userFound = true;
				currentUser = new User(rs.getInt("userID"), rs.getInt("roleID"), 
						rs.getString("firstName"), rs.getString("lastName"), 
						rs.getString("email"));
				
				setChanged();
				notifyObservers(ConfChangeType.LOGIN_SUCCESSFUL);
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
		
		if (!userFound) {
			setChanged();
			notifyObservers(ConfChangeType.LOGIN_FAIL);
		}
	}

	public List<User> getUserByRole(int roleId){
		List<User> users = new ArrayList<User>();
//		for(User u: my_users.values()){
//			if(u.getRole() == roleId)
//				users.add(u);
//		}
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE roleID = '" + roleId + "'");
			
			while (rs.next()) {
				users.add(new User(rs.getInt("userID"), rs.getInt("roleID"), 
						rs.getString("firstName"), rs.getString("lastName"), 
						rs.getString("email")));
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		
		
		return users;
	}
	
	/**
	 * @author Steven Bradley
	 * @param authorId
	 * @return the list of papers by the given author.
	 */
	public List<Paper> getPapersByAuthor(int authorId){
		List<Paper> papers = new ArrayList<Paper>();
//		for(Paper p: my_papers.values()){
//			if(p.getAuthorID() == authorId)
//				papers.add(p);
//		}
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM paper WHERE userID = '" + authorId + "'");
			
			while (rs.next()) {
				papers.add(new Paper(rs.getInt("paperID"), rs.getInt("userID"), rs.getString("title"),
						rs.getString("abstract"), rs.getString("fileName")));
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		
		return papers;
	}
	
	/**
	 * @param paperId
	 * @return
	 */
	public Recommendation getRecommendationForPaper(int paperId) {
		Recommendation r = null;
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM recommendation WHERE paperID = '" + paperId + "'");
			
			if (rs.next()) {
				r = new Recommendation();
				r.setState(rs.getInt("score"));
				r.setRationale(rs.getString("comment"));
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return r;
		
	}
	
	/**
	 * @param paperId
	 * @return
	 */
	public String getPaperDecision(int paperId) {
		String decisionStr = "Being Reviewed";
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM paper WHERE paperID = '" + paperId + "'");
			
			if (rs.next()) {
				int decision = rs.getInt("decision");

				if (decision == 1) {
					decisionStr = "Accepted";
				} if (decision == 2) {
					decisionStr = "Rejected";
				}
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return decisionStr;
		
	}
	
	/**
	 * @param paperId
	 * @return
	 */
	public User getSPCforPaper(int paperId) {
		User spc = null;
		
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM recommendation WHERE paperID = '" + paperId + "'");
			
			if (rs.next()) {
				int userID = rs.getInt("userID");
				rs = stmt.executeQuery("SELECT * FROM user WHERE userID = '" + userID + "'");
				
				spc = new User(rs.getInt("userID"), rs.getInt("roleID"), 
						rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"));
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return spc;
	}
	
	/**
	 * @param paperId
	 * @return
	 */
	public List<Review> getReviewsForPaper(int paperId) {
		ArrayList<Review> list = new ArrayList<Review>();
		
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM review WHERE paperID = '" + paperId + "'");
			
			while (rs.next()) {
				list.add(new Review(rs.getInt("userID"), rs.getInt("score"), rs.getString("comment")));
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return list;
	}
	
	/**
	 * @param spcId
	 * @return
	 */
	public List<Paper> getPapersBySpc(int spcId){
		List<Paper> papers = new ArrayList<Paper>();
//		for(Paper p: my_papers.values()){
//			if(p.getSubchairID() == spcId)
//				papers.add(p);
//		}
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM recommendation WHERE userID = '" + spcId + "'");
			
			while (rs.next()) {
				papers.add(getPaper(rs.getInt("paperID")));
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return papers;
	}

	// Set duedate 1 month before conference starts
	/**
	 * @return
	 */
	public int getDaysLeft() {
		long todaysDate = new GregorianCalendar().getTimeInMillis();

		long daysLeft = (DEADLINE.getTimeInMillis() - todaysDate)
				/ (1000 * 60 * 60 * 24);

		return (int) daysLeft;
	}

	/**
	 * @return
	 */
	public GregorianCalendar getDeadline() {
		return DEADLINE;
	}
	
	/**
	 * 
	 */
	public void saveConference() {
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		setChanged();
//		notifyObservers(ConfChangeType.CONF_SAVED);
	}
}
