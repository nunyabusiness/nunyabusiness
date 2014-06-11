package model;

/**
 * 
 * @author Christopher Barrett, Steven Bradley
 * I needed this for my part
 */
/**
 * @author solid_000
 *
 */
public class Review 
{
	private int reviewerID;
	
	private int score;
	
	private String comments;
	
	/**
	 * Constructor
	 */
	public Review()
	{
		score = 0;
		comments = "";
	}
	
	/**
	 * overloaded constructor
	 * @param avg inputed average
	 * @param comment inputed comment
	 */
	public Review(int userID, int avg, String comment)
	{
		reviewerID = userID;
		score = avg;
		comments = comment;
	}
	
	public int getReviewerID() {
		return reviewerID;
	}
	
	/**
	 * Setter
	 * @param avg score from reviewer
	 */
	public void setScore(int avg)
	{
		score = avg;
	}
	
	/**
	 * Setter
	 * @param comment comments from reviewer
	 */
	public void setComment(String comment)
	{
		comments = comment;
	}
	
	/**
	 * getter
	 * @return average
	 */
	public int getScore()
	{
		return score;
	}
	
	/**
	 * getter
	 * @return comments
	 */
	public String getComment()
	{
		return comments;
	}
	
	/**
	 * @author STeven bradley
	 */
//	public String toString() {
//		return score + "," + comments;
//	}
}
