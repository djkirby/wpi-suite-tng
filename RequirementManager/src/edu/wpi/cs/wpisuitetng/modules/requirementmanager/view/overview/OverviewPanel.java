/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

public class OverviewPanel extends JPanel {
	
	/**
	 * Sets up directory table of requirements in system
	 */
	public OverviewPanel()
	{
		SpringLayout overviewLayout = new SpringLayout();
		this.setLayout(overviewLayout);

		OverviewFilterPanel filterPanel = new OverviewFilterPanel();
		
		String[] columnNames = {"ID", "Name", "Release #", "Iteration", "Type", "Status", "Priority", "Estimate"};
				
		Object[][] data = {};
		
		OverviewTable table = new OverviewTable(data, columnNames);
		
		JScrollPane tablePanel = new JScrollPane(table);
		
		// Constrain the filtersPanel
		overviewLayout.putConstraint(SpringLayout.NORTH, filterPanel, 0,SpringLayout.NORTH, this);
		overviewLayout.putConstraint(SpringLayout.WEST, filterPanel, 0, SpringLayout.WEST, this);
		overviewLayout.putConstraint(SpringLayout.SOUTH, filterPanel, 0, SpringLayout.SOUTH, this);
		overviewLayout.putConstraint(SpringLayout.EAST, filterPanel, 200,SpringLayout.WEST, filterPanel);

		// Constrain the table panel
		overviewLayout.putConstraint(SpringLayout.NORTH, tablePanel, 0, SpringLayout.NORTH, this);
		overviewLayout.putConstraint(SpringLayout.WEST, tablePanel, 0, SpringLayout.EAST, filterPanel);
		overviewLayout.putConstraint(SpringLayout.EAST, tablePanel, 0, SpringLayout.EAST, this);
		overviewLayout.putConstraint(SpringLayout.SOUTH, tablePanel, 0, SpringLayout.SOUTH, this);
		
		this.add(filterPanel);
		this.add(tablePanel);
	}
}