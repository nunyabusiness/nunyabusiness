package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.GroupLayout;

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
    private JTable completedTabel;
    private JLabel conferenceLabel;
    private JTextField emailField;

    private JPanel homeTab;
    private JButton jButton1;
    private JButton jButton2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JButton loginButton;
    private JFrame loginFrame;
    private JTabbedPane myTabbedPane;
    private JScrollPane pcTab;
    private JPanel reviewTab;
    private JPanel spcTab;
    private JPanel submitTab;
    private JTextField textField1;
    
    private ProjectMenuBar myMenuBar;
    
    private Conference myConference;
    
    /**
     * 
     * 
     * @param theConference
     */
    public ProjectJFrame(final Conference theConference) {    	
    	myConference = theConference;
    	myMenuBar = new ProjectMenuBar(this, myConference);
    	
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    public final void displayLogin() {
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }
    
    private void initComponents() {

        loginFrame = new JFrame();
        emailField = new JTextField();
        JLabel emailLabel = new JLabel();
        JLabel loginHeader = new JLabel();
        loginButton = new JButton();
        myTabbedPane = new JTabbedPane();
        homeTab = new JPanel();
        JLabel homeTabHeader = new JLabel();
        JLabel confInfoLabel = new JLabel();
        JLabel paperSubmitLabel = new JLabel();
        JLabel daysLeftLabel = new JLabel();
        jLabel1 = new JLabel();
        submitTab = new JPanel();
        JLabel submitLabel = new JLabel();
        JLabel titleLabel = new JLabel();
        textField1 = new JTextField();
        JLabel abstractLabel = new JLabel();
        JTextArea abstractTextArea = new JTextArea();
        jButton1 = new JButton();
        jLabel2 = new JLabel();
        jButton2 = new JButton();
        authorTab = new JPanel();
        reviewTab = new JPanel();
        spcTab = new JPanel();
        pcTab = new JScrollPane();
        JPanel pcPanel = new JPanel();
        JScrollPane pcScrollPane = new JScrollPane();
        completedTabel = new JTable();
        completedLabel = new JLabel();
        conferenceLabel = new JLabel();

        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setTitle("MSEE Login");
        loginFrame.setResizable(false);

        emailLabel.setText("Email Address");

        loginHeader.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        loginHeader.setText("Welcome to the MSEE conference login portal");

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

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
                    .addGroup(GroupLayout.Alignment.TRAILING, loginFrameLayout.createSequentialGroup()
                        .addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emailField, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(GroupLayout.Alignment.TRAILING, loginFrameLayout.createSequentialGroup()
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
                    .addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(loginButton)
                .addContainerGap())
        );

        abstractTextArea.setLineWrap(true);
        abstractTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Conference Controls");
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(650, 416));
        setResizable(false);

        myTabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        myTabbedPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        homeTab.setBackground(new java.awt.Color(255, 255, 255));

        homeTabHeader.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        homeTabHeader.setText("Welcome Back [user first name] [user last name]!");

        confInfoLabel.setFont(new java.awt.Font("Tahoma", 2, 14)); 
        confInfoLabel.setText("Conference Information:");

        paperSubmitLabel.setText("You currently have [paper number] submitted to the Conference.");

        daysLeftLabel.setText("The Conference deadline is in " + myConference.getDaysLeft() 
        		+ " day(s).");

        jLabel1.setText("The Conference will be held on " 
        		+ myConference.getDeadline().get(Calendar.MONTH) + "/" 
        		+ myConference.getDeadline().get(Calendar.DAY_OF_MONTH) 
        		+ "/" + myConference.getDeadline().get(Calendar.YEAR) + " .");

        GroupLayout homeTabLayout = new GroupLayout(homeTab);
        homeTab.setLayout(homeTabLayout);
        homeTabLayout.setHorizontalGroup(
            homeTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(homeTabLayout.createSequentialGroup()
                .addGroup(homeTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(homeTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(homeTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(homeTabHeader)
                            .addComponent(confInfoLabel)))
                    .addGroup(homeTabLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(homeTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(paperSubmitLabel)
                            .addComponent(daysLeftLabel)
                            .addComponent(jLabel1))))
                .addContainerGap(151, Short.MAX_VALUE))
        );
        homeTabLayout.setVerticalGroup(
            homeTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(homeTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(homeTabHeader)
                .addGap(52, 52, 52)
                .addComponent(confInfoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paperSubmitLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(daysLeftLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(138, Short.MAX_VALUE))
        );

        myTabbedPane.addTab("Home", homeTab);

        submitTab.setBackground(new java.awt.Color(255, 255, 255));

        submitLabel.setFont(new java.awt.Font("Dialog", 1, 18)); 
        submitLabel.setText("Submit a paper to be reviewed");

        titleLabel.setText("Title");

        abstractLabel.setText("Abstract");

        jButton1.setText("Select Paper");

        jLabel2.setText("[Paper Title].[extension]");

        jButton2.setText("Submit Paper");

        GroupLayout submitTabLayout = new GroupLayout(submitTab);
        submitTab.setLayout(submitTabLayout);
        submitTabLayout.setHorizontalGroup(
            submitTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(submitTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(submitTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(submitLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(submitTabLayout.createSequentialGroup()
                        .addGroup(submitTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(submitTabLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addComponent(abstractLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(submitTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(submitTabLayout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
                            .addGroup(submitTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(textField1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(abstractTextArea, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
                            .addComponent(jButton2))))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        submitTabLayout.setVerticalGroup(
            submitTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(submitTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(submitLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(submitTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(submitTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(abstractLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(abstractTextArea, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(submitTabLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        myTabbedPane.addTab("Submit Paper", submitTab);

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

        pack();
    }

    private void loginButtonActionPerformed(ActionEvent evt) {
        if ("admin".equals(emailField.getText())) {
            loginFrame.setVisible(false);
            this.setVisible(true);
        }
    }

    /**
     * Still needing to get done to implement observer/observable. Need ENUM CLASS!
     */
	public void update(Observable o, Object arg) {
		//
	}

    

}
