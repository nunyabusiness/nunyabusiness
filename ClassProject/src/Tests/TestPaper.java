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
	private static final int PID = 21;
	private static final int AID = 4;
	private static final String FILE = "Charlie";
	private static final String ABST = "Horner";
	private static final String TITLE = "CH@blah.edu";
	
	private Paper paper;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		paper = new Paper(PID, AID, TITLE, ABST, FILE);
	}
	
	/**
	 * Test method for {@link model.Paper#getId()}.
	 */
	@Test
	public void testGetId() 
	{
		assertEquals("The get paper id works", PID, paper.getId());
	}

	/**
	 * Test method for {@link model.Paper#getAuthorID()}.
	 */
	@Test
	public void testGetAuthorID() 
	{
		assertEquals("The get author id works", AID, paper.getAuthorID());
	}

	/**
	 * Test method for {@link model.Paper#getTitle()}.
	 */
	@Test
	public void testGetTitle() 
	{
		assertEquals("The get title works", TITLE, paper.getTitle());
	}

	/**
	 * Test method for {@link model.Paper#getAbstract()}.
	 */
	@Test
	public void testGetAbstract() 
	{
		assertEquals("The get abstract works", ABST, paper.getAbstract());
	}

	/**
	 * Test method for {@link model.Paper#getFile()}.
	 */
	@Test
	public void testGetFile() 
	{
		assertEquals("The get file works", FILE, paper.getFile());
	}

}
