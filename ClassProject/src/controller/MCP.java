/**
 * packages because thats where it exists.
 */
package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Conference;
import model.Paper;
import model.User;
import view.ProjectJFrame;

/**
 * Feel free to change move relocate. In reality its just a wrapper so just abuse
 * @author Christopher Barrett, Erik Tedder
 * MCP Class is a throw back to an old programming style used in the 80's for keeping users enthraled with different games.
 */
public class MCP 
{
	/**
	 * to access the conference or create the conference
	 */
	Conference newCon;
	
	/**
	 * The MCP constructor because...
	 * @throws IOException this program deals with IO and could throw an IO exception
	 * @param theConference is the conference being referenced
	 */
	public MCP(Conference theConference) throws IOException
	{
		newCon = theConference;
		loadFiles();
	}
	
	/**
	 * Loads files from specific locations.
	 * @throws IOException load in from files hence IO
	 * no parameters cause not needed yet
	 */
	private void loadFiles() throws IOException
	{
		String UserFileName = "src/files/usercsv.txt";
		BufferedReader fileIn = null;
		char para = '"';
		char blank = ' ';
		
		//try catch 
		try 
		{
			fileIn = new BufferedReader(new FileReader(UserFileName));
		}
		catch (FileNotFoundException e)
		{
			System.out.print(e);
		}
		
		//0-"UserID",1-"FirstName",2-"LastName",3-"email",4-"RoleID""
		//fixed above
		while (fileIn.ready())
		{
			String line = fileIn.readLine();
			String[] portion = line.split(",");
			for (String hi: portion)
			{
				hi.replace(para, blank);
			}
			
			//integers in from the file of strings
			int id = Integer.parseInt(portion[0]);
			int role = Integer.parseInt(portion[4]);
			
			//int id, int role, String first, String last, String email
			User person = new User(id, role, portion[1], portion[2], portion[3]);
			newCon.addUser(person);
		}
		
		fileIn.close();
		
		//new file
		String PaperFileName = "src/files/papercsv.txt";
		
		//try catch
		try 
		{
			fileIn = new BufferedReader(new FileReader(PaperFileName));
		}
		catch (FileNotFoundException e)
		{
			System.out.print(e);
		}
		
		//Adjustments will be made when the paper class is flushed out.
		while (fileIn.ready())
		{
		            String line = fileIn.readLine();
		            String[] item = line.split("~"); //split parts of the paper/review/recom
		            String[] papers = item[0].split(",");
		            String[] recom = item[1].split(",");
		            String[] review = item[2].split("^");
		           
		            //int id, int role, String first, String last, String email
		            Paper current = new Paper(newCon, blank, blank, null, line, line);
		            newCon.addPaper(current);
		           
		            //current.addRec(recom[0]);
		           
		            for (String that: review)
		            {
		                String[] rev = that.split(",");
		                //current.addRev(something, something, something);
		            }
		}

		fileIn.close();
	}

	/**
	 * write out to files
	 * @param users list of users
	 * @param papers list of papers
	 * @throws IOException writing so IO
	 */
	public void writeOut(ArrayList<User> users, ArrayList<Paper> papers) throws IOException
	{
		String UserFileName = "src/files/usercsv.txt";
		BufferedWriter fileOut = null;
		
		//try catch
		try 
		{
			fileOut = new BufferedWriter(new FileWriter(UserFileName));
		}
		catch (FileNotFoundException e)
		{
			System.out.print(e);
		}
		
		//for all users write to the file as follows without going to the end of the file first
		for (User cur: users)
		{
			//int id, String first, String last, String email, int role
			fileOut.write(cur.getID() + "," + cur.getFirstName() + "," + 
					cur.getLastName() + "," + cur.getEmail() + "," + cur.getRole());
		}
		
		fileOut.close();
		
		//will do the same for papers again when it gets flushed out.
	}

	/**
	 * Main void 
	 * @param args command line inputs if they exist
	 */
	public static void main(String[] args) 
	{
		java.awt.EventQueue.invokeLater(new Runnable() {
            Conference con = new Conference();
			
			public void run() {
            	try {
            		new MCP(con);
            	} catch (IOException e) {
            		System.err.println(e.getMessage());
            	}
                new ProjectJFrame(con).displayLogin();
            }
        });
		
	}

}
