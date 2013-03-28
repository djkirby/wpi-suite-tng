package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics;

import java.util.LinkedList;
import java.util.List;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Requirement;

/**Class containing a list of requirements. 
 * To be used as the class to hold subrequirements in a requirement.
 * 
 * @author Gabriel McCormick
 *
 */
public class SubRequirements {
	
	private List<Requirement> subRequirements;
	
	public SubRequirements (){
		this.subRequirements = new LinkedList<Requirement>();
	}
	
	/**
	 * 
	 * @return the list of requirements
	 */
	public List<Requirement> getSubRequirements() {
		return subRequirements;
	}
	
	/**
	 * 
	 * @param subRequirements
	 */
	public void setSubRequirements(List<Requirement> subRequirements) {
		this.subRequirements = subRequirements;
	}
	
	/**Method to add a requirement to the list of sub-requirements
	 * 
	 * @param requirement Requirement to add
	 */
	public void addSubRequirement(Requirement subRequirement){
		this.subRequirements.add(subRequirement);
	}
	
	/** Method to remove a requirement to the list of sub-requirements
	 * 
	 * @param id The id of the requirement to be remove from the list of sub-requirements
	 * @return true if the requirement was found and removed, false if the requirement was not found
	 */
	public boolean removeSubRequirement (int id){
		// iterate through the list looking for the requirement to remove
		for (int i=0; i < this.subRequirements.size(); i++){
			if (subRequirements.get(i).getId() == id){
				// remove the id
				subRequirements.remove(i);
				return true;
			}
		}
		return false;
	}
}
