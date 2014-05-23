/**
 * 
 */
package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
	public void MCP() throws IOException
	{
		newCon = new Conference(null);
		loadFiles();
		loadGUI();
	}
	
	private void loadFiles() throws FileNotFoundException
	{
		String UserFileName = "../files/usercsv.txt";
		String PaperFileName = "../files/papercsv.txt";
		BufferedReader fileIn;
		
		try 
		{
			fileIn = new BufferedReader(new FileReader(UserFileName));
			
			while (fileIn.ready())
			{
				String line = fileIn.readLine();
				String[] portion = line.split(",");
				
				User person = new User(null);
				person.setKey(0);
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.print(e);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
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

	}

}
