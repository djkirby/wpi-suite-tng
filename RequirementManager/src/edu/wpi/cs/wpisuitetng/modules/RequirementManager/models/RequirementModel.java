package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller.AddRequirementController;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.ViewEventController;


/**List of Requirements being pulled from the server
 * 
 * @author Gabriel McCormick
 *
 */
@SuppressWarnings("serial")
public class RequirementModel extends AbstractListModel{
	
	/**
	 * The list in which all the requirements for a single project are contained
	 */
	private List<Requirement> requirements;
	private int nextID; // the next available ID number for the requirements that are added.
	
	//the static object to allow the requirement model to be 
	private static RequirementModel instance; 

	/**
	 * Constructs an empty list of requirements for the project
	 */
	private RequirementModel (){
		requirements = new ArrayList<Requirement>();
		nextID = 0;
	}
	
	/**
	 * Returns the instance of the requirement model singleton.
	 */
	public static RequirementModel getInstance()
	{
		if(instance == null)
		{
			instance = new RequirementModel();
		}
		
		return instance;
	}
	
	/**
	 * Adds a single requirement to the requirements of the project
	 * 
	 * @oaram newReq The requirement to be added to the list of requirements in the project
	 */
	public void addRequirement(Requirement newReq){
		// add the requirement
		requirements.add(newReq);
		AddRequirementController.getInstance().addRequirement(newReq);
		ViewEventController.getInstance().refreshTable();
	}
	
	/**
	 * Removes the requirement with the given ID
	 * 
	 * @param removeId The ID number of the requirement to be removed from the list of requirements in the project
	 */
	public void removeRequirement(int removeId){
		// iterate through list of requirements until id of project is found
		for (int i=0; i < this.requirements.size(); i++){
			if (requirements.get(i).getId() == removeId){
				// remove the id
				requirements.remove(i);
				break;
			}
		}
		
		ViewEventController.getInstance().refreshTable();
	}

	/**
	 * Provides the number of elements in the list of requirements for the project. This
	 * function is called internally by the JList in NewRequirementPanel. Returns elements
	 * in reverse order, so the newest requirement is returned first.
	 * 
	 * @return the number of requirements in the project
	 */
	public int getSize() {
		return requirements.size();
	}
	
	/**
	 * 
	 * Provides the next ID number that should be used for a new requirement that is created.
	 * 
	 * @return the next open id number
	 */
	public int getNextID()
	{
		
		return this.nextID++;
	}

	/**
	 * This function takes an index and finds the requirement in the list of requirements
	 * for the project. Used internally by the JList in NewRequirementModel.
	 * 
	 * @param index The index of the requirement to be returned
	 * @return the requirement associated with the provided index
	 */
	public Requirement getElementAt(int index) {
		return requirements.get(requirements.size() - 1 - index);
	}

	/**
	 * Removes all requirements from this model
	 * 
	 * NOTE: One cannot simply construct a new instance of
	 * the model, because other classes in this module have
	 * references to it. Hence, we manually remove each requirement
	 * from the model.
	 */
	public void emptyModel() {
		int oldSize = getSize();
		Iterator<Requirement> iterator = requirements.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
		this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
		ViewEventController.getInstance().refreshTable();
	}
	
	/**
	 * Adds the given array of requirements to the list
	 * 
	 * @param requirements the array of requirements to add
	 */
	public void addRequirements(Requirement[] requirements) {
		for (int i = 0; i < requirements.length; i++) {
			this.requirements.add(requirements[i]);
			if(requirements[i].getId() > nextID) nextID = requirements[i].getId() + 1;
		}
		this.fireIntervalAdded(this, 0, Math.max(getSize() - 1, 0));
		ViewEventController.getInstance().refreshTable();
	}

	public List<Requirement> getRequirements() {
		return requirements;
	}
	
}
