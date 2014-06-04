package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;

import model.Conference;
import model.Paper;
import model.Review;
import model.User;

/**
 * JScrollPane which will act as the tab for the subprogram chair view.
 * 
 * @author Erik Tedder
 * @date 6/2/2014
 */
@SuppressWarnings("serial")
public class SPCTab extends JScrollPane {

	private Conference myConference;
	private JTable myUnassignedTable;
	private JTable myAssignedTable;

	public SPCTab(final Conference theConference) {
		super();

		myConference = theConference;
		myUnassignedTable = new JTable();
		myAssignedTable = new JTable();

		myUnassignedTable.setDragEnabled(false);
		myUnassignedTable.getTableHeader().setReorderingAllowed(false);
		myUnassignedTable.getTableHeader().setResizingAllowed(false);
		
		myAssignedTable.setDragEnabled(false);
		myAssignedTable.getTableHeader().setReorderingAllowed(false);
		myAssignedTable.getTableHeader().setResizingAllowed(false);

		initClass();
	}

	private void initClass() {
		JPanel pcPanel = new JPanel();
		JPanel innerPcPanel = new JPanel();
		innerPcPanel.setBackground(new java.awt.Color(255, 255, 255));
		innerPcPanel.setLayout(new BoxLayout(innerPcPanel, BoxLayout.Y_AXIS));

		JLabel unassignedLabel = new JLabel("Papers Needing Reviewers");
		unassignedLabel.setAlignmentX(CENTER_ALIGNMENT);
		JScrollPane unassignedScrollPane = new JScrollPane();
		unassignedScrollPane.setPreferredSize(new Dimension(600, 125));

		setBackground(new java.awt.Color(255, 255, 255));
		setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		pcPanel.setBackground(new java.awt.Color(255, 255, 255));

		myUnassignedTable.setShowHorizontalLines(false);
		myUnassignedTable.setShowVerticalLines(false);
		unassignedScrollPane.setViewportView(myUnassignedTable);

		unassignedLabel.setToolTipText("From this table you will be able to look over any "
				+ "papers that have already completed their review process. ");

		myUnassignedTable.addMouseListener(new MouseAdapter() {			
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Integer paperID = (Integer) myUnassignedTable.getValueAt(myUnassignedTable.getSelectedRow(), 0);
					
					new AssignDialog(myConference.getUserByRole(4),	myConference.getPaper(paperID));
				}
			}
		});

		innerPcPanel.add(unassignedLabel);
		innerPcPanel.add(unassignedScrollPane);
		innerPcPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		
		JLabel assignedLabel = new JLabel("Papers Needing Reviewers");
		assignedLabel.setAlignmentX(CENTER_ALIGNMENT);
		JScrollPane assignedScrollPane = new JScrollPane();
		assignedScrollPane.setPreferredSize(new Dimension(600, 125));

		myAssignedTable.setShowHorizontalLines(false);
		myAssignedTable.setShowVerticalLines(false);
		assignedScrollPane.setViewportView(myAssignedTable);

		assignedLabel.setToolTipText("From this table you will be able to look over any "
				+ "papers that have already completed their review process. ");

		myAssignedTable.addMouseListener(new MouseAdapter() {			
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					//do nothing
				}
			}
		});

		innerPcPanel.add(assignedLabel);
		innerPcPanel.add(assignedScrollPane);
		innerPcPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		
		JButton assignSPC = new JButton("New Reviewer");
		assignSPC.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				new ReviewDialog(myConference.getUserByRole(0));
			}
		});
		assignSPC.setAlignmentX(CENTER_ALIGNMENT);
		innerPcPanel.add(assignSPC);

		pcPanel.add(innerPcPanel);
		setViewportView(pcPanel);
	}

	public void updateTables() {
		ArrayList<Paper> allPapers = (ArrayList<Paper>) myConference.getPapersBySpc(myConference.getCurrentUser().getID());
		ArrayList<Paper> unassignedPapers = new ArrayList<Paper>();
		ArrayList<Paper> assignedPapers = new ArrayList<Paper>();
		
		for (Paper p : allPapers) {
			if (p.getReviewerList().size() != 3) {
				unassignedPapers.add(p);
			} else {
				assignedPapers.add(p);
			}
		}
		
		
		myUnassignedTable.setModel(new UnassignedTableModel(unassignedPapers));	
		myAssignedTable.setModel(new AssignedTableModel(assignedPapers));
	}


	private class AssignDialog extends JDialog {

		private ArrayList<User> myUsers;

		private Paper myPaper;

		public AssignDialog(final List<User> theUsers, final Paper thePaper) {
			super();
			setTitle("Assign a subprogram chair");

			myUsers = (ArrayList<User>) theUsers;
			myPaper = thePaper;

			initDialog();

			//setSize(new Dimension(175, 300));
			pack();
			setLocationRelativeTo(null);
			setResizable(false);
			setVisible(true);
		}

		private void initDialog() {
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

			final User[] nameArray = myUsers.toArray(new User[0]);

			final JComboBox<User> list = new JComboBox<User>(nameArray);
			final JComboBox<User> list2 = new JComboBox<User>(nameArray);
			final JComboBox<User> list3 = new JComboBox<User>(nameArray);
			
			JPanel rev1 = new JPanel();
			rev1.add(new JLabel("Reviewer 1:"));
			rev1.add(list);
			panel.add(rev1);
			
			JPanel rev2 = new JPanel();
			rev2.add(new JLabel("Reviewer 2:"));
			rev2.add(list2);
			panel.add(rev2);
			
			JPanel rev3 = new JPanel();
			rev3.add(new JLabel("Reviewer 3:"));
			rev3.add(list3);
			panel.add(rev3);
			
			add(new JLabel("Select a Reviewer to be assigned:", SwingConstants.CENTER), BorderLayout.NORTH);
			add(panel, BorderLayout.CENTER);

			JPanel buttonPanel = new JPanel();
			JButton set = new JButton("Assign to Paper");
			set.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to "
							+ "assign reviewers:\n\n" + nameArray[list.getSelectedIndex()] 
							+ "\n" + nameArray[list2.getSelectedIndex()] + "\n" 
							+ nameArray[list3.getSelectedIndex()] + "\n\nto Paper " 
							+ myPaper.getTitle(), "Assign To Paper", 
							JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION){
						if (nameArray[list.getSelectedIndex()].getID() !=  nameArray[list2.getSelectedIndex()].getID() 
								&& nameArray[list.getSelectedIndex()].getID() !=  nameArray[list3.getSelectedIndex()].getID() 
								&& nameArray[list2.getSelectedIndex()].getID() !=  nameArray[list3.getSelectedIndex()].getID()) {
							myConference.assignReviewerToPaper(nameArray[list.getSelectedIndex()].getID(), myPaper.getId());
							myConference.assignReviewerToPaper(nameArray[list2.getSelectedIndex()].getID(), myPaper.getId());
							myConference.assignReviewerToPaper(nameArray[list3.getSelectedIndex()].getID(), myPaper.getId());
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Cannot assign the same reviewer twice to a paper. Try again.");
						}
						
					}
				}
			});
			buttonPanel.add(set);
			buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));

			JButton cancel = new JButton("Cancel");
			cancel.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			buttonPanel.add(cancel);

			add(buttonPanel, BorderLayout.SOUTH);
		}
	}

	private class ReviewDialog extends JDialog {

		private ArrayList<User> myUsers;

		public ReviewDialog(final List<User> theUsers) {
			super();
			setTitle("Assign a Reviewer");

			myUsers = (ArrayList<User>) theUsers;

			initDialog();

			//setSize(new Dimension(175, 300));
			pack();
			setLocationRelativeTo(null);
			setResizable(false);
			setVisible(true);
		}

		private void initDialog() {
			JPanel panel = new JPanel();

			final User[] nameArray = myUsers.toArray(new User[0]);

			final JComboBox<User> list = new JComboBox<User>(nameArray);
			panel.add(list);
			add(new JLabel("Select a user to be set to a reviewer:", SwingConstants.CENTER), BorderLayout.NORTH);
			add(panel, BorderLayout.CENTER);

			JPanel buttonPanel = new JPanel();
			JButton set = new JButton("Change Role");
			set.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to "
							+ "change " + nameArray[list.getSelectedIndex()] + "'s role to "
							+ "reviwer?", "Change User's Role", 
							JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION){
						myConference.changeUserRole(nameArray[list.getSelectedIndex()].getID(), 4);
						dispose();
					}
				}
			});
			buttonPanel.add(set);
			buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));

			JButton cancel = new JButton("Cancel");
			cancel.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			buttonPanel.add(cancel);

			add(buttonPanel, BorderLayout.SOUTH);
		}
	}

	/**
	 * Inner-class for a new TableModel. Contains the information of the papers and displays
	 * it accordingly in the appropriate JTable.
	 * 
	 * @author Erik Tedder
	 *
	 */
	private class UnassignedTableModel extends AbstractTableModel {

		private String[] columnNames = {"ID", "Title", "Author Name", "Reviewer 1", 
										"Reviewer 2", "Reviewer 3"};
		private ArrayList<Paper> myPaperList;

		public UnassignedTableModel(final List<Paper> arrayList) {
			myPaperList = (ArrayList<Paper>) arrayList;
		}

		public int getRowCount() {
			return myPaperList.size();
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public String getColumnName(int column) {
			return columnNames[column];
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			Object ret = null;
			List<Integer> reviewers = myPaperList.get(rowIndex).getReviewerList();

			switch (columnIndex) {
			case 0:
				ret = (Object) myPaperList.get(rowIndex).getId();
				break;
			case 1:
				ret = (Object) myPaperList.get(rowIndex).getTitle();
				break;
			case 2:
				User author = myConference.getUser(myPaperList.get(rowIndex).getAuthorID());
				ret = (Object) author.getFirstName() + " " + author.getLastName();
				break;	
			case 3:
				if (reviewers.size() > 0 && reviewers.get(0) != 0) {
					User rev1 = myConference.getUser(reviewers.get(0));
					ret = (Object) rev1.getFirstName() + " " + rev1.getLastName();
				} else {
					ret = (Object) "N/A";
				}
				break;
			case 4:
				if (reviewers.size() > 1) {
					User rev2 = myConference.getUser(reviewers.get(1));
					ret = (Object) rev2.getFirstName() + " " + rev2.getLastName();
				} else {
					ret = (Object) "N/A";
				}
				break;
			case 5:
				if (reviewers.size() > 2) {
					User rev3 = myConference.getUser(reviewers.get(2));
					ret = (Object) rev3.getFirstName() + " " + rev3.getLastName();
				} else {
					ret = (Object) "N/A";
				}
				break;
			}

			return ret;
		}

	}
	
	/**
	 * Inner-class for a new TableModel. Contains the information of the papers and displays
	 * it accordingly in the appropriate JTable.
	 * 
	 * @author Erik Tedder
	 *
	 */
	private class AssignedTableModel extends AbstractTableModel {

		private String[] columnNames = {"ID", "Title", "Author Name", "Review 1", 
										"Review 2", "Review 3"};
		private ArrayList<Paper> myPaperList;

		public AssignedTableModel(final ArrayList<Paper> arrayList) {
			myPaperList = (ArrayList<Paper>) arrayList;
		}

		public int getRowCount() {
			return myPaperList.size();
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public String getColumnName(int column) {
			return columnNames[column];
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			Object ret = null;
			List<Review> reviews = myPaperList.get(rowIndex).getRev();
			List<Integer> reviewers = myPaperList.get(rowIndex).getReviewerList();

			switch (columnIndex) {
			case 0:
				ret = (Object) myPaperList.get(rowIndex).getId();
				break;
			case 1:
				ret = (Object) myPaperList.get(rowIndex).getTitle();
				break;
			case 2:
				User author = myConference.getUser(myPaperList.get(rowIndex).getAuthorID());
				ret = (Object) author.getFirstName() + " " + author.getLastName();
				break;	
			case 3:
				if (reviews.size() > 0 && reviews.get(0).getScore() != 0) {
					ret = (Object) reviews.get(0).getScore();
				} else {
					User rev = myConference.getUser(reviewers.get(0));
					ret = (Object) rev.getFirstName() + " " + rev.getLastName();
				}
				break;
			case 4:
				if (reviews.size() > 1 && reviews.get(1).getScore() != 0) {
					ret = (Object) reviews.get(1).getScore();
				} else {
					User rev = myConference.getUser(reviewers.get(1));
					ret = (Object) rev.getFirstName() + " " + rev.getLastName();
				}
				break;
			case 5:
				if (reviews.size() > 2 && reviews.get(2).getScore() != 0) {
					ret = (Object) reviews.get(2).getScore();
				} else {
					User rev = myConference.getUser(reviewers.get(2));
					ret = (Object) rev.getFirstName() + " " + rev.getLastName();
				}
				break;
			}

			return ret;
		}

	}


}
