package view;

import javax.swing.JPanel;

import model.Conference;

/**
 * JPanel which will act as the tab for the Review view.
 * 
 * @author Erik Tedder
 * @date 6/2/2014
 */
@SuppressWarnings("serial")
public class ReviewTab extends JPanel {
	
	private Conference myConference;

	public ReviewTab(final Conference theConference) {
		super();
		setBackground(new java.awt.Color(255, 255, 255));
		
		myConference = theConference;

	}
}
