package model;

/**
 * 
 * @author Christopher Barrett
 * I needed this for my part
 */
public class Review 
{
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
	public Review(int avg, String comment)
	{
		score = avg;
		comments = comment;
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
	//how the f is this not been done yet?
}
