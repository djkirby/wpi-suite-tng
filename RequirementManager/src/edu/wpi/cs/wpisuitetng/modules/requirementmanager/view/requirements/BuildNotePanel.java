package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListIterator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.border.Border;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.UpdateRequirementController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.Note;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.NoteList;

public class BuildNotePanel extends JPanel{
	Requirement req;
	JTextArea noteMessage;
	/**
	 * Constructs a panel with a scrolling list of notes for the requirement, as
	 * well as the elements to add new notes
	 * 
	 * @return panel for displaying and creating notes
	 */
	public BuildNotePanel(Requirement requirementBeingEdited) 
	{
		req = requirementBeingEdited;
		// Buttons to be added to the bottom of the NotePanel
		JButton buttonAddNote = new JButton("Add Note");
		JButton buttonClear = new JButton("Clear");

		// Create text area for note to be added
		noteMessage = new JTextArea();
		noteMessage.setLineWrap(true); // If right of box is reached, goes down a
										// line
		noteMessage.setWrapStyleWord(true); // Doesn't chop off words
				
		// Error message label in case no note was included
		final JLabel errorMsg = new JLabel();

		// Layout manager for entire note panel
		GridBagLayout layout = new GridBagLayout();
		JPanel panel = new JPanel(layout);
		GridBagConstraints c = new GridBagConstraints();

		// Layout manager for panel that contains the buttons
		GridBagLayout bottomLayout = new GridBagLayout();
		JPanel bottomPanel = new JPanel(bottomLayout);
		GridBagConstraints bc = new GridBagConstraints();

		// Create new scroll pane for notes
		final JScrollPane scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// Always show scroll bar

		c.fill = GridBagConstraints.BOTH; // Fill grid cell with elements
		c.weightx = 1; // Fill horizontal space
		c.weighty = 1; // Fill all the vertical space
		panel.add(scroll, c);

		c.gridy = 1; // Row 1
		c.weighty = .2; // Fill 0% of vertical space
		panel.add(noteMessage,c);
				
		bc.anchor = GridBagConstraints.WEST; // Anchor buttons to west of bottom panel
		bottomPanel.add(buttonAddNote, bc); // Include "Add note" button to bottom panel
				
		bc.gridx = 1; // Column 1
		bottomPanel.add(buttonClear, bc); // Include "Clear" button to bottom
											// panel

		bc.gridx = 2; // Column 2
		bottomPanel.add(errorMsg, bc); // Add error message label to bottom
										// panel

		c.weighty = 0; // Do not stretch
		c.gridy = 2; // Row 2
		c.fill = GridBagConstraints.NONE; // Do not fill cell
		c.anchor = GridBagConstraints.WEST; // Anchor buttons to west of panel
		panel.add(bottomPanel, c); // Add buttons to the panel

		// Set scroll pane to display notes associated with edited requirement
		scroll.setViewportView(NotePanel.createList(requirementBeingEdited
				.getNotes()));

		// Listener for add note button
		buttonAddNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Display error message if there is no text in noteMessage
				if (noteMessage.getText().length() <= 0) {
					errorMsg.setText(" Error: Must add text to create note.");
				} else {

					String msg = noteMessage.getText(); // Get text from
														// noteMessage

					// Clear all text areas
					noteMessage.setText("");
					errorMsg.setText("");

					// Add note to requirement
					req.getNotes().add(msg);

					// Update panel to show new note
					scroll.setViewportView(NotePanel
							.createList(req.getNotes()));
							
					// Update database so requirement stores new note
					UpdateRequirementController.getInstance()
							.updateRequirement(req);
				}
			}
		});

		// Listener for the Clear button
		buttonClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Clear all text fields
				noteMessage.setText("");
				errorMsg.setText("");				}
		});
			
	}
	
	public String getMessage() {
		return noteMessage.getText();
	}
	
}
