package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Conference;

/**
 * The JPanel class which displays the information and fields necessary to submit a paper to
 * the conference.
 * 
 * @author Erik Tedder
 * @date 5/25/2014
 */
@SuppressWarnings("serial")
public class SubmitTab extends JPanel {
	
	private static final String PATH = "src/files/";
	
	/**
	 * The current conference.
	 */
	private Conference myConference;
	
	/**
	 * JLabel that will contain the name of the file selected.
	 */
	private JLabel myFileLabel;
	
	/**
	 * TextField for entering in the paper's title.
	 */
	private JTextField myPaperTitle;
	
	/**
	 * The text area that will contain the abstract information of the paper.
	 */
	private JTextArea myAbstractArea;
	
	/**
	 * JFileChooser for selecting the paper to be submitted.
	 */
	private JFileChooser myFileChooser;
	
	/**
	 * The paper file a user uploads to the conference.
	 */
	private File myFile;
	
	/**
	 * Constructor of a new submit tab.
	 * 
	 * @param theConference The current conference.
	 */
	public SubmitTab(final Conference theConference) {
		myConference = theConference;
		
		myFileLabel = new JLabel();
		myPaperTitle = new JTextField();
		myAbstractArea = new JTextArea();
		myFileChooser = new JFileChooser(".");
		//the filters for what type of files are acceptable
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "txt, doc, docx files", "txt", "docx", "doc");
	    myFileChooser.setFileFilter(filter);
	    
		initPanel();
	}
	
	/**
	 * Method which initializes the Panel's internal objects.
	 */
	private void initPanel() {
		myAbstractArea.setLineWrap(true);
        myAbstractArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JLabel submitLabel = new JLabel();
		JLabel titleLabel = new JLabel("Title");
		JLabel abstractLabel = new JLabel("Abstract");
		
		JButton selectButton = new JButton("Select Paper");
		//Set action for select button
		selectButton.addActionListener(new ActionListener() {			
			public void actionPerformed(final ActionEvent e) {
				try {
					fileSelectAction();
				} catch (final IOException error) {
					JOptionPane.showMessageDialog(null, error.getMessage());
				}
			}
		});
		JButton submitButton = new JButton("Submit Paper");
		//Sets action for the submit button. Calls the SubmitFileAction method to save the
		//given file to a designated path.
		submitButton.addActionListener(new ActionListener() {			
			public void actionPerformed(final ActionEvent e) {
				if (myFile != null &&myFile.exists()) {					
					try {
						submitFileAction();
					} catch (final IOException error) {
						JOptionPane.showMessageDialog(null, error.getMessage());
					}
				} else {
					JOptionPane.showMessageDialog(null, "Invalid or no file selected. Please "
							+ "try again.");
				}
			}
		});
		
		setBackground(new java.awt.Color(255, 255, 255));

        submitLabel.setFont(new java.awt.Font("Dialog", 1, 18)); 
        submitLabel.setText("Submit a paper to be reviewed");
        
        //Layout generated by the NetBeans IDE
        GroupLayout submitTabLayout = new GroupLayout(this);
        setLayout(submitTabLayout);
        submitTabLayout.setHorizontalGroup(
            submitTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(submitTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(submitTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(submitLabel, GroupLayout.PREFERRED_SIZE, 
                    		GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(submitTabLayout.createSequentialGroup()
                        .addGroup(submitTabLayout.createParallelGroup(
                        		GroupLayout.Alignment.LEADING)
                            .addGroup(submitTabLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 
                                		GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addComponent(abstractLabel, GroupLayout.PREFERRED_SIZE,
                            		GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(submitTabLayout.createParallelGroup(
                        		GroupLayout.Alignment.LEADING)
                            .addGroup(submitTabLayout.createSequentialGroup()
                                .addComponent(selectButton)
                                .addPreferredGap(
                                		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(myFileLabel))
                            .addGroup(submitTabLayout.createParallelGroup(
                            		GroupLayout.Alignment.LEADING, false)
                                .addComponent(myPaperTitle, GroupLayout.DEFAULT_SIZE, 
                                		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(myAbstractArea, GroupLayout.DEFAULT_SIZE, 438, 
                                		Short.MAX_VALUE))
                            .addComponent(submitButton))))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        submitTabLayout.setVerticalGroup(
            submitTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(submitTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(submitLabel, GroupLayout.PREFERRED_SIZE, 
                		GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(submitTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 
                    		GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(myPaperTitle, GroupLayout.PREFERRED_SIZE, 
                    		GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(submitTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(abstractLabel, GroupLayout.PREFERRED_SIZE, 
                    		GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(myAbstractArea, GroupLayout.PREFERRED_SIZE, 114, 
                    		GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(submitTabLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(selectButton)
                    .addComponent(myFileLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submitButton)
                .addContainerGap(41, Short.MAX_VALUE))
        );
	}

	/**
	 * Action for select button. Prompts the user to select a file to be submitted. Displays
	 * name to GUI to allow user to identify what they selected.
	 * 
	 * @throws IOException Input/Output exception for bad files.
	 */
	private void fileSelectAction() throws IOException {
		//Display the Open dialog window for myFileChooser
        final int result = myFileChooser.showOpenDialog(null);
        
        //Check if image was selected
        if (result == JFileChooser.APPROVE_OPTION) { 
            myFileLabel.setText(myFileChooser.getSelectedFile().getName());
            myFile = myFileChooser.getSelectedFile();
        }
	}
	
	/**
	 * Method which takes a given file and saves it to the conference's system files.
	 * 
	 * (Code originates from http://www.journaldev.com/861/4-ways-to-copy-file-in-java).
	 * 
	 * @throws IOException
	 */
	private void submitFileAction() throws IOException {
		InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(myFile);
	        os = new FileOutputStream(new File(PATH + myFile.getName()));
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {	        
	    	is.close();
	    	
	    	if (os == null) {
	    		System.err.println("uh oh");
	    	}
	    	os.close();			
	    }
	}
}
