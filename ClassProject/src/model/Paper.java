package model;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Steven Bradley
 * @version june 2 2014
 *
 */
public class Paper {
	private String myTitle;
	private String myAbstract;
	private String myFile;
	private int myID;
	private int myAuthorID;
	private int mySubchair;
	private List<Integer> myReviewers;
	private List<Review> myReviews;
	
	// 0 - undecided, 1-accepted, 2-declined
	// please see Recommendation class;
	private Recommendation recommendation;
	// 0 - undecided, 1-yes, 2-no
	private int decision = 0;
	
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

	public void assignSpc(int id){
		if (myAuthorID != id) {
			mySubchair = id;
		} 	
	}
	
	public void assignReviewer(int id) {
		if (myAuthorID != id) {
			myReviewers.add(id);
		}
	}
	
	public void setRecommendation(Recommendation r){
		recommendation = r;
	}
	
	public int getAuthorID() {
		return myAuthorID;
	}
	
	public int getSubchairID() {
		return mySubchair;
	}
	public List<Integer> getReviewerList(){
		return myReviewers;
	}
	
	public int getStatus(){
		return recommendation.state;
	}
	public void setDecision(int decision){
		this.decision = decision;
	}
	public void submitReviewToPaper(Review r){
		myReviews.add(r);
	}
	public int getId(){
		return myID;
	}
	
	public String toString() {
		String output =  myID + "," + myAuthorID + "," + myTitle + "," + myAbstract + "," + myFile + "," + mySubchair + "~" +
				recommendation.toString() + "~";
		
		for (Review r : myReviews) {
			output = output + r.toString() + "^";
		}
		output = output + "~";
		
		return output;
	}
}
