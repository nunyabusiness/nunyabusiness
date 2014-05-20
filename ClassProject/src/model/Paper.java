package model;


public class Paper {
	private int key;
	private Conference conference;
	private Author author;
	private Spc spc;
	
	// 0 - undecided, 1-accepted, 2-declined
	// please see Recommendation class;
	private Recommendation recommendation;
	// 0 - undecided, 1-yes, 2-no
	private int decision = 0;
	
	public Paper(Conference conference,Author author){
		this.conference = conference;
		this.author = author;
		spc = null;
		recommendation = new Recommendation();
	}
	public void setKey(int key){
		this.key = key;
	}
	public void assignSpc(Spc spc){
		this.spc = spc; 
	}
	public void setRecommendation(Recommendation r){
		recommendation = r;
	}
	public int getStatus(){
		return recommendation.state;
	}
	public void setDecision(int decision){
		this.decision = decision;
	}
}
