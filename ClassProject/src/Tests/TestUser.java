/**
 * 
 */
package Tests;

import static org.junit.Assert.*;
import model.User;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Christopher
 *
 */
public class TestUser 
{
	private static final int USERID = 21;
	private static final int ROLE = 4;
	private static final int ROLE2 = 2;
	private static final String FIRST = "Charlie";
	private static final String LAST = "Horner";
	private static final String EMAIL = "CH@blah.edu";
	
	private User use;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public void setUp() throws Exception 
	{
		use = new User(USERID, ROLE, FIRST, LAST, EMAIL);
	}

	/**
	 * Test method for {@link model.User#getID()}.
	 */
	@Test
	public void testGetID() 
	{
		assertEquals("The get id works", USERID, use.getID());
	}

	/**
	 * Test method for {@link model.User#getFirstName()}.
	 */
	@Test
	public void testGetFirstName() 
	{
		assertEquals("The get first name works", FIRST, use.getFirstName());
	}

	/**
	 * Test method for {@link model.User#getLastName()}.
	 */
	@Test
	public void testGetLastName()
	{
		assertEquals("The get last name works", LAST, use.getLastName());
	}

	/**
	 * Test method for {@link model.User#getEmail()}.
	 */
	@Test
	public void testGetEmail() 
	{
		assertEquals("The get email works", EMAIL, use.getEmail());
	}

	/**
	 * Test method for {@link model.User#setRole(int)}.
	 */
	@Test
	public void testSetRole() 
	{
		use.setRole(ROLE2);
		
		assertEquals("The set/get role works", ROLE2, use.getRole());
	}

	/**
	 * Test method for {@link model.User#toString()}.
	 */
	@Test
	public void testToString()
	{
		assertEquals("The toString works", "Charlie Horner", use.toString());
	}

}
