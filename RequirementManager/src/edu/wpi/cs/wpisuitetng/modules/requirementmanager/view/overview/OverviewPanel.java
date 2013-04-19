/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableCellRenderer;

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
		DefaultTableCellRenderer centeredRenderer = new DefaultTableCellRenderer();
		centeredRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		table.getColumnModel().getColumn(0).setMaxWidth(60);
		
		table.getColumnModel().getColumn(4).setMaxWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setMinWidth(80);
		table.getColumnModel().getColumn(4).setCellRenderer(centeredRenderer);
		
		table.getColumnModel().getColumn(5).setMaxWidth(120);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setMinWidth(80);
		table.getColumnModel().getColumn(5).setCellRenderer(centeredRenderer);
		
		table.getColumnModel().getColumn(6).setMaxWidth(120);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setMinWidth(80);
		table.getColumnModel().getColumn(6).setCellRenderer(centeredRenderer);
		
		
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
