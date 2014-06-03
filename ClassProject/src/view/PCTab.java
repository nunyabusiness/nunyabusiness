package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;

import model.Conference;
import model.Paper;
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
	private JTable myCompleteTable;
	
	public PCTab(final Conference theConference) {
		super();
		
		myConference = theConference;
		myCompleteTable = new JTable();
		
		myCompleteTable.setDragEnabled(false);
		myCompleteTable.getTableHeader().setReorderingAllowed(false);
		myCompleteTable.getTableHeader().setResizingAllowed(false);
		
		initClass();
	}
	
	private void initClass() {
		JPanel pcPanel = new JPanel();
		JPanel innerPcPanel = new JPanel();
		innerPcPanel.setBackground(new java.awt.Color(255, 255, 255));
		innerPcPanel.setLayout(new BoxLayout(innerPcPanel, BoxLayout.Y_AXIS));
		
		JLabel completedLabel = new JLabel("All Papers");
		completedLabel.setAlignmentX(CENTER_ALIGNMENT);
		JScrollPane pcScrollPane = new JScrollPane();
		pcScrollPane.setPreferredSize(new Dimension(600, 125));
		
		setBackground(new java.awt.Color(255, 255, 255));
        setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pcPanel.setBackground(new java.awt.Color(255, 255, 255));

        myCompleteTable.setModel(new TableModel(myConference.getAllPapers()));
        myCompleteTable.setShowHorizontalLines(false);
        myCompleteTable.setShowVerticalLines(false);
        pcScrollPane.setViewportView(myCompleteTable);

        completedLabel.setToolTipText("From this table you will be able to look over any "
        		+ "papers that have already completed their review process. ");

        //Testing display of tables.
        final JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("View");
        deleteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					JOptionPane.showMessageDialog(null, myCompleteTable.getValueAt(myCompleteTable.getSelectedRow(), 0) 
							+ " " + myCompleteTable.getValueAt(myCompleteTable.getSelectedRow(), 1) 
							+ " " + myCompleteTable.getValueAt(myCompleteTable.getSelectedRow(), 2) 
							+ " " + myCompleteTable.getValueAt(myCompleteTable.getSelectedRow(), 3));
				} catch (ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "Please select a user first");
				}
            }
        });
        popupMenu.add(deleteItem);
        myCompleteTable.setComponentPopupMenu(popupMenu);
        
        innerPcPanel.add(completedLabel);
        innerPcPanel.add(pcScrollPane);
        innerPcPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        JButton assignSPC = new JButton("New Subprogram Chair");
        assignSPC.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				new SPCDialog(myConference.getUserByRole(0));
			}
		});
        assignSPC.setAlignmentX(CENTER_ALIGNMENT);
        innerPcPanel.add(assignSPC);

        pcPanel.add(innerPcPanel);
        setViewportView(pcPanel);
	}
	
	public void updateTables() {
		myCompleteTable.setModel(new TableModel(myConference.getAllPapers()));
	}
	
	private class SPCDialog extends JDialog {
		
		private ArrayList<User> myUsers;
		
		public SPCDialog(final List<User> theUsers) {
			super();
			setTitle("Assign a subprogram chair");
			
			myUsers = (ArrayList<User>) theUsers;
			
			initDialog();
			
			setLocationRelativeTo(null);
			//setSize(new Dimension(175, 300));
			pack();
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
					nameArray[list.getSelectedIndex()].setRole(2);
					dispose();
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
	private class TableModel extends AbstractTableModel {

		private String[] columnNames = {"Title", "Author Name", "SPC", "Reviewer 1", 
										"Reviewer 2", "Reviewer 3"};
		private ArrayList<Paper> myPaperList;

		public TableModel(final ArrayList<Paper> arrayList) {
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
				ret = (Object) myPaperList.get(rowIndex).getTitle();
				break;
			case 1:
				User author = myConference.getUser(myPaperList.get(rowIndex).getAuthorID());
				ret = (Object) author.getFirstName() + " " + author.getLastName();
				break;
			case 2:				
				if (myPaperList.get(rowIndex).getSubchairID() > 0) {
					User spc = myConference.getUser(myPaperList.get(rowIndex).getSubchairID());
					ret = (Object) spc.getFirstName() + " " + spc.getLastName();
				} else {
					ret = (Object) "N/A";
				}						
				break;		
			case 3:
				if (reviewers.size() > 0) {
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

}
