package model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.joda.time.DateTime;

public class Conference extends Observable {

	public static final GregorianCalendar DEADLINE = new GregorianCalendar(
			2014, 5, 21);

	private User my_currentUser;

	// private List<User> myUserList;

	private Map<Integer, User> my_users;
	private Map<Integer, Paper> my_papers;

	// we going to parse paperId from text file;
	int lastPaperId = 0;
	int lastReviewId = 0;

	public Conference() {
		my_currentUser = null;
		// myUserList = new ArrayList<User>();

		my_users = new HashMap<Integer, User>();
		my_papers = new HashMap<Integer, Paper>();
	}

	public void addUser(User the_user) {
		my_users.put(the_user.myID, the_user);
		// myUserList.add(user);

	}

	// US01. As an Author, I want to submit a manuscript to a conference.
	public void addPaper(String title, String Abstract, String filename) {
		int id = my_papers.size() + 1;
		my_papers.put(id , new Paper(id, my_currentUser.getID(), title, Abstract, filename));
		my_currentUser.submitPaper(id);

	}

	// US02. As a Program Chair I want to designate a Subprogram Chair for a
	// manuscript.
	public void assignSpc(int the_spcKey, int the_paperKey) {
		my_papers.get(the_paperKey).assignSpc(the_spcKey);
	}

	// US03. As a Subprogram Chair, I want to assign a paper to reviewers.
	public void assignReviewerToPaper(int the_reviewerKey, int the_paperKey) {
		my_papers.get(the_paperKey).assignReviewer(the_reviewerKey);
	}

	// US04. As a Reviewer, I want to view a list of manuscripts to which I have
	// been assigned.
	public List<Paper> getReviewerList(int reviewerKey) {
		List<Paper> list = new ArrayList<Paper>();
		for (Paper p : my_papers.values()) {
			if (p.getReviewerList().contains(reviewerKey))
				list.add(p);
		}
		return list;
	}

	// US05. As a Reviewer, I want to submit a review for a manuscript to which
	// I have been assigned.
	// US06. As an Author I want to obtain the reviews for the manuscripts that
	// I have submitted.
	public void submitReview(int paperId, Review review) {
		my_papers.get(paperId).submitReviewToPaper(review);
		setChanged();
		notifyObservers(review);
	}

	// US07. As an Author, I want to make changes to my submission, including
	// unsubmitting my manuscript.
	public void makeChangesToManuscript(Paper p) {
		my_papers.put(p.getId(), p);
	}

	// US08. As a Subprogram Chair, I want to submit my recommendation for a
	// paper.
	public void spcSubmitRecommendation(int paperKey, Recommendation r) {
		if (my_papers.containsKey(paperKey)) {
			my_papers.get(paperKey).setRecommendation(r);
		} else {
			throw new NullPointerException("No such paper");
		}
	}

	// US09. As a Program Chair, I want to view a list of all submitted
	// manuscripts and the acceptance status
	// US11. As a Program Chair, I want to see which papers are assigned to
	// which Subprogram chairs.
	// Please see US02
	public List<Paper> getAllPapers() {
		// return collection of papers
		// status can be obtained by getStatus()
		return new ArrayList<Paper>(my_papers.values());
	}

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
		return my_currentUser;
	}

	/**
	 * Method which logs out the current user of the program.
	 * 
	 * (Erik)
	 */
	public void logout() {
		my_currentUser = null;

		setChanged();
		notifyObservers(ConfChangeType.LOGOUT);
	}

	// US10. As a Program Chair, I want to make an acceptance decision (yes or
	// no) on a submitted manuscript.
	public void submitDecision(int paperKey, int decision) {
		my_papers.get(paperKey).setDecision(decision);
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
		for (User u : my_users.values()) {
			if (theUserID == u.getID()) {
				userFound = true;
				my_currentUser = u;

				setChanged();
				notifyObservers(ConfChangeType.LOGIN_SUCCESSFUL);
			}
		}
		if (!userFound) {
			setChanged();
			notifyObservers(ConfChangeType.LOGIN_FAIL);
		}
	}

	public List<User> getUserByRole(int roleId){
		List<User> users = new ArrayList<User>();
		for(User u: my_users.values()){
			if(u.getRole() == roleId)
				users.add(u);
		}
		return users;
	}
	public List<Paper> getPapersByAuthor(int authorId){
		List<Paper> papers = new ArrayList<Paper>();
		for(Paper p: my_papers.values()){
			if(p.getAuthorID() == authorId)
				papers.add(p);
		}
		return papers;
	}
	public List<Paper> getPapersBySpc(int spcId){
		List<Paper> papers = new ArrayList<Paper>();
		for(Paper p: my_papers.values()){
			if(p.getSubchairID() == spcId)
				papers.add(p);
		}
		return papers;
	}

	// Set duedate 1 month before conference starts
	public int getDaysLeft() {
		long todaysDate = new GregorianCalendar().getTimeInMillis();

		long daysLeft = (DEADLINE.getTimeInMillis() - todaysDate)
				/ (1000 * 60 * 60 * 24);

		return (int) daysLeft;
	}

	public GregorianCalendar getDeadline() {
		return DEADLINE;
	}
}
