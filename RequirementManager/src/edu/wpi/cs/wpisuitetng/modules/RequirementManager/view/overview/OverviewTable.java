package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.overview;

import javax.swing.JTable;

public class OverviewTable extends JTable {
	
	/**
	 * Creates a blank table in the overview tab
	 * 
	 * @param data	Information for each table column
	 * @param columnNames	Setting the name for each column
	 */
	public OverviewTable(Object[][] data, String[] columnNames)
	{
		super(data, columnNames);
		
		setFillsViewportHeight(true);
	}
}
