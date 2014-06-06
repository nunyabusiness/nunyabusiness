/**
 * 
 */
package Tests;

import static org.junit.Assert.*;
import model.Review;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Christopher
 *
 */
public class TestReview 
{
	int SCORE = 4;
	String COMMENT = "comments";
	
	Review rev;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		rev = new Review(SCORE, COMMENT);
	}

	/**
	 * Test method for {@link model.Review#setScore(int)}.
	 */
	@Test
	public void testSetScore() 
	{
		rev.setScore(2);
		
		assertEquals(2, rev.getScore());
	}

	/**
	 * Test method for {@link model.Review#setComment(java.lang.String)}.
	 */
	@Test
	public void testSetComment() 
	{
		rev.setComment("awful");
		
		assertEquals("awful", rev.getComment());
	}

	/**
	 * Test method for {@link model.Review#getScore()}.
	 */
	@Test
	public void testGetScore() 
	{
		assertEquals(SCORE, rev.getScore());
	}

	/**
	 * Test method for {@link model.Review#getComment()}.
	 */
	@Test
	public void testGetComment() 
	{
		assertEquals(COMMENT, rev.getComment());
	}

	

}
