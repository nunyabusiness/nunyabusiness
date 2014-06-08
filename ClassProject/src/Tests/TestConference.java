/**
 * 
 */
package Tests;

import static org.junit.Assert.*;

import model.BusinessRuleException;
import model.Conference;
import model.Paper;
import model.Recommendation;
import model.Review;
import model.User;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Christopher
 *
 */
public class TestConference 
{
	private static final int PID = 1;
	private static final int AID = 56;
	private static final String FILE = "howaboutit.txt";
	private static final String ABST = "its a thing";
	private static final String TITLE = "Thing";
	private static final int PCID = 64; //from the user db a Program Chair
	private static final int SPCID = 56;
	private static final int REVID1 = 17;
	private static final int REVID2 = 21;
	private static final int REVID3 = 29;
	
	private static Conference con;
	private static Paper paper;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUp() throws Exception 
	{
		con = new Conference();
		
		paper = new Paper(PID, AID, TITLE, ABST, FILE);
	}
	
	/**
	 * 
	 */
	@After
	public static void breakDown()
	{
		con.logout();
	}
	
	/**
	 * 
	 */
	@AfterClass
	public static void breakOut()
	{
		con.saveConference();
	}

	/**
	 * Test method for {@link model.Conference#getUser(int)}.
	 */
	@Test
	public void testGetUser() 
	{
		User u = con.getUser(PCID);
		
		assertEquals("The get user works if the user collected returns the correct name", "Patricia Diaz", u.toString());
	}

	/**
	 * Test method for {@link model.Conference#addPaper(java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws BusinessRuleException 
	 */
	@Test
	public void testAddPaper() throws BusinessRuleException 
	{
		con.login(AID);
		
		con.addPaper(TITLE, ABST, FILE);
		
		assertEquals("if the papers are equal",paper, con.getPaper(PID));
	}

	/**
	 * Test method for {@link model.Conference#assignSpc(int, int)}.
	 * @throws BusinessRuleException 
	 */
	@Test
	public void testAssignSpc() throws BusinessRuleException 
	{
		con.login(PCID);
		
		con.assignSpc(SPCID, PID);
		
		assertEquals("if the assignment is correct than equals true", SPCID, con.getSPCforPaper(PID).getID());
	}

	/**
	 * Test method for {@link model.Conference#assignReviewerToPaper(int, int)}.
	 * @throws BusinessRuleException 
	 */
	@Test
	public void testAssignReviewerToPaper() throws BusinessRuleException 
	{
		con.login(SPCID);
		
		con.assignReviewerToPaper(REVID1, PID);
		con.assignReviewerToPaper(REVID2, PID);
		con.assignReviewerToPaper(REVID3, PID);
		
		assertEquals("If the paper exists in the list of papers for that reviewer", paper, con.getReviewerList(REVID1).get(PID));
		}

	/**
	 * Test method for {@link model.Conference#submitReview(int, model.Review)}.
	 */
	@Test
	public void testSubmitReview() 
	{
		con.login(REVID1);
		
		Review rev = new Review();
		rev.setScore(4);
		rev.setComment("sucks");
		
		con.submitReview(PID, rev);
		
		con.logout();
		
		con.login(REVID2);
		
		Review rev1 = new Review();
		rev.setScore(4);
		rev.setComment("sucks");
		
		con.submitReview(PID, rev1);
		
		con.logout();
		
		con.login(REVID3);
		
		Review rev2 = new Review();
		rev.setScore(4);
		rev.setComment("sucks");
		
		con.submitReview(PID, rev2);
		
		assertEquals("I hope that things work correctly sort of cheated", rev.getComment(), con.getReviewsForPaper(PID).get(PID).getComment());
	}

	/**
	 * Test method for {@link model.Conference#spcSubmitRecommendation(int, model.Recommendation)}.
	 */
	@Test
	public void testSpcSubmitRecommendation() 
	{
		con.login(SPCID);
		
		Recommendation rec = new Recommendation();
		rec.setState(3);
		rec.setRationale("success");
		
		con.spcSubmitRecommendation(PID, rec);
		
		assertEquals("should work right?", rec.toString(), con.getRecommendationForPaper(PID).toString());
	}

	/**
	 * Test method for {@link model.Conference#getAllPapers()}.
	 */
	@Test
	public void testGetAllPapers() 
	{
		assertEquals("", paper, con.getAllPapers().get(PID));
	}

	/**
	 * Test method for {@link model.Conference#getCurrentUser()}.
	 */
	@Test
	public void testGetCurrentUser() 
	{
		con.login(PCID);
		
		assertEquals("Should be right",PCID, con.getCurrentUser().myID);
	}

	/**
	 * Test method for {@link model.Conference#submitDecision(int, int)}.
	 */
	@Test
	public void testSubmitDecision() 
	{
		con.login(PCID);
		
		con.submitDecision(PID, 1);
		
		assertEquals("This is correct", 1, con.getPaperDecision(PID));
	}

	/**
	 * Test method for {@link model.Conference#changeUserRole(int, int)}.
	 */
	@Test
	public void testChangeUserRole() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Conference#getUserByRole(int)}.
	 */
	@Test
	public void testGetUserByRole() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Conference#getPapersByAuthor(int)}.
	 */
	@Test
	public void testGetPapersByAuthor() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Conference#getPapersBySpc(int)}.
	 */
	@Test
	public void testGetPapersBySpc()
	{
		fail("Not yet implemented"); // TODO
	}
	
	/**
	 * Test method for {@link model.Conference#removePaper(model.Paper)}.
	 */
	@Test
	public void testRemovePaper() 
	{
		con.login(AID);
		
		con.removePaper(paper);
		
		assertNull("I am not sure if it is null or DNE", con.getPaper(PID));
	}

	/**
	 * Test method for {@link model.Conference#getDaysLeft()}.
	 */
	@Test
	public void testGetDaysLeft()
	{
		
		
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Conference#getDeadline()}.
	 */
	@Test
	public void testGetDeadline() 
	{
		fail("Not yet implemented"); // TODO
	}
}
