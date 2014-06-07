/**
 * 
 */
package Tests;

import static org.junit.Assert.*;
import model.Paper;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Christopher
 *
 */
public class TestPaper 
{
	int PID = 1;
	int AID = 21;
	String TITLE = "title";
	String ABS = "Some";
	String FILE = "file.txt";
	
	Paper pap;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		//int paperID, int authorID, String title, String anAbstract, String file
		pap = new Paper(PID, AID, TITLE, ABS, FILE);
	}

	/**
	 * Test method for {@link model.Paper#assignSpc(int)}.
	 */
	@Test
	public void testAssignSpc() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Paper#assignReviewer(int)}.
	 */
	@Test
	public void testAssignReviewer() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Paper#setRecommendation(model.Recommendation)}.
	 */
	@Test
	public void testSetRecommendation() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Paper#getId()}.
	 */
	@Test
	public void testGetId() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Paper#getAuthorID()}.
	 */
	@Test
	public void testGetAuthorID() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Paper#getSubchairID()}.
	 */
	@Test
	public void testGetSubchairID() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Paper#getReviewerList()}.
	 */
	@Test
	public void testGetReviewerList() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Paper#getStatus()}.
	 */
	@Test
	public void testGetStatus() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Paper#setDecision(int)}.
	 */
	@Test
	public void testSetDecision() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Paper#submitReviewToPaper(model.Review)}.
	 */
	@Test
	public void testSubmitReviewToPaper() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Paper#getTitle()}.
	 */
	@Test
	public void testGetTitle() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Paper#getAbstract()}.
	 */
	@Test
	public void testGetAbstract() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Paper#getFile()}.
	 */
	@Test
	public void testGetFile() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Paper#getRec()}.
	 */
	@Test
	public void testGetRec() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Paper#hasRev()}.
	 */
	@Test
	public void testHasRev() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Paper#getRev()}.
	 */
	@Test
	public void testGetRev() 
	{
		fail("Not yet implemented"); // TODO
	}

}
