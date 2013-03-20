 package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.overview.OverviewPanel;

public class MainView extends JTabbedPane {

	/**
	 * Creates overview tab
	 */
	public MainView() {
		OverviewPanel overview = new OverviewPanel();
		this.addTab("Overview", overview);
	}

	/**
	 * Overrides insertTab function to add the closable tab element.
	 * 
	 * @param title	Title of the tab
	 * @param icon	Icon at front of tab
	 * @param component	Fills the area underneath the tab
	 * @param tip	Tool tip when user hovers over tab with mouse
	 * @param index	 Position for where to add the tab
	 */
	@Override
	public void insertTab(String title, Icon icon, Component component,
			String tip, int index) {
		super.insertTab(title, icon, component, tip, index);
		//don't add close button to the overview panel
		if (!(component instanceof OverviewPanel)) {
			setTabComponentAt(index, new ClosableTabComponent(this));
		}
	}

}
