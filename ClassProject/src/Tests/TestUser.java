/**
 * 
 */
package Tests;

import static org.junit.Assert.*;
import model.BusinessRuleException;
import model.User;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Christopher
 *
 */
public class TestUser 
{
	int ID = 4;
	int ROLE = 2;
	String FIRST = "Charlie";
	String LAST = "Horner";
	String EMAIL = "CH@clean.com";
	User person;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		person = new User(ID, ROLE, FIRST, LAST, EMAIL);
	}

	/**
	 * Test method for {@link model.User#getID()}.
	 */
	@Test
	public void testGetID() 
	{	
		assertEquals(ID, person.getID());
	}

	/**
	 * Test method for {@link model.User#getRole()}.
	 */
	@Test
	public void testGetRole() 
	{
		assertEquals(ID, person.getID());
	}

	/**
	 * Test method for {@link model.User#getFirstName()}.
	 */
	@Test
	public void testGetFirstName() 
	{
		assertEquals(ID, person.getID());
	}

	/**
	 * Test method for {@link model.User#getLastName()}.
	 */
	@Test
	public void testGetLastName() 
	{
		assertEquals(ID, person.getID());
	}

	/**
	 * Test method for {@link model.User#getEmail()}.
	 */
	@Test
	public void testGetEmail() 
	{
		assertEquals(ID, person.getID());
	}

	/**
	 * Test method for {@link model.User#setRole(int)}.
	 */
	@Test
	public void testSetRole() 
	{
		person.setRole(1);
		
		assertEquals(1, person.getRole());
	}

	/**
	 * Test method for {@link model.User#submitPaper(int)}.
	 * @throws BusinessRuleException 
	 */
	@Test
	public void testSubmitPaper() throws BusinessRuleException 
	{
		person.submitPaper(1);
		
		fail("not sure how I am going to test this one.");
	}

	/**
	 * Test method for {@link model.User#submitPaper(int)}.
	 * @throws BusinessRuleException 
	 */
	@Test
	public void testSubmitFivePapers() throws BusinessRuleException 
	{
		person.submitPaper(1);
		person.submitPaper(2);
		person.submitPaper(3);
		person.submitPaper(4);
		person.submitPaper(5);
		fail("Throws exception");
	}
	
	/**
	 * Test method for {@link model.User#toString()}.
	 */
	@Test
	public void testToString() 
	{
		assertEquals("Charlie Horner", person.toString());
	}

}
