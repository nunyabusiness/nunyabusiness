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

import model.BusinessRuleException;
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
public class PCTab extends JScrollPane {
	
	private Conference myConference;
	private JTable assignmentTable;
	private JTable processTable;
	
	public PCTab(final Conference theConference) {
		super();
		
		myConference = theConference;
		assignmentTable = new JTable();
		processTable = new JTable();
		
		assignmentTable.setDragEnabled(false);
		assignmentTable.getTableHeader().setReorderingAllowed(false);
		assignmentTable.getTableHeader().setResizingAllowed(false);
		
		processTable.setDragEnabled(false);
		processTable.getTableHeader().setReorderingAllowed(false);
		processTable.getTableHeader().setResizingAllowed(false);
		
		initClass();
	}
	
	private void initClass() {
		JPanel pcPanel = new JPanel();
		JPanel innerPcPanel = new JPanel();
		innerPcPanel.setBackground(new java.awt.Color(255, 255, 255));
		innerPcPanel.setLayout(new BoxLayout(innerPcPanel, BoxLayout.Y_AXIS));
		
		JLabel completedLabel = new JLabel("Papers Needing Assignment");
		completedLabel.setAlignmentX(CENTER_ALIGNMENT);
		JScrollPane pcScrollPane = new JScrollPane();
		pcScrollPane.setPreferredSize(new Dimension(600, 125));
		
		setBackground(new java.awt.Color(255, 255, 255));
        setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pcPanel.setBackground(new java.awt.Color(255, 255, 255));

        assignmentTable.setModel(new UnassignedTableModel(myConference.getAllPapers()));
        assignmentTable.setShowHorizontalLines(false);
        assignmentTable.setShowVerticalLines(false);
        pcScrollPane.setViewportView(assignmentTable);

        completedLabel.setToolTipText("From this table you will be able to look over any "
        		+ "papers that need to be assigned to a subprogram chair.");
        
        assignmentTable.addMouseListener(new MouseAdapter() {			
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Integer paperID = (Integer) assignmentTable.getValueAt(assignmentTable.getSelectedRow(), 0);
					
					new AssignDialog(myConference.getUserByRole(2),	myConference.getPaper(paperID));
				}
			}
		});
        
        JLabel assignedLabel = new JLabel("Papers In Review");
        assignedLabel.setAlignmentX(CENTER_ALIGNMENT);
		JScrollPane pcScrollPane1 = new JScrollPane();
		pcScrollPane1.setPreferredSize(new Dimension(600, 125));
		
		processTable.setShowHorizontalLines(false);
		processTable.setShowVerticalLines(false);
        pcScrollPane1.setViewportView(processTable);

        assignedLabel.setToolTipText("From this table you will be able to look over any "
        		+ "papers in the review process.");
        
        processTable.addMouseListener(new MouseAdapter() {			
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					//do nothing for now
				}
			}
		});
        
        innerPcPanel.add(completedLabel);
        innerPcPanel.add(pcScrollPane);
        innerPcPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        innerPcPanel.add(assignedLabel);
        innerPcPanel.add(pcScrollPane1);
        innerPcPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        JButton assignSPC = new JButton("New Subprogram Chair");
        assignSPC.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				new SPCDialog(myConference.getUserByRole(4));
			}
		});
        assignSPC.setAlignmentX(CENTER_ALIGNMENT);
        innerPcPanel.add(assignSPC);

        pcPanel.add(innerPcPanel);
        setViewportView(pcPanel);
	}
	
	public void updateTables() {
		ArrayList<Paper> allPapers = myConference.getAllPapers();
		ArrayList<Paper> unassignedPapers = new ArrayList<Paper>();
		ArrayList<Paper> assignedPapers = new ArrayList<Paper>();
		
		for (Paper p : allPapers) {
			if (p.getSubchairID() != 0) {
				assignedPapers.add(p);
			} else {
				unassignedPapers.add(p);
			}
		}
		
		
		assignmentTable.setModel(new UnassignedTableModel(unassignedPapers));
		processTable.setModel(new AssignedTableModel(assignedPapers));
	}
	
	private class SPCDialog extends JDialog {
		
		private ArrayList<User> myUsers;
		
		public SPCDialog(final List<User> theUsers) {
			super();
			setTitle("Assign a subprogram chair");
			
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
			add(new JLabel("Select a user to be set to a subprogram chair:", SwingConstants.CENTER), BorderLayout.NORTH);
			add(panel, BorderLayout.CENTER);
			
			JPanel buttonPanel = new JPanel();
			JButton set = new JButton("Change Role");
			set.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to "
							+ "change " + nameArray[list.getSelectedIndex()] + "'s role to "
									+ "subprogram chair?", "Change User's Role", 
									JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION){
						myConference.changeUserRole(nameArray[list.getSelectedIndex()].getID(), 2);
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
			
			final User[] nameArray = myUsers.toArray(new User[0]);
			
			final JComboBox<User> list = new JComboBox<User>(nameArray);
			panel.add(list);
			add(new JLabel("Select a subprogram chair to be assigned:", SwingConstants.CENTER), BorderLayout.NORTH);
			add(panel, BorderLayout.CENTER);
			
			JPanel buttonPanel = new JPanel();
			JButton set = new JButton("Assign to Paper");
			set.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to "
							+ "assign " + nameArray[list.getSelectedIndex()] + " to Paper " + myPaper.getTitle(), "Assign To Paper", 
									JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION){
						try {
							myConference.assignSpc(nameArray[list.getSelectedIndex()].getID(), myPaper.getId());
						} catch (BusinessRuleException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
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

		private String[] columnNames = {"ID", "Title", "Author Name", "SPC"};
		private ArrayList<Paper> myPaperList;

		public UnassignedTableModel(final ArrayList<Paper> arrayList) {
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
				if (myPaperList.get(rowIndex).getSubchairID() > 0) {
					User spc = myConference.getUser(myPaperList.get(rowIndex).getSubchairID());
					ret = (Object) spc.getFirstName() + " " + spc.getLastName();
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

		private String[] columnNames = {"ID", "Title", "Author Name", "SPC", "Review 1", 
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
				if (myPaperList.get(rowIndex).getRec() != null 
				&& myPaperList.get(rowIndex).getRec().getState() != 0) {
					ret = (Object) myPaperList.get(rowIndex).getRec().getState();
				} else {
					ret = (Object) myConference.getUser(myPaperList.get(rowIndex).getSubchairID());
				}						
				break;		
			case 4:
				if (reviews.size() > 0 && reviews.get(0).getScore() != 0) {
					ret = (Object) reviews.get(0).getScore();
				} else {
					ret = (Object) "None";
				}
				break;
			case 5:
				if (reviews.size() > 1 && reviews.get(1).getScore() != 0) {
					ret = (Object) reviews.get(1).getScore();
				} else {
					ret = (Object) "None";
				}
				break;
			case 6:
				if (reviews.size() > 2 && reviews.get(2).getScore() != 0) {
					ret = (Object) reviews.get(2).getScore();
				} else {
					ret = (Object) "None";
				}
				break;
			}

			return ret;
		}

	}

}
