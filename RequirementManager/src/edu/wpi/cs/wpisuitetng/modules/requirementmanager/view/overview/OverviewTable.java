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

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.GetRequirementsController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.iterationcontroller.GetIterationController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

public class OverviewTable extends JTable
{
	private DefaultTableModel tableModel = null;
	private boolean initialized;
	/**
	 * Sets initial table view
	 * 
	 * @param data	Initial data to fill OverviewTable
	 * @param columnNames	Column headers of OverviewTable
	 */
	public OverviewTable(Object[][] data, String[] columnNames)
	{
		this.tableModel = new DefaultTableModel(columnNames, 0);
		this.setModel(tableModel);

		this.getTableHeader().setReorderingAllowed(false);
		this.setAutoCreateRowSorter(true);
		setFillsViewportHeight(true);

		ViewEventController.getInstance().setOverviewTable(this);
		initialized = false;

		/* Create double-click event listener */
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				
				if(getRowCount() > 0)
				{
					int mouseY = e.getY();
					Rectangle lastRow = getCellRect(getRowCount() - 1, 0, true);
					int lastRowY = lastRow.y + lastRow.height;

					if(mouseY > lastRowY) 
					{
						getSelectionModel().clearSelection();
						repaint();
					}
				}
				
				if (e.getClickCount() == 2)
				{
					ViewEventController.getInstance().editSelectedRequirement();
				}
			}
		});
	}
	
	/**
	 * updates OverviewTable with the contents of the requirement model
	 * 
	 */
	public void refresh() {
		tableModel.setRowCount(0); //clear the table
		
		List<Requirement> requirements = RequirementModel.getInstance().getRequirements();
		for (int i = 0; i < requirements.size(); i++) {
			Requirement req = requirements.get(i);
			if (!req.isDeleted()) {
				tableModel.addRow(new Object[]{ req.getId(), 
												req,
												req.getRelease(),
												req.getIteration(),
												req.getType(),
												req.getStatus(),
												req.getPriority(),
												req.getEstimate()
												 });
			}
		}
			
	}
	
	/**
	 * Overrides the isCellEditable method to ensure no cells are editable.
	 * 
	 * @param row	row of OverviewTable cell is located
	 * @param col	column of OverviewTable cell is located
	 */
	@Override
	public boolean isCellEditable(int row, int col)
	{
		return false;
	}
	
	
	/**
	 * Overrides the paintComponent method to retrieve the requirements on the first painting.
	 * 
	 * @param g	The component object to paint
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		if(!initialized)
		{
			try 
			{
				GetRequirementsController.getInstance().retrieveRequirements();
				GetIterationController.getInstance().retrieveIterations();
				initialized = true;
			}
			catch (Exception e)
			{

			}
		}

		super.paintComponent(g);
	}
	
	/**
	 * Overrides the table cell renderer to set the background of deleted requirements to grey.
	 * 
	 * @param renderer	Prepares the table renderer
	 * @param row	Determining row to be edited
	 * @param col	Determining column to be edited
	 */
	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int col)
	{
		JLabel renderLabel = (JLabel)super.prepareRenderer(renderer, row, col);
		
		boolean isSelected = false;
		for(int i = 0; i < this.getSelectedRows().length; i++)
		{
			if(row == this.getSelectedRows()[i]) {
				isSelected = true;
				break;
			}
		}
		
		Requirement currentRequirement = (Requirement)tableModel.getValueAt(row, 1);
		
		if(!isSelected)
		{
			if(currentRequirement.getStatus() == RequirementStatus.DELETED)
			{
				renderLabel.setBackground(Color.LIGHT_GRAY);
			}
			else
			{
				renderLabel.setBackground(Color.WHITE);
			}
		}
		
		return renderLabel;
	}
}