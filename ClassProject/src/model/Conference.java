package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.joda.time.DateTime;

public class Conference extends Observable {

	private Map<Integer, Paper> papers;
	private Map<Integer, Review> reviews;
	private Map<Integer, User> users;
	private Map<Reviewer, List<Integer>> reviewers_list;
	private Map<Spc, Integer> spc_list;
	private DateTime date;
	private DateTime dueDate;

	// we going to parse paperId from text file;
	int lastPaperId = 0;
	int lastReviewId = 0;
	int lastUserId = 0;

	public Conference(DateTime date) {
		this.date = date;
		setDueDate(date);

		papers = new HashMap<Integer, Paper>();
		reviews = new HashMap<Integer, Review>();
		users = new HashMap<Integer, User>();
		reviewers_list = new HashMap<Reviewer, List<Integer>>();
		spc_list = new HashMap<Spc, Integer>();
	}

	public void addUser(User user) {
		users.put(++lastUserId, user);
		user.setKey(lastUserId);
	}

	//US01. As an Author, I want to submit a manuscript to a conference. 
	public void addPaper(Paper paper) {
		papers.put(++lastPaperId, paper);
		paper.setKey(lastPaperId);
	}

	//US02. As a Program Chair I want to designate a Subprogram Chair for a manuscript. 
	public void assignSpc(Spc spc, int paperKey) {
		
		// double assignment!!!!!
		spc_list.put(spc, paperKey);
		papers.get(paperKey).assignSpc(spc);
		
	}

	//US03. As a Subprogram Chair, I want to assign a paper to reviewers. 
	public void assignReviewerToPaper(Reviewer r, int paperKey) {
		// if reviewer already assigned to paper(s).
		if (reviewers_list.containsKey(r))
			reviewers_list.get(r).add(paperKey);
		// if no papers to assigned to reviewer yet
		else {
			List<Integer> list = new ArrayList<Integer>();
			list.add(paperKey);
			reviewers_list.put(r, list);
		}
	}
	//US04. As a Reviewer, I want to view a list of manuscripts to which I have been assigned. 
	public List<Paper> getReviewerList(int reviewerKey) {
		List<Paper> list = new ArrayList<Paper>();
		if (reviewers_list.containsKey(reviewerKey))
			for (Integer p : reviewers_list.get(reviewerKey))
				list.add(papers.get(p));

		return list;
	}
	//US05. As a Reviewer, I want to submit a review for a manuscript to which I have been assigned.
	//US06. As an Author I want to obtain the reviews for the manuscripts that I have submitted.   
	public void submitReview(Review review){
		reviews.put(++lastReviewId, review);
		setChanged();
		notifyObservers(review);
	}
	//US07. As an Author, I want to make changes to my submission, including unsubmitting my manuscript. 
	public void removeManuscript(int paperKey){
		papers.remove(paperKey);
	}
	//US08. As a Subprogram Chair, I want to submit my recommendation for a paper. 
	public void submitRecommendation(int paperKey, Recommendation r){
		if(papers.containsKey(paperKey)){
			papers.get(paperKey).setRecommendation(r);
		}else{
			throw new NullPointerException("No such paper");
		}
	}
	//US09. As a Program Chair, I want to view a list of all submitted manuscripts and the acceptance status
	//US11. As a Program Chair, I want to see which papers are assigned to which Subprogram chairs.
	// Please see US02
	public List<Paper> getAllPapers(){
		// return collection of papers
		// status can be obtained by getStatus()
		return new ArrayList<Paper>(papers.values());
	}
	
	//US10. As a Program Chair, I want to make an acceptance decision (yes or no) on a submitted manuscript. 
	public void submitDecision(int paperKey,int decision){
		papers.get(paperKey).setDecision(decision);
	}
	
	//US12. As a user, I want to log in. 
	public boolean findUserByKey(String login, String password){
		for(User u: users.values()){
			if(u.getLogin().equals(login)){
				return u.verifyCredentials(login, password);
			}
		}
		return false;
	}
	// Set duedate 1 month before conference starts
	private void setDueDate(DateTime date) {
		int year = date.getYear();
		int month = date.getMonthOfYear();
		int day = date.getDayOfMonth();
		int hour = date.getHourOfDay();
		int minute = date.getMinuteOfHour();

		if (month == 0) {
			year--;
			month = 12;
		}
		dueDate = new DateTime(year, month, day, hour, minute);
	}
}
