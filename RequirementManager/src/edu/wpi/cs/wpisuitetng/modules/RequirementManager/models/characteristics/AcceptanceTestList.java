package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics;

import java.util.LinkedList;
import java.util.List;

/**Class for holding a list of Acceptance Tests for a Requirement
 * 
 * @author Gabriel McCormick
 *
 */
public class AcceptanceTestList {
	
	private List<AcceptanceTest> tests;
	
	public AcceptanceTestList (){
		this.tests = new LinkedList<AcceptanceTest>();
	}

	/**
	 * @return the tests
	 */
	public List<AcceptanceTest> getTests() {
		return tests;
	}

	/**
	 * @param tests the tests to set
	 */
	public void setTests(List<AcceptanceTest> tests) {
		this.tests = tests;
	}
	
	/**Adds the acceptance test to the list of acceptance tests
	 * 
	 * @param test The acceptance test to add to the list
	 */
	public void addAcceptanceTest(AcceptanceTest test){
		this.tests.add(test);
	}
	
	/**Removes the acceptance test with the given id form the lsit
	 * 
	 * @param id The id of the acceptance test to be removed
	 * @return True if the requirement is found and removed, false if it is not found.
	 */
	public boolean removeAcceptanceTest(int id){
		// iterate through the list looking for the requirement to remove
		for (int i=0; i < this.tests.size(); i++){
			if (tests.get(i).getId() == id){
				// remove the id
				tests.remove(i);
				return true;
			}
		}
		return false;
	}
}
