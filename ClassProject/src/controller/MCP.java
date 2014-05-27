/**
 * 
 */
package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import view.ProjectJFrame;
import model.Conference;
import model.User;

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
	public MCP() throws IOException
	{
		newCon = new Conference();
		loadFiles();
		
	}
	
	private void loadFiles() throws IOException
	{
		String UserFileName = "src/files/usercsv.txt";
		//String PaperFileName = "src/files/papercsv.txt";
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
		
	}
	
	private void loadGUI()
	{
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProjectJFrame(new Conference()).displayLogin();
            }
        });
		
	}

}
