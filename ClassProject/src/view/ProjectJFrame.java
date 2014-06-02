package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.KeyStroke;

import model.ConfChangeType;
import model.Conference;

/**
 * Beginning class for the GUI of the 360 team project.
 *
 * @author Erik Tedder
 * @date 5/23/2014
 */
@SuppressWarnings("serial")
public class ProjectJFrame extends JFrame implements Observer {
	
	private JPanel authorTab;
    private JLabel completedLabel;
    private JTable completedTable;
    private JLabel conferenceLabel;
    private JTextField userIDField;
    
    private JFrame loginFrame;
    private JTabbedPane myTabbedPane;
    private JScrollPane pcTab;
    private JPanel reviewTab;
    private JPanel spcTab;
    
    private ProjectMenuBar myMenuBar;
    private HomeTab myHomeTab;
    private SubmitTab mySubmitTab;
    
    private Conference myConference;
    
    /**
     * Creation of a new JFrame for the team's project with a given conference to display the
     * information for.
     * 
     * @param theConference The conference in which to display information for.
     */
    public ProjectJFrame(final Conference theConference) {    	
    	myConference = theConference;
    	
    	myConference.addObserver(this);
    	
    	myMenuBar = new ProjectMenuBar(this, myConference);
    	myHomeTab = new HomeTab(myConference);
    	mySubmitTab = new SubmitTab(myConference);
    	
    	initLoginFrame();
        initComponents();        
        this.setLocationRelativeTo(null);
    }
    
    /**
     * Method which displays the login frame for the conference.
     * 
     * This method is used to start the GUI application from other sources.
     */
    public final void displayLogin() {
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }
    
