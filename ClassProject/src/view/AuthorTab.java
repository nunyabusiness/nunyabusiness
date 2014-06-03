package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Conference;
import model.Paper;
import model.User;

/**
 * JPanel which will act as the tab for the Author view.
 * 
 * @author Erik Tedder
 * @date 6/2/2014
 */
@SuppressWarnings("serial")
public class AuthorTab extends JPanel {
	
	private Conference myConference;
	private JPanel myCenterPanel;

	/**
	 * Constructor of the AuthorTab.
	 * 
	 * @param theConference The conference of this tab.
	 */
	public AuthorTab(final Conference theConference) {
		super();
		setBackground(new java.awt.Color(255, 255, 255));
		
		myConference = theConference;

		setLayout(new BorderLayout());
		add(new JLabel("Your Papers (Click to View)", SwingConstants.CENTER), BorderLayout.NORTH);
		JPanel innerCenterPanel = new JPanel();
		innerCenterPanel.setBackground(new java.awt.Color(255, 255, 255));
		myCenterPanel = new JPanel();
		myCenterPanel.setLayout(new BoxLayout(myCenterPanel, BoxLayout.Y_AXIS));
		myCenterPanel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		
		myCenterPanel.setBackground(new java.awt.Color(255, 255, 255));
		
		innerCenterPanel.add(myCenterPanel);
		add(innerCenterPanel, BorderLayout.CENTER);
		
		updateDisplay();
	}

	/**
	 * Method which updates the display of current papers to an author.
	 */
	public void updateDisplay() {
		if (myConference.getCurrentUser() != null) {
			myCenterPanel.removeAll();
			
			User user = myConference.getCurrentUser();
			ArrayList<Paper> papers = (ArrayList<Paper>) myConference.getPapersByAuthor(user.getID());
			
			for (final Paper p : papers) {
				JButton button = new JButton(p.getTitle());
				button.setAlignmentX(CENTER_ALIGNMENT);
				button.addActionListener(new ActionListener() {					
					public void actionPerformed(ActionEvent e) {
						new PaperDialog(p);
					}
				});
				myCenterPanel.add(button);
				myCenterPanel.add(Box.createRigidArea(new Dimension(0,3)));
			}
			
			if (papers.size() == 0) {
				myCenterPanel.add(new JLabel("No current papers on file", SwingConstants.CENTER));
			}
		}
	}
	
	/**
	 * Inner JDialog class that is responsible for showing the contents of a paper.
	 * 
	 * @author Erik Tedder	 *
	 */
	public class PaperDialog extends JDialog {
		
		private Paper myPaper;
		
		/**
		 * Constructor of a new PaperDialog.
		 * 
		 * @param thePaper The paper to have contents displayed.
		 */
		public PaperDialog(final Paper thePaper) {
			super();
			setTitle(thePaper.getTitle());
			
			myPaper = thePaper;			
			
			initDialog();
			setLocationRelativeTo(null);
			pack();
			setPreferredSize(new Dimension(175, 500));
			setResizable(false);
			setVisible(true);
		}

		/**
		 * Initialize the dialog window.
		 */
		private void initDialog() {
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			add(panel);
			panel.add(new JLabel("Selected Paper Information:"), BorderLayout.NORTH);
			
			JPanel centerPanel = new JPanel();
			centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
			
			Component separator = Box.createRigidArea(new Dimension(0, 5));
			
			JLabel titleLabel = new JLabel();
			titleLabel.setText("<HTML><U>Title:</U></HTML>");
			titleLabel.setAlignmentX(CENTER_ALIGNMENT);
			centerPanel.add(titleLabel);			
			centerPanel.add(new JLabel(myPaper.getTitle()));
			centerPanel.add(separator);
			JLabel abstractLabel = new JLabel();
			abstractLabel.setText("<HTML><U>Abstract:</U></HTML>");
			abstractLabel.setAlignmentX(CENTER_ALIGNMENT);
			centerPanel.add(abstractLabel);
			JLabel absLabel = new JLabel("<HTML><body style='width: 150px'>" + myPaper.getAbstract());
			centerPanel.add(absLabel);
			centerPanel.add(separator);
			JLabel fileLabel = new JLabel();
			fileLabel.setText("<HTML><U>File Name:</U></HTML>");
			fileLabel.setAlignmentX(CENTER_ALIGNMENT);
			centerPanel.add(fileLabel);
			centerPanel.add(new JLabel(myPaper.getFile()));
			centerPanel.add(separator);
			
			panel.add(centerPanel, BorderLayout.CENTER);
			
			//Button to just close the panel out
			JPanel bottomPanel = new JPanel();
			bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			//Button to remove the paper.
			JButton removeButton = new JButton("Delete Paper");
			removeButton.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Confirm Paper Deletion", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.OK_OPTION) {
						myConference.removePaper(myPaper);
						dispose();
					}					
				}
			});
			
			bottomPanel.add(okButton);
			bottomPanel.add(Box.createRigidArea(new Dimension(10, 0)));
			bottomPanel.add(removeButton);
			
			panel.add(bottomPanel, BorderLayout.SOUTH);
		}
	}
}
