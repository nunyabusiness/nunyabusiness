package model;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Steven Bradley
 * @version june 2 2014
 *
 */
public class Paper {
	/**
	 * The title of the paper.
	 */
	private String myTitle;
	/**
	 * A brief summary of the paper.
	 */
	private String myAbstract;
	/**
	 * The name of the paper file.
	 */
	private String myFile;
	/**
	 * The unique ID for the paper.
	 */
	private int myID;
	/**
	 * The unique ID for the author of the paper.
	 */
	private int myAuthorID;
	/**
	 * The unique ID for the subchair assigned to the paper.
	 */
	private int mySubchair;
	/**
	 * The list of IDs for reviewers assigned to the paper.
	 */
	private List<Integer> myReviewers;
	/**
	 * The list of reviews of the paper.
	 */
	private List<Review> myReviews;
	
	// 0 - undecided, 1-accepted, 2-declined
	// please see Recommendation class;
	/**
	 * The recommendation made after the paper is reviewed.
	 */
	private Recommendation recommendation;
	// 0 - undecided, 1-yes, 2-no
	/**
	 * Whether or not the paper has been accepted or rejected.
	 */
	private int decision = 0;
	
	/** Creates a new paper with the folling fields.
	 * @param paperID
	 * @param authorID
	 * @param title
	 * @param anAbstract
	 * @param file
	 */
	public Paper(int paperID, int authorID, String title, String anAbstract, String file){
		myID = paperID;
		myAuthorID = authorID;
		myTitle = title;
		myAbstract = anAbstract;
		myFile = file;
		myReviewers = new ArrayList<Integer>();
		myReviews = new ArrayList<Review>();
		recommendation = new Recommendation();
	}

	/** Assigns the given subchair id to the paper.
	 *  Checks to make sure the subchair is not the author of the paper.
	 * @param id
	 */
	public void assignSpc(int id){
		if (myAuthorID != id) {
			mySubchair = id;
		} 	
	}
	
	/** Assigns the given reviewer ID to the paper.
	 *  Checks to make sure the reviewer is not the author of the paper.
	 * @param id
	 */
	public void assignReviewer(int id) {
		if (myAuthorID != id) {
			myReviewers.add(id);
		}
	}
	
	/** Sets the recommendation for the paper.
	 * @param r
	 */
	public void setRecommendation(Recommendation r){
		recommendation = r;
	}
	
	/** Gets the unique ID for the paper.
	 * @return the ID of the paper.
	 */
	public int getId(){
		return myID;
	}
	
	/** Gets the unique ID of the author.
	 * @return ID of the author.
	 */
	public int getAuthorID() {
		return myAuthorID;
	}
	
	/** Gets the unique ID of the subchair.
	 * @return ID of the subchair.
	 */
	public int getSubchairID() {
		return mySubchair;
	}
	
	/** Gets the list of reviewer IDs for the paper.
	 * @return List of reviewer IDs.
	 */
	public List<Integer> getReviewerList(){
		return myReviewers;
	}
	
	/** Gets the current status of the paper. (Accept/Reject/Undecided)
	 * @return state of decision.
	 */
	public int getStatus(){
		return recommendation.state;
	}
	
	/** Sets the decision for the paper. (Accept/Reject/Undecided)
	 * @param decision
	 */
	public void setDecision(int decision){
		this.decision = decision;
	}
	
	/** Adds the given review to the list of the papers reviews.
	 * @param r the review to add to the paper.
	 */
	public void submitReviewToPaper(Review r){
		myReviews.add(r);
	}
	

	public String toString() {
		String output =  myID + "," + myAuthorID + "," + myTitle + "," + myAbstract + "," + myFile + "," + mySubchair
				+ "~" + recommendation.toString() + "~";
		
		for (Review r : myReviews) {
			output = output + r.toString() + "^";
		}
		
		return output;
	}
}