    /**
     * Method to initialize and create the login frame.
     */
    private void initLoginFrame() {
    	//Initialize initial values
    	loginFrame = new JFrame();
        userIDField = new JTextField();
        JLabel userIDLabel = new JLabel();
        JLabel loginHeader = new JLabel();
        JButton loginButton = new JButton("Login");
        
        userIDLabel.setText("User-ID");

        loginHeader.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        loginHeader.setText("Welcome to the MSEE conference login portal");
        
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setTitle("MSEE Login");
        loginFrame.setResizable(false);        
        
        //create new loginaction object and add to loginbutton
        LoginAction loginAction = new LoginAction();
        loginButton.addActionListener(loginAction);
        
        //set userIDfield's action map to allow for hitting enter to submit
        userIDField.getInputMap().put((KeyStroke) loginAction.getValue(Action.ACCELERATOR_KEY), 
                loginAction.getValue(Action.NAME));
        userIDField.getActionMap().put(loginAction.getValue(Action.NAME), loginAction);
        
        //Layout generated by the NetBeans IDE 
        GroupLayout loginFrameLayout = new GroupLayout(loginFrame.getContentPane());
        loginFrame.getContentPane().setLayout(loginFrameLayout);
        loginFrameLayout.setHorizontalGroup(
            loginFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(loginFrameLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(loginHeader, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(GroupLayout.Alignment.TRAILING, loginFrameLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(loginFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, 
                    		loginFrameLayout.createSequentialGroup()
                        .addComponent(userIDLabel, GroupLayout.PREFERRED_SIZE, 
                        		GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userIDField, GroupLayout.PREFERRED_SIZE, 189, 
                        		GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(GroupLayout.Alignment.TRAILING, 
                    		loginFrameLayout.createSequentialGroup()
                        .addComponent(loginButton)
                        .addGap(140, 140, 140))))
        );
        loginFrameLayout.setVerticalGroup(
            loginFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(loginFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loginHeader)
                .addGap(39, 39, 39)
                .addGroup(loginFrameLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(userIDLabel, GroupLayout.PREFERRED_SIZE, 
                    		GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(userIDField, GroupLayout.PREFERRED_SIZE, 
                    		GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, 
                		Short.MAX_VALUE)
                .addComponent(loginButton)
                .addContainerGap())
        );
    }
    
    /**
     * Method which initializes the remaining components of the Frame.
     */
    private void initComponents() {        
        myTabbedPane = new JTabbedPane();
        authorTab = new JPanel();
        reviewTab = new JPanel();
        spcTab = new JPanel();
        pcTab = new JScrollPane();
        JPanel pcPanel = new JPanel();
        JScrollPane pcScrollPane = new JScrollPane();
        completedTable = new JTable();
        completedLabel = new JLabel();
        conferenceLabel = new JLabel();        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Conference Controls");
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(650, 416));
        setResizable(false);

        myTabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        myTabbedPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        myTabbedPane.addTab("Home", myHomeTab);

        myTabbedPane.addTab("Submit Paper", mySubmitTab);

        authorTab.setBackground(new java.awt.Color(255, 255, 255));

        GroupLayout authorTabLayout = new GroupLayout(authorTab);
        authorTab.setLayout(authorTabLayout);
        authorTabLayout.setHorizontalGroup(
            authorTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        authorTabLayout.setVerticalGroup(
            authorTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        myTabbedPane.addTab("Your Papers", authorTab);

        reviewTab.setBackground(new java.awt.Color(255, 255, 255));
        reviewTab.setEnabled(false);

        GroupLayout reviewTabLayout = new GroupLayout(reviewTab);
        reviewTab.setLayout(reviewTabLayout);
        reviewTabLayout.setHorizontalGroup(
            reviewTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        reviewTabLayout.setVerticalGroup(
            reviewTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        myTabbedPane.addTab("Review", reviewTab);

        spcTab.setBackground(new java.awt.Color(255, 255, 255));

        GroupLayout spcTabLayout = new GroupLayout(spcTab);
        spcTab.setLayout(spcTabLayout);
        spcTabLayout.setHorizontalGroup(
            spcTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        spcTabLayout.setVerticalGroup(
            spcTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        myTabbedPane.addTab("Subprogram Chair View", spcTab);

        pcTab.setBackground(new java.awt.Color(255, 255, 255));
        pcTab.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pcPanel.setBackground(new java.awt.Color(255, 255, 255));

        completedTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Paper", "Author", "SPC Review", "Review 1", "Review 2", "Review 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        completedTable.setShowHorizontalLines(false);
        completedTable.setShowVerticalLines(false);
        pcScrollPane.setViewportView(completedTable);

        completedLabel.setText("Completed Reviews");
        completedLabel.setToolTipText("From this table you will be able to look over any "
        		+ "papers that have already completed their review process. ");

        completedTable.getModel().setValueAt(123, 0, 0);
        
        GroupLayout pcPanelLayout = new GroupLayout(pcPanel);
        pcPanel.setLayout(pcPanelLayout);
        pcPanelLayout.setHorizontalGroup(
            pcPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pcPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pcPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(pcScrollPane, GroupLayout.PREFERRED_SIZE, 572, GroupLayout.PREFERRED_SIZE)
                    .addComponent(completedLabel))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        pcPanelLayout.setVerticalGroup(
            pcPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pcPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(completedLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pcScrollPane, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(307, Short.MAX_VALUE))
        );

        pcTab.setViewportView(pcPanel);

        myTabbedPane.addTab("Program Chair View", pcTab);

        conferenceLabel.setFont(new java.awt.Font("Dialog", 1, 24)); 
        conferenceLabel.setForeground(new java.awt.Color(255, 51, 51));
        conferenceLabel.setText("MSEE Conference Controls");

        setJMenuBar(myMenuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(conferenceLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(myTabbedPane, GroupLayout.PREFERRED_SIZE, 617, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(conferenceLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(myTabbedPane, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();        
    }

    /**
     * Still needing to get done to implement observer/observable. 
     */
	public void update(Observable o, Object arg) {
		//User has successfully logged in
		if (arg == ConfChangeType.LOGIN_SUCCESSFUL) {
			loginFrame.setVisible(false);
            this.setVisible(true);
            myHomeTab.setLabelValues();
            determinePermission();
		}
		
		//User log-in credentials are invalid
		if (arg == ConfChangeType.LOGIN_FAIL) {
			JOptionPane.showMessageDialog(this, "Invalid User-ID, Please try again");
		}
		
		//User has logged out
		if (arg == ConfChangeType.LOGOUT) {
			this.setVisible(false);
			myTabbedPane.setSelectedIndex(0);
			mySubmitTab.clearLabels();
			userIDField.setText("");
			loginFrame.setVisible(true);
		}
	}    
	
	/**
	 * Method which checks the current logged in user to determine which tabs they should have
	 * access to view based on their role.
	 */
	private void determinePermission() {
		int userRole = myConference.getCurrentUser().getRole();
		
		switch (userRole) {
		case 0: //user is an Author
			myTabbedPane.setEnabledAt(3, false);
			myTabbedPane.setEnabledAt(4, false);
			myTabbedPane.setEnabledAt(5, false);
			break;
		case 1: //Program Chair
			myTabbedPane.setEnabledAt(3, false);
			myTabbedPane.setEnabledAt(4, false);
			myTabbedPane.setEnabledAt(5, true);
			break;
		case 2: //subprogram chair
			myTabbedPane.setEnabledAt(3, false);
			myTabbedPane.setEnabledAt(4, true);
			myTabbedPane.setEnabledAt(5, false);			
			break;
		case 3: //admin or debug (shouldn't be used within user file)
			myTabbedPane.setEnabledAt(3, true);
			myTabbedPane.setEnabledAt(4, true);
			myTabbedPane.setEnabledAt(5, true);
			break;
		case 4: //Reviewer
			myTabbedPane.setEnabledAt(3, true);
			myTabbedPane.setEnabledAt(4, false);
			myTabbedPane.setEnabledAt(5, false);
			
			break;
		}
	}

	/**
	 * Inner AbstractAction class to allow for the user to hit 'enter' to log in instead of 
	 * always having to click on the login button.
	 * 
	 * @author Erik
	 * @date 5/28/2014
	 */
	private class LoginAction extends AbstractAction {
		
	    /**
	     * Constructor for the LoginAction
	     */
		LoginAction() {
	        putValue(Action.NAME, "Login");
	        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
	    }
	    
	    /**
	     * The action that attempts to log into the program based on given userIDField value.
	     * 
	     * @param the_event the event that triggered this action.
	     */
	    public void actionPerformed(final ActionEvent the_event) {
	    	try {
				int id = Integer.parseInt(userIDField.getText());
				myConference.login(id);
			} catch (NumberFormatException error) {
				JOptionPane.showMessageDialog(null, "Invalid ID format. ID must be a "
						+ "number");
			}
	    }
	}

}
