package model;

import java.util.ArrayList;
import java.util.List;


public class Paper {
	private String myTitle;
	private String myAbstract;
	private String myFilePath;
	private int myID;
	private int myAuthorID;
	private int mySubchair;
	private List<Integer> myReviewers;
	private List<Review> myReviews;
	private Conference conference;
	
	// 0 - undecided, 1-accepted, 2-declined
	// please see Recommendation class;
	private Recommendation recommendation;
	// 0 - undecided, 1-yes, 2-no
	private int decision = 0;
	
	public Paper(Conference conference, int authorID, int paperID, String title, String anAbstract, String filePath){
		myID = paperID;
		myAuthorID = authorID;
		myTitle = title;
		myAbstract = anAbstract;
		myFilePath = filePath;
		this.conference = conference;
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
	
	public int getStatus(){
		return recommendation.state;
	}
	public void setDecision(int decision){
		this.decision = decision;
	}
}
