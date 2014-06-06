/**
 * 
 */
package Tests;

import static org.junit.Assert.*;
import model.Recommendation;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Christopher
 *
 */
public class TestRecommendation 
{
	Recommendation rec;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		rec = new Recommendation();
	}

	/**
	 * Test method for {@link model.Recommendation#setState(int)}.
	 */
	@Test
	public void testGetState() 
	{
		rec.setState(4);
		
		assertEquals("State is equals", 4, rec.getState());
	}

	/**
	 * Test method for {@link model.Recommendation#toString()}.
	 */
	@Test
	public void testToString() 
	{
		rec.setRationale("comments");
		rec.setState(4);
		
		assertEquals("comments,4" , rec.toString());
	}

}
