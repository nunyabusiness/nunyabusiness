package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import org.joda.time.DateTime;

public class Conference extends Observable {
	List<Author> authors;
	List<Paper> papers;
	List<User> users;
	DateTime date;
	DateTime dueDate;
	
	public Conference(DateTime date){
		authors = new ArrayList<Author>();
		papers = new ArrayList<Paper>();
		users = new ArrayList<User>();
		this.date = date;
		setDueDate(date);
	}
	public void addPaper(Paper paper){
		papers.add(paper);	
	}
	public void addAuthor(Author author){
		authors.add(author);
	}
	
	private void setDueDate(DateTime date){
		int year = date.getYear();
		int month = date.getMonthOfYear();
		int day = date.getDayOfMonth();
		int hour = date.getHourOfDay();
		int minute = date.getMinuteOfHour();
		
		if(month == 0){
			year--;
			month = 12;
		}
		dueDate = new DateTime(year,month,day,hour,minute);
	}
}
