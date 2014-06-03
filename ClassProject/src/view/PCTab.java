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
		
		initClass();
	}
	
	private void initClass() {
		JPanel pcPanel = new JPanel();
		JLabel completedLabel = new JLabel();
		JScrollPane pcScrollPane = new JScrollPane();
		
		setBackground(new java.awt.Color(255, 255, 255));
        setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pcPanel.setBackground(new java.awt.Color(255, 255, 255));

        myCompleteTable.setModel(new TableModel(myConference.getUserByRole(0)));
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

	/**
	 * Inner-class for a new TableModel. Contains the information of the papers and displays
	 * it accordingly in the appropriate JTable.
	 * 
	 * @author Erik Tedder
	 *
	 */
	private class TableModel extends AbstractTableModel {

		private String[] columnNames = {"ID", "First Name", "Last Name", "Email"};
		private ArrayList<User> myUserList;

		public TableModel(final List<User> theUsers) {
			myUserList = (ArrayList<User>) theUsers;
		}

		public int getRowCount() {
			return myUserList.size();
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
				ret = (Object) myUserList.get(rowIndex).getID();
				break;
			case 1:
				ret = (Object) myUserList.get(rowIndex).getFirstName();
				break;
			case 2:
				ret = (Object) myUserList.get(rowIndex).getLastName();
				break;
			case 3:
				ret = (Object) myUserList.get(rowIndex).getEmail();
				break;				
			}

			return ret;
		}

	}

}
