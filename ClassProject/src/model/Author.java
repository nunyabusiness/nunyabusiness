package model;

import java.util.List;

public class Author extends User {
	private List<Paper> mySubmittedPapers;
		
	public Author(int id, int priority, String name, String email, Conference conference) {
		super(id, priority, name, email, conference);
		
	}


	public void submitPaper(Paper paper) {
		super.getConference().addPaper(paper);
		mySubmittedPapers.add(paper);
	}

}
