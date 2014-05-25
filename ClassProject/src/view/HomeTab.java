package view;

import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.ConfChangeType;
import model.Conference;

/**
 * JPanel class for the HomeTab of the TCSS 360 Team project. Home tab displays user's name
 * and various information pertaining to the current conference.
 * 
 * @author Erik Tedder
 * @date 5/25/2014
 */
@SuppressWarnings("serial")
public class HomeTab extends JPanel implements Observer {

	/**
	 * The current conference.
	 */
	private Conference myConference;
	
	/**
	 * JLabel that will contain the date of the Conference.
	 */
	private JLabel myConferenceDateLabel;
	
	/**
	 * JLabel that displays the current user's name.
	 */
	private JLabel myHomeTabHeader;
	
	/**
	 * JLabel that displays the number of papers submitted by user.
	 */
	private JLabel myPaperSubmitLabel;
	
	/**
	 * JLabel that displays the days remaining til Conference.
	 */
	private JLabel myDaysLeftLabel;
	
	/**
	 * Constructor of the HomeTab.
	 * 
	 * @param theConference the current conference.
	 */
	public HomeTab(Conference theConference) {
		super();
		
		myConference = theConference;
		myConferenceDateLabel = new JLabel();
		myHomeTabHeader = new JLabel();
		myPaperSubmitLabel = new JLabel();
		myDaysLeftLabel = new JLabel();
		
		initPanel();
		setLabelValues();
	}

	/**
	 * Method to initialize the JPanel of the HomeTab.
	 */
	private void initPanel() {
		JLabel confInfoLabel = new JLabel();
		
		setBackground(new java.awt.Color(255, 255, 255));

        myHomeTabHeader.setFont(new java.awt.Font("Tahoma", 1, 18)); 

        confInfoLabel.setFont(new java.awt.Font("Tahoma", 2, 14)); 
        confInfoLabel.setText("Conference Information:");

        GroupLayout homeTabLayout = new GroupLayout(this);
        setLayout(homeTabLayout);
        homeTabLayout.setHorizontalGroup(
            homeTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(homeTabLayout.createSequentialGroup()
                .addGroup(homeTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(homeTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(homeTabLayout.createParallelGroup(
                        		GroupLayout.Alignment.LEADING)
                            .addComponent(myHomeTabHeader)
                            .addComponent(confInfoLabel)))
                    .addGroup(homeTabLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(homeTabLayout.createParallelGroup(
                        		GroupLayout.Alignment.LEADING)
                            .addComponent(myPaperSubmitLabel)
                            .addComponent(myDaysLeftLabel)
                            .addComponent(myConferenceDateLabel))))
                .addContainerGap(151, Short.MAX_VALUE))
        );
        homeTabLayout.setVerticalGroup(
            homeTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(homeTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(myHomeTabHeader)
                .addGap(52, 52, 52)
                .addComponent(confInfoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(myPaperSubmitLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(myDaysLeftLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(myConferenceDateLabel)
                .addContainerGap(138, Short.MAX_VALUE))
        );		
	}
	
	/**
	 * Method which sets all the values of the Home Tab's labels based upon the current logged
	 * in user and the current Conference.
	 */
	private void setLabelValues() {		
		//Display the name of the current user.
//		myHomeTabHeader.setText("Welcome Back " + myConference.getCurrentUser().getFirstName() 
//				+ " " + myConference.getCurrentUser().getLastName() + "!");
		
		//TODO: remove
		myHomeTabHeader.setText("Welcome Back");
		
		//TODO: Allow for papers to be counted for the current user.
		myPaperSubmitLabel.setText("You currently have # papers submitted to the Conference.");

		//Display the days left til conference
        myDaysLeftLabel.setText("The Conference deadline is in " + myConference.getDaysLeft() 
        		+ " day(s).");

        //Display the date of the conference
        myConferenceDateLabel.setText("The Conference will be held on " 
        		+ myConference.getDeadline().get(Calendar.MONTH) + "/" 
        		+ myConference.getDeadline().get(Calendar.DAY_OF_MONTH) 
        		+ "/" + myConference.getDeadline().get(Calendar.YEAR) + " .");
	}

	/**
	 * Observer update class which updates whenever the Conference class makes relevent 
	 * changes.
	 * 
	 * @param o The observable object being observed.
	 * @param arg The passed value (if applicable).
	 */
	public void update(Observable o, Object arg) {
		if (arg == ConfChangeType.LOGIN_SUCCESSFUL) {
			setLabelValues();
		}
	}
	
	

}
