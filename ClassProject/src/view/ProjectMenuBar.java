package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.Conference;

/**
 * The JMenuBar class for the 
 * 
 * @author Erik Tedder
 * @date 5/24/2014
 */
@SuppressWarnings("serial")
public class ProjectMenuBar extends JMenuBar {

	private JMenu myFileMenu;
	private JMenu myHelpMenu;
	private JMenuItem myExitMenuItem;
	private JMenuItem myHelpMenuItem;
	private JMenuItem myLogoutMenuItem;
	
	private Conference myConference;
	
	/**
	 * No-argument constructor of a new ProjectMenuBar.
	 */
	public ProjectMenuBar(final Conference theConference) {
		super();
		
		myFileMenu = new JMenu("File");
		myHelpMenu = new JMenu("Help");
		myExitMenuItem = new JMenuItem("Exit");
		myHelpMenuItem = new JMenuItem("Help");
		myLogoutMenuItem = new JMenuItem("Logout");
		
		
		myConference = theConference;
		
		initMenus();
	}
	
	/**
	 * Method which creates the menus for the menu bar and initializes the menu items.
	 */
	private void initMenus() {
		add(myFileMenu);
		add(myHelpMenu);
		
		myFileMenu.add(myLogoutMenuItem);
		myFileMenu.addSeparator();
		myFileMenu.add(myExitMenuItem);
		
		myHelpMenu.add(myHelpMenuItem);		
	}

}
