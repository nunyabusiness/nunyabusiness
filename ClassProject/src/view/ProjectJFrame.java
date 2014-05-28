package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

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

import model.ConfChangeType;
import model.Conference;
import model.User;




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
    private JTable completedTabel;
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
        JButton loginButton = new JButton();
        
        userIDLabel.setText("User-ID");

        loginHeader.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        loginHeader.setText("Welcome to the MSEE conference login portal");
        
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setTitle("MSEE Login");
        loginFrame.setResizable(false);        

        loginButton.setText("Login");
        loginButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				myConference.login(Integer.parseInt(userIDField.getText()));				
			}
		});

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
        completedTabel = new JTable();
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

        completedTabel.setModel(new javax.swing.table.DefaultTableModel(
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
        completedTabel.setShowHorizontalLines(false);
        completedTabel.setShowVerticalLines(false);
        pcScrollPane.setViewportView(completedTabel);

        completedLabel.setText("Completed Reviews");
        completedLabel.setToolTipText("From this table you will be able to look over any papers that have already completed their review process. ");

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
        
        //disables all panels except for the home and submit paper tabs
        for (int i = 2; i < myTabbedPane.getTabCount(); i++)
        	myTabbedPane.setEnabledAt(i, false);

        pack();        
    }

    /**
     * Still needing to get done to implement observer/observable. 
     */
	public void update(Observable o, Object arg) {
		if (arg == ConfChangeType.LOGIN_SUCCESSFUL) {
			loginFrame.setVisible(false);
            this.setVisible(true);
            myHomeTab.setLabelValues();
		}
		
		if (arg == ConfChangeType.LOGIN_FAIL) {
			JOptionPane.showMessageDialog(this, "Invalid User-ID, Please try again");
		}
	}

    

}
