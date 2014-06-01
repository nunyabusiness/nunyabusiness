/**
 * 
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
 * @author Christopher
 *
 */
public class MCP 
{
	Conference newCon;
	
	/**
	 * @throws IOException 
	 * 
	 */
	public MCP(Conference theConference) throws IOException
	{
		newCon = theConference;
		loadFiles();
		
	}
	
	private void loadFiles() throws IOException
	{
		String UserFileName = "src/files/usercsv.txt";
		BufferedReader fileIn = null;
		char para = '"';
		char blank = ' ';
		
		try 
		{
			fileIn = new BufferedReader(new FileReader(UserFileName));
		}
		catch (FileNotFoundException e)
		{
			System.out.print(e);
		}
		
		//0-"UserID",1-"FirstName",2-"LastName",3-"email",4-"ConferenceID",
		//5-"ConferenceTitle",6-"ConferenceDescription",7-"RoleID",8-"Role"
		//TODO: fix above
		while (fileIn.ready())
		{
			String line = fileIn.readLine();
			String[] portion = line.split(",");
			for (String hi: portion)
			{
				hi.replace(para, blank);
			}
			
			int id = Integer.parseInt(portion[0]);
			int role = Integer.parseInt(portion[4]);
			
			//int id, int role, String first, String last, String email
			User person = new User(id, role, portion[1], portion[2], portion[3]);
			newCon.addUser(person);
		}
		
		fileIn.close();
		
		String PaperFileName = "src/files/papercsv.txt";
		
		try 
		{
			fileIn = new BufferedReader(new FileReader(PaperFileName));
		}
		catch (FileNotFoundException e)
		{
			System.out.print(e);
		}
		
		while (fileIn.ready())
		{
		            String line = fileIn.readLine();
		            String[] item = line.split("~");
		            String[] papers = item[0].split(",");
		            String[] recom = item[1].split(",");
		            String[] review = item[2].split("^");
		           
		            //int id, int role, String first, String last, String email
		            Paper current = new Paper(newCon, blank, blank, null, line, line);
		            newCon.addPaper(current);
		           
		            current.addRec(recom[0]);
		           
		            for (String that: review)
		            {
		                String[] rev = that.split(",");
		                current.addRev(something, something, something);
		            }
		}

		fileIn.close();
	}

	public void writeOut(ArrayList<User> users, ArrayList<Paper> papers) throws IOException
	{
		String UserFileName = "src/files/usercsv.txt";
		BufferedWriter fileOut = null;
		
		try 
		{
			fileOut = new BufferedWriter(new FileWriter(UserFileName));
		}
		catch (FileNotFoundException e)
		{
			System.out.print(e);
		}
		
		for (User cur: users)
		{
			//int id, int role, String first, String last, String email
			fileOut.write(cur.getID() + "," + cur.getRole() + "," + 
					cur.getFirstName() + "," + cur.getLastName() + "," + cur.getEmail());
		}
	}

	/**
	 * @param args
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
