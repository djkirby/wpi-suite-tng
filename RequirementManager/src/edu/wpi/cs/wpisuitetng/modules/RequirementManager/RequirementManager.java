package edu.wpi.cs.wpisuitetng.modules.RequirementManager;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule;
import edu.wpi.cs.wpisuitetng.janeway.modules.JanewayTabModel;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.ToolbarView;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.ViewEventController;


public class RequirementManager implements IJanewayModule {

	private List<JanewayTabModel> tabs;
	
	/** Constructor for Requirement Manager. Creates a main view that contains a
	 *  toolbar on the top for each sub-tab. 
	 * 
	 */
	public RequirementManager() {
		tabs = new ArrayList<JanewayTabModel>();

		ToolbarView toolBar = new ToolbarView();
		MainView mainPanel = new MainView();
		ViewEventController.getInstance().setMainView(mainPanel);
		ViewEventController.getInstance().setToolBar(toolBar);

		// Create a tab model that contains the toolbar panel and the main content panel
		JanewayTabModel tab1 = new JanewayTabModel(getName(), new ImageIcon(), toolBar, mainPanel);

		// Add the tab to the list of tabs owned by this module
		tabs.add(tab1);
	}
	
	/**
	 * Sets tab name in module
	 * 
	 * @return "Requirement Manager"	The name of the tab
	 */
	@Override
	public String getName() {
		return "Requirement Manager";
	}

	/**
	 * Allows the module to have multiple tabs
	 * 
	 * @return tabs	Returns the list of the tabs the module has
	 */
	@Override
	public List<JanewayTabModel> getTabs() {
		return tabs;
	}

}
