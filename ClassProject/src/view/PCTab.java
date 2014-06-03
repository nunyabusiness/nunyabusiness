package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import model.Conference;
import model.Paper;
import model.User;

/**
 * JScrollPane which will act as the tab for the program chair view.
 * 
 * @author Erik Tedder
 * @date 6/2/2014
 */
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
		JLabel completedLabel = new JLabel();
		JScrollPane pcScrollPane = new JScrollPane();
		
		setBackground(new java.awt.Color(255, 255, 255));
        setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pcPanel.setBackground(new java.awt.Color(255, 255, 255));

        myCompleteTable.setModel(new TableModel(myConference.getAllPapers()));
        myCompleteTable.setShowHorizontalLines(false);
        myCompleteTable.setShowVerticalLines(false);
        pcScrollPane.setViewportView(myCompleteTable);

        completedLabel.setText("Completed Reviews");
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

        setViewportView(pcPanel);
	}
	
	public void updateTables() {
		myCompleteTable.setModel(new TableModel(myConference.getAllPapers()));
	}

	/**
	 * Inner-class for a new TableModel. Contains the information of the papers and displays
	 * it accordingly in the appropriate JTable.
	 * 
	 * @author Erik Tedder
	 *
	 */
	private class TableModel extends AbstractTableModel {

		private String[] columnNames = {"Title", "Author Name", "SPC", "Reviewer 1", "Reviewer 2", "Reviewer 3"};
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
