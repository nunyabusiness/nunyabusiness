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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import model.BusinessRuleException;
import model.ConfChangeType;
import model.Conference;
import model.Paper;
import model.Recommendation;
import model.Review;
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
	
	private Connection c;
	
	/**
	 * The MCP constructor because...
	 * @throws IOException this program deals with IO and could throw an IO exception
	 * @param theConference is the conference being referenced
	 */
	public MCP(Conference theConference) throws IOException
	{
		newCon = theConference;
		//newCon.addObserver(this);		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:nunya.sqlite");
			loadFiles();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		} finally {
			System.out.println("Opened database successfully");			
		}
		
	  }
	
	
	/**
	 * Loads files from specific locations.
	 * @throws IOException load in from files hence IO
	 * no parameters cause not needed yet
	 */
	private void loadFiles() throws IOException
	{
		
		try {
			Statement stmt = null;
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM user;" );
			
			while ( rs.next() ) {
				User u = new User(rs.getInt("userID"), rs.getInt("roleID"), 
						rs.getString("firstName"), rs.getString("lastName"), 
						rs.getString("email"));
				//newCon.addUser(u);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		String UserFileName = "Files/usercsv.txt";
//		BufferedReader fileIn = null;
//		char para = '"';
//		char blank = ' ';
//		
//		//try catch 
//		try 
//		{
//			fileIn = new BufferedReader(new FileReader(UserFileName));
//		}
//		catch (FileNotFoundException e)
//		{
//			System.out.print(e);
//		}
//		
//		//0-"UserID",1-"FirstName",2-"LastName",3-"email",4-"RoleID""
//		//fixed above
//		while (fileIn.ready())
//		{
//			String line = fileIn.readLine();
//			String[] portion = line.split(",");
//			for (String hi: portion)
//			{
//				hi.replace(para, blank);
//			}
//			
//			//integers in from the file of strings
//			int id = Integer.parseInt(portion[0]);
//			int role = Integer.parseInt(portion[4]);
//			
//			//int id, int role, String first, String last, String email
//			User person = new User(id, role, portion[1], portion[2], portion[3]);
//			newCon.addUser(person);
//		}
//		
//		fileIn.close();
//		
//		//new file
//		String PaperFileName = "Files/papercsv.txt";
//		
//		//try catch
//		try 
//		{
//			fileIn = new BufferedReader(new FileReader(PaperFileName));
//		}
//		catch (FileNotFoundException e)
//		{
//			System.out.print(e);
//		}
//		
//		//Adjustments will be made when the paper class is flushed out.
//		while (fileIn.ready())
//		{
//			//special character splits for splits sake
//            String line = fileIn.readLine();
//            String[] item = line.split("~"); //split parts of the paper/review/recom
//            String[] papers = item[0].split(",");
//            String[] recom = item[1].split(",");
//            String[] review;
//            if (item[2].contains("^"))
//            {
//            	review = item[2].split("^");
//            }
//            else 
//            {
//            	review = new String[1];
//            	review[0] = item[2];
//            }
//            
//           
//            //paper id, author id, title, abstract, filename,spcId~recommendation~ id,score, comments^ id,score, comments
//            int paperId = Integer.parseInt(papers[0]);
//            int authorId = Integer.parseInt(papers[1]);
//            int spcId = Integer.parseInt(papers[5]);
//            
//            //paper stuff
//            //int paperID, int authorID, String title, String anAbstract, String file~
//            Paper current = new Paper(paperId, authorId, papers[2], papers[3], papers[4]);
//            try {
//				current.assignSpc(spcId);
//			} catch (BusinessRuleException e) {
//				e.printStackTrace();
//			}
//           
//           
//            //recomendation
//            int reco = Integer.parseInt(recom[0]); 
//            Recommendation rec = new Recommendation();
//            rec.setState(reco);
//            rec.setRationale(recom[1]);
//            current.setRecommendation(rec);
//           
//            //Reviews
//            for (String that: review)
//            {
//            	String[] rev = that.split(",");
//            	
//            	int revId = Integer.parseInt(rev[0]);
//            	int avg = Integer.parseInt(rev[1]);
//                try {
//					current.assignReviewer(revId);
//				} catch (BusinessRuleException e) {
//					e.printStackTrace();
//				}
//                Review view = new Review(avg, rev[2]);
//                current.submitReviewToPaper(view);
//            } 
//            
//            try {
//				newCon.addPaper(current);
//			} catch (BusinessRuleException e) {
//				e.printStackTrace();
//			} //String title, String Abstract, String filename
//		}
//
//		fileIn.close();
	}

	/**
	 * write out to files
	 * @param users list of users
	 * @param papers list of papers
	 * @throws IOException writing so IO
	 */
//	public void writeOut(ArrayList<User> users, ArrayList<Paper> papers) throws IOException
//	{
//		String UserFileName = "Files/usercsv.txt";
//		BufferedWriter fileOut = null;
//		
//		//try catch
//		try 
//		{
//			fileOut = new BufferedWriter(new FileWriter(UserFileName));
//		}
//		catch (FileNotFoundException e)
//		{
//			System.out.print(e);
//		}
//		
//		//for all users write to the file as follows without going to the end of the file first
//		for (User cur: users)
//		{
//			//int id, String first, String last, String email, int role
//			fileOut.write(cur.getID() + "," + cur.getFirstName() + "," + 
//					cur.getLastName() + "," + cur.getEmail() + "," + cur.getRole() + "\n");
//		}
//		
//		fileOut.close();
//		
//		String PaperFileName = "Files/papercsv.txt";
//		
//		//try catch
//		try 
//		{
//			fileOut = new BufferedWriter(new FileWriter(PaperFileName));
//		}
//		catch (FileNotFoundException e)
//		{
//			System.out.print(e);
//		}
//		
//		//for all users write to the file as follows without going to the end of the file first
//		for (Paper cur: papers)
//		{
//			//int id, String first, String last, String email, int role
//			//fileOut.write(cur.toString() + "\n");
//			//myID + "," + myAuthorID + "," + myTitle + "," + myAbstract + "," + myFile + "," + mySubchair
//			//+ "~" + recommendation.toString() + "~";
//			Recommendation r = cur.getRec();
//			fileOut.write(cur.getId() + "," + cur.getAuthorID() + "," + cur.getTitle() + "," + cur.getAbstract() +
//					"," + cur.getFile() + "," + cur.getSubchairID() + "~" + r.toString() + "~");
//			
//			if (cur.hasRev())
//			{
//				List<Review> rev = cur.getRev();
//				List<Integer> revId = cur.getReviewerList();
//				
//				for (int i = 0; i < revId.size(); i++)
//				{
//					int id = revId.get(i);
//					Review current = rev.get(i);
//					fileOut.write(id + "," + current.getScore() + "," + current.getComment() + "^");
//				}
//			}
//			else
//			{
//				fileOut.write(0 + "," + 0 + ",null" );
//			}
//			
//			fileOut.write("\n");
//		}
//		
//		fileOut.close();
//		
//		//will do the same for papers again when it gets flushed out.
//	}

	/**
	 * Main void 
	 * @param args command line inputs if they exist
	 */
	public static void main(String[] args) 
	{
		java.awt.EventQueue.invokeLater(new Runnable() {
            Conference con = new Conference();
			
			public void run() {
            	ProjectJFrame gui = new ProjectJFrame(con);
            	con.addObserver(gui);
            	gui.displayLogin();
            }
        });
		
	}

//	public void update(Observable o, Object arg) {
//		if (arg == ConfChangeType.CONF_SAVED) {
//			try {
//				writeOut(newCon.getAllUsers(), newCon.getAllPapers());
//			} catch (IOException e) {
//				JOptionPane.showMessageDialog(null, "Error writing out");
//			}
//		}
//	}

}
