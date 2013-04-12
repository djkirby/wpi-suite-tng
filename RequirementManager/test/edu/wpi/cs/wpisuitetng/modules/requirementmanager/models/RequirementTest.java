/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.models;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.MockNetwork;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.GetRequirementsController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementPriority;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementType;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.Transaction;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.TransactionHistory;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.configuration.NetworkConfiguration;

/**
 * Tests the jSON conversion functions and all the getters and setters
 * 
 * @author Benjamin Senecal
 */
public class RequirementTest {

	GetRequirementsController controller;

	@Before
	public void setUp() throws Exception {
		Network.initNetwork(new MockNetwork());
		Network.getInstance().setDefaultNetworkConfiguration(
				new NetworkConfiguration("http://wpisuitetng"));
		IterationModel.getInstance().setBacklog(new Iteration(1, "Backlog"));
	}

	@Test
	public void jSONConversionTests() {
		Requirement object = new Requirement(4, "Test", "A test");

		assertEquals(object.getStatus(), RequirementStatus.NEW);
		assertEquals(object.getPriority(), RequirementPriority.BLANK);

		object.setRelease("1.1.01");
		object.setStatus(RequirementStatus.INPROGRESS, true);
		object.setPriority(RequirementPriority.MEDIUM, true);
		object.setEffort(10);
		object.setEstimate(1);

		Requirement origObject = object; // change here
		String jsonMessage = object.toJSON();
		Requirement newObject = Requirement.fromJson(jsonMessage); // change
																	// here...
		assertTrue(newObject instanceof Requirement);

		assertEquals(origObject.getId(), 4);
		assertEquals(origObject.getName(), "Test");
		assertEquals(origObject.getRelease(), "1.1.01");
		assertEquals(origObject.getStatus(), RequirementStatus.INPROGRESS);
		assertEquals(origObject.getPriority(), RequirementPriority.MEDIUM);
		assertEquals(origObject.getDescription(), "A test");
		assertEquals(origObject.getEstimate(), 1);
		assertEquals(origObject.getEffort(), 10);

		TransactionHistory history = origObject.getHistory();
		// History has to be created in GUI side
		// assertEquals(history.getItem(0).getMessage(),"Changed status from NEW to INPROGRESS")

	}
	
	/**
	 * Test the setting methods in the Requirement class in the src package
	 * 
	 * @author: Benjamin Senecal
	 */
	@Test
	public void settingRequirementFieldsTest () {
		Requirement object = new Requirement(5, "Test", "This is a test");
		
		// setId
		object.setId(10);
		assertEquals(object.getId(), 10);
		
		// setName
		object.setName("Changed");
		assertEquals(object.getName(), "Changed");
		object.setName("01234567890123456789012345678901234567890123456789012345678901234567890123545678901234567890123456789this is extra and should not be included");
		assertEquals(object.getName(), "0123456789012345678901234567890123456789012345678901234567890123456789012354567890123456789012345678");
		
		// setStatus
		//if requirement was just created
		object.setStatus(RequirementStatus.OPEN, true);
		assertEquals(object.getStatus(), RequirementStatus.OPEN);
		ListIterator<Transaction> iter = object.getHistory().getIterator(0);
		assertFalse(iter.hasNext());
		//normal status setting
		object.setName("Name");	// makes checking the transaction history easier
		object.setStatus(RequirementStatus.INPROGRESS, false);
		assertEquals(object.getStatus(), RequirementStatus.INPROGRESS);
		iter = object.getHistory().getIterator(0);
		assertEquals(iter.next().getMessage(), "Changed status of Name from OPEN to INPROGRESS");
		//if the you change it to the current status
		object.setStatus(RequirementStatus.INPROGRESS, false);
		assertEquals(object.getStatus(), RequirementStatus.INPROGRESS);
		iter = object.getHistory().getIterator(1);
		assertFalse(iter.hasNext());
		//if you change it to the current status upon creation
		object.setStatus(RequirementStatus.NEW, true);
		assertEquals(object.getStatus(), RequirementStatus.NEW);
		iter = object.getHistory().getIterator(1);
		assertFalse(iter.hasNext());
		
		// setDescription
		object.setDescription("Changed the description too");
		assertEquals(object.getDescription(), "Changed the description too");

		// setPriority
		//if you change it to the current priority upon creation
		object.setPriority(RequirementPriority.BLANK, true);
		assertEquals(object.getPriority(), RequirementPriority.BLANK);
		iter = object.getHistory().getIterator(1);
		assertFalse(iter.hasNext());
		//if requirement was just created
		object.setPriority(RequirementPriority.HIGH, true);
		assertEquals(object.getPriority(), RequirementPriority.HIGH);
		iter = object.getHistory().getIterator(1);
		assertFalse(iter.hasNext());
		//normal priority setting
		object.setPriority(RequirementPriority.HIGH, false);
		assertEquals(object.getPriority(), RequirementPriority.HIGH);
		iter = object.getHistory().getIterator(1);
		assertFalse(iter.hasNext());
		//if the you change it to the current priority
		object.setPriority(RequirementPriority.LOW, false);
		assertEquals(object.getPriority(), RequirementPriority.LOW);
		iter = object.getHistory().getIterator(1);
		assertEquals(iter.next().getMessage(), "Changed priority of Name from HIGH to LOW");
		
		// setType
		object.setType(RequirementType.USERSTORY);
		assertEquals(object.getType(), RequirementType.USERSTORY);
		
		// TODO: setIteration
		
	}
	
	@Test
	public void testCopyFromRequirement() {
		Requirement r = new Requirement(0, "name", "desc");
		r.setEffort(4);
		r.setEstimate(4);
		r.setIteration("",false);
		r.setPriority(RequirementPriority.HIGH, true);
		r.setRelease("release 1");
		r.setStatus(RequirementStatus.INPROGRESS, true);
		r.setType(RequirementType.USERSTORY);
		
		Requirement r2 = new Requirement(0, "", "");
		r2.copyFrom(r);
		
		assertEquals(r.getId(), 0);
		assertEquals(r.getName(), "name");
		assertEquals(r.getDescription(), "desc");
		assertEquals(r.getPriority(), RequirementPriority.HIGH);
		assertEquals(r.getRelease(), "release 1");
		assertEquals(r.getStatus(), RequirementStatus.INPROGRESS);
		assertEquals(r.getType(), RequirementType.USERSTORY);
	}
	
	@Test
	public void testToString() {
		Requirement r = new Requirement(0, "name", "desc");
		assertEquals("name", r.toString());
		assertEquals(r.toString(), r.getName());
	}
	
	@Test
	public void testAssigningRequirementsToPeople() {
		List<String> peopleAssignedTo = Arrays.asList("Gabe", "Ben");
		Requirement r = new Requirement(0, "name", "desc");
		r.setAssignedTo(peopleAssignedTo);
		assertEquals(r.getAssignedTo(), peopleAssignedTo);
	}
	
}