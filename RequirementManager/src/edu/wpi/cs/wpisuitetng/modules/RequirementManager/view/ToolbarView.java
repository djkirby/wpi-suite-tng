package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;

public class ToolbarView extends JPanel {

	/**
	 * Default Constructor for the ToolbarView. Is a constant size to above the tabs.
	 * Creates a button that contains the actionListener and creates the Requirements tab.
	 */
	public ToolbarView() {
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED)); // add a border so you can see the panel
		
		SpringLayout toolbarLayout = new SpringLayout();
		this.setLayout(toolbarLayout);
		
		JButton createButton = new JButton("Create Requirement");
		createButton.addActionListener(new ActionListener() {
			
			/**
			 * Tells the event controller to open the tab
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewEventController.getInstance().createRequirement();
				
			}
		});
		toolbarLayout.putConstraint(SpringLayout.NORTH, createButton, 25,SpringLayout.NORTH, this);
		toolbarLayout.putConstraint(SpringLayout.WEST, createButton, 50, SpringLayout.WEST, this);
		this.add(createButton);
	}
}
